package com.example.facilito.ui.componentes.menu.balon;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BalonViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public BalonViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Balon fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}