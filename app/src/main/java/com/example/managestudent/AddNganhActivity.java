package com.example.managestudent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddNganhActivity extends AppCompatActivity {

    private EditText etNameNganh;
    private Button btnSaveNganh;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_nganh);

        etNameNganh = findViewById(R.id.etNameNganh);
        btnSaveNganh = findViewById(R.id.btnSaveNganh);

        dbHelper = new DatabaseHelper(this);

        btnSaveNganh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameNganh = etNameNganh.getText().toString();

                if (dbHelper.addNganh(nameNganh)) {
                    Toast.makeText(AddNganhActivity.this, "Ngành đã được thêm", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(AddNganhActivity.this, "Lỗi khi thêm ngành", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
