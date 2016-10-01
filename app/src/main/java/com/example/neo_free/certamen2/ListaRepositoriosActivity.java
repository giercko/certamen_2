package com.example.neo_free.certamen2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ListaRepositoriosActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView textview;
    private TextView name_repo;
    private TextView description_repo;
    private TextView update_repo;
    private ListView repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_repositorios);
        name_repo = (TextView)findViewById(R.id.name_repo);
        description_repo = (TextView)findViewById(R.id.description_repo);
        update_repo = (TextView)findViewById(R.id.update_repo);
        repo = (ListView)findViewById(R.id.repo);



        Obtener_lista_repo tarea = new Obtener_lista_repo();
        Bundle bundle = this.getIntent().getExtras();
        tarea.execute(
                       bundle.getString("USER").toString()
        );
        //recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
       // recyclerView.setHasFixedSize(false);
        String[][] lista = tarea.resultado() ;
        //adapter = new Repo_adapter(lista);
        //layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        //recyclerView.setLayoutManager(layoutManager);

        //recyclerView.setAdapter(adapter);


    }
    private class Obtener_lista_repo extends AsyncTask<String, Integer, Boolean> {
        private String[][] repo_list;
        private int no_existe;

        protected Boolean doInBackground(String... params) {
            boolean result = true;
            HttpClient httpClient = new DefaultHttpClient();
            //HttpGet del = new HttpGet("https://api.github.com/users"+params[0]+"/repos");
            HttpGet del = new HttpGet("http://www.mocky.io/v2/57eee3822600009324111202");
            del.setHeader("content-type", "application/json");

            try {
                HttpResponse resp = httpClient.execute(del);
                String respStr = EntityUtils.toString(resp.getEntity());
                try {
                    JSONArray respJSON = new JSONArray(respStr);
                    no_existe = 0;
                    repo_list = new String[respJSON.length()][4];

                    for (int i = 0; i < respJSON.length(); i++) {
                        JSONObject obj = respJSON.getJSONObject(i);
                        String[] repo = new String[4];
                        repo[0] = obj.getString("name");
                        repo[1] = obj.getString("description");
                        repo[2] = obj.getString("update_ad");
                        repo[3] = obj.getString("html_url");
                        repo_list[i] = repo;
                    }

                } catch (JSONException e) {
                    try {
                        JSONObject jobj = new JSONObject(respStr);
                        no_existe = 1;
                    } catch (JSONException n) {
                        result = false;
                    }
                }

            } catch (Exception ex) {
                Log.e("ServicioRest", "Error", ex);
                result = false;
            }
            return result;
        }

        public String[][] resultado(){
            return repo_list;
        }

        protected void onPostExecute(Boolean result) {
            if (result) {
                if (no_existe == 1) {
                    repo_list = null;
                }else{
                    ArrayAdapter<String[]> adaptador = new ArrayAdapter<String[]>(ListaRepositoriosActivity.this, android.R.layout.list_content, repo_list);
                    repo.setAdapter(adaptador);
                }
            }
        }
    }
}
