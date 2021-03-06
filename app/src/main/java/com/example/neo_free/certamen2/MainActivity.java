package com.example.neo_free.certamen2;



import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private EditText user_search;
    private Button btn_search;
    //private TextView app_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user_search = (EditText)findViewById(R.id.user_search);
        btn_search = (Button)findViewById(R.id.btn_search);
        //app_name = (TextView)findViewById(R.id.app_name);

        btn_search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent_l = new Intent(MainActivity.this, ListaRepositoriosActivity.class);

                Bundle user_name_to_search = new Bundle();
                user_name_to_search.putString("USER", user_search.getText().toString());

                intent_l.putExtras(user_name_to_search);
                startActivity(intent_l);

            }
        });
    }


}
