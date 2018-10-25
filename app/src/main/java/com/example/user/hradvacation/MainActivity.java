package com.example.user.hradvacation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    protected JSONObject loginWithGivenData(){
        try {
            EditText MilnumData = (EditText) findViewById(R.id.editText3);
            EditText BirthData = (EditText) findViewById(R.id.editText4);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username", MilnumData.getText().toString());
            jsonObject.put("password", BirthData.getText().toString());
            com.example.user.hradvacation.CommProtocol Communication = new CommProtocol();
            //리턴 값 처리
            return new JSONObject(Communication.execute("auth/login", jsonObject.toString(), "").get());
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText MilnumData = (EditText) findViewById(R.id.editText3);
        Button register_button = (Button)findViewById(R.id.main_register);
        Button manager_button = (Button)findViewById(R.id.manager_button);
        Button list_button = (Button)findViewById(R.id.list_button);

        // If going to register new vacation data to DB
        register_button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                String tf = "";
                String token = "";

                try {
                    JSONObject r_jsonObject = loginWithGivenData();
                    tf = r_jsonObject.getString("state");

                    // If Authorization fails
                    if (tf.equals("false")) {
                        Intent intent = new Intent(
                                getApplicationContext(),
                                PopupActivity.class
                        );
                        intent.putExtra("data", "login failed");
                        startActivity(intent);
                    } else {
                        //If Authorization success , maintain received token.
                        token = r_jsonObject.getString("token");
                        Intent intent = new Intent(
                                getApplicationContext(),
                                Register.class
                        );
                        intent.putExtra("token", token);
                        intent.putExtra("milnum", MilnumData.getText().toString());

                        startActivity(intent);
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        //If going to manage requests which was not certifcated
        manager_button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                String tf = "";
                String token = "";
                try {
                    JSONObject r_jsonObject = loginWithGivenData();
                    tf = r_jsonObject.getString("state");

                    if(tf.equals("false")) {
                        Intent intent = new Intent(
                                getApplicationContext(),
                                PopupActivity.class
                        );

                        intent.putExtra("data", "login failed");
                        startActivity(intent);
                    }
                    else{
                        token = r_jsonObject.getString("token");

                        JSONObject jsonObject2 = new JSONObject();
                        jsonObject2.put("manager_milnum", MilnumData.getText().toString());
                        com.example.user.hradvacation.CommProtocol Communication2 = new CommProtocol();
                        //리턴 값 처리
                        String resultJSON2 = Communication2.execute("manager_show",jsonObject2.toString(), token).get();
                        //Toast.makeText(getApplicationContext(), resultJSON2, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(
                                getApplicationContext(),
                                ManagerView.class
                        );
                        intent.putExtra("token", token);
                        intent.putExtra("data", resultJSON2);
                        startActivity(intent);
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });


        list_button.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                String tf = "";
                String token = "";
                try {
                    JSONObject r_jsonObject = loginWithGivenData();
                    tf = r_jsonObject.getString("state");

                    if(tf.equals("false")) {
                        Intent intent = new Intent(
                                getApplicationContext(),
                                PopupActivity.class
                        );

                        intent.putExtra("data", "login failed");
                        startActivity(intent);
                    }
                    else{
                        token = r_jsonObject.getString("token");

                        JSONObject jsonObject2 = new JSONObject();
                        jsonObject2.put("milnum", MilnumData.getText().toString());
                        com.example.user.hradvacation.CommProtocol Communication2 = new CommProtocol();
                        //리턴 값 처리
                        String resultJSON2 = Communication2.execute("hero_check",jsonObject2.toString(), token).get();
                        //Toast.makeText(getApplicationContext(), resultJSON2, Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(
                                getApplicationContext(),
                                UserView.class
                        );
                        intent.putExtra("token", token);
                        intent.putExtra("data", resultJSON2);
                        startActivity(intent);
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}