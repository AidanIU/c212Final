package programs;

import models.Item;
import models.Staff;

import java.util.List;

public interface IStore{
    List<Item> getItemsFromFile(){}
    List<Staff> getStaffFromFile(){}
    void saveItemsFromFile(){}
    void saveStaffFromFile(){}
    void takeAction(){}
}