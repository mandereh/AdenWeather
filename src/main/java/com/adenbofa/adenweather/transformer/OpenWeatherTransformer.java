package com.adenbofa.adenweather.transformer;

import com.adenbofa.adenweather.domain.CityWeather;
import com.adenbofa.adenweather.entity.OpenWeatherResponseEntity;
import com.adenbofa.adenweather.entity.WeatherResponse;
import org.springframework.stereotype.Service;

@Service
public class OpenWeatherTransformer {
    public CityWeather transformToDomain(final OpenWeatherResponseEntity entity) {
        return CityWeather.builder()
                .weather(entity.getWeather()[0].getMain())
                .details(entity.getWeather()[0].getDescription())
                .build();
    }

    public WeatherResponse transformToEntity(CityWeather cityWeather) {
        return WeatherResponse.builder()
                .weather(cityWeather.getWeather())
                .details(cityWeather.getDetails())
                .build();
    }
}
