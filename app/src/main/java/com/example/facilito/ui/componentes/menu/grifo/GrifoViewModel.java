package com.example.facilito.ui.componentes.menu.grifo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GrifoViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public GrifoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is grifo fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}