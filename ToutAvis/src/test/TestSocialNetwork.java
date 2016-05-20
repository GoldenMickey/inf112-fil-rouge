package test;
import java.nio.charset.Charset;
import java.util.Random;

import avis.SocialNetwork;


/** 
 * @author F. Portecop, L. Halley
 * @date mars 2015
 * @version V1.0
 */

public class TestSocialNetwork {
	public static String randomString() {
	    byte[] array = new byte[7]; // length is bounded by 7
	    new Random().nextBytes(array);
	    String generatedString = new String(array, Charset.forName("UTF-8"));
	 
	    return generatedString;
	}
	
	public static void main(String[] args) {
		int nbTests = 0;
		int nbErreurs = 0;

		System.out.println("Test du Social Network");
		SocialNetwork sn = new SocialNetwork();

		//**********************************************************************************
		// Les cas d'erreurs sont traités plus spécifiquement dans le main de chaque tests
		//**********************************************************************************
		
		nbTests++;
		nbErreurs += TestsAddMember.addMemberOKTest(sn, "Paul", "paul", "Badass", "0.1");
		
		nbTests++;
		nbErreurs += TestsAddItemFilm.addItemFilmOKTest(sn, "Paul", "paul", "La vie de Florence", "drame", "Florence Portecop", "Portecop Florence", 60, "0.2");

		nbTests++;
		nbErreurs += TestsAddMember.addMemberOKTest(sn, "Richard", "richard", "CooliBoy", "0.3");

		nbTests++;
		nbErreurs += TestsAddItemBook.addItemBookOKTest(sn, "Richard", "richard", "Man of the year", "fiction", "Florence Portecop", 40, "0.4");

		nbTests++;
		nbErreurs += TestsReviewItemFilm.reviewItemFilmOKTest(sn, "Richard", "richard", "La vie de Florence", 5.0f, "Magnifique", "0.5");

		nbTests++;
		nbErreurs += TestsConsultItem.consultItemOKTest(sn, "La vie de Florence", "0.6");

		// Boucle rendement - Ajout d'utilisateurs
		for(int i=0; i<510; i++) {
			nbTests++;
			nbErreurs += TestsAddMember.addMemberOKTest(sn, "Richard", "richard", "CooliBoy", null);
		}
		
		// Boucle rendement - Ajout d'items
		for(int i=0; i<550; i++) {
			nbTests++;
			
			if(i % 2 == 0)
				nbErreurs += TestsAddItemFilm.addItemFilmOKTest(sn, "Richard", "richard", "CooliBoy", randomString(), randomString(), randomString(), 40, null);
			else
				nbErreurs += TestsAddItemBook.addItemBookOKTest(sn, "Richard", "richard", "CooliBoy", randomString(), randomString(), 40, null);
		}
		
		// Ce n'est pas du test, mais cela peut "rassurer"...
		System.out.println(sn);

		// Bilan du test de addMember
		System.out.println("TestsSocialNetwork :   " + nbErreurs + " erreur(s) / " +  nbTests + " tests effectués");

	}
}