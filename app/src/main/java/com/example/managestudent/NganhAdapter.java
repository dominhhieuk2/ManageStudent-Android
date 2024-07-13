package com.example.managestudent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class NganhAdapter extends ArrayAdapter<Nganh> {

    private Context mContext;
    private int mResource;

    public NganhAdapter(Context context, int resource, ArrayList<Nganh> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(mResource, parent, false);
        }

        Nganh nganh = getItem(position);

        if (nganh != null) {
            TextView tvNganhName = convertView.findViewById(R.id.tvNganhName);
            tvNganhName.setText(nganh.getName());
        }

        return convertView;
    }
}
