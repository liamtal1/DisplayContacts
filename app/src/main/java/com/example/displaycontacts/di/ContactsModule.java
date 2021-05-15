package com.example.displaycontacts.di;

import com.example.displaycontacts.repository.ContactsDataSource;
import com.example.displaycontacts.repository.ContactsLocalDataSource;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class ContactsModule {

    @Singleton
    @Binds
    public abstract ContactsDataSource bindContactsDataSource(ContactsLocalDataSource localDataSource);
}
