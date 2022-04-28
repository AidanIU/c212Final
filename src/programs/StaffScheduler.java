package programs;

import models.Staff;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static utils.FileUtils.readStaffFromFile;

public class StaffScheduler {
    private List<Staff> peopleInfo;

    public void scheduleStaff() {
        String[] weekdays = {"M", "T", "W", "TR", "F", "SAT", "SUN"};
        try {
            peopleInfo = readStaffFromFile();
            PrintWriter out = new PrintWriter("src/resources/store_schedule_OUT.txt");

            //puts people in order starting with those working the fewest days to make sure they get at
            //least one work day if they can work very few days
            char search = '.';
            for (int i =1; i < peopleInfo.size(); i++){
                int index = 0;
                while((peopleInfo.get(i).getAvailability().chars().filter(ch -> ch == search).count()
                <peopleInfo.get(i-1).getAvailability().chars().filter(ch -> ch == search).count())&&index!=0){
                    peopleInfo.add(peopleInfo.get(i));
                    peopleInfo.remove(i);
                }
            }

            for (int i = 0; i < 7; i++) {
                int count = 0;
                out.print(weekdays[i] + " ");
                for (int x = 0; x < this.peopleInfo.size(); x++) {
                    String aval = peopleInfo.get(x).getAvailability();
                    if ((aval.contains("." + weekdays[i]) || aval.contains(weekdays[i] + ".")) && count != 3) {
                        out.print("(" + peopleInfo.get(x).getName() + ") ");
                        Staff a = peopleInfo.get(x);
                        peopleInfo.remove(x);
                        peopleInfo.add(a);
                        count++;
                    }
                }
                out.print("\n");
            }
            out.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

