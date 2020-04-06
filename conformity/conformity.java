import java.util.*;
import java.io.*;  

public class conformity{

    public static void main(String[] args){

        Kattio io = new Kattio(System.in, System.out);

        int max_count = 0;
        int num_frosch = io.getInt();
        int total_students = 0;

        //store all courses
        HashMap<String, Integer> courseCount = new HashMap<String, Integer>();

        for (int i = 0; i < num_frosch; i ++){
            int[] temp = new int[5];
            int cur_count = 0; //holds current count of num of frosh taking this course
            for(int j=0;j<5;j++){
                temp[j]=io.getInt();
            }

            //create unique key for each combination of courses
            Arrays.sort(temp);
            String joinedCourse = Arrays.toString(temp);

            // set of courses found in hashmap, update count by 1
            if(courseCount.containsKey(joinedCourse)){
                cur_count = courseCount.get(joinedCourse);
                cur_count++;
            }
            else{ //course not yet registered in our hashmap
                cur_count=1;
            }
            courseCount.put(joinedCourse, cur_count);
            if(cur_count>max_count)
                max_count=cur_count;
        }

        Set courses = courseCount.keySet();
        for (int i = 0; i<courses.length;i++){
            System.out.println(courses[i]);
        }
        /*
        Iterator hmIterator = courseCount.entrySet().iterator();
        while(hmIterator.hasNext()){
            Map.Entry mapElement = (Map.Entry)hmIterator.next();
            int val = (int)mapElement.getValue();
            if(val==max_count){
                total_students+=val;
            }
        }
        */
        io.println(total_students);
        
        io.close();    
    }
}



