package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AlertDialogFragment.AlertDialogListener{

    EditText item;
    Button add;
    ArrayList<String> itemList = new ArrayList<>();
    ArrayAdapter arrayAdapter;
    RecyclerView rv;
    RVAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        item = findViewById(R.id.editText);
        add = findViewById(R.id.button);
        rv = findViewById(R.id.rv); //RecycleView


        itemList = FileHelper.readData(this);

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, itemList);

        add.setOnClickListener(v -> {
            String itemName = item.getText().toString();
            itemList.add(itemName);
            item.setText("");
            FileHelper.writeData(itemList, getApplicationContext());
            arrayAdapter.notifyDataSetChanged();
            adapter.notifyDataSetChanged();
        });

        itemList.add("abde");
        adapter = new RVAdapter(itemList, getSupportFragmentManager());
        rv.setAdapter(adapter);

        File file = new File("listinfoa.dat");
        if (!file.exists()){
            Toast.makeText(getApplicationContext(), "File listinfoa.dat was not found", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void apply(int position) {
        itemList.remove(position);
        adapter.notifyDataSetChanged();
        FileHelper.writeData(itemList, getApplicationContext());
    }

}