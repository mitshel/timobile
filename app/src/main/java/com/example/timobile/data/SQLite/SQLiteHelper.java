package com.example.timobile.data.SQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;

import com.example.timobile.data.model.TimAdSoft;
import com.example.timobile.data.model.TimCheckCFG;
import com.example.timobile.data.model.TimObj;
import com.example.timobile.data.model.TimTo;
import com.example.timobile.utils.FileUtils;

import java.util.ArrayList;

import static com.example.timobile.data.SQLite.SQLiteTable.BUILDING_NAME;
import static com.example.timobile.data.SQLite.SQLiteTable.BUX_NAME;
import static com.example.timobile.data.SQLite.SQLiteTable.BUX_XAR;
import static com.example.timobile.data.SQLite.SQLiteTable.COMINFO;
import static com.example.timobile.data.SQLite.SQLiteTable.DBCODE;
import static com.example.timobile.data.SQLite.SQLiteTable.DB_NAME;
import static com.example.timobile.data.SQLite.SQLiteTable.DOL;
import static com.example.timobile.data.SQLite.SQLiteTable.D_PRIH;
import static com.example.timobile.data.SQLite.SQLiteTable.FILIAL_NAME;
import static com.example.timobile.data.SQLite.SQLiteTable.FIO;
import static com.example.timobile.data.SQLite.SQLiteTable.FLOOR;
import static com.example.timobile.data.SQLite.SQLiteTable.FNO_WORK;
import static com.example.timobile.data.SQLite.SQLiteTable.F_SPIS;
import static com.example.timobile.data.SQLite.SQLiteTable.INVN_COMMENT;
import static com.example.timobile.data.SQLite.SQLiteTable.INV_N;
import static com.example.timobile.data.SQLite.SQLiteTable.IP_ADDR;
import static com.example.timobile.data.SQLite.SQLiteTable.KABINET_N;
import static com.example.timobile.data.SQLite.SQLiteTable.KABINET_NAME;
import static com.example.timobile.data.SQLite.SQLiteTable.LOGIN;
import static com.example.timobile.data.SQLite.SQLiteTable.MAN_NAME;
import static com.example.timobile.data.SQLite.SQLiteTable.MOL_NAME;
import static com.example.timobile.data.SQLite.SQLiteTable.NNAKL;
import static com.example.timobile.data.SQLite.SQLiteTable.NOMNUM;
import static com.example.timobile.data.SQLite.SQLiteTable.NW_NAME;
import static com.example.timobile.data.SQLite.SQLiteTable.OBJ_ID;
import static com.example.timobile.data.SQLite.SQLiteTable.OBJ_NAME;
import static com.example.timobile.data.SQLite.SQLiteTable.OTV_SERV_NAME;
import static com.example.timobile.data.SQLite.SQLiteTable.PN;
import static com.example.timobile.data.SQLite.SQLiteTable.PRICH_SPIS;
import static com.example.timobile.data.SQLite.SQLiteTable.PRIM;
import static com.example.timobile.data.SQLite.SQLiteTable.PROMPL_NAME;
import static com.example.timobile.data.SQLite.SQLiteTable.SCHET;
import static com.example.timobile.data.SQLite.SQLiteTable.SEAL_N;
import static com.example.timobile.data.SQLite.SQLiteTable.SIT_BALANCE;
import static com.example.timobile.data.SQLite.SQLiteTable.SN;
import static com.example.timobile.data.SQLite.SQLiteTable.TAB;
import static com.example.timobile.data.SQLite.SQLiteTable.TELEPHONE_NUMBER;
import static com.example.timobile.data.SQLite.SQLiteTable.TIM_AD_SOFT;
import static com.example.timobile.data.SQLite.SQLiteTable.TIM_CHKCFG_S;
import static com.example.timobile.data.SQLite.SQLiteTable.TIM_OBJ;
import static com.example.timobile.data.SQLite.SQLiteTable.TIM_TO;
import static com.example.timobile.data.SQLite.SQLiteTable.TOWN_NAME;
import static com.example.timobile.data.SQLite.SQLiteTable.TYPE_NAME;
import static com.example.timobile.data.SQLite.SQLiteTable.Y_VIP;

