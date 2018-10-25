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

import org.json.JSONObject;

import java.util.ArrayList;

public class UserListAdapter extends BaseAdapter {
    LayoutInflater inflater = null;
    private ArrayList<UserShowItemData> dataholder = null;
    private int listCnt = 0;
    private Intent intent;
    private Context context;

    public UserListAdapter(ArrayList<UserShowItemData> _datali, Context context, Intent intent){
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
            convertView = inflater.inflate(R.layout.user_listview_item, parent, false);
        }

        TextView dateStartText = (TextView) convertView.findViewById(R.id.dateStart);
        TextView dateEndText = (TextView) convertView.findViewById(R.id.dateEnd);
        TextView stateText = (TextView) convertView.findViewById(R.id.data_state);

        dateStartText.setText(dataholder.get(pos).userDateStartStr);
        dateEndText.setText(dataholder.get(pos).userDateEndStr);
        stateText.setText(dataholder.get(pos).parsedString);

        Button button_reject = (Button) convertView.findViewById(R.id.button_reject);

        button_reject.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    JSONObject js_obj = new JSONObject();
                    js_obj.put("id", dataholder.get(index).id);
                    js_obj.put("state", "true");
                    com.example.user.hradvacation.CommProtocol Communication = new CommProtocol();
                    //리턴 값 처리

                    String resultJSON = Communication.execute("hero_delete", js_obj.toString(), intent.getStringExtra("token")).get();
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

        if(!dataholder.get(pos).isClicked){
            button_reject.setVisibility(View.INVISIBLE);
        }else{
            button_reject.setVisibility(View.VISIBLE);
        }

        return convertView;
    }
}