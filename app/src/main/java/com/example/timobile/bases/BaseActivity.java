package com.example.timobile.bases;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        setView();
    }

    //Сокращенный вариант получения view по id
    @SuppressWarnings("unchecked")
    protected <V extends View> V find(@IdRes int id) {
        return (V) findViewById(id);
    }

    protected abstract int getLayoutId();

    protected abstract void initView();



    public void setView() {
    }
}