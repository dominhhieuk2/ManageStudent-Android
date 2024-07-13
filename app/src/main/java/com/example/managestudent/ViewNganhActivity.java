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

public class ViewNganhActivity extends AppCompatActivity {

    private ListView listViewNganh;
    private DatabaseHelper dbHelper;
    private ArrayList<Nganh> nganhList;
    private NganhAdapter adapter;

    private EditText editTextNganhName;
    private Button buttonAddNganh, buttonUpdateNganh, buttonDeleteNganh;
    private int selectedNganhId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_nganh);

        listViewNganh = findViewById(R.id.listViewNganh);
        dbHelper = new DatabaseHelper(this);

        editTextNganhName = findViewById(R.id.editTextNganhName);
        buttonAddNganh = findViewById(R.id.buttonAddNganh);
        buttonUpdateNganh = findViewById(R.id.buttonUpdateNganh);
        buttonDeleteNganh = findViewById(R.id.buttonDeleteNganh);

        refreshData();

        buttonAddNganh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nganhName = editTextNganhName.getText().toString();

                if (dbHelper.addNganh(nganhName)) {
                    Toast.makeText(ViewNganhActivity.this, "Đã thêm ngành", Toast.LENGTH_SHORT).show();
                    refreshData();
                } else {
                    Toast.makeText(ViewNganhActivity.this, "Thêm ngành thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonUpdateNganh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nganhName = editTextNganhName.getText().toString();

                if (selectedNganhId == -1) {
                    Toast.makeText(ViewNganhActivity.this, "Vui lòng chọn ngành cần sửa", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (dbHelper.updateNganh(selectedNganhId, nganhName)) {
                    Toast.makeText(ViewNganhActivity.this, "Đã cập nhật ngành", Toast.LENGTH_SHORT).show();
                    refreshData();
                    selectedNganhId = -1;
                } else {
                    Toast.makeText(ViewNganhActivity.this, "Cập nhật ngành thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonDeleteNganh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedNganhId == -1) {
                    Toast.makeText(ViewNganhActivity.this, "Vui lòng chọn ngành cần xóa", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (dbHelper.deleteNganh(selectedNganhId)) {
                    Toast.makeText(ViewNganhActivity.this, "Đã xóa ngành", Toast.LENGTH_SHORT).show();
                    refreshData();
                    selectedNganhId = -1;
                } else {
                    Toast.makeText(ViewNganhActivity.this, "Xóa ngành thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        listViewNganh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Nganh nganh = nganhList.get(position);
                selectedNganhId = nganh.getId();
                editTextNganhName.setText(nganh.getName());
            }
        });

        listViewNganh.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Nganh nganh = nganhList.get(position);
                selectedNganhId = nganh.getId();
                editTextNganhName.setText(nganh.getName());
                return true;
            }
        });
    }

    private void refreshData() {
        nganhList = dbHelper.getAllNganhs();
        adapter = new NganhAdapter(this, R.layout.item_nganh, nganhList);
        listViewNganh.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void clearForm() {
        editTextNganhName.setText("");
    }
}

