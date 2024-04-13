package com.project.weather.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Weather {
    private Coord coord;
    private List<WeatherDetails> weather;
    private String base;
    private MainDetails main;
    private int visibility;
    private WindDetails wind;
    private CloudsDetails clouds;
    private long dt;
    private SysDetails sys;
    private int timezone;
    private long id;
    private String name;
    private int cod;
    private RainDetails rain;

    @Data
    public static class RainDetails {
        @JsonProperty("1h")
        private double rain1h;
    }
    @Data
    public static class Coord {
        private double lon;
        private double lat;
    }

    @Data
    public static class WeatherDetails {
        private int id;
        private String main;
        private String description;
        private String icon;
    }

    @Data
    public static class MainDetails {
        private double temp;
        private double feels_like;
        private double temp_min;
        private double temp_max;
        private double sea_level;
        private double grnd_level;
        private int pressure;
        private int humidity;
        public double getPressureAtm(){
              return (double)  pressure/1000;
        }
    }

    @Data
    public static class WindDetails {
        private double speed;
        private int deg;
        private double gust;
    }

    @Data
    public static class CloudsDetails {
        private int all;
    }

    @Data
    public static class SysDetails {
        private int type;
        private int id;
        private String country;
        private long sunrise;
        private long sunset;
    }

}
