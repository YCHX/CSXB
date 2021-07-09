import java.io.*;



public class UserLists {
    private static final int BUF_SIZE = 255;
    String username;
    Todo[] tls = new Todo[BUF_SIZE];

    public UserLists(String username){
        File f = new File("./data/"+username+".csv");
        if (f.exists()){
            this.username = username;
        }else{
            try{
                f.createNewFile();
                this.username = username;
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    public int delete(String title){
        for (int i = 0; i < tls.length; i++){
            if (tls[i] == null) break;
            if (tls[i].title.equals(title)){
                tls[i].delete();
                return 0;
            }
        }
        return 1;
    }

    public void add(String title){
        tls[tls.length] = new Todo(title);
    }

    public Todo[] loadline(){
        String line = null;
            var tl = new Todo[BUF_SIZE];
            var i = 0;
        try{
            BufferedReader reader = new BufferedReader(new FileReader("./data/"+username+".csv"));
            while ((line = reader.readLine()) != null){
                String[] buf = line.split(",",0);
                if (buf[0].equals("deleted")){
                    continue;
                }
                tl[i] = new Todo(buf[1]);
                if (!buf[2].equals("none")) tl[i].changeMemo(buf[1]);
                if (!buf[3].equals("none")) tl[i].changeTag(buf[2]);
                if (!buf[4].equals("none")) tl[i].changeArea(buf[3]);
                if (buf[5].equals("true")) { tl[i].check(); } else { tl[i].uncheck(); }
                tl[i].printTodo();
                i++;
            }
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        tls = tl;
    return tl;
    }

    public void writeFile(){
        int length = tls.length;
        int i = 0;
        try{
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("./data/"+username+".csv")));
            while (i < length){
                if (tls[i] == null) break;
                String s = tls[i].writeSet();
                System.out.println(s);
                pw.println(s);
                i++;
            }
            pw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void writeFile(Todo tl){
        try{
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("./data/"+username+".csv")));
            
                String s = tl.writeSet();
                System.out.println(s);
                pw.println(s);
                
            
            pw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
