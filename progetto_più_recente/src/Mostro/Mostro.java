package Mostro;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Line2D;

public interface Mostro
{
	void muovi(int visualeX);
	void disegna(Graphics2D g2d);
	public int set(int visualeX);
	
	public Rectangle getHitBox();
	double getX();
	double getY();
	void resetPos();
	void danneggia();
	void playSuonoMorte();
	
	
}
