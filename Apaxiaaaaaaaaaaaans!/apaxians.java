import java.util.*;
import java.io.*; 

public class apaxians{

	public static void main(String[] args)throws Exception{

		InputStreamReader r = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(r);	
		String name = br.readLine();

		for (char ch = 'a'; ch <= 'z'; ch++){
			name = name.replaceAll(Character.toString(ch) + "+", Character.toString(ch));
		}
		//char[] nameArray = name.toCharArray();

		System.out.println(name);

		
	}
}
