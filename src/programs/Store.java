package programs;

import models.Item;
import models.Staff;
import utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public abstract class Store implements IStore{

    public void takeAction() {
        //ADD '<itemName>' <itemCost> <itemQuantity>
        //SAW
        //COST '<itemName>'
        //EXIT
        //FIND '<itemName>'
        //FIRE '<staffName>'
        //HIRE '<staffName>' <age> <role>
        //SELL '<itemName>' <quantity>
        //PROMOTE '<staffName>' <role>
        //SCHEDULE
        //QUANTITY '<itemName>'

        List<String> inputList = new ArrayList<String>();
        List<Staff> staffList = new ArrayList<Staff>();
        List<Item> inventoryList = new ArrayList<Item>();

        try {
            inputList = FileUtils.readCommandsFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            staffList = FileUtils.readStaffFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            inventoryList = FileUtils.readInventoryFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (String command: inputList){
            if (command.substring(0,2) == "ADD"){
                //
                int startName = command.indexOf("'");
                int endName = command.lastIndexOf("'");
                String itemName = command.substring(startName + 1 , endName - 1);
                String restOfString = command.substring(endName + 1);
                //
                String inventoryString = itemName + " " + restOfString;
                inventoryList.add(inventoryString);
                String outputString = itemName + " was added to iventory";
                FileUtils.writeLineToOutputFile(outputString);
            }
            else if (command.substring(0,2) == "SAW"){}
            else if (command.substring(0,3) == "COST"){}
            else if (command.substring(0,3) == "EXIT"){}
            else if (command.substring(0,3) == "FIND"){}
            else if (command.substring(0,3) == "FIRE"){}
            else if (command.substring(0,3) == "HIRE"){}
            else if (command.substring(0,3) == "SELL"){}
            else if (command.substring(0,7) == "PROMOTE"){}
            else if (command.substring(0,8) == "SCHEDULE"){}
            else if (command.substring(0,8) == "QUANTITY"){}
        }


        try {
            FileUtils.writeInventoryToFile(inventoryList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileUtils.writeStaffToFile(staffList);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public Store(){
            takeAction();
        }
}