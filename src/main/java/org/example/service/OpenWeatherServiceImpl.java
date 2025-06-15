package org.example.service;

import lombok.Value;
import org.example.exception.WeatherServiceException;
import org.example.model.WeatherDTO;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class OpenWeatherServiceImpl implements WeatherService {

    private static final Logger logger = LoggerFactory.getLogger(OpenWeatherServiceImpl.class);


    private String apiKey = "53e0828a76aec0355452dab147aaaccc";

    private final RestTemplate restTemplate;

    public OpenWeatherServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public WeatherDTO getWeather(String city) throws WeatherServiceException {
        try {
            String url = String.format(
                    "http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric&lang=us",
                    URLEncoder.encode(city, String.valueOf(StandardCharsets.UTF_8)),
                    apiKey
            );

            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);


            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new WeatherServiceException("API 请求失败，状态码: " + response.getStatusCode());
            }

            JSONObject json = new JSONObject(response.getBody());
            return WeatherDTO.fromJson(json);

        } catch (RestClientException e) {
            logger.error("天气服务调用失败", e);
            throw new WeatherServiceException("天气服务暂时不可用", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}