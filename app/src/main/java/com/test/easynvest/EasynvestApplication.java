package com.test.easynvest;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.test.easynvest.dagger.component.ApplicationComponent;
import com.test.easynvest.dagger.component.DaggerApplicationComponent;
import com.test.easynvest.dagger.module.ApplicationModule;

import timber.log.Timber;

public class EasynvestApplication extends MultiDexApplication {

    private static final String TAG = EasynvestApplication.class.getSimpleName();
    private RefWatcher refWatcher;
    private static int stateCounter;

    private static Context mContext;

    public static Context getStaticContext() {
        return mContext;
    }

    protected ApplicationComponent applicationComponent;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static EasynvestApplication get(Context context) {
        return (EasynvestApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        stateCounter = 0;
        mContext = this;
        refWatcher = LeakCanary.install(this);
        initImageLoader(this);
        Timber.plant(new Timber.DebugTree());


        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);

    }

    public ApplicationComponent getComponent(){
        return applicationComponent;
    }


    public static RefWatcher getRefWatcher(Context context) {
        EasynvestApplication application = (EasynvestApplication) context.getApplicationContext();
        return application.refWatcher;
    }


    /**
     * @return true if application is on background
     * */
    public static boolean isApplicationOnBackground()
    {
        return stateCounter == 0;
    }

    //to be called on each Activity onStart()
    public static void activityStarted() {
        stateCounter++;
    }

    //to be called on each Activity onStop()
    public static void activityStopped() {
        stateCounter--;
    }

    private void initImageLoader(Context ctx) {

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .resetViewBeforeLoading(false)  // default
                .cacheInMemory(true) // default
                .cacheOnDisk(true) // default
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default
                .build();


        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(ctx);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024);
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app
        config.defaultDisplayImageOptions(options);

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());

    }

}