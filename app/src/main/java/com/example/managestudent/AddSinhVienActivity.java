package com.example.managestudent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddSinhVienActivity extends AppCompatActivity {

    private EditText etName, etDate, etGender, etAddress, etIdNganh;
    private Button btnSave;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sinhvien);

        etName = findViewById(R.id.etName);
        etDate = findViewById(R.id.etDate);
        etGender = findViewById(R.id.etGender);
        etAddress = findViewById(R.id.etAddress);
        etIdNganh = findViewById(R.id.etIdNganh);
        btnSave = findViewById(R.id.btnSave);

        dbHelper = new DatabaseHelper(this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String date = etDate.getText().toString();
                String gender = etGender.getText().toString();
                String address = etAddress.getText().toString();
                int idNganh = Integer.parseInt(etIdNganh.getText().toString());

                if (dbHelper.addSinhVien(name, date, gender, address, idNganh)) {
                    Toast.makeText(AddSinhVienActivity.this, "Sinh viên đã được thêm", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(AddSinhVienActivity.this, "Lỗi khi thêm sinh viên", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
