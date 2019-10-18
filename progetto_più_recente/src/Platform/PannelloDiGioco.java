package Platform;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Livelli.*;

import Mostro.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class PannelloDiGioco extends JPanel implements ActionListener{

	Personaggio personaggio;
	int punteggio;
	
	volatile Boolean tornaAlMenu;
	
	
	private ArrayList<Livello> livelli;	
	private int indexLivelloCorrente;
	
	ArrayList<Thread> threadMostri;
	ArrayList<Thread> threadMonete;
	
	
	
	int visualeX;                  //posizione di tutti i blocchi rispetto allo schermo
	
	Timer timerDelGioco;
	
	
	String pwdAttuale;
	
	Image[] schermataLivello;
	
	Boolean schermata1;
	Boolean schermata2;
	Boolean schermata3;
	Boolean schermata4;
	Boolean schermata5;
	Boolean schermata6;
	
	int contaFrame;
	
	
	public PannelloDiGioco(String pwd) {
		
		schermata1 = true;
		schermata2 = false;
		schermata3 = false;
		schermata4 = false;
		schermata5 = false;
		schermata6 = false;
		
		schermataLivello = new Image[6];
		
		caricaSchermate();
		
		tornaAlMenu = false;
		
		punteggio = 0;
		visualeX = 150;
		
		setLivelli(new ArrayList<Livello>());
		
		pwdAttuale = pwd;
		caricaLivelli();
		

		
		threadMostri = new ArrayList<Thread>();
		threadMonete = new ArrayList<Thread>();
		
		personaggio = new Personaggio(getLivelli().get(getIndexLivelloCorrente()).getxSpawn(), getLivelli().get(getIndexLivelloCorrente()).getySpawn(), this);//respawn del personaggio
		Thread movimento = new Thread(personaggio);
		movimento.start();
		
		//AVVIO THREAD DEI MOSTRI DEL PRIMO LIVELLO
		assegnamentoThread();
	
		
		timerDelGioco = new Timer();
		
		contaFrame = 0;
		
		timerDelGioco.schedule(new TimerTask() {

			@Override
			public void run() {			//viene chiamato ogni volta che il timer finisce
				
				if (contaFrame == 300)
				{
					schermata1 = false;
					schermata2 = false;
					schermata3 = false;
					schermata4 = false;
					if (schermata5)
					{
						schermata5 = false;
						schermata6 = true;
					}
					
					else if (schermata6)
					{
						timerDelGioco.cancel();
						tornaAlMenu = true;
					}
					contaFrame = 0;
				}
				
				
				repaint();
				
				if (schermata1 || schermata2 || schermata3 || schermata4 || schermata5 || schermata6)
					contaFrame++;
				else 
				{
					contaFrame = 0;
					
					personaggio.muovi();//viene chiamato ad ogni frame per aggiornare la posizione del giocatore
					
					for (Blocco i: getLivelli().get(getIndexLivelloCorrente()).getBlocchi())
						i.set(visualeX);
					
					for (Moneta i : getLivelli().get(getIndexLivelloCorrente()).getMonete())
						i.set(visualeX);
					
					
					for (Mostro i : getLivelli().get(getIndexLivelloCorrente()).getMostri())
						i.muovi(visualeX);
				}
			}
			
		}, 0, 17); //0 corrisponde al tempo di inizio del timer e poi scandiamo l'intervallo tra i frame in ms
					// 17 =  1000/60 cioè i millisecondi in ogni frame (circa 60 fps)
	}
	
	private void assegnamentoThread() {
		
		//ARRAY MOSTRI
		for (int i = 0; i < getLivelli().get(getIndexLivelloCorrente()).getMostri().size(); i++)
		{
			Thread t = new Thread((Runnable) getLivelli().get(getIndexLivelloCorrente()).getMostri().get(i));
			threadMostri.add(t);
		}
		
		for (int i = 0; i < threadMostri.size(); i++)
			threadMostri.get(i).start();
		
		
		
		//ARRAY MONETE
		for (int i = 0; i < getLivelli().get(getIndexLivelloCorrente()).getMonete().size(); i++)
		{
			Thread t = new Thread(getLivelli().get(getIndexLivelloCorrente()).getMonete().get(i));
			threadMonete.add(t);
		}
		
		for (int i = 0; i < threadMonete.size(); i++)
			threadMonete.get(i).start();
		
	}

	private void caricaLivelli() {
		
		getLivelli().add(new Livello1(this));
		getLivelli().add(new Livello2(this));
		getLivelli().add(new Livello3(this));
		getLivelli().add(new Boss(this));
		
		for (int i = 0; i < livelli.size(); i++) {
	
			if (pwdAttuale.equals(livelli.get(i).getPwdLivello())) {
				
				setIndexLivelloCorrente(i);
				
				if (indexLivelloCorrente == 0)
				{
					schermata1 = true;
				}
				
				else if (indexLivelloCorrente == 1)
				{
					schermata2 = true;
				}
				
				else if (indexLivelloCorrente == 2)
				{
					schermata3 = true;
				}
				
				else if (indexLivelloCorrente == 3)
				{
					schermata4 = true;
				}
				
				
				
				if (indexLivelloCorrente == 3) {
					Menu.mainTheme.stop();
					Menu.bossTheme.stop();
					Menu.bossTheme.loop();
				
				}
				
				break;
			}
				
		}
		
		//setIndexLivelloCorrente(0);
			
		
	}

	public void respawn() {

		personaggio.setY(getLivelli().get(getIndexLivelloCorrente()).getySpawn());
		
		visualeX = 150;
		
		livelli.get(indexLivelloCorrente).caricaMostri();
		
		resetThreadMostri();

		
		personaggio.dx = 0;
		personaggio.dy = 0;
	}
	


	public void paint (Graphics g) {
		
		super.paint(g);
		
		Graphics2D grafica2d = (Graphics2D) g; // cast da Graphics a Graphics2d poichè paint riceve un oggetto
												//di tipo Graphics. Per funzionalità usiamo Graphics2d
		
		getLivelli().get(getIndexLivelloCorrente()).disegna(grafica2d);
		
		cambioLivello();
		
		
		
		for (Blocco i: getLivelli().get(getIndexLivelloCorrente()).getBlocchi()) // generazione blocchi del livello
			i.disegna(grafica2d);
		
		for (Moneta i : getLivelli().get(getIndexLivelloCorrente()).getMonete())
			i.disegna(grafica2d);
		
		
		
		if (punteggio < 0)
			punteggio = 0;
		grafica2d.setColor(Color.BLACK);
		grafica2d.drawString("Punteggio : " + punteggio, 1000, 50);
		
		personaggio.disegna(grafica2d);
		
		for (Mostro i : getLivelli().get(getIndexLivelloCorrente()).getMostri())	//ha dato errore !!!MO DI FI CA RE!!! CAPITO?
			i.disegna(grafica2d);
			
		if (schermata1)
			grafica2d.drawImage(schermataLivello[0], 0,0,1280,720,null);
		
		if (schermata2)
			grafica2d.drawImage(schermataLivello[1], 0,0,1280,720,null);
		
		if (schermata3)
			grafica2d.drawImage(schermataLivello[2], 0,0,1280,720,null);
		
		if (schermata4)
			grafica2d.drawImage(schermataLivello[3], 0,0,1280,720,null);
		
		if (schermata5)
			grafica2d.drawImage(schermataLivello[4], 0,0,1280,720,null);
		
		if (schermata6)
			grafica2d.drawImage(schermataLivello[5], 0,0,1280,720,null);
		
		
	}
	

	
	private void cambioLivello() {
		if (getLivelli().get(getIndexLivelloCorrente()).fineLivello()) {
			if (indexLivelloCorrente == 0)
				schermata2 = true;
			
			if (indexLivelloCorrente == 1)
				schermata3 = true;
			
			if (indexLivelloCorrente == 2)
				schermata4 = true;
			
			setIndexLivelloCorrente(getIndexLivelloCorrente() + 1); // cambio livello
			personaggio.setY(getLivelli().get(getIndexLivelloCorrente()).getySpawn());
			visualeX = 150;
			resetThreadMostri();	//al cambio di livelli, l'array si pulisce e ricrea i thread del livello appena cambiato
			resetThreadMonete();
			
			if (indexLivelloCorrente == 3) {
				Menu.mainTheme.stop();
				Menu.bossTheme.stop();
				Menu.bossTheme.loop();
			
			}
				
		}
		
	}

	private void resetThreadMostri() {
		

		threadMostri.clear();
		
		for (int i = 0; i < getLivelli().get(getIndexLivelloCorrente()).getMostri().size(); i++)
		{
			Thread t = new Thread((Runnable) getLivelli().get(getIndexLivelloCorrente()).getMostri().get(i));
			threadMostri.add(t);
		}
		
		for (int i = 0; i < threadMostri.size(); i++)
			threadMostri.get(i).start();
		
	}
	
	private void resetThreadMonete() {
		

		
		threadMonete.clear();
		
		for (int i = 0; i < getLivelli().get(getIndexLivelloCorrente()).getMonete().size(); i++)
		{
			Thread t = new Thread(getLivelli().get(getIndexLivelloCorrente()).getMonete().get(i));
			threadMonete.add(t);
		}
		
		for (int i = 0; i < threadMonete.size(); i++)
			threadMonete.get(i).start();
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}

	public void tastoPremuto(KeyEvent e) {
		//movimento personaggio che controlliamo
		if (e.getKeyCode() == KeyEvent.VK_Z ) personaggio.MUOVI_SOPRA = true;
		if (e.getKeyCode() == KeyEvent.VK_LEFT ) personaggio.MUOVI_SINISTRA = true;
	//	if (e.getKeyChar() == 's') personaggio.MUOVI_SOTTO = true;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT ) personaggio.MUOVI_DESTRA = true;
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE ) 
		{
			timerDelGioco.cancel();
			tornaAlMenu = true;
		}
		
	}

	public Boolean getTornaAlMenu() {
		
		return tornaAlMenu;
	}

	public void setTornaAlMenu(Boolean tornaAlMenu) {
		Menu.bossTheme.stop();
		Menu.mainTheme.loop();
		this.tornaAlMenu = tornaAlMenu;
	}

	public void tastoRilasciato(KeyEvent e) {
		//movimento personaggio che controlliamo
		if (e.getKeyCode() == KeyEvent.VK_Z ) personaggio.MUOVI_SOPRA = false;
		if (e.getKeyCode() == KeyEvent.VK_LEFT ) personaggio.MUOVI_SINISTRA = false;
		if (e.getKeyChar() == 's') personaggio.MUOVI_SOTTO = false;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT ) personaggio.MUOVI_DESTRA = false;
		
	}
	
	public Personaggio getPersonaggio()
	{
		return personaggio;
	}

	public ArrayList<Livello> getLivelli() {
		return livelli;
	}

	public void setLivelli(ArrayList<Livello> livelli) {
		this.livelli = livelli;
	}

	public int getIndexLivelloCorrente() {
		return indexLivelloCorrente;
	}

	public void setIndexLivelloCorrente(int indexLivelloCorrente) {
		this.indexLivelloCorrente = indexLivelloCorrente;
	}
	
	public void caricaSchermate()
	{
		try {
			for (int i = 0; i < 6; i++)
			{
			  schermataLivello[i] = ImageIO.read(new File("schermateLivello" + File.separator+ i  + ".jpg"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Boolean getSchermata5() {
		return schermata5;
	}

	public void setSchermata5(Boolean schermata5) {
		this.schermata5 = schermata5;
	}

	
}
