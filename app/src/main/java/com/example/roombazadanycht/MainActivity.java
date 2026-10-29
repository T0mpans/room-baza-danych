package com.example.roombazadanycht;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SzkolaDataBase szkolaDataBase;
    Button button;
    EditText editText;
    Spinner spinner;
    ListView listView;
    List<Nauczyciel> nauczycielList;
    ArrayAdapter<Nauczyciel> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        szkolaDataBase = Room.databaseBuilder(
                getApplicationContext(), SzkolaDataBase.class, "szkola_db")
                .allowMainThreadQueries()
                .build();

        /*szkolaDataBase.getNauczycielDAO().wstawNauczycielaDoBazy(
                new Nauczyciel("Anna", "matematyka")
        );*/
        button = findViewById(R.id.button);
        editText = findViewById(R.id.editTextTextPersonName);
        spinner = findViewById(R.id.spinner);
        listView = findViewById(R.id.listView);
        nauczycielList = szkolaDataBase.getNauczycielDAO().zwrocWszystkichNauczycieliZBazy();
        arrayAdapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                nauczycielList);
        listView.setAdapter(arrayAdapter);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Nauczyciel nauczyciel = new Nauczyciel(
                                editText.getText().toString(),
                                spinner.getSelectedItem().toString()
                        );
                        szkolaDataBase.getNauczycielDAO().wstawNauczycielaDoBazy(nauczyciel);
                    }
                }
        );
    }
}