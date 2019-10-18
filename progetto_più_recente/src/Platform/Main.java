package Platform;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;


public class Main {

	public static void main(String[] args) {
		
		MainFrame framePrincipale = new MainFrame();
		
		framePrincipale.setSize(1280,720); // risoluzione frame
	

	
		Dimension dimensioneSchermo = Toolkit.getDefaultToolkit().getScreenSize();
		framePrincipale.setLocation((int)(dimensioneSchermo.getWidth()/2 - framePrincipale.getSize().getWidth()/2),(int)(dimensioneSchermo.getHeight()/2 - framePrincipale.getSize().getHeight()/2));
		// posizionamento schermo (centrale)
	
		framePrincipale.setResizable(false);
		framePrincipale.setTitle("Kill Tauro");
	
	
	
		framePrincipale.setVisible(true);
		framePrincipale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
