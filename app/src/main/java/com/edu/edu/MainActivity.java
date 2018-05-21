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

    ListView listaFoto;

    String[] nome;

    String[] descricao;

    int[] imgid;

    ArrayAdapter<String> adapter;

    CustomListView customListView;

    private ImageButton botao;

    private EditText barraPesquisa;

    ArrayList<Model> arrayList = new ArrayList<Model>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botao = (ImageButton) findViewById(R.id.botaoPesquisar);

        listaFoto = (ListView) findViewById(R.id.listView);

        nome = new String[]{"FAE", "FAESP", "USP", "UP", "ITA", "UTFPR", "UFPR", "FGV", "UERJ", "UFRGS", "UFSC", "UEM", "UEPG"};

        descricao  = new String[]{"Faculdade de Administração e Economia", "Faculdade Anchieta de Ensino Superior do Paraná", "Universidade de São Paulo", "Universidade Positivo", "Instituto Tecnológico de Aeronáutica ", "Universidade Tecnológica Federal do Paraná", "Universidade Federal do Paraná", "Fundação Getúlio Vargas", "Universidade do Estado do Rio de Janeiro", "Universidade Federal do Rio Grande do Sul", "Universidade Federal de Santa Catarina", "Universidade Estadual de Maringá", "Universidade Estadual de Ponta Grossa"};

        imgid = new int[]{R.drawable.fae, R.drawable.faesp, R.drawable.usp, R.drawable.up, R.drawable.fae, R.drawable.utfpr, R.drawable.ufpr, R.drawable.fae, R.drawable.fae, R.drawable.fae, R.drawable.fae, R.drawable.fae, R.drawable.fae};

        for(int i = 0; i < nome.length; i++){

            Model model = new Model(nome[i], descricao[i], imgid[i]);

            arrayList.add(model);
        }

        customListView = new CustomListView(this, arrayList);

        listaFoto.setAdapter(customListView);

        /*lista = (ListView) findViewById(R.id.listView);

        ArrayList<String> arrayLista = new ArrayList<>();

        arrayLista.addAll(Arrays.asList(getResources().getStringArray(R.array.cursos)));

        adapter = new ArrayAdapter<String>(

            MainActivity.this,
            android.R.layout.simple_list_item_1,
            arrayLista
        );

        lista.setAdapter(adapter);*/

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

        //adapter.getFilter().filter(barraPesquisa.getText().toString());

        customListView.filter(barraPesquisa.getText().toString());

        InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(barraPesquisa.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        getWindow().getDecorView().clearFocus();
    }
}