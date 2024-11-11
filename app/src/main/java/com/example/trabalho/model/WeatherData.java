package com.example.trabalho.model;
import java.util.List;

public class WeatherData {
    private String cityName;
    private String description;
    private String temperature;
    private String humidity;
    private String windSpeed;
    private String forecast;

    // Classe interna para o forecast
    public static class Forecast {
        private String date;
        private String description;
        private String max;
        private String min;

        // Getters
        public String getDate() {
            return date;
        }

        public String getDescription() {
            return description;
        }

        public String getMax() {
            return max;
        }

        public String getMin() {
            return min;
        }
    }

    // Classe interna para os dados de "results"
    public static class Results {
        private String city;
        private String description;
        private int temp;
        private int humidity;
        private String windSpeedy;
        private List<Forecast> forecast;

        // Getters
        public String getCity() {
            return city;
        }

        public String getDescription() {
            return description;
        }

        public int getTemp() {
            return temp;
        }

        public int getHumidity() {
            return humidity;
        }

        public String getWindSpeedy() {
            return windSpeedy;
        }

        public List<Forecast> getForecast() {
            return forecast;
        }
    }

    private Results results;

    // Métodos getters
    public String getCityName() {
        return results != null ? results.getCity() : null;
    }

    public String getDescription() {
        return results != null ? results.getDescription() : null;
    }

    public String getTemperature() {
        return String.valueOf(results != null ? results.getTemp() : 0);
    }

    public String getHumidity() {
        return String.valueOf(results != null ? results.getHumidity() : 0);
    }

    public String getWindSpeed() {
        return results != null ? results.getWindSpeedy() : null;
    }

    public List<Forecast> getForecast() {
        return results != null ? results.getForecast() : null;
    }
}
