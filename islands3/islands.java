import java.util.*;
import java.io.*;  

public class islands{

    public static void main(String[] args){

        Kattio io = new Kattio(System.in, System.out);

        int num_row = io.getInt();
        int num_col = io.getInt();
        int islands = 0;

        int num_grids = num_row*num_col;
        char[][] grids = new char[num_row][num_col];
        int[] land = new int[num_grids];
        int[][] adjMatrix = new int[num_grids][num_grids];

        //get grids
        for(int i = 0; i < num_row; i++){
            String temp_grids = io.getWord();
            for(int j = 0; j < num_col; j++){
                grids[i][j] = temp_grids.charAt(j);
                int index = i*num_col + j;
                if(temp_grids.charAt(j)=='L')
                    land[index]=1;
            }
        }

        for(int i = 0; i < num_grids; i ++){ //set neighbours 
            int row = i/num_col;
            int col = i - (row)*num_col;
            if(grids[row][col]!='W'){
                int neigh_row = 0;
                int neigh_col = 0;
                int index = 0;
                //only add for those within boundary
                for(int j = 0; j < 4; j ++){
                    switch(j){
                        case 0: //top
                            neigh_row = row - 1;
                            neigh_col = col;
                            break;
                        case 1: //right
                            neigh_row = row;
                            neigh_col = col + 1;
                            break;
                        case 2: //bottom
                            neigh_row = row + 1;
                            neigh_col = col;
                            break;
                        case 3: //left
                            neigh_row = row;
                            neigh_col = col - 1;
                            break;
                        default:
                            break;
                    }
                    if((neigh_row >= 0 && neigh_row < num_row) && (neigh_col >= 0 && neigh_col < num_col)){
                        index = (neigh_row)*num_col + neigh_col;
                        if(grids[neigh_row][neigh_col] == 'L' || grids[neigh_row][neigh_col] == 'C')
                            adjMatrix[i][index] = 1;
                        }
                    }
            }
        }
        // for(int i = 0; i < num_grids; i ++){
        //     for(int j= 0 ;j<num_grids;j++){
        //         io.print(adjMatrix[i][j] + " ");
        //     }
        //     io.println("");
        // }

        UnionFind components = new UnionFind(num_grids);
        for(int i = 0; i < num_grids; i ++){
            for(int j = 0; j <num_grids; j++){
                if(adjMatrix[i][j]==1){
                    if(!components.isSameSet(i,j)){
                        components.unionSet(i,j);
                    }
                }
            }
        }
        HashSet<Integer> sets = new HashSet<Integer>();
        for(int i = 0; i < num_grids; i ++){
            if(land[i] == 1){
                sets.add(components.findSet(i));
            }
        }
        io.println(sets.size());
        io.close();    
    }
}

class UnionFind {                                              
  public int[] p;
  public int[] rank;
  public int numSets;

  public UnionFind(int N) {
    p = new int[N];
    rank = new int[N];
    numSets = N;
    for (int i = 0; i < N; i++) {
      p[i] = i;
      rank[i] = 0;
    }
  }

  public int findSet(int i) { 
    if (p[i] == i) return i;
    else {
      p[i] = findSet(p[i]);
      return p[i]; 
    } 
  }

  public Boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }

  public void unionSet(int i, int j) { 
    if (!isSameSet(i, j)) { 
      numSets--; 
      int x = findSet(i), y = findSet(j);
      // rank is used to keep the tree short
      if (rank[x] > rank[y]) 
        p[y] = x;
      else { 
        p[x] = y;
        if (rank[x] == rank[y]) 
          rank[y] = rank[y]+1; 
      } 
    } 
  }
}