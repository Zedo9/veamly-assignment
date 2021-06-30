package com.chedly;

import com.chedly.cli.Parser;
import com.chedly.model.Facility;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static String output;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Facility facility = new Facility();
        Parser parser = new Parser(facility);
        System.out.println("**** Available commands ****");
        Arrays.stream(parser.getRegisteredCommands()).forEach(System.out::println);
        System.out.println("********");
        while (true){
            System.out.print("> ");
            String input = sc.nextLine();
            if (input.length() == 0){
                break;
            }
            try{
                parser.parse(input);
                output = parser.getOutput();
                System.out.println(output);
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
