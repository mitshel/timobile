package com.example.timobile.presentation.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.timobile.R;
import com.example.timobile.bases.BaseFragment;
import com.example.timobile.data.SQLite.SQLiteController;
import com.example.timobile.data.model.TimAdSoft;

import java.util.ArrayList;

import static com.example.timobile.data.SQLite.SQLiteTable.OBJ_ID;

public class SoftwareFragment extends BaseFragment {

    ArrayList<TimAdSoft> timAdSofts;
    SQLiteController sqLiteController;

    LinearLayout linearLayoutContainer;

    public static SoftwareFragment newInstance(Integer objId) {
        SoftwareFragment fragment = new SoftwareFragment();
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
            int objId = getArguments().getInt(OBJ_ID);
            timAdSofts = sqLiteController.getTimAdSofts(objId);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(getLayoutId(), container, false);
        initView(v);
        createView(timAdSofts);
        return v;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_software;
    }

    @Override
    protected void initView(View v) {
        linearLayoutContainer = find(R.id.software_container, v);

    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void createView(ArrayList<TimAdSoft> timAdSofts) {
        if (timAdSofts == null) {
            return;
        }

        for (TimAdSoft s : timAdSofts) {

            View viewBorder = new View(getContext());
            ViewGroup.LayoutParams viewBorderParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 20);
            viewBorder.setLayoutParams(viewBorderParams);
            viewBorder.setBackground(getContext().getDrawable(R.drawable.border_bottom));

            //Контейнер для TextView
            ViewGroup.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            LinearLayout layoutSoftwareName = new LinearLayout(getContext());
            LinearLayout layoutSoftwareVersion = new LinearLayout(getContext());
            LinearLayout layoutGroupAd = new LinearLayout(getContext());
            LinearLayout layoutType = new LinearLayout(getContext());
            LinearLayout layoutBorder = new LinearLayout(getContext());

            layoutSoftwareName.setLayoutParams(linearLayoutParams);
            layoutSoftwareVersion.setLayoutParams(linearLayoutParams);
            layoutGroupAd.setLayoutParams(linearLayoutParams);
            layoutType.setLayoutParams(linearLayoutParams);
            layoutSoftwareName.setOrientation(LinearLayout.HORIZONTAL);
            layoutSoftwareVersion.setOrientation(LinearLayout.HORIZONTAL);
            layoutGroupAd.setOrientation(LinearLayout.HORIZONTAL);
            layoutType.setOrientation(LinearLayout.HORIZONTAL);
            layoutBorder.setOrientation(LinearLayout.HORIZONTAL);


            //TextView с название стобца
            ViewGroup.LayoutParams textNameParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);

            TextView textSoftwareName = new TextView(getContext());
            TextView textSoftwareVersionName = new TextView(getContext());
            TextView textGroupAdName = new TextView(getContext());
            TextView textTypeName = new TextView(getContext());


            textSoftwareName.setLayoutParams(textNameParams);
            textSoftwareVersionName.setLayoutParams(textNameParams);
            textGroupAdName.setLayoutParams(textNameParams);
            textTypeName.setLayoutParams(textNameParams);

            textSoftwareName.setText("Наименование");
            textSoftwareVersionName.setText("Версия ПО");
            textGroupAdName.setText("Группа AD");


            //TextView с текстом столбца
            ViewGroup.LayoutParams textParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 2);

            TextView textSoftware = new TextView(getContext());
            TextView textSoftwareVersion = new TextView(getContext());
            TextView textGroupAd = new TextView(getContext());


            textSoftware.setLayoutParams(textParams);
            textSoftwareVersion.setLayoutParams(textParams);
            textGroupAd.setLayoutParams(textParams);

            textSoftware.setText(s.getSoft_name());
            textSoftwareVersion.setText(s.getSoft_ver());
            textGroupAd.setText(s.getG_name());


            layoutSoftwareName.addView(textSoftwareName, 0);
            layoutSoftwareVersion.addView(textSoftwareVersionName, 0);
            layoutGroupAd.addView(textGroupAdName, 0);

            layoutSoftwareName.addView(textSoftware, 1);
            layoutSoftwareVersion.addView(textSoftwareVersion, 1);
            layoutGroupAd.addView(textGroupAd, 1);

            layoutBorder.addView(viewBorder);

            linearLayoutContainer.addView(layoutSoftwareName);
            linearLayoutContainer.addView(layoutSoftwareVersion);
            linearLayoutContainer.addView(layoutGroupAd);
            linearLayoutContainer.addView(layoutType);
            linearLayoutContainer.addView(layoutBorder);


//            softwareName.setText(s.getSoft_name());
//            version.setText(s.getSoft_ver());
//            group.setText(s.getG_name());
//            textView4.setText("-");

//            tableRow.addView(softwareName);
//            tableRow.addView(version);
//            tableRow.addView(group);
//            tableRow.addView(textView4);


        }
    }
}