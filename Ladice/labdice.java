import java.util.*;
import java.io.*;  

public class labdice{

    public static void main(String[] args){

        Kattio io = new Kattio(System.in, System.out);

        int num_items = io.getInt();
        int num_drawers = io.getInt();
        int drawer1 = 0;
        int drawer2 = 0;
        UnionFind drawers = new UnionFind(num_drawers + 1);
        int[] filled_drawers = new int[num_drawers + 1];
        Arrays.fill(filled_drawers,1);

        for(int i = 0; i < num_items; i ++){
            drawer1 = io.getInt();
            drawer2 = io.getInt();
            //if not same set, join and get num_slots left 
            if(!drawers.isSameSet(drawer1,drawer2)){
                int total = filled_drawers[drawers.findSet(drawer1)] + filled_drawers[drawers.findSet(drawer2)];
                drawers.unionSet(drawer1,drawer2);
                filled_drawers[drawers.findSet(drawer1)] = total;
            }
            int current_val = filled_drawers[drawers.findSet(drawer1)];
            //if still have slot, "taken" by current one and minus 1 slot 
            if(current_val>0){
                filled_drawers[drawers.findSet(drawer1)] = current_val - 1;
                io.println("LADICA");
            }
            else
                io.println("SMECE");
        }
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

  public int numDisjointSets() { return numSets; }
}
