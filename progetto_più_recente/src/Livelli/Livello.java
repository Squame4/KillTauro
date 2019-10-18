package Livelli;

import java.awt.Graphics2D;
import java.util.ArrayList;

import Mostro.Mostro;
import Platform.Blocco;
import Platform.Moneta;

public interface Livello {
	
	public void generaLivello();
	
	public boolean fineLivello();
	
	public ArrayList<Blocco> getBlocchi();
	
	public ArrayList<Mostro> getMostri();
	
	public int getxSpawn();

	public int getySpawn();

	public void disegna(Graphics2D grafica2d);
	
	public void caricaMostri();
	
	public void eliminaMostro(int i);

	public ArrayList<Moneta> getMonete();

	public void eliminaMoneta(int i);
	
	public String getPwdLivello();
	
	
}
