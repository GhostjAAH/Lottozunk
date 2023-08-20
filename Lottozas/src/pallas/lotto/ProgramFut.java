package pallas.lotto;

import java.util.Scanner;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class ProgramFut {
	public static void main(String[] args) {
		Resource resource = new ClassPathResource("applicationContext.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		
		Lottohuzas huzas = (Lottohuzas)factory.getBean("huzasBean");
		Gyakoriszamok szamok = (Gyakoriszamok)factory.getBean("szamokBean");
		Szamkiiras kiir = (Szamkiiras)factory.getBean("kiirBean");
		
		Scanner sc = new Scanner(System.in);
		int valasztas;
		boolean case1Executed = false;
		boolean case2Executed = false;
		
		do {
			System.out.println("Valassz az alabbi menupontok kozul!");
			System.out.println("1. Lottohuzas");
			System.out.println("2. A 3 leggyakoribb szam osszegyujtese");
			System.out.println("3. A 3 leggyakoribb szam kiiratasa:");
			System.out.println("4. Kilepes");
			System.out.print("A valasztas: ");
			valasztas = sc.nextInt();
			
			switch (valasztas) {
            case 1:
                System.out.println("Szamok kihuzasa..");
                huzas.szamKihuzas();
                case1Executed = true;
                break;
            case 2:
            	if (case1Executed) {
                System.out.println("Leggyakoribb szamok osszegyujtese...");
                szamok.szamGyujtes();
                case2Executed = true;
            	} else {
            		System.out.println("Eloszor huzd ki a szamokat az 1. menuponttal");
            	}
                break;
            case 3:
            	if(case1Executed && case2Executed) {
                System.out.println("A Leggyakoribb szamok az alabbiak:");
                kiir.szamKiiras();
            	} else {
            		System.out.println("A szamok nincsenek osszegyujtve, vagy ki sincsenek huzva."
            						  + "Eloszor huzd ki a szamokat, majd gyujtsd ossze oket!");
            	}
                break;
            case 4:
                System.out.println("Kilepes...");
                break;
            default:
                System.out.println("Adj meg egy jo erteket 1-4-ig.");
                break;
			}
		} while (valasztas !=4);
		sc.close();
	}	
}
