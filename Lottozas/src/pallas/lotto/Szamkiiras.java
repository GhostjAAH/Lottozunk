package pallas.lotto;

import java.util.*;

public class Szamkiiras {
	
	private Gyakoriszamok gyakszamok;
	private List<Integer> vegsoSzamok = new ArrayList<>();
	
	public List<Integer> getVegsoSzamok() {
		return vegsoSzamok;
	}
	public void setVegsoSzamok(List<Integer> vegsoSzamok) {
		this.vegsoSzamok = vegsoSzamok;
	}
	public Szamkiiras (Gyakoriszamok gyakszamok) {
		this.gyakszamok = gyakszamok;
	}
	public Szamkiiras() {
	}
	public void szamKiiras() {
		
		vegsoSzamok = gyakszamok.getSzamok();
		
		for (int szamok :  vegsoSzamok) {
			System.out.print(szamok + "  ");
		}
		System.out.println();
	}
}
