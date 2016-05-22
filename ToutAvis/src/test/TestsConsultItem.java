package test;

import java.util.LinkedList;

import avis.SocialNetwork;

import exception.BadEntry;
import exception.ItemFilmAlreadyExists;
import exception.ItemBookAlreadyExists;
import exception.MemberAlreadyExists;
import exception.NotItem;
import exception.NotMember;

/** 
 * @author F. Portecop, L. Halley
 * @date mars 2015
 * @version V1.0
 */

public class TestsConsultItem {
	
	//BAD ENTRY TEST
	public static int consultItemsBadEntryTest(SocialNetwork sn, String recherche, String idTest, String messErreur){
		// vérifie que l'ajout d'un membre (pseudo, pwd, profil) est refusée (levée de l'exception BadEntry et  pas de modification du sn)
		// si c'est bien le cas, ne fait rien
		// sinon, affiche le message d'erreur passé en paramètre
		try {
			sn.consultItems(recherche);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (BadEntry e) {
				System.out.println("Test " + idTest + " : l'exception BadEntry a bien été levée");
				return 0;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}

	//TEST OK
	public static int consultItemOKTest (SocialNetwork sn, String recherche, String idTest){
		try{
			System.out.println(sn.consultItems(recherche));
			return 0;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}
	
	public static void main(String[] args) {
		int nbTests = 0;
		int nbErreurs = 0;

		System.out.println("Tests review de films");
		SocialNetwork sn = new SocialNetwork();

		// tests de consultItems
		try {
			sn.addMember("Paul", "paul", "filmeur impulsif");
		} catch (BadEntry | MemberAlreadyExists e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			sn.addItemFilm("Paul", "paul", "La Peste", "drame", "Portecop Florence", "Portecop Florence", 4);
		} catch (BadEntry | ItemFilmAlreadyExists | NotMember e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			sn.addItemBook("Paul", "paul", " la peste ira bien", "fiction", "Portecop Florence", 6);
		} catch (BadEntry | ItemBookAlreadyExists | NotMember e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// BadEntry tests
		nbTests++;
		nbErreurs += consultItemsBadEntryTest(sn, null, "BadEntry Recherche 1", "La recherche avec un string non instancié est accepté");
		nbTests++;
		nbErreurs += consultItemsBadEntryTest(sn, " ", "BadEntry Recherche 2", "La recherche avec un string de moins de 1 caractère est acceptée");

		// <=> fiche numéro 2
		// ajout de 3 membres avec entrées "correctes"

		nbTests++;
		nbErreurs += consultItemOKTest(sn, "La PeSte", "Test OK");

		// ce n'est pas du test, mais cela peut "rassurer"...
		System.out.println(sn);

		// bilan du test de addMember
		
		System.out.println("TestsConsultItems :   " + nbErreurs + " erreur(s) / " +  nbTests + " tests effectués");

	}
}