package com.example.facilito.ui.componentes.menu.tramites;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TramitesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public TramitesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is tramites fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}