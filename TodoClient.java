import java.net.*;
import java.util.Scanner;
import java.io.*;

public class TodoClient {
    public static void main(String[] args) throws IOException {
        InetAddress addr = InetAddress.getByName("localhost");
        System.out.println("addr = " + addr);
        Socket socket = new Socket(addr, Integer.parseInt(args[0]));
        try {
            System.out.println("socket = " + socket);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            Scanner sc = new Scanner(System.in);
            while (true){
                System.out.print(">");
                String tmp = sc.nextLine();
                if (tmp.equals("bye")){
                    break;
                }
                if (tmp.equals("")){
                    continue;
                }
                out.println(tmp);
                
                    String str = in.readLine();
                    System.out.println(str);
            }
        } finally {
            socket.close();
        }
    }

}
