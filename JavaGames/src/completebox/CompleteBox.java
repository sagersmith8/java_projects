package completebox;

import javax.swing.JFrame;

/**
 * @author Sage
 */
public class CompleteBox extends JFrame{
	
	public CompleteBox(){
		this.setBounds(0, 0, 500, 500);
		this.setTitle("Complete the Box");
		this.setVisible(true);
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args){
		new CompleteBox();
	}

}
