package com.misern.keystroke.model;

public class Distance {
    private Long distance;
    private String clazz;

    public Distance(Long distance, String clazz) {
        this.distance = distance;
        this.clazz = clazz;
    }
    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
}
