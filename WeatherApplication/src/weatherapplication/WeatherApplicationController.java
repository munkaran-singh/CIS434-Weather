/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package weatherapplication;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class WeatherApplicationController {

    @FXML
    private Text cityName;

    @FXML
    private TextField cityInput;
    
    @FXML
    private Text currentPrecipitation;

    @FXML
    private Text currentTemp;

    @FXML
    private Text currentTemp1;

    @FXML
    private Text feelsLike;

    @FXML
    private Text highLow1;

    @FXML
    private Text highLow2;

    @FXML
    private Text highLow3;

    @FXML
    private Text highLow4;

    @FXML
    private Text highLow5;

    @FXML
    private Text highLow6;

    @FXML
    private Text highLowToday;

    @FXML
    private Text humidityPercent;

    @FXML
    private Text precipitation;

    @FXML
    private Text precipitation1;

    @FXML
    private Text precipitation2;

    @FXML
    private Text precipitation3;

    @FXML
    private Text precipitation4;

    @FXML
    private Text precipitation5;

    @FXML
    private Text precipitation6;

    @FXML
    private Text precipitationHourFive;

    @FXML
    private Text precipitationHourFour;

    @FXML
    private Text precipitationHourOne;

    @FXML
    private Text precipitationHourThree;

    @FXML
    private Text precipitationHourTwo;

    @FXML
    private Text precipitationToday;

    @FXML
    private Button searchButton;

    @FXML
    private Text sunrise1;

    @FXML
    private Text sunrise2;

    @FXML
    private Text sunrise3;

    @FXML
    private Text sunrise4;

    @FXML
    private Text sunrise5;

    @FXML
    private Text sunrise6;

    @FXML
    private Text sunriseToday;

    @FXML
    private Text sunset1;

    @FXML
    private Text sunset2;

    @FXML
    private Text sunset3;

    @FXML
    private Text sunset4;

    @FXML
    private Text sunset5;

    @FXML
    private Text sunset6;

    @FXML
    private Text sunsetToday;

    @FXML
    private Text tempHourFive;

    @FXML
    private Text tempHourFour;

    @FXML
    private Text tempHourOne;

    @FXML
    private Text tempHourThree;

    @FXML
    private Text tempHourTwo;

    @FXML
    private Text windDirection;

    @FXML
    private Text windSpeed;
    
    @FXML
    private ImageView weatherImage;
    
    @FXML
    private ListView<String> savedList;
    private ObservableList<String> recentSearches = FXCollections.observableArrayList();
   
    @FXML
    private void initialize() {
        savedList.setItems(recentSearches); // Bind the ListView to the ObservableList
    }
    
    @FXML
    private void handleSearch(ActionEvent event) {
        cityNameString = cityInput.getText().trim();
        
        if (cityNameString.isEmpty()) {
            return;
        }

        // Add location to recent searches if not already present
        if (!recentSearches.contains(cityNameString)) {
            recentSearches.add(cityNameString);
        }

        // Limit the list to the last 10 searches
        if (recentSearches.size() > 10) {
            recentSearches.remove(0);
        }
        String imagePath = "/image/Sun.png"; //eventually put into if & else if for determining photo
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        weatherImage.setImage(image);
        // Update UI elements with weather data for the new location
        updateWeatherInfo(cityNameString);
    }
    String cityNameString = "Cleveland";
    JSONObject coordinates = (JSONObject) getLocation(cityNameString);
            String coordCityName = (String) coordinates.get("name");
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
                double currentPrecipitationAPI = (double) currentWeather.get("precipitation");
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



    private void updateWeatherInfo(String location) {
        
        //Left Pane
        updateHumidity(humidityPercentString);
        updatePrecipitation(precipitationString);
        updateWindDirection(windDirectionString);
        updateWindSpeed(windSpeedString);
        updateFeelsLike(feelsLikeString);
        //Center Pane Top
        updateName(cityNameString);
        updateCurrentTemp(currentTempString);
        //Center Pane Hourly Percipitation
        updateCurrentPrecipitation(precipitationString);
        updatePrecipitationHourOne(precipitationHourOneString);
        updatePrecipitationHourtwo(precipitationHourTwoString);
        updatePrecipitationHourthree(precipitationHourThreeString);
        updatePrecipitationHourFour(precipitationHourFourString);
        updatePrecipitationHourFive(precipitationHourFiveString);
        //Center Pane Hourly Temp
        updateCurrentTemp1(currentTempString);
        updateTempHourOne(tempHourOneString);
        updateTempHourTwo(tempHourTwoString);
        updateTempHourThree(tempHourThreeString);
        updateTempHourFour(tempHourFourString);
        updateTempHourFive(tempHourFiveString);
        //Right Pane +0 Days
        updateHighLowToday(highLowTodayString);
        updatePrecipitationToday(precipitationTodayString);
        updateSunriseToday(sunriseTodayString);
        updateSunsetToday(sunsetTodayString);
        //Right Pane +1 Day
        updateHighLow1(highLow1String);
        updatePrecipitation1(precipitation1String);
        updateSunrise1(sunrise1String);
        updateSunset1(sunset1String);
        //Right Pane +2 Days
        updateHighLow2(highLow2String);
        updatePrecipitation2(precipitation2String);
        updateSunrise2(sunrise2String);
        updateSunset2(sunset2String);
        //Right Pane +3 Days
        updateHighLow3(highLow3String);
        updatePrecipitation3(precipitation3String);
        updateSunrise3(sunrise3String);
        updateSunset3(sunset3String);
        //Right Pane +4 Days
        updateHighLow4(highLow4String);
        updatePrecipitation4(precipitation4String);
        updateSunrise4(sunrise4String);
        updateSunset4(sunset4String);
        //Right Pane +5 Days
        updateHighLow5(highLow5String);
        updatePrecipitation5(precipitation5String);
        updateSunrise5(sunrise5String);
        updateSunset5(sunset5String);
        //Right Pane +6 Days
        updateHighLow6(highLow6String);
        updatePrecipitation6(precipitation6String);
        updateSunrise6(sunrise6String);
        updateSunset6(sunset6String);
    }

    
    //Left Pane
    private void updatePrecipitation(String text) {
        precipitation.setText(text);}      
    private void updateHumidity(String text) {
        humidityPercent.setText(text);}
    private void updateWindDirection(String text) {
        windDirection.setText(text);}
    private void updateWindSpeed(String text) {
        windSpeed.setText(text);}
    private void updateFeelsLike(String text) {
        feelsLike.setText(text);}
    //Center Pane Top
    private void updateName(String text) {
        cityName.setText(text);}
    private void updateCurrentTemp(String text) {
        currentTemp.setText(text);}
    //Center Pane Hourly Precipitation
    private void updateCurrentPrecipitation(String text) {
        currentPrecipitation.setText(text);}      
    private void updatePrecipitationHourOne(String text) {
        precipitationHourOne.setText(text);}
    private void updatePrecipitationHourtwo(String text) {
        precipitationHourTwo.setText(text);}
    private void updatePrecipitationHourthree(String text) {
        precipitationHourThree.setText(text);}
    private void updatePrecipitationHourFour(String text) {
        precipitationHourFour.setText(text);}
    private void updatePrecipitationHourFive(String text) {
        precipitationHourFive.setText(text);}
    //Center Pane Hourly Temp
    private void updateCurrentTemp1(String text) {
        currentTemp1.setText(text);}    
    private void updateTempHourOne(String text) {
        tempHourOne.setText(text);}    
    private void updateTempHourTwo(String text) {
        tempHourTwo.setText(text);} 
    private void updateTempHourThree(String text) {
        tempHourThree.setText(text);} 
    private void updateTempHourFour(String text) {
        tempHourFour.setText(text);} 
    private void updateTempHourFive(String text) {
        tempHourFive.setText(text);} 
    //Right Pane +0 Days
     private void updateHighLowToday(String text) {
        highLowToday.setText(text);}   
    private void updatePrecipitationToday(String text) {
        precipitationToday.setText(text);}
    private void updateSunriseToday(String text) {
        sunriseToday.setText(text);}
    private void updateSunsetToday(String text) {
        sunsetToday.setText(text);} 
    //Right Pane +1 Day
     private void updateHighLow1(String text) {
        highLow1.setText(text);}   
    private void updatePrecipitation1(String text) {
        precipitation1.setText(text);}
    private void updateSunrise1(String text) {
        sunrise1.setText(text);}
    private void updateSunset1(String text) {
        sunset1.setText(text);} 
    //Right Pane +2 Days
     private void updateHighLow2(String text) {
        highLow2.setText(text);}   
    private void updatePrecipitation2(String text) {
        precipitation2.setText(text);}
    private void updateSunrise2(String text) {
        sunrise2.setText(text);}
    private void updateSunset2(String text) {
        sunset2.setText(text);} 
    //Right Pane +3 Days
     private void updateHighLow3(String text) {
        highLow3.setText(text);}   
    private void updatePrecipitation3(String text) {
        precipitation3.setText(text);}
    private void updateSunrise3(String text) {
        sunrise3.setText(text);}
    private void updateSunset3(String text) {
        sunset3.setText(text);} 
    //Right Pane +4 Days
     private void updateHighLow4(String text) {
        highLow4.setText(text);}   
    private void updatePrecipitation4(String text) {
        precipitation4.setText(text);}
    private void updateSunrise4(String text) {
        sunrise4.setText(text);}
    private void updateSunset4(String text) {
        sunset4.setText(text);} 
    //Right Pane +5 Days
     private void updateHighLow5(String text) {
        highLow5.setText(text);}   
    private void updatePrecipitation5(String text) {
        precipitation5.setText(text);}
    private void updateSunrise5(String text) {
        sunrise5.setText(text);}
    private void updateSunset5(String text) {
        sunset5.setText(text);} 
    //Right Pane +6 Days
     private void updateHighLow6(String text) {
        highLow6.setText(text);}   
    private void updatePrecipitation6(String text) {
        precipitation6.setText(text);}
    private void updateSunrise6(String text) {
        sunrise6.setText(text);}
    private void updateSunset6(String text) {
        sunset6.setText(text);} 
    
    //Left Pane
    String humidityPercentString = String.valueOf(currentRelativeHumidity)+String.valueOf(currentWeatherUnits.get("relative_humidity_2m"));
    String windDirectionString = String.valueOf(currentWindDirection);
    String windSpeedString = String.valueOf(currentWindSpeed);
    String feelsLikeString = String.valueOf(currentApparentTemperature);
    //Center Pane Top
    //Center Pane Hourly Precipitation
    String precipitationString = "50%"; //used for left pane as well
    String precipitationHourOneString = "51%";
    String precipitationHourTwoString = "52%";
    String precipitationHourThreeString = "53%";
    String precipitationHourFourString = "54%";
    String precipitationHourFiveString = "55%";
    //Center Pane Hourly Temp
    String currentTempString = "70º"; //used for center pane top as well
    String tempHourOneString = "71º";
    String tempHourTwoString = "72º";
    String tempHourThreeString = "73º";
    String tempHourFourString = "74º";
    String tempHourFiveString = "75º";
    //Right Pane +0 Days
    String highLowTodayString = "80º/40º";
    String precipitationTodayString = "10% precipitation";
    String sunriseTodayString = "Sunrise: 8:00 am";
    String sunsetTodayString = "Sunset: 6:00 pm";
    //Right Pane +1 Day
    String highLow1String = "81º/41º";
    String precipitation1String = "11% precipitation";
    String sunrise1String = "Sunrise: 8:01 am";
    String sunset1String = "Sunset: 6:01 pm";
    //Right Pane +2 Days
    String highLow2String = "82º/42º";
    String precipitation2String = "12% precipitation";
    String sunrise2String = "Sunrise: 8:02 am";
    String sunset2String = "Sunset: 6:02 pm";
    //Right Pane +3 Days
    String highLow3String = "83º/43º";
    String precipitation3String = "13% precipitation";
    String sunrise3String = "Sunrise: 8:03 am";
    String sunset3String = "Sunset: 6:03 pm";
    //Right Pane +4 Days
    String highLow4String = "84º/44º";
    String precipitation4String = "14% precipitation";
    String sunrise4String = "Sunrise: 8:04 am";
    String sunset4String = "Sunset: 6:04 pm";
    //Right Pane +5Days
    String highLow5String = "85º/45º";
    String precipitation5String = "15% precipitation";
    String sunrise5String = "Sunrise: 8:05 am";
    String sunset5String = "Sunset: 6:05 pm";
    //Right Pane +6 Days
    String highLow6String = "86º/46º";
    String precipitation6String = "16% precipitation";
    String sunrise6String = "Sunrise: 8:06 am";
    String sunset6String = "Sunset: 6:06 pm";
    
    
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
