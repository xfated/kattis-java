import java.util.*;
import java.io.*;  

public class delimsoup{

    public static void main(String[] args)throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        
        Stack<String> stack = new Stack<String>();

        boolean fail = false;
        //get length of line
        int len = Integer.parseInt(br.readLine());
        String sentence = br.readLine();
        for(int i = 0; i < len; i ++){
            String val = sentence.substring(i,i+1);
            // spaces
            if(val.equals(" ")) 
                continue;
            // open bracket
            else if(val.equals("(") || val.equals("[") || val.equals("{"))
                stack.push(val);
            // close bracket
            else{
                if(stack.empty()){
                    pw.println(val + " " + i);
                    fail = true;
                    break;
                }
                String top = stack.pop();          
                if(val.equals("]"))
                    if(!top.equals("[")){
                        pw.println(val + " " + i);
                        fail = true;
                        break;
                    }
                if(val.equals(")"))
                    if(!top.equals("(")){
                        pw.println(val + " " + i);
                        fail = true;
                        break;
                    }
                if(val.equals("}"))
                    if(!top.equals("{")){
                        pw.println(val + " " + i);
                        fail = true;
                        break;
                    }
            }

        }
        if(!fail)
            pw.println("ok so far");

        pw.close();    
    }
}



