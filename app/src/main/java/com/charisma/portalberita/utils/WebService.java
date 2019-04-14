package com.charisma.portalberita.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.charisma.portalberita.face.Listener;
import com.charisma.portalberita.model.Response;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Bayu Charisma Putra on 3/8/2019.
 */
public class WebService extends AsyncTask {

    private final String BASE_URL = "http://192.168.43.100/ws_berita_papsi/";

    public final static int METHOD_GET = 1;
    public final static int METHOD_POST = 2;

    private Context context;
    private Listener listener;
    private String url;
    private int method;

    public WebService(Context context, Listener listener, String url, int method) {
        Log.i("=====", url);
        this.context = context;
        this.listener = listener;
        this.url = url;
        this.method = method;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        if (params.length > 0)
            Log.i("=====", (String) params[0]);

        if (method == METHOD_GET) {
            try{
                String link = BASE_URL + url;
                if (params.length > 0) link += "?" + params[0];

                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(link));
                HttpResponse response = client.execute(request);

                BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuffer sb = new StringBuffer("");
                String line="";

                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    break;
                }

                in.close();
                return sb.toString();
            } catch(Exception e){
                return "Exception: " + e.getMessage();
            }
        } else if (method == METHOD_POST) {
            try{
                String link = BASE_URL + url;
                URL url = new URL(link);
                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                if (params.length > 0) {
                    String paramsPost = "";
                    for (String param : params[0].toString().split("&")) {
                        String[] kv = param.split("=");
                        if (kv.length < 2) continue;
                        paramsPost += URLEncoder.encode(kv[0], "UTF-8") + "=" + URLEncoder.encode(kv[1], "UTF-8") + "&";
                    }
                    paramsPost = paramsPost.substring(0, paramsPost.length() - 1);
                    Log.i("=====", paramsPost);
                    wr.write(paramsPost);
                }

                wr.flush();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while((line = reader.readLine()) != null) {
                    sb.append(line);         break;
                }

                return sb.toString();
            } catch(Exception e){
                return "Exception: " + e.getMessage();
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        Log.i("=====", o.toString());

        ObjectMapper mapper = new ObjectMapper();
        Response response;
        try {
            response = mapper.readValue(o.toString(), Response.class);
        } catch (IOException e) {
            response = new Response();
            response.setStatus(1);
            response.setMessage("Lost connection");
        }

        if (response.getStatus() == 0) {
            listener.onSucessWs(new Object[]{response});
        } else {
            listener.onFailedWs(new Object[]{response});
        }
    }
}
