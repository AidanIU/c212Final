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

import static java.lang.String.valueOf;


public abstract class Store implements IStore{

    public void takeAction() throws IOException {
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
            if (command.equals("") || command.equals(" ") || command.equals("\n")){
                System.out.println("No Commands!!");
                break;
            }
            if (command.substring(0,3).equals("ADD")){
                //
                int startName = command.indexOf("'");
                int endName = command.lastIndexOf("'");
                String itemName = command.substring(startName + 1 , endName);
                String restOfString = command.substring(endName + 2);
                String[] arrOfStr = restOfString.split(" ");
                int cost = Integer.parseInt(arrOfStr[0]);
                int quantity = Integer.parseInt(arrOfStr[1]);
                int aisle = Integer.parseInt(arrOfStr[2]);
                //
                inventoryList.add(new Item(itemName, cost, quantity, aisle));
                String outputLine = itemName + " has been added to inventory";
                try {
                    FileUtils.writeLineToOutputFile(outputLine);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if (command.substring(0,3).equals("SAW")){
                boolean toRemove = false;
                Item removingItem = null;
                String newName = null;
                int newPrice = 0;
                int totalPlanks = 0;
                int aisle = 0;
                ArrayList<Item> bigPlanks = new ArrayList<Item>();
                for (Item item: inventoryList) {
                    String name = item.getName();
                    if (name.length() < 5) {continue;}
                    if (name.substring(0,5).equals("Plank")){
                        removingItem = item;
                        toRemove = true;
                        String[] arrOfStr = name.split(" ");
                        String[] arrOfStr2 = arrOfStr[0].split("-");
                        int length = Integer.parseInt(arrOfStr2[1]);
                        int quantity = item.getQuantity();
                        aisle = item.getAisle();
                        List<Integer> newPlanks = SawPrimePlanks.getPlankLengths(length);
                        int newLength = newPlanks.get(0);
                        totalPlanks = newPlanks.size() * quantity;
                        newPrice = newLength * newLength;
                        newName = "Plank-" + Integer.toString(newLength);
                    }
                }
                if (toRemove) {
                    inventoryList.remove(removingItem);
                    inventoryList.add(new Item(newName, newPrice, totalPlanks, aisle));
                }
                try {
                    FileUtils.writeLineToOutputFile("Planks Sawed");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if (command.substring(0,4).equals("COST")){
                int startName = command.indexOf("'");
                int endName = command.lastIndexOf("'");
                String itemName = command.substring(startName + 1 , endName);
                int price = 0;

                for (Item itemFromList: inventoryList) {
                    if(itemFromList.getName().equals(itemName)){
                        price = (int) itemFromList.getPrice();
                    }
                }
                String printingPrice = valueOf(price);
                try {
                    FileUtils.writeLineToOutputFile(itemName + ": $" + printingPrice);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if (command.substring(0,4).equals("EXIT")){
                Scanner enterButton = new Scanner(System.in);
                try {
                    FileUtils.writeLineToOutputFile("Thank you for visiting High's Hardware and Gardening!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Press enter to continue...");
                String wait = enterButton.nextLine();
            }
            else if (command.substring(0,4).equals("FIND")) {
                int startName = command.indexOf("'");
                int endName = command.lastIndexOf("'");
                String itemName = command.substring(startName + 1, endName);

                for (int i = 0; i < inventoryList.size(); i++) {
                    if (itemName.equals(inventoryList.get(i).getName())) {
                        StoreMapDisplay.display(inventoryList.get(i));
                        try {
                            FileUtils.writeLineToOutputFile("Performing store lookup for " + itemName);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    } else if (i == inventoryList.size()-1){
                        try {
                            FileUtils.writeLineToOutputFile("ERROR: " + itemName + " cannot be found");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

//Peter
            else if (command.substring(0,4).equals("FIRE")){}
            //Peter
            else if (command.substring(0,4).equals("HIRE")){}


            else if (command.substring(0,4).equals("SELL")) {

                int startName = command.indexOf("'");
                int endName = command.lastIndexOf("'");
                String itemName = command.substring(startName + 1 , endName);
                String restOfString = command.substring(endName + 2);
                String[] arrOfStr = restOfString.split(" ");
                int quantity = Integer.parseInt(arrOfStr[0]);

                for (int i = 0; i < inventoryList.size(); i++) {
                    if (itemName.equals(inventoryList.get(i).getName()) &&
                            inventoryList.get(i).getQuantity() >= quantity){
                        try {
                            FileUtils.writeLineToOutputFile(quantity + " " + itemName + " was sold");
                        }  catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if (i == inventoryList.size()-1){
                        try {
                            FileUtils.writeLineToOutputFile("ERROR: " + itemName + " could not be sold");
                        }  catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            else if (command.substring(0,7).equals("PROMOTE")){
                //G Gardner
                //M Manager
                //C Cashier
                //Jason Henderson 35 M T.TR.SAT.SUN
                int startName = command.indexOf("'");
                int endName = command.lastIndexOf("'");
                String staffName = command.substring(startName + 1 , endName);
                String role = command.substring(command.length()-1);
                if (role.equals("G")){role = "Gardner";}
                if (role.equals("M")){role = "Manager";}
                if (role.equals("C")){role = "Cashier";}
                boolean remove = false;
                Staff staffRemove = null;
                for (Staff staffMember: staffList) {
                    if (staffMember.getName().equals(staffName)){
                        remove = true;
                        staffRemove = staffMember;
                        //<staffName> was promoted to <role>
                        String outputLine = staffMember.getName() + " was promoted to " + role;
                        try {
                            FileUtils.writeLineToOutputFile(outputLine);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                if (remove){
                    staffList.remove(staffRemove);
                    staffList.add(new Staff(staffRemove.getName(), staffRemove.getAge(), role.substring(0,1), staffRemove.getAvailability()));
                }
            }
            else if (command.substring(0,8).equals("SCHEDULE")) {
                StaffScheduler create = new StaffScheduler();
                create.scheduleStaff();
                System.out.println("Schedule created");
            }
            else if (command.substring(0,8).equals("QUANTITY")){
                int startName = command.indexOf("'");
                int endName = command.lastIndexOf("'");
                String itemName = command.substring(startName + 1 , endName - 1);

                for (Item QuantityOfR: inventoryList) {
                    if(QuantityOfR.getName().equals(itemName)){
                        try {
                            FileUtils.writeLineToOutputFile(valueOf(QuantityOfR.getQuantity()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

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