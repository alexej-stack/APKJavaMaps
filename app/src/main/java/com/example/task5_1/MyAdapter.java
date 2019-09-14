package com.example.task5_1;
import android.app.AlertDialog;
import android.app.Dialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.lang.Object;
import android.view.View;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.task5_1.Retrofit.Coord;

import java.util.List;

class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Coord> coords;

    DataAdapter(Context context, List<Coord> coords) {
        this.coords=coords;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
        Coord coord = coords.get(position);

        holder.nameView.setText(Double.toString(coord.getLat()));
        holder.companyView.setText(Double.toString(coord.getLon()));
    }

    @Override
    public int getItemCount() {
        return coords.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final TextView nameView, companyView;
        ViewHolder(View view){
            super(view);

            nameView = (TextView) view.findViewById(R.id.lat);
            companyView = (TextView) view.findViewById(R.id.lon);
        }
    }
}