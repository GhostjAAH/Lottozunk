package pallas.lotto;

import java.sql.*;
import java.util.*;

public class Gyakoriszamok {
	private int hanyGyakori;
	private List<Integer> szamok = new ArrayList<>();
	public int getHanyGyakori() {
		return hanyGyakori;
	}
	public void setHanyGyakori(int hanyGyakori) {
		this.hanyGyakori = hanyGyakori;
	}
	public List<Integer> getSzamok() {
		return szamok;
	}
	public void setSzamok(List<Integer> szamok) {
		this.szamok = szamok;
	}
	
	public void szamGyujtes() {	
		szamok.clear();
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lottozas","root", "");
			PreparedStatement preparedStatement = connection.prepareStatement
					("SELECT szam, COUNT(*) AS szam_count " +
		            "FROM (" +
		            "   SELECT szam1 AS szam FROM lotto_szamok " +
		            "   UNION ALL " +
		            "   SELECT szam2 AS szam FROM lotto_szamok " +
		            "   UNION ALL " +
		            "   SELECT szam3 AS szam FROM lotto_szamok " +
		            "   UNION ALL " +
		            "   SELECT szam4 AS szam FROM lotto_szamok " +
		            "   UNION ALL " +
		            "   SELECT szam5 AS szam FROM lotto_szamok" +
		            ") AS all_numbers " +
		            "GROUP BY szam " +
		            "ORDER BY szam_count DESC");
			ResultSet eredmenyek = preparedStatement.executeQuery();
			int elozoSzamcount = -1;
			int szamSzamlalo = 0;
			while(eredmenyek.next()) {
				
				int szamCount = eredmenyek.getInt("szam_count");
				int szam = eredmenyek.getInt("szam");
				if (szamCount != elozoSzamcount && szamSzamlalo > hanyGyakori) {
			    	break;
			    }
			    if (szamCount != elozoSzamcount && szamSzamlalo <= hanyGyakori) {
			        elozoSzamcount = szamCount;
			        szamok.add(szam);
			        szamSzamlalo++;
			    } else {
			        szamok.add(szam);
			        szamSzamlalo++;
			    }    
			}
			preparedStatement.close();
			connection.close();
		}
		catch (SQLException e) {
			System.out.println("Gyakoriszamok not gud");
			e.printStackTrace();
		}
	}
}
