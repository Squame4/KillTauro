package Mostro;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Platform.*;
import sun.java2d.loops.DrawLine;

public class Minotauro implements Mostro,Runnable
{
	
	PannelloDiGioco pannello;
	int x;
	int y;
	

	int width;
	int height;
	int XIniziale; //in caso di respawn
	int YIniziale;
	
	double  dx,dy;  //vettori direzione velocità
	
	int pv;
	
	Rectangle hitBox;
	ArrayList<Image> sprite;
	int indice;
	Image imgCorrente;
	Shape linea;
	
	URL urlMinoSound;
    AudioClip minoSound;

	
	public Minotauro(int x_ , int y_ , PannelloDiGioco p)
	{
		
		x = x_;
		y = y_;
		pannello = p;
		XIniziale = x;
		YIniziale = y;

		pv = 4;
		
		dx = 1; //il movimento iniziale è a destra
		
		width = 384; //dim sprite
		height = 240;
		
		hitBox = new Rectangle(x, y, 120 , 132);
		sprite = new ArrayList<Image>();
		linea = new Line2D.Double(x , y-10 , x+10 , y-10 );
		
		caricaSprite();
		indice = 0;
		imgCorrente = sprite.get(indice);
		

		
	}
	

	private void caricaSprite() {
		
		for (int i = 0; i < 8; i++) {
			try {
				sprite.add(ImageIO.read(new File("minotauro" + File.separator + i + ".png")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}


	@Override
	public void run() {
		
		while(true) {

		if (indice == 7)
			indice = 0;
			
		++indice;
		
		imgCorrente = sprite.get(indice);
		
		
		
		try {
			Thread.sleep(60);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
		
	}

	@Override
	public void muovi(int visualeX) {
		// TODO Auto-generated method stub
		
		
		
		
		dy+=0.3;
		
		if (pv == 4)
		{
			dx = dx + Math.signum(dx)*0.2;
			
			if (dx > 3)
				dx = 3;
			if (dx < 3)
				dx = -3;
		}
		
		if (pv == 3)
		{
			dx = dx + Math.signum(dx)*0.6;
			
			if (dx > 7)
				dx = 7;
			if (dx < -7)
				dx = -7;
		}
		if (pv == 2)
		{
			dx = dx + Math.signum(dx)*1.2;
			
			if (dx > 10)
				dx = 10;
			if (dx < -10)
				dx = -10;
		}
		if (pv == 1)
		{
			dx = dx + Math.signum(dx)*2;
			
			if (dx > 15)
				dx = 15;
			if (dx < -15)
				dx = -15;
		}
		
		hitBox.x+=dx;
		
		for (Blocco i : pannello.getLivelli().get(pannello.getIndexLivelloCorrente()).getBlocchi())	
			if(i.getHitBox().intersects(hitBox) && (i.getTipo() >= 1  || i.getTipo() == -3)) 
			{
				hitBox.x-=dx;
			
				dx = -dx;
				
				hitBox.x = x;
			}
		
		
		hitBox.y += dy;
		for (Blocco i : pannello.getLivelli().get(pannello.getIndexLivelloCorrente()).getBlocchi()) 
		{
					if (hitBox.intersects(i.getHitBox()) && i.getTipo() >= 1) 
					{
						hitBox.y -= dy;
							
							dy = 0;
							hitBox.y = y; // y = hitBox.y;
					}
		}
		
		
		
		
		
		x += dx - pannello.getPersonaggio().getDx() ;
		y += dy;
		
		
		hitBox.x = x;
		hitBox.y = y;
		

		
		
	}

	@Override
	public void disegna(Graphics2D g2d) {
		// TODO Auto-generated method stub
		
		//DISEGNO HITBOX (DEBUG)
		
//		g2d.setColor(Color.BLACK);
//		g2d.fillRect(x, y, hitBox.width, hitBox.height);
		
		//DISEGNO LINEA (DEBUG)
		
		//g2d.setColor(Color.RED);
		//g2d.drawLine(x+20 , y-1 , x+120 , y-1);
		
		if (dx > 0)
			g2d.drawImage(imgCorrente, x - 70, y - height / 2 + 10, width, height, null);
		else
			g2d.drawImage(imgCorrente, x + width / 2 , y - height / 2 + 10, -width, height, null);
		
		
	}
	
	public int set(int visualeX) {
		
		if (pv == 4)
		{
			dx = dx + 0.2;
			
			if (dx > 3)
				dx = 3;
		}
		
		if (pv == 3)
		{
			dx = dx + 5;
			
			if (dx > 20)
				dx = 20;
		}
		if (pv == 2)
		{
			dx = dx + 10;
			
			if (dx > 50)
				dx = 50;
		}
		if (pv == 1)
		{
			dx = dx + 40;
			
			if (dx > 150)
				dx = 150;
		}
		
		
		x = (int) (XIniziale + visualeX  + dx);

		hitBox.x = x;		
		return x;
		
	}

	 
	public Rectangle getHitBox() {
		return hitBox;
	}
	
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}


	public void resetPos()
	{
		x = XIniziale;
		y = YIniziale;
	}


	@Override
	public void danneggia() {
		// TODO Auto-generated method stub
		--pv;
		
		
		urlMinoSound = Menu.class.getResource("ost/minotauro.wav");
		minoSound = Applet.newAudioClip(urlMinoSound);
		minoSound.play();
		
		
		//System.out.println(pv);
		
		if (pv <= 0)
		{
			pannello.getLivelli().get(pannello.getIndexLivelloCorrente()).eliminaMostro(0);
			pannello.setSchermata5(true);
		}
		
	}


	@Override
	public void playSuonoMorte() {
		urlMinoSound = Menu.class.getResource("ost/minotauro2.wav");
		minoSound = Applet.newAudioClip(urlMinoSound);
		minoSound.play();
		
	}


	
	
}
