package Endless;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Platform.Blocco;
import Platform.MainFrame;
import Platform.Menu;
import Platform.PannelloDiGioco;

public class PersonaggioEndless implements Runnable {

	PannelloEndless pannello;
	int x;
	int y;
	int width;
	int height;
	
	double  dx,dy;  //vettori direzione velocità
	
	Rectangle hitBox;
	ArrayList<Image> sprite;
	int indice;
	Image imgCorrente;
	Image saltoSu;
	

	boolean correndo;
	boolean correndoSx;
	boolean saltandoSu;
	
	boolean MUOVI_DESTRA;
	boolean MUOVI_SINISTRA;
	boolean MUOVI_SOPRA;
	boolean MUOVI_SOTTO;
	
	URL urlJump;
    AudioClip jump;
	URL urlCoin;
	AudioClip coin;
	URL urlDeath;
	AudioClip death;
	
	PersonaggioEndless(int _x, int _y, PannelloEndless p) {
		pannello = p; // la classe riceve il pannello di gioco come parametro in quanto esso contiene
								  // funzioni e variabili che devono essere accessibili dal personaggio
		x = _x;
		y = _y;
		
		dx = 3;
		
		width = 64; //dell'immagine da caricare
		height = 64;
		
		sprite = new ArrayList<Image>();
		caricaSprite();
		indice = 0;
		imgCorrente = sprite.get(indice);
		
		caricaAudio();
		
		correndo = true;
		correndoSx = false;
		saltandoSu = false;
		

		
		hitBox = new Rectangle(x, y, 32, 57);
	}
	
	private void caricaAudio() {
		urlJump = Menu.class.getResource("ost/jump.wav");
		jump = Applet.newAudioClip(urlJump);
		urlDeath = Menu.class.getResource("ost/death.wav");
		death = Applet.newAudioClip(urlDeath);

		
	}

	private void caricaSprite() {
		for (int i = 0; i < 7; i++) {
			try {
				sprite.add(ImageIO.read(new File("player" + File.separator + i + ".png")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				saltoSu = ImageIO.read(new File("player" + File.separator + "Jump0.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}
	
	public void muovi () {
		
		if(MainFrame.sonoInEndless)
		dx = dx + 0.8;
		
		if (dx > 6)
			dx = 6;
		
		if (MUOVI_SOPRA) { 				//SALTO
			//controlliamo se sta toccando il terreno

				hitBox.y ++;				//mandiamo la hitbox giù 
				saltandoSu = true;		



				for (Blocco i : pannello.blocchi)	
					if(i.getHitBox().intersects(hitBox) && i.getTipo() >= 1) 
					{

						jump.play();
						dy = -8;
					}



				hitBox.y --;				//ripristiniamo la posizione della hitbox


			}



		dy += 0.3; // forza di gravità
		
		//COLLISIONE ORIZZONTALE
				hitBox.x += dx;         // mandiamo avanti la hitbox, in modo da prevedere dove sarà al prossimo frame
				
				for (Blocco i : pannello.blocchi) 
				{
					if (hitBox.intersects(i.getHitBox()) && i.getTipo() >= 1) 
					{
						hitBox.x -= dx;	//se ha trovato un blocco che interseca, ripristino posizione della hitbox
						
						dx = 0;							//se ha trovato un blocco intersecato, allora impedisce al player di muoversi
						hitBox.x = x; //la hitBox e la posizione x devono essere uguali (la x non si muove)
					}
				}
				
				
				//COLLISIONE VERTICALE
				hitBox.y += dy;
				for (Blocco i : pannello.blocchi) 
				{
							if (hitBox.intersects(i.getHitBox()) && i.getTipo() >= 1) 
							{
								hitBox.y -= dy;
									
									dy = 0;
									hitBox.y = y; // y = hitBox.y;
							}
				}
				
				
				pannello.visualeX -= dx;
				//x += dx;
				y += dy;
				
				hitBox.x = x;
				hitBox.y = y;
				
				// MORTE
				if (y > pannello.getHeight() + 100)
				{
					death.play();
					pannello.respawn();
				}
		
		
		
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void disegna (Graphics2D g2d)
	{
		if (correndoSx)
			g2d.drawImage(imgCorrente, x + 47, y - 8, -width, height, null); // girato a sx (flip)
		else 
			g2d.drawImage(imgCorrente, x  - 15, y - 8, width, height, null); //-15 e -8 usate come costanti per allineare hitbox e sprite
	}

	@Override
	public void run() {
		while (true)
		{
			if (indice >= 6)
				indice = 0;
			
			if(correndo || correndoSx) 
				++indice;
			else 
				indice = 0;
				
			if (saltandoSu) {
				imgCorrente = saltoSu;
				saltandoSu = false;
			}
				
			else
				imgCorrente = sprite.get(indice);

			
				try {
					Thread.sleep(60);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public Rectangle getHitBox() {
		return hitBox;
	}

	public double getDx() {
		// TODO Auto-generated method stub
		return dx;
	}
	
	
}