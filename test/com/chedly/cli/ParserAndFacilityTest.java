package com.chedly.cli;

import com.chedly.model.Facility;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class ParserAndFacilityTest {
    private Parser parser = new Parser(new Facility());
    private final String[] commands = {
            "add <machine_id> <number_of_units>",
            "average <machine_id>",
            "create <machine_name> <machine_id>",
            "temperature <machine_id>",
            "temperature <machine_id> <temperature>",
            "total <machine_id>"
    };

    @Test
    public void allCommandsShouldBeSavedOnInitialize() {
        assertArrayEquals(commands, parser.getRegisteredCommands());
    }

    @Test
    public void parserShouldThrowExceptionWithUnknownCommandMessageWhenInputIsInvalid(){
        Throwable exception1 = assertThrows(
                IllegalArgumentException.class, () -> {
                    parser.parse(" ");
                }
        );
        Throwable exception2 = assertThrows(
                IllegalArgumentException.class, () -> {
                    parser.parse("test");
                }
        );
        Throwable exception3 = assertThrows(
                IllegalArgumentException.class, () -> {
                    parser.parse("test test");
                }
        );
        Throwable exception4 = assertThrows(
                IllegalArgumentException.class, () -> {
                    parser.parse("12");
                }
        );
        assertEquals("Unknown Command!", exception1.getMessage());
        assertEquals("Unknown Command!", exception2.getMessage());
        assertEquals("Unknown Command!", exception3.getMessage());
        assertEquals("Unknown Command!", exception4.getMessage());
    }

    @Test
    public void machineShouldBeAddedWithSuccessMessage(){
        parser.parse("create MACHINE1 IDX123456");
        String output = parser.getOutput();
        assertEquals("Machine successfully added!",output);
    }

    @Test
    public void allCommandsShouldReturnNoMachineWithSuchIdForUnexistantMachines(){
        /* avg */
        parser.parse("average IDX123456");
        String output1 = parser.getOutput();
        assertEquals("No machine with ID IDX123456",output1);
        /* add units */
        parser.parse("add IDX123456 20");
        String output2 = parser.getOutput();
        assertEquals("No machine with ID IDX123456",output2);
        /* Temp set */
        parser.parse("temperature IDX123456 20");
        String output3 = parser.getOutput();
        assertEquals("No machine with ID IDX123456",output3);
        /* Temp get*/
        parser.parse("temperature IDX123456");
        String output4 = parser.getOutput();
        assertEquals("No machine with ID IDX123456",output4);
        /* Total */
        parser.parse("total IDX123456");
        String output5 = parser.getOutput();
        assertEquals("No machine with ID IDX123456",output5);
    }

    @Test
    public void testTotalOutputOfMachine(){
        parser.parse("create MACHINE1 IDX123456");
        parser.parse("add IDX123456 12");
        parser.parse("add IDX123456 40");
        parser.parse("total IDX123456");
        assertEquals("52",parser.getOutput());
    }

    @Test
    public void testAverageOutputOfMachine(){
        parser.parse("create MACHINE1 IDX123456");
        parser.parse("add IDX123456 12");
        parser.parse("add IDX123456 40");
        parser.parse("average IDX123456");
        assertEquals("26.0",parser.getOutput());
    }

    @Test
    public void testSetAndGetTemperatureOfMachine(){
        parser.parse("create MACHINE1 IDX123456");
        parser.parse("temperature IDX123456 120");
        parser.parse("temperature IDX123456");
        assertEquals("120.0",parser.getOutput());
    }

    @Test
    public void addUnitsShouldOnlyAcceptIntegers(){
        parser.parse("create MACHINE1 IDX123456");
        parser.parse("add IDX123456 sev");
        assertEquals("Please specify a valid number of units (Integer)",parser.getOutput());
        parser.parse("add IDX123456 1.2");
        assertEquals("Please specify a valid number of units (Integer)",parser.getOutput());
        parser.parse("add IDX123456 1sqd");
        assertEquals("Please specify a valid number of units (Integer)",parser.getOutput());
        parser.parse("add IDX123456 20");
        assertEquals("Number of units successfully updated!",parser.getOutput());
    }

    @Test
    public void setTemperaturesShouldOnlyAcceptFloats(){
        parser.parse("create MACHINE1 IDX123456");
        parser.parse("temperature IDX123456 sev");
        assertEquals("Please specify a valid temperature (Float)",parser.getOutput());
        parser.parse("temperature IDX123456 z5z");
        assertEquals("Please specify a valid temperature (Float)",parser.getOutput());
        parser.parse("temperature IDX123456 20");
        assertEquals("Temperature successfully set!",parser.getOutput());
        parser.parse("temperature IDX123456 .2");
        assertEquals("Temperature successfully set!",parser.getOutput());
        parser.parse("temperature IDX123456 2.2");
        assertEquals("Temperature successfully set!",parser.getOutput());
    }

}