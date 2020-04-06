import java.util.*;
import java.io.*; 
import java.lang.Math;

public class exactlyE{

    public static void main(String[] args)throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int[] initial = new int[2];
        String initialStr = br.readLine();
        String[] initialArr = initialStr.trim().split("\\s+");
        for (int i = 0; i < 2; i++)
        	initial[i] = Integer.parseInt(initialArr[i]);
        
        int[] end = new int[2];
        String endStr = br.readLine();
        String[] endArr = endStr.trim().split("\\s+");
        for (int i = 0; i < 2; i++)
        	end[i] = Integer.parseInt(endArr[i]);
        
        int electCharge = Integer.parseInt(br.readLine());

        int noSteps = Math.abs(initial[0]-end[0]) + Math.abs(initial[1] - end[1]);

        if (electCharge < noSteps)
        	System.out.println("N");
        else if ((electCharge - noSteps)%2 != 0)
        	System.out.println("N");
        else
        	System.out.println("Y");
    }
}

public static int maxDifference(List<Integer> qty) {
    // Write your code here
        int prev = qty.get(0);
        int cur = 0;
        int maxDiff = -1;

        for(int i = 1; i < qty.size(); i ++){
            cur = qty.get(i);
            //current is smaller, store
            if(cur<prev){
                prev = cur;
            }
            else if (cur>prev){ //curr is greater than before
                int temp = cur - prev;
                if (temp>maxDiff)
                    maxDiff=temp;
            }
            
        }
        return maxDiff;

    }