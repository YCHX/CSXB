import java.io.*;

public class LoginMap {
    private byte[] decode(byte[] b){
        byte tmp;
        if (b.length % 2 == 0){
            for (int i = 0; i < b.length; i+=2){
                tmp = b[i];
                b[i] = b[i+1];
                b[i+1] = tmp;
            }
        }else{
            for (int i = 1; i < b.length; i+=2){
                tmp = b[i];
                b[i] = b[i+1];
                b[i+1] = tmp;
            }
        }
        
        return b;
    }

    public int checkin(String username, String pass) throws IOException{
        String line = null;
        int l = pass.length();
        int result = 0;
        byte tmp;
        
        BufferedReader tb = new BufferedReader(new FileReader("./data/table"));
        while ((line = tb.readLine()) != null){
            String[] buf = line.split(" ",0);
            if (!buf[0].equals(username)){
                continue;
            }else{
                byte[] b = pass.getBytes();
                
                
                String p = new String(decode(b));
                if (p.equals(buf[1])){
                    result = 1;
                    break;
                }else{
                    result = 2;
                    break;
                }
            }
        }
        tb.close();
        return result;
        
    }

    public void reg(String username, String pass) throws IOException{
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("./data/table",true)));
        String p = new String(decode(pass.getBytes()));
        String s = username + " " + p;
        pw.println(s);
        pw.close();
    }
}
