import javax.swing.JOptionPane;


public class InsufficientFundsException extends Exception 
{
	private long funds;
	public InsufficientFundsException(long fun)
	{
		this.funds = fun;
	}
}
