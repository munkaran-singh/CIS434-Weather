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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import java.util.ArrayList;
import java.util.List;

public class WeatherApplicationController {

    @FXML
    private Text cityName;

    @FXML
    private Text currentCondition;

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
    private Button saveButton;

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
    private Text dayFive;

    @FXML
    private Text dayFour;

    @FXML
    private Text dayOne;

    @FXML
    private Text daySix;

    @FXML
    private Text dayThree;

    @FXML
    private Text dayTwo;
    
    @FXML
    private Text timeCurrent;

    @FXML
    private Text timeFive;

    @FXML
    private Text timeFour;

    @FXML
    private Text timeOne;

    @FXML
    private Text timeThree;

    @FXML
    private Text timeTwo;
    
    @FXML
    private ListView<String> savedList;
    private ObservableList<String> recentSearches = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        savedList.setItems(recentSearches);

        savedList.setOnMouseClicked(event -> {
            if(event.getClickCount()==2){
                String selectedCity = savedList.getSelectionModel().getSelectedItem();
                TheMainSearchFunction(selectedCity);
            }

        });
    }
    
    //Left Pane
    String humidityPercentString;
    String windDirectionString;
    String windSpeedString;
    String feelsLikeString;
    //Center Pane Top
    String cityNameInput;
    String cityNameString;
    String currentWeatherConditionsforImage;
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
    //images
    String clearDay = "/image/clearDay.png";
    String clearNight = "/image/clearNight.png";
    String cloudDay = "/image/cloudDay.png";
    String cloudNight = "/image/cloudNight.png";
    String rainDay = "/image/rainDay.png";
    String rainNight = "/image/rainNight.png";
    String snowDay = "/image/snowdaypng";
    String snowNight = "/image/snowNight.png";
    
    //Center Pane
    String currentConditionString;
    //Center Pane Times
    String timeCurrentString;
    String timeOneString;    
    String timeTwoString;    
    String timeThreeString;    
    String timeFourString;
    String timeFiveString;
    //Right Pane Days
    String dayOneString;
    String dayTwoString;
    String dayThreeString;
    String dayFourString;
    String dayFiveString;
    String daySixString;
    
    
    @FXML
    private void handleSave(ActionEvent event) {
        if (cityNameInput == null || cityNameInput.isEmpty()) {
            System.out.println("No search result to save.");
            return;
        }
        if (!recentSearches.contains(cityNameInput)) {
            recentSearches.add(cityNameInput);
            initialize();
        }


        if (recentSearches.size() > 10) {
            recentSearches.remove(0);
        }

        System.out.println("Saved " + cityNameInput + " to recent searches.");
    }
    
    @FXML
    private void handleSearch(ActionEvent event) {
        TheMainSearchFunction(cityInput.getText().trim());
    }



    private void TheMainSearchFunction(String input){
        weatherObject defaulttt = new weatherObject();
        cityNameInput = input;           

        
         //System.out.println("testing default constructor getLocation: \n"+defaulttt.getLocation(cityNameInput).toString()+"\n");
        
        
         if (cityNameInput.isEmpty() || !defaulttt.getLocation(cityNameInput).toString().contains("elevation")) {
            showAlert("Invalid Entry", "Please enter a valid city name or ZIP code.");
            System.out.println("MAIN: Please enter a valid city name/zip");
            return;
        }

        weatherObject currentWeatherSearch = new weatherObject(cityNameInput);
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
        //System.out.println("testing day of week parsing: "+currentWeatherSearch.parseDayOfWeek(currentWeatherSearch.currentDateAndTime));
        System.out.println("testing current weather conditions parsing: "+currentWeatherSearch.parseWMO(currentWeatherSearch.currentWeatherCode)+"\n");
        System.out.println("testing city, state, country: "+currentWeatherSearch.coordCityName+", "+currentWeatherSearch.cityState+", "+currentWeatherSearch.cityCountry+"\n");
        System.out.println("testing current weather conditions for image double hashmap: "+currentWeatherSearch.parseWMOforImage(currentWeatherSearch.parseWMO(currentWeatherSearch.currentWeatherCode))+"\n");



      
        
        // Update UI elements with weather data for the new location
            //Left Pane
        humidityPercentString= String.valueOf(currentWeatherSearch.currentRelativeHumidity)+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("relative_humidity_2m"));
        windDirectionString= String.valueOf(currentWeatherSearch.currentWindDirection)+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("wind_direction_10m"));
        windSpeedString= String.valueOf(currentWeatherSearch.currentWindSpeed)+" "+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("wind_speed_10m"));
        feelsLikeString= String.valueOf(currentWeatherSearch.currentApparentTemperature)+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("apparent_temperature"));
        //Center Pane Top
        cityNameString= String.valueOf(currentWeatherSearch.coordCityName)+", "+currentWeatherSearch.cityState;
        currentConditionString = currentWeatherSearch.parseWMO(currentWeatherSearch.currentWeatherCode);
        currentWeatherConditionsforImage = currentWeatherSearch.parseWMOforImage(currentWeatherSearch.parseWMO(currentWeatherSearch.currentWeatherCode));
        //Center Pane Hourly Precipitation
        precipitationString= String.valueOf(currentWeatherSearch.hourlyPrecipitationProbability.get(0))+String.valueOf(currentWeatherSearch.hourlyWeatherUnits.get("precipitation_probability")); //used for left pane as well
        precipitationHourOneString= String.valueOf(currentWeatherSearch.hourlyPrecipitationProbability.get(1))+String.valueOf(currentWeatherSearch.hourlyWeatherUnits.get("precipitation_probability"));
        precipitationHourTwoString= String.valueOf(currentWeatherSearch.hourlyPrecipitationProbability.get(2))+String.valueOf(currentWeatherSearch.hourlyWeatherUnits.get("precipitation_probability"));
        precipitationHourThreeString= String.valueOf(currentWeatherSearch.hourlyPrecipitationProbability.get(3))+String.valueOf(currentWeatherSearch.hourlyWeatherUnits.get("precipitation_probability"));
        precipitationHourFourString= String.valueOf(currentWeatherSearch.hourlyPrecipitationProbability.get(4))+String.valueOf(currentWeatherSearch.hourlyWeatherUnits.get("precipitation_probability"));
        precipitationHourFiveString= String.valueOf(currentWeatherSearch.hourlyPrecipitationProbability.get(5))+String.valueOf(currentWeatherSearch.hourlyWeatherUnits.get("precipitation_probability"));
        
        //Center Pane Hourly Temp
        timeCurrentString = "Now";
        timeOneString = currentWeatherSearch.parseTimeHourOnly(String.valueOf(currentWeatherSearch.hourlyTime.get(1)));
        timeTwoString = currentWeatherSearch.parseTimeHourOnly(String.valueOf(currentWeatherSearch.hourlyTime.get(2)));   
        timeThreeString = currentWeatherSearch.parseTimeHourOnly(String.valueOf(currentWeatherSearch.hourlyTime.get(3)));
        timeFourString = currentWeatherSearch.parseTimeHourOnly(String.valueOf(currentWeatherSearch.hourlyTime.get(4)));
        timeFiveString = currentWeatherSearch.parseTimeHourOnly(String.valueOf(currentWeatherSearch.hourlyTime.get(5)));
        currentTempString= String.valueOf(currentWeatherSearch.currentTemperature)+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("temperature_2m")); //used for center pane top as well
        tempHourOneString= String.valueOf(currentWeatherSearch.hourlyTemperature.get(0))+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("temperature_2m"));
        tempHourTwoString= String.valueOf(currentWeatherSearch.hourlyTemperature.get(1))+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("temperature_2m"));
        tempHourThreeString= String.valueOf(currentWeatherSearch.hourlyTemperature.get(2))+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("temperature_2m"));
        tempHourFourString= String.valueOf(currentWeatherSearch.hourlyTemperature.get(3))+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("temperature_2m"));
        tempHourFiveString= String.valueOf(currentWeatherSearch.hourlyTemperature.get(4))+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("temperature_2m"));
        //Right Pane +0 Days
        highLowTodayString= String.valueOf(currentWeatherSearch.dailyMaxTemp.get(7))+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("temperature_2m"))+"/"+String.valueOf(currentWeatherSearch.dailyMinTemp.get(7))+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("temperature_2m"));
        precipitationTodayString= String.valueOf(currentWeatherSearch.dailyPrecipitationProbability.get(7))+String.valueOf(currentWeatherSearch.dailyWeatherUnits.get("precipitation_probability_max"));
        sunriseTodayString= "Sunrise: "+currentWeatherSearch.parseTime(String.valueOf(currentWeatherSearch.dailySunrise.get(7)));
        sunsetTodayString= "Sunset: "+currentWeatherSearch.parseTime(String.valueOf(currentWeatherSearch.dailySunset.get(7)));
        //Right Pane +1 Day
        dayOneString = "Tomorrow";
        highLow1String= String.valueOf(currentWeatherSearch.dailyMaxTemp.get(8))+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("temperature_2m"))+"/"+String.valueOf(currentWeatherSearch.dailyMinTemp.get(8))+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("temperature_2m"));
        precipitation1String= String.valueOf(currentWeatherSearch.dailyPrecipitationProbability.get(8))+String.valueOf(currentWeatherSearch.dailyWeatherUnits.get("precipitation_probability_max"));
        sunrise1String= "Sunrise: "+currentWeatherSearch.parseTime(String.valueOf(currentWeatherSearch.dailySunrise.get(8)));
        sunset1String= "Sunset: "+currentWeatherSearch.parseTime(String.valueOf(currentWeatherSearch.dailySunset.get(8)));
        //Right Pane +2 Days
        
        dayTwoString = String.valueOf(currentWeatherSearch.parseDayOfWeek(String.valueOf(currentWeatherSearch.dailyDate.get(9))));
        highLow2String= String.valueOf(currentWeatherSearch.dailyMaxTemp.get(9))+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("temperature_2m"))+"/"+String.valueOf(currentWeatherSearch.dailyMinTemp.get(9))+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("temperature_2m"));
        precipitation2String= String.valueOf(currentWeatherSearch.dailyPrecipitationProbability.get(9))+String.valueOf(currentWeatherSearch.dailyWeatherUnits.get("precipitation_probability_max"));
        sunrise2String= "Sunrise: "+currentWeatherSearch.parseTime(String.valueOf(currentWeatherSearch.dailySunrise.get(9)));
        sunset2String= "Sunset: "+currentWeatherSearch.parseTime(String.valueOf(currentWeatherSearch.dailySunset.get(9)));
        //Right Pane +3 Days
        
        dayThreeString = String.valueOf(currentWeatherSearch.parseDayOfWeek(String.valueOf(currentWeatherSearch.dailyDate.get(10))));
        highLow3String= String.valueOf(currentWeatherSearch.dailyMaxTemp.get(10))+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("temperature_2m"))+"/"+String.valueOf(currentWeatherSearch.dailyMinTemp.get(10))+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("temperature_2m"));
        precipitation3String= String.valueOf(currentWeatherSearch.dailyPrecipitationProbability.get(10))+String.valueOf(currentWeatherSearch.dailyWeatherUnits.get("precipitation_probability_max"));
        sunrise3String= "Sunrise: "+currentWeatherSearch.parseTime(String.valueOf(currentWeatherSearch.dailySunrise.get(10)));
        sunset3String= "Sunset: "+currentWeatherSearch.parseTime(String.valueOf(currentWeatherSearch.dailySunset.get(10)));
        //Right Pane +4 Days
        

        dayFourString = String.valueOf(currentWeatherSearch.parseDayOfWeek(String.valueOf(currentWeatherSearch.dailyDate.get(11))));
        highLow4String= String.valueOf(currentWeatherSearch.dailyMaxTemp.get(11))+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("temperature_2m"))+"/"+String.valueOf(currentWeatherSearch.dailyMinTemp.get(11))+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("temperature_2m"));
        precipitation4String= String.valueOf(currentWeatherSearch.dailyPrecipitationProbability.get(11))+String.valueOf(currentWeatherSearch.dailyWeatherUnits.get("precipitation_probability_max"));
        sunrise4String= "Sunrise: "+currentWeatherSearch.parseTime(String.valueOf(currentWeatherSearch.dailySunrise.get(11)));
        sunset4String= "Sunset: "+currentWeatherSearch.parseTime(String.valueOf(currentWeatherSearch.dailySunset.get(11)));
        //Right Pane +5Days
        
        dayFiveString = String.valueOf(currentWeatherSearch.parseDayOfWeek(String.valueOf(currentWeatherSearch.dailyDate.get(12))));
        highLow5String= String.valueOf(currentWeatherSearch.dailyMaxTemp.get(12))+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("temperature_2m"))+"/"+String.valueOf(currentWeatherSearch.dailyMinTemp.get(12))+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("temperature_2m"));
        precipitation5String= String.valueOf(currentWeatherSearch.dailyPrecipitationProbability.get(12))+String.valueOf(currentWeatherSearch.dailyWeatherUnits.get("precipitation_probability_max"));
        sunrise5String= "Sunrise: "+currentWeatherSearch.parseTime(String.valueOf(currentWeatherSearch.dailySunrise.get(12)));
        sunset5String= "Sunset: "+currentWeatherSearch.parseTime(String.valueOf(currentWeatherSearch.dailySunset.get(12)));
        //Right Pane +6 Days
        
        daySixString = String.valueOf(currentWeatherSearch.parseDayOfWeek(String.valueOf(currentWeatherSearch.dailyDate.get(13))));
        highLow6String= String.valueOf(currentWeatherSearch.dailyMaxTemp.get(13))+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("temperature_2m"))+"/"+String.valueOf(currentWeatherSearch.dailyMinTemp.get(13))+String.valueOf(currentWeatherSearch.currentWeatherUnits.get("temperature_2m"));
        precipitation6String= String.valueOf(currentWeatherSearch.dailyPrecipitationProbability.get(13))+String.valueOf(currentWeatherSearch.dailyWeatherUnits.get("precipitation_probability_max"));
        sunrise6String= "Sunrise: "+currentWeatherSearch.parseTime(String.valueOf(currentWeatherSearch.dailySunrise.get(13)));
        sunset6String= "Sunset: "+currentWeatherSearch.parseTime(String.valueOf(currentWeatherSearch.dailySunset.get(13)));

        System.out.println("setting background image");
        if(currentWeatherSearch.isDayOrNightBinary == 0){
            System.out.println("it is night");
            switch (currentWeatherConditionsforImage) {
                case "Sunny":
                    setImage(clearNight);
                    System.out.println("it is clear\nsetting clear night image");
                    break;
                case "Cloudy":
                    setImage(cloudNight);
                    System.out.println("it is cloudy\nsetting cloudy night image");
                    break;
                case "Raining":
                    setImage(rainNight);
                    System.out.println("it is raining\nsetting rainy night image");
                    break;
                case "Snowing":
                    setImage(snowNight);
                    System.out.println("it is snowing\nsetting snowy night image");
                    break;
            }
        }
        else{
            System.out.println("it is day");
            switch (currentWeatherConditionsforImage) {
                case "Sunny":
                    setImage(clearDay);
                    System.out.println("it is clear\nsetting clear day image");
                    break;
                case "Cloudy":
                    setImage(cloudDay);
                    System.out.println("it is cloudy\nsetting cloudy day image");
                    break;
                case "Raining":
                    setImage(rainDay);
                    System.out.println("it is raining\nsetting rainy day image");
                    break;
                case "Snowing":
                    setImage(snowDay);
                    System.out.println("it is snowing\nsetting snowy day image");
                    break;
            }
        }

        updateWeatherInfo(cityNameInput);
    }

    private void setImage(String imagePath){
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        weatherImage.setImage(image);
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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
        //Center Pane Current Condition
        updateCurrentCondition(currentConditionString);
        //Center Pane Times
        updateTimeCurrent(timeCurrentString);
        updateTimeOne(timeOneString);
        updateTimeTwo(timeTwoString);
        updateTimeThree(timeThreeString);
        updateTimeFour(timeFourString);
        updateTimeFive(timeFiveString);
        //Right Pane Days
        updateDayOne(dayOneString);
        updateDayTwo(dayTwoString);
        updateDayThree(dayThreeString);
        updateDayFour(dayFourString);
        updateDayFive(dayFiveString);
        updateDaySix(daySixString);
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
    //Center Pane Current Condition
    private void updateCurrentCondition(String text) {
        currentCondition.setText(text);}
    //Center Pane Times
    private void updateTimeCurrent(String text) {
        timeCurrent.setText(text);}
    private void updateTimeOne(String text) {
        timeOne.setText(text);}
    private void updateTimeTwo(String text) {
        timeTwo.setText(text);}
    private void updateTimeThree(String text) {
        timeThree.setText(text);}
    private void updateTimeFour(String text) {
        timeFour.setText(text);}
    private void updateTimeFive(String text) {
        timeFive.setText(text);}
    //Right Pane Days
    private void updateDayOne(String text) {
        dayOne.setText(text);}
    private void updateDayTwo(String text) {
        dayTwo.setText(text);}
    private void updateDayThree(String text) {
        dayThree.setText(text);}
    private void updateDayFour(String text) {
        dayFour.setText(text);}
    private void updateDayFive(String text) {
        dayFive.setText(text);}
    private void updateDaySix(String text) {
        daySix.setText(text);}

}
