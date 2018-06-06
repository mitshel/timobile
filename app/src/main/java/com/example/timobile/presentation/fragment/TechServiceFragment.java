package com.example.timobile.presentation.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.timobile.R;
import com.example.timobile.bases.BaseFragment;
import com.example.timobile.data.SQLite.SQLiteController;
import com.example.timobile.data.model.TimTo;
import com.example.timobile.presentation.TechServiseFullInfoActivity;

import java.util.ArrayList;

import static com.example.timobile.data.SQLite.SQLiteTable.OBJ_ID;


public class TechServiceFragment extends BaseFragment {


    ArrayList<TimTo> timTos;
    SQLiteController sqLiteController;

    LinearLayout linearLayoutContainer;

    private int objId;

    public TechServiceFragment() {
    }

    public static TechServiceFragment newInstance(Integer objId) {
        TechServiceFragment fragment = new TechServiceFragment();
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
            timTos = sqLiteController.getTimTo(objId);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(getLayoutId(), container, false);
        initView(v);
        createView(timTos);

        return v;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_service_tech;
    }

    @Override
    protected void initView(View v) {
        linearLayoutContainer = find(R.id.service_tech_container, v);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void createView(ArrayList<TimTo> timTos) {
        if (timTos == null) {
            return;
        }

        for (final TimTo t : timTos) {

            View viewBorder = new View(getContext());
            ViewGroup.LayoutParams viewBorderParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 20);
            viewBorder.setLayoutParams(viewBorderParams);
            viewBorder.setBackground(getContext().getDrawable(R.drawable.border_bottom));

            //Контейнер для TextView
            ViewGroup.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            LinearLayout allViewContainer = new LinearLayout(getContext());
            allViewContainer.setLayoutParams(linearLayoutContainer.getLayoutParams());
            allViewContainer.setOrientation(linearLayoutContainer.getOrientation());

            LinearLayout layout_to_id = new LinearLayout(getContext());
            LinearLayout layout_req_date = new LinearLayout(getContext());
            LinearLayout layout_def_req = new LinearLayout(getContext());
            LinearLayout layout_maintance_req = new LinearLayout(getContext());
            LinearLayout layout_def_real = new LinearLayout(getContext());
            LinearLayout layout_maintance_real = new LinearLayout(getContext());
            LinearLayout layout_isp_name = new LinearLayout(getContext());
            LinearLayout layout_req_owner_name = new LinearLayout(getContext());
            LinearLayout layout_req_end_date = new LinearLayout(getContext());

            LinearLayout layout_border = new LinearLayout(getContext());
            layout_border.setLayoutParams(viewBorderParams);

            layout_to_id.setLayoutParams(linearLayoutParams);
            layout_req_date.setLayoutParams(linearLayoutParams);
            layout_def_req.setLayoutParams(linearLayoutParams);
            layout_maintance_req.setLayoutParams(linearLayoutParams);
            layout_to_id.setLayoutParams(linearLayoutParams);
            layout_def_real.setLayoutParams(linearLayoutParams);
            layout_maintance_real.setLayoutParams(linearLayoutParams);
            layout_isp_name.setLayoutParams(linearLayoutParams);
            layout_to_id.setLayoutParams(linearLayoutParams);
            layout_req_owner_name.setLayoutParams(linearLayoutParams);

            layout_to_id.setOrientation(LinearLayout.HORIZONTAL);
            layout_req_date.setOrientation(LinearLayout.HORIZONTAL);
            layout_def_req.setOrientation(LinearLayout.HORIZONTAL);
            layout_maintance_req.setOrientation(LinearLayout.HORIZONTAL);
            layout_def_real.setOrientation(LinearLayout.HORIZONTAL);
            layout_maintance_real.setOrientation(LinearLayout.HORIZONTAL);
            layout_isp_name.setOrientation(LinearLayout.HORIZONTAL);
            layout_req_owner_name.setOrientation(LinearLayout.HORIZONTAL);

            //TextView с название стобца
            ViewGroup.LayoutParams textNameParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);

            TextView to_idName = new TextView(getContext());
            TextView req_dateName = new TextView(getContext());
            TextView def_reqName = new TextView(getContext());
            TextView maintance_reqName = new TextView(getContext());
            TextView def_realName = new TextView(getContext());
            TextView maintance_realName = new TextView(getContext());
            TextView isp_nameName = new TextView(getContext());
            TextView req_owner_nameName = new TextView(getContext());
            TextView req_end_dateName = new TextView(getContext());


            to_idName.setLayoutParams(textNameParams);
            req_dateName.setLayoutParams(textNameParams);
            def_reqName.setLayoutParams(textNameParams);
            maintance_reqName.setLayoutParams(textNameParams);
            def_realName.setLayoutParams(textNameParams);
            maintance_realName.setLayoutParams(textNameParams);
            isp_nameName.setLayoutParams(textNameParams);
            req_owner_nameName.setLayoutParams(textNameParams);
            req_end_dateName.setLayoutParams(textNameParams);

            to_idName.setText("№: ");
            req_dateName.setText("Дата дефекта: ");
            def_reqName.setText("Описание дефекта: ");
            maintance_reqName.setText("Вид обслуживания: ");
            def_realName.setText("Выявленные дефекты: ");
            maintance_realName.setText("Выполнено обслуживание: ");
            isp_nameName.setText("Дата реморнта: ");
            req_owner_nameName.setText("Исполнитель: ");
            req_end_dateName.setText("Назнач. Сроки: ");

            //TextView с текстом столбца
            ViewGroup.LayoutParams textParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 2);

