package com.project.weather.Controller;

import com.project.weather.Service.IpAddressService;
import com.project.weather.Service.LocationService;
import com.project.weather.Models.Weather;
import com.project.weather.Service.WeatherService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/weather")
public class WeatherController {

    private WeatherService weatherService;
    private LocationService locationService;

    @Autowired
    public WeatherController(WeatherService weatherService, LocationService locationService) {
        this.weatherService = weatherService;
        this.locationService= locationService;
    }

    @GetMapping("/{city}")
    public String getWeather(@PathVariable String city, Model model) {
        Weather weather = weatherService.getWeatherByCity(city);
        System.out.println("JSON Response: " + weather);
        // Add the Weather object to the model for rendering in the view
        model.addAttribute("weather", weather);
        // Return the name of the view (e.g., "weatherView") where you want to display the weather information
        return "weather";
    }

    @GetMapping("/location")
    public String getWeatherByLocation(@RequestParam(required = false) String ipAddress, HttpServletRequest request, Model model) {
        if (ipAddress == null) {
            ipAddress = IpAddressService.getClientIpAddress(request);
            System.out.println("Client IP Address: " + ipAddress);
        }

        System.out.println("IP Address: " + ipAddress);

        // Ensure that you have a valid IP address before proceeding
        if (ipAddress != null && !ipAddress.isEmpty() && !"unknown".equalsIgnoreCase(ipAddress)) {
            Weather weather = weatherService.getWeatherByLocation(ipAddress);
            System.out.println("JSON Response: " + weather);
            model.addAttribute("weather", weather);
            return "weather";
        } else {
            // Handle the case where the IP address is not available
            return "error"; // You can create an error view or redirect to an error page
        }
    }


}

