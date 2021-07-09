import java.io.*;
import java.util.Scanner;
import java.net.*;


public class TodoServer {
    private static int PORT = 8080;
    

    public static void main(String[] args) throws IOException{
        var s = new ServerSocket(PORT);
        System.out.println("Started: " + s);
        

        try {
            var socket = s.accept();
            try {
                
                    
                System.out.println("Connection accepted: " + socket);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                var out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                while (true){
                    String tmp = in.readLine();
                    if (tmp.equals("bye")) break;
                    
                    if (tmp.equals("")){
                        out.println("Please input something");
                        continue;
                    }
                    String[] tmps = tmp.split(" ");
                    out.println("OK");
                    
                    if (tmps[0].equals("login")){
                        if (tmps.length >1){
                            if (!tmps[1].equals("")){
                                String pass = in.readLine();
                                LoginMap lm = new LoginMap();

                                byte[] bs = pass.getBytes();
                                byte t;
                                if (bs.length % 2 == 0){
                                    for (int i = 0; i < bs.length; i+=2){
                                        t = bs[i];
                                        bs[i] = bs[i+1];
                                        bs[i+1] = t;
                                    }
                                }else{
                                    for (int i = 1; i < bs.length; i+=2){
                                        t = bs[i];
                                        bs[i] = bs[i+1];
                                        bs[i+1] = t;
                                    }
                                }
                                pass = new String(bs);

                                try {
                                    if (lm.checkin(tmps[1], pass) == 1){
                                        out.println("Login Success");
                                        String usn = tmps[1];
                                        while (true){
                                            tmp = in.readLine();
                                            if (tmp.equals("bye")) break;
                                            if (tmp.equals("")){
                                                out.println("Please input something");
                                                continue;
                                            }
                                            out.println("OK");
                                            String[] tmps2 = tmp.split(" ");
                                            Todo[] td = new Todo[255];
                                            UserLists us = new UserLists(usn);
                                            td = us.loadline();
                                            switch (tmps2[0]){
                                                case "show":
                                                    for (int i = 0; i < td.length;i++){
                                                        if (td[i] == null) break;
                                                        out.println(td[i].printTodo());
                                                    }
                                                break;
                                                case "delete":
                                                    us.delete(tmps2[1]);
                                                    us.writeFile();
                                                break;
                                                case "add":
                                                    us.add(tmps2[1]);
                                                    us.writeFile();
                                                break;
                                                default:
                                                break;
                                            }
                                        }
                                            

                                    }else if (lm.checkin(tmps[1], pass) == 2){
                                        out.println("Login failed");
                                    }else{
                                        out.println("User not exists");
                                        lm.reg(tmps[1], pass);
                                    }
                                } catch (IOException e) {
                                    
                                    e.printStackTrace();
                                }
                            }
                        }
                    }else{
                        out.println("Please login with a username");
                    }
                }
            }
            finally {
                    
                socket.close();
            }
        }finally{
            s.close();
        }
        
    }
}
