import java.util.*;
import java.io.*;  

public class integerlists{

    public static void main(String[] args)throws IOException{
        
        Kattio io = new Kattio(System.in, System.out);

        int numTest = io.getInt();

        //iterate for each test case
        for(int i = 0; i < numTest; i ++){
            //read instr
            String instrString = io.getWord();
            char[] instr = instrString.toCharArray();

            //read no. of int
            int numInt = io.getInt();

            int[] testList = new int[numInt];
            
            
            String test = io.getWord(); 
            test = test.replaceAll("\\[|\\]","");
            String[] test2 = test.split(",");
            for(int b = 0; b < numInt; b++){
                testList[b] = Integer.parseInt(test2[b]);
            }
            /*String listString = io.getWord().replaceAll("\\[|\\]","");
            int[] testList = Arrays.stream(listString.split(","))
                .map(String::trim).mapToInt(Integer::parseInt).toArray();
            */
            
            int start = 0;
            int end = numInt-1;
            if(numInt==1)
                end=start;    
            boolean reverse = false;
            boolean error = false;

            for(int k = 0; k < instr.length; k++){
                if(instr[k] == 'R'){
                    reverse = !reverse;
                }
                else if(instr[k] == 'D'){
                    if(numInt==0){
                        io.println("error");
                        error = true;
                        break;
                    }
                    else if (numInt>1){
                        if(reverse){
                            end--;
                        }
                        else{
                            start++;
                        }
                    }
                    numInt--;
                }
            }
            if(!error){
                io.print("[");
                if(!reverse){
                    if(numInt!=0){
                        io.print(testList[start]);
                        for(int a = start+1; a <= end; a ++){
                            io.print(",");
                            io.print(testList[a]);
                        }
                    }
                }
                else{
                    if(numInt!=0){
                        io.print(testList[end]);
                        for(int a = end-1; a >= start; a --){
                            io.print(",");
                            io.print(testList[a]);
                        }
                    }
                }
                io.println("]");
            }
            
        }
        io.close();
    }

}


