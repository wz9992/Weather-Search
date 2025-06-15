package org.example.model;

import lombok.Data;
import org.json.JSONObject;

@Data
public class WeatherDTO {
    private String city;
    private int temperature;
    private int humidity;
    private String description;
    private String iconUrl;

    public static WeatherDTO fromJson(JSONObject json) {
        JSONObject main = json.getJSONObject("main");
        JSONObject weather = json.getJSONArray("weather").getJSONObject(0);

        WeatherDTO dto = new WeatherDTO();
        dto.setCity(json.getString("name"));
        dto.setTemperature(main.getInt("temp"));
        dto.setHumidity(main.getInt("humidity"));
        dto.setDescription(weather.getString("description"));
        dto.setIconUrl("http://openweathermap.org/img/w/" +
                weather.getString("icon") + ".png");
        return dto;
    }
}
