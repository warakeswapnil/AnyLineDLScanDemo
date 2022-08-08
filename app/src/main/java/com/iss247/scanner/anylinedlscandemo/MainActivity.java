package com.iss247.scanner.anylinedlscandemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnAnyLineScanner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAnyLineScanner = findViewById(R.id.btnAnyLineScanner);
        btnAnyLineScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAnylineScannerClick();
            }
        });
    }

    private void onAnylineScannerClick() {
        Intent intent = new Intent(this, AnyLineDLScannerDemoActivity.class);
        startActivity(intent);
    }
}