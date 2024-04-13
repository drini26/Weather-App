package com.project.weather.Service;
import com.project.weather.Models.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;


@Service
public class WeatherService {
    @Autowired
    LocationService locationService;

    @Value("${openWeatherMap.apiKey}")
    private String apiKey;
    private final String apiUrl = "http://api.openweathermap.org/data/2.5/weather";
    private final String apiLocationUrl = "https://api.openweathermap.org/data/2.5/weather";

    private final RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Weather getWeatherByCity(String city) {
        String url = String.format("%s?q=%s&appid=%s&units=metric", apiUrl, city, apiKey);
        String jsonResponse = restTemplate.getForObject(url, String.class);

        // Use Jackson ObjectMapper to deserialize JSON into Weather object
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Weather weather = objectMapper.readValue(jsonResponse, Weather.class);
            System.out.println("Parsed Weather Object: " + weather);

            return weather;
        } catch (IOException e) {
            // Handle the exception appropriately (e.g., log it or throw a custom exception)
            e.printStackTrace();
            return null;
        }
    }
    public Weather getWeatherByLocation(String ipAddress) {
        LocationService.LocationInfo locationInfo = locationService.getLocationFromIpAddress(ipAddress);

        Double latitude = locationInfo.getLatitude();
        Double longitude = locationInfo.getLongitude();

        String url = String.format("%s?lat=%s&lon=%s&appid=%s&units=metric", apiLocationUrl, latitude, longitude, apiKey);
        String jsonResponse = restTemplate.getForObject(url, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Weather weather = objectMapper.readValue(jsonResponse, Weather.class);
            System.out.println("Parsed Weather Object: " + weather);
            return weather;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }



}

