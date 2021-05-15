package com.example.displaycontacts.repository;

import com.example.displaycontacts.model.Contact;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class ContactsRepository implements ContactsDataSource{

    ContactsDataSource localDataSource;

    @Inject
    public ContactsRepository(ContactsDataSource localDataSource) {
        this.localDataSource = localDataSource;
    }

    @Override
    public Single<List<Contact>> getContactList() {
        return localDataSource.getContactList();
    }
}
