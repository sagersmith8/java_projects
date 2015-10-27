
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Music
{
	Clip clip;
	public Music()
	{
		 try{
		      AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(this.getClass().getResource("StandUpandShout.wav"));
		     Clip clip = AudioSystem.getClip();
		     clip.open(audioInputStream);
		     clip.start( );
		    }
		   catch(Exception ex)
		   { 
			   System.err.println("Unable to Play Sound");
		   }
	}
	
	public void destroy()
	{
		clip.close();
	}
}