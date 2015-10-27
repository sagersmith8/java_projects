package roostersMario;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


@SuppressWarnings("serial")
public class Board extends JPanel implements ActionListener
{
        boolean soot;
        @SuppressWarnings("rawtypes")
		ArrayList score = new ArrayList();
 

    public class Shoot extends Thread implements Runnable
    {


        @Override
        public void run()
        {
            if(soot)
            {
                while(soot)
                {
                    try
                    {
                        Thread.sleep(5);
                    }
                    catch (InterruptedException ex)
                    {
                        Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    bx = bx + 1;
                    repaint();

                    if(bx > 1400)
                    {
                        soot = false;
                     //   stop();
                    }
                }
            }
        }


        public void shoot()
        {

            start();
        }

    }


    boolean shOOt = false;
    private final int WIDTHH = 1200;
    private final int HEIGHTT = 600;
    private final int ALL_DOTS = 900;
    private final int DELAY = 1;

    private int x[] = new int[ALL_DOTS];
    private int y[] = new int[ALL_DOTS];
    int mx = 0;
    int my = 0;

    private int dots;
    private int apple_x;
    private int apple_y;

    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean inGame = true;
    private Timer timer;
    private Image romney;
    private Image head;
    private Image fbomb;
    private Image map;
    private Image boss;
    private Image blood;


    int bx;
    int	by;
    int dawg;
    int sup;
    int dx;
    int dy;

    boolean x1 = false;
    boolean x2 = false;
    boolean x3 = false;
    boolean x4 = false;
    boolean hitt = false;


    int xz = 150;
    int yz = 487;


    public Board()
    {

	addKeyListener(new TAdapter());


        ImageIcon iih = new ImageIcon(this.getClass().getResource("mario.png"));
        head = iih.getImage();

	ImageIcon iha = new ImageIcon(this.getClass().getResource("map.jpg"));
	map = iha.getImage();

        ImageIcon ihqa = new ImageIcon(this.getClass().getResource("Romney.png"));
        romney = ihqa.getImage();

        ImageIcon ihaqa = new ImageIcon(this.getClass().getResource("Boss.gif"));
	boss = ihaqa.getImage();

        ImageIcon wihaqa = new ImageIcon(this.getClass().getResource("fbomb.png"));
	fbomb = wihaqa.getImage();

        ImageIcon qwihaqa = new ImageIcon(this.getClass().getResource("blood.png"));
	blood = qwihaqa.getImage();



        setFocusable(true);
        initGame();

    }


    private void initGame()
    {


        dots = 0;

        locateApple();

        timer = new Timer(DELAY, this);
        timer.start();
    }


    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        if (inGame)
        {
            g.drawImage(map, mx,my,this);


            g.drawImage(romney, apple_x, apple_y, this);


            if(shOOt)
            {
				g.drawImage(fbomb,bx,by,this);
                new Thread();
                Shoot s = new Shoot();
                s.start();
            }

            if(hitt)
            {
                g.drawImage(blood,dx,dy,this);
            }

            for (int z = 0; z < dots; z++)
            {
                if (z == 0)
                    g.drawImage(head, xz, yz, this);
            }

            Toolkit.getDefaultToolkit().sync();
            g.dispose();
            score.size();

            if(mx <= (-10500))
            {
                 g.dispose();
                inGame = false;
                repaint();
            }

        }
        else
        {
           g.drawImage(boss,50,50,this);
           repaint();
        }
    }


    @SuppressWarnings("unchecked")
	public void checkApple()
    {
        if ((bx >= apple_x - 13) && (bx <= apple_x +212) && (by >= apple_y - 13) && (by <= apple_y +212))
        {
           score.add("Romney");
           hitt = true;
           new Thread();
           Hit h = new Hit();
           h.start();
           locateApple();
        }
    }

    public void move()
    {

        for (int z = dots; z > 0; z--)
        {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (left)
        {
            mx += 4;
            apple_x +=4;
        }

        if (right)
        {
            mx -= 4;
            apple_x -=4;
        }

        if (up)
        {
           // Jump j
        }

    //    if (down)
     //   {
     //       y[0] += 5;
     //   }
    }


    public void checkCollision()
    {

          for (int z = dots; z > 0; z--)
          {

              if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z]))
              {
                  inGame = false;
              }
          }

        if (y[0] > HEIGHTT)
        {
        }

        if (y[0] < 0)
        {
            up = false;
        }

        if (x[0] > WIDTHH)
        {
            inGame = false;
        }

        if (x[0] < 0)
        {
            inGame = false;
        }
    }

    public void locateApple()
    {
        int r = (int) (Math.random() * 1000) + 200;
        apple_x = (r);
        r = 480;
        apple_y = (r);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

        if (inGame)
        {
            checkApple();
            checkCollision();
            move();
        }

        repaint();
    }


    private class TAdapter extends KeyAdapter
    {

        @Override
        public void keyPressed(KeyEvent e)
        {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT))
            {
                left = true;
                up = false;
                right = false;
            }

            if ((key == KeyEvent.VK_RIGHT))
            {
                right = true;
                up = false;
                left= false;
            }

            if ((key == KeyEvent.VK_UP))
            {
                up = true;
                right = false;
                left = false;
            }

            if ((key == KeyEvent.VK_DOWN))
            {
                right = false;
                left = false;
                up = false;
            }

            if ((key == KeyEvent.VK_N))
            {
                    locateApple();
                    {
                            inGame = true;
                            dots = 3;
                            for (int z = 0; z < dots; z++)
                            {
                                    x[z] = 50 - z*10;
                                    y[z] = 50;
                            }
                            repaint();
                    }
            }


			if(key == KeyEvent.VK_SPACE)
			{
                                shOOt = true;
                                soot = true;
                                bx = (xz+30);
                                by = (yz+30);
				repaint();
			}
        }


        @Override
        public void keyReleased(KeyEvent e)
        {
			if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT)
			{
                right = false;
                left = false;
                up = false;
			}
		}
    }

    public class Hit extends Thread implements Runnable
    {
        @Override
		public void run()
		{
			if(hitt)
			{
				dx = apple_x;
				dy = apple_y;

				while(hitt)
				{
					dx += 5;
					repaint();
					try
					{
						Hit.sleep(10);
					}

					catch(Exception e)
					{
					}

					if(dx >= 1000)
					{
                                            hitt = false;
					}
				}
			}
		}

		public Hit()
		{
		//	start();
		}

	}
}