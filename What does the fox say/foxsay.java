import java.util.*;
import java.io.*;  

public class foxsay{

    public static void main(String[] args)throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        
        int num_test = Integer.parseInt(br.readLine());

        for(int tests = 0; tests < num_test; tests++){
            String sounds = br.readLine();
            HashSet<String> animals = new HashSet<String>();
            String known_sound;

            if(tests!=0){
                pw.println("");
            }
            boolean stop = false;
            while(!stop){
                known_sound = br.readLine();
                if(known_sound.equals("what does the fox say?")){
                    stop = true;
                    continue;
                }
                String[] animal = known_sound.split(" ");
                animals.add(animal[2]);
            }
                

            String[] sounds_list = sounds.split(" ");
            StringBuilder foxsays = new StringBuilder();
            boolean first = true;
            for(int i = 0; i < sounds_list.length; i++){
                if(!animals.contains(sounds_list[i])){
                    if(first){
                        foxsays.append(sounds_list[i]);
                        first = false;    
                    }
                    else
                        foxsays.append(" " + sounds_list[i]);
                }
            }

            pw.print(foxsays);
        }
        pw.close();    
    }
}



