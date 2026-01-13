package com.example.lista_zakupow;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> rzeczyDoZrobienia;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<Boolean> wykonane;


    private Button button;

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listView = findViewById(R.id.listView);
        button = findViewById(R.id.btnAdd);
        editText = findViewById(R.id.etNewItem);

        rzeczyDoZrobienia = new ArrayList<>();
        rzeczyDoZrobienia.add("Wyjscie do kina");
        rzeczyDoZrobienia.add("Nauczyc sie robienia czegos");
        rzeczyDoZrobienia.add("Wyjdz z psem");

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,rzeczyDoZrobienia);

        listView.setAdapter(arrayAdapter);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String rzeczDozrobienie = editText.getText().toString();
                rzeczyDoZrobienia.add(rzeczDozrobienie);
                arrayAdapter.notifyDataSetChanged();
                editText.setText("");
            }
        });
        //to jest na 4, MOZE byc zamiast formularza cos z losowaniem, przecwiczyc

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                view.setBackgroundColor(Color.GRAY);
                TextView textView = (TextView) view;
                if(textView.getPaintFlags() == Paint.STRIKE_THRU_TEXT_FLAG){
                    textView.setPaintFlags(Paint.ANTI_ALIAS_FLAG);
                }else{
                    textView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                }
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                rzeczyDoZrobienia.remove(i);
                arrayAdapter.notifyDataSetChanged();
                return false;
            }
        });
    }
}
