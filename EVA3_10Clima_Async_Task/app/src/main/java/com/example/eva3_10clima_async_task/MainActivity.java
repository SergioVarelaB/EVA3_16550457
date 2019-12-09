package com.example.eva3_10clima_async_task;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
public class MainActivity extends AppCompatActivity  implements Clima.AsyncResponse {
    public ArrayList<Climaa> clima = new ArrayList<Climaa>();
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Clima(this).execute();
    }
    @Override
    public void processFinish(ArrayList<Climaa> output) {
        lv = findViewById(R.id.lv);
        lv.setAdapter(new ClimaAdapter(this, R.layout.layout_clima, output));
    }
}
class Clima extends AsyncTask<Void, Void, String> {

    public interface AsyncResponse {
        void processFinish(ArrayList<Climaa> clima);
    }
    public AsyncResponse delegate = null;

    public Clima(AsyncResponse delegate){
        this.delegate = delegate;
    }
    ArrayList<Climaa> clima = new ArrayList<Climaa>();
    String nombre = "";
    int idImg = 0;
    String descripcion = "";
    double temperatura;
    final String ruta = "https://samples.openweathermap.org/data/2.5/group?id=524901,703448,2643743&units=metric&appid=b6907d289e10d714a6e88b30761fae22";
    @Override
    protected String doInBackground(Void... voids) {
        String resultado  = null;
        //aqui hacemos la conexion
        try {
            URL url = new URL(ruta);
            HttpsURLConnection http = (HttpsURLConnection)url.openConnection();
            http.connect();
            if(http.getResponseCode() == HttpsURLConnection.HTTP_OK){
                //leer respuesta
                String linea;
                StringBuffer lineas = new StringBuffer();
                InputStream inputStream = http.getInputStream();
                InputStreamReader inputSreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputSreamReader);

                while((linea = bufferedReader.readLine()) != null){
                    lineas.append(linea);
                }
                resultado = lineas.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(s != null){
            //procesamos los objetos
            try {
                JSONObject jsonClima = new JSONObject(s);
                JSONArray jsonCiudades = jsonClima.getJSONArray("list");
                for (int i = 0; i < jsonCiudades.length(); i++){
                    JSONObject jsonCiudad = jsonCiudades.getJSONObject(i);
                    //nombre
                    nombre = jsonCiudad.getString("name");
                    //Clima
                    JSONArray js = jsonCiudad.getJSONArray("weather");
                    JSONObject jss = js.getJSONObject(0);
                    descripcion = jss.getString("description");
                    //temperatura
                    //JSONArray temp = jsonCiudad.getJSONArray("id");
                    //JSONObject jT = jsTemp.getJSONObject(0);
                    //temperatura = jT.getDouble("temp");
                    //Log.wtf("aa", temp.getString(0));
                    //imagen
                    int id = jss.getInt("id");
                    if(id >= 800 && id < 900){
                        idImg = R.drawable.sunrise;
                    }
                    if(id >= 700 && id < 800){
                        idImg = R.drawable.cloudy;
                    }
                    Log.wtf("a",jss.getString("description"));
                    clima.add(new Climaa(idImg,nombre+"",30.0, descripcion));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        delegate.processFinish(clima);
    }
}
