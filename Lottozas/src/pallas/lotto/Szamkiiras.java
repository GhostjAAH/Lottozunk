package pallas.lotto;

import java.util.*;

public class Szamkiiras {
	private Gyakoriszamok gyakszamok;
	
	public Szamkiiras (Gyakoriszamok gyakszamok) {
		this.gyakszamok = gyakszamok;
	}
	public Szamkiiras() {
	}
	public void szamKiiras() {
		List<Integer> vegsoSzamok = gyakszamok.getLegGyakszamok();
		//Átteszem a 3 leggyakoribb számot tömb-be
		Integer[] tomb = vegsoSzamok.toArray(new Integer[vegsoSzamok.size()]);
		//Szépre formázom :) és kiíratom
		String tombString = Arrays.toString(tomb);
		System.out.print(tombString);
		System.out.println();
	}
}
