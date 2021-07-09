import java.io.*;
import java.util.Scanner;


public class TodoLocal{
    public static void main(String[] args){
        Todo[] t = new Todo[255];
        t[0] = new Todo("one");
        t[1] = new Todo("two");
        t[2] = new Todo("three");
        int i = 3;
        Scanner sc = new Scanner(System.in);
        UserLists us = new UserLists("ad");
        us.tls = t;
        us.writeFile();
        while (true){
            String s = sc.nextLine();
            String[] tmp = s.split(" ");
            if (tmp[0].equals("bye")) break;
            switch (tmp[0]){
                case "add":
                    t[i] = new Todo(tmp[1]);
                    us.tls = t;
                    us.writeFile();
                break;
                case "delete":
                    us.delete(tmp[1]);
                    us.writeFile();
                break;
                case "show":
                us.writeFile();
                break;
                default:
                break;
            }
        }
        // t[0].printTodo();
        // t[0].check();
        // t[0].changeTag("test");
        // t[1].printTodo();
        // t[0].printTodo();
        
    }
}
