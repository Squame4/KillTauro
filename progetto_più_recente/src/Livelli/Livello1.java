package Livelli;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Mostro.*;
import Platform.Blocco;
import Platform.Moneta;
import Platform.PannelloDiGioco;

public class Livello1 implements Livello {

	private PannelloDiGioco pannello;
	private ArrayList<Blocco> blocchi;
	private ArrayList<Moneta> monete;
	int xSpawn;
	int ySpawn;
	Image background;
	
	private ArrayList<Mostro> mostri;
	
	String pwdLivello;
	
	public Livello1(PannelloDiGioco p)
	{
		pannello = p;
		blocchi = new ArrayList<Blocco>();
		mostri = new ArrayList<Mostro>();
		monete = new ArrayList<Moneta>();
		
		xSpawn = 400;
		ySpawn = 560;
		
		try {
			background = ImageIO.read(new File("background" + File.separator + 1 + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		caricaMostri();
		
		caricaMonete();
		
		
		generaLivello();
		
		pwdLivello = "begin";
		
	
	}

	public void caricaMonete()
	{
		//monete.clear();
		Moneta m1 = new Moneta(pannello, 800, 500);
		Moneta m2 = new Moneta(pannello, 850, 500);
		Moneta m3 = new Moneta(pannello, 900, 500);
		Moneta m4 = new Moneta(pannello, 950, 500);
		Moneta m5 = new Moneta(pannello, 3540, 340);
		Moneta m6 = new Moneta(pannello, 3560, 410);
		Moneta m7 = new Moneta(pannello, 3580, 480);
		Moneta m8 = new Moneta(pannello, 3600, 550);
		Moneta m9 = new Moneta(pannello, 4140, 550);
		Moneta m10 = new Moneta(pannello,4210, 550);
		Moneta m11 = new Moneta(pannello,4280, 550);
		Moneta m12 = new Moneta(pannello, 2600, 380);
		Moneta m13 = new Moneta(pannello, 2280, 400);
		Moneta m14 = new Moneta(pannello, 2350, 400);
		
		monete.add(m1);
		monete.add(m2);
		monete.add(m3);
		monete.add(m4);
		monete.add(m5);
		monete.add(m6);
		monete.add(m7);
		monete.add(m8);
		monete.add(m9);
		monete.add(m10);
		monete.add(m11);
		monete.add(m12);
		monete.add(m13);
		monete.add(m14);
		
		
		
		
		
		
		
		
	}

	public void caricaMostri() {
		mostri.clear();
		Mostro b1 = new Beshtia(2920,400,pannello);
		Mostro b2 = new Beshtia(3800,400,pannello);
		Mostro b3 = new Beshtia(900,600,pannello);
		Mostro b4 = new Beshtia(4000,400,pannello);
		
		mostri.add(b1);
		mostri.add(b2);
		mostri.add(b3);
		mostri.add(b4);
	
		
	}
	
	public void eliminaMostro(int i)
	{
		mostri.get(i).playSuonoMorte();
		mostri.remove(i);
		
	}
	

	@Override
	public void generaLivello() {
		

		int  matLivello [][] = 
					{{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,10,10,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,10,10,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,10,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-3,-3,-3,0,0,0,0,0},
					{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-3,0,0,0,0,0,0,0,10,10,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,3,0,0,0,0,0,1,2,2,2,2,2,2,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,11,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,3,0,0,0,0,0,5,6,0,0,0,0,0,5,4,4,4,4,4,4,6,0,0,0,0,0,0,0,0,0,0,0,-3,0,0,0,0,0,0,0,0,-3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,11,11,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,4,6,0,0,0,0,0,5,6,0,0,0,0,0,5,4,4,4,4,4,4,6,0,0,0,0,0,0,0,0,0,0,0,-3,0,0,0,0,0,0,0,0,-3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,10,0,0,0,0,0,0,0,0,0,0,0,0,-1,0,0},
					{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,11,11,11,0,0,0,0,0,0,0,0,0,-3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,3,0,0,0,0,5,4,6,0,0,0,0,0,5,6,0,0,0,0,0,5,4,4,4,4,4,4,6,0,0,0,0,0,0,0,0,0,0,-3,0,0,0,0,0,0,0,0,0,-3,0,0,0,10,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,10,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{ 0,0,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3,0,0,0,0,0,0,0,0,10,10,10,10,10,10,10,0,0,0,0,0,5,4,6,0,0,0,0,5,4,6,0,0,0,0,0,5,6,0,0,0,0,0,5,4,4,4,4,4,4,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,10,0,0,0,0,0,0,0,0,0,10,0,0,0,0,0,0,0,0,0,0,0,10,0,0,0,0,10,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{ 0,0,5,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,6,0,0,0,0,0,0,0,0,10,10,10,10,10,10,10,0,0,0,0,0,5,4,6,0,0,0,0,5,4,6,0,0,0,0,0,5,6,0,0,0,0,0,5,4,4,4,4,4,4,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3,0,0,0,0,-3,-3,-3,0,0,1,2,2,2,2,3,}};
		
		for (int  i = 0; i < 133; i+= 1)
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
	
	public ArrayList<Mostro> getMostri()
	{
		return mostri;
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
	
	public void disegna (Graphics2D g2d){
		g2d.drawImage(background, 0 , 0 , 1280, 720, null);

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
