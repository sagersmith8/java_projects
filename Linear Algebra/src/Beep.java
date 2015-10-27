/**
 * Created by Sage on 8/29/2014.
 */
public class Beep {
    static int x = 0;

    public static void addOne(){
        x+=100;
        x/=2;
    }

    public static void main(String[] args) {
        System.out.println(""+x);
        addOne();
        System.out.println(""+x);
    }
}