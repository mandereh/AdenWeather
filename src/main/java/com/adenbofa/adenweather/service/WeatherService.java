package com.adenbofa.adenweather.service;

import com.adenbofa.adenweather.domain.CityCoordinates;
import com.adenbofa.adenweather.domain.CityWeather;
import com.adenbofa.adenweather.domain.WeatherRequestDetails;
import com.adenbofa.adenweather.entity.WeatherResponse;
import com.adenbofa.adenweather.provider.GeocodingProvider;
import com.adenbofa.adenweather.transformer.GeocodingCoordinatesTransformer;
import com.adenbofa.adenweather.transformer.OpenWeatherTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    private GeocodingProvider geocodingProvider;
    private GeocodingCoordinatesTransformer geocodingCoordinatesTransformer;
    private  WeatherService weatherProvider;
    private OpenWeatherTransformer openWeatherTransformer;

    @Autowired
    public WeatherService(GeocodingProvider geocodingProvider, GeocodingCoordinatesTransformer geocodingCoordinatesTransformer, WeatherService weatherProvider, OpenWeatherTransformer openWeatherTransformer) {
        this.geocodingProvider = geocodingProvider;
        this.geocodingCoordinatesTransformer = geocodingCoordinatesTransformer;
        this.weatherProvider = weatherProvider;
        this.openWeatherTransformer = openWeatherTransformer;
    }

    public WeatherResponse getWeather(final WeatherRequestDetails weatherRequestDetails) throws Exception {
        //get lattitude and longitude
        final CityCoordinates cityCoordinates = GeocodingCoordinatesTransformer
                .transformToDomain(geocodingProvider.getCoordinates(weatherRequestDetails));
        final CityWeather cityWeather = openWeatherTransformer.transformToDomain(weatherProvider.getWeather(cityCoordinates));

        return openWeatherTransformer.transformToEntity(cityWeather);

    }
}
