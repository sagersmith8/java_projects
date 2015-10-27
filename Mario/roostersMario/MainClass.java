package roostersMario;

import javax.swing.JFrame;


@SuppressWarnings("serial")
public class MainClass extends JFrame
{
    public MainClass()
    {

		add(new Board());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 630);
		setLocationRelativeTo(null);
		setLocation(200,50);
		setTitle("Gangster Mario");

		setResizable(false);
		setVisible(true);
    }

    public static void main(String[] args)
    {
        new MainClass();

    }
}