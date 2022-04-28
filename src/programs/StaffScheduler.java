package programs;

import models.Staff;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import static utils.FileUtils.readStaffFromFile;

public class StaffScheduler {
    private List<Staff> peopleInfo;

    public void scheduleStaff() {
        String[] weekdays = {"M", "T", "W", "TR", "F", "SAT", "SUN"};

        try {
            peopleInfo = readStaffFromFile();
            PrintWriter out = new PrintWriter("src/resources/store_schedule_OUT.txt");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            out.println("Created "  +  dtf.format(now));

            //puts people in order starting with those working the fewest days to make sure they get at
            //least one work day if they can work very few days
            char search = '.';
            for (int i = 1; i < peopleInfo.size(); i++){
                int index = i;
                while((peopleInfo.get(index).getAvailability().chars().filter(ch -> ch == search).count()
                <peopleInfo.get(index-1).getAvailability().chars().filter(ch -> ch == search).count())){
                    Staff a = peopleInfo.get(index);
                    peopleInfo.set(index,peopleInfo.get(index-1));
                    peopleInfo.set(index-1,a);
                    if (index ==1){
                        break;
                    }else{
                    index--;}
                }
            }

            //creates an array to hold how many times each person has been scheduled
            int[] counter = new int[peopleInfo.size()];
            Arrays.fill(counter,0);

            for(int i = 0; i < 7; i++){
                ArrayList<Staff> people = new ArrayList<>();
                out.print(weekdays[i] + " ");

                //gets the indexes of the people who can work
                for (int x = 0; x < this.peopleInfo.size(); x++){
                    String aval = peopleInfo.get(x).getAvailability();
                    if (aval.contains("." + weekdays[i]) || aval.contains(weekdays[i] + ".")){
                        people.add(peopleInfo.get(x));
                    }
                }

                //adds each working person to the file if they have worked the least, does this 3 times
                //or hired everyone who can work if there are less than three who can work
                for (int x = 0; x < 3; x++){
                    //lowest is assigned the index of the person who can work a day that has worked fewer days than everyone else
                    //who can work that day
                    int lowest = 0;
                    for (int z = 0; z < people.size(); z++){
                        for(int l = z; l < people.size(); l++){
                            if (counter[peopleInfo.indexOf(people.get(z))] > counter[peopleInfo.indexOf(people.get(l))]){
                                lowest = l;
                                z = l;
                                break;
                            }
                        }
                    }

                    out.print("("+people.get(lowest).getName()+") ");
                    counter[peopleInfo.indexOf(people.get(lowest))] +=1;
                    people.remove(lowest);
                    System.out.println(Arrays.toString(counter));
                }
                out.println();
            }
            out.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

