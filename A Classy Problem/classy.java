import java.util.*;
import java.io.*;  

public class classy{

    public static void main(String[] args){

        Kattio io = new Kattio(System.in, System.out);

        int cases = io.getInt();
        int members = 0; // number of members in each case

        String tempName = "";
        ClassComparator classComp = new ClassComparator();
        // handle each case 
        for(int a = 0; a < cases; a ++){
            members = io.getInt();

            Person[] group = new Person[members];
            /* get details for each member */
            for(int i = 0; i < members; i ++){
                Person temp = new Person();
                // omit the ':'
                tempName = io.getWord();
                temp.setName(tempName.substring(0,tempName.length()-1));
                temp.setCla(io.getWord());
                group[i] = temp;

                io.getWord(); //we don't need the string "class"
            }

            Arrays.sort(group, classComp);


            for(int i = 0; i < members; i ++)
                io.println(group[i].getName());
            for(int i = 0; i < 30; i ++)
                io.print("=");
            io.println("");
        }

        io.close();
    }

    
}
class Person{

        public String name;
        public String _class;

        public void setName(String personName){
            name = personName;
        }

        public void setCla(String personClass){
            _class = personClass;
        }

        public String getCla(){
            return _class;
        }

        public String getName(){
            return name;
        }
    }

class ClassComparator implements Comparator<Person>{
    public int compare(Person person1, Person person2){
        String[] p1Class = person1.getCla().split("-");
        String[] p2Class = person2.getCla().split("-");

        //identify the class with less items
        int shortestClassLength = 0;
        int shortestClass = 0;
        if(p1Class.length < p2Class.length){
            shortestClassLength = p1Class.length;
            shortestClass = 1;
        }
        else{
            shortestClassLength = p2Class.length;
            shortestClass = 2;
        }

        // reverse order of class
        Collections.reverse(Arrays.asList(p1Class));
        Collections.reverse(Arrays.asList(p2Class));

        for(int i = 0; i < shortestClassLength; i++){
            // case when p1Class is higher
            if((p1Class[i].equals("upper") && p2Class[i].equals("middle")) ||
               (p1Class[i].equals("middle") && p2Class[i].equals("lower")) ||
               (p1Class[i].equals("upper") && p2Class[i].equals("lower")))
                return -1;
            //case when p1Class is lower
            else if((p2Class[i].equals("upper") && p1Class[i].equals("middle")) ||
                    (p2Class[i].equals("middle") && p1Class[i].equals("lower")) ||
                    (p2Class[i].equals("upper") && p1Class[i].equals("lower")))
                return 1;
        }
        /* will reach here if both classes are t
he same up till length of shorter class */
        /* so left the checking of longer class */
        if(shortestClass == 1){
            // handling longer class (p2)
            for(int i = shortestClassLength; i < p2Class.length; i ++){
                if(p2Class[i].equals("lower"))
                    return -1;
                else if(p2Class[i].equals("upper")){    
                   return 1;
                }
            }
        }
        else{
            for(int i = shortestClassLength; i < p1Class.length; i ++){
                if(p1Class[i].equals("lower"))
                    return 1;
                else if(p1Class[i].equals("upper")){    
                   return -1;
                }
            }
        }
        // if same class, compare alphabetical order
        return person1.getName().compareTo(person2.getName());
    }

    public boolean equals(Object obj){
        return this == obj;
    }
}

