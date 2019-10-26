package ru.unfortunately.school.mypaint.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.unfortunately.school.mypaint.Models.MyColors;
import ru.unfortunately.school.mypaint.R;

public class ColorSpinnerAdapter extends BaseAdapter {

    private List<MyColors> mColors;

    public ColorSpinnerAdapter(List<MyColors> colors){
        mColors = new ArrayList<>(colors);
    }

    @Override
    public int getCount() {
        return mColors.size();
    }

    @Override
    public MyColors getItem(int position) {
        return mColors.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(
                    android.R.layout.simple_list_item_1, parent, false);
            ViewHolder holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.mValue.setText(getItem(position).mColorNameRes);
        return convertView;
    }

    private class ViewHolder{
        private final TextView mValue;

        ViewHolder(View view){
            mValue = view.findViewById(android.R.id.text1);
            mValue.setTextSize(view.getResources().getDimension(R.dimen.spinner_text_size));
        }
    }
}
