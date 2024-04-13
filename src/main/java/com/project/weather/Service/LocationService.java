package com.project.weather.Service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class LocationService {

    @Value("${IPSTACK_API_KEY}")
    private String IPSTACK_API_KEY;
    private final WebClient webClient;

    public LocationService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://api.ipstack.com").build();
    }

    public LocationInfo getLocationFromIpAddress(String ipAddress) {
        return webClient.get()
                .uri("/{ip}?access_key={apiKey}", ipAddress, IPSTACK_API_KEY)
                .retrieve()
                .bodyToMono(LocationInfo.class)
                .block();

    }
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class LocationInfo {
        private Double latitude;
        private Double longitude;
    }
}



