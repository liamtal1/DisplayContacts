package com.example.displaycontacts.repository;

import com.example.displaycontacts.model.Contact;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface ContactsDataSource {
    Single<List<Contact>> getContactList();
}
