package com.mpda;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WeatherDataTest {

    private WeatherData weatherData;
    private CurrentConditionsDisplay currentConditionsDisplay;
    private StatisticsDisplay statisticsDisplay;

    @BeforeEach
    void setUp() {
        weatherData = new WeatherData();
        currentConditionsDisplay = new CurrentConditionsDisplay();
        statisticsDisplay = new StatisticsDisplay();
    }

    @Test
    void testRegisterObserver() {
        // Register observer
        weatherData.registerObserver(currentConditionsDisplay);
        assertEquals(1, weatherData.getObserversCount());
    }

    @Test
    void testRemoveObserver() {
        // Register observer
        weatherData.registerObserver(currentConditionsDisplay);
        weatherData.registerObserver(statisticsDisplay);
        assertEquals(2, weatherData.getObserversCount());

        // Remove observer
        weatherData.removeObserver(currentConditionsDisplay);
        assertEquals(1, weatherData.getObserversCount());
    }

    @Test
    void testCurrentConditionsDisplay() {
        weatherData.registerObserver(currentConditionsDisplay);
        weatherData.setMeasurements(23, 65, 30.4f);
        assertEquals("Current conditions: 23.0C degrees, 65.0% humidity, 30.4 pressure", currentConditionsDisplay.getDisplay());
    }

    @Test
    void testStatisticsDisplay() {
        weatherData.registerObserver(statisticsDisplay);
        weatherData.setMeasurements(23, 65, 30.4f);
        assertEquals("Temperature average: 23.0", statisticsDisplay.getDisplay());
        weatherData.setMeasurements(30, 70, 29.2f);
        assertEquals("Temperature average: 26.5", statisticsDisplay.getDisplay());
        weatherData.setMeasurements(16, 90, 29.2f);
        assertEquals("Temperature average: 23.0", statisticsDisplay.getDisplay());
    }

    @Test
    void testSetMeasurements() {
        weatherData.setMeasurements(80, 65, 30.4f);
        assertEquals(80, weatherData.getTemperature());
        assertEquals(65, weatherData.getHumidity());
        assertEquals(30.4f, weatherData.getPressure());

        weatherData.setMeasurements(75, 60, 29.8f);
        assertEquals(75, weatherData.getTemperature());
        assertEquals(60, weatherData.getHumidity());
        assertEquals(29.8f, weatherData.getPressure());
    }

    @Test
    void testStatisticsDisplayFailed() {
        weatherData.registerObserver(statisticsDisplay);

        // Set incorrect measurements (expected to fail)
        weatherData.setMeasurements(23, 65, 30.4f);
        assertEquals("Temperature average: 23.0", statisticsDisplay.getDisplay());
        weatherData.setMeasurements(30, 70, 29.2f);
        assertEquals("Temperature average: 25.0", statisticsDisplay.getDisplay());
        weatherData.setMeasurements(16, 90, 29.2f);
        assertEquals("Temperature average: 41.0", statisticsDisplay.getDisplay());
    }

    @Test
    void testCurrentConditionsDisplayFailed() {
        weatherData.registerObserver(currentConditionsDisplay);

        // Set incorrect measurements (expected to fail)
        weatherData.setMeasurements(23, 60, 40.0f);
        assertEquals("Current conditions: 16.0C degrees, 70.0% humidity, 35.0 pressure", currentConditionsDisplay.getDisplay());
    }

    @Test
    void testRemoveObserverFailed() {
        // Register observer
        weatherData.registerObserver(currentConditionsDisplay);
        weatherData.registerObserver(statisticsDisplay);
        assertEquals(2, weatherData.getObserversCount());

        // Remove observer
        weatherData.removeObserver(currentConditionsDisplay);
        assertEquals(1, weatherData.getObserversCount());

        // Remove observer again (expected to fail)
        weatherData.removeObserver(currentConditionsDisplay);
        assertEquals(0, weatherData.getObserversCount());
    }
}
