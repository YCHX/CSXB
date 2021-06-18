import java.io.*;



public class UserLists {
    private static final int BUF_SIZE = 255;
    String username;
    

    public UserLists(String username){
        File f = new File("./data/"+username+",csv");
        if (f.exists()){
            this.username = username;
        }else{
            try{
                f.createNewFile();
                this.username = username;
            }catch(IOException e){

            }
        }
    }

    public Todo[] loadline(){
        String line = null;
            Todo[] tl = new Todo[BUF_SIZE];
            int i = 0;
        try{
            BufferedReader reader = new BufferedReader(new FileReader("./data/"+username+".csv"));
            
            
            while ((line = reader.readLine()) != null){
                String[] buf = line.split(",",0);
                tl[i] = new Todo(buf[0]);
                if (!buf[1].equals("none")) buf[1] = tl[i].memo;
                if (!buf[2].equals("none")) buf[2] = tl[i].tag;
                if (!buf[3].equals("none")) buf[3] = tl[i].area;
                if (!buf[4].equals("true")) { tl[i].checked = true; } else { tl[i].checked = false; }
                i++;
            }
            
        reader.close();
        

    }catch(IOException e){
        e.printStackTrace();
    }
    return tl;
    }

}
