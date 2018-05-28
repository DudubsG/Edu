package com.edu.edu;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Integer> fotos = new ArrayList<>();

    ListView listaFoto;

    TextView textoResultado;

    String[] nome;

    String[] descricao;

    int[] imgid;

    int[] imagensCursos;

    CustomListView customListView;

    RecyclerView recyclerView;

    RecyclerViewAdapter recyclerViewAdapter;

    ImageButton botao;

    EditText barraPesquisa;

    ArrayList<Model> arrayList = new ArrayList<>();

    LinearLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaFoto = findViewById(R.id.listView);

        textoResultado = findViewById(R.id.tvResultado);

        nome = new String[]{"FAE", "FAESP", "USP", "UP", "ITA", "UTFPR", "UFPR", "FGV", "UERJ", "UFRGS", "UFSC", "UEM", "UEPG"};

        descricao  = new String[]{"Faculdade de Administração e Economia", "Faculdade Anchieta de Ensino Superior do Paraná", "Universidade de São Paulo", "Universidade Positivo", "Instituto Tecnológico de Aeronáutica ", "Universidade Tecnológica Federal do Paraná", "Universidade Federal do Paraná", "Fundação Getúlio Vargas", "Universidade do Estado do Rio de Janeiro", "Universidade Federal do Rio Grande do Sul", "Universidade Federal de Santa Catarina", "Universidade Estadual de Maringá", "Universidade Estadual de Ponta Grossa"};

        imgid = new int[]{R.drawable.fae, R.drawable.faesp, R.drawable.usp, R.drawable.up, R.drawable.ita, R.drawable.utfpr, R.drawable.ufpr, R.drawable.fgv, R.drawable.uerj, R.drawable.ufrgs, R.drawable.ufsc, R.drawable.uem, R.drawable.uepg};

        imagensCursos = new int[]{R.drawable.administracao, R.drawable.direito, R.drawable.medicina, R.drawable.engenharia, R.drawable.psicologia};

        for(int i = 0; i < nome.length; i++){

            Model model = new Model(nome[i], descricao[i], imgid[i]);

            arrayList.add(model);
        }

        botao = findViewById(R.id.imageHorizontal);

        customListView = new CustomListView(this, arrayList);

        listaFoto.setAdapter(customListView);

        barraPesquisa = findViewById(R.id.pesquisar);

        for(int i : imagensCursos){

            fotos.add(i);
        }

        recyclerView = findViewById(R.id.recycler);

        manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerViewAdapter = new RecyclerViewAdapter(this, fotos);

        recyclerView.setAdapter(recyclerViewAdapter);

        recyclerView.setLayoutManager(manager);

        barraPesquisa.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_DONE) {

                    buscar1();

                    return true;
                }

                return false;
            }
        });
    }

    public void buscar1(){

        customListView.filter(barraPesquisa.getText().toString());

        InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        im.hideSoftInputFromWindow(barraPesquisa.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        getWindow().getDecorView().clearFocus();

        if(!CustomListView.resultado){

            textoResultado.setVisibility(View.VISIBLE);
        }

        else{

            textoResultado.setVisibility(View.INVISIBLE);

            CustomListView.resultado = false;
        }
    }

    public void buscar(View view){

        customListView.filter(barraPesquisa.getText().toString());

        InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        im.hideSoftInputFromWindow(barraPesquisa.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        getWindow().getDecorView().clearFocus();

        if(!CustomListView.resultado){

            textoResultado.setVisibility(View.VISIBLE);
        }

        else{

            textoResultado.setVisibility(View.INVISIBLE);

            CustomListView.resultado = false;
        }
    }
}