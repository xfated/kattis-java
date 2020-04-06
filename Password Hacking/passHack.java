
import java.util.*;
import java.io.*;  

public class passHack{

    public static void main(String[] args){

        Kattio io = new Kattio(System.in, System.out);

        int noPassword = io.getInt();

        double[] pwChance = new double[noPassword];
        double expected = 0;


        for (int i = 0; i < noPassword; i ++){
            io.getWord();
            pwChance[i] = io.getDouble();
        }

        Arrays.sort(pwChance);

        for(int i = noPassword - 1; i >= 0; i --)
            expected += (noPassword - i) * pwChance[i]; 
        

        io.println(expected);

        io.close();
    }
}

