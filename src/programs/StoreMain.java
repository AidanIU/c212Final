package programs;

import models.Item;
import models.Staff;

import java.util.List;

public class StoreMain{
    public static void main(String[] args) {
        Store highsHardware = new Store() {
            @Override
            public List<Item> getItemsFromFile() {
                return null;
            }

            @Override
            public List<Staff> getStaffFromFile() {
                return null;
            }

            @Override
            public void saveItemsFromFile() {

            }

            @Override
            public void saveStaffFromFile() {

            }
        };
    }
}