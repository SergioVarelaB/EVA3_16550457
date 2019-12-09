package com.example.eva3_10clima_async_task;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ClimaAdapter extends ArrayAdapter<Climaa> {

    Context context;
    int resource;
    Climaa[] Ciudades;

    public ClimaAdapter(Context context, int resource, ArrayList<Climaa> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.Ciudades = objects.toArray(new Climaa[0]);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        TextView txtCiudad, txtTemp, textView;

        if(convertView == null){
            //Crear nuestro layout que representa una fila en la lista
            //INFLATER
            LayoutInflater lInflator = ((Activity) context).getLayoutInflater();
            convertView = lInflator.inflate(resource, parent, false);
        }

        imageView = convertView.findViewById(R.id.imageView);
        txtCiudad = convertView.findViewById(R.id.txtCiudad);
        txtTemp = convertView.findViewById(R.id.txtTemp);
        textView = convertView.findViewById(R.id.textView);

        imageView.setImageResource(Ciudades[position].getImagen());
        txtCiudad.setText(Ciudades[position].getCiudad());
        txtTemp.setText(Ciudades[position].getTemp() + "C");
        textView.setText(Ciudades[position].getClima());

        return convertView;
    }
}
