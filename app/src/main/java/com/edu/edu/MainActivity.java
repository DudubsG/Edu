package com.edu.edu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView barraPesquisa;

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        barraPesquisa = (ListView) findViewById(R.id.barraPesquisa);

        ArrayList<String> arrayBarra = new ArrayList<>();

        arrayBarra.addAll(Arrays.asList(getResources().getStringArray(R.array.cursos)));

        adapter = new ArrayAdapter<String>(

            MainActivity.this,
            android.R.layout.simple_list_item_1,
            arrayBarra
        );

        barraPesquisa.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.pesquisa_menu, menu);

        MenuItem item = menu.findItem(R.id.procurarCursos);

        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                adapter.getFilter().filter(s);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}