package programs;

import models.Item;
import models.Staff;

import java.util.List;

public interface IStore{
    public List<Item> getItemsFromFile();
    public List<Staff> getStaffFromFile();
    public void saveItemsFromFile();
    public void saveStaffFromFile();
    public void takeAction();
}