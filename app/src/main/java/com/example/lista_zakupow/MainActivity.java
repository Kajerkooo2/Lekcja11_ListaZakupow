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
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<ToDo> rzeczyDoZrobienia;
    private ArrayAdapter<ToDo> arrayAdapter;
    private Spinner spinner;

    private Button button;

    private EditText editText;

    private TextView summary;

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
        spinner = findViewById(R.id.spinner);
        summary = findViewById(R.id.summary);

        rzeczyDoZrobienia = new ArrayList<>();
        rzeczyDoZrobienia.add(new ToDo((byte)1,"Wyjscie do kina"));
        rzeczyDoZrobienia.add(new ToDo((byte)2,"Nauczyc sie robienia czegos"));
        rzeczyDoZrobienia.add(new ToDo((byte)3,"Wyjdz z psem"));

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,rzeczyDoZrobienia);

        listView.setAdapter(arrayAdapter);

        // zaktualizuj podsumowanie na start
        updateSummary();

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String rzeczDozrobienie = editText.getText().toString().trim();
                if(rzeczDozrobienie.isEmpty()) {
                    return;
                }
                byte piorytetrzeczy = (byte)spinner.getSelectedItemPosition();
                rzeczyDoZrobienia.add(new ToDo(piorytetrzeczy, rzeczDozrobienie));
                arrayAdapter.notifyDataSetChanged();
                editText.setText("");
                updateSummary();
            }
        });
        //to jest na 4, MOZE byc zamiast formularza cos z losowaniem, przecwiczyc

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textView = (TextView) view;
                if(rzeczyDoZrobienia.get(i).isCzyWykonane()){
                    rzeczyDoZrobienia.get(i).setCzyWykonane(false);
                    textView.setPaintFlags(textView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    view.setBackgroundColor(Color.TRANSPARENT);
                }else{
                    rzeczyDoZrobienia.get(i).setCzyWykonane(true);
                    textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    view.setBackgroundColor(Color.GRAY);
                }
                updateSummary();
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                rzeczyDoZrobienia.remove(i);
                arrayAdapter.notifyDataSetChanged();
                updateSummary();
                return true;
            }
        });
    }

    private void updateSummary() {
        int notDone = 0;
        for (ToDo t : rzeczyDoZrobienia) {
            if (!t.isCzyWykonane()) notDone++;
        }
        String text = "Pozostało: " + notDone;
        summary.setText(text);
    }
}