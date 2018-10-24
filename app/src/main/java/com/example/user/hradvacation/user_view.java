package com.example.user.hradvacation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class user_view extends AppCompatActivity {
    private int current_clicked = -1;
    private ListView  gen_listView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_view);

        String[] examples = {"2018-10-22 ~ 2018-10-26", "2018-10-22 ~ 2018-04-02", "2018-10-33-2019-54-79"};
        String[] state_string = {"대기", "승인", "거절"};
        ArrayList<UserShowItemData> dataholder = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            UserShowItemData item = new UserShowItemData();
            // You must apply data that is from server.
            item.userDateStr = examples[i];
            item.state = i % 3;
            item.parsedString = state_string[item.state];
            item.isClicked = false;
            dataholder.add(item);
        }

        gen_listView = (ListView) findViewById(R.id.admin_manage_list);
        final UserDataListAdapter gen_Adapter = new UserDataListAdapter(dataholder);
        gen_listView.setAdapter(gen_Adapter);
        gen_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                if(current_clicked != -1){
                    ((UserShowItemData) gen_Adapter.getItem(current_clicked)).isClicked = false;
                }
                if(current_clicked == position){
                    ((UserShowItemData) gen_Adapter.getItem(position)).isClicked = false;
                    current_clicked = -1;
                }else {
                    ((UserShowItemData) gen_Adapter.getItem(position)).isClicked = true;
                    current_clicked = position;
                }
                gen_Adapter.notifyDataSetChanged();
            }
        });



    }
}
