package Geometria;

public class Cerchio extends Figura{
	private double raggio;
	private Punto centro;
	
	public Cerchio(double raggio, Punto centro) {
		super();
		this.raggio = raggio;
		this.centro = centro;
	}
	
	public double getRaggio() {
		return raggio;
	}

	public void setRaggio(double raggio) {
		this.raggio = raggio;
	}

	public Punto getCentro() {
		return centro;
	}

	public void setCentro(Punto centro) {
		this.centro = centro;
	}
	
	public double getPerimetro() {
		return 10.0;
	}
	public void sposta(int x, int y) {
		Punto nuovoCentro = new Punto(x, y);
		setCentro(nuovoCentro);
	}
	
}
