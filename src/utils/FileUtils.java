package utils;

import models.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileUtils {
    private static File inputFile = new File("../resources/input.txt");
    private static File outputFile = new File("../resources/output.txt");
    private static File inventoryFile = new File("../resources/inventory.txt");
    private static File staffFile = new File("../resources/staff.txt");
    private static File staffAvailabilityFile = new File("../resources/staff_availability_IN.txt");
    private static File shiftSchedulesFile = new File("../resources/shift_schedules_IN.txt");
    private static File storeScheduleFile = new File("../resources/store_schedule_OUT.txt");

    public static List<Item> readInventoryFromFile() throws IOException {
        Scanner s = new Scanner(inventoryFile);
        List<Item> list = new ArrayList<Item>();
        while (s.hasNext()){
            String itemList = s.next();
            String[] arrOfStr = itemList.split(" ");
            String name = arrOfStr[0];
            Double price = Double.parseDouble(arrOfStr[1]);
            int quantity = Integer.parseInt(arrOfStr[2]);
            int aisleNum = Integer.parseInt(arrOfStr[3]);
            list.add(new Item(name,price,quantity, aisleNum));
        }
        s.close();
        return list;
    }

    public List<Staff> readStaffFromFile() throws IOException {
        //Staff file will be in format firstname lastname age role
        Scanner s = new Scanner(staffFile);
        List<Staff> list = new ArrayList<Staff>();
        while (s.hasNext()){
            String staffList = s.next();
            String[] arrOfStr = staffList.split(" ");
            String name = arrOfStr[0] + " " + arrOfStr[1];
            int age = Integer.parseInt(arrOfStr[2]);
            String role = arrOfStr[3];
            list.add(new Staff(name, age, role));
        }
        s.close();
        return list;
    }

    public void writeInventoryToFile(List<Item> items) throws IOException {
        FileWriter writer = new FileWriter(inventoryFile);
        for(Item anItem: items) {
            writer.write(anItem.getName() + " " + anItem.getPrice() + " " + anItem.getQuantity() + " " + anItem.getAisle());
        }
        writer.close();
    }

    public void writeStaffToFile(List<Staff> employees) throws IOException {
        FileWriter writer = new FileWriter(staffFile);
        for(Staff staffMember: employees) {
            writer.write(staffMember.getName() + " " + staffMember.getAge() + " " + staffMember.getRole());
        }
        writer.close();
    }

    public static List<String> readCommandsFromFile() throws IOException {
        Scanner s = new Scanner(inputFile);
        ArrayList<String> list = new ArrayList<String>();
        while (s.hasNext()){
            list.add(s.next());
        }
        s.close();
        return list;
    }

    public static void writeStoreScheduleToFile(List<String> lines) throws IOException {
        FileWriter writer = new FileWriter(storeScheduleFile);
        for(String line: lines) {
            writer.write(line);
        }
        writer.close();
    }

    public static void writeLineToOutputFile(String line) throws IOException {
        FileWriter file = new FileWriter(outputFile, true);
        BufferedWriter writer = new BufferedWriter(file);
        writer.write(line);
        writer.newLine();
        writer.close();
    }

}
