import java.util.*;
import java.io.*;  

public class sortOfSorting{

    public static void main(String[] args){

        Kattio io = new Kattio(System.in, System.out);

        int noStudents = io.getInt();
        StudentComparator stuComp = new StudentComparator();

        while(noStudents != 0){
            /* create array to fit next set of students */
            String[] studentArr = new String[noStudents];

            /* read and set details of students */
            for(int i = 0; i < noStudents; i ++){
                studentArr[i] = io.getWord();
            }

            /* sort in ascending based on first 2 alphabet of name */
            Arrays.sort(studentArr, stuComp);

            for(int i = 0; i < noStudents; i++){
                io.println(studentArr[i]);
            }

            noStudents = io.getInt();
            if (noStudents != 0)
                io.println("");
        }

        io.close();
    }
}


class StudentComparator implements Comparator<String>{
    public int compare(String s1, String s2){

        if(s1.charAt(0) < s2.charAt(0))
            return -1;
        else if(s1.charAt(0) == s2.charAt(0))
            if(s1.charAt(1) < s2.charAt(1))
                return -1;
            else if (s1.charAt(1) == s2.charAt(1))
                return 0;
            else
                return 1;
        else
            return 1;
    }

    public boolean equals(Object obj){
        return this == obj;
    }
}