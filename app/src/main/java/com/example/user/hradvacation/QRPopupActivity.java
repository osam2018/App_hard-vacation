package com.example.user.hradvacation;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

import static android.app.Activity.RESULT_OK;

public class QRPopupActivity extends Activity{

    ImageView imageView;
    TextView txttext;

    Button okay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_qr_popup);

        //UI 객체생성
        imageView = (ImageView)findViewById(R.id.QR_Image);
        okay = (Button)findViewById(R.id.bbutton);
        txttext = findViewById(R.id.txtText);
        //데이터 가져오기
        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        String filename = intent.getStringExtra("filename");
        myQRcreator QRcreator = new myQRcreator();
        String result = QRcreator.CreateQRCode(data, "/QRCODE/", filename);

        if(result.equals("true")) {
            File imgFile = new File(Environment.getExternalStorageDirectory()+"/QRCODE/" + filename + ".jpg" );
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            imageView.setImageBitmap(myBitmap);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

            txttext.setText("http://13.125.195.148:57728/data?hash="+data);
        }
        else{

            txttext.setText("승인되지 않았습니다.");
        }

        okay.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) { Intent intent2 = new Intent();
            intent2.putExtra("result", "Close Popup");
            setResult(RESULT_OK, intent2);

            //액티비티(팝업) 닫기
                finish();
            }
        });
    }
}
