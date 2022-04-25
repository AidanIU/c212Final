package programs;

import models.Item;
import models.Staff;
import utils.FileUtils;
import java.util.Random;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


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
                String[] arrOfStr = restOfString.split(" ");
                int cost = Integer.parseInt(arrOfStr[0]);
                int quantity = Integer.parseInt(arrOfStr[1]);
                //Document only gives cost and quantity as part of this command, no aisle number
                Random rand;
                int number = rand.nextInt(21) + 1
                //
                inventoryList.add(new Item(itemName, cost, quantity, number));
                String outputLine = itemName + " has been addde to inventory";
                FileUtils.writeLineToOutputFile(outputLine);
            }
            else if (command.substring(0,2) == "SAW"){}
            else if (command.substring(0,3) == "COST"){
                int startName = command.indexOf("'");
                int endName = command.lastIndexOf("'");
                String itemName = command.substring(startName + 1 , endName - 1);
                int price = 0;

                for (Item itemFromList: inventoryList) {
                    if(itemFromList.getName() == itemName){
                        price = itemFromList.getPrice();
                    }
                }
                String printingPrice = valueOf(price);
                FileUtils.writeLineToOutputFile(itemName + ": $" + printingPrice);
            }
            else if (command.substring(0,3) == "EXIT"){
                Scanner enterButton = new Scanner(System.in);
                FileUtils.writeLineToOutputFile("Thank you for visiting High's Hardware and Gardening!");
                System.out.println("Press enter to continue...");
                String wait = enterButton.nextLine();
            }
            else if (command.substring(0,3) == "FIND"){

            }
            else if (command.substring(0,3) == "FIRE"){}
            else if (command.substring(0,3) == "HIRE"){}
            else if (command.substring(0,3) == "SELL"){}
            else if (command.substring(0,7) == "PROMOTE"){}
            else if (command.substring(0,8) == "SCHEDULE"){}
            else if (command.substring(0,8) == "QUANTITY"){
                int startName = command.indexOf("'");
                int endName = command.lastIndexOf("'");
                String itemName = command.substring(startName + 1 , endName - 1);

                for (Item QuantityOfR: inventoryList) {
                    if(QuantityOfR.getName() == itemName){
                        FileUtils.writeLineToOutputFile(String.valueOf(QuantityOfR.getQuantity()));

                    }
                }

            }
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