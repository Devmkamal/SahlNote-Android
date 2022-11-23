package com.kamal.sahlsample.di


import com.kamal.sahlsample.ui.login.LoginFragment
import com.kamal.sahlsample.ui.notes.add.AddNoteFragment
import com.kamal.sahlsample.ui.notes.list.NotesFragment
import com.kamal.sahlsample.ui.register.RegisterFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun contributeRegisterFragment(): RegisterFragment

    @ContributesAndroidInjector
    abstract fun contributeNotesFragment(): NotesFragment

    @ContributesAndroidInjector
    abstract fun contributeAddNoteFragment(): AddNoteFragment

}
