package com.example.contentmedia.ui.contact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import static android.Manifest.permission.READ_CONTACTS;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;

import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.READ_CONTACTS;
import com.example.contentmedia.R;
import com.example.contentmedia.model.Contact;

import java.net.URI;
import java.util.ArrayList;

public class ContactActivity extends AppCompatActivity  implements ContactAdapter.ContactCallback{
RecyclerView rvContactC;
ArrayList<Contact> lstContact;
private static final int REQUEST_CODE_READ_CONTACTS=102;
ContactAdapter contactAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        rvContactC=findViewById(R.id.rvContact);
        //
        if(checkContacPermission()){
            lstContact=getListContact();
        }else {
            ActivityCompat.requestPermissions(this,new String[]{READ_CONTACTS,CALL_PHONE},REQUEST_CODE_READ_CONTACTS);

        }

        contactAdapter=new ContactAdapter(lstContact);
        contactAdapter.setContactCallback(this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        rvContactC.setAdapter(contactAdapter);
        rvContactC.setLayoutManager(linearLayoutManager);
    }



    ArrayList<Contact> getListContact() {
        ContentResolver contentResolver = getApplicationContext().getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");

        Contact contact;
        ArrayList<Contact> contacts = new ArrayList<>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                contact = new Contact();
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                @SuppressLint("Range") String phoneNo = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                @SuppressLint("Range") String photoUri = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI));
                contact.setName(name);
                contact.setPhoneNumber(phoneNo);
                contact.setPhoto(photoUri);

                contacts.add(contact);
            }
        }
        return contacts;
    }private boolean checkContacPermission(){
        int hasReadContactPermission = ContextCompat.checkSelfPermission(this,READ_CONTACTS);
        int hasPhoneCallPermission = ContextCompat.checkSelfPermission(this,CALL_PHONE);
        return hasReadContactPermission == PackageManager.PERMISSION_GRANTED  && hasPhoneCallPermission == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onItemClickedCall(String Phonenumber) {
        Intent intent=new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+Phonenumber));
        startActivity(intent);
    }
}