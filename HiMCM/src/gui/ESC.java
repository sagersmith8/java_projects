package gui;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ESC extends JFrame implements ActionListener, ItemListener {

	private BufferedImage aImage ;
	private ImageIcon aImg;
	int which=1;
	DecimalFormat format = new DecimalFormat("###,##0.0");
	int[] zone1 = new int[256];
	int[] zone2 = new int[256];
	int[] zone3 = new int[256];
	double[] percentageCovered = new double[256];
	boolean[] zonesUsed = new boolean[6];
	double [] population = {50000d,80000d,30000d,55000d,35000d,20000d,270000d};
	double [][] averageTravelTimes = {	{1,8,12,14,10,16},
			{8,1,6,18,16,16},
			{12,18,1.5,12,6,4},
			{16,14,4,1,16,12},
			{18,16,10,4,2,2},
			{16,18,4,12,2,2}	};

	private JPanel main = new JPanel(new GridLayout(3,2));
		private JButton[] zones = new JButton[6];
	private JPanel bottom = new JPanel(new GridLayout(1,2));
		private JPanel stats = new JPanel(new GridLayout(2,1));
			private JPanel coveredP = new JPanel();
				private JLabel coveredL = new JLabel("People covered:    ");
				private JTextField coveredF = new JTextField(7);
			private JPanel uncoveredP = new JPanel();
				private JLabel uncoveredL = new JLabel("People not covered: ");
				private JTextField uncoveredF = new JTextField(7);
		private JPanel checkbox = new JPanel(new GridLayout(3,1));
		private CheckboxGroup group = new CheckboxGroup();
			private Checkbox bothZones = new Checkbox("Include all zones", false, group);
			private Checkbox noZones = new Checkbox("Only include transition zones", false, group);
			private Checkbox onlyEnd = new Checkbox("Transition zone and end zone", true, group);

	private JMenuBar m = new JMenuBar();
		private JMenu file = new JMenu("File");
			private JMenuItem about = new JMenuItem("About");
			private JMenuItem exit = new JMenuItem("Exit");
			private JMenuItem reset = new JMenuItem("Reset");
		private JMenu optimize = new JMenu("Optimize");
			private JMenuItem one = new JMenuItem("One Ambulance");
			private JMenuItem two = new JMenuItem("Two Ambulances");
			private JMenuItem three = new JMenuItem("Three Ambulances");

	private ESC() {
		setBounds(30,30,420,550);
		setTitle("ESC Coverage Planner");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
		setJMenuBar(m);
			m.add(file);
				file.add(about);
					about.addActionListener(this);
				file.add(reset);
					reset.addActionListener(this);
				file.add(exit);
					exit.addActionListener(this);
			m.add(optimize);
				optimize.add(one);
					one.addActionListener(this);
				optimize.add(two);
					two.addActionListener(this);
				optimize.add(three);
					three.addActionListener(this);

		for(int i=0; i<zones.length; i++) {
			zones[i] = new JButton("ZONE " + (i+1));
			zones[i].addActionListener(this);
			zones[i].setBackground(Color.BLACK);
			zones[i].setForeground(Color.WHITE);
			main.add(zones[i]);
		}

		bottom.add(stats);
			stats.add(coveredP);
				coveredP.add(coveredL);
				coveredP.add(coveredF);
					coveredF.setEditable(false);
			stats.add(uncoveredP);
				uncoveredP.add(uncoveredL);
				uncoveredP.add(uncoveredF);
					uncoveredF.setEditable(false);
		bottom.add(checkbox);
			checkbox.add(bothZones);
				bothZones.addItemListener(this);
			checkbox.add(noZones);
				noZones.addItemListener(this);
			checkbox.add(onlyEnd);
				onlyEnd.addItemListener(this);

		add(main, BorderLayout.CENTER);
		add(bottom, BorderLayout.SOUTH);

		invalidate();
		validate();

		try {
			aImage = ImageIO.read(new File("ambulance.png"));
			aImg = new ImageIcon(aImage);
		} catch(Exception e) {
			JOptionPane.showMessageDialog(this, "Could not read images for backgrounds!\nA red zone means there is an ambulance in that zone.\nA black square means there is no ambulance in that zone.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == about) {
			JOptionPane.showMessageDialog(null, "Created for HiMCM 2013\nProblem A\nTeam number: 4515", "About", JOptionPane.INFORMATION_MESSAGE);
		} else if(e.getSource() == reset) {
			onlyEnd.setState(true);
			reset();
		} else if(e.getSource() == exit) {
			System.exit(0);
		} else if(e.getSource() == one){
			reset();
			zonesUsed[4] = true;
			zones[4].setBackground(Color.red);
			zones[4].setIcon(aImg);
		} else if(e.getSource() == two) {
			reset();
			zonesUsed[4] = true;
			zones[4].setBackground(Color.red);
			zones[4].setIcon(aImg);
			zonesUsed[1] = true;
			zones[1].setBackground(Color.red);
			zones[1].setIcon(aImg);
		} else if(e.getSource() == three) {
			reset();
			zonesUsed[4] = true;
			zones[4].setBackground(Color.red);
			zones[4].setIcon(aImg);
			zonesUsed[0] = true;
			zones[0].setBackground(Color.red);
			zones[0].setIcon(aImg);
			zonesUsed[1] = true;
			zones[1].setBackground(Color.red);
			zones[1].setIcon(aImg);
		} else {
			for(int i=0; i<zones.length; i++) {
				if(e.getSource() == zones[i]) {
					if(zonesUsed[i]) {
						zones[i].setIcon(null);
						zones[i].setBackground(Color.black);
						zones[i].setText("ZONE " + (i+1));
						zonesUsed[i] = false;
					} else if(!zonesUsed[i] && !check()) {
						zones[i].setIcon(aImg);
						zones[i].setBackground(Color.red);
						zonesUsed[i] = true;
					}

				}
			}
		}
		output(numZones());
	}

	public void reset() {
		for(int i=0; i<zones.length; i++) {
			zones[i].setIcon(null);
			zones[i].setText("ZONE " + (i+1));
			zones[i].setBackground(Color.BLACK);
			zonesUsed[i] = false;
		}
	}

	public void itemStateChanged(ItemEvent e) {
		if(onlyEnd.getState()) {
			which = 1;
		} else if(noZones.getState()) {
			which = 2;
		} else if(bothZones.getState()) {
			which = 3;
		}
		output(numZones());
	}

	public static void main(String[] args) {
		new ESC();
	}

	public double populationPercentage(int zone) {
		double dd = population[zone]/population[6];
		dd *= 100d;
		return dd;
	}

	@SuppressWarnings({ })
	public double numZones() {	
		int count = 0;
		double d = 0;
		ArrayList<Integer> num = new ArrayList<Integer>();
		for(int i=0; i<zonesUsed.length; i++) {
			if(zonesUsed[i]) {
				count++;
				num.add(i);
			}
		}
		if(count == 3) {
			d = covered(num.get(0), num.get(1), num.get(2), which);
		} else if(count == 2) {
			d = covered(num.get(0), num.get(1), which);
		} else if(count == 1) {
			d = covered(num.get(0), which);
		}

		return d;
	}

	public double covered(int zone1, int zone2, int zone3, int which) {
		boolean alreadyCovered[] = new boolean[6];
		double percentCovered = 0;

		switch(which){
		case 1:
			for(int i=0; i<population.length-1; i++) {
				if(averageTravelTimes[zone1][i] < 8) {
					alreadyCovered[i] = true;
					percentCovered += populationPercentage(i);
				}
			}
			for(int i=0; i<population.length-1; i++) {
				if(averageTravelTimes[zone2][i] < 8) {
					if(alreadyCovered[i] == false) {
						alreadyCovered[i] = true;
						percentCovered += populationPercentage(i);
					}
				}
			}
			for(int i=0; i<population.length-1; i++) {
				if(averageTravelTimes[zone3][i] <= 8) {
					if(alreadyCovered[i] == false) {
						alreadyCovered[i] = true;
						percentCovered += populationPercentage(i);
					}
				}
			}
			break;

		case 2:
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
			break;
		case 3:
			for(int i=0; i<population.length-1; i++) {
				if(averageTravelTimes[zone1][i]+averageTravelTimes[i][i]+averageTravelTimes[zone1][zone1] < 8) {
					alreadyCovered[i] = true;
					percentCovered += populationPercentage(i);
				}
			}
			for(int i=0; i<population.length-1; i++) {
				if(averageTravelTimes[zone2][i]+averageTravelTimes[i][i]+averageTravelTimes[zone2][zone2] < 8) {
					if(alreadyCovered[i] == false) {
						alreadyCovered[i] = true;
						percentCovered += populationPercentage(i);
					}
				}
			}
			for(int i=0; i<population.length-1; i++) {
				if(averageTravelTimes[zone3][i]+averageTravelTimes[i][i]+averageTravelTimes[zone3][zone3] <= 8) {
					if(alreadyCovered[i] == false) {
						alreadyCovered[i] = true;
						percentCovered += populationPercentage(i);
					}
				}
			}
			break;
		}
		return percentCovered;
	}

	public double covered(int zone1, int zone2, int which) {
		boolean alreadyCovered[] = new boolean[6];
		double percentCovered = 0;

		switch(which){
		case 1:
			for(int i=0; i<population.length-1; i++) {
				if(averageTravelTimes[zone1][i] < 8) {
					alreadyCovered[i] = true;
					percentCovered += populationPercentage(i);
				}
			}
			for(int i=0; i<population.length-1; i++) {
				if(averageTravelTimes[zone2][i] < 8) {
					if(alreadyCovered[i] == false) {
						alreadyCovered[i] = true;
						percentCovered += populationPercentage(i);
					}
				}
			}
			break;

		case 2:
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
			break;

		case 3:
			for(int i=0; i<population.length-1; i++) {
				if(averageTravelTimes[zone1][i]+averageTravelTimes[i][i]+averageTravelTimes[zone1][zone1] < 8) {
					alreadyCovered[i] = true;
					percentCovered += populationPercentage(i);
				}
			}
			for(int i=0; i<population.length-1; i++) {
				if(averageTravelTimes[zone2][i]+averageTravelTimes[i][i]+averageTravelTimes[zone2][zone2] < 8) {
					if(alreadyCovered[i] == false) {
						alreadyCovered[i] = true;
						percentCovered += populationPercentage(i);
					}
				}
			}
			break;
		}
		return percentCovered;
	}

	public double covered(int zone1, int which) {
		boolean alreadyCovered[] = new boolean[6];
		double percentCovered = 0;

		switch(which){
		case 1:
			for(int i=0; i<population.length-1; i++) {
				if(averageTravelTimes[zone1][i] < 8) {
					alreadyCovered[i] = true;
					percentCovered += populationPercentage(i);
				}
			}
			break;

		case 2:
			for(int i=0; i<population.length-1; i++) {
				if(averageTravelTimes[zone1][i]+averageTravelTimes[i][i] < 8) {
					alreadyCovered[i] = true;
					percentCovered += populationPercentage(i);
				}
			}
			break;

		case 3:
			for(int i=0; i<population.length-1; i++) {
				if(averageTravelTimes[zone1][i]+averageTravelTimes[i][i]+averageTravelTimes[zone1][zone1] < 8) {
					alreadyCovered[i] = true;
					percentCovered += populationPercentage(i);
				}
			}
			break;
		}
		return percentCovered;
	}

	public boolean check() {
		int count = 0;
		for(int i=0; i<zonesUsed.length; i++) {
			if(zonesUsed[i]) {
				count++;
			}
		}
		if(count<3) {
			return false;
		} else {
			JOptionPane.showMessageDialog(this, "You can only select three zones at one time.", "Error", JOptionPane.ERROR_MESSAGE);
			return true;
		}
	}

	public void output(double percentCovered) {
		coveredF.setText(format.format(2700*percentCovered));
		uncoveredF.setText(format.format(2700*(100-percentCovered)));
	}

	public void sort(double[] a, int[] z1, int[] z2, int[] z3) {
		for (int i = 0; i <a.length; i++) {
			for (int j = i; j > 0; j--) {	
				if (a[j] > a[j-1]) {
					if(z2==null && z3 == null) {
						swap(a,z1,null,null,j, j-1);
					} else if(z3==null) {
						swap(a,z1, z2, null, j, j-1);
					} else {
						swap(a,z1,z2,z3,j,j-1);
					}
				} else {
					j = 0;
				}
			}
		}
	}

	public void swap(double[] swapArray, int [] swapArray1, int [] swapArray2, int [] swapArray3,int first, int second) {
		double hold;
		int hold1, hold2, hold3;

		hold = swapArray[first];
		swapArray[first] = swapArray[second];
		swapArray[second] = hold;

		hold1 = swapArray1[first];
		swapArray1[first] = swapArray1[second];
		swapArray1[second] = hold1;

		if(swapArray2 !=null) {
			hold2 = swapArray2[first];
			swapArray2[first] = swapArray2[second];
			swapArray2[second] = hold2;
		}
		if(swapArray3!=null) {
			hold3 = swapArray3[first];
			swapArray3[first] = swapArray3[second];
			swapArray3[second] = hold3;
		}
	}

	public void deleteDuplicates(double[] per,int [] z1, int[]z2, int [] z3, int i) {
		int[]first = new int[3];
		int[]second = new int[3];
		if(z3==null&&z2==null) {
			first[0]=z1[i-1];
			first[1]=-1;
			first[2]=-1;
			second[0]=z1[i-1];
			second[1]=-1;
			second[2]=-1;
		} else if(z3== null) {
			first[0]=z1[i-1];
			first[1]=z2[i-1];
			first[2]=-1;
			second[0]=z1[i];
			second[1]=z2[i];
			second[2]=-1;
		} else {
			first[0]=z1[i-1];
			first[1]=z2[i-1];
			first[2]=z3[i-1];
			second[0]=z1[i];
			second[1]=z2[i];
			second[2]=z3[i];
		}
		Arrays.sort(first);
		Arrays.sort(second);
		if(Arrays.equals(first, second)) {
			z1[i-1] =0;
			if(z2!=null) {
				z2[i-1] =0;
			}
			if(z3!=null) {
				z3[i-1] =0;
			}
			per[i-1]=0;
		}
	}

	public void populateZones(int ambulanceNumbers) {
		int counter = 0;
		if(ambulanceNumbers == 3) {
			for(int i = 0; i < averageTravelTimes.length; i++) {
				for(int j = 0; j < averageTravelTimes.length/2; j++) {
					for(int k = 0; k < averageTravelTimes.length/4; k++) {
						if(i != j && i != k && k != j) {
							zone1[counter] = i+1;
							zone2[counter] = j+1;
							zone3[counter] = k+1;
							percentageCovered[counter] = covered(i,j,k,1);
							counter++;
						}					
					}
				}
			}
			sort(percentageCovered,zone1,zone2,zone3);
			covered(zone1[0], zone2[0], zone3[0], which);
		}

		else if(ambulanceNumbers == 2) {
			for(int i = 0; i < averageTravelTimes.length; i++) {
				for(int j = 0; j < averageTravelTimes.length/2; j++) {
					if(i != j) {
						zone1[counter] = i+1;
						zone2[counter] = j+1;
						percentageCovered[counter] = covered(i,j,-1,1);
						counter++;
					}					
				}
			}
			sort(percentageCovered,zone1,zone2,null);
			covered(zone1[0], zone2[0], which);
		}

		else if(ambulanceNumbers == 1) {
			for(int i = 0; i < averageTravelTimes.length; i++) {
				zone1[counter] = i+1;
				percentageCovered[counter] = covered(i,-1,-1,1);
				counter++;
			}
			sort(percentageCovered,zone1,null,null);
			covered(zone1[0], which);
		}
	}
}