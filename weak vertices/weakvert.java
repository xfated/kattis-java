import java.util.*;
import java.io.*;  

public class weakvert{

    public static void main(String[] args){

        Kattio io = new Kattio(System.in, System.out);

        int num_vertice = io.getInt();

        while(num_vertice != -1){

            int[][] adjMatrix = new int[num_vertice][num_vertice];
            HashSet<Integer> strong = new HashSet<Integer>();
            
            for(int i = 0; i < num_vertice; i ++){
                for(int j = 0; j < num_vertice; j ++){
                    adjMatrix[i][j] = io.getInt();
                }    
            }

            for(int i = 0; i < num_vertice; i ++){
                // i not yet declared a strong vertex
                if(!strong.contains(i)){
                    //iterate through i's neighbours
                    for(int j = 0; j < num_vertice && j != i; j ++){
                        if(adjMatrix[i][j] == 1){ //j is a neighbour of i
                            //go through all of j's neighbours excl i
                            for(int k = 0; k < num_vertice && k != j; k++){
                                if(adjMatrix[j][k] == 1) { // j's neighbour == k
                                    if(adjMatrix[k][i] == 1){ //k has an edge to i
                                        strong.add(i);
                                        strong.add(j);
                                        strong.add(k);
                                    }
                                }
                            }
                        }
                    }        
                }
            }

            boolean first = true;
            for(int i = 0; i < num_vertice; i ++){
                if(!strong.contains(i)){
                    if(first){
                        io.print(i);
                        first = false;
                    }
                    else
                        io.print(" " + i);
                }
            }

            num_vertice = io.getInt();
            if(num_vertice != -1){
                io.println("");
            }
        }
        io.close();    
    }
}