@SuppressWarnings("WeakerAccess")
public class SQLiteHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;

    private static final String TAG = SQLiteHelper.class.getSimpleName();

    private static SQLiteDatabase myDataBase;

    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
        getDataBaseInstant();
    }

    /**
     * Получаем SINGLETON нашей базы данных
     */
    private synchronized SQLiteDatabase getDataBaseInstant() {

        if (myDataBase != null) {
            return myDataBase;
        } else {
            myDataBase = updateDataBase();
        }
        return myDataBase;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_NAME);
        onCreate(db);
    }

    /**
     * Обновить/открыть базу данных
     */
    private SQLiteDatabase updateDataBase() throws SQLException {
        //Open the database
        Log.d(TAG, "updateDataBase: " + FileUtils.getDatabasePath());
        myDataBase = SQLiteDatabase.openDatabase(FileUtils.getDatabasePath(), null, SQLiteDatabase.OPEN_READWRITE);
        if (myDataBase == null) {
            return null;
        }

        return myDataBase;
    }

    @Override
    public synchronized void close() {
        getDataBaseInstant().close();
        super.close();
    }

    /**
     * Для ИНФО фрагмента
     *
     * @param objId
     * @return TibObj
     */
    public TimObj getMainInfo(String objId) {
        TimObj e;

        String selection = OBJ_ID + " =? ";
        String[] selectionArgs = {objId};

//select a.*, b.bdname
//        from tim_obj a, tim_filial b
//        where a.dbcode=b.bdcode

        Cursor c = getDataBaseInstant().query(TIM_OBJ, null,
                selection, selectionArgs, null, null, null);

        if (c != null && c.moveToFirst()) {

            e = new TimObj();
            e.setInvN(stringFromCursor(INV_N, c));
            e.setObj_ID(integerFromCursor(OBJ_ID, c));
            e.setSn(stringFromCursor(SN, c));
            e.setBux_xar(stringFromCursor(BUX_XAR, c));
            e.setBux_name(stringFromCursor(BUX_NAME, c));
            e.setD_prih(stringFromCursor(D_PRIH, c));
            e.setNnakl(stringFromCursor(NNAKL, c));
            e.setPrim(stringFromCursor(PRIM, c));
            e.setNw_name(stringFromCursor(NW_NAME, c));
            e.setSit_balance(integerFromCursor(SIT_BALANCE, c));
            e.setFnoWork(integerFromCursor(FNO_WORK, c));
            e.setF_spis(integerFromCursor(F_SPIS, c));
            e.setPn(stringFromCursor(PN, c));
            e.setInvN_comment(stringFromCursor(INVN_COMMENT, c));
            e.setNomnum(stringFromCursor(NOMNUM, c));
            e.setSchet(stringFromCursor(SCHET, c));
            e.setY_vip(integerFromCursor(Y_VIP, c));
            e.setSeal_N(stringFromCursor(SEAL_N, c));
            e.setType_name(stringFromCursor(TYPE_NAME, c));
            e.setKabinet_n(stringFromCursor(KABINET_N, c));
            e.setKabinet_name(stringFromCursor(KABINET_NAME, c));
            e.setFloor(stringFromCursor(FLOOR, c));
            e.setBuilding_name(stringFromCursor(BUILDING_NAME, c));
            e.setOtv_serv_name(stringFromCursor(OTV_SERV_NAME, c));
            e.setPrompl_name(stringFromCursor(PROMPL_NAME, c));
            e.setTown_name(stringFromCursor(TOWN_NAME, c));
            e.setDol(stringFromCursor(DOL, c));
            e.setTab(stringFromCursor(TAB, c));
            e.setLogin(stringFromCursor(LOGIN, c));
            e.setTelephoneNumber(stringFromCursor(TELEPHONE_NUMBER, c));
            e.setMan_name(stringFromCursor(MAN_NAME, c));
            e.setFio(stringFromCursor(FIO, c));
            e.setCominfo(stringFromCursor(COMINFO, c));
            e.setIp_addr(stringFromCursor(IP_ADDR, c));
            e.setMol_name(stringFromCursor(MOL_NAME, c));
            e.setFilial_name(stringFromCursor(FILIAL_NAME, c));
            e.setDbcode(integerFromCursor(DBCODE, c));
            e.setObj_name(stringFromCursor(OBJ_NAME, c));
            e.setPrich_spis(stringFromCursor(PRICH_SPIS, c));
            e.setMark_name(stringFromCursor(MAN_NAME, c));

            return e;
        }
        if (c != null) {
            c.close();
        }

        return null;
    }


    /**
     * Для ПО(2) фрагмента
     *
     * @param objId
     * @return
     */
    public ArrayList<TimAdSoft> getTimAdSofts(String objId) {

        ArrayList<TimAdSoft> timAdSofts = new ArrayList<>();


        String selection = OBJ_ID + " =? ";
        String[] selectionArgs = {objId};

        Cursor c = getDataBaseInstant().query(TIM_AD_SOFT, null,
                selection, selectionArgs, null, null, null);

        if (c != null && c.moveToFirst()) {
            TimAdSoft soft;

            while (c.moveToNext()) {
                soft = new TimAdSoft();
                soft.setSoft_name(stringFromCursor("soft_name", c));
                soft.setSoft_ver(stringFromCursor("soft_ver", c));
                soft.setG_name(stringFromCursor("g_name", c));

                timAdSofts.add(soft);
            }

        }

        if (c != null) {
            c.close();
        }
        if (timAdSofts.size() == 0) {
            return null;
        }

        return timAdSofts;
    }


    /**
     * Для TOиР(3) фрагмента
     *
     * @param objId
     * @return TibObj
     */
    public ArrayList<TimTo> getTimTo(String objId) {

        ArrayList<TimTo> timTos = new ArrayList<>();


        String selection = OBJ_ID + " =? ";
        String[] selectionArgs = {objId};

        Cursor c = getDataBaseInstant().query(TIM_TO, null,
                selection, selectionArgs, null, null, null);

        if (c != null && c.moveToFirst()) {
            TimTo timTo;

            while (c.moveToNext()) {
                timTo = new TimTo();
                timTo.setTo_id(integerFromCursor("to_id", c).toString());
                timTo.setMaintance_req(stringFromCursor("maintance_req", c));
                timTo.setDef_req(stringFromCursor("def_req", c));
                timTo.setIsp_name(stringFromCursor("isp_name", c));
                timTo.setDef_real(stringFromCursor("def_real", c));
                timTo.setReq_date(stringFromCursor("req_date", c));
                timTo.setRem_end_date(stringFromCursor("rem_end_date", c));
                timTo.setReq_owner_name(stringFromCursor("req_owner_name", c));
                timTo.setMaintance_real(stringFromCursor("maintance_real", c));

                timTos.add(timTo);
            }
        }

        if (c != null) {
            c.close();
        }
        if (timTos.size() == 0) {
            return null;
        }

        return timTos;
    }

    private String stringFromCursor(String columnName, Cursor cursor) {
        int columnIndex = cursor.getColumnIndex(columnName);
        if (columnIndex == -1) {
            return ("-");
        }
        String value = cursor.getString(columnIndex);
        if (TextUtils.isEmpty(value) || value.equalsIgnoreCase("null")) {
            return ("-");
        }
        return value;
    }

    private Integer integerFromCursor(String columnName, Cursor cursor) {
        int columnIndex = cursor.getColumnIndex(columnName);
        if (columnIndex == -1) {
            return 0;
        }
        return cursor.getInt(columnIndex);
    }

    public TimCheckCFG getTimCheckCFG(String objId) {
        TimCheckCFG cfg;

        String selection = "conf_id" + " =? ";
        String[] selectionArgs = {objId};


        Cursor c = getDataBaseInstant().query(TIM_CHKCFG_S, null,
                selection, selectionArgs, null, null, null);

        if (c != null && c.moveToFirst()) {

            cfg = new TimCheckCFG();
            cfg.setConf_id(integerFromCursor("conf_id", c).toString());
            cfg.setCPU(stringFromCursor("CPU", c));
            cfg.setMemory_in_Mb(integerFromCursor("Memory_in_Mb", c).toString());
            cfg.setTotal_HDD_in_Mb(integerFromCursor("Total_HDD_in_Mb", c).toString());
            cfg.setComputer_Name(stringFromCursor("Computer_Name", c));
            cfg.setIP_Addr(stringFromCursor("IP_Addr", c));
            cfg.setMAC_Addr(stringFromCursor("MAC_Addr", c));
            cfg.setCPU_BrandName(stringFromCursor("CPU_BrandName", c));
            cfg.setCPU_Freq_in_Mhz(integerFromCursor("CPU_Freq_in_Mhz", c).toString());
            cfg.setCurrent_User_Name(stringFromCursor("Current_User_Name", c));
            cfg.setRecord_Date(stringFromCursor("Record_Date", c));
            cfg.setSystem(stringFromCursor("system", c));
            cfg.setF_err(integerFromCursor("f_err", c).toString());
            cfg.setInfo(stringFromCursor("info", c));
            cfg.setLoad_date(stringFromCursor("load_date", c));
            cfg.setDbcode(integerFromCursor("dbcode", c).toString());
            cfg.setFilename(stringFromCursor("filename", c));

            return cfg;
        }
        if (c != null) {
            c.close();
        }

        return null;
    }
}