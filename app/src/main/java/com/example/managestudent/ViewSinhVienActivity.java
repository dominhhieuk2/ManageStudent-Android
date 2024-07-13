package com.example.managestudent;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ViewSinhVienActivity extends AppCompatActivity {

    private ListView listViewSinhVien;
    private DatabaseHelper dbHelper;
    private ArrayList<Student> studentList;
    private CustomAdapter adapter;

    private EditText editTextName, editTextDate, editTextGender, editTextAddress, editTextIdNganh;
    private Button buttonAdd, buttonUpdate, buttonDelete;
    private int selectedStudentId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sinhvien);

        listViewSinhVien = findViewById(R.id.listViewSinhVien);
        dbHelper = new DatabaseHelper(this);

        editTextName = findViewById(R.id.editTextName);
        editTextDate = findViewById(R.id.editTextDate);
        editTextGender = findViewById(R.id.editTextGender);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextIdNganh = findViewById(R.id.editTextIdNganh);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);

        refreshData();

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String date = editTextDate.getText().toString();
                String gender = editTextGender.getText().toString();
                String address = editTextAddress.getText().toString();
                int idNganh = Integer.parseInt(editTextIdNganh.getText().toString());

                if (dbHelper.addSinhVien(name, date, gender, address, idNganh)) {
                    Toast.makeText(ViewSinhVienActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                    refreshData();
                } else {
                    Toast.makeText(ViewSinhVienActivity.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
                }
                clearForm();
            }
        });

        listViewSinhVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student selectedStudent = studentList.get(position);
                selectedStudentId = selectedStudent.getId();
                editTextName.setText(selectedStudent.getName());
                editTextDate.setText(selectedStudent.getDate());
                editTextGender.setText(selectedStudent.getGender());
                editTextAddress.setText(selectedStudent.getAddress());
                editTextIdNganh.setText(String.valueOf(selectedStudent.getIdNganh()));
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedStudentId != -1) {
                    String name = editTextName.getText().toString();
                    String date = editTextDate.getText().toString();
                    String gender = editTextGender.getText().toString();
                    String address = editTextAddress.getText().toString();
                    int idNganh = Integer.parseInt(editTextIdNganh.getText().toString());

                    if (dbHelper.updateSinhVien(selectedStudentId, name, date, gender, address, idNganh)) {
                        Toast.makeText(ViewSinhVienActivity.this, "Dữ liệu đã được cập nhật!", Toast.LENGTH_SHORT).show();
                        refreshData();
                    } else {
                        Toast.makeText(ViewSinhVienActivity.this, "Dữ liệu không được cập nhật!", Toast.LENGTH_SHORT).show();
                    }
                    clearForm();
                } else {
                    Toast.makeText(ViewSinhVienActivity.this, "Không sinh viên nào được chọn", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedStudentId != -1) {
                    if (dbHelper.deleteSinhVien(selectedStudentId)) {
                        Toast.makeText(ViewSinhVienActivity.this, "Dữ liệu đã được xóa!", Toast.LENGTH_SHORT).show();
                        refreshData();
                    } else {
                        Toast.makeText(ViewSinhVienActivity.this, "Dữ liệu chưa được xóa!", Toast.LENGTH_SHORT).show();
                    }
                    clearForm();
                } else {
                    Toast.makeText(ViewSinhVienActivity.this, "Không sinh viên nào được chọn", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void refreshData() {
        studentList = dbHelper.getAllSinhVien();
        adapter = new CustomAdapter(this, studentList);
        listViewSinhVien.setAdapter(adapter);
    }

    private void clearForm() {
        editTextName.setText("");
        editTextDate.setText("");
        editTextGender.setText("");
        editTextAddress.setText("");
        editTextIdNganh.setText("");
        selectedStudentId = -1;
    }
}
