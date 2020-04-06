import java.util.*;
import java.io.*;  

public class boatparts{

    public static void main(String[] args)throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        
        String details = br.readLine();
        String[] detailsList = details.split(" ");
        int num_parts = Integer.parseInt(detailsList[0]);
        int num_days = Integer.parseInt(detailsList[1]);

        HashSet<String> replaced = new HashSet<String>(500,(float)0.5);
        int capacity = 0;
        for(int i = 1; i <= num_days; i ++){
            String part = br.readLine();
            //if part was not replaced before
            if (!replaced.contains(part)){
                replaced.add(part);
                capacity++;
            }
            //if all parts replaced, print the day
            if(capacity==num_parts){
                pw.println(i);
                break;
            }
        }

        if(capacity < num_parts)
            pw.println("paradox avoided");

        pw.close();    
    }
}

