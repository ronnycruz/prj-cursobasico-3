package com.rgsystems.loantable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by rcruz on 6/28/2017.
 **/

public class AmortizeAdapter extends ArrayAdapter<String[]>
{
    public AmortizeAdapter(Context context, ArrayList<String[]> objects){
        super(context, 0, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        String row[] = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.amortize_text_view_item, parent, false);
        }

        TextView tvMonth        = convertView.findViewById(R.id.tvMonth);
        TextView tvPrinciple    = convertView.findViewById(R.id.tvPrinciple);
        TextView tvInterest     = convertView.findViewById(R.id.tvInterest);
        TextView tvRemaining    = convertView.findViewById(R.id.tvRemaining);

        tvMonth.setText(row[0]);
        tvPrinciple.setText(row[1]);
        tvInterest.setText(row[2]);
        tvRemaining.setText(row[3]);

        return convertView;
    }
}
