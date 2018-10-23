package com.example.user.hradvacation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdminListAdapter extends BaseAdapter {
    LayoutInflater inflater = null;
    private ArrayList<AdminShowItemData> dataholder = null;
    private int listCnt = 0;

    public AdminListAdapter(ArrayList<AdminShowItemData> _datali){
        dataholder = _datali;
        listCnt = _datali.size();
    }

    @Override
    public int getCount(){
        return listCnt;
    }

    @Override
    public Object getItem(int pos){
        return null;
    }

    @Override
    public long getItemId(int pos){
        return 0;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent){
        if(convertView == null){
            final Context context = parent.getContext();
            if(inflater == null){
                inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }

        TextView usernameText = (TextView) convertView.findViewById(R.id.username);
        TextView dateTodateText = (TextView) convertView.findViewById(R.id.dateTodate);

        usernameText.setText(dataholder.get(pos).userNameStr);
        dateTodateText.setText(dataholder.get(pos).userDateStr);
        return convertView;
    }
}