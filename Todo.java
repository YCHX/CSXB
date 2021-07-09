public class Todo {
    boolean deleted;
    String title;
    String memo;
    String tag;
    String area;
    boolean checked;
    String[] deadline;
    

    public Todo(String title){
        this.title = title;
        this.memo = "none";
        this.tag = "none";
        this.area = "none";
        this.checked = false;
        this.deadline = new String[3];
    }

    public void setDeadline(String yyyy,String mm,String dd){
        this.deadline[0] = yyyy;
        this.deadline[1] = mm;
        this.deadline[2] = dd;
    }

    public void changeMemo(String memo){
        this.memo = memo;
    }

    public void changeTag(String tag){
        this.tag = tag;
    }

    public void changeArea(String area){
        this.area = area;
    }

    public void check(){
        checked = true;
    }

    public void uncheck(){
        checked = false;
    }

    public void delete(){
        deleted = true;
    }

    public String printTodo(){
        String s = "";
        if (!deleted){
            if (checked == true){
                System.out.print("√ ");
                s += "√ ";
            }else{
                System.out.print("□ ");
                s += "□ ";
            }
            System.out.print(title+" ");
            s += title+" ";
            if (!memo.equals("none")){
                System.out.print(memo+" ");
                s += memo+" ";
            }
            if (!tag.equals("none")){
                System.out.print(tag+" ");
                s += tag+" ";
            }
            if (!area.equals("none")){
                System.out.print(area+" ");
                s += area+" ";
            }
            System.out.println();
        }
        return s;
    }

    public String writeSet(){
        String s = "";
        if (deleted){
            s += "deleted,";
        }else{
            s += "live,";
        }
        s += title+",";
        s += memo+",";
        s += tag+",";
        s += area+",";
        if (checked){
            s += "true";
        }else{
            s += "false";
        }
        



        return s;
    }
}
