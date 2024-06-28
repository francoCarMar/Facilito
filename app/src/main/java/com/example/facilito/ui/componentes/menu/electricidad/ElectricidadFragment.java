package com.example.facilito.ui.componentes.menu.electricidad;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.facilito.databinding.FragmentElectricidadBinding;
import com.example.facilito.databinding.FragmentElectricidadBinding;

public class ElectricidadFragment extends Fragment {

    private FragmentElectricidadBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ElectricidadViewModel electricidadViewModel =
                new ViewModelProvider(this).get(ElectricidadViewModel.class);

        binding = FragmentElectricidadBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDashboard;
        electricidadViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}