package com.example.neo_free.certamen2;

import android.support.v7.widget.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


public class Repo_adapter extends RecyclerView.Adapter<Repo_adapter.ViewHolder>{
    private String[][] datos;

    public Repo_adapter(String[][] datos){
        this.datos = datos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View ItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_view, parent, false);
        return new ViewHolder(ItemView);

    }
    @Override
    public int getItemCount(){
        return datos.length;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name_repo.setText(datos[position][0]);
        holder.description_repo.setText(datos[position][1]);
        holder.update_repo.setText(datos[position][2]);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name_repo;
        private TextView description_repo;
        private TextView update_repo;

        public ViewHolder(View itemView){
            super(itemView);
            name_repo = (TextView)itemView.findViewById(R.id.name_repo);
            description_repo = (TextView)itemView.findViewById(R.id.description_repo);
            update_repo = (TextView)itemView.findViewById(R.id.update_repo);
        }
    }

}
