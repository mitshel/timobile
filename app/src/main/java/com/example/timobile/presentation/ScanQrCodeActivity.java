package com.example.timobile.presentation;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import com.example.timobile.R;
import com.example.timobile.bases.BaseActivity;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

import static com.example.timobile.data.SQLite.SQLiteTable.OBJ_ID;

public class ScanQrCodeActivity extends BaseActivity {


    // Camera element
    BarcodeDetector barcodeDetector;
    CameraSource cameraSource;

    // UI Elements
    SurfaceView cameraPreview;
    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStop() {
        super.onStop();
        cameraSource.stop();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_scan_qr_code;
    }

    @Override
    protected void initView() {
        cameraPreview = (SurfaceView) find(R.id.cameraPreview);
        txtResult = find(R.id.txtResult);

        //Устанавливаем тип того, что будем сканировать
        barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE)//Формат код а
                .build();

        cameraSource = new CameraSource.Builder(this, barcodeDetector)
                .setRequestedPreviewSize(1, 100000)//Разрешение
//                .setAutoFocusEnabled(true)//Автофокус
                .build();

        cameraPreview.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                    }
                    cameraSource.start(cameraPreview.getHolder());
                } catch (IOException e) {

                }

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            //При рапозновании QR кода вызывается этот метод
            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> qrCode = detections.getDetectedItems();


                //Если распознование прошло успешно возвращаем результат в преведущую активность
                if (qrCode.size() != 0) {
                    txtResult.post(new Runnable() {
                        @Override
                        public void run() {
                            txtResult.setText(qrCode.valueAt(0).displayValue);
                        }
                    });
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra(OBJ_ID, qrCode.valueAt(0).displayValue);
                    setResult(RESULT_OK, returnIntent);
                    finish();
                }
            }
        });
    }
}
