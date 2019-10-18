package Endless;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ControlloTastiEndless extends KeyAdapter {
	
	PannelloEndless pannello;
	
	public ControlloTastiEndless(PannelloEndless p) {
		
		pannello = p;
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		pannello.tastoPremuto(e);
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		pannello.tastoRilasciato(e);
	}
	
	

}