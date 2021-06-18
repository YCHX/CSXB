public class Todo {
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
}
