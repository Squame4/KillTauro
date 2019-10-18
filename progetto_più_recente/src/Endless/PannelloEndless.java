package Endless;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Livelli.Livello;
import Mostro.Mostro;
import Platform.Blocco;
import Platform.Menu;
import Platform.Moneta;
import Platform.Personaggio;

public class PannelloEndless extends JPanel implements ActionListener{

	PersonaggioEndless personaggio;
	int punteggio;
	
	volatile Boolean tornaAlMenu;

	ArrayList<Blocco> blocchi;
	
	
	
	int visualeX;                  //posizione di tutti i blocchi rispetto allo schermo
	int offset;
	
	Timer timerDelGioco;
	
	Image background;
	
	public PannelloEndless() {
		
		try {
			background = ImageIO.read(new File("background" + File.separator + 1 + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		tornaAlMenu = false;
		
		punteggio = 0;
		visualeX = 150;
		
		blocchi = new ArrayList<Blocco>();
			
		personaggio = new PersonaggioEndless(300,-200, this);//respawn del personaggio
		Thread movimento = new Thread(personaggio);
		movimento.start();
		
		respawn();
		
		timerDelGioco = new Timer();
		timerDelGioco.schedule(new TimerTask() {

			@Override
			public void run() {			//viene chiamato ogni volta che il timer finisce
				
				if (blocchi.get(blocchi.size()-1).getX() < 1280)
				{
					
					offset += 1180; // aggiorno la prossima pos ideale dove creare i blocchi
					caricaBlocchi(offset);
				
					
				}
			
				
				for (Blocco i: blocchi)
					i.set(visualeX);
		
				
			for (Iterator<Blocco> it = blocchi.iterator(); it.hasNext();)
				{
					Blocco bloccoDaRimuovere = it.next();
					if (bloccoDaRimuovere.getX() < -80)
					 it.remove();
				
					
				}
				
				personaggio.muovi();
					
				repaint();
				
				
				
			}
			
		}, 0, 17); //0 corrisponde al tempo di inizio del timer e poi scandiamo l'intervallo tra i frame in ms
					// 17 =  1000/60 cioè i millisecondi in ogni frame (circa 60 fps)
	}
	

	private void caricaBlocchi(int offset) {
		// TODO Auto-generated method stub
		
		int s = 40;
		
		Random rand = new Random();
		int indice = rand.nextInt(6);
		
		if(indice == 0)
			for (int i = 0; i < 30; ++i)
				blocchi.add(new Blocco(offset + i*s, 600, 40, 40 ,10));
		else if (indice == 1)
		{
			for (int i = 0; i < 30; ++i)
			{
				if(i<13)
					blocchi.add(new Blocco(offset + i*s, 500, 40, 40 ,10));
				if(i>=13 && i<19)
					blocchi.add(new Blocco(offset + i*s, 400, 40, 40 ,10));
				if(i >=19)
					blocchi.add(new Blocco(offset + i*s, 500, 40, 40 ,10));
				
			}
			
			
			
		}			
		else if (indice == 2)
		{
			
			for (int i = 0; i < 30; ++i)
			{
				if(i<10)
					blocchi.add(new Blocco(offset + i*s, 500, 40, 40 ,10));
				if(i>=10 && i<25)
					blocchi.add(new Blocco(offset + i*s, 400, 40, 40 ,10));
				if(i >=25)
					blocchi.add(new Blocco(offset + i*s, 300, 40, 40 ,10));
				
			}
			
			
		}
		
		else if (indice == 3)
		{
			
			for (int i = 0; i < 30; ++i)
			{
				if(i<10)
					blocchi.add(new Blocco(offset + i*s, 500, 40, 40 ,10));
				if(i>=10 && i<20)
					blocchi.add(new Blocco(offset + i*s, 400, 40, 40 ,10));
				if(i >=20)
					blocchi.add(new Blocco(offset + i*s, 600, 40, 40 ,10));
				
			}
		
		}
		else if (indice == 4)
		{
			
			for (int i = 0; i < 30; ++i)
			{
					if(i<27)
					blocchi.add(new Blocco(offset + i*s, 500, 40, 40 ,10));
					
					if(i>3 && i<27)
						blocchi.add(new Blocco(offset + i*s, 460, 40, 40 ,10));
					if(i>6 && i<24)
						blocchi.add(new Blocco(offset + i*s, 420, 40, 40 ,10));
						
				
			}
		
		}
		
		else if (indice == 5)
		{
			
			for (int i = 0; i < 30; ++i)
			{
					
				if (i != 3 && i!= 6 && i != 8 && i!= 11 &&i != 15 && i!= 16 && i<20)
				blocchi.add(new Blocco(offset + i*s, 450, 40, 40 ,10));
				
				if(i> 4 && i<27)
				blocchi.add(new Blocco(offset + i*s, 250, 40, 40 ,10));
				
				if ( i > 24)
					blocchi.add(new Blocco(offset + i*s, 600, 40, 40 ,10));
				
				if ( i > 28)
					blocchi.add(new Blocco(offset + i*s, 560, 40, 40 ,10));
				
				if ( i!= 11 &&i != 12 && i!= 18 &&i != 23 && i!= 27)
				blocchi.add(new Blocco(offset + i*s, 650, 40, 40 ,10));
						
				
			}
		
		}
		
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
public void paint (Graphics g) {
		
		super.paint(g);
		
		Graphics2D grafica2d = (Graphics2D) g; // cast da Graphics a Graphics2d poichè paint riceve un oggetto
												//di tipo Graphics. Per funzionalità usiamo Graphics2d
		
		g.drawImage(background, 0 , 0 , 1280, 720, null);
		
		for (int i = 0; i < blocchi.size(); ++i) // generazione blocchi del livello
			blocchi.get(i).disegna(grafica2d);
		
		
		if (offset > 1500)
			punteggio = offset/10;
		
		grafica2d.setColor(Color.BLACK);
		grafica2d.drawString("Punteggio : " + punteggio, 1000, 50);
		
		personaggio.disegna(grafica2d);
		
		
		
	}


public void tastoPremuto(KeyEvent e) {
	// TODO Auto-generated method stub
	
	if (e.getKeyCode() == KeyEvent.VK_Z ) personaggio.MUOVI_SOPRA = true;
	if (e.getKeyCode() == KeyEvent.VK_ESCAPE ) 
	{	
		tornaAlMenu = true;
		timerDelGioco.cancel();
	}
	
	
}


public void tastoRilasciato(KeyEvent e) {
	// TODO Auto-generated method stub
	
	if (e.getKeyCode() == KeyEvent.VK_Z ) personaggio.MUOVI_SOPRA = false;
	
}

public Boolean getTornaAlMenu() {
	
	return tornaAlMenu;
}

public void setTornaAlMenu(Boolean tornaAlMenu) {

	this.tornaAlMenu = tornaAlMenu;
}


public void respawn() 
{
	
	
		personaggio.setX(300);
		personaggio.setY(-200);
		
		visualeX = 150;
		
		
		personaggio.dx = 0;
		personaggio.dy = 0;
		
		punteggio = 0;
		
		
		
		blocchi.clear();
		
		offset = -150;
		caricaBlocchi(offset);
	
}


}