import java.util.*;
import java.io.*;  

public class gpbernie{

    public static void main(String[] args){

        Kattio io = new Kattio(System.in, System.out);

        int num_trips = io.getInt();

        HashMap<String, ArrayList<Integer>> countries = new HashMap<String, ArrayList<Integer>>();
        HashMap<String, Boolean> sorted = new HashMap<String,Boolean>();

        for(int i = 0; i < num_trips; i++){
            String country = io.getWord();
            int year = io.getInt();

            sorted.put(country,false);
            //country is alr in our map
            ArrayList<Integer> years;
            if(countries.containsKey(country)){
                years = countries.get(country);
                years.add(year);
            }
            else{
                years = new ArrayList<Integer>();
                years.add(year);
            }
            countries.put(country,years);
        }

        int num_query = io.getInt();
        for(int i = 0; i < num_query; i ++){
            String country = io.getWord();
            int query = io.getInt();

            ArrayList<Integer> years = countries.get(country);
            if(sorted.get(country)==false){
                years.sort(null);
                sorted.put(country,true);
            }
            int year = years.get(query-1);
            io.println(year);
        }

        
        io.close();    
    }
}