            TextView to_id = new TextView(getContext());
            TextView req_date = new TextView(getContext());
            TextView def_req = new TextView(getContext());
            TextView maintance_req = new TextView(getContext());
            TextView def_real = new TextView(getContext());
            TextView maintance_real = new TextView(getContext());
            TextView isp_name = new TextView(getContext());
            TextView req_owner_name = new TextView(getContext());
            TextView req_end_date = new TextView(getContext());

            to_id.setFilters(new InputFilter[]{new InputFilter.LengthFilter(50)});
            req_date.setFilters(new InputFilter[]{new InputFilter.LengthFilter(50)});
            def_req.setFilters(new InputFilter[]{new InputFilter.LengthFilter(50)});
            maintance_req.setFilters(new InputFilter[]{new InputFilter.LengthFilter(50)});
            def_real.setFilters(new InputFilter[]{new InputFilter.LengthFilter(50)});
            maintance_real.setFilters(new InputFilter[]{new InputFilter.LengthFilter(50)});
            isp_name.setFilters(new InputFilter[]{new InputFilter.LengthFilter(50)});
            req_owner_name.setFilters(new InputFilter[]{new InputFilter.LengthFilter(50)});
            req_end_date.setFilters(new InputFilter[]{new InputFilter.LengthFilter(50)});

            to_id.setLayoutParams(textParams);
            req_date.setLayoutParams(textParams);
            def_req.setLayoutParams(textParams);
            maintance_req.setLayoutParams(textParams);
            def_real.setLayoutParams(textParams);
            maintance_real.setLayoutParams(textParams);
            isp_name.setLayoutParams(textParams);
            req_owner_name.setLayoutParams(textParams);
            req_end_date.setLayoutParams(textParams);

            to_id.setText(t.getTo_id());
            req_date.setText(t.getReq_date());
            def_req.setText(t.getDef_req());
            maintance_req.setText(t.getMaintance_req());
            def_real.setText(t.getDef_real());
            maintance_real.setText(t.getMaintance_real());
            isp_name.setText(t.getIsp_name());
            req_owner_name.setText(t.getReq_owner_name());
            req_end_date.setText(t.getRem_end_date());


            layout_to_id.addView(to_idName, 0);
            layout_req_date.addView(req_dateName, 0);
            layout_def_req.addView(def_reqName, 0);
            layout_maintance_req.addView(maintance_reqName, 0);
            layout_def_real.addView(def_realName, 0);
            layout_maintance_real.addView(maintance_realName, 0);
            layout_isp_name.addView(isp_nameName, 0);
            layout_req_owner_name.addView(req_owner_nameName, 0);
            layout_req_end_date.addView(req_end_dateName, 0);

            layout_to_id.addView(to_id, 1);
            layout_req_date.addView(req_date, 1);
            layout_def_req.addView(def_req, 1);
            layout_maintance_req.addView(maintance_req, 1);
            layout_def_real.addView(def_real, 1);
            layout_maintance_real.addView(maintance_real, 1);
            layout_isp_name.addView(isp_name, 1);
            layout_req_owner_name.addView(req_owner_name, 1);
            layout_req_end_date.addView(req_end_date, 1);

            layout_border.addView(viewBorder);

            allViewContainer.addView(layout_to_id);
            allViewContainer.addView(layout_req_date);
            allViewContainer.addView(layout_def_req);
            allViewContainer.addView(layout_maintance_req);
            allViewContainer.addView(layout_def_real);
            allViewContainer.addView(layout_maintance_real);
            allViewContainer.addView(layout_isp_name);
            allViewContainer.addView(layout_req_owner_name);
            allViewContainer.addView(layout_req_end_date);
            allViewContainer.addView(layout_border);

            allViewContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TechServiseFullInfoActivity.start(getContext(), t);
                }
            });

            linearLayoutContainer.addView(allViewContainer);

        }
    }
}
