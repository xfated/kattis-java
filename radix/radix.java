
import java.util.*;
import java.io.*;  
import java.lang.Math;

public class radix{

    public static void main(String[] args){

        Kattio io = new Kattio(System.in, System.out);

        int num = io.getInt();

        int[] arr = new int[num];  //array of the prices of books

        for(int i=0; i<num; i++){
            arr[i] = io.getInt();
        }

        radixSort(arr, num, 5);
        
        for(int i = 0; i < num; i ++){
            io.println(arr[i]);
        }

        io.close();
    }

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
    }
}
