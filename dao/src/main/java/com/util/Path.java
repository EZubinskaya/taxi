package com.util;

/**
 * Created by kzub on 9/30/2015.
 */
public class Path {
    private String distance;
    private Integer duration;

    public Path() {
    }

    public Path(String distance, Integer duration) {
        this.distance = distance;
        this.duration = duration;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
