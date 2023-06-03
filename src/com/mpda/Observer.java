package com.mpda;

public interface Observer {

    void update(float temperature, float humidity, float pressure);
    void display();

}
