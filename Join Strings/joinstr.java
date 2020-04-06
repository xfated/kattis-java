import java.util.*;
import java.io.*;  

public class joinstr{

    public static void main(String[] args){

        Kattio io = new Kattio(System.in, System.out);

        int numString = io.getInt();
        //for ease of manipulating 1-based in dexing. index 0 is dummy
        String[] texts = new String[numString + 1];
        int[] next = new int[numString+1];
        int[] end = new int[numString+1];
        int first = 0;
        if (numString == 1){
            io.println(io.getWord());
        }
        else{
            //read all the words
            for(int i = 1; i <= numString; i++){
                texts[i] = io.getWord();
            }
            // instr holds index of word
            int instr1 = 0;
            int instr2 = 0;

            // N-1 Instructions 
            for(int i = 0; i < numString-1; i++){
                //read instruction
                instr1 = io.getInt();
                instr2 = io.getInt();

                //set up a head of string
                if(i == 0)
                    first = instr1;
                //update front of string
                if(instr2 == first){
                    first = instr1;
                }
                //no next yet
                if(next[instr1] == 0){
                    next[instr1] = instr2;
                    end[instr1] = instr2;
                }
                //update next, and end of chain.
                else{
                    int temp = end[instr1];
                    while(end[temp]!=0){ //reached the end of the queue
                        temp=end[temp];
                    }
                    next[temp] = instr2;
                    end[temp] = instr2;
                    end[instr1] = temp;
                }
            }
            int a = 0;
            //StringBuilder str = new StringBuilder("");
            for(a = first; next[a] != 0; a = next[a]){
                io.print(texts[a]);
            }
            //str.append(texts[a]);
            //io.print(str);
            io.print(texts[a]);
        }


        io.close();
    }

}


