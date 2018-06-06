package com.example.timobile.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.example.timobile.R;
import com.example.timobile.bases.BaseActivity;
import com.example.timobile.presentation.adapter.ViewPagerAdapter;
import com.example.timobile.presentation.fragment.InfoFragment;
import com.example.timobile.presentation.fragment.SoftwareFragment;
import com.example.timobile.presentation.fragment.TechServiceFragment;
import com.example.timobile.presentation.fragment.TimCheckCFGFragment;

import static com.example.timobile.data.SQLite.SQLiteTable.OBJ_ID;

public class AllInfoActivity extends BaseActivity {

    private int objId = 0;

    // UI Elements
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public static void start(Context context, int obj_id) {
        Intent starter = new Intent(context, AllInfoActivity.class);
        starter.putExtra(OBJ_ID, obj_id);
        context.startActivity(starter);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle data = getIntent().getExtras();
        if (data != null) {
            objId = data.getInt(OBJ_ID);
        }
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);//связывает ViewPager и TabLayout вместе
    }


    @Override
    protected int getLayoutId() {
        return R.layout.info_activity;
    }

    @Override
    protected void initView() {
        viewPager = find(R.id.viewpager);
        tabLayout = find(R.id.tablayout);
    }


    private void setupViewPager(ViewPager viewPager) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        ViewPagerAdapter adapter = new ViewPagerAdapter(fragmentManager);

        adapter.addFragment(InfoFragment.newInstance(objId), "ИНФО");
        adapter.addFragment(SoftwareFragment.newInstance(objId), "ПО");
        adapter.addFragment(TechServiceFragment.newInstance(objId), "ТОиР");
        adapter.addFragment(TimCheckCFGFragment.newInstance(objId), "CheckCFG");

        viewPager.setAdapter(adapter);
    }

}
