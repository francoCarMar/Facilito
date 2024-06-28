package com.example.facilito.ui.componentes.menu.gas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GasViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public GasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Gas fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}