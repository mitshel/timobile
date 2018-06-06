package com.example.timobile.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.timobile.R;
import com.example.timobile.bases.BaseActivity;
import com.example.timobile.data.SQLite.SQLiteController;
import com.example.timobile.data.model.TimTo;

public class TechServiseFullInfoActivity extends BaseActivity {

    TextView to_id;
    TextView req_date;
    TextView def_req;
    TextView maintance_req;
    TextView def_real;
    TextView maintance_real;
    TextView isp_name;
    TextView req_owner_name;
    TextView req_end_date;

    private int objId;
    SQLiteController sqLiteController;

    TimTo timTo;

    public static String TIM_TO_OBJECT = "Tim To Object";

    public static void start(Context context, TimTo t) {
        Intent starter = new Intent(context, TechServiseFullInfoActivity.class);
        starter.putExtra(TIM_TO_OBJECT, t);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sqLiteController = new SQLiteController(this);

        if (getIntent() != null) {
            timTo = (TimTo) getIntent().getSerializableExtra(TIM_TO_OBJECT);
            setTimToText(timTo);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tech_servise_full_info;
    }

    public void setTimToText(TimTo t) {
        to_id.setText(t.getTo_id());
        req_date.setText(t.getReq_date());
        def_req.setText(t.getDef_req());
        maintance_req.setText(t.getMaintance_req());
        def_real.setText(t.getDef_real());
        maintance_real.setText(t.getMaintance_real());
        isp_name.setText(t.getIsp_name());
        req_owner_name.setText(t.getReq_owner_name());
        req_end_date.setText(t.getRem_end_date());

    }

    @Override
    protected void initView() {
        to_id = find(R.id.to_id);
        req_date = find(R.id.req_date);
        def_req = find(R.id.def_req);
        maintance_req = find(R.id.maintance_req);
        def_real = find(R.id.def_real);
        maintance_real = find(R.id.maintance_real);
        isp_name = find(R.id.isp_name);
        req_owner_name = find(R.id.req_owner_name);
        req_end_date = find(R.id.req_end_date);
    }
}
