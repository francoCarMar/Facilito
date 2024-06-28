package com.example.facilito.ui.componentes.menu.electricidad;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ElectricidadViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ElectricidadViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is electtricidad fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}