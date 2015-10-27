import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Music implements Runnable
{
	public Music()
	{
		Thread play = new Thread(this);
		play.start();
	}
	boolean first = true;
	public void run()
	{
		try
		{
			 URL url = this.getClass().getClassLoader().getResource("mario.wav");
			 AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
			 Clip clip = AudioSystem.getClip();
			 clip.open(audioIn);
			 clip.loop(Clip.LOOP_CONTINUOUSLY);

		}
		catch(Exception ex)
		{
			System.out.println("Catch");
		}
		while(true)
		{
		}
	}
	public static void main(String [] args)//throws Exception
	{
		new Music();
	}
}