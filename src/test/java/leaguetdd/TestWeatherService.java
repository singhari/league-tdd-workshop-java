package leaguetdd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.util.Map;

import org.junit.Test;

public class TestWeatherService {

    @Test
    public void testGetWeather() throws IOException {
        WeatherService service = new WeatherService();
        Map<String, Object> data = service.getWeather("New York");
        System.out.println("Weather data: " + data);
        assertNotNull(data);
        assertNotNull("Temperature should be present in weather data", data.get("temperature"));
        assertNotNull("Humidity should be present in weather data", data.get("humidity"));
    }

    @Test
    public void testGetWeatherCache() throws IOException {
        WeatherService service = new WeatherService();
        Map<String, Object> data1 = service.getWeather("New York");
        Map<String, Object> data2 = service.getWeather("New York");
        assertEquals("Data should be cached and equal", data1, data2);
    }

    @Test
    public void testHandleApiError() throws IOException {
        WeatherService service = new WeatherService();
        Map<String, Object> data = service.getWeather("InvalidCity");
        assertNull("Data should be null for invalid city", data);
    }
}
