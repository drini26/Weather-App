package com.project.weather.Controller;
import com.project.weather.Service.LocationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/user")
public class UserController {

    private final LocationService locationService;

    @Autowired
    public UserController(LocationService locationService) {
        this.locationService = locationService;
    }
    @GetMapping("/location")
    public String getUserLocation(HttpServletRequest request) {
        String ipAddress = request.getRemoteAddr();
        LocationService.LocationInfo locationInfo = locationService.getLocationFromIpAddress(ipAddress);
        return "User's location: " + locationInfo.getLatitude() + ", " + locationInfo.getLongitude();
    }

}
