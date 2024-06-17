package leagueTest;

//WeatherService.java
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class WeatherService {
 private Map<String, CacheEntry> cache;
 private static final long CACHE_DURATION = 600000;  // cache duration in milliseconds

 public WeatherService() {
     this.cache = new HashMap<>();
 }

 public Map<String, Object> getWeather(String city) throws IOException {
     long currentTime = System.currentTimeMillis();

     // Check cache first
     if (cache.containsKey(city)) {
         CacheEntry entry = cache.get(city);
         if (currentTime - entry.timestamp < CACHE_DURATION) {
             return entry.data;
         }
     }

     // Fetch new data
    URL url = new URL("https://api.openweathermap.org/data/2.5/weather?lat=44.34&lon=10.99&appid=4523628bd365a0c61f43c9ba0c9cd1ff");
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
        System.out.println("API Response: " + inline.toString());

        // Parse JSON data manually
        String json = inline.toString();
        Map<String, Object> weatherData = parseJson(json);

        // Update cache
        cache.put(city, new CacheEntry(weatherData, currentTime));

        return weatherData;
    }
 }

 private Map<String, Object> parseJson(String json) {
    Gson gson = new Gson();
    return gson.fromJson(json);
     Map<String, Object> data = new HashMap<>();
     String tempKey = "\"temp\":";
     String humidityKey = "\"humidity\":";

     int tempIndex = json.indexOf(tempKey) + tempKey.length();
     int tempEndIndex = json.indexOf(",", tempIndex);
     double temperature = Double.parseDouble(json.substring(tempIndex, tempEndIndex).trim());

     int humidityIndex = json.indexOf(humidityKey) + humidityKey.length();
     int humidityEndIndex = json.indexOf("}", humidityIndex);
     int humidity = Integer.parseInt(json.substring(humidityIndex, humidityEndIndex).trim());

     data.put("temperature", temperature);
     data.put("humidity", humidity);

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


