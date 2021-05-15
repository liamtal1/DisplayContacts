package com.example.displaycontacts.viewmodel;

import com.example.displaycontacts.model.Contact;
import com.example.displaycontacts.repository.ContactsRepository;
import com.example.displaycontacts.ui.ErrorListener;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
@HiltViewModel
public class ContactsViewModelImpl extends ContactsViewModel {
    private ContactsRepository contactsRepository;
    private CompositeDisposable compositeDisposable;

    @Inject
    public ContactsViewModelImpl(ContactsRepository repository) {
        super();
        this.contactsRepository = repository;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getContacts(ErrorListener listener) {
        Disposable disposable = contactsRepository
                .getContactList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        contacts -> contactsLiveData.postValue(contacts)
                        , throwable -> listener.onError("An error has occurred"));
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
