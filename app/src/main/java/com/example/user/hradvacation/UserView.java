package com.example.user.hradvacation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.content.Intent;
import org.json.JSONObject;
import org.json.JSONException;
import org.json.JSONArray;

import java.util.ArrayList;


public class UserView extends AppCompatActivity {
    private int current_clicked = -1;
    private ListView  gen_listView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view);

        String[] state_string = {"대기", "승인", "거절"};
        ArrayList<UserShowItemData> dataholder = new ArrayList<>();

        Intent intent = getIntent();
        String zeroString = intent.getStringExtra("data");
        try{
            JSONObject oneString = new JSONObject(zeroString);
            if(oneString.getString("state").equals("true")){
                JSONArray JSONdata = new JSONArray(oneString.getString("data"));
                String[] Stringdata = ParsingJSON.toStringArray(JSONdata);
                for(String s : Stringdata) {
                    UserShowItemData item = new UserShowItemData();
                    JSONObject jsonObject = new JSONObject(s);
                    item.userDateStartStr = jsonObject.getString("start").split("T")[0];
                    item.userDateEndStr = jsonObject.getString("end").split("T")[0];
                    item.state = Integer.parseInt(jsonObject.getString("cert"));
                    item.id = jsonObject.getString("id");
                    item.parsedString = state_string[item.state];
                    item.isClicked = false;
                    item.url = jsonObject.getString("hash");
                    dataholder.add(item);
                }
            }
        }catch(JSONException e){
            e.printStackTrace();
        }

        gen_listView = (ListView) findViewById(R.id.user_manage_list);
        final UserListAdapter gen_Adapter = new UserListAdapter(dataholder, getApplicationContext(), intent);
        gen_listView.setAdapter(gen_Adapter);
        gen_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                if (current_clicked != -1) {
                    ((UserShowItemData) gen_Adapter.getItem(current_clicked)).isClicked = false;
                    ((UserShowItemData) gen_Adapter.getItem(position)).isClicked = true;
                    current_clicked = position;
                } else {
                    ((UserShowItemData) gen_Adapter.getItem(position)).isClicked = true;
                    current_clicked = position;
                }
                gen_Adapter.notifyDataSetChanged();

                Intent intent2 = new Intent(getApplicationContext(), QRPopupActivity.class);

                intent2.putExtra("data", ((UserShowItemData)gen_Adapter.getItem(position)).url);
                intent2.putExtra("cert",((UserShowItemData)gen_Adapter.getItem(position)).parsedString);
                intent2.putExtra("filename",((UserShowItemData)gen_Adapter.getItem(position)).userDateStartStr);

                startActivity(intent2);
            }
        });



    }
}
