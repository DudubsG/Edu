package com.edu.edu;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView lista;

    ArrayAdapter<String> adapter;

    private ImageButton botao;

    private EditText barraPesquisa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = (ListView) findViewById(R.id.barraPesquisa);

        ArrayList<String> arrayBarra = new ArrayList<>();

        arrayBarra.addAll(Arrays.asList(getResources().getStringArray(R.array.cursos)));

        adapter = new ArrayAdapter<String>(

            MainActivity.this,
            android.R.layout.simple_list_item_1,
            arrayBarra
        );

        lista.setAdapter(adapter);

        botao = (ImageButton) findViewById(R.id.botaoPesquisar);

        barraPesquisa = (EditText) findViewById(R.id.pesquisar);

        barraPesquisa.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    botao.performClick();

                    return true;
                }

                return false;
            }
        });
    }

    public void buscar(View view){

        adapter.getFilter().filter(barraPesquisa.getText().toString());

        InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(barraPesquisa.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        getWindow().getDecorView().clearFocus();
    }
}