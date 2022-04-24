package programs;

import models.Item;
import models.Staff;
import utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public abstract class Store implements IStore{

    public void takeAction() {
        //ADD '<itemName>' <itemCost> <itemQuantity>
        //COST '<itemName>'
        //EXIT
        //FIND '<itemName>'
        //FIRE '<staffName>'
        //HIRE '<staffName>' <age> <role>
        //PROMOTE '<staffName>' <role>
        //SAW
        //SCHEDULE
        //SELL '<itemName>' <quantity>
        //QUANTITY '<itemName>'

        List<String> inputList = new ArrayList<String>();
        List<Staff> staffList = new ArrayList<Staff>();

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


    }

    public Store(){

        }
}