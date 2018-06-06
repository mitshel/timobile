package com.example.timobile.presentation;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timobile.R;
import com.example.timobile.bases.BaseActivity;

import static com.example.timobile.data.SQLite.SQLiteTable.OBJ_ID;

public class MainActivity extends BaseActivity implements View.OnClickListener {


    private static final int PERMISSION_REQUEST_CODE = 111;
    // UI Elements
    Button btnFindItem;
    Button btnStartScan;
    EditText inputFindItem;

    public static final int SCAN_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Проверяет есть ли у нас разрешения
        if (!isPermissionGranted()) {
            requestMultiplePermissions();//Запросить разрешения
        }

        btnFindItem.setOnClickListener(this);
        btnStartScan.setOnClickListener(this);

        inputFindItem.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    startInfo();
                }
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SCAN_REQUEST_CODE && resultCode == RESULT_OK) {
            String qrCode = data.getStringExtra(OBJ_ID);
            inputFindItem.setText(qrCode);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        inputFindItem = find(R.id.input_find_item);
        btnFindItem = find(R.id.btn_find_item);
        btnStartScan = find(R.id.btn_start_scan);
    }

    @Override
    public void setView() {
        super.setView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_find_item:
                startInfo();
                break;

            case R.id.btn_start_scan:
                startScan();
                break;
        }
    }

    private void startScan() {
        Intent startScanIntent = new Intent(MainActivity.this, ScanQrCodeActivity.class);
        startActivityForResult(startScanIntent, SCAN_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this, "Разрешения получены", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(MainActivity.this, "Разрешения не получены", Toast.LENGTH_LONG).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void startInfo() {
        String input = inputFindItem.getText().toString();
        if (!TextUtils.isEmpty(input))
            AllInfoActivity.start(MainActivity.this, Integer.parseInt(input));
    }


    /**
     * Запросить разрешения для приложения
     */
    private void requestMultiplePermissions() {
        ActivityCompat.requestPermissions(this,
                new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA
                },
                PERMISSION_REQUEST_CODE);
    }

    private boolean isPermissionGranted() {
        // проверяем разрешение - есть ли оно у нашего приложения
        int permissionWrite = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionWrite == PackageManager.PERMISSION_DENIED) {
            return false;
        }

        int permissionCamera = ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (permissionCamera == PackageManager.PERMISSION_DENIED) {
            return false;
        }
        return true;
    }
}
