package com.chedly.model;

public class Machine {
    private String name;
    private String id;
    private Float temperature;
    private Integer additions;
    private Integer totalUnits;

    public Machine(String name, String id){
        this.name = name;
        this.id = id;
        this.additions = 0;
        this.totalUnits = 0;
    }

    public void addUnits(Integer numberOfUnits) {
        this.additions ++;
        this.totalUnits += numberOfUnits;
    }

    public Float getTemperature() {
        return temperature;
    }

    public Integer getAdditions() {
        return additions;
    }

    public Integer getTotalUnits() {
        return totalUnits;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "Machine{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", temperature=" + temperature +
                ", additions=" + additions +
                ", totalUnits=" + totalUnits +
                '}';
    }
}
