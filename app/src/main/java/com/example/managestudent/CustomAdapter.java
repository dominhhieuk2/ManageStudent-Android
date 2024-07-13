package com.example.managestudent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Student> students;

    public CustomAdapter(Context context, ArrayList<Student> students) {
        this.context = context;
        this.students = students;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_item, parent, false);
        }

        Student currentStudent = students.get(position);

        TextView textViewName = convertView.findViewById(R.id.textViewName);
        TextView textViewDate = convertView.findViewById(R.id.textViewDate);
        TextView textViewGender = convertView.findViewById(R.id.textViewGender);
        TextView textViewAddress = convertView.findViewById(R.id.textViewAddress);
        TextView textViewIdNganh = convertView.findViewById(R.id.textViewIdNganh);

        textViewName.setText("Tên: " + currentStudent.getName());
        textViewDate.setText("Năm sinh: " + currentStudent.getDate());
        textViewGender.setText("Giới tính: " + currentStudent.getGender());
        textViewAddress.setText("Địa chỉ: " + currentStudent.getAddress());
        textViewIdNganh.setText("Ngành: " + String.valueOf(currentStudent.getIdNganh()));

        return convertView;
    }
}
