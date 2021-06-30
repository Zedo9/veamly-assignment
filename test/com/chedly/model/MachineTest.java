package com.chedly.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MachineTest {

    @Test
    public void totalUnitsEqualsZeroWhenCreated(){
        Machine machine = new Machine("test","test");
        Integer units = machine.getTotalUnits();
        assertEquals(0,units);
    }

    @Test
    public void totalAdditionsEqualsZeroWhenCreated(){
        Machine machine = new Machine("test","test");
        Integer additions = machine.getAdditions();
        assertEquals(0,additions);
    }

    @Test
    public void totalUnitsAndAdditionsShouldUpdateAfterAddingUnit(){
        Machine machine = new Machine("test","test");
        machine.addUnits(20);
        assertEquals(20,machine.getTotalUnits());
        assertEquals(1,machine.getAdditions());
    }

    @Test
    public void temperatureShouldBeSet(){
        Machine machine = new Machine("test","test");
        machine.setTemperature(Float.valueOf(20));
        assertEquals(20,machine.getTemperature());
    }
}