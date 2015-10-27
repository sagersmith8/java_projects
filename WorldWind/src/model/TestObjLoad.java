package model;

public class TestObjLoad {
	static ObjLoad objLoad = new ObjLoad("models/The DarkNight.obj");
	static  int counter = 0;
	public static void main(String[] args) 
	{
		for(int i = 0; i < objLoad.size(); i++)
		{
			counter++;
			if(counter == 1)
			{
				System.out.print("X:" + objLoad.get(i));
			}
			else if(counter == 2)
			{
				System.out.print(" Y:" + objLoad.get(i));
			}
			else if(counter ==3)
			{
				System.out.print(" Z:" + objLoad.get(i));
				System.out.println("");
				counter=0;
			}
		}
	}

}
