import java.util.*;
import java.io.*; 

public class fizzBuzz{

    public static void main(String[] args)throws Exception{

        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(r);  
        int[] input = new int[3];
        String inputString = br.readLine();
        String[] inputArray = inputString.trim().split("\\s+");
        for (int i = 0; i < 3; i++)
        	input[i] = Integer.parseInt(inputArray[i]);
        
        for (int i = 1; i <= input[2]; i++){
            if((i % input[0] == 0) && (i % input[1] == 0))
                System.out.println("FizzBuzz");
            else if(i % input[0] == 0)
                System.out.println("Fizz");
            else if(i % input[1] == 0)
                System.out.println("Buzz");
            else 
                System.out.println(i);
        }
    }
}