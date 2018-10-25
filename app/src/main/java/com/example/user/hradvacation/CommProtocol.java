package com.example.user.hradvacation;


import android.os.AsyncTask;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.Writer;
import java.net.MalformedURLException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ProtocolException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONException;
import org.json.JSONObject;

public class CommProtocol extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(String... strings) {

        HttpURLConnection con = null;
        BufferedReader reader = null;

        try {
            URL url = new URL("http://13.125.195.148:57728/" + strings[0]);

            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");//POST방식으로 보냄
            //con.setRequestProperty("Cache-Control", "no-cache");//캐시 설정
            con.setRequestProperty("Content-Type", "application/json");//application JSON 형식으로 전송
            con.setRequestProperty("Accept", "application/json");//서버에 response 데이터를 json으로 받음
            con.setRequestProperty("charset", "utf-8");
            con.setRequestProperty("Authorization", "Bearer "+strings[2]);
            con.setDoOutput(true);//Outstream으로 post 데이터를 넘겨주겠다는 의미
            con.setDoInput(true);//Inputstream으로 서버로부터 응답을 받겠다는 의미
            con.connect();

            //버퍼를 생성하고 넣음
            Writer writer = new BufferedWriter(new OutputStreamWriter(con.getOutputStream(), "UTF-8"));
            writer.write(strings[1]);
            writer.flush();
            writer.close();

            /*
            Log.i("STATUS", String.valueOf(con.getResponseCode()));
            Log.d("message", strings[1]);
            Log.i("MSG", con.getRequestProperty("data"));
            */

            //서버로 부터 데이터를 받음
            InputStream stream = con.getInputStream();

            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            return buffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
                if (con != null) {
                    con.disconnect();
                }
                if(reader != null){
                    try {
                        reader.close();
                    }catch (final IOException e){
                        e.printStackTrace();
                    }
                }
            //}
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}