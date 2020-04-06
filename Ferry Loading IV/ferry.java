import java.util.*;
import java.io.*;  

public class ferry{

    public static void main(String[] args){

        Kattio io = new Kattio(System.in, System.out);

        int num_test = io.getInt();
        //iterate for each test case
        for(int test = 0; test < num_test; test++){
            int ferry_len = io.getInt() * 100; //convert to cm
            int num_car = io.getInt();
            int num_trips = 0;

            LinkedList<Integer> carsLeft = new LinkedList<Integer>();
            LinkedList<Integer> carsRight = new LinkedList<Integer>();
            
            //get cars
            for(int i = 0; i < num_car; i ++){
                int car_len = io.getInt();
                String car_side = io.getWord();
                if(car_side.equals("left"))
                    carsLeft.offer(car_len);
                else
                    carsRight.offer(car_len);
            } 

            boolean isLeft = true;
            // while there is still a car on any side
            while(!(carsLeft.peekFirst()==null && carsRight.peekFirst()==null)){
                //start with left. alternate side
                if(isLeft){ //process left queue
                    int current_cap = 0;
                    boolean full=false;
                    while(!full){
                        if(carsLeft.peekFirst() == null){ //if alr empty dont do anything
                            full=true; 
                            continue;
                        }
                        int car = carsLeft.peekFirst();
                        if(current_cap + car < ferry_len){
                            current_cap += car;  //add length of car
                            carsLeft.poll();  //remove from queue
                        }
                        else{
                            full=true;
                            continue;
                        }
                    }
                    num_trips++;
                    isLeft = false;
                }
                else{ //process right queue
                    int current_cap = 0;
                    boolean full=false;
                    while(!full){
                        if(carsRight.peekFirst() == null){ //if alr empty dont do anything
                            full=true; 
                            continue;
                        }
                        int car = carsRight.peekFirst();
                        if(current_cap + car < ferry_len){
                            current_cap += car;  //add length of car
                            carsRight.poll();  //remove from queue
                        }
                        else{
                            full=true;
                            continue;
                        }
                    }
                    num_trips++;
                    isLeft = true;
                }
            }
            io.println(num_trips);

        }
        io.close();    
    }
}



