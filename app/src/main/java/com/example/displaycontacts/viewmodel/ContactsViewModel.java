package com.example.displaycontacts.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.displaycontacts.model.Contact;
import com.example.displaycontacts.ui.ErrorListener;

import java.util.List;

public abstract class ContactsViewModel extends ViewModel {
    protected MutableLiveData<List<Contact>> contactsLiveData;

    public ContactsViewModel() {
        contactsLiveData = new MutableLiveData<>();
    }
    public abstract void getContacts(ErrorListener listener);

    public MutableLiveData<List<Contact>> getContactsLiveData() {
        return contactsLiveData;
    }
}
