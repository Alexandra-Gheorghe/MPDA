package com.mpda;

import java.util.ArrayList;
import java.util.List;

public class StatisticsDisplay implements Observer{

    private List<Float> temperatureReadings;

    public StatisticsDisplay() {
        temperatureReadings = new ArrayList<>();
    }

    public void update(float temperature, float humidity, float pressure) {
        temperatureReadings.add(temperature);

    }

    private float calculateAverage() {
        float sum = 0;
        for (float reading : temperatureReadings) {
            sum += reading;
        }
        return sum / temperatureReadings.size();
    }

    public void display() {
        System.out.println("Temperature average: " + calculateAverage());
    }

    public String getDisplay() {
        return "Temperature average: " + calculateAverage();
    }

    public List<Float> getTemperatureReadings() {
        return temperatureReadings;
    }

}
