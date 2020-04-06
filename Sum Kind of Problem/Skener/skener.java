import java.util.*;
import java.io.*; 

public class skener{

    public static void main(String[] args)throws Exception{

        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(r);  
        int[] input = new int[4];
        String inputString = br.readLine();
        String[] inputArray = inputString.trim().split("\\s+");
        for (int i = 0; i < 4; i++)
        	input[i] = Integer.parseInt(inputArray[i]);
        int ZR = input[2];
        int ZC = input[3];

        String[] oriArt = new String[input[0]];
        String[] newArt = new String[input[0]*input[2]];
        
        for (int i = 0; i < input[0]; i++){
        	oriArt[i] = br.readLine();
        }
        boolean flag = true;

        String temp = "";
        char[] ch = new char[input[1]];

        for (int i = 0; i < newArt.length; i += ZR){
        	flag = true;
        	for(int j = 0; j < ZR; j++){
        		if (flag){
	        		ch = oriArt[(int)(i / ZR)].toCharArray();
	        		for(int length = 0; length < ch.length; length++){
	        			for(int k = 0; k < ZC; k++){
	        				temp = temp + ch[length];
	        			}
	        		}
	        		flag = false;
	        	}
       	 		newArt[i+j] = temp;
        	}
        	temp = "";
        }

        for (int i = 0; i < newArt.length; i++){
        	System.out.println(newArt[i]);
        }
    }
}