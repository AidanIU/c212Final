package programs;

import models.Staff;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static utils.FileUtils.readStaffFromFile;

public class StaffScheduler {
    private List<Staff> peopleInfo;

    public void scheduleStaff() throws IOException {
        String[] weekdays = {"M", "T", "W", "TR", "F", "SAT", "SUN"};

        peopleInfo = readStaffFromFile();

        PrintWriter out = new PrintWriter("src/resources/store_schedule_OUT2.txt");

        for (int i = 0; i < 7; i++) {
            int count = 0;
            out.print(weekdays[i] + " ");
            for (int x = 0; x < this.peopleInfo.size(); x++) {
                out.print(weekdays[i] + " ");
                String aval = peopleInfo.get(x).getAvailability();
                if (aval.contains("." + weekdays[i]) || aval.contains(weekdays[i] + ".")) {
                    out.print("(" + peopleInfo + ") ");
                }
                System.out.println();
            }
        }
        out.close();
    }
}

