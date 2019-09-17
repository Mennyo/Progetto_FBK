package Geometria;

import java.util.List;
import java.util.ArrayList;

public class Main {
	public static void main (String[] args) {
		List<Figura> figure = new ArrayList<Figura>();
		Cerchio cerchio = new Cerchio (5.0, new Punto(10, 10));
		Quadrato quadrato = new Quadrato(5.0, new Punto (15, 15));
		figure.add(cerchio);
		figure.add(quadrato);
		for (Figura figura : figure) {
			System.out.println(figura.getPerimetro());
		}
	}
}
