package com.example.managestudent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnAddSinhVien, btnViewSinhVien, btnAddNganh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddSinhVien = findViewById(R.id.btnAddSinhVien);
        btnViewSinhVien = findViewById(R.id.btnViewSinhVien);
        btnAddNganh = findViewById(R.id.btnAddNganh);

        btnAddSinhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddSinhVienActivity.class);
                startActivity(intent);
            }
        });

        btnViewSinhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewSinhVienActivity.class);
                startActivity(intent);
            }
        });

        btnAddNganh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddNganhActivity.class);
                startActivity(intent);
            }
        });
    }
}
