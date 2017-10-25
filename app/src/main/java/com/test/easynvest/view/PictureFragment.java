package com.test.easynvest.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.BuildConfig;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.test.easynvest.R;
import com.test.easynvest.util.AndroidVersionHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by DT on 10/25/17.
 */

public class PictureFragment extends Fragment {


    private BaseActivity activity;
    public String[] PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    public static final int ASK_MULTIPLE_PERMISSION_REQUEST_CODE = 5;
    protected Uri mFileUri;
    protected String mImagePath;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = ((BaseActivity) getActivity());
    }


    public void checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!hasPermissions(getActivity(), PERMISSIONS)) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) ||
                        ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    askPermissions();
                } else {
                    requestPermissions(PERMISSIONS, ASK_MULTIPLE_PERMISSION_REQUEST_CODE);
                }
            } else {
                showCameraDialog();
            }
        } else {
            showCameraDialog();
        }
    }

    public void showCameraDialog() {

        CharSequence items[] = new CharSequence[] {getString(R.string.dialog_choose_picture_camera_option), getString(R.string.dialog_choose_picture_gallery_option)};

        final AlertDialog alert = new AlertDialog.Builder(getActivity(), R.style.DialogTheme)
                .setTitle(R.string.dialog_picture_title)
                .setItems(items, (dialogInterface, i) -> {
                    if (i == 0)
                        openCamera();
                    else
                        openGallery();
                })
                .create();

        alert.setButton(AlertDialog.BUTTON_POSITIVE, "CANCELAR", (dialog, which) -> {
            alert.dismiss();
        });

        alert.show();

    }

    public void openCamera() {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
            mFileUri = prepareFile();
            if (mFileUri != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mFileUri);
                takePictureIntent.putExtra("android.intent.extras.CAMERA_FACING", 1);
                startActivityForResult(takePictureIntent, activity.PICK_CAMERA);
            } else {
                Toast.makeText(activity, getResources().getString(R.string.fail_prepare_camera_alert), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(activity, getResources().getString(R.string.fail_initialize_camera_alert), Toast.LENGTH_SHORT).show();
        }
    }

    public void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Selecionar Foto"), activity.PICK_IMAGE);
    }

    protected void showBasicAlertWithAction(String title, String message) {
        final AlertDialog alert = new AlertDialog.Builder(getActivity(), R.style.DialogTheme)
                .setTitle(title)
                .setMessage(message)
                .create();

        alert.setButton(AlertDialog.BUTTON_POSITIVE, "OK", (dialog, which) -> {
            requestPermissions(PERMISSIONS, ASK_MULTIPLE_PERMISSION_REQUEST_CODE);
            alert.dismiss();
        });

        alert.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCELAR", (dialog, which) -> {
            alert.dismiss();
        });

        alert.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case ASK_MULTIPLE_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                        showCameraDialog();
                }
            }
        }
    }


    public static boolean hasPermissions(Context context, String... permissions) {
        for (String permission : permissions)
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED)
                return false;
        return true;
    }


    public void askPermissions() {
        showBasicAlertWithAction(getResources().getString(R.string.dialog_permission_title),
                getResources().getString(R.string.camera_permission_message));
    }

    private File createImageFile() throws IOException {
        String imageFileName = "IMAGE" + System.currentTimeMillis();
        File storageDir = activity.getExternalFilesDir(null);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        return image;
    }

    public Uri prepareFile() {
        try {
            File photoFile = createImageFile();
            if (photoFile != null) {
                if(AndroidVersionHelper.isNougatOrHigher()){
                    return FileProvider.getUriForFile(activity, BuildConfig.APPLICATION_ID + ".provider", photoFile);
                } else {
                    return Uri.fromFile(photoFile);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }


}
