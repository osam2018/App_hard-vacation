package com.example.user.hradvacation;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ListAdapter;
        import android.widget.ListView;
        import android.widget.Toast;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.ArrayList;




public class manager_view extends AppCompatActivity {
    private int current_clicked = -1;
    private ListView  gen_listView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_view);

        //String[] examples = {"상병 안신우", "2018-10-22 ~ 2018-10-26", "상병 곽범주", "2018-10-22 ~ 2018-04-02"};
        ArrayList<AdminShowItemData> dataholder = new ArrayList<>();

        Intent intent = getIntent();
        String zeroString = intent.getStringExtra("data");


        try {
            JSONObject oneString = new JSONObject(zeroString);
            if(oneString.getString("state").equals("true")){
                JSONArray JSONdata = new JSONArray(oneString.getString("data"));
                String[] Stringdata = ParsingJSON.toStringArray(JSONdata);
                //Toast.makeText(getApplicationContext(), Stringdata.toString(), Toast.LENGTH_LONG).show();
                for (String s : Stringdata) {
                    try {
                        AdminShowItemData item = new AdminShowItemData();
                        JSONObject jsonObject = new JSONObject(s);
                        item.userNameStr = jsonObject.getString("username");
                        item.userDateStr = jsonObject.getString("start").split("T")[0] + '~' + jsonObject.getString("end").split("T")[0];
                        item.id = jsonObject.getString("id");
                        //Toast.makeText(getApplicationContext(), item.id, Toast.LENGTH_LONG).show();
                        item.isClicked = false;
                        dataholder.add(item);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (JSONException e) {
                e.printStackTrace();
        }

        gen_listView = (ListView) findViewById(R.id.admin_manage_list);
        final AdminListAdapter gen_Adapter = new AdminListAdapter(dataholder, getApplicationContext(), intent);
        gen_listView.setAdapter(gen_Adapter);
        gen_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                if (current_clicked == position) {
                    ((AdminShowItemData) gen_Adapter.getItem(position)).isClicked = false;
                    current_clicked = -1;
                } else if (current_clicked != -1) {
                    ((AdminShowItemData) gen_Adapter.getItem(current_clicked)).isClicked = false;
                    ((AdminShowItemData) gen_Adapter.getItem(position)).isClicked = true;
                    current_clicked = position;
                } else {
                    ((AdminShowItemData) gen_Adapter.getItem(position)).isClicked = true;
                    current_clicked = position;
                }
                    gen_Adapter.notifyDataSetChanged();
                }
        });
    }
}
