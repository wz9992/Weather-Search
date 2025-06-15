package org.example.service;

import org.example.model.WeatherDTO;
import org.example.exception.WeatherServiceException;

public interface WeatherService {
    /**
     * 获取城市天气信息
     * @param city 城市名称
     * @return 天气数据对象
     * @throws WeatherServiceException 服务异常
     */
    WeatherDTO getWeather(String city) throws WeatherServiceException;
}