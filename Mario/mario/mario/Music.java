package mario;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Music
{
	Clip clip;
	public Music()
	{
		try
		{
			 URL url = this.getClass().getClassLoader().getResource("mario.wav");
			 AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
			 clip = AudioSystem.getClip();
			 clip.open(audioIn);
			 clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
		catch(Exception ex)
		{
			System.out.println("Catch");
		}
	}
	
	public void destroy()
	{
		clip.close();
	}
}