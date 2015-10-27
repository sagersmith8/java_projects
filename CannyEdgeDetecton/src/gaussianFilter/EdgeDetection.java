package gaussianFilter;

public class EdgeDetection extends Filter {
	
	boolean x;
	float [] matrixX = new float[]{ 
			   -1f, 0f, 1f, 
			   -2f, 0f, 2f, 
			   -1f, 0f, 1f, 
	};
	
	boolean y;
	float [] matrixY = new float[]{
			   -1f, -2f, -1f, 
			   0f, 0f, 0f, 
			   1f, 2f, 1f, 
	};
	
	boolean x2;
	float [] matrixX2 = new float[]{ 
			   1f, 0f, -1f, 
			   2f, 0f, -2f, 
			   1f, 0f, -1f, 
	};
	
	boolean y2;
	float [] matrixY2 = new float[]{
			   1f, 2f, 1f, 
			   0f, 0f, 0f, 
			   -1f, -2f, -1f, 
	};
	
	/**
	 * 
	 */
	public EdgeDetection() {
		
	}
	
	public EdgeDetection(boolean x, boolean x2, boolean y, boolean y2) {
		
	}
}
