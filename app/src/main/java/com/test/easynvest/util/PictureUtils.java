package com.test.easynvest.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ThumbnailUtils;
import android.util.DisplayMetrics;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by DT on 7/18/17.
 */

public class PictureUtils {
    private static final String TAG = "CameraExif";
    private static int mPictureSize;

    // Returns the degrees in clockwise. Values are 0, 90, 180, or 270.
    public static int getOrientation(byte[] jpeg) {
        if (jpeg == null) {
            return 0;
        }

        int offset = 0;
        int length = 0;

        // ISO/IEC 10918-1:1993(E)
        while (offset + 3 < jpeg.length && (jpeg[offset++] & 0xFF) == 0xFF) {
            int marker = jpeg[offset] & 0xFF;

            // Check if the marker is a padding.
            if (marker == 0xFF) {
                continue;
            }
            offset++;

            // Check if the marker is SOI or TEM.
            if (marker == 0xD8 || marker == 0x01) {
                continue;
            }
            // Check if the marker is EOI or SOS.
            if (marker == 0xD9 || marker == 0xDA) {
                break;
            }

            // Get the length and check if it is reasonable.
            length = pack(jpeg, offset, 2, false);
            if (length < 2 || offset + length > jpeg.length) {
                Log.e(TAG, "Invalid length");
                return 0;
            }

            // Break if the marker is EXIF in APP1.
            if (marker == 0xE1 && length >= 8 &&
                    pack(jpeg, offset + 2, 4, false) == 0x45786966 &&
                    pack(jpeg, offset + 6, 2, false) == 0) {
                offset += 8;
                length -= 8;
                break;
            }

            // Skip other markers.
            offset += length;
            length = 0;
        }

        // JEITA CP-3451 Exif Version 2.2
        if (length > 8) {
            // Identify the byte order.
            int tag = pack(jpeg, offset, 4, false);
            if (tag != 0x49492A00 && tag != 0x4D4D002A) {
                Log.e(TAG, "Invalid byte order");
                return 0;
            }
            boolean littleEndian = (tag == 0x49492A00);

            // Get the offset and check if it is reasonable.
            int count = pack(jpeg, offset + 4, 4, littleEndian) + 2;
            if (count < 10 || count > length) {
                Log.e(TAG, "Invalid offset");
                return 0;
            }
            offset += count;
            length -= count;

            // Get the count and go through all the elements.
            count = pack(jpeg, offset - 2, 2, littleEndian);
            while (count-- > 0 && length >= 12) {
                // Get the tag and check if it is orientation.
                tag = pack(jpeg, offset, 2, littleEndian);
                if (tag == 0x0112) {
                    // We do not really care about type and count, do we?
                    int orientation = pack(jpeg, offset + 8, 2, littleEndian);
                    switch (orientation) {
                        case 1:
                            return 0;
                        case 3:
                            return 180;
                        case 6:
                            return 90;
                        case 8:
                            return 270;
                    }
                    Log.i(TAG, "Unsupported orientation");
                    return 0;
                }
                offset += 12;
                length -= 12;
            }
        }

        Log.i(TAG, "Orientation not found");
        return 0;
    }

    private static int pack(byte[] bytes, int offset, int length,
                            boolean littleEndian) {
        int step = 1;
        if (littleEndian) {
            offset += length - 1;
            step = -1;
        }

        int value = 0;
        while (length-- > 0) {
            value = (value << 8) | (bytes[offset] & 0xFF);
            offset += step;
        }
        return value;
    }


    public static Bitmap rotate(Bitmap bitmap, int degree) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        Matrix mtx = new Matrix();
        mtx.setRotate(degree);
        Bitmap rotated = Bitmap.createBitmap(bitmap, 0, 0, w, h, mtx, true);
        return rotated;
    }

    public static Bitmap decodeSampledBitmapFromResource(byte[] data, int maxSize) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        //options.inPurgeable = true;
        BitmapFactory.decodeByteArray(data, 0, data.length, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, maxSize);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(data, 0, data.length, options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int maxSize) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        mPictureSize = Math.max(height, width);
        int inSampleSize = 1;

        if (mPictureSize > maxSize) {

            final int halfSize = mPictureSize / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfSize / inSampleSize) >= maxSize) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static boolean cropFile(File file) {

        Bitmap rotated = null;
        boolean fileCroped = false;
        long startTime = System.currentTimeMillis(); //fetch starting time

        while(fileCroped||(System.currentTimeMillis()-startTime)<10000) {

            if (file.exists() && file.length() > 0) {
                try {
                    Bitmap bmp = BitmapFactory.decodeFile(file.getAbsolutePath());

                    if (bmp == null) return false;

                    int height = bmp.getHeight();
                    int width = bmp.getWidth();

                    int prop = 0;
                    Bitmap cropped = null;
                    if (height > width) {
                        prop = (width * 1000) / height;
                        cropped = ThumbnailUtils.extractThumbnail(bmp, prop, 1000, ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
                    } else {
                        prop = (height * 1000) / width;
                        cropped = ThumbnailUtils.extractThumbnail(bmp, 1000, prop, ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
                    }
                    //high chances of out of memory... :p (cropped will be recycled inside if necessary)
                    rotated = ExifUtil.rotateBitmap(file.getAbsolutePath(), cropped);
                    //save cropped as the "correct" bitmap (smaller size for upload and stuff)

                    FileOutputStream fOut = new FileOutputStream(file);
                    rotated.compress(Bitmap.CompressFormat.JPEG, 90, fOut);
                    fOut.flush();
                    fOut.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }

                if (rotated != null) rotated.recycle();
                fileCroped = true;
                return true;
            }
        }
        return false;
    }

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static float convertPixelsToDp(float px, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return dp;
    }

}
