package exact3dextract.experiment.LaserExperiment;

/**
 * Created by Sage on 4/10/2014.
 */
public class ColorPoint {
    private int x, y;
    private double brightness;

    public ColorPoint(int x, int y, double brightness){
        this.x = x;
        this.y = y;
        this.brightness = brightness;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public double getBrightness(){
        return brightness;
    }
}
