package com.tekit.software.mystonecalculator.Util;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class StonCaluApp extends Application {

    private static StonCaluApp mInstance;


    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Stetho.initializeWithDefaults(this);
        //Fabric.with(this, new Crashlytics());



    }

    public static StonCaluApp getInstance() {
        return mInstance;
    }

}
