package Platform;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;


import Mostro.*;

public class Personaggio implements Runnable {

	PannelloDiGioco pannello;
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
	
	Personaggio(int _x, int _y, PannelloDiGioco p) {
		pannello = p; // la classe riceve il pannello di gioco come parametro in quanto esso contiene
								  // funzioni e variabili che devono essere accessibili dal personaggio
		x = _x;
		y = _y;
		
		width = 64; //dell'immagine da caricare
		height = 64;
		
		sprite = new ArrayList<Image>();
		caricaSprite();
		indice = 0;
		imgCorrente = sprite.get(indice);
		
		caricaAudio();
		
		correndo = false;
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
		if (MUOVI_SINISTRA && MUOVI_DESTRA || !MUOVI_SINISTRA && ! MUOVI_DESTRA) { //se non premo tasti / premo entrambe le 
			dx *= 0.5; 																//direzioni, il personaggio rallenta
			correndo = false;
			correndoSx = false;
		}
			
		else if (MUOVI_SINISTRA && !MUOVI_DESTRA) {
			correndoSx = true;
			correndo = false;
			dx = dx - 0.4;
		}
			
		else if (MUOVI_DESTRA && !MUOVI_SINISTRA) {
			correndo = true;
			correndoSx = false;
			dx = dx + 0.4;
		}
			
		
		//azzeramento velocità
		if (dx > 0 && dx < 0.2)
			dx = 0;
		if (dx < 0 && dx > -0.2) 
			dx = 0;
		
		//velocità massima
		if (dx > 7)
			dx = 7;
		if (dx < -7)
			dx = -7;
		
		if (MUOVI_SOPRA) { 				//SALTO
										//controlliamo se sta toccando il terreno
			
			hitBox.y ++;				//mandiamo la hitbox giù 
			saltandoSu = true;		
			
			
			
			for (Blocco i : pannello.getLivelli().get(pannello.getIndexLivelloCorrente()).getBlocchi())	
				if(i.hitBox.intersects(hitBox) && i.tipo >= 1) {
				
					jump.play();
					dy = -8;
				}
					
			
			
			//controlliamo che ogni blocco non tocchi il personaggio
			//se la hitbox incrementata di uno in direzione verticale
			//interseca un blocco allora possiamo saltare
					
			
			hitBox.y --;				//ripristiniamo la posizione della hitbox
			
			
		}
		
		
	
		dy += 0.3; // forza di gravità
		
		
		//COLLISIONE ORIZZONTALE
		hitBox.x += dx;         // mandiamo avanti la hitbox, in modo da prevedere dove sarà al prossimo frame
		
		for (Blocco i : pannello.getLivelli().get(pannello.getIndexLivelloCorrente()).getBlocchi()) 
		{
			if (hitBox.intersects(i.hitBox) && i.tipo >= 1) 
			{
				hitBox.x -= dx;	//se ha trovato un blocco che interseca, ripristino posizione della hitbox				
				dx = 0;							//se ha trovato un blocco intersecato, allora impedisce al player di muoversi
				hitBox.x = x; //la hitBox e la posizione x devono essere uguali (la x non si muove)
			}
		}
		
		//La hitbox del personaggio viene mandata avanti a controllare la presenza di un blocco.
		//Con il for scorriamo tutti i blocchi e nel caso in cui ci sia una intersezione facciamo ritornare
		//la hitbox nella posizione precedente e azzeriamo la velocità. La coordinata del personaggio
		//diventa la stessa di quella della sua hitbox.(Stessa cosa per la collisione verticale)
		
		//COLLISIONE VERTICALE
		hitBox.y += dy;
		for (Blocco i : pannello.getLivelli().get(pannello.getIndexLivelloCorrente()).getBlocchi()) 
		{
					if (hitBox.intersects(i.hitBox) && i.tipo >= 1) 
					{
							hitBox.y -= dy;
							dy = 0;
							hitBox.y = y;
					}
		}
		
		
		//controllo morte
		// VERTICALE
		
		boolean morto = false;
		
		hitBox.y += dy;
		
		for (Mostro i : pannello.getLivelli().get(pannello.getIndexLivelloCorrente()).getMostri()) 
		{
			
			
					
					if (hitBox.intersects(i.getHitBox()))
					{
						hitBox.y -= dy;
						morto = true;
						hitBox.y = y;
					}
			
		}
		
		hitBox.x += dx;
		
		for (Mostro i : pannello.getLivelli().get(pannello.getIndexLivelloCorrente()).getMostri()) 
		{
			
					if (hitBox.intersects(i.getHitBox()))
					{
						hitBox.x -= dx;
						morto = true;
						hitBox.x = x;
					}
			
		}
		
		//monete
		
		
		
		int indexMoneta=0; //indice della moneta da eliminare
		int iEliminaMoneta = 0;
		boolean presa=false;
		
		hitBox.y += dy;
		for (Moneta i : pannello.getLivelli().get(pannello.getIndexLivelloCorrente()).getMonete()) 
		{
			
					if (hitBox.intersects(i.getHitBox()))
					{
												
						hitBox.y -= dy;
						iEliminaMoneta = indexMoneta;
						presa = true;
						
						
						hitBox.y = y;
					}
			
					++indexMoneta;
		}
		
		if(presa)
		{
		pannello.getLivelli().get(pannello.getIndexLivelloCorrente()).eliminaMoneta(iEliminaMoneta);
		pannello.punteggio+=15;
		urlCoin = Menu.class.getResource("ost/coin.wav");
		coin = Applet.newAudioClip(urlCoin);
		coin.play();
		}
		
		indexMoneta=0; //indice della moneta da eliminare
		iEliminaMoneta = 0;
		presa=false;
		
		hitBox.x += dx;
		for (Moneta i : pannello.getLivelli().get(pannello.getIndexLivelloCorrente()).getMonete()) 
		{
			
					if (hitBox.intersects(i.getHitBox()))
					{
						
						hitBox.x -= dx;
						iEliminaMoneta = indexMoneta;
						presa = true;
						
						
						hitBox.x = x;
					}
			
					++indexMoneta;
		}
		
		if(presa)
		{
		pannello.getLivelli().get(pannello.getIndexLivelloCorrente()).eliminaMoneta(iEliminaMoneta);
		pannello.punteggio+=15;
		urlCoin = Menu.class.getResource("ost/coin.wav");
		coin = Applet.newAudioClip(urlCoin);
		coin.play();
		}
		
		
		
		
		//kill nemico
		
		if (!morto)
		{
		int index=0; //indice del nemico da eliminare
		int iElimina = 0;
		boolean kill=false;
		
		hitBox.y += dy;
		for (Mostro i : pannello.getLivelli().get(pannello.getIndexLivelloCorrente()).getMostri()) 
		{
			
					if (
						(	hitBox.intersectsLine(i.getX()+20 , i.getY()-0.5 , i.getX()+80 , i.getY()-0.5) && (i instanceof Beshtia) )
						|| (  hitBox.intersectsLine(i.getX()+5 , i.getY()-0.5 , i.getX()+35 , i.getY()-0.5) && (i instanceof Shpettro)) 
						|| (  hitBox.intersectsLine(i.getX()+5 , i.getY()-0.5 , i.getX()+35 , i.getY()-0.5) && (i instanceof ShpettroVerticale)
							))
					{
					
						if(i instanceof ShpettroVerticale)
						dy = -10;
						else
						dy = -6;
						
						pannello.punteggio+=50;
						hitBox.y -= dy;
						iElimina = index;
						kill = true;
						
						
						hitBox.y = y;
					}
			
					++index;
		}
		
		if(kill)
		pannello.getLivelli().get(pannello.getIndexLivelloCorrente()).eliminaMostro(iElimina);
		
		}
		
		//kill BOSS
		
		int indexBoss = 0;
		int iEliminaBoss = 0;
		boolean danneggiaBoss = false;
		
		if (!morto)
		{
			
			
			hitBox.y += dy;
			for (Mostro i : pannello.getLivelli().get(pannello.getIndexLivelloCorrente()).getMostri()) 
			{
				
						if (hitBox.intersectsLine(i.getX()+20 , i.getY()-0.2 , i.getX()+120 , i.getY()-0.2) && (i instanceof Minotauro))
						{
						
							dy = -16;
						
							hitBox.y -= dy;
							
							iEliminaBoss = indexBoss;
							danneggiaBoss = true;
							hitBox.y = y;
						}
						
						indexBoss++;
				
			}			
			
		}
		
		if(danneggiaBoss)
			pannello.getLivelli().get(pannello.getIndexLivelloCorrente()).getMostri().get(iEliminaBoss).danneggia();
			
		
		pannello.visualeX -= dx;
		//x += dx;
		y += dy;
		
		hitBox.x = x;
		hitBox.y = y;
		
		// MORTE
		if (y > pannello.getHeight() + 100 || morto)
		{
			death.play();
			pannello.respawn();
			pannello.punteggio-=100;
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
