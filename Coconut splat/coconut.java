
import java.util.*;
import java.io.*;  

public class coconut{

    public static void main(String[] args){

        Kattio io = new Kattio(System.in, System.out);

        // read input
        int syll = io.getInt();
        int numPeople = io.getInt(); 
        int curr = 0;    //current pointer to hand
        int size = numPeople; //holds no of hands in game
        int steps = 0; // number of steps to move in that interation
        int tempStat = 0;
        
        Player temp;

        LinkedList<Player> game = new LinkedList<Player>(); 

        for(int i = 0; i < numPeople; i++){
            Player newPlayer = new Player(i+1, false, 1);
            game.add(newPlayer);
        }

        while(size > 1){
            /* DEBUG
            for(int i =0; i<size; i ++){
                io.print(game.get(i).getPlayerNum() + "." + game.get(i).getHandStat() +  " ");
            }
            io.println("");
            */
            curr = curr + (syll % size - 1); //calculate no. of steps we need to move that game
            //only possible too move 1 back. i.e. to back of queue
            if(curr < 0)
                curr = size - 1;
            if ( curr > size - 1 )
                curr = curr - size;
            temp = game.get(curr);
            //check broken or not
            //if not yet broken, set to broken and insert another 'hand'
            if(temp.getBroken() == false){
                temp.setBroken();
                Player newPlayer = new Player(temp.getPlayerNum(), true, 1);
                game.add(curr,newPlayer);
                size++;
                // curr remains on the initial hand
            }
            else{
                //if already broken, check stat
                tempStat = temp.getHandStat();
                //half coconut
                if(tempStat == 1){
                    temp.dropStat();
                    curr++;
                    if(curr > size - 1) //move to front
                        curr = 0;
                }
                //turnover coconut. remove from game
                else{
                    game.remove(curr);
                    size --;
                }
            }
        }

        io.println(game.getFirst().getPlayerNum());
        io.close();
    }
}

class Player{

    int playerNum;
    boolean broken;
    int handStat;

    public Player(int num, boolean broke, int stat){
        playerNum = num;
        broken = broke;
        handStat = stat;
    }

    public int getPlayerNum(){
        return playerNum;
    }
    public boolean getBroken(){
        return broken;
    }

    public int getHandStat(){
        return handStat;
    }

    public void dropStat(){
        handStat = handStat-1;
    }

    public void setBroken(){
        broken = true;
    }
}


