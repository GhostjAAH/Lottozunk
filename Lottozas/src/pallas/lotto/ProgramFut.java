package pallas.lotto;

import java.sql.*;
import java.util.*;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class ProgramFut {
	public static void main(String[] args) {
		
		Resource resource = new ClassPathResource("applicationContext.xml");
		BeanFactory factory = new XmlBeanFactory(resource);

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lottozas","root", "");
			
		Scanner sc = new Scanner(System.in);
		int valasztas;
		boolean szamHuzas = false;
		boolean szamGyujtes = false;
		boolean szamTorles = false;
		boolean szamKiiras = false;
		
		do {
			System.out.println("Valassz az alabbi menupontok kozul!");
			System.out.println("1. Szamok kihuzasa");
			System.out.println("2. A 3 leggyakoribb szam osszegyujtese");
			System.out.println("3. A 3 leggyakoribb szam kiiratasa");
			System.out.println("4. Adatok torlese az adatbazisbol");
			System.out.println("5. Kilepes");
			System.out.print("A valasztas: ");
			valasztas = sc.nextInt();
			
			switch (valasztas) {
            case 1:
            	PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) AS adatok FROM lotto_szamok");
            	ResultSet vanAdat = preparedStatement.executeQuery();
            	if(vanAdat.next() && vanAdat.getInt("adatok") == 0) {
            		System.out.println("Ures az adatbazis! Szamok kihuzasa..\n");
            		Lottohuzas huzas = (Lottohuzas)factory.getBean("huzasBean");
            		huzas.szamKihuzas();
            		szamHuzas = true;
            		szamTorles = false;
            		preparedStatement.close();
            	} else {
            		System.out.println("Az adatbazis mar tartalmaz adatokat!\n");	
            		szamHuzas = true;
            		szamTorles = false;
            	}
                break;
            case 2:
            	if (szamHuzas && !szamTorles && !szamGyujtes) {
            		System.out.println("Leggyakoribb szamok osszegyujtese...\n");
            		Gyakoriszamok szamok = (Gyakoriszamok)factory.getBean("szamokBean");
            		szamok.szamGyujtes();
            		szamGyujtes = true;
            	} else {
            		System.out.println("Nem huztad meg ki az osszegyujtendo szamokat, vagy mar ossze is gyujtotted oket!\n");
            	}
                break;
            case 3:
            	if(szamHuzas && szamGyujtes && !szamTorles && !szamKiiras) {
            		System.out.println("A Leggyakoribb szamok az alabbiak:\n");
            		Szamkiiras kiir = (Szamkiiras)factory.getBean("kiirBean");
            		kiir.szamKiiras();
            		System.out.println();
            		szamKiiras = true;
            	} else {
            		System.out.println("A szamok nincsenek osszegyujtve, vagy ki sincsenek huzva, vagy mar kiirtad oket!\n");
            	}
                break;
            case 4:
            	System.out.println("Adatok torlese az adatbazisbol...\n");
            	preparedStatement = connection.prepareStatement("DELETE from lotto_szamok");
				preparedStatement.executeUpdate();
				preparedStatement.close();
				szamTorles = true;
				szamGyujtes = false;
				szamKiiras = false;
				break;
            case 5:
                System.out.println("Kilepes...");
                break;
            default:
                System.out.println("Adj meg egy jo erteket 1-5-ig!\n");
                break;
			}
		} while (valasztas !=5);
		sc.close();
		connection.close();
		}
		catch (SQLException e) {
			System.out.println("Menu not gud");
			e.printStackTrace();
		}
	}	
}
