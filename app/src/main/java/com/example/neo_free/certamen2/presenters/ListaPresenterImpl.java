package com.example.neo_free.certamen2.presenters;

import android.os.AsyncTask;

import com.example.neo_free.certamen2.Tarjeta;
import com.example.neo_free.certamen2.presenters.contract.ListaPresenter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by neo_free on 02/11/2016.
 */

public class ListaPresenterImpl extends AsyncTask<String, Tarjeta[], Tarjeta[]> implements ListaPresenter  {
    static String url_m =  "http://www.mocky.io/v2/57eee3822600009324111202";

    @Override
    public Tarjeta[] searchUser(String user) { // Se recibira el json
        String url = "https://api.github.com/users/" + user + "/repos";
        String response = null;
        try {
            //HTTP CLIENT
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpEntity httpEntity = null;
            HttpResponse httpResponse = null;
            //METODO GET
            HttpGet httpGet = new HttpGet(url_m); // cambiar url_m por url
            httpResponse = httpClient.execute(httpGet);


            httpEntity = httpResponse.getEntity();
            response = EntityUtils.toString(httpEntity);
            //EXCEPCIONES
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Tarjeta[] repo_list = new Tarjeta[0];
        try {
            JSONArray respJSON = new JSONArray(response);
            repo_list = new Tarjeta[respJSON.length()];
            String title;
            String description;
            String update;
            String html_url;
            for (int i = 0; i < respJSON.length(); i++) {
                JSONObject obj = respJSON.getJSONObject(i);
                String[] repo = new String[4];
                title = obj.getString("name");
                description = obj.getString("description");
                update = obj.getString("update_ad");
                url = obj.getString("html_url");
                repo_list[i] = new Tarjeta(title, description, update, url);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return repo_list;
    }


    @Override
    protected Tarjeta[] doInBackground(String... params) {
        Tarjeta[] resultado = searchUser(params[0]);
        return resultado;
    }

    @Override
    protected void onPostExecute(Tarjeta[] result){


    };
}
