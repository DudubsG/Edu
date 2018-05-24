package com.edu.edu;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private List<Integer> fotos = new ArrayList<>();

    ListView lista;

    ListView listaFoto;

    String[] nome;

    String[] descricao;

    int[] imgid;

    int[] imagensCursos;

    ArrayAdapter<String> adapter;

    CustomListView customListView;

    RecyclerView recyclerView;

    RecyclerViewAdapter recyclerViewAdapter;

    private ImageButton botao;

    private EditText barraPesquisa;

    ArrayList<Model> arrayList = new ArrayList<Model>();

    LinearLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botao = (ImageButton) findViewById(R.id.botaoPesquisar);

        listaFoto = (ListView) findViewById(R.id.listView);

        nome = new String[]{"FAE", "FAESP", "USP", "UP", "ITA", "UTFPR", "UFPR", "FGV", "UERJ", "UFRGS", "UFSC", "UEM", "UEPG"};

        descricao  = new String[]{"Faculdade de Administração e Economia", "Faculdade Anchieta de Ensino Superior do Paraná", "Universidade de São Paulo", "Universidade Positivo", "Instituto Tecnológico de Aeronáutica ", "Universidade Tecnológica Federal do Paraná", "Universidade Federal do Paraná", "Fundação Getúlio Vargas", "Universidade do Estado do Rio de Janeiro", "Universidade Federal do Rio Grande do Sul", "Universidade Federal de Santa Catarina", "Universidade Estadual de Maringá", "Universidade Estadual de Ponta Grossa"};

        imgid = new int[]{R.drawable.fae, R.drawable.faesp, R.drawable.usp, R.drawable.up, R.drawable.ita, R.drawable.utfpr, R.drawable.ufpr, R.drawable.fgv, R.drawable.uerj, R.drawable.ufrgs, R.drawable.ufsc, R.drawable.uem, R.drawable.uepg};

        imagensCursos = new int[]{R.drawable.administracao, R.drawable.direito, R.drawable.medicina, R.drawable.engenharia, R.drawable.psicologia};

        for(int i = 0; i < nome.length; i++){

            Model model = new Model(nome[i], descricao[i], imgid[i]);

            arrayList.add(model);
        }

        customListView = new CustomListView(this, arrayList);

        listaFoto.setAdapter(customListView);

        barraPesquisa = (EditText) findViewById(R.id.pesquisar);

        for(int i = 0; i < imagensCursos.length; i++){

            fotos.add(imagensCursos[i]);
        }

        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerViewAdapter = new RecyclerViewAdapter(this, fotos);

        recyclerView.setAdapter(recyclerViewAdapter);

        recyclerView.setLayoutManager(manager);

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

        customListView.filter(barraPesquisa.getText().toString());

        InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(barraPesquisa.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        getWindow().getDecorView().clearFocus();
    }
}