import java.util.*;
import java.io.*;
import java.lang.*;  

public class gcpc{

    public static void main(String[] args) throws Exception {

        Kattio io = new Kattio(System.in, System.out);

        int num_teams = io.getInt();
        int num_events = io.getInt();
        BST teamBST = new BST();
        teamBST.insert(1,0,0);

        for(int i = 0; i < num_events; i ++){
          int t = io.getInt();
          int penalty = io.getInt();
          BSTVertex team = teamBST.search(t);
          // if team is already inside, delete current one and update
          if(team != null){
            OneTeam temp = teamBST.teams.get(t);
            teamBST.delete(t);
            teamBST.insert(t, temp.solved += 1, temp.penalty += penalty);
          }
          // else insert a new 'team' with solved solutions = 1
          else{
            teamBST.insert(t,1,penalty);
          }
          io.println(teamBST.getRank(1));
        }
        // teamBST.serialize(teamBST.root);
        io.close();
    }
}

// holds each team's details
class OneTeam {
  OneTeam(int v, int s, int p){
    teamNo = v;
    solved = s;
    penalty = p;
  }
  public int teamNo;
  public int penalty;
  public int solved;
}

// each vertex should be unique. so store solved, penalty pair as a vertex
// num_teams == number of teams with that score
class BSTVertex {
  BSTVertex(int s, int p) { 
    solved = s; 
    penalty = p; 
    parent = left = right = null; 
    height = 0;
    size = 1;
    num_team = 1;
  }
  
  public BSTVertex parent, left, right;
  public int num_team;
  public int size;
  public int height;
  public int penalty;
  public int solved;
}

// record all the existing teams in the hashmap, to access their respective score/penalty
class BST {
  public BSTVertex root;
  public HashMap<Integer,OneTeam> teams;
  public BST() { root = null; teams = new HashMap<Integer,OneTeam>(); }

  // public method called to search for a value v. 
  // returns the vertex if found else null
  public BSTVertex search(int v) {
    if(teams.containsKey(v)){
      BSTVertex res = search(root, teams.get(v).solved, teams.get(v).penalty);
      return res;
    }
    else{
      return null;
    }
  }

  // helper method to perform search
  // recursively search until can find the relevant vertex.
  public BSTVertex search(BSTVertex T, int s, int p) {
         if (T == null)  return null;                     // not found
    else if (T.solved == s && T.penalty == p) return T;                        // found
    else if (T.solved > s)  { 
      return search(T.right, s, p);       // search to the right
    }
    else if (T.solved == s){
      if(T.penalty < p){
        return search(T.right, s, p);
      }
      else {
        return search(T.left, s, p);
      }
    }
    else                 
      return search(T.left, s, p);        // search to the left
  }
  
  public int getRank(int v) {
    int rank = getRank(root, teams.get(v).solved, teams.get(v).penalty);
    return rank;
  }

  public int getRank(BSTVertex T, int s, int p){
    if (T.solved == s && T.penalty == p){ //if is root, return rank of left subtree + 1
      if(T.left == null)
        return 1;
      else 
        return T.left.size + 1;
    }

    // for current method of storing, 1 vertex may represent multiple teams with the same score
    // so use num_teams instead of 1 as size of vertex
    else if (T.solved > s){ //if in rightsubtree, return rank in rightsubtree + rank of root
      if(T.left == null)
        return T.num_team + getRank(T.right, s, p);
      else  
        return T.left.size + T.num_team + getRank(T.right, s, p);
    }
    else if (T.solved == s){ 
      if(T.penalty < p){ //right subtree
        if(T.left == null)
          return T.num_team + getRank(T.right, s, p);
        else {
          return T.left.size + T.num_team + getRank(T.right, s, p);
        }
      }
      else //left subtree, return its rank in left subtree
        return getRank(T.left, s, p);
    }
    else{ // left subtree
        return getRank(T.left, s, p);
    }
  }

  // public method called to find Minimum key value in BST
  public BSTVertex findMin() { return findMin(root); }

  public BSTVertex findMin(BSTVertex T) {
  	//T.left == null means at leftmost vertex
    if (T.left == null) return T;                    // this is the min
    else                return findMin(T.left);           // go to the left
  }
  
  // public method to find successor to given value v in BST.
  // in this context, successor --> the team that is next in rank
  public BSTVertex successor(int s, int p) { 
    BSTVertex vPos = search(root, s, p);
    return vPos == null ? null : successor(vPos);
  }

  // helper recursive method to find successor to for a given vertex T in BST
  public BSTVertex successor(BSTVertex T) {
    if (T.right != null)                       // this subtree has right subtree
      // successor is min in right subtree
      return findMin(T.right);  // the successor is the minimum of right subtree
    else {
      BSTVertex par = T.parent;
      BSTVertex cur = T;
      // if par(ent) is not root and cur(rent) is its right children
      // search until reach root or reach a right turn. i.e. is the left child of parent 
      while ((par != null) && (cur == par.right)) {
        cur = par;                                         // continue moving up
        par = cur.parent;
      }
      return par == null ? null : par;           // this is the successor of T
    }
  }


  // public method called to insert a new key with value v into BST
  public void insert(int v, int s, int p) { 
      root = insert(root, v, s, p);
  }

  // helper recursive method to perform insertion of new vertex into BST
  public BSTVertex insert(BSTVertex T, int v, int s, int p) {
    if (T == null) {
      OneTeam tempTeam = new OneTeam(v,s,p);
      //store details in a hashmap
      teams.put(v, tempTeam);

      // store score/penalty pair in the tree
      BSTVertex temp = new BSTVertex(s,p);
      return temp;          // insertion point is found
    }
    // recursively iterate until find the right spot
    // then will return a new node (from above)
    // set the parent
    if(T.solved == s && T.penalty == p){
      // team already exists, just update its details
      if(teams.containsKey(v)){
        OneTeam temp = teams.get(v);
        temp.solved ++;
        temp.penalty += p;
        teams.put(v,temp);
      }
      // team doesnt exist but have another team with same score/penalty
      // add new team to hashmap, increase num_team by 1
      else{
        OneTeam temp = new OneTeam(v,s,p);
        teams.put(v,temp);
        T.num_team++;
      }
    }
    else if (T.solved > s) {                                      // search to the right
      T.right = insert(T.right, v, s, p);
      T.right.parent = T;
    }
    else if(T.solved == s){ 
      if(T.penalty < p){ //if larger penalty, put in right subtree
        T.right = insert(T.right, v, s, p);
        T.right.parent = T;   
      }
      else{
        T.left = insert(T.left, v, s, p);
        T.left.parent = T;     
      }
    }
    else {                                                 // search to the left
      T.left = insert(T.left, v, s, p);
      T.left.parent = T;
    }

    //compute height and size before any balancing
    computeSizeHeight(T);
    if(T.parent!=null)
          computeSizeHeight(T.parent);

    if(balanceFactor(T) == 2){
      if(balanceFactor(T.left) == 0 || balanceFactor(T.left) == 1){
        T = rotateRight(T);
      }
      else if(balanceFactor(T.left) == -1){
        T.left = rotateLeft(T.left);
        T = rotateRight(T);
      }
    }
    else if (balanceFactor(T) == -2){
        if(balanceFactor(T.right) == -1 || balanceFactor(T.right) == 0)
          T = rotateLeft(T);
        else if(balanceFactor(T.right) == 1){
          T.right = rotateRight(T.right);
          T = rotateLeft(T);
        }
    }

    return T;                                          // return the updated BST
  }  

  // public method to delete a vertex containing key with value v from BST
  // Starts delete from root
  public void delete(int v) {
    root = delete(root, v, teams.get(v).solved, teams.get(v).penalty);
    teams.remove(v);
  }

  // helper recursive method to perform deletion 
  public BSTVertex delete(BSTVertex T, int v, int s, int p) {
    if (T == null)  return T;              // cannot find the item to be deleted

    if(T.solved == s && T.penalty == p){
        if(T.num_team > 1){
          T.num_team--;                                            // this is the node to be deleted
        }
        // no child, just delete
        else if (T.left == null && T.right == null){                   // this is a leaf
          return null;     
        }                                // simply erase this node
        // 1 child (right child), connect parent to child.
        else if (T.left == null && T.right != null) {   // only one child at right        
          T.right.parent = T.parent;
          T = T.right;                                                 // bypass T
        }
        // 1 child (left child)
        else if (T.left != null && T.right == null) {    // only one child at left        
          T.left.parent = T.parent;
          T = T.left;                                                  // bypass T       
        }
        // 2 child, find successor and replace T with successor. 
        else {                                 // has two children, find successor
          BSTVertex temp = successor(s, p);
          T.right = delete(T.right, temp.solved, temp.penalty);      // delete the old successorV
          //set pointers for temp
          temp.left = T.left;
          temp.right = T.right;
          temp.parent = T.parent;
          //simply replace T with temp
          T = temp; //replace T with its successor
        }
        
        //compute height/size before balancing
        computeSizeHeight(T);
        //compute height and size for parent
        if(T.parent!=null)
          computeSizeHeight(T.parent);

        if(T!=null){
          if(balanceFactor(T) == 2){
            if(balanceFactor(T.left) == 0 || balanceFactor(T.left) == 1)
              T = rotateRight(T);
            else if(balanceFactor(T.left) == -1){
              T.left = rotateLeft(T.left);
              T = rotateRight(T);
            }
          }
          else if (balanceFactor(T) == -2){
            if(balanceFactor(T.right) == -1 || balanceFactor(T.right) == 0)
              T = rotateLeft(T);
            else if(balanceFactor(T.right) == 1){
              T.right = rotateRight(T.right);
              T = rotateLeft(T);
            }
          }
        }

    }
    else if (T.solved > s)                                    // search to the right
      T.right = delete(T.right, v, s, p);
    // if larger than V, delete from right subtree
    else if(T.solved == s){
      if(T.penalty < p)  // same s but more penalty
        T.right = delete(T.right, v, s, p);
      else
        T.left = delete(T.left, v, s, p);
    }
    else                               // search to the left
      T.left = delete(T.left, v, s, p);                                         // return the updated BST

    computeSizeHeight(T);
    if(T.parent!=null)
          computeSizeHeight(T.parent);

    return T;
  }

  // helper recursive method to perform deletion for entire node 
  // used for deletion of successor for cases with 2 children
  // called in above 'delete' function.
  public BSTVertex delete(BSTVertex T, int s, int p) {
    if (T == null)  return T;              // cannot find the item to be deleted

    if(T.solved == s && T.penalty == p){
        if (T.left == null && T.right == null)                   // this is a leaf
          return null;                                      // simply erase this node
        // 1 child (right child), connect parent to child.
        else if (T.left == null && T.right != null) {   // only one child at right        
          T.right.parent = T.parent;
          T = T.right;                                                 // bypass T
        }
        // 1 child (left child)
        else if (T.left != null && T.right == null) {    // only one child at left        
          T.left.parent = T.parent;
          T = T.left;                                                  // bypass T       
        }
        // 2 child, find successor and replace T with successor. 
        else {                                 // has two children, find successor
          BSTVertex temp = successor(s, p);
          T.right = delete(T.right, temp.solved, temp.penalty);      // delete the old successorV
          //set pointers for temp
          temp.left = T.left;
          temp.right = T.right;
          temp.parent = T.parent;
          //simply replace T with temp
          T = temp; //replace T with its successor
        }
        
        computeSizeHeight(T);
        //compute height and size for parent
        if(T.parent!=null)
          computeSizeHeight(T.parent);

        // BSTVertex P = T.parent;
        if(T!=null){
          if(balanceFactor(T) == 2){
            if(balanceFactor(T.left) == 0 || balanceFactor(T.left) == 1)
              T = rotateRight(T);
            else if(balanceFactor(T.left) == 01){
              T.left = rotateLeft(T.left);
              T = rotateRight(T);
            }
          }
          else if (balanceFactor(T) == -2){
            if(balanceFactor(T.right) == -1 || balanceFactor(T.right) == 0)
              T = rotateLeft(T);
            else if(balanceFactor(T.right) == 1){
              T.right = rotateRight(T.right);
              T = rotateLeft(T);
            }
          }
        }

    }
    else if (T.solved > s)                                    // search to the right
      T.right = delete(T.right, s, p);
    // if larger than V, delete from right subtree
    else if(T.solved == s){
      if(T.penalty < p)  // same s but more penalty
        T.right = delete(T.right, s, p);
      else
        T.left = delete(T.left, s, p);
    }
    else                               // search to the left
      T.left = delete(T.left, s, p);                                         // return the updated BST

    computeSizeHeight(T);
    if(T.parent!=null)
          computeSizeHeight(T.parent);
    return T;
  }


  public int balanceFactor(BSTVertex T){
    if(T.left == null && T.right == null){
      return 0;
    }
    else if (T.left == null){
      return (- (T.right.height+1));
    }
    else if (T.right == null){
      return (T.left.height+1);
    }
    else{
      return (T.left.height - T.right.height);
    }
  }

  public BSTVertex rotateLeft(BSTVertex T){
    BSTVertex w = T.right;
    w.parent = T.parent;
    T.parent = w;
    T.right = w.left;
    if(w.left!=null)
      w.left.parent = T;
    w.left = T;
    computeSizeHeight(T); //Have to compute T first! since T is a child of w
    computeSizeHeight(w);
    return w; //w has taken the place of T
  }

  public BSTVertex rotateRight(BSTVertex T){
    BSTVertex w = T.left;
    w.parent = T.parent;
    T.parent = w;
    T.left = w.right;
    if(w.right!=null)
      w.right.parent = T;
    w.right = T;
    computeSizeHeight(T); //Have to compute T first! since T is a child of w
    computeSizeHeight(w);
    return w;
  }

  // function to compute the stuffs since this code is used multiple times
  public void computeSizeHeight(BSTVertex T){
    //compute height and size
    if(T.left == null && T.right == null){
      T.height = 0;
      T.size = T.num_team;
    }
    else if (T.left == null){
      T.height = T.right.height + 1;
      T.size = T.right.size + T.num_team;
    }
    else if (T.right == null){
      T.height = T.left.height + 1;
      T.size = T.left.size + T.num_team;
    }
    else{
      T.height = Math.max(T.left.height,T.right.height) + 1;
      T.size = T.left.size + T.right.size + T.num_team;
    } 
  }


  // serializes the binary tree and prints it out. initialize with the root
  public static void serialize(BSTVertex root){
    
    if(root == null){
      System.out.print(" |-1| ");
      return;
    }
    
    System.out.print(" |" + root.solved + " (" + root.penalty + ")|");
    
    serialize(root.left);
    serialize(root.right);
  }
}