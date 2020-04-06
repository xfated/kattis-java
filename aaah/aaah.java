import java.util.*;

public class aaah{
	public static void main(String[] args){

		Scanner scan = new Scanner(System.in);
		String input1 = scan.nextLine();
		String input2 = scan.nextLine();

		if (input1.length() >= input2.length())
			System.out.println("go");
		else
			System.out.println("no");
	}
}