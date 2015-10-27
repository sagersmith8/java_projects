package practicePrograms;

public class PowerDidgitSum 
{
	public static void main(String[] args)
	{
		int l = (int)Math.pow(2, 1000);
		String s = String.valueOf(Math.pow(2, 1000));
		int sum = 0;
		
		System.out.println(l);
		System.out.println(s);
		for(int i=0; i<String.valueOf(l).length(); i++)
		{
			sum+=(int)String.valueOf(l).charAt(i);
		}
		
		System.out.println("Final: " + sum);
	}
}
