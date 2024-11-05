import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;

    //pls COMMENT THE HELL OUTA THIS CODE
    //do it for your future self, he'll soooo appreciate it

    //write time converter function
    //create hasmaps to map Months to numbers, weather status (grouped) to weather code

public class App {
    public static void main(String[] args) throws Exception {
        

        Scanner scanner = new Scanner(System.in);
        String userInput;
        System.out.println("Enter a city:");
        //Enter a city:
        //Press enter to intiate tennis scores API call:
        userInput = scanner.nextLine();

        JSONObject coordinates = (JSONObject) getLocation(userInput);
            String cityName = (String) coordinates.get("name");
            double cityLatitude = (double) coordinates.get("latitude"); 
            double cityLongitude = (double) coordinates.get("longitude");

        JSONObject weather = (JSONObject) getWeather(cityLatitude, cityLongitude);

            JSONObject currentWeatherUnits = (JSONObject) weather.get("current_units");
            JSONObject currentWeather = (JSONObject) weather.get("current");
                String currentTime = (String) currentWeather.get("time");
                Long currentInterval = (Long) currentWeather.get("interval");
                double currentTemperature = (double) currentWeather.get("temperature_2m");
                Long currentRelativeHumidity = (Long) currentWeather.get("relative_humidity_2m");
                double currentApparentTemperature = (double) currentWeather.get("apparent_temperature");
                long isDayOrNightBinary = (long) currentWeather.get("is_day");
                double currentPrecipitation = (double) currentWeather.get("precipitation");
                long currentWeatherCode = (long) currentWeather.get("weather_code");
                double currentWindSpeed = (double) currentWeather.get("wind_speed_10m");
                long currentWindDirection = (long) currentWeather.get("wind_direction_10m");

            JSONObject hourlyWeatherUnits = (JSONObject) weather.get("hourly_units");
            JSONObject hourlyWeather = (JSONObject) weather.get("hourly");
                JSONArray hourlyTime = (JSONArray) hourlyWeather.get("time");
                JSONArray hourlyTemperature = (JSONArray) hourlyWeather.get("temperature_2m");
                JSONArray hourlyPrecipitationProbability = (JSONArray) hourlyWeather.get("precipitation_probability");
                    
            JSONObject dailyWeatherUnits = (JSONObject) weather.get("daily_units");
            JSONObject dailyWeather = (JSONObject) weather.get("daily");
                JSONArray dailyDate = (JSONArray) dailyWeather.get("time");
                JSONArray dailyMaxTemp = (JSONArray) dailyWeather.get("temperature_2m_max");
                JSONArray dailyMinTemp = (JSONArray) dailyWeather.get("temperature_2m_min");
                JSONArray dailySunrise = (JSONArray) dailyWeather.get("sunrise");
                JSONArray dailySunset = (JSONArray) dailyWeather.get("sunset");
                JSONArray dailyUVMax = (JSONArray) dailyWeather.get("uv_index_max");
                JSONArray dailyTotalPrecipitation = (JSONArray) dailyWeather.get("precipitation_sum");


        
        System.out.println("\ntesting testing beep boop \n");
        System.out.println("\nCoordinates for "+cityName+" are:\nLatitude: "+cityLatitude+"\nLongitude: "+cityLongitude+"\n");
        System.out.println("Current temperature in "+cityName+": "+currentTemperature+"\n");
        System.out.println("Time: "+currentTime+"\n");
        System.out.println("Current precipitation: "+currentPrecipitation+" inches\n");
        System.out.println("Hour "+hourlyTime.get(0)+" precip probability: "+hourlyPrecipitationProbability.get(0)+"%\n");
        System.out.println("testing taking directly from arrays (sunset): "+dailySunset.get(0)+" and total precip: "+dailyTotalPrecipitation.get(0)+"\n");
        scanner.close();

    }
    



    public static JSONObject getLocation(String userCity){
        try{
            userCity = userCity.replaceAll(" ", "+");
            //https://geocoding-api.open-meteo.com/v1/search?name="+userCity+"&count=1&language=en&format=json
            //https://app.atptour.com/api/v2/gateway/livematches/website?scoringTournamentLevel
            String userCityGeocodingURL = "https://geocoding-api.open-meteo.com/v1/search?name="+userCity+"&count=1&language=en&format=json";
            HttpURLConnection getGeocodingResponse = getAPIresponse(userCityGeocodingURL);
            String geocodingResponse = parseAPIresponse(getGeocodingResponse);

            JSONParser locJSONparser = new JSONParser();
            JSONObject locJSONparserResults = (JSONObject) locJSONparser.parse(geocodingResponse);
            
            JSONArray coordinates = (JSONArray) locJSONparserResults.get("results");
            return (JSONObject) coordinates.get(0);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static JSONObject getWeather(double latitude, double longitude){
        try{
            String weatherAPIurl = "https://api.open-meteo.com/v1/forecast?latitude="+latitude+"&longitude="+longitude+"&current=temperature_2m,relative_humidity_2m,apparent_temperature,is_day,precipitation,weather_code,wind_speed_10m,wind_direction_10m&hourly=temperature_2m,precipitation_probability&daily=temperature_2m_max,temperature_2m_min,sunrise,sunset,uv_index_max,precipitation_sum&temperature_unit=fahrenheit&wind_speed_unit=mph&precipitation_unit=inch&timezone=America%2FNew_York&forecast_hours=6";
            HttpURLConnection getWeatherResponse = getAPIresponse(weatherAPIurl);
            String weatherResponse = parseAPIresponse(getWeatherResponse);
            
            JSONParser weatherJSONparser = new JSONParser();
            JSONObject weatherJSONparserResults = (JSONObject) weatherJSONparser.parse(weatherResponse);
            
            //JSONObject currentWeather = (JSONObject) weatherJSONparserResults.get("current");
            return (JSONObject) weatherJSONparserResults;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public static HttpURLConnection getAPIresponse(String userURL){
        try{
            URI APIuri = new URI(userURL);
            URL APIurl = APIuri.toURL();
            HttpURLConnection responseConnection = (HttpURLConnection) APIurl.openConnection();
            responseConnection.setRequestMethod("GET");
            return responseConnection;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public static String parseAPIresponse(HttpURLConnection geocodingResponse){
        try{
            StringBuilder apiResponseJSONdata = new StringBuilder();
            Scanner incomingAPIdata = new Scanner(geocodingResponse.getInputStream());
            while (incomingAPIdata.hasNext()) {
                apiResponseJSONdata.append(incomingAPIdata.nextLine());
            }
            incomingAPIdata.close();
            //System.out.println("testing beep boop, printing out toString() of API response:\n"+apiResponseJSONdata.toString()+"\n");
            return apiResponseJSONdata.toString();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}


