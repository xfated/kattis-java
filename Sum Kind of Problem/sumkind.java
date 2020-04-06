import java.util.*;
import java.io.*; 

public class sumkind{

    public static void main(String[] args)throws Exception{

        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(r);  

        int noData = Integer.parseInt(br.readLine());

        int[][] dataArray = new int[noData][2];
        int dataIndex = 0;
        int n = 0;


        for(int i = 0; i < noData; i++){
        	String initialStr = br.readLine();
        	String[] initialArr = initialStr.trim().split("\\s+");
        	for (int j = 0; j < 2; j++)
        		dataArray[i][j] = Integer.parseInt(initialArr[j]);

            dataIndex = dataArray[i][0];
            n = dataArray[i][1];
            System.out.println(dataIndex + " "
                            + sumArith(1,n,1) + " "
                            + sumArith(1,n,2) + " "
                            + sumArith(2,n,2));
        }


    }

    public static int sumArith(int a, int n, int d){
        return (2*a + (n-1)*d)*n/2;
    }
}