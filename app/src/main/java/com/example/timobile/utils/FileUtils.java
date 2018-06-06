package com.example.timobile.utils;

import android.os.Environment;

import java.io.File;

import static com.example.timobile.data.SQLite.SQLiteTable.DB_FOLDER_NAME;
import static com.example.timobile.data.SQLite.SQLiteTable.DB_NAME;

/**
 * Created by Администратор on 25.04.2018.
 */

public class FileUtils {


    /**
     * Получить путь к базе данных
     */
    public static String getDatabasePath() {
        String path = Environment.
                getExternalStorageDirectory().getAbsolutePath() +
                File.separator +
                DB_FOLDER_NAME +
                File.separator +
                DB_NAME;

        return path;
    }

    /**
     * Проверяет существует ли база данных
     */
    public static boolean checkAndCreateFolder() {
        String path = Environment.
                getExternalStorageDirectory().getAbsolutePath() +
                File.separator +
                DB_FOLDER_NAME;

        File folder = new File(path);
        folder.setExecutable(true);
        folder.setReadable(true);
        folder.setWritable(true);

        return folder.mkdir();
    }
}
