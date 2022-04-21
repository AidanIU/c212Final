package programs;

public interface IStore{
    List<Item> getItemsFromFile(){}
    List<Staff> getStaffFromFile(){}
    void saveItemsFromFile(){}
    void saveStaffFromFile(){}
    void takeAction(){}
}