package com.example.timobile;

import android.app.Application;

import com.example.timobile.utils.FileUtils;

/**
 * Created by Грунин Роман on 27.04.2018.
 */

public class App extends Application {
    public App() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //Создаём папку для базы данных
        FileUtils.checkAndCreateFolder();
    }
}
