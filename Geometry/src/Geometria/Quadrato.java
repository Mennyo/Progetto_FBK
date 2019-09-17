package Geometria;

public class Quadrato extends Figura{
	private double lato;
	private Punto centro;
	

	public Quadrato(double lato, Punto centro) {
		super();
		this.lato = lato;
		this.setCentro(centro);
		
	}

	public double getLato() {
		return lato;
	}

	public void setLato(double lato) {
		this.lato = lato;
	}

	public Punto getCentro() {
		return centro;
	}

	public void setCentro(Punto centro) {
		this.centro = centro;
	}
	
	public double getPerimetro() {
		return 20.0;
	}
	public void sposta(int x, int y) {
		Punto nuovoCentro = new Punto(x, y);
		setCentro(nuovoCentro);
	}
}
