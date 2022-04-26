package programs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class StaffScheduler {
    private ArrayList<String[]>  namesAvailability = new ArrayList<>();
//    private ArrayList<Integer> storeHours = new ArrayList<>();
//    private ArrayList<String> schedule = new ArrayList<>();
//    private int totalTime = 0;

    public StaffScheduler() throws FileNotFoundException {
        updateNamesAvailability();
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
        in.close();
    }

//    private void updateStoreHours() throws FileNotFoundException {
//        File inputFile= new File("C:\\Users\\peter\\IdeaProjects\\c212Final\\src\\resources\\shift_schedules_IN.txt");
//        Scanner in = new Scanner(inputFile);
//
//        while(in.hasNext()) {
//            String temp = in.nextLine();
//            int open = Integer.parseInt(temp.substring(temp.indexOf(" ", 1)+1,temp.indexOf(" ", 2)));
//            int close = Integer.parseInt(temp.substring(temp.indexOf(" ", 2)+1));
//            int time = (close - open)*60;
//            this.totalTime += time;
//            storeHours.add(time);
//        }
//        in.close();
//    }

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


    public void scheduleStaff() throws FileNotFoundException {
        String[] weekdays = {"M","T","W","TR","F","SAT","SUN"};

        PrintWriter out = new PrintWriter("C:\\Users\\peter\\IdeaProjects\\c212Final\\src\\resources\\store_schedule_OUT.txt");

        for (int i = 0; i < 7; i++){
            int count = 0;
            out.print(weekdays[i]+ " ");
            for (int x = 0; x < this.namesAvailability.size(); x++){
                if ((namesAvailability.get(x)[0].contains("."+weekdays[i]) || namesAvailability.get(x)[0].contains(weekdays[i]+".")) && count!=3){
                    out.print("("+namesAvailability.get(x)+") ");
                    namesAvailability.add(namesAvailability.get(x));
                    namesAvailability.remove(x);
                }
            }
        }
        out.close();
    }
}

