public class DATA {
    private String info;
    private int count;


    public DATA(String info, int count){
        this.info = info;
        this.count = count;
    }

    public String getInfo() {
        return info;
    }

    public int getCount() {
        return count;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void addCount() {
        this.count ++;
    }


}
