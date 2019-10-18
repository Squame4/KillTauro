package Platform;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import java.awt.Image;

public class Blocco {
	
	private int x;
	int y;
	
	int width;
	int height;
	
	ArrayList<Image> tiles;
	
	int xIniziale; //ci serve in caso di respawn
	
	//tipo (> 0 -> blocco con collisioni, 0 -> blocco vuoto, -1 -> portale, -2 chiave) 
	int tipo;
	
	Rectangle hitBox;
	
	public Blocco (int _x, int _y, int w, int h, int tipo)
	{
		tiles = new ArrayList<Image>();
		caricaTiles();
		
		this.tipo = tipo;
		setX(_x);
		y = _y;
		xIniziale = getX(); //deviazione della telecamera
		height = h;
		width = w;
		
		hitBox = new Rectangle(getX(),y,width,height);
	}
	
	
	private void caricaTiles() {
		for (int i = 1; i < 13; i++) {
			try {
				tiles.add(ImageIO.read(new File("tiles" + File.separator + i + ".png")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}


	public void disegna (Graphics2D g2d)
	{

		
		if (tipo == 1) //inizio erba
			g2d.drawImage(tiles.get(tipo - 1), getX() , y , width, height, null);
		
		if (tipo == 2) //metà erba
			g2d.drawImage(tiles.get(tipo - 1), getX() , y , width, height, null);
		
		if (tipo == 3) // fine erba
			g2d.drawImage(tiles.get(tipo - 1), getX() , y , width, height, null);
		
		if (tipo == 4) //centro
			g2d.drawImage(tiles.get(tipo - 1), getX() , y , width, height, null);
		
		if (tipo == 5) //terra lato sx
			g2d.drawImage(tiles.get(tipo - 1), getX() , y , width, height, null);
		
		if (tipo == 6) //terra lato dx
			g2d.drawImage(tiles.get(tipo - 1), getX() , y , width, height, null);
		
		if (tipo == 7) //terra angolo sx
			g2d.drawImage(tiles.get(tipo - 1), getX() , y , width, height, null);
		
		if (tipo == 8) //terra fine centro
			g2d.drawImage(tiles.get(tipo - 1), getX() , y , width, height, null);
		
		if (tipo == 9) //terra angolo dx
			g2d.drawImage(tiles.get(tipo - 1), getX() , y , width, height, null);
		
		if (tipo == 10)//pietra
			g2d.drawImage(tiles.get(tipo - 1), getX() , y , width, height, null);
		
		if (tipo == 11)//mattoni
			g2d.drawImage(tiles.get(tipo - 1), getX() , y , width, height, null);
		
		if (tipo == -1){
			g2d.drawImage(tiles.get(11), getX() , y , width, height, null);

		}
		
	}
		


	public int set(int visualeX) {
		setX(xIniziale + visualeX); // visuale x ci da la deviazione della telecamera
		hitBox.x = getX();
		
		return getX();
		
	}


	public void setTipo(int tipo) {
		this.tipo = tipo;
	}


	public int getTipo() {
		return tipo;
	}


	public Rectangle getHitBox() {
		return hitBox;
	}


	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}
	
	
}
