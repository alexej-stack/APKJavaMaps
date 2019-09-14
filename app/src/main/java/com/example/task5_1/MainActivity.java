package com.example.task5_1;

import android.os.Bundle;

import com.example.task5_1.Retrofit.Coord;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.lang.Object;
import java.util.ArrayList;
import java.util.List;
import android.view.View;



public class MainActivity extends AppCompatActivity {

    List<Coord> phones = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setInitialData();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        // создаем адаптер
        DataAdapter adapter = new DataAdapter(this, phones);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);
    }

    private void setInitialData(){

        phones.add(new Coord (56.128,11.409));

    }
}
