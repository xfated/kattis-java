import java.util.*;
import java.io.*;  

public class teque{

    public static void main(String[] args){

        Kattio io = new Kattio(System.in, System.out);

        int numInstr = io.getInt();
        // split array into 2
        // first array add from back. 2nd array add from front
        int[] first = new int[numInstr];
        int[] second = new int[numInstr];

        //reference of sizes for each array
        int size1 = 0;
        int size2 = 0;

        //holds reference start point of array
        //start point to current first item.
        //end point at current  last item.
        int start1 = numInstr/2;
        int start2 = numInstr/2;
        int end1 = numInstr/2;
        int end2 = numInstr/2;

        String instr = "";
        int val = 0;
        for(int i = 0; i < numInstr; i ++){
            instr = io.getWord();
            val = io.getInt();
            //add to start of first array --> XXXXX    XXXXXX
            if(instr.equals("push_front")){
                if(size1==0)
                    first[start1]=val;
                else{
                    start1--;
                    if(start1==-1)
                        start1=numInstr-1; //move to back of array
                    first[start1]=val;
                }
                size1++;
                if(size1>size2+1){   // move to start of 2nd queue to aintain size of first queue = (totalsize)/2 e.g if 9 --> 5 4
                    if(size2==0){
                        second[start2] = first[end1];
                        if(size1>1){
                            end1--;
                            if(end1==-1)
                                end1=numInstr-1;
                        }
                    }
                    else{
                        start2--;
                        if(start2==-1) //if reach front of q, move to back (loop)
                            start2=numInstr-1; 
                        second[start2] = first[end1]; //2nd q shorten by 1, first 1 extend by 1;
                        first[end1] = 0;
                        if(size1>1){
                            end1--;
                            if(end1==-1)
                               end1=numInstr-1;
                        }
                    }
                    size1--;
                    size2++;
                }
            }
            if(instr.equals("push_back")){
                if(size2==0)
                    second[end2]=val;
                else{
                    end2++;
                    if(end2==numInstr)//if reach end of q, move to front
                        end2=0;
                    second[end2]=val;
                }
                size2++;

                if(size2>size1){ //to maintain size

                    if(size1==0){
                        first[end1] = second[start2];
                        if(size2>1)
                            start2++;
                        if(start2==numInstr)
                            start2=0;
                    }
                    else{
                        end1++;
                        if(end1==numInstr)
                            end1=0;
                        first[end1] = second[start2]; 
                        if(size2>1)
                            start2++;
                        if(start2==numInstr)
                            start2=0;
                    }
                    size2--;
                    size1++;
                }
            }
            if(instr.equals("push_middle")){
                //middle always add to start of 2nd due to our maintained size
                if(size2==0)
                    second[start2]=val;
                else{
                    start2--;
                    if(start2==-1)//if reach end of q, move to front
                        start2=numInstr-1;
                    second[start2]=val;
                }
                size2++;
                if(size2>size1){ //to maintain size
                    if(size1==0){
                        first[end1] = second[start2];
                        second[start2]=0;
                        if(size2>1)
                            start2++;
                        if(start2==numInstr)
                            start2=0;
                    }
                    else{
                        end1++;
                        if(end1==numInstr)
                            end1=0;
                        first[end1] = second[start2]; 
                        if(size2>1)
                            start2++;
                        if(start2==numInstr)
                            start2=0;
                    }
                    size2--;
                    size1++;
                }
            }
            if(instr.equals("get")){
                //if value is in 2nd array
                if(val+1>size1){
                    io.println(second[(start2+(val-size1))%numInstr]);
                }
                else
                    io.println(first[(start1+val)%numInstr]);
            }

            //Debugging 
            /*
            for(int k = 0; k < first.length; k++)
                System.out.print(first[k] + " ");
            System.out.print(" || ");
            for(int k = 0; k < first.length; k++)
                System.out.print(second[k] + " ");
            System.out.println("Size1: " + size1 + " & Size2: " + size2);
            System.out.println("start1: " + start1 + " end1: "+ end1);
            System.out.println("start2: " + start2 + " end2: "+ end2);
            */
        }
        io.close();    
    }
}



