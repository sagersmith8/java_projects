package gaussianFilter;

public class Filter {
	
	private int x,y;
	private double a;
	private double[][] v;
	
	public Filter(int dimmension, double deviationConstant) {
		x = dimmension;
		y = dimmension;
		a = deviationConstant;
		v = new double[x][y];
		for (int y = 0; y < v.length; y++) {
			for (int x = 0; x < v[0].length; x++) {
				v[x][y] = (1/(2*Math.PI*Math.pow(a,2)))*Math.pow(Math.E, -((Math.pow(x-(this.x/2), 2)+Math.pow(y-(this.y/2), 2))/(2*Math.pow(a, 2))));
				//			     (1/2PIa^2)								e^			-x^2						+y^2			/			2a^2
			}
		}
	}
	
	public double[][] getValues(){
		return v;
	}

	public int getDimmension() {
		return y;
	}

	public void setDimmension(int y) {
		this.y = y;
	}

	public double getDeviation() {
		return a;
	}

	public void setDeviation(double a) {
		this.a = a;
	}
}