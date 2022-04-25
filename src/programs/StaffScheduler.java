package programs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class StaffScheduler {
    private ArrayList<String[]>  namesAvailability = new ArrayList<>();
    private ArrayList<Integer> storeHours = new ArrayList<>();
    private ArrayList<String> schedule = new ArrayList<>();
    private int totalTime = 0;

    public StaffScheduler() throws FileNotFoundException {
        updateNamesAvailability();
        updateStoreHours();
        sortWorkPeople();
    }

    private void updateNamesAvailability() throws FileNotFoundException {
        File inputFile= new File("C:\\Users\\peter\\OneDrive\\Documents\\C212\\c212Final-main\\src\\resources\\staff_availability_IN.txt");
        Scanner in = new Scanner(inputFile);

        while(in.hasNext()) {
            String temp = in.nextLine();
            String[] info = {temp.substring(0, temp.indexOf(" ", 2)),
                    temp.substring(temp.indexOf(" ", 4) + 1)};
            namesAvailability.add(info);
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
            this.totalTime += time;
            storeHours.add(time);
        }
    }

    private void sortWorkPeople(){
        for (int i = 1; i < this.namesAvailability.size(); i++){
            int index = i;
            while((this.namesAvailability.get(i)[1].chars().filter(ch -> ch == '.').count()
            <this.namesAvailability.get(i-1)[1].chars().filter(ch -> ch == '.').count())&& (index>0)){
                String[] temp = this.namesAvailability.get(i);
                this.namesAvailability.set(i,this.namesAvailability.get(i));
                this.namesAvailability.set(i-1,temp);
            }
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
        String[] weekdays = {"M","T","W","TR","F","SAT","SUN"};
        int count = 0;

        for (int i = 0; i < 7; i++){
            for (int x = 0; x < namesAvailability.size(); x++){
                if ()
            }
        }

        PrintWriter out = new PrintWriter("C:\\Users\\peter\\IdeaProjects\\c212Final\\src\\resources\\store_schedule_OUT.txt");
    }
}

