package com.example.contact;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ContactAdapter contactAdapter;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        addButton = findViewById(R.id.addButton);

        contactAdapter = new ContactAdapter(ContactDB.getInstance().getContacts());
        recyclerView.setAdapter(contactAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditContactActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        contactAdapter.setOnItemClickListener(new ContactAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(MainActivity.this, EditContactActivity.class);
                intent.putExtra("position", position);
                startActivityForResult(intent, 1);
            }
        });

           SwipeToDeleteTouchListener swipeToDeleteTouchListener = new SwipeToDeleteTouchListener(this, new SwipeToDeleteTouchListener.OnDeleteListener() {
            @Override
            public void onDelete(int position) {
                contactAdapter.removeContact(position);
            }
        });
        new ItemTouchHelper(swipeToDeleteTouchListener).attachToRecyclerView(recyclerView);
        contactAdapter.setOnItemClickListener(new ContactAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(MainActivity.this, EditContactActivity.class);
                intent.putExtra("position", position);
                startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (data != null && data.getBooleanExtra("contact_updated", false)) {
                contactAdapter.notifyDataSetChanged();
            }
        }
    }
}
