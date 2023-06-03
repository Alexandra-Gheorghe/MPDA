package com.mpda;

public class CurrentConditionsDisplay implements Observer{

    private float temperature;
    private float humidity;
    private float pressure;

    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    public void display() {
        System.out.println("Current conditions: " + temperature + "C degrees, " + humidity + "% humidity, " + pressure + " pressure");
    }

    public String getDisplay() {
        return "Current conditions: " + temperature + "C degrees, " + humidity + "% humidity, " + pressure + " pressure";
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }

}
