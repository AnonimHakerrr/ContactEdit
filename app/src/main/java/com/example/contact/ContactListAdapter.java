package com.example.contact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ContactListAdapter extends BaseAdapter {

    private Context context;
    private List<Contact> contacts;

    public ContactListAdapter(Context context, List<Contact> contacts) {
        this.context = context;
        this.contacts = contacts;
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.contact_item, parent, false);
        }

        TextView nameTextView = convertView.findViewById(R.id.nameTextView);
        TextView phoneTextView = convertView.findViewById(R.id.phoneTextView);

        Contact contact = contacts.get(position);
        nameTextView.setText(contact.getFullName());
        phoneTextView.setText(contact.getPhoneNumber());

        return convertView;
    }
}
