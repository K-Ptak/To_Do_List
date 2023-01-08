package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AlertDialogFragment.AlertDialogListener{

    EditText item;
    Button add;
    ListView listView;
    ArrayList<String> itemList = new ArrayList<>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        item = findViewById(R.id.editText);
        add = findViewById(R.id.button);
        listView = findViewById(R.id.list);

        itemList = FileHelper.readData(this);

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, itemList);

        listView.setAdapter(arrayAdapter);

        add.setOnClickListener(v -> {
            String itemName = item.getText().toString();
            itemList.add(itemName);
            item.setText("");
            FileHelper.writeData(itemList, getApplicationContext());
            arrayAdapter.notifyDataSetChanged();
        });
        listView.setOnItemClickListener((adapterView, view, position, id) -> {
                    DialogFragment alertDialogFragment = new AlertDialogFragment(position);
                    alertDialogFragment.show(getSupportFragmentManager(), AlertDialogFragment.TAG + position);
        });

        File file = new File("listinfoa.dat");
        if (!file.exists()){
            Toast.makeText(getApplicationContext(), "File listinfoa.dat was not found", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void apply(int position) {
        itemList.remove(position);
        arrayAdapter.notifyDataSetChanged();
        FileHelper.writeData(itemList, getApplicationContext());
    }

}