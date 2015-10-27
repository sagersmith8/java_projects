import java.util.*;
public class Encrypter
{
	static Scanner scan = new Scanner(System.in);
	static int num = scan.nextInt();
	static String encrypt = scan.next();
	static String plain = scan.next();
	static int ans;
	static int int1[] = new int[encrypt.length()];
	static int int2[] = new int[plain.length()];
	static int length = plain.length()/encrypt.length();
	static int [] alphabet = new int[26];
	
	public static void main(String[]args)
	{
		for(int i = 0; i < alphabet.length; i++)
		{
			alphabet[i] = i+1;
		}
		
		for(int i = 0; i < plain.length();i++)
		{
			int1[i] = toOurIntValue(plain.charAt(i)); 
		}
		for(int i = 0; i < encrypt.length();i++)
		{
			int2[i] = toOurIntValue(encrypt.charAt(i));
		}
	}
	
	static public int toOurIntValue(char bob)
	{
		char joe = bob;
		int stain = 0;
		if(joe == 'a')
		{
			stain = 1;
		}
		if(joe == 'b')
		{
			stain = 2;
		}
		if(joe == 'c')
		{
			stain = 3;
		}
		if(joe == 'd')
		{
			stain = 4;
		}
		if(joe == 'e')
		{
			stain = 5;
		}
		if(joe == 'f')
		{
			stain = 6;
		}
		if(joe == 'g')
		{
			stain = 7;
		}
		if(joe == 'h')
		{
			stain = 8;
		}
		if(joe == 'i')
		{
			stain = 9;
		}
		if(joe == 'j')
		{
			stain = 10;
		}
		if(joe == 'k')
		{
			stain = 11;
		}
		if(joe == 'l')
		{
			stain = 12;
		}
		if(joe == 'm')
		{
			stain = 13;
		}
		if(joe == 'n')
		{
			stain = 14;
		}
		if(joe == '0')
		{
			stain = 15;
		}
		if(joe == 'p')
		{
			stain = 16;
		}
		if(joe == 'q')
		{
			stain = 17;
		}
		if(joe == 'r')
		{
			stain = 18;
		}
		if(joe == 's')
		{
			stain = 19;
		}
		if(joe == 't')
		{
			stain = 20;
		}
		if(joe == 'u')
		{
			stain = 21;
		}
		if(joe == 'v')
		{
			stain = 22;
		}
		if(joe == 'w')
		{
			stain = 23;
		}
		if(joe == 'x')
		{
			stain = 24;
		}
		if(joe == 'y')
		{
			stain = 25;
		}
		if(joe == 'z')
		{
			stain = 26;
		}
		
		return stain;
	}
}
