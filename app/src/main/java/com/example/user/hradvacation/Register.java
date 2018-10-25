package com.example.user.hradvacation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Register extends AppCompatActivity {

    public void doStuff(String str) throws Exception{
        throw new Exception(str);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button register_button = (Button)findViewById(R.id.register_submit);


        register_button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                try {
                    Intent intent2 = getIntent();
                    String token = intent2.getStringExtra("token");
                    String milnum = intent2.getStringExtra("milnum");

                    EditText RegionData = (EditText) findViewById(R.id.editText);
                    EditText LevelData = (EditText) findViewById(R.id.editText2);
                    EditText StartData = (EditText) findViewById(R.id.editText7);
                    EditText EndData = (EditText) findViewById(R.id.editText8);
                    EditText UsernameData = (EditText) findViewById(R.id.editText5);
                    EditText ManageMilnumData = (EditText) findViewById(R.id.editText6);

                    JSONObject js_obj= new JSONObject();
                    js_obj.put("username", UsernameData.getText().toString());
                    js_obj.put("region", RegionData.getText().toString());
                    js_obj.put("level", LevelData.getText().toString());
                    js_obj.put("start", StartData.getText().toString());
                    js_obj.put("end", EndData.getText().toString());
                    js_obj.put("milnum", milnum);
                    js_obj.put("manager_milnum", ManageMilnumData.getText().toString());

                    com.example.user.hradvacation.CommProtocol Communication = new CommProtocol();
                    //리턴 값 처리

                    //Toast.makeText(getApplicationContext(), token, Toast.LENGTH_LONG).show();
                    String resultJSON = Communication.execute("register", js_obj.toString(), token).get();

                    JSONObject r_jsonObject = new JSONObject(resultJSON);
                    String data = r_jsonObject.getString("state");

                    Intent intent = new Intent(
                            getApplicationContext(),
                            PopupActivity.class
                    );

                    intent.putExtra("data", data);
                    startActivity(intent);


                }catch(JSONException e){
                    e.printStackTrace();
                }
                catch(ExecutionException e){
                    e.printStackTrace();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }

                // Here comes SQL request for registering new value.
                /*
                Intent intent = new Intent(
                        getApplicationContext(),
                        MainActivity.class
                );
                startActivity(intent);
*/

            }
        });
    }
}
