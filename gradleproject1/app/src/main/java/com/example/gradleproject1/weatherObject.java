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
        String cityState;
        String cityCountry;
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

    public String parseTimeHourOnly(String dateTimeString){
        String[] dateTimeArray = dateTimeString.split("T");
        String currentTime24hr = dateTimeArray[1];
        String currentHour24 = currentTime24hr.split(":")[0];
        int tempHour24 = Integer.parseInt(currentHour24);
        String currentMinutes = currentTime24hr.split(":")[1];
        String currentHour12hr = String.valueOf(tempHour24%12);
        if (tempHour24%12==0){
            currentHour12hr = "12";
        }
        String amORpm;
        if (tempHour24/12==0){
            amORpm = " am";
        }
        else {
            amORpm = " pm";
        }
        String currentHour = currentHour12hr+amORpm;
        return currentHour;
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
        String dateTimeArray = dateTimeString;
        String currentDate = dateTimeArray;
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
        wmoConditions.put(0, "Clear sky");
        wmoConditions.put(1, "Mainly clear");
        wmoConditions.put(2, "Partly cloudy");
        wmoConditions.put(3, "Overcast");
        wmoConditions.put(45, "Fog");
        wmoConditions.put(48, "Depositing rime fog");
        wmoConditions.put(51, "Drizzle: light intensity");
        wmoConditions.put(53, "Drizzle: moderate intensity");
        wmoConditions.put(55, "Drizzle: dense intensity");
        wmoConditions.put(56, "Freezing Drizzle: light intensity");
        wmoConditions.put(57, "Freezing Drizzle: dense intensity");
        wmoConditions.put(61, "Rain: slight intensity");
        wmoConditions.put(63, "Rain: moderate intensity");
        wmoConditions.put(65, "Rain: heavy intensity");
        wmoConditions.put(66, "Freezing Rain: light intensity");
        wmoConditions.put(67, "Freezing Rain: heavy intensity");
        wmoConditions.put(71, "Snow fall: slight intensity");
        wmoConditions.put(73, "Snow Fall: moderate intensity");
        wmoConditions.put(75, "Snow Fall: heavy intensity");
        wmoConditions.put(77, "snow grains");

        wmoConditions.put(80, "Rain Showers: slight");
        wmoConditions.put(81, "Rain Showers: moderate");
        wmoConditions.put(82, "Rain Showers: violent");

        wmoConditions.put(85, "Snow Showers; slight");
        wmoConditions.put(86, "Snow Showers: heavy");

        wmoConditions.put(95, "Thunderstorm: slight or moderate");
        wmoConditions.put(96, "Thunderstorm with slight hail");
        wmoConditions.put(99, "Thunderstorm with heavy hail");
        
        
        

        return wmoConditions.get(wmoInt);
    }

    public String parseWMOforImage(String WMOValue){
        HashMap<String,String> wmoConditionsforImage = new HashMap<String, String>();
        wmoConditionsforImage.put("Clear sky", "Sunny");
        wmoConditionsforImage.put("Mainly clear", "Sunny");
        wmoConditionsforImage.put("Partly cloudy", "Cloudy");
        wmoConditionsforImage.put("Overcast", "Cloudy");
        wmoConditionsforImage.put("Fog", "Cloudy");
        wmoConditionsforImage.put("Depositing rime fog", "Cloudy");
        wmoConditionsforImage.put("Drizzle: light intensity", "Raining");
        wmoConditionsforImage.put("Drizzle: moderate intensity", "Raining");
        wmoConditionsforImage.put("Drizzle: dense intensity", "Raining");
        wmoConditionsforImage.put("Freezing Drizzle: light intensity", "Raining");
        wmoConditionsforImage.put("Freezing Drizzle: dense intensity", "Raining");
        wmoConditionsforImage.put("Rain: slight intensity", "Raining");
        wmoConditionsforImage.put("Rain: moderate intensity", "Raining");
        wmoConditionsforImage.put("Rain: heavy intensity", "Raining");
        wmoConditionsforImage.put("Freezing Rain: light intensity", "Raining");
        wmoConditionsforImage.put("Freezing Rain: heavy intensity", "Raining");
        wmoConditionsforImage.put("Rain Showers: slight", "Raining");
        wmoConditionsforImage.put("Rain Showers: moderate", "Raining");
        wmoConditionsforImage.put("Rain Showers: violent", "Raining");
        wmoConditionsforImage.put("Thunderstorm: slight or moderate", "Raining");
        wmoConditionsforImage.put("Thunderstorm with slight hail", "Raining");
        wmoConditionsforImage.put("Thunderstorm with heavy hail", "Raining");
        wmoConditionsforImage.put("Snow fall: slight intensity", "Snowing");
        wmoConditionsforImage.put("Snow Fall: moderate intensity", "Snowing");
        wmoConditionsforImage.put("Snow Fall: heavy intensity", "Snowing");
        wmoConditionsforImage.put("snow grains", "Snowing");
        wmoConditionsforImage.put("Snow Showers; slight", "Snowing");
        wmoConditionsforImage.put("Snow Showers: heavy", "Snowing");
        

        return wmoConditionsforImage.get(WMOValue);
    }

    public static JSONObject getLocation(String userCity){
        try{
            userCity = userCity.replaceAll(" ", "+");
            String userCityGeocodingURL = "https://geocoding-api.open-meteo.com/v1/search?name="+userCity+"&count=1&language=en&format=json";
            HttpURLConnection getGeocodingResponse = getAPIresponse(userCityGeocodingURL);
            String geocodingResponse = parseAPIresponse(getGeocodingResponse);

            /*
            if (geocodingResponse.equals("Please enter a valid city/zip code")){
                System.out.println("recieved error message");
                return null;
            }
            else{
            */
                //System.out.println("recievbed no error message");
                JSONParser locJSONparser = new JSONParser();
                JSONObject locJSONparserResults = (JSONObject) locJSONparser.parse(geocodingResponse);
                //System.out.println("testing pre-result json: "+locJSONparserResults.toString());
                if(locJSONparserResults.containsKey("results")){
                    JSONArray coordinates = (JSONArray) locJSONparserResults.get("results");
                    return (JSONObject) coordinates.get(0);
                }
                else{
                    return locJSONparserResults;
                }
                
                

            //}

            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static JSONObject getWeather(double latitude, double longitude){
        try{
            //String weatherAPIurl = "https://api.open-meteo.com/v1/forecast?latitude="+latitude+"&longitude="+longitude+"&current=temperature_2m,relative_humidity_2m,apparent_temperature,is_day,precipitation,weather_code,wind_speed_10m,wind_direction_10m&hourly=temperature_2m,precipitation_probability&daily=temperature_2m_max,temperature_2m_min,sunrise,sunset,uv_index_max,precipitation_sum&temperature_unit=fahrenheit&wind_speed_unit=mph&precipitation_unit=inch&timezone=America%2FNew_York&forecast_hours=6";
            String weatherAPIurl = "https://api.open-meteo.com/v1/forecast?latitude="+latitude+"&longitude="+longitude+"&current=temperature_2m,relative_humidity_2m,apparent_temperature,is_day,precipitation,weather_code,wind_speed_10m,wind_direction_10m&hourly=temperature_2m,precipitation_probability&daily=temperature_2m_max,temperature_2m_min,sunrise,sunset,uv_index_max,precipitation_probability_max&temperature_unit=fahrenheit&wind_speed_unit=mph&timezone=auto&forecast_hours=6&past_days=7";
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
            /*
            if (apiResponseJSONdata.toString().contains("results") || apiResponseJSONdata.toString().contains("latitude")) {
                System.out.println("found weather results!");
                System.out.println("returning toSTring of apiResponseJSONData");
                //System.out.println("testing beep boop, printing out toString() of API response:\n"+apiResponseJSONdata.toString()+"\n");
                
                
            }
            else{
                System.out.println("no results found :(");
                System.out.println("retuning error message");
                //System.out.println("testing beep boop, printing out toString() of API response:\n"+apiResponseJSONdata.toString()+"\n");
                return("Please enter a valid city/zip code");
            }
            */
            return apiResponseJSONdata.toString();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
/*
    public weatherObject(String cityName, int locationListLength){
        coordinates = (JSONObject) getLocation(cityName, locationListLength);
            coordCityName = (String) coordinates.get("name");
            cityLatitude = (double) coordinates.get("latitude"); 
            cityLongitude = (double) coordinates.get("longitude");
            cityState = (String) coordinates.get("admin1");
            cityCountry = (String) coordinates.get("country");
    }*/


    public weatherObject(){
    }

    public weatherObject(String cityName){
        System.out.println("weatherObject created");
        coordinates = (JSONObject) getLocation(cityName);
        System.out.println("set coordinates variable");
        System.out.println("name: "+coordinates.get("name"));
            coordCityName = (String) coordinates.get("name");
            cityLatitude = (double) coordinates.get("latitude"); 
            cityLongitude = (double) coordinates.get("longitude");
            cityState = (String) coordinates.get("admin1");
            cityCountry = (String) coordinates.get("country");
            /*
            if (coordinates != null){
                System.out.println("city found");
                isCity = true;
            }
            else{
                System.out.println("city not found");
            }*/

        weather = (JSONObject) getWeather(cityLatitude, cityLongitude);
        System.out.println("set weather variable");

        
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
