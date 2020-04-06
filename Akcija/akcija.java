
import java.util.*;
import java.io.*;  
import java.lang.Math;

public class akcija{

    public static void main(String[] args){

        Kattio io = new Kattio(System.in, System.out);

        int tempPrice = 0; //when we read value of book, store here first
        int numBooks = io.getInt();  // total num of books
        int netCost = 0;   //final cost after discount
        int totalCost = 0; //cost before discount
        int totalDiscount = 0; //discount

        int[] prices = new int[numBooks];  //array of the prices of books

        for(int i=0; i<numBooks; i++){
            tempPrice = io.getInt();
            totalCost += tempPrice;
            prices[i] = tempPrice;
        }

        //sort prices in descending
        //Arrays.sort(prices);
        Arrays.sort(prices);

        //3rd book in each stack is discounted
        for(int i = numBooks-3; i>=0; i -= 3){
            if(i<0)
                break;
            totalDiscount += prices[i];
        }

        netCost = totalCost - totalDiscount;
        
        io.println(netCost);

        io.close();
    }
    /*
    static void radixSort(int[] array, int n, int d){

        int[] leftover = new int[n];
        int k = 0;

        for(int j = d; j > 0; j--){
            //copy into temp
            for(int i = 0; i < n; i ++)
                leftover[i] = array[i] / (int)Math.pow(10,d-j);
            
            // create 10 queues
            QueueArr[] digit = new QueueArr[10];
            for(int i = 0 ; i < 10; i ++)
                digit[i] = new QueueArr();

            for(int i = 0; i < n; i ++){
                //get rightmost digit
                k = leftover[i] % 10;            
                //update array 
                digit[k].offer(array[i]);
            }

            int pos = 0;
            int digitArr = 0;
            while(digitArr <= 9){
                while(digit[digitArr].peek() != null){
                    array[pos] = digit[digitArr].poll();
                    pos ++;
                }
                digitArr ++;
            }
        } 
    }*/
}
