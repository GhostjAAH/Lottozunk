package pallas.lotto;

import java.util.*;
import java.util.Random;

public class Lottohuzas {
	
	private int MaxElem;
	private List<Integer> szamok = new ArrayList<>();
		
	public List<Integer> getSzamok(){
		return szamok;
	}
	public void setSzamok(List<Integer> szamok) {
		this.szamok = szamok;
	}
	
	public int getMaxElem() {
		return MaxElem;
	}
	public void setMaxElem(int maxElem) {
		MaxElem = maxElem;
	}

	public void szamKihuzas() {
		Random random = new Random();
		List<Integer> huzas = new ArrayList<>();	
		
	for (int i = 0; i < 3; i++) {
		huzas.clear(); //Üresre állítom a listát, hogy ne maradjon benne az előző 5 szám
		for(int j = 0; j < 5; j++){
	        int huzasSzam = random.nextInt(90); 
	        // Ellenőrzöm, hogy ne legyen duplikáció és "0" a húzott számok között
	        while (huzas.contains(huzasSzam) && huzasSzam == 0) {
	        	huzasSzam = random.nextInt(90);
	        }//Összegyüjtöm a húzott számokat egy listába
	        huzas.add(huzasSzam);
		}
		// Hozzáadom a húzott számokat a végső listához
		for (Integer szam : huzas) {
			szamok.add(szam);
		}
	}
	}
}
