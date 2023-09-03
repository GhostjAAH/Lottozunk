package pallas.lotto;

import java.util.*;
import java.sql.*;

public class Lottohuzas {
	
	private int maxHuzas;
	private int szamHuzas;
	private int szamig;
	
	public int getMaxHuzas() {
		return maxHuzas;
	}
	public void setMaxHuzas(int maxHuzas) {
		this.maxHuzas = maxHuzas;
	}
	public int getSzamHuzas() {
		return szamHuzas;
	}
	public void setSzamHuzas(int szamHuzas) {
		this.szamHuzas = szamHuzas;
	}
	public int getSzamig() {
		return szamig;
	}
	public void setSzamig(int szamig) {
		this.szamig = szamig;
	}

	public void szamKihuzas() {
		Random random = new Random();
		List<Integer> huzas = new ArrayList<>();	
		
		for (int i = 0; i < maxHuzas; i++) {
			huzas.clear(); //Üresre állítom a listát, hogy ne maradjon benne az előző 5 szám
			for(int j = 0; j < szamHuzas; j++){
				int huzasSzam = random.nextInt(szamig)+1; 
				// Ellenőrzöm, hogy ne legyen duplikáció
				while (huzas.contains(huzasSzam)) {
					huzasSzam = random.nextInt(szamig)+1;
				}//Összegyüjtöm a húzott számokat egy listába
				huzas.add(huzasSzam);
			}
			try {
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lottozas","root", "");
				PreparedStatement preparedStatement;
						
				// Hozzáadom a húzott számokat az adatbázishoz
				preparedStatement = connection.prepareStatement("INSERT INTO lotto_szamok (szam1, szam2, szam3, szam4, szam5) VALUES (?, ?, ?, ?, ?)");					
				for (int n = 0; n < huzas.size(); n++) {
					int huzottSzam = huzas.get(n);
					preparedStatement.setInt(n + 1, huzottSzam);
				} 
				preparedStatement.executeUpdate();
				preparedStatement.close();
			    connection.close();
			}
			catch (SQLException e) {
				System.out.println("Lottohuzas not gud");
				e.printStackTrace();
			}
		}
	}
}
