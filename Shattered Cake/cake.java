import java.util.*;
import java.io.*; 

public class cake{

    public static void main(String[] args)throws Exception{

        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(r);  

        int width = Integer.parseInt(br.readLine());
        int pieces = Integer.parseInt(br.readLine());        

        int[] tempPiece = new int[2];
        int totalArea = 0;

        for(int i = 0; i < pieces; i++){
        	String initialStr = br.readLine();
        	String[] initialArr = initialStr.trim().split("\\s+");
        	for (int j = 0; j < 2; j++)
        		tempPiece[j] = Integer.parseInt(initialArr[j]);

        	totalArea += tempPiece[0] * tempPiece[1];
        }
        
        System.out.println(totalArea/width);
    }
}