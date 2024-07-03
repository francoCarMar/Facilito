package com.example.facilito.ui.componentes.menu.grifo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.facilito.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.example.facilito.databinding.FragmentGrifoBinding;

import java.util.HashMap;
import java.util.Map;

public class GrifoFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Map<String, LatLng> locations;
    private Map<String, String> prices;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grifo, container, false);

        // Inicializar el fragmento de mapa
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        // Inicializar los datos de los precios y ubicaciones
        initializeData();

        // Configurar botones de filtro
        Button filterGPremium = view.findViewById(R.id.filter_g_premium);
        Button filterGRegular = view.findViewById(R.id.filter_g_regular);
        Button filterDiesel = view.findViewById(R.id.filter_diesel);
        Button filterGnv = view.findViewById(R.id.filter_gnv);

        filterGPremium.setOnClickListener(v -> filterMap("G PREMIUM"));
        filterGRegular.setOnClickListener(v -> filterMap("G REGULAR"));
        filterDiesel.setOnClickListener(v -> filterMap("DIESEL"));
        filterGnv.setOnClickListener(v -> filterMap("GNV"));

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        filterMap("G REGULAR"); // Mostrar G REGULAR por defecto
    }

    private void filterMap(String filter) {
        if (mMap != null) {
            mMap.clear();
            for (Map.Entry<String, LatLng> entry : locations.entrySet()) {
                String type = entry.getKey().split(":")[0];
                if (type.equals(filter)) {
                    LatLng location = entry.getValue();
                    String price = prices.get(entry.getKey());
                    mMap.addMarker(new MarkerOptions().position(location).title(price));
                }
            }
            if (!locations.isEmpty()) {
                LatLng firstLocation = locations.values().iterator().next();
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firstLocation, 15));
            }
        }
    }

    private void initializeData() {
        locations = new HashMap<>();
        prices = new HashMap<>();

        // Agregar ubicaciones y precios
        locations.put("G REGULAR:1", new LatLng(-12.0464, -77.0428));
        prices.put("G REGULAR:1", "S/ 16.10");

        locations.put("G REGULAR:2", new LatLng(-12.0470, -77.0440));
        prices.put("G REGULAR:2", "S/ 15.79");

        locations.put("G REGULAR:3", new LatLng(-12.0480, -77.0450));
        prices.put("G REGULAR:3", "S/ 15.97");

        // Agregar más ubicaciones y precios para otros tipos
        locations.put("G PREMIUM:1", new LatLng(-12.0464, -77.0428));
        prices.put("G PREMIUM:1", "S/ 17.10");

        locations.put("DIESEL:1", new LatLng(-12.0470, -77.0440));
        prices.put("DIESEL:1", "S/ 14.79");

        locations.put("GNV:1", new LatLng(-12.0480, -77.0450));
        prices.put("GNV:1", "S/ 12.97");
    }
}