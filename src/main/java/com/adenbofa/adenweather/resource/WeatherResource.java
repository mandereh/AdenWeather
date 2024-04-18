package com.adenbofa.adenweather.resource;

import com.adenbofa.adenweather.domain.WeatherRequestDetails;
import com.adenbofa.adenweather.entity.WeatherResponse;
import com.adenbofa.adenweather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class WeatherResource {


    private WeatherService weatherService;

    @Autowired
    public WeatherResource(WeatherService weatherService) {
        this.weatherService = weatherService;
    }
    @GetMapping("/weather/{city}")
    public @ResponseBody WeatherResponse weather(@PathVariable("city") String city) {
        final WeatherRequestDetails weatherRequestDetails = WeatherRequestDetails.builder()
                .city(city)
                .build();
        return weatherService.getWeather(weatherRequestDetails);
    }
}
