package Platform;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Endless.ControlloTastiEndless;
import Endless.PannelloEndless;
import Livelli.Livello;
import Livelli.Livello1;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.event.KeyListener;
import java.util.ArrayList;


public class MainFrame extends JFrame implements Runnable{

	Menu menu;
	
	PannelloDiGioco pannello;
	
	PannelloDiGioco pannello2;
	
	PannelloEndless pannelloEndless;
	
	Guida guida;
	
	public static Boolean sonoInEndless;
	
	Boolean controllaGuida;
	
	Boolean stoGiocando;
	
	Boolean stoGiocando2;
	
	Boolean carico;
	
	ArrayList<String> password;
	
	public MainFrame() {
		
		
		stoGiocando2 = false;
		
		carico = false;
		
		sonoInEndless = false;
		
		stoGiocando = false;
		
		controllaGuida = false;
			
		menu = new Menu (1280,720);
		
		menu.setLocation(0, 0);
		menu.setSize(this.getSize());
		menu.setVisible(true);
		this.add(menu);
		Thread t = new Thread(this);
		t.start();
	}


	@Override
	public void run() {
			while (true)
			{
				
				if (menu.getAvviaGioco())
				{
					
					menu.setAvviaGioco(false);
					stoGiocando = true;
					pannello = new PannelloDiGioco("livello 1");
					pannello.setLocation(0, 0);
					pannello.setSize(this.getSize());
					pannello.setVisible(true);
					this.setContentPane(pannello);
					this.invalidate();
					this.validate();
					removeKeyListener(menu);
					addKeyListener(new ControlloTasti(pannello));
					pannello.setFocusable(true);
					requestFocusInWindow();
				}
				
				else if (menu.getVediGuida())
				{
					menu.setVediGuida(false);
					controllaGuida = true;
					guida = new Guida(); 
					guida.setLocation(0, 0);
					guida.setSize(this.getSize());
					guida.setVisible(true);
					this.setContentPane(guida);
					this.invalidate();
					this.validate();
					removeKeyListener(menu);
					addKeyListener(guida);
					guida.setFocusable(true);
					requestFocusInWindow();
				}
				
				else if (menu.getModalitaInfinita())
				{
					
					menu.setModalitaInfinita(false);
					sonoInEndless = true;
					pannelloEndless = new PannelloEndless(); 
					pannelloEndless.setLocation(0, 0);
					pannelloEndless.setSize(this.getSize());
					pannelloEndless.setVisible(true);
					this.setContentPane(pannelloEndless);
					this.invalidate();
					this.validate();
					removeKeyListener(menu);
					addKeyListener(new ControlloTastiEndless(pannelloEndless));
					pannelloEndless.setFocusable(true);
					requestFocusInWindow();
				}
				
				else if (menu.getCaricaGioco())
				{
					
					
					

					String password = sistemaCaricamento();
					
					menu.setCaricaGioco(false);
					stoGiocando2 = true;
					pannello2 = new PannelloDiGioco(password);
					pannello2.setLocation(0, 0);
					pannello2.setSize(this.getSize());
					pannello2.setVisible(true);
					this.setContentPane(pannello2);
					this.invalidate();
					this.validate();
					removeKeyListener(menu);
					addKeyListener(new ControlloTasti(pannello2));
					pannello2.setFocusable(true);
					requestFocusInWindow();
				}
				
				else if (controllaGuida)
				{
					if (guida.getTornaAlMenu())
					{
						controllaGuida = false;
						guida.setTornaAlMenu(false);
						menu.setVisible(true);
						this.setContentPane(menu);
						this.invalidate();
						this.validate();
						removeKeyListener(guida);
						addKeyListener(menu);
						menu.setFocusable(true);
						requestFocusInWindow();
					}
				}
				
				else if (stoGiocando)
				{
					if(pannello.getTornaAlMenu())
					{
						stoGiocando = false;
						pannello.setTornaAlMenu(false);
						menu.setVisible(true);
						this.setContentPane(menu);
						this.invalidate();
						this.validate();

						addKeyListener(menu);
						menu.setFocusable(true);
						requestFocusInWindow();
					}
				}
				
				else if (stoGiocando2)
				{
					if(pannello2.getTornaAlMenu())
					{
						stoGiocando2 = false;
						pannello2.setTornaAlMenu(false);
						menu.setVisible(true);
						this.setContentPane(menu);
						this.invalidate();
						this.validate();

						addKeyListener(menu);
						menu.setFocusable(true);
						requestFocusInWindow();
					}
				}
				
				else if (sonoInEndless)
				{
					if(pannelloEndless.getTornaAlMenu())
					{
						sonoInEndless = false;
						pannelloEndless.setTornaAlMenu(false);
						menu.setVisible(true);
						this.setContentPane(menu);
						this.invalidate();
						this.validate();
						addKeyListener(menu);
						menu.setFocusable(true);
						requestFocusInWindow();
					}
				}
			}
	}

	private String sistemaCaricamento() {
		
		
		String password = JOptionPane.showInputDialog("Inserisci password livello");
		
		return password;
	}
	
}
