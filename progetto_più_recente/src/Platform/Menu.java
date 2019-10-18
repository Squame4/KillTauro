package Platform;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Menu extends JPanel implements KeyListener{

	Image background;
	Image freccia;
	Image tasti;
	Boolean[] indicatore;
	volatile Boolean avviaGioco;
	volatile Boolean caricaGioco;
	volatile Boolean modalitaInfinita;
	volatile Boolean vediGuida;
	
	URL urlMainTheme;
	static AudioClip mainTheme;
	URL urlBossTheme;
	static AudioClip bossTheme;
	
	
	int larghezza;
	int altezza;
	
	public Menu(int larghezzaFrame,int altezzaFrame) {
		super();
		avviaGioco = false;
		caricaGioco = false;
		modalitaInfinita = false;
		vediGuida = false;
		
		larghezza = larghezzaFrame;
		altezza =altezzaFrame;
		
		caricaTheme();
		
		
		
		indicatore = new Boolean[5];
		indicatore [0] = true;
		for (int i = 1; i<5;i++)
			indicatore[i] = false;
		
		this.addKeyListener(this);
		
		this.setFocusable(true);
	
		caricaImmagini();
	}
	
	private void caricaTheme() {
		urlMainTheme = Menu.class.getResource("ost/maintheme.wav");
		mainTheme = Applet.newAudioClip(urlMainTheme);
		urlBossTheme = Menu.class.getResource("ost/bosstheme.wav");
		bossTheme = Applet.newAudioClip(urlBossTheme);
		mainTheme.loop();
		
		
	}

	public Boolean getCaricaGioco() {
		return caricaGioco;
	}

	public void setCaricaGioco(Boolean caricaGioco) {
		this.caricaGioco = caricaGioco;
	}

	public Boolean getModalitaInfinita() {
		return modalitaInfinita;
	}

	public void setModalitaInfinita(Boolean modalitaInfinita) {
		this.modalitaInfinita = modalitaInfinita;
	}

	public Boolean getVediGuida() {
		return vediGuida;
	}

	public void setVediGuida(Boolean vediGuida) {
		this.vediGuida = vediGuida;
	}

	public void caricaImmagini()
	{
		try {
			background = ImageIO.read(new File("title" + File.separator  + "titlescreen.jpg"));
			freccia = ImageIO.read(new File("title" + File.separator  + "freccina.png"));
			tasti = ImageIO.read(new File("title" + File.separator  + "scritte.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void disegnaTasti (Graphics2D g2d){
		g2d.drawImage(tasti,  25, 30, 1280, 720, null);	
	}
	
	public void disegnaBackground (Graphics2D g2d){
		g2d.drawImage(background, 0 , 0 , larghezza, altezza - 20, null);	
	}
	
	public void disegnaFreccia (Graphics2D g2d){
		if (indicatore[0])
			g2d.drawImage(freccia,  larghezza/2-175, altezza/3, 50, 50, null);
		if (indicatore[1])
			g2d.drawImage(freccia,  larghezza/2-175, altezza/3+80, 50, 50, null);
		if (indicatore[2])
			g2d.drawImage(freccia,  larghezza/2-175, altezza/3+160, 50, 50, null);
		if (indicatore[3])
			g2d.drawImage(freccia,  larghezza/2-175, altezza/3+240, 50, 50, null);
		if (indicatore[4])
			g2d.drawImage(freccia,  larghezza/2-175, altezza/3+320, 50, 50, null);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D grafica2d = (Graphics2D) g;
		disegnaBackground(grafica2d);
		disegnaTasti(grafica2d);
		disegnaFreccia(grafica2d);
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			if (indicatore[0] == true)
			{
				indicatore[0] = false;
				indicatore[1] = true;
			}
			
			else if (indicatore[1] == true)
			{
				indicatore[1] = false;
				indicatore[2] = true;
			}
			
			else if (indicatore[2] == true)
			{
				indicatore[2] = false;
				indicatore[3] = true;
			}
			
			else if (indicatore[3] == true)
			{
				indicatore[3] = false;
				indicatore[4] = true;
			}
			
			else if (indicatore[4] == true)
			{
				indicatore[4] = false;
				indicatore[0] = true;
			}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			if (indicatore[0] == true)
			{
				indicatore[0] = false;
				indicatore[4] = true;
			}
			
			else if (indicatore[1] == true)
			{
				indicatore[1] = false;
				indicatore[0] = true;
			}
			
			else if (indicatore[2] == true)
			{
				indicatore[2] = false;
				indicatore[1] = true;
			}
			
			else if (indicatore[3] == true)
			{
				indicatore[3] = false;
				indicatore[2] = true;
			}
			
			else if (indicatore[4] == true)
			{
				indicatore[4] = false;
				indicatore[3] = true;
			}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			if (indicatore[0])
			{
				avviaGioco = true;
			}
			
			if (indicatore[1])
			{
				caricaGioco = true;
			}
			
			if (indicatore[2])
			{
				modalitaInfinita = true;
			}
			
			if (indicatore[3])
			{
				vediGuida = true;
			}
			
			if (indicatore[4])
			{
				avviaGioco = false;
				System.exit(0);
			}
		}
		
		repaint();
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public Boolean getAvviaGioco()
	{
		return avviaGioco;
	}
	
	public void setAvviaGioco(Boolean bool)
	{
		avviaGioco = bool;
	}
	

}
