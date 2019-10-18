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

public class Shpettro implements Mostro,Runnable
{
	
	PannelloDiGioco pannello;
	int x;
	int y;
	

	int width;
	int height;
	int XIniziale; //in caso di respawn
	int YIniziale;
	
	double  dx,dy;  //vettori direzione velocità
	
	Rectangle hitBox;
	ArrayList<Image> sprite;
	int indice;
	Image imgCorrente;
	Shape linea;
	
	URL urlMorteSpettro;
    AudioClip morteSpettro;

	
	public Shpettro(int x_ , int y_ , PannelloDiGioco p)
	{
		x = x_;
		y = y_;
		pannello = p;
		XIniziale = x;
		YIniziale = y;
		
		dx = 1; //il movimento iniziale è a destra
		
		width = 64; //dim sprite
		height = 64;
		
		hitBox = new Rectangle(x, y, 40, 40);
		sprite = new ArrayList<Image>();
		linea = new Line2D.Double(x , y-10 , x+10 , y-10 );
		
		caricaSprite();
		indice = 0;
		imgCorrente = sprite.get(indice);
		

		
	}
	

	private void caricaSprite() {
		
		for (int i = 0; i < 6; i++) {
			try {
				sprite.add(ImageIO.read(new File("ghost" + File.separator + i + ".png")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}


	@Override
	public void run() {
		
		while(true) {

		if (indice == 5)
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
		
		
		
		dx = dx + Math.signum(dx)*0.2;
		
		if (dx > 3)
			dx = 3;
		if (dx < -3)
			dx = -3;
		
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
							hitBox.y = y; 
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
		//g2d.drawLine(x+5 , y-3 , x+35 , y-3);
		
		if (dx > 0)
			g2d.drawImage(imgCorrente, x - 13, y - 10, width, height, null);
		else
			g2d.drawImage(imgCorrente, x + 54, y - 10, -width, height, null);
		
	
		
	}
	
	public int set(int visualeX) {
		
		dx = dx + 0.2;
		
		if (dx > 3)
			dx = 3;
		
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
		
	}


	@Override
	public void playSuonoMorte() {
		urlMorteSpettro = Menu.class.getResource("ost/speettro.wav");
		morteSpettro = Applet.newAudioClip(urlMorteSpettro);
		morteSpettro.play();
		
	}


	
	
}
