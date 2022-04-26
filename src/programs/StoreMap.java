package programs;

import javax.swing.*;
import java.awt.*;

public class StoreMap extends JComponent {
    private final int aisleNum;
    public StoreMap(int aisleNumberToHighlight) {
        this.aisleNum = aisleNumberToHighlight;
    }

    public void paintComponent(Graphics g) {
        // Drawing the map
        // No need to touch the code in this section of the method
        // Set StoreMapDisplay.WIDTH to 700 and StoreMapDisplay.HEIGHT to 500 for this to display properly!

        int canvasWidth = StoreMapDisplay.WIDTH - 10;
        int canvasHeight = StoreMapDisplay.HEIGHT - 37;
        int aisleWidth = 200;
        int aisleHeight = 40;
        // draw the map
        // perimeter walls
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, canvasWidth, 10); // north
        g.fillRect(0, canvasHeight-10, canvasWidth, 10); // south
        g.fillRect(0, 0, 10, canvasHeight-10); // west
        g.fillRect(canvasWidth-13, 0, 10, canvasHeight-10); // east
        // draw gardening section walls
        g.fillRect(450, 0, 10, canvasHeight/2); // west
        g.fillRect(450, canvasHeight/2-10, canvasHeight/2, 10);
        // draw shelves
        g.setColor(Color.BLUE);
        for (int i = 0; i < 7; i++) {
            g.fillRect(30, 30 + 60 * i, aisleWidth, aisleHeight);
            if (i > 3) g.fillRect(450, 30 + 60 * i, aisleWidth, aisleHeight);
        }
        // draw planter boxes in gardening section
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                g.fillRect(490 + 60 * i, 30 + 60 * j, 40, 40);
            }
        }
        // draw aisle numbers
        // shelves
        g.setColor(Color.BLACK);
        for (int i = 0; i < 12; i++) {
            if (i < 8) {
                g.drawString("Aisle " + (i+1), 110, 25 + 60 * i);
            } else {
                g.drawString("Aisle " + (i+1), 530, 25 + 60 * (i-4));
            }
        }
        // planter boxes
        g.setColor(Color.WHITE);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                g.drawString("A " + (12 + (i+1) + 3 * j), 500 + i * 60, 60 + 60 * j);
            }
        }

        // TODO: draw the box appropriately around the aisle. You'll need to define these boundaries yourself for each one!
        g.setColor(Color.GREEN);
        int i = aisleNum - 1;
        if (i < 8) {
            g.drawRect(30, 30 + 60 * i, aisleWidth, aisleHeight);
            g.drawRect(30 - 1, (30 + 60 * i) - 1, aisleWidth + 2, aisleHeight + 2);
            g.drawRect(30 - 2, (30 + 60 * i) - 2, aisleWidth + 4, aisleHeight + 4);
        } else if (i < 12) {
            g.drawRect(450, (30 + 60 * (i - 4)), aisleWidth, aisleHeight);
            g.drawRect(450 - 1, (30 + 60 * (i - 4)) - 1, aisleWidth + 2, aisleHeight + 2);
            g.drawRect(450 - 2, (30 + 60 * (i - 4)) - 2, aisleWidth + 4, aisleHeight + 4);
        } else if (i < 15) {
            g.drawRect(490 + 60 * (i - 12), 30 + 60 * 0, 40, 40);
            g.drawRect((490 + 60 * (i - 12) - 1), ((30 + 60 * 0)-1) , 40 + 2, 40 + 2);
            g.drawRect((490 + 60 * (i - 12) - 2), ((30 + 60 * 0)-2), 40 + 4, 40 + 4);
        } else if (i < 18) {
            g.drawRect(490 + 60 * (i - 15), 30 + 60 * 1, 40, 40);
            g.drawRect((490 + 60 * (i - 15) - 1), ((30 + 60 * 1)-1), 40 + 2, 40 + 2);
            g.drawRect((490 + 60 * (i - 15) - 2), ((30 + 60 * 1)-2), 40 + 4, 40 + 4);
        }else if (i < 21) {
            g.drawRect(490 + 60 * (i - 18), 30 + 60 * 2, 40, 40);
            g.drawRect((490 + 60 * (i - 18) - 1), ((30 + 60 * 2)-1), 40 + 2, 40 + 2);
            g.drawRect((490 + 60 * (i - 18) - 2), ((30 + 60 * 2)-2), 40 + 4, 40 + 4);
        }
    }
}


