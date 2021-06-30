package com.chedly.model;

import java.util.HashMap;

public class Facility {
    HashMap<String, Machine> machines;

    public Facility(){
        machines = new HashMap<String,Machine>();
    }

    public String createMachine(String name, String id){
        if (machines.containsKey(id)){
            return "There is already a machine with this ID";
        }
        Machine machine = new Machine(name,id);
        machines.put(id, machine);
        return "Machine successfully added!";
    }

    public String addUnitsToMachine(String machineId, Integer numberOfUnits) {
        if (machines.containsKey(machineId)){
            Machine machine = machines.get(machineId);
            machine.addUnits(numberOfUnits);
             return "Number of units successfully updated!";
        }
        return "No machine with ID "+ machineId;
    }

    public String setTemperatureForMachine(String machineId,Float temperature) {
        if (machines.containsKey(machineId)){
            Machine machine = machines.get(machineId);
            machine.setTemperature(temperature);
            return "Temperature successfully set!";
        }
        return "No machine with ID "+ machineId;
    }

    public String getTemperatureForMachine(String machineId) {
        if (machines.containsKey(machineId)){
            Machine machine = machines.get(machineId);
            Float temperature = machine.getTemperature();
            return temperature.toString();
        }
        return "No machine with ID "+ machineId;
    }

    public String getTotalForMachine(String machineId) {
        if (machines.containsKey(machineId)){
            Machine machine = machines.get(machineId);
            Integer numberOfUnits = machine.getTotalUnits();
            return numberOfUnits.toString();
        }
        return "No machine with ID "+ machineId;
    }

    public String getAverageForMachine(String machineId) {
        if (machines.containsKey(machineId)){
            Machine machine = machines.get(machineId);
            float average = machine.getTotalUnits()/machine.getAdditions();
            return String.valueOf(average);
        }
        return "No machine with ID "+ machineId;
    }

}
