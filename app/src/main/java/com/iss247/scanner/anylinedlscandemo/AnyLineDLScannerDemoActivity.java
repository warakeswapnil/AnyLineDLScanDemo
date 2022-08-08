package com.iss247.scanner.anylinedlscandemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.mlkit.vision.barcode.Barcode;

import java.util.HashMap;
import java.util.List;

import io.anyline.AnylineSDK;
import io.anyline.camera.NativeBarcodeResultListener;
import io.anyline.plugin.ScanResult;
import io.anyline.plugin.ScanResultListener;
import io.anyline.plugin.ScanRunSkippedListener;
import io.anyline.plugin.ScanRunSkippedReason;
import io.anyline.plugin.id.ID;
import io.anyline.plugin.id.IdScanViewPlugin;
import io.anyline.plugin.id.Identification;
import io.anyline.view.ScanView;

public class AnyLineDLScannerDemoActivity extends AppCompatActivity implements ScanRunSkippedListener {

    private ScanView anylineDlScanView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_any_line_dlscanner_demo);

        initAnyLineSDK();

        initScanView();
    }

    private void initAnyLineSDK() {
        try {
            String licenseKey = getString(R.string.anyline_license_key);
            AnylineSDK.init(licenseKey, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initScanView() {
        anylineDlScanView = findViewById(R.id.anylineDlScanView);
        anylineDlScanView.init("anyline_driving_license_config.json");
        anylineDlScanView.getScanViewPlugin().addScanRunSkippedListener(this);

        IdScanViewPlugin idScanViewPlugin = (IdScanViewPlugin) anylineDlScanView.getScanViewPlugin();
        idScanViewPlugin.addScanResultListener(new ScanResultListener<ScanResult<ID>>() {
            @Override
            public void onResult(ScanResult<ID> idScanResult) {
                Identification identification = (Identification) idScanResult.getResult();
                HashMap<String, String> data = (HashMap<String, String>) identification.getResultData();
                Log.e("ANYLINE_SCAN_DEMO", "Scan Data = " + data);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        anylineDlScanView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        anylineDlScanView.stop();
    }

    @Override
    public void onRunSkipped(ScanRunSkippedReason scanRunSkippedReason) {
        if (scanRunSkippedReason.getCode() == 5555) {
            Toast.makeText(this, (scanRunSkippedReason.getText()), Toast.LENGTH_SHORT).show();
        }
    }
}