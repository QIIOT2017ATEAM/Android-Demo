package com.example.sec.myapplication.Db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import android.util.Log;



public class PHPRequest {

    public URL url;
    public PHPRequest(String url) throws MalformedURLException { this.url = new URL(url); }



    private String readStream(InputStream in) throws IOException {

        StringBuilder jsonHtml = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        String line = null;

        while((line = reader.readLine()) != null)

            jsonHtml.append(line);
            reader.close();

        return jsonHtml.toString();

    }

/*  전송 데이터의 종류 갯수에 따라
String postData = "Data1=" + data1 + "&" + "Data2=" + data2 + "&" + "Data3=" + data3;
위 코드가 변경 됩니다. 여기서 초록색은 php에 스트림에 들어가는 변수 이름과 같아야합니다.
 $data_stream = "'".$_POST['Data1']."','".$_POST['Data2']."','".$_POST['Data3']."'"; */

    public String PhPtest(final String data1, final String data2) {

        try {

            String postData = "Data1=" + data1 + "&" + "Data2=" + data2;
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(5000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(postData.getBytes("UTF-8"));
            outputStream.flush();
            outputStream.close();
            String result = readStream(conn.getInputStream());
            conn.disconnect();

            return result;

        }

        catch (Exception e) {

            Log.i("PHPRequest", "request was failed.");
            return null;

        }

    }

}
