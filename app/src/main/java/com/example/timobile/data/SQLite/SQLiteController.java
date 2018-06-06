package com.example.timobile.data.SQLite;

import android.content.Context;

import com.example.timobile.data.model.TimAdSoft;
import com.example.timobile.data.model.TimCheckCFG;
import com.example.timobile.data.model.TimObj;
import com.example.timobile.data.model.TimTo;

import java.util.ArrayList;

/**
 * Created by benidict on 02/09/2017.
 */

@SuppressWarnings("WeakerAccess")
public class SQLiteController {

    private SQLiteHelper sqliteHelper;
    Context context;

    public SQLiteController(Context mContext) {
        this.context = mContext;
        sqliteHelper = new SQLiteHelper(context);
    }


    /**
     * ИНФО(1)
     */
    public TimObj getMainInfoByObjId(Integer objId) {
        return sqliteHelper.getMainInfo(objId.toString());
    }

    /***
     * ТОиР(3)
     */
    public ArrayList<TimTo> getTimTo(Integer objId) {
        return sqliteHelper.getTimTo(objId.toString());
    }

    /***
     * CheckCFG(4)
     */
    public TimCheckCFG getTimCheckCFG(Integer objId) {
        return sqliteHelper.getTimCheckCFG(objId.toString());
    }

    /**
     * ПО(2)
     */
    public ArrayList<TimAdSoft> getTimAdSofts(Integer objId) {
        return sqliteHelper.getTimAdSofts(objId.toString());
    }
}