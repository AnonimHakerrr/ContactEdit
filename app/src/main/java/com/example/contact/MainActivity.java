package com.example.contact;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;



import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Contact> contacts;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        contacts = ContactDB.getContacts();
        listView = findViewById(R.id.listView);

        ContactListAdapter adapter = new ContactListAdapter(this, contacts);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, EditContactActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
    }
}
