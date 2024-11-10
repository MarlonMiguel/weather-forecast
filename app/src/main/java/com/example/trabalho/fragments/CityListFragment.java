package com.example.trabalho.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalho.R;
import com.example.trabalho.adapters.CitiesAdapter;
import com.example.trabalho.model.Cidade;
import java.util.ArrayList;
import java.util.List;

public class CityListFragment extends Fragment {

    private RecyclerView recyclerView;
    private CitiesAdapter citiesAdapter;
    private List<Cidade> citiesList;
    private EditText searchCity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_city_list, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        searchCity = view.findViewById(R.id.search_city);

        // Configuração do RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Inicializar lista de cidades
        citiesList = new ArrayList<>();
        citiesList.add(new Cidade("Cidade Exemplo 1"));
        citiesList.add(new Cidade("Cidade Exemplo 2"));
        // Adicione mais cidades conforme necessário

        citiesAdapter = new CitiesAdapter(citiesList, cidade -> {
            // Ação quando uma cidade for clicada
        });

        recyclerView.setAdapter(citiesAdapter);

        // Filtro de cidades em tempo real
        searchCity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                filterCities(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        return view;
    }

    private void filterCities(String query) {
        List<Cidade> filteredCities = new ArrayList<>();
        for (Cidade cidade : citiesList) {
            if (cidade.getNome().toLowerCase().contains(query.toLowerCase())) {
                filteredCities.add(cidade);
            }
        }
        citiesAdapter.updateCitiesList(filteredCities);
    }
}
