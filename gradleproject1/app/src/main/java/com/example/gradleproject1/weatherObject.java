package com.example.gradleproject1;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class weatherObject {
    JSONObject coordinates;
        String coordCityName;
        double cityLatitude; 
        double cityLongitude;
    JSONObject weather;
    JSONObject currentWeatherUnits;
    JSONObject currentWeather;
        String currentDateAndTime;
        Long currentInterval;
        double currentTemperature;
        Long currentRelativeHumidity;
        double currentApparentTemperature;
        long isDayOrNightBinary;
        double currentPrecipitationAPI;
        long currentWeatherCode;
        double currentWindSpeed;
        long currentWindDirection;

    JSONObject hourlyWeatherUnits;
    JSONObject hourlyWeather;
        JSONArray hourlyTime;
        JSONArray hourlyTemperature;
        JSONArray hourlyPrecipitationProbability;

    JSONObject dailyWeatherUnits;
    JSONObject dailyWeather;
        JSONArray dailyDate;
        JSONArray dailyMaxTemp;
        JSONArray dailyMinTemp;
        JSONArray dailySunrise;
        JSONArray dailySunset;
        JSONArray dailyUVMax;
        JSONArray dailyPrecipitationProbability;
    
    
    public String parseTime(String dateTimeString){
        String[] dateTimeArray = dateTimeString.split("T");
        String currentTime24hr = dateTimeArray[1];
        String currentHour24 = currentTime24hr.split(":")[0];
        int tempHour24 = Integer.parseInt(currentHour24);
        String currentMinutes = currentTime24hr.split(":")[1];
        String currentHour12hr = String.valueOf(tempHour24%12);
        String amORpm;
        if (tempHour24/12==0){
            amORpm = " am";
        }
        else {
            amORpm = " pm";
        }
        String currentTimeConcatenated = currentHour12hr+":"+currentMinutes+amORpm;
        return currentTimeConcatenated;
    }

    public String parseDate(String dateTimeString){
        String[] dateTimeArray = dateTimeString.split("T");
        String currentDate = dateTimeArray[0];
        String[] yearMonthDay = currentDate.split("-");
        String year = yearMonthDay[0];
        String month = yearMonthDay[1];
        String day = yearMonthDay[2];
        HashMap<String, String> months = new HashMap<String, String>();
        months.put("01", "January");
        months.put("02", "February");
        months.put("03", "March");
        months.put("04", "April");
        months.put("05", "May");
        months.put("06", "June");
        months.put("07", "July");
        months.put("08", "August");
        months.put("09", "September");
        months.put("10", "October");
        months.put("11", "November");
        months.put("12", "December");
        return months.get(month)+" "+day+", "+year;
    }

    public String parseDayOfWeek(String dateTimeString){
        String[] dateTimeArray = dateTimeString.split("T");
        String currentDate = dateTimeArray[0];
        String[] yearMonthDay = currentDate.split("-");
        int lastTwoOfYear = Integer.parseInt(yearMonthDay[0].replace("20",""));
        int fourthOfYear = lastTwoOfYear/4;
        String month = yearMonthDay[1];
        int day = Integer.parseInt(yearMonthDay[2]);
        HashMap<String, Integer> monthKey = new HashMap<String, Integer>();
        monthKey.put("01",1);
        monthKey.put("02",4);
        monthKey.put("03",4);
        monthKey.put("04",0);
        monthKey.put("05",2);
        monthKey.put("06",5);
        monthKey.put("07",0);
        monthKey.put("08",3);
        monthKey.put("09",6);
        monthKey.put("10",1);
        monthKey.put("11",4);
        monthKey.put("12",6);
        HashMap<Integer, String> dayOfWeek = new HashMap<Integer, String>();
        dayOfWeek.put(0,"Saturday");
        dayOfWeek.put(1,"Sunday");
        dayOfWeek.put(2,"Monday");
        dayOfWeek.put(3,"Tuesday");
        dayOfWeek.put(4,"Wednesday");
        dayOfWeek.put(5,"Thursday");
        dayOfWeek.put(6,"Friday");

        int dayOfWeekKey = (lastTwoOfYear+fourthOfYear+day-1+monthKey.get(month))%7;

        return dayOfWeek.get(dayOfWeekKey);
    }

    public String parseWMO(Long WMO){
        int wmoInt = WMO.intValue();
        HashMap<Integer,String> wmoConditions = new HashMap<Integer, String>();
        wmoConditions.put(0, "Sunny");
        wmoConditions.put(1, "Sunny");
        wmoConditions.put(2, "Sunny");
        wmoConditions.put(3, "Cloudy");
        wmoConditions.put(45, "Cloudy");
        wmoConditions.put(48, "Cloudy");
        wmoConditions.put(51, "Raining");
        wmoConditions.put(53, "Raining");
        wmoConditions.put(55, "Raining");
        wmoConditions.put(56, "Raining");
        wmoConditions.put(57, "Raining");
        wmoConditions.put(61, "Raining");
        wmoConditions.put(63, "Raining");
        wmoConditions.put(65, "Raining");
        wmoConditions.put(66, "Raining");
        wmoConditions.put(67, "Raining");
        wmoConditions.put(80, "Raining");
        wmoConditions.put(81, "Raining");
        wmoConditions.put(82, "Raining");
        wmoConditions.put(95, "Raining");
        wmoConditions.put(96, "Raining");
        wmoConditions.put(99, "Raining");
        wmoConditions.put(71, "Snowing");
        wmoConditions.put(73, "Snowing");
        wmoConditions.put(75, "Snowing");
        wmoConditions.put(77, "Snowing");
        wmoConditions.put(85, "Snowing");
        wmoConditions.put(86, "Snowing");
        

        return wmoConditions.get(wmoInt);
    }

    public static JSONObject getLocation(String userCity){
        try{
            userCity = userCity.replaceAll(" ", "+");
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
            //String weatherAPIurl = "https://api.open-meteo.com/v1/forecast?latitude="+latitude+"&longitude="+longitude+"&current=temperature_2m,relative_humidity_2m,apparent_temperature,is_day,precipitation,weather_code,wind_speed_10m,wind_direction_10m&hourly=temperature_2m,precipitation_probability&daily=temperature_2m_max,temperature_2m_min,sunrise,sunset,uv_index_max,precipitation_sum&temperature_unit=fahrenheit&wind_speed_unit=mph&precipitation_unit=inch&timezone=America%2FNew_York&forecast_hours=6";
            String weatherAPIurl = "https://api.open-meteo.com/v1/forecast?latitude="+latitude+"&longitude="+longitude+"&current=temperature_2m,relative_humidity_2m,apparent_temperature,is_day,precipitation,weather_code,wind_speed_10m,wind_direction_10m&hourly=temperature_2m,precipitation_probability&daily=temperature_2m_max,temperature_2m_min,sunrise,sunset,uv_index_max,precipitation_probability_max&temperature_unit=fahrenheit&wind_speed_unit=mph&timezone=auto&forecast_hours=6";
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

    public weatherObject(String cityName){
        coordinates = (JSONObject) getLocation(cityName);
            coordCityName = (String) coordinates.get("name");
            cityLatitude = (double) coordinates.get("latitude"); 
            cityLongitude = (double) coordinates.get("longitude");

        weather = (JSONObject) getWeather(cityLatitude, cityLongitude);

        
        currentWeatherUnits = (JSONObject) weather.get("current_units");
        currentWeather = (JSONObject) weather.get("current");
            currentDateAndTime = (String) currentWeather.get("time");
            currentInterval = (Long) currentWeather.get("interval");
            currentTemperature = (double) currentWeather.get("temperature_2m");
            currentRelativeHumidity = (Long) currentWeather.get("relative_humidity_2m");
            currentApparentTemperature = (double) currentWeather.get("apparent_temperature");
            isDayOrNightBinary = (long) currentWeather.get("is_day");
            currentPrecipitationAPI = (double) currentWeather.get("precipitation");
            currentWeatherCode = (long) currentWeather.get("weather_code");
            currentWindSpeed = (double) currentWeather.get("wind_speed_10m");
            currentWindDirection = (long) currentWeather.get("wind_direction_10m");

        hourlyWeatherUnits = (JSONObject) weather.get("hourly_units");
        hourlyWeather = (JSONObject) weather.get("hourly");
            hourlyTime = (JSONArray) hourlyWeather.get("time");
            hourlyTemperature = (JSONArray) hourlyWeather.get("temperature_2m");
            hourlyPrecipitationProbability = (JSONArray) hourlyWeather.get("precipitation_probability");

        dailyWeatherUnits = (JSONObject) weather.get("daily_units");
        dailyWeather = (JSONObject) weather.get("daily");
            dailyDate = (JSONArray) dailyWeather.get("time");
            dailyMaxTemp = (JSONArray) dailyWeather.get("temperature_2m_max");
            dailyMinTemp = (JSONArray) dailyWeather.get("temperature_2m_min");
            dailySunrise = (JSONArray) dailyWeather.get("sunrise");
            dailySunset = (JSONArray) dailyWeather.get("sunset");
            dailyUVMax = (JSONArray) dailyWeather.get("uv_index_max");
            dailyPrecipitationProbability = (JSONArray) dailyWeather.get("precipitation_probability_max");
            

    }
    
}
