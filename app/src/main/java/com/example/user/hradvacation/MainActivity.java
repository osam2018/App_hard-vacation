package com.example.user.hradvacation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button register_button = (Button)findViewById(R.id.main_register);
        Button manager_button = (Button)findViewById(R.id.manager_button);
        Button list_button = (Button)findViewById(R.id.list_button);

        register_button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                String resultJSON = "";
                String tf = "";
                String data = "";
                String token = "";
                try {
                    EditText MilnumData = (EditText) findViewById(R.id.editText3);
                    EditText BirthData = (EditText) findViewById(R.id.editText4);
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("username", MilnumData.getText().toString());
                    jsonObject.put("password", BirthData.getText().toString());
                    com.example.user.hradvacation.CommProtocol Communication = new CommProtocol();
                    //리턴 값 처리
                    resultJSON = Communication.execute("auth/login",jsonObject.toString(),"").get();
                    //Toast.makeText(getApplicationContext(), resultJSON, Toast.LENGTH_LONG).show();
                    JSONObject r_jsonObject = new JSONObject(resultJSON);
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
                        Intent intent = new Intent(
                                getApplicationContext(),
                                Register.class
                        );
                        intent.putExtra("token", token);
                        intent.putExtra("milnum", MilnumData.getText().toString());

                        startActivity(intent);
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        manager_button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                String resultJSON = "";
                String tf = "";
                String data = "";
                String token = "";
                try {
                    EditText MilnumData = (EditText) findViewById(R.id.editText3);
                    EditText BirthData = (EditText) findViewById(R.id.editText4);
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("username", MilnumData.getText().toString());
                    jsonObject.put("password", BirthData.getText().toString());
                    com.example.user.hradvacation.CommProtocol Communication = new CommProtocol();
                    //리턴 값 처리
                    resultJSON = Communication.execute("auth/login",jsonObject.toString(),"").get();
                    //Toast.makeText(getApplicationContext(), resultJSON, Toast.LENGTH_LONG).show();
                    JSONObject r_jsonObject = new JSONObject(resultJSON);
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
                        jsonObject2.put("username", MilnumData.getText().toString());
                        jsonObject2.put("password", MilnumData.getText().toString());
                        com.example.user.hradvacation.CommProtocol Communication2 = new CommProtocol();
                        //리턴 값 처리
                        String resultJSON2 = Communication2.execute("manager_show",jsonObject2.toString(), token).get();
                        //Toast.makeText(getApplicationContext(), resultJSON2, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(
                                getApplicationContext(),
                                manager_view.class
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
                String resultJSON = "";
                String tf = "";
                String data = "";
                String token = "";
                try {
                    EditText MilnumData = (EditText) findViewById(R.id.editText3);
                    EditText BirthData = (EditText) findViewById(R.id.editText4);
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("username", MilnumData.getText().toString());
                    jsonObject.put("password", BirthData.getText().toString());
                    com.example.user.hradvacation.CommProtocol Communication = new CommProtocol();
                    //리턴 값 처리
                    resultJSON = Communication.execute("auth/login",jsonObject.toString(),"").get();
                    //Toast.makeText(getApplicationContext(), resultJSON, Toast.LENGTH_LONG).show();
                    JSONObject r_jsonObject = new JSONObject(resultJSON);
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
                        jsonObject2.put("username", MilnumData.getText().toString());
                        jsonObject2.put("password", BirthData.getText().toString());
                        com.example.user.hradvacation.CommProtocol Communication2 = new CommProtocol();
                        //리턴 값 처리
                        String resultJSON2 = Communication2.execute("hero_check",jsonObject2.toString(), token).get();
                        //Toast.makeText(getApplicationContext(), resultJSON2, Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(
                                getApplicationContext(),
                                user_view.class
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