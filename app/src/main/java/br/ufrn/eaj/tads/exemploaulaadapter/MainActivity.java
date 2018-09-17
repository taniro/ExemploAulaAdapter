package br.ufrn.eaj.tads.exemploaulaadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import br.ufrn.eaj.tads.exemploaulaadapter.banco.AppDatabase;
import br.ufrn.eaj.tads.exemploaulaadapter.banco.Fruta;
import br.ufrn.eaj.tads.exemploaulaadapter.banco.FrutaDao;

public class MainActivity extends AppCompatActivity {

    AppDatabase db;
    FrutaDao myFrutaDao;

    String[] FRUTAS;

    ArrayAdapter<String> stringArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = AppDatabase.getDatabase(this);
        myFrutaDao = db.frutaDao();

        FRUTAS = new String[db.frutaDao().getNumberOfRows()];

        int i = 0;
        for (Fruta f: db.frutaDao().listAll()){
            FRUTAS[i] = f.getNome();
            i++;
        }

        AutoCompleteTextView autoCompleteFrutas = findViewById(R.id.autoCompleteTextView);
        stringArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, FRUTAS);
        autoCompleteFrutas.setAdapter(stringArrayAdapter);
        autoCompleteFrutas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, stringArrayAdapter.getItem(i).toString(), Toast.LENGTH_SHORT).show();
            }
        });

        MultiAutoCompleteTextView multiAutoCompleteFrutas = findViewById(R.id.multiAutoCompleteTextView);
        multiAutoCompleteFrutas.setAdapter(stringArrayAdapter);
        multiAutoCompleteFrutas.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        multiAutoCompleteFrutas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, ((TextView) view).getText().toString() +"item="+i+"col="+l   , Toast.LENGTH_SHORT).show();
            }
        });

        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(stringArrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, ((TextView) view).getText().toString() +"item="+i+"col="+l   , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}
