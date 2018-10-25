package com.example.user.hradvacation;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.view.Window;
import android.content.Intent;
import android.view.View;

public class PopupActivity extends Activity {
    TextView txttext;
    Button okay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_popup);

        //UI 객체생성
        txttext = (TextView)findViewById(R.id.text_notice);
        okay = (Button)findViewById(R.id.button_close);
        //데이터 가져오기
        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        txttext.setText(data);

        okay.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent();
                intent2.putExtra("result", "Close Popup");
                setResult(RESULT_OK, intent2);

                //액티비티(팝업) 닫기
                finish();
            }
        });

    }
}
