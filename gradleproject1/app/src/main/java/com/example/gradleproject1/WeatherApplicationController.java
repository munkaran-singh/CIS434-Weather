/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.gradleproject1;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

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
   
    private void initialize() {
        savedList.setItems(recentSearches); // Bind the ListView to the ObservableList
    }
    //Left Pane
    String humidityPercentString;
    String windDirectionString;
    String windSpeedString;
    String feelsLikeString;
    //Center Pane Top
    String cityNameString;
    //Center Pane Hourly Precipitation
    String precipitationString; //used for left pane as well
    String precipitationHourOneString;
    String precipitationHourTwoString;
    String precipitationHourThreeString;
    String precipitationHourFourString;
    String precipitationHourFiveString;
    
    //Center Pane Hourly Temp
    String currentTempString;
    String tempHourOneString;
    String tempHourTwoString;
    String tempHourThreeString;
    String tempHourFourString;
    String tempHourFiveString;
    //Right Pane +0 Days
    String highLowTodayString;
    String precipitationTodayString;
    String sunriseTodayString;
    String sunsetTodayString;
    //Right Pane +1 Day
    String highLow1String;
    String precipitation1String;
    String sunrise1String;
    String sunset1String;
    //Right Pane +2 Days
    String highLow2String;
    String precipitation2String;
    String sunrise2String;
    String sunset2String;
    //Right Pane +3 Days
    String highLow3String;
    String precipitation3String;
    String sunrise3String;
    String sunset3String;
    //Right Pane +4 Days
    String highLow4String;
    String precipitation4String;
    String sunrise4String;
    String sunset4String;
    //Right Pane +5Days
    String highLow5String;
    String precipitation5String;
    String sunrise5String;
    String sunset5String;
    //Right Pane +6 Days
    String highLow6String;
    String precipitation6String;
    String sunrise6String;
    String sunset6String;

    @FXML
    private void handleSearch(ActionEvent event) {
        weatherObject defaulttt = new weatherObject();
        cityNameString = cityInput.getText().trim();           

        
         //System.out.println("testing default constructor getLocation: \n"+defaulttt.getLocation(cityNameString).toString()+"\n");
        
        
         if (cityNameString.isEmpty() || !defaulttt.getLocation(cityNameString).toString().contains("elevation")) {
            System.out.println("MAIN: Please enter a valid city name/zip");
            return;
        }

        // Add location to recent searches if not already present
        if (!recentSearches.contains(cityNameString)) {

            recentSearches.add(cityNameString);
            initialize();
        }

        // Limit the list to the last 10 searches
        if (recentSearches.size() > 10) {

            recentSearches.remove(0);
        }

        weatherObject currentWeatherSearch = new weatherObject(cityNameString);
        /*
        System.out.println(currentWeatherSearch.isCity);
        if(currentWeatherSearch.equals(null)){
            System.out.println("Location not found\n");
            //return;
        }*/
        //System.out.println("testing printing object: "+currentWeatherSearch.toString());
        
        //System.out.println("testing pre-result json: "+currentWeatherSearch.coordinates.toString());
        /*
        if (!currentWeatherSearch.coordinates.containsKey("elevation")){
            System.out.println("MAIN: Please enter a valid city name/zip");
        }
        else{
            System.out.println("Main: found eleveatyion");
        }*/
        System.out.println("testing date parsing: "+currentWeatherSearch.parseDate(currentWeatherSearch.currentDateAndTime));
        System.out.println("testing day of week parsing: "+currentWeatherSearch.parseDayOfWeek(currentWeatherSearch.currentDateAndTime));
        System.out.println("testing current weather conditions parsing: "+currentWeatherSearch.parseWMO(currentWeatherSearch.currentWeatherCode)+"\n");
        System.out.println("testing city, state, country: "+currentWeatherSearch.coordCityName+", "+currentWeatherSearch.cityState+", "+currentWeatherSearch.cityCountry+"\n");
        System.out.println("testing current weather conditions for image double hashmap: "+currentWeatherSearch.parseWMOforImage(currentWeatherSearch.parseWMO(currentWeatherSearch.currentWeatherCode))+"\n");

        String imagePath = "/image/Sun.png"; //eventually put into if & else if for determining photo
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        weatherImage.setImage(image);
        
        // Update UI elements with weather data for the new location
            //Left Pane
        humidityPercentString= String.valueOf(currentWeatherSearch.currentRelativeHumidity)+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("relative_humidity_2m"));
        windDirectionString= String.valueOf(currentWeatherSearch.currentWindDirection)+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("wind_direction_10m"));
        windSpeedString= String.valueOf(currentWeatherSearch.currentWindSpeed)+" "+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("wind_speed_10m"));
        feelsLikeString= String.valueOf(currentWeatherSearch.currentApparentTemperature)+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("apparent_temperature"));
        //Center Pane Top
        cityNameString= String.valueOf(currentWeatherSearch.coordCityName)+", "+currentWeatherSearch.cityState;
        //Center Pane Hourly Precipitation
        //change this to WMO code
        precipitationString= String.valueOf(currentWeatherSearch.hourlyPrecipitationProbability.get(0))+String.valueOf(currentWeatherSearch.hourlyWeatherUnits.get("precipitation_probability")); //used for left pane as well
        precipitationHourOneString= String.valueOf(currentWeatherSearch.hourlyPrecipitationProbability.get(1))+String.valueOf(currentWeatherSearch.hourlyWeatherUnits.get("precipitation_probability"));
        precipitationHourTwoString= String.valueOf(currentWeatherSearch.hourlyPrecipitationProbability.get(2))+String.valueOf(currentWeatherSearch.hourlyWeatherUnits.get("precipitation_probability"));
        precipitationHourThreeString= String.valueOf(currentWeatherSearch.hourlyPrecipitationProbability.get(3))+String.valueOf(currentWeatherSearch.hourlyWeatherUnits.get("precipitation_probability"));
        precipitationHourFourString= String.valueOf(currentWeatherSearch.hourlyPrecipitationProbability.get(4))+String.valueOf(currentWeatherSearch.hourlyWeatherUnits.get("precipitation_probability"));
        precipitationHourFiveString= String.valueOf(currentWeatherSearch.hourlyPrecipitationProbability.get(5))+String.valueOf(currentWeatherSearch.hourlyWeatherUnits.get("precipitation_probability"));
        
        //Center Pane Hourly Temp
        currentTempString= String.valueOf(currentWeatherSearch.currentTemperature)+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("temperature_2m")); //used for center pane top as well
        tempHourOneString= String.valueOf(currentWeatherSearch.hourlyTemperature.get(0))+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("temperature_2m"));
        tempHourTwoString= String.valueOf(currentWeatherSearch.hourlyTemperature.get(1))+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("temperature_2m"));
        tempHourThreeString= String.valueOf(currentWeatherSearch.hourlyTemperature.get(2))+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("temperature_2m"));
        tempHourFourString= String.valueOf(currentWeatherSearch.hourlyTemperature.get(3))+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("temperature_2m"));
        tempHourFiveString= String.valueOf(currentWeatherSearch.hourlyTemperature.get(4))+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("temperature_2m"));
        //Right Pane +0 Days
        highLowTodayString= String.valueOf(currentWeatherSearch.dailyMaxTemp.get(0))+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("temperature_2m"))+"/"+String.valueOf(currentWeatherSearch.dailyMinTemp.get(0))+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("temperature_2m"));
        precipitationTodayString= String.valueOf(currentWeatherSearch.dailyPrecipitationProbability.get(0))+String.valueOf(currentWeatherSearch.dailyWeatherUnits.get("precipitation_probability_max"));
        sunriseTodayString= "Sunrise: "+currentWeatherSearch.parseTime(String.valueOf(currentWeatherSearch.dailySunrise.get(0)));
        sunsetTodayString= "Sunset: "+currentWeatherSearch.parseTime(String.valueOf(currentWeatherSearch.dailySunset.get(0)));
        //Right Pane +1 Day
        highLow1String= String.valueOf(currentWeatherSearch.dailyMaxTemp.get(1))+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("temperature_2m"))+"/"+String.valueOf(currentWeatherSearch.dailyMinTemp.get(1))+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("temperature_2m"));
        precipitation1String= String.valueOf(currentWeatherSearch.dailyPrecipitationProbability.get(1))+String.valueOf(currentWeatherSearch.dailyWeatherUnits.get("precipitation_probability_max"));
        sunrise1String= "Sunrise: "+currentWeatherSearch.parseTime(String.valueOf(currentWeatherSearch.dailySunrise.get(1)));
        sunset1String= "Sunset: "+currentWeatherSearch.parseTime(String.valueOf(currentWeatherSearch.dailySunset.get(1)));
        //Right Pane +2 Days
        highLow2String= String.valueOf(currentWeatherSearch.dailyMaxTemp.get(2))+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("temperature_2m"))+"/"+String.valueOf(currentWeatherSearch.dailyMinTemp.get(2))+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("temperature_2m"));
        precipitation2String= String.valueOf(currentWeatherSearch.dailyPrecipitationProbability.get(2))+String.valueOf(currentWeatherSearch.dailyWeatherUnits.get("precipitation_probability_max"));
        sunrise2String= "Sunrise: "+currentWeatherSearch.parseTime(String.valueOf(currentWeatherSearch.dailySunrise.get(2)));
        sunset2String= "Sunset: "+currentWeatherSearch.parseTime(String.valueOf(currentWeatherSearch.dailySunset.get(2)));
        //Right Pane +3 Days
        highLow3String= String.valueOf(currentWeatherSearch.dailyMaxTemp.get(3))+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("temperature_2m"))+"/"+String.valueOf(currentWeatherSearch.dailyMinTemp.get(3))+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("temperature_2m"));
        precipitation3String= String.valueOf(currentWeatherSearch.dailyPrecipitationProbability.get(3))+String.valueOf(currentWeatherSearch.dailyWeatherUnits.get("precipitation_probability_max"));
        sunrise3String= "Sunrise: "+currentWeatherSearch.parseTime(String.valueOf(currentWeatherSearch.dailySunrise.get(3)));
        sunset3String= "Sunset: "+currentWeatherSearch.parseTime(String.valueOf(currentWeatherSearch.dailySunset.get(3)));
        //Right Pane +4 Days
        highLow4String= String.valueOf(currentWeatherSearch.dailyMaxTemp.get(4))+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("temperature_2m"))+"/"+String.valueOf(currentWeatherSearch.dailyMinTemp.get(4))+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("temperature_2m"));
        precipitation4String= String.valueOf(currentWeatherSearch.dailyPrecipitationProbability.get(4))+String.valueOf(currentWeatherSearch.dailyWeatherUnits.get("precipitation_probability_max"));
        sunrise4String= "Sunrise: "+currentWeatherSearch.parseTime(String.valueOf(currentWeatherSearch.dailySunrise.get(4)));
        sunset4String= "Sunset: "+currentWeatherSearch.parseTime(String.valueOf(currentWeatherSearch.dailySunset.get(4)));
        //Right Pane +5Days
        highLow5String= String.valueOf(currentWeatherSearch.dailyMaxTemp.get(5))+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("temperature_2m"))+"/"+String.valueOf(currentWeatherSearch.dailyMinTemp.get(5))+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("temperature_2m"));
        precipitation5String= String.valueOf(currentWeatherSearch.dailyPrecipitationProbability.get(5))+String.valueOf(currentWeatherSearch.dailyWeatherUnits.get("precipitation_probability_max"));
        sunrise5String= "Sunrise: "+currentWeatherSearch.parseTime(String.valueOf(currentWeatherSearch.dailySunrise.get(5)));
        sunset5String= "Sunset: "+currentWeatherSearch.parseTime(String.valueOf(currentWeatherSearch.dailySunset.get(5)));
        //Right Pane +6 Days
        highLow6String= String.valueOf(currentWeatherSearch.dailyMaxTemp.get(6))+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("temperature_2m"))+"/"+String.valueOf(currentWeatherSearch.dailyMinTemp.get(6))+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("temperature_2m"));
        precipitation6String= String.valueOf(currentWeatherSearch.dailyPrecipitationProbability.get(6))+String.valueOf(currentWeatherSearch.dailyWeatherUnits.get("precipitation_probability_max"));
        sunrise6String= "Sunrise: "+currentWeatherSearch.parseTime(String.valueOf(currentWeatherSearch.dailySunrise.get(6)));
        sunset6String= "Sunset: "+currentWeatherSearch.parseTime(String.valueOf(currentWeatherSearch.dailySunset.get(6)));
        updateWeatherInfo(cityNameString);
    }

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

}
