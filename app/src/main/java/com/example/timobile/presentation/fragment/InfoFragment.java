package com.example.timobile.presentation.fragment;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.RequiresPermission;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.timobile.R;
import com.example.timobile.bases.BaseFragment;
import com.example.timobile.data.SQLite.SQLiteController;
import com.example.timobile.data.model.TimObj;

import static com.example.timobile.data.SQLite.SQLiteTable.OBJ_ID;

public class InfoFragment extends BaseFragment {

    SQLiteController sqLiteController;

    // Data model
    TimObj equipment;

    // UI Elements
    TextView obj_ID, info_about_name, obj_name, invN, sn, d_prih, nnakl, prim, nw_name, sit_balance, fnoWork, f_spis, bux_name,
            bux_xar, filial_name,
            pn, nomnum, y_vip, seal_N, mark_name, type_name,
            building_name, otv_serv_name, Town_name, Ip_addr, Mol_name;


    private int objId;

    @RequiresPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sqLiteController = new SQLiteController(getContext());
        if (getArguments() != null) {
            objId = getArguments().getInt(OBJ_ID);
            equipment = sqLiteController.getMainInfoByObjId(objId);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(getLayoutId(), container, false);
        initView(v);

        setMainInfo(equipment);

        return v;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_info;
    }

    public static InfoFragment newInstance(Integer objId) {
        InfoFragment fragment = new InfoFragment();
        Bundle data = new Bundle();
        data.putInt(OBJ_ID, objId);
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    protected void initView(View v) {
        filial_name = find(R.id.filial_name, v);
        info_about_name = find(R.id.infoAboutMan, v);
        bux_name = find(R.id.bux_name, v);
        obj_ID = find(R.id.obj_ID, v);
        obj_name = find(R.id.obj_name, v);
        bux_xar = find(R.id.bux_xar, v);
        invN = find(R.id.invN, v);
        sn = find(R.id.sn, v);
        d_prih = find(R.id.d_prih, v);
        nnakl = find(R.id.nnakl, v);
        pn = find(R.id.pn, v);
        nomnum = find(R.id.nomnum, v);
        y_vip = find(R.id.y_vip, v);
        seal_N = find(R.id.seal_N, v);
        mark_name = find(R.id.mark_name, v);
        type_name = find(R.id.type_name, v);
        building_name = find(R.id.building_name, v);
        otv_serv_name = find(R.id.otv_serv_name, v);
        Town_name = find(R.id.Town_name, v);
        Ip_addr = find(R.id.Ip_addr, v);
        Mol_name = find(R.id.Mol_name, v);
    }

    public void setMainInfo(TimObj e) {
        if (e == null) {
            return;
        }
        filial_name.setText(e.getFilial_name());
        bux_name.setText(e.getBux_name());
        bux_xar.setText(e.getBux_xar());
        y_vip.setText(String.valueOf(e.getY_vip()));
        y_vip.setText(String.valueOf(e.getY_vip()));
        info_about_name.setText(e.getDol() + " " + e.getFio());
        obj_ID.setText(String.valueOf(e.getObj_ID()));
        obj_name.setText(e.getObj_name());
        invN.setText(e.getInvN());
        sn.setText(e.getSn());
        d_prih.setText(e.getD_prih());
        nnakl.setText(e.getNnakl());
        pn.setText(e.getPn());
        nomnum.setText(e.getNomnum());
        seal_N.setText(e.getSeal_N());
        mark_name.setText(e.getMan_name());
        type_name.setText(e.getType_name());
        building_name.setText(e.getBuilding_name());
        otv_serv_name.setText(e.getOtv_serv_name());
        Town_name.setText(e.getTown_name());
        Ip_addr.setText(e.getIp_addr());
        Mol_name.setText(e.getMol_name());
    }
}