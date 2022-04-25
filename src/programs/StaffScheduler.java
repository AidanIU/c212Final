package programs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class StaffScheduler {
    private ArrayList<String[]>  namesAvaliability = new ArrayList<>();
    private ArrayList<Integer> storeHours = new ArrayList<>();
    private ArrayList<String> schedule = new ArrayList<>();

    public StaffScheduler() throws FileNotFoundException {
        updateNamesAvailability();
        updateStoreHours();
    }

    private void updateNamesAvailability() throws FileNotFoundException {
        File inputFile= new File("C:\\Users\\peter\\OneDrive\\Documents\\C212\\c212Final-main\\src\\resources\\staff_availability_IN.txt");
        Scanner in = new Scanner(inputFile);

        while(in.hasNext()) {
            String temp = in.nextLine();
            String[] info = {temp.substring(0, temp.indexOf(" ", 2)),
                    temp.substring(temp.indexOf(" ", 4) + 1)};
            namesAvaliability.add(info);
        }
    }

    private void updateStoreHours() throws FileNotFoundException {
        File inputFile= new File("C:\\Users\\peter\\IdeaProjects\\c212Final\\src\\resources\\shift_schedules_IN.txt");
        Scanner in = new Scanner(inputFile);

        while(in.hasNext()) {
            String temp = in.nextLine();
            int open = Integer.parseInt(temp.substring(temp.indexOf(" ", 1)+1,temp.indexOf(" ", 2)));
            int close = Integer.parseInt(temp.substring(temp.indexOf(" ", 2)+1));
            int time = (close - open)*60;
        }
    }








//    File inputFile = new File(fileName);
//    Scanner in = new Scanner(inputFile);
//
//while (in.hasNext()) {
//        String temp = in.nextLine();
//
//    PrintWriter out = new PrintWriter(fileName);
//        for (String i: patients){
//        out.println(i);
//    }
//        out.close();

    public void scheduleStaff(){



        PrintWriter out = new PrintWriter("C:\\Users\\peter\\IdeaProjects\\c212Final\\src\\resources\\store_schedule_OUT.txt");
    }
}

