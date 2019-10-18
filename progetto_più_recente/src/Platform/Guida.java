package Platform;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Guida extends JPanel implements KeyListener{

	Image guida;
	
	volatile Boolean tornaAlMenu;
	
	public Boolean getTornaAlMenu() {
		return tornaAlMenu;
	}



	public void setTornaAlMenu(Boolean tornaAlMenu) {
		this.tornaAlMenu = tornaAlMenu;
	}



	public Guida() {
		super();
		
		tornaAlMenu = false;
		
		this.addKeyListener(this);
		
		this.setFocusable(true);
		caricaGuida();
	}
	
	
	
	private void caricaGuida() {
		try {
			guida = ImageIO.read(new File("title" + File.separator  + "comandi.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void disegnaGuida (Graphics2D g2d){
		
		g2d.drawImage(guida, 0, 0, 1280, 720, null);	
	}

	public void paint (Graphics g) {
		
		super.paint(g);
		
		Graphics2D grafica2d = (Graphics2D) g; // cast da Graphics a Graphics2d poichè paint riceve un oggetto
												//di tipo Graphics. Per funzionalità usiamo Graphics2d
		disegnaGuida(grafica2d);
		
	}



	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		if ( e.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			tornaAlMenu = true;
		}
	}



	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
