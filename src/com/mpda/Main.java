package com.mpda;

public class Main {

    public static void main(String[] args) {

        WeatherData weatherData = WeatherData.getInstance();

        CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay();
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay();

        weatherData.registerObserver(currentConditionsDisplay);
        weatherData.registerObserver(statisticsDisplay);

        // Simulate measurements being updated
        weatherData.setMeasurements(23, 65, 30.4f);
        weatherData.setMeasurements(30, 70, 29.2f);
        weatherData.setMeasurements(16, 90, 29.2f);
    }
}
