package com.adenbofa.adenweather.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CityWeather {
    private String weather;
    private String details;

}
