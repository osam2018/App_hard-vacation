package com.example.user.hradvacation;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.widget.ListAdapter;
        import android.widget.ListView;
        import java.util.ArrayList;



public class manager_view extends AppCompatActivity {

    private ListView  gen_listView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_view);

        String[] examples = {"'상병 안신우", "'2018-10-22 ~ 2018-10-26", "'상병 곽범주", "'2018-10-22 ~ 2018-04-02"};
        ArrayList<AdminShowItemData> dataholder = new ArrayList<>();
        for (int i = 0; i < 2; i++){
            AdminShowItemData item = new AdminShowItemData();
            item.userNameStr = examples[i*2];
            item.userDateStr = examples[i*2+1];
            dataholder.add(item);
        }

        gen_listView = (ListView) findViewById(R.id.admin_manage_list);
        ListAdapter gen_Adapter = new AdminListAdapter(dataholder);
        gen_listView.setAdapter(gen_Adapter);
    }
}
