import java.util.*;
import java.io.*;  

public class kattisquest{

    public static void main(String[] args){

        Kattio io = new Kattio(System.in, System.out);

        int num_commands = io.getInt();
        int E = 0;
        int G = 0;
        int X = 0;
        TreeMap<Integer, PriorityQueue<Integer>> quests = new TreeMap<Integer, PriorityQueue<Integer>>();
        String instr = "";
        for(int i = 0; i < num_commands; i ++){
            instr = io.getWord();
            switch(instr){
                case "add":
                    E = io.getInt();
                    G = io.getInt();
                    // that energy value already present
                    if(quests.containsKey(E)){
                        PriorityQueue<Integer> temp = quests.get(E);
                        temp.add(G);
                        quests.put(E,temp);
                    }
                    // energy value not yet inside
                    else{
                        PriorityQueue<Integer> temp = new PriorityQueue<Integer>(20,Collections.reverseOrder());
                        temp.add(G);
                        quests.put(E,temp);
                    }
                    break;
                case "query":
                    X = io.getInt();
                    long num_gold = 0;
                    //if there is a quest with energy X, will fully use up X
                    if(quests.containsKey(X)){
                        PriorityQueue<Integer> temp = quests.get(X);
                        num_gold = temp.poll();
                        // if list is empty, remove entry
                        if(temp.peek()==null)
                            quests.remove(X);
                        else
                            quests.put(X,temp);
                    }
                    else{
                        quests.put(X, new PriorityQueue<Integer>());
                        int next_highest = 0;
                        if(quests.lowerKey(X)==null){
                            quests.remove(X);
                            io.println(0);
                            break;
                        }
                        else{
                            next_highest = quests.lowerKey(X);
                            quests.remove(X);
                        }
                        while(X > 0){
                            X -= next_highest;
                            // take the list with the next highest key
                            PriorityQueue<Integer> temp = quests.get(next_highest);
                            // remove highest value
                            num_gold += temp.poll();
                            if(temp.peek()==null){  //if no more entries, remove key map
                                quests.remove(next_highest);
                            }
                            else //otherwise put back leftover map
                                quests.put(next_highest, temp);

                            // if value of remaining energy not found in tree
                            if(!quests.containsKey(X)){
                                // set placeholder
                                quests.put(X, new PriorityQueue<Integer>());
                                //check if there is any lower key. if none, exit
                                if(quests.lowerKey(X)==null){
                                    quests.remove(X);
                                    break;
                                }
                                //if there is, set as the next val
                                next_highest = quests.lowerKey(X);
                                quests.remove(X); //remove placeholder
                            }
                            else //if already has X, set X as the next key
                                next_highest = X;
                        }
                    }
                    io.println(num_gold);
                    break;
                default:
                    break;
            }
        }
        io.close();    
    }
}

