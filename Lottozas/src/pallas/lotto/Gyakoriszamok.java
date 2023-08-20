package pallas.lotto;

import java.util.*;

public class Gyakoriszamok {
	
	private Lottohuzas lottohuzas;
	private List<Integer> legGyakszamok = new ArrayList<>();
	
	public Gyakoriszamok (Lottohuzas lottohuzas) {
		this.lottohuzas = lottohuzas;
	}
	public Gyakoriszamok() {
	}
	
	public List<Integer> getLegGyakszamok() {
		return legGyakszamok;
	}
	public void setLegGyakszamok(List<Integer> legGyakszamok) {
		this.legGyakszamok = legGyakszamok;
	}
	
	public void szamGyujtes() {	
		List<Integer> szamokLista = lottohuzas.getSzamok();
		
		Map<Integer, Integer> elofordulasMap = new HashMap<>();
		
		for (int szam : szamokLista) {
			//Beletesszük a számokat a HashMap-be, úgy, hogy;
			//Ha már van ilyen szám, akkor a hozzá tartozó key előfordulását 1-gyel növeli,
			//Ha még nincs, akkor beállítja a számhoz tartozó key előfordulását 1-re és beleteszi a HashMap-be.
			elofordulasMap.put(szam, elofordulasMap.getOrDefault(szam, 0)+1);
		}
		while (legGyakszamok.size() < 3) {
			//Megkeressük a legnagyobb előfordulást a Collections.max metódussal és elmentjük
			int maxElofordulasok = Collections.max(elofordulasMap.values());
			
			int legGyakoriSzam = 0;
			//Végigmegyünk a HashMap kulcs-érték párokon
			for(Map.Entry<Integer, Integer> entry : elofordulasMap.entrySet()) {
				//Ha a számhoz(kulcs) tartozó érték(value) megegyezik a maxElofordulasok-ban lévő értékkel->
				if (entry.getValue() == maxElofordulasok) {
					//->Az értékhez(value) tartozó kulcsot(számot) elmenti és belerakja a listába
					legGyakoriSzam = entry.getKey();
					legGyakszamok.add(legGyakoriSzam);
					//A végén kitörli a HashMapból, hogy a következő iterációnál ne zavarjon
					elofordulasMap.remove(legGyakoriSzam);
					break;
				}
			}
		}
	}
}
