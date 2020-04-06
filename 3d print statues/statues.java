import java.util.*;
import java.io.*;

public class statues{
	public static void main(String[] args)throws Exception{

		InputStreamReader r = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(r);
		int input = Integer.parseInt(br.readLine());

		int count = 1;
		int maxStat = 1;
		do{
			if(maxStat >= input)
				break;
			maxStat*=2;
			count++;
		} while(true);
		System.out.println(count);
	}
}