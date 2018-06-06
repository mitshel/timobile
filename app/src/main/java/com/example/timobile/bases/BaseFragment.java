package com.example.timobile.bases;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.View;


/**
 * Created by benidict on 02/09/2017.
 */

public abstract class BaseFragment extends Fragment {

    //Сокращенный вариант получения view по id
    @SuppressWarnings("unchecked")
    protected <V extends View> V find(@IdRes int id, View v) {
        return (V) v.findViewById(id);
    }

    protected abstract int getLayoutId();

    protected abstract void initView(View v);

}