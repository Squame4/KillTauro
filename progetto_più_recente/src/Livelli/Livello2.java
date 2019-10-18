package Livelli;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Mostro.Mostro;
import Mostro.Shpettro;
import Mostro.ShpettroVerticale;
import Platform.Blocco;
import Platform.Moneta;
import Platform.PannelloDiGioco;

public class Livello2 implements Livello {

	private PannelloDiGioco pannello;
	private ArrayList<Blocco> blocchi;
	private ArrayList<Moneta> monete;
	Image background;
	
	int xSpawn;
	int ySpawn;
	
	private ArrayList<Mostro> mostri;
	
	String pwdLivello;
	
	public Livello2(PannelloDiGioco p)
	{
		pannello = p;
		blocchi = new ArrayList<Blocco>();
		mostri = new ArrayList<Mostro>();
		monete = new ArrayList<Moneta>();
		xSpawn = 400;
		ySpawn = 500;
		generaLivello();
		caricaMostri();
		caricaMonete();
		
		
		try {
			background = ImageIO.read(new File("background" + File.separator + 2 + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		pwdLivello = "flying";
	}
	
	@Override
	public void generaLivello() {

		int  matLivello [][] = 
	    {{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-3,-3,-3,-3,-3,-3,-3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}, 
				{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			   { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,9,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			   { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-3,0,0,0,0,0,0,0,0,0,1,2,2,2,2,2,2,2,2,3,0,0,0,0,0,0,0,0,0,0,1,2,2,2,2,2,2,2,2,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			   { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,8,8,8,8,8,8,8,8,9,0,0,0,0,0,0,0,0,0,0,7,8,8,8,8,8,8,8,8,9,0,-3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,2,2,2,2,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			   { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,4,4,4,4,4,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,9,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1,0,0,0},
			   { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,4,4,4,4,4,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,11,0,0,0},
			 { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,2,2,2,3,0,0,0,5,4,4,4,4,4,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,9,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,11,11,11,0,0},
				{ 0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,2,3,0,0,0,7,8,8,8,8,9,0,0,0,5,4,4,4,4,4,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,3,0,0,0,0,0,0,0,0,0,0,0,1,2,2,2,2,2,3},
				{ 1,2,2,2,2,2,2,2,2,2,3,0,0,5,4,4,6,0,0,0,0,0,0,0,0,0,0,0,0,7,8,8,8,8,8,9,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,9,0,0,0,0,0,0,0,0,0,0,0,5,4,4,4,4,4,6},
			   { 5,4,4,4,4,4,4,4,4,4,6,0,0,5,4,4,6,0,0,0,0,0,0,0,0,0,0,-3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,8,8,8,8,8,9}};
		
		for (int  i = 0; i < 96; i += 1)
		{
			for(int j = 0; j < 18; ++j)
			{
				
				if(matLivello[j][i] != 0)
				 blocchi.add(new Blocco(i*40, j*40, 40, 40, matLivello[j][i]));
				
				
			}
		}

	}
		

	@Override
	public boolean fineLivello() {
		
		for (Blocco i : blocchi) 
			if (pannello.getPersonaggio().getHitBox().intersects(i.getHitBox()) && i.getTipo() == -1)
				return true;
		
		return false;
	}

	@Override
	public ArrayList<Blocco> getBlocchi() {
		// TODO Auto-generated method stub
		return blocchi;
	}

	@Override
	public int getxSpawn() {
		// TODO Auto-generated method stub
		return xSpawn;
	}

	@Override
	public int getySpawn() {
		// TODO Auto-generated method stub
		return ySpawn;
	}

	@Override
	public void disegna(Graphics2D g2d) {
		g2d.drawImage(background, 0 , 0 , 1280, 720, null);
		
	}

	@Override
	public ArrayList<Mostro> getMostri() {
		// TODO Auto-generated method stub
		return mostri;
	}

	@Override
	public void caricaMostri() {
		// TODO Auto-generated method stub
		mostri.clear();
		Mostro sv1 = new ShpettroVerticale (1080, 600,pannello);
		Mostro s1 = new Shpettro (2400, 360,pannello);
		Mostro s2 = new Shpettro (3440, 600,pannello);
		Mostro sv2 = new ShpettroVerticale (2720, 320,pannello);
		mostri.add(sv1);
		mostri.add(s1);
		mostri.add(s2);
		mostri.add(sv2);
		
	}
	
	public void caricaMonete()
	{
		Moneta m1 = new Moneta(pannello, 1120, 400);
		Moneta m2 = new Moneta(pannello, 1560, 320);
		Moneta m3 = new Moneta(pannello, 1640, 320);
		Moneta m4 = new Moneta(pannello, 1720, 320);
		Moneta m5 = new Moneta(pannello, 2840, 200);
		Moneta m6 = new Moneta(pannello, 2840, 160);
		Moneta m7 = new Moneta(pannello, 2840, 120);
		Moneta m8 = new Moneta(pannello, 3760, 520);
		Moneta m9 = new Moneta(pannello, 3640, 520);
		
		monete.add(m1);
		monete.add(m2);
		monete.add(m3);
		monete.add(m4);
		monete.add(m5);
		monete.add(m6);
		monete.add(m7);
		monete.add(m8);
		monete.add(m9);
		
	}

	@Override
	public void eliminaMostro(int i) {
		// TODO Auto-generated method stub
		mostri.get(i).playSuonoMorte();
		mostri.remove(i);
		
	}

	@Override
	public ArrayList<Moneta> getMonete() {
		// TODO Auto-generated method stub
		return monete;
	}

	@Override
	public void eliminaMoneta(int i) {
		// TODO Auto-generated method stub
		monete.remove(i);
		
	}

	@Override
	public String getPwdLivello() {
		// TODO Auto-generated method stub
		return pwdLivello;
	}

	
	
}
