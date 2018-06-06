package com.example.timobile.presentation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.timobile.R;
import com.example.timobile.bases.BaseFragment;
import com.example.timobile.data.SQLite.SQLiteController;
import com.example.timobile.data.model.TimCheckCFG;

import static com.example.timobile.data.SQLite.SQLiteTable.OBJ_ID;

public class TimCheckCFGFragment extends BaseFragment {

    TextView
            CPU,
            Memory_in_Mb,
            Total_HDD_in_Mb,
            Computer_Name,
            IP_Addr,
            MAC_Addr,
            CPU_BrandName,
            CPU_Freq_in_Mhz,
            Current_User_Name,
            Record_Date,
            f_err,
            info,
            dbcode,
            filename,
            load_date,
            conf_id,
            system;

    SQLiteController sqLiteController;
    TimCheckCFG cfg;
    private int objId;
    private TimCheckCFG mainInfo;

    public TimCheckCFGFragment() {
        // Required empty public constructor
    }

    public static TimCheckCFGFragment newInstance(Integer objId) {
        TimCheckCFGFragment fragment = new TimCheckCFGFragment();
        Bundle data = new Bundle();
        data.putInt(OBJ_ID, objId);
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sqLiteController = new SQLiteController(getContext());

        if (getArguments() != null) {
            objId = getArguments().getInt(OBJ_ID);
            cfg = sqLiteController.getTimCheckCFG(objId);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(getLayoutId(), container, false);
        initView(v);

        setMainInfo(cfg);

        return v;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tim_check_cfg;
    }

    @Override
    protected void initView(View v) {
        CPU = find(R.id.cpu, v);
        Memory_in_Mb = find(R.id.memory_in_mb, v);
        Total_HDD_in_Mb = find(R.id.Total_HDD_in_Mb, v);
        Computer_Name = find(R.id.computer_name, v);
        IP_Addr = find(R.id.ip_addr, v);
        MAC_Addr = find(R.id.mac_addr, v);
        CPU_BrandName = find(R.id.cpu_brandname, v);
        CPU_Freq_in_Mhz = find(R.id.cpu_freq_in_mhz, v);
        Current_User_Name = find(R.id.current_user_name, v);
        Record_Date = find(R.id.record_date, v);
        info = find(R.id.info, v);
        filename = find(R.id.filename, v);
        load_date = find(R.id.load_date, v);
        conf_id = find(R.id.conf_id, v);
    }

    public void setMainInfo(TimCheckCFG cfg) {
        if (cfg == null) {
            return;
        }

        CPU.setText(cfg.getCPU());
        Memory_in_Mb.setText(cfg.getMemory_in_Mb());
        Total_HDD_in_Mb.setText(cfg.getTotal_HDD_in_Mb());
        Computer_Name.setText(cfg.getComputer_Name());
        IP_Addr.setText(cfg.getIP_Addr());
        MAC_Addr.setText(cfg.getMAC_Addr());
        CPU_BrandName.setText(cfg.getCPU_BrandName());
        CPU_Freq_in_Mhz.setText(cfg.getCPU_Freq_in_Mhz());
        Current_User_Name.setText(cfg.getCurrent_User_Name());
        Record_Date.setText(cfg.getRecord_Date());
        info.setText(cfg.getInfo());
        filename.setText(cfg.getFilename());
        load_date.setText(cfg.getLoad_date());
        conf_id.setText(cfg.getConf_id());
    }
}
