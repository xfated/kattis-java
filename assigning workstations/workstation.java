import java.util.*;
import java.io.*;  

public class workstation{

    public static void main(String[] args){

        Kattio io = new Kattio(System.in, System.out);

        int num_researchers = io.getInt();
        int num_minutes = io.getInt();
        int min_arrived = 0;
        int duration = 0;
        /*
        Researcher[] researchers = new Researcher[num_researchers];
        for(int i = 0; i < num_researchers; i ++){
        	min_arrived = io.getInt();
        	duration = io.getInt();
        	Researcher temp = new Researcher(min_arrived, duration);
        	researchers[i] = temp;
        }

        ResearcherComparator researcherComp = new ResearcherComparator();
        Arrays.sort(researchers, researcherComp);
		*/

        //insert each researcher into a priority queue -> auto sort.
        PriorityQueue<Researcher> researchers = new PriorityQueue<Researcher>(num_researchers, new ResearcherComparator()); 
		for(int i = 0; i < num_researchers; i ++){
			min_arrived = io.getInt();
        	duration = io.getInt();
        	Researcher temp = new Researcher(min_arrived, duration);
        	researchers.offer(temp);
		}

		//holds the times for next available free workstation
        PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>();

        //consider first researcher
        Researcher first = researchers.poll();
        min_arrived = first.get_arrival_time();
        duration = first.get_time_spent();

        int num_unlocks = 1;
        int next_free = min_arrived + duration;

        pQueue.offer(next_free);

        // for each researcher, get timings.
        for(int i = 1 ; i < num_researchers; i ++){
        	Researcher temp = researchers.poll();
        	min_arrived = temp.get_arrival_time();
        	duration = temp.get_time_spent();

        	next_free = pQueue.peek();

        	//remove all workstations that are already locked
        	while(next_free + num_minutes < min_arrived){
        		pQueue.poll();
        		//exit if queue is empty
        		if(pQueue.peek()==null)
        			break;
        		next_free = pQueue.peek();
        	}
        	// if next cubicle will only be free after time the researcher arrive
        	// have to unlock new one 
        	if(next_free > min_arrived || pQueue.peek()==null){
        		num_unlocks++;
        		int end_time = min_arrived + duration;
        		
        		// add the end time of this newly unlocked workstation to heap
        		pQueue.offer(end_time);
        	}
        	// next free slot can be taken up by reseacher
        	else{
        		pQueue.poll();
        		int end_time = min_arrived + duration;
        		pQueue.offer(end_time);
        	}
        	
        }

      	io.println(num_researchers-num_unlocks);		
        io.close();    
    }
}

class Researcher{
	int arrival_time;
	int time_spent;

	public Researcher(int arrival, int time){
		arrival_time = arrival;
		time_spent = time;
	}

	public int get_arrival_time(){
		return arrival_time;
	}

	public int get_time_spent(){
		return time_spent;
	}
}

class ResearcherComparator implements Comparator<Researcher>{

	public int compare(Researcher r1, Researcher r2){
		return r1.get_arrival_time() - r2.get_arrival_time();
	}
}