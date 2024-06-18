package leaguetdd;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;

public class WeatherService {
    private Map<String, CacheEntry> cache;
    private static final long CACHE_DURATION = 600000; // cache duration in milliseconds

    public WeatherService() {
        this.cache = new HashMap<>();
    }

    public Map<String, Object> getWeather(String city) {
        long currentTime = System.currentTimeMillis();

        // Check cache first
        if (cache.containsKey(city)) {
            CacheEntry entry = cache.get(city);
            if (currentTime - entry.timestamp < CACHE_DURATION) {
                return entry.data;
            }
        }
        try {
            return getWeatherFromApi(city);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Map<String, Object> getWeatherFromApi(String city)
            throws MalformedURLException, IOException, ProtocolException {
        // Fetch new data
        URL url = new URL(
                "https://api.openweathermap.org/data/2.5/weather?lat=44.34&lon=10.99&appid=4523628bd365a0c61f43c9ba0c9cd1ff");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        // Check if the connection is successful
        int responseCode = conn.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        } else {
            StringBuilder inline = new StringBuilder();
            Scanner scanner = new Scanner(url.openStream());

            while (scanner.hasNext()) {
                inline.append(scanner.nextLine());
            }
            scanner.close();

            // Print the JSON response for debugging
            String json = inline.toString();
            System.out.println("API Response: " + json);
            Map<String, Object> weatherData = parseJson(json);

            // Update cache
            long currentTime = System.currentTimeMillis();
            cache.put(city, new CacheEntry(weatherData, currentTime));

            return weatherData;
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private Map<String, Object> parseJson(String json) {
        Gson gson = new Gson();
        Map<String, Map> fromJson = gson.fromJson(json, Map.class);
        Map<String, Object> data = new HashMap<>();
        data.put("temperature", fromJson.get("main").get("temp"));
        data.put("humidity", fromJson.get("main").get("humidity"));

        return data;
    }

    private class CacheEntry {
        Map<String, Object> data;
        long timestamp;

        CacheEntry(Map<String, Object> data, long timestamp) {
            this.data = data;
            this.timestamp = timestamp;
        }
    }

    public static void main(String[] args) throws IOException {
        WeatherService service = new WeatherService();
        Map<String, Object> data = service.getWeather("New York");
        if (data != null) {
            System.out.println("Temperature: " + data.get("temperature"));
            System.out.println("Humidity: " + data.get("humidity"));
        } else {
            System.out.println("Failed to fetch weather data");
        }
    }
}
