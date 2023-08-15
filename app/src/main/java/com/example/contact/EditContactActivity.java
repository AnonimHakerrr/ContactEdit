package com.example.contact;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class EditContactActivity extends AppCompatActivity {

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText phoneNumberEditText;
    private Button saveButton;
    private ContactAdapter contactAdapter;
    private int contactPosition;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);
        contactAdapter = new ContactAdapter(ContactDB.getInstance().getContacts());

        firstNameEditText = findViewById(R.id.editTextFirstName);
        lastNameEditText = findViewById(R.id.editTextLastName);
        phoneNumberEditText = findViewById(R.id.editTextPhoneNumber);
        saveButton = findViewById(R.id.buttonSave);


        Intent intent = getIntent();
        if (intent != null) {
            contactPosition = intent.getIntExtra("position", -1);
            if (contactPosition != -1) {
                Contact contact = ContactDB.getInstance().getContacts().get(contactPosition);
                firstNameEditText.setText(contact.getFirstName());
                lastNameEditText.setText(contact.getLastName());
                phoneNumberEditText.setText(contact.getPhoneNumber());
            }
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveContactChanges();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (data != null && data.getBooleanExtra("contact_updated", false)) {
                // Оновити список контактів
                contactAdapter.notifyDataSetChanged();
            }
        }
    }

    private void saveContactChanges() {
        // Отримати оновлені дані контакта з віджетів
        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        String phoneNumber = phoneNumberEditText.getText().toString();

        if (contactPosition != -1) {

            ContactDB.getInstance().updateContact(contactPosition, new Contact(firstName, lastName, phoneNumber));
        }

        contactAdapter.notifyDataSetChanged();
        Intent resultIntent = new Intent();
        resultIntent.putExtra("contact_updated", true);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}


