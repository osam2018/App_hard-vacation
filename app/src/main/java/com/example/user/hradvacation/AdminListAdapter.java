package com.example.user.hradvacation;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;
import org.json.JSONException;

import java.util.ArrayList;

public class AdminListAdapter extends BaseAdapter {
    LayoutInflater inflater = null;
    private ArrayList<AdminShowItemData> dataholder = null;
    private int listCnt = 0;
    private Intent intent;
    private Context context;

    public AdminListAdapter( ArrayList<AdminShowItemData> _datali, Context context, Intent intent){
        dataholder = _datali;
        listCnt = _datali.size();
        this.context = context;
        this.intent = intent;
    }

    @Override
    public int getCount(){
        return listCnt;
    }

    @Override
    public Object getItem(int pos){
        return dataholder.get(pos);
    }

    @Override
    public long getItemId(int pos){
        return 0;
    }

    @Override
    public View getView(int pos, View convertView, final ViewGroup parent){
        final int index = pos;
        if(convertView == null){
            final Context context = parent.getContext();
            if(inflater == null){
                inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }

        final TextView usernameText = (TextView) convertView.findViewById(R.id.username);
        final TextView dateTodateText = (TextView) convertView.findViewById(R.id.dateTodate);

        usernameText.setText(dataholder.get(pos).userNameStr);
        dateTodateText.setText(dataholder.get(pos).userDateStr);


        Button button_accept = (Button) convertView.findViewById(R.id.button_accept);
        Button button_reject = (Button) convertView.findViewById(R.id.button_reject);



        button_accept.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                try{
                    JSONObject js_obj = new JSONObject();
                    js_obj.put("id", Integer.parseInt(dataholder.get(index).id));
                    js_obj.put("state", "true");
                    com.example.user.hradvacation.CommProtocol Communication = new CommProtocol();
                    //리턴 값 처리

                    String resultJSON = Communication.execute("manager_accept", js_obj.toString(), intent.getStringExtra("token")).get();
                    Log.i("STATUS", Integer.toString(index));

                    ((ListView) parent).clearChoices();
                    dataholder.remove(index);
                    listCnt -= 1;
                    notifyDataSetChanged();

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        button_reject.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                try{
                    JSONObject js_obj = new JSONObject();
                    js_obj.put("id", Integer.parseInt(dataholder.get(index).id));
                    js_obj.put("state", "false");
                    com.example.user.hradvacation.CommProtocol Communication = new CommProtocol();
                    //리턴 값 처리
                    String resultJSON = Communication.execute("manager_deny", js_obj.toString(), intent.getStringExtra("token")).get();

                    ((ListView) parent).clearChoices();
                    dataholder.remove(index);
                    listCnt -= 1;
                    notifyDataSetChanged();

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        if(!dataholder.get(pos).isClicked){
            button_accept.setVisibility(View.INVISIBLE);
            button_reject.setVisibility(View.INVISIBLE);
        }else{
            button_accept.setVisibility(View.VISIBLE);
            button_reject.setVisibility(View.VISIBLE);
        }

        return convertView;
    }
}