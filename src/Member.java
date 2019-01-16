public class Member {
    private String status;
    private int daysIll;
    private int x;
    private int y;

    public Member(int x, int y) {
        status = StatusType.HEALTHY;
        daysIll = 0;
        this.x = x;
        this.y = y;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public int getDaysIll() {
        return this.daysIll;
    }

    public void incremenetDaysIll() {
        this.daysIll++;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
