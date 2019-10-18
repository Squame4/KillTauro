package Platform;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Moneta implements Runnable
{
	
	PannelloDiGioco pannello;
	int x;
	int y;
	

	int width;
	int height;
	int XIniziale; //in caso di respawn
	int YIniziale;
	
	Rectangle hitBox;
	ArrayList<Image> sprite;
	int indice;
	Image imgCorrente;
	
	public Moneta(PannelloDiGioco pannello, int x, int y) 
	{
		super();
		this.pannello = pannello;
		this.x = x;
		this.y = y;
		width = 32;
		height = 32;
		XIniziale = x;
		YIniziale = y;
		hitBox = new Rectangle(width, height);
		
		sprite = new ArrayList<Image>();
		caricaSprites();
		indice = 0;
		imgCorrente = sprite.get(indice);
		
		
	}
	

	
	
	private void caricaSprites() {
		
		for (int i = 0; i < 7; i++) {
			try {
				sprite.add(ImageIO.read(new File("moneta" + File.separator + i + ".png")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}




	public void disegna(Graphics2D g2d) {
		// TODO Auto-generated method stub
		
		//DISEGNO HITBOX (DEBUG)
		
//		g2d.setColor(Color.BLACK);
//		g2d.fillRect(x, y, hitBox.width, hitBox.height);
		
		//DISEGNO LINEA (DEBUG)
		
//		g2d.setColor(Color.RED);
//		g2d.drawLine(x+20 , y-3 , x+80 , y-3);
		
		
		g2d.drawImage(imgCorrente, x - 7, y - 31, width, height, null);
		
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




	public void set(int visualeX) {
		// TODO Auto-generated method stub
		x = XIniziale + visualeX; // visuale x ci da la deviazione della telecamera
		hitBox.y = y;
		hitBox.x = x;
		
		
	}




	@Override
	public void run() {
		while(true) {

			if (indice == 6)
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

	







}
