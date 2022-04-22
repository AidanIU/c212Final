package programs;

import models.Item;

import javax.swing.*;

public class StoreMapDisplay {
   public static final int HEIGHT = 500;
   public static final int WIDTH = 700;

    public static void display(Item product) {

      JFrame frame = new JFrame("High's Hardware Product Lookup:" + product.getName());


        frame.setSize(700, 500);
        StoreMap map = new StoreMap(product.getAisle());
        frame.add(map);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}