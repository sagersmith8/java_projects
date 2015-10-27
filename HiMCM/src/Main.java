import java.util.Scanner;

public class Main
{
	double [][] averageTravelTimes = {	{1,8,12,14,10,16},
										{8,1,6,18,16,16},
										{12,18,1.5,12,6,4},
										{16,14,4,1,16,12},
										{18,16,10,4,2,2},
										{16,18,4,12,2,2}	};

	double [] population = {50000d,80000d,30000d,55000d,35000d,20000d,270000d};
	
	Scanner in = new Scanner(System.in);
	
	public Main()
	{
//		checkZones();
//		checkZones2();
		checkZones1();
	}
	
	public static void main(String[] args) 
	{
		new Main();
	}
	
	public double populationPercentage(int zone)
	{
		double dd = population[zone]/population[6];
		dd *= 100d;
		return dd;
	}
	
	public double covered(int zone1, int zone2, int zone3) {
		boolean alreadyCovered[] = new boolean[6];
		double percentCovered = 0;
		
		for(int i=0; i<population.length-1; i++) {
			if(averageTravelTimes[zone1][i]+averageTravelTimes[i][i] < 8) {
				alreadyCovered[i] = true;
				percentCovered += populationPercentage(i);
			}
		}
		for(int i=0; i<population.length-1; i++) {
			if(averageTravelTimes[zone2][i]+averageTravelTimes[i][i] < 8) {
				if(alreadyCovered[i] == false) {
					alreadyCovered[i] = true;
					percentCovered += populationPercentage(i);
				}
			}
		}
		for(int i=0; i<population.length-1; i++) {
			if(averageTravelTimes[zone3][i]+averageTravelTimes[i][i] <= 8) {
				if(alreadyCovered[i] == false) {
					alreadyCovered[i] = true;
					percentCovered += populationPercentage(i);
				}
			}
		}
		return percentCovered;
	}
	
	public void checkZones() {		
		for(int i=0; i<6; i++) {
			for(int j=0; j<6; j++) {
				for(int k=0; k<6; k++) {
					if(covered(i,j,k) > 90 && i!=k && k!=j && i!=j) {
						System.out.println("ZONES: " + (i+1) + ", " + (j+1) + ", " + (k+1) + ":\t" + covered(i,j,k));
					}
				}
			}
		}
		
	}
	
	public double covered(int zone1, int zone2) {
		boolean alreadyCovered[] = new boolean[6];
		double percentCovered = 0;
		
		for(int i=0; i<population.length-1; i++) {
			if(averageTravelTimes[zone1][i]+averageTravelTimes[i][i] < 8) {
				alreadyCovered[i] = true;
				percentCovered += populationPercentage(i);
			}
		}
		for(int i=0; i<population.length-1; i++) {
			if(averageTravelTimes[zone2][i]+averageTravelTimes[i][i] < 8) {
				if(alreadyCovered[i] == false) {
					alreadyCovered[i] = true;
					percentCovered += populationPercentage(i);
				}
			}
		}

		return percentCovered;
	}
	
	public void checkZones2() {		
		for(int i=0; i<6; i++) {
			for(int j=0; j<6; j++) {
				if(covered(i,j) > 60 && i!=j) {
					System.out.println("ZONES: " + (i+1) + ", " + (j+1) + ":\t" + covered(i,j));
				}
			}
		}
		
	}
	
	public double covered(int zone1) {
		boolean alreadyCovered[] = new boolean[6];
		double percentCovered = 0;
		
		for(int i=0; i<population.length-1; i++) {
			if(averageTravelTimes[zone1][i]+averageTravelTimes[i][i] < 8) {
				alreadyCovered[i] = true;
				percentCovered += populationPercentage(i);
			}
		}

		return percentCovered;
	}
	
	public void checkZones1() {		
		for(int i=0; i<6; i++) {
			System.out.println("ZONES: " + (i+1) + ":\t" + covered(i));
		}
		
	}
}


