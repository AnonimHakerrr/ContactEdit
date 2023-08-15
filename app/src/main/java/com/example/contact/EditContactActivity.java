package com.example.contact;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class EditContactActivity extends AppCompatActivity {

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText phoneNumberEditText;
    private Button saveButton;

    private int contactPosition;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

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

    private void saveContactChanges() {
        // Отримати оновлені дані контакта з віджетів
        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        String phoneNumber = phoneNumberEditText.getText().toString();

        if (contactPosition != -1) {
            // Оновити контакт в ContactDB або базі даних
            ContactDB.getInstance().updateContact(contactPosition, new Contact(firstName, lastName, phoneNumber));
        }

        // Підготувати Intent з оновленими даними
        Intent resultIntent = new Intent();
        resultIntent.putExtra("contact_updated", true);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}


