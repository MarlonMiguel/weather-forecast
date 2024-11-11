package com.example.trabalho.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalho.R;
import com.example.trabalho.activities.MainActivity;
import com.example.trabalho.adapters.CitiesAdapter;
import com.example.trabalho.model.Cidade;
import com.example.trabalho.model.WeatherData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CityListFragment extends Fragment {

    private RecyclerView recyclerView;
    private CitiesAdapter citiesAdapter;
    private List<Cidade> citiesList;
    private EditText codigoIbgeEditText; // EditText para exibir cidade e estado

    private Spinner countrySpinner;
    private Spinner citySpinner;
    private List<String> estados;
    private ExecutorService executorService;
    private String estadoSelecionado = "";
    private Button loadButton;
    private TextView cityName,weatherDescription,temperature,humidity, windSpeed, forecast;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_city_list, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        codigoIbgeEditText = view.findViewById(R.id.search_city);

        executorService = Executors.newSingleThreadExecutor();
        countrySpinner = view.findViewById(R.id.estado_spinner);
        citySpinner = view.findViewById(R.id.cidade_spinner);
        loadButton = view.findViewById(R.id.load_button);

        cityName = view.findViewById(R.id.city_name);
        weatherDescription = view.findViewById(R.id.weather_description);
        temperature = view.findViewById(R.id.temperature);
        humidity = view.findViewById(R.id.humidity);
        windSpeed = view.findViewById(R.id.wind_speed);
        forecast = view.findViewById(R.id.forecast);

        estados = new ArrayList<>();
        citiesList = new ArrayList<>();
        carregarEstados();

        estados.add(0, "Estado");
        ArrayAdapter<String> estadoAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, estados);
        estadoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner.setAdapter(estadoAdapter);

        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    estadoSelecionado = (String) parent.getItemAtPosition(position);
                    carregarCidades(estadoSelecionado);
                } else {
                    estadoSelecionado = "";
                    citySpinner.setAdapter(null);
                    codigoIbgeEditText.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = codigoIbgeEditText.getText().toString().trim();

                if (city.isEmpty()) {
                    Toast.makeText(getActivity(), "Por favor, insira cidade e estado.", Toast.LENGTH_SHORT).show();
                } else {
                    String url = "https://api.hgbrasil.com/weather?key=SUA-CHAVE&city_name=" + city;

                    executorService.execute(() -> {
                        String weatherDataJson = fetchWeatherData(url);
                        if (getActivity() != null) {
                            getActivity().runOnUiThread(() -> {
                                if (weatherDataJson != null) {
                                    WeatherData weatherData = parseWeatherData(weatherDataJson);

                                    if (weatherData != null) {
                                        cityName.setText(weatherData.getCityName());
                                        weatherDescription.setText(weatherData.getDescription());
                                        temperature.setText(weatherData.getTemperature() + "°C");
                                        humidity.setText("Umidade: " + weatherData.getHumidity() + "%");
                                        windSpeed.setText("Vento: " + weatherData.getWindSpeed());

                                        if (weatherData.getForecast() != null && !weatherData.getForecast().isEmpty()) {
                                            WeatherData.Forecast firstForecast = weatherData.getForecast().get(0);
                                            forecast.setText(firstForecast.getDescription());
                                        }

                                        Cidade novaCidade = new Cidade(weatherData.getCityName(), estadoSelecionado);
                                        citiesList.add(novaCidade);

                                        citiesAdapter.notifyDataSetChanged();

                                    } else {
                                        Toast.makeText(getActivity(), "Erro ao processar os dados do clima.", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(getActivity(), "Erro ao obter os dados do clima.", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        citiesAdapter = new CitiesAdapter(citiesList, cidade -> {
        });

        recyclerView.setAdapter(citiesAdapter);

        return view;
    }

    private WeatherData parseWeatherData(String weatherDataJson) {
        Gson gson = new Gson();
        return gson.fromJson(weatherDataJson, WeatherData.class);
    }

    private void carregarCidades(String estadoSelecionado) {
        executorService.execute(() -> {
            List<Cidade> cidades = fetchCidadesFromAPI(estadoSelecionado);
            if (getActivity() != null) {
                getActivity().runOnUiThread(() -> {
                    List<String> cidadeNomes = new ArrayList<>();
                    cidadeNomes.add("Cidade");
                    for (Cidade cidade : cidades) {
                        cidadeNomes.add(cidade.getNome());
                    }
                    ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, cidadeNomes);
                    cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(cityAdapter);

                    citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (position > 0) {
                                String nomeCidade = cidades.get(position - 1).getNome();
                                codigoIbgeEditText.setText(nomeCidade + "," + estadoSelecionado);
                            } else {
                                codigoIbgeEditText.setText("");
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            codigoIbgeEditText.setText("");
                        }
                    });
                });
            }
        });
    }

    private List<Cidade> fetchCidadesFromAPI(String estadoSelecionado) {
        List<Cidade> cidades = new ArrayList<>();
        try {
            String urlString = "https://servicodados.ibge.gov.br/api/v1/localidades/estados/" + estadoSelecionado + "/municipios";
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(10000);  // Timeout de 10 segundos
            urlConnection.setReadTimeout(10000);     // Timeout de leitura de 10 segundos

            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            String jsonResponse = response.toString();
            Gson gson = new Gson();
            cidades = gson.fromJson(jsonResponse, new TypeToken<List<Cidade>>(){}.getType());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cidades;
    }
    private String fetchWeatherData(String urlString) {
        StringBuilder response = new StringBuilder();
        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(10000);
            urlConnection.setReadTimeout(10000);

            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response.toString();
    }

    private void carregarEstados() {
        estados.add("AC");
        estados.add("AL");
        estados.add("AM");
        estados.add("AP");
        estados.add("BA");
        estados.add("CE");
        estados.add("DF");
        estados.add("ES");
        estados.add("GO");
        estados.add("MA");
        estados.add("MG");
        estados.add("MS");
        estados.add("MT");
        estados.add("PA");
        estados.add("PB");
        estados.add("PE");
        estados.add("PI");
        estados.add("PR");
        estados.add("RJ");
        estados.add("RN");
        estados.add("RO");
        estados.add("RR");
        estados.add("RS");
        estados.add("SC");
        estados.add("SE");
        estados.add("SP");
        estados.add("TO");
    }
}
