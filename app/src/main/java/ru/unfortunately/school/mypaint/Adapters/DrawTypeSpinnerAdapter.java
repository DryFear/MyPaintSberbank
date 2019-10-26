package ru.unfortunately.school.mypaint.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.unfortunately.school.mypaint.Models.DrawTypes;
import ru.unfortunately.school.mypaint.R;

public class DrawTypeSpinnerAdapter extends BaseAdapter {

    private List<DrawTypes> mDrawTypes;

    public DrawTypeSpinnerAdapter(List<DrawTypes> drawTypes){
        mDrawTypes = new ArrayList<>(drawTypes);
    }

    @Override
    public int getCount() {
        return mDrawTypes.size();
    }

    @Override
    public DrawTypes getItem(int position) {
        return mDrawTypes.get(position);
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
        holder.mValue.setText(getItem(position).mTypeNameRes);
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
