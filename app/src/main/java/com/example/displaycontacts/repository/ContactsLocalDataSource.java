package com.example.displaycontacts.repository;

import com.example.displaycontacts.model.Contact;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;
import io.reactivex.rxjava3.core.Single;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;

public class ContactsLocalDataSource implements ContactsDataSource {

    private Context context;

    @Inject
    public ContactsLocalDataSource(@ApplicationContext Context context) {
        this.context = context;
    }

    @Override
    public Single<List<Contact>> getContactList() {

        HashMap<String,Contact> contactMap = new HashMap<>();
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                if (cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor cursorInfo = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);

                    while (cursorInfo.moveToNext()) {
                        String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        String phoneNumber = cursorInfo.getString(cursorInfo.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        Contact contact = new Contact(name, phoneNumber);
                        contactMap.put(id,contact);
                    }

                    cursorInfo.close();
                }
            }
            cursor.close();
        }
        List<Contact> list = new ArrayList<>(contactMap.values());
        Collections.sort(list, (c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
        return Single.just(list);
    }
}
