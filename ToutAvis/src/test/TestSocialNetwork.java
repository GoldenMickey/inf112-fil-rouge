package test;
import java.nio.charset.Charset;
import java.util.Random;
import java.util.UUID;

import avis.SocialNetwork;

/** 
 * @author F. Portecop, L. Halley
 * @date Mai 2016
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

		// Boucle rendement - Ajout d'utilisateurs
		for(int i=0; i<550; i++) {
			nbTests++;
			nbErreurs += TestsAddMember.addMemberOKTest(sn, UUID.randomUUID().toString(), UUID.randomUUID().toString(), "CooliBoy", null);
		}
		
		// Boucle rendement - Ajout d'items
		for(int i=0; i<5010; i++) {
			nbTests++;
			if(i % 2 == 0)
				nbErreurs += TestsAddItemFilm.addItemFilmOKTest(sn, "Richard", "richard", UUID.randomUUID().toString(), "Someone", "Some other one", "Someone", 40, null);
			else
				nbErreurs += TestsAddItemBook.addItemBookOKTest(sn, "Paul", "paul", UUID.randomUUID().toString(), "Someone", "Loul", 30, null);
		}
		
		//Test Karma
		nbTests++;
		nbErreurs += TestsAddMember.addMemberOKTest(sn, "Jean", "jean", "CooliBoy", null);
		nbTests++;
		nbErreurs += TestsAddMember.addMemberOKTest(sn, "Bob", "boba", "CooliBoy", null);
		nbTests++;
		nbErreurs += TestsAddMember.addMemberOKTest(sn, "Patrick", "patrick", "CooliBoy", null);
		
		nbTests++;
		nbErreurs += TestsAddItemBook.addItemBookOKTest(sn, "Jean", "jean", "Epingle", "fiction", "Yayo", 3, null);
		nbTests++;
		nbErreurs += TestsAddItemFilm.addItemFilmOKTest(sn, "Jean", "jean", "Ciseaux", "fiction", "Yayo", "Youhou", 3, null);
		
		//JEAN USER REVIEWS 2 ITEMS
		nbTests++;
		nbErreurs += TestsReviewItemBook.reviewItemBookOKTest (sn, "Jean", "jean", "Epingle", 1.2f, "Un commentaire",null);
		nbTests++;
		nbErreurs += TestsReviewItemFilm.reviewItemFilmOKTest (sn, "Jean", "jean", "Ciseaux", 1.5f, "Un commentaire",null);

		//BOB REVIEWS 2 ITEMS
		nbTests++;
		nbErreurs += TestsReviewItemBook.reviewItemBookOKTest (sn, "Bob", "boba", "Epingle", 2, "Un commentaire",null);
		nbTests++;
		nbErreurs += TestsReviewItemFilm.reviewItemFilmOKTest (sn, "Jean", "jean", "Ciseaux", 1.5f, "Un commentaire",null);

		//BOB REVIEW JEAN'S OPINION
		nbTests++;
		nbErreurs += TestsReviewItemBook.reviewItemBookOKTest (sn, "Patrick", "patrick", "Epingle", 4.5f, "Un commentaire",null);
		nbTests++;
		nbErreurs += TestsReviewItemBook.reviewItemBookOKTest (sn, "Patrick", "patrick", "Punaise", 4f, "Un commentaire",null);
		nbTests++;
		nbErreurs += TestsReviewItemFilm.reviewItemFilmOKTest (sn, "Patrick", "patrick", "Ciseaux", 3.6f, "Un commentaire",null);
		
		nbTests++;
		nbErreurs += TestsConsultItem.consultItemOKTest(sn, "Epin", "Consult Epingle");
		nbTests++;
		nbErreurs += TestsConsultItem.consultItemOKTest(sn, "PunAiSe", "Consult Punaise");
		nbTests++;
		nbErreurs += TestsConsultItem.consultItemOKTest(sn, "ciseau", "Consult Ciseaux");
		
		// Ce n'est pas du test, mais cela peut "rassurer"...
		System.out.println(sn);

		// Bilan du test de addMember
		System.out.println("TestsSocialNetwork :   " + nbErreurs + " erreur(s) / " +  nbTests + " tests effectués");

	}
}