package test;

import avis.Review;
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

public class TestsReviewOpinion {
	
	//BAD ENTRY TEST
	public static int reviewOpinionBadEntryTest(SocialNetwork sn, String pseudo, String password, String date, String titre, float note, String commentaire, String idTest, String messErreur){
		// vérifie que l'ajout d'un membre (pseudo, pwd, profil) est refusée (levée de l'exception BadEntry et  pas de modification du sn)
		// si c'est bien le cas, ne fait rien
		// sinon, affiche le message d'erreur passé en paramètre
		try {
			sn.reviewOpinion(pseudo, password, date, titre, note, commentaire);
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
	
	//NOT MEMBER TEST
	public static int reviewOpinionNotMemberTest(SocialNetwork sn, String pseudo, String password, String date, String titre, float note, String commentaire, String idTest, String messErreur){
		// vérifie que l'ajout d'un membre (pseudo, pwd, profil) est refusée (levée de l'exception BadEntry et  pas de modification du sn)
		// si c'est bien le cas, ne fait rien
		// sinon, affiche le message d'erreur passé en paramètre
		try {
			sn.reviewOpinion(pseudo, password, date, titre, note, commentaire);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (NotMember e) {
				System.out.println("Test " + idTest + " : l'exception NotMember a bien été levée");
				return 0;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}
	
	//NOT ITEM TEST
	public static int reviewOpinionNotItemTest(SocialNetwork sn, String pseudo, String password, String date, String titre, float note, String commentaire, String idTest, String messErreur){
		// vérifie que l'ajout d'un membre (pseudo, pwd, profil) est refusée (levée de l'exception BadEntry et  pas de modification du sn)
		// si c'est bien le cas, ne fait rien
		// sinon, affiche le message d'erreur passé en paramètre
		try {
			sn.reviewOpinion(pseudo, password, date, titre, note, commentaire);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (NotItem e) {
				System.out.println("Test " + idTest + " : l'exception NotItem a bien été levée");
				return 0;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}

	//TEST OK
	public static int reviewOpinionOKTest(SocialNetwork sn, String pseudo, String password, String date, String titre, float note, String commentaire, String idTest, String messErreur){
		// vérifie que l'ajout d'un membre (pseudo, pwd, profil) est refusée (levée de l'exception BadEntry et  pas de modification du sn)
		// si c'est bien le cas, ne fait rien
		// sinon, affiche le message d'erreur passé en paramètre
		try {
			sn.reviewOpinion(pseudo, password, date, titre, note, commentaire);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 0;
		} catch (Exception e) {
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

		// tests de reviewOpinion
		//Creation de 3 USERS
		try {
			sn.addMember("Paul", "paul", "filmeur impulsif");
		} catch (BadEntry | MemberAlreadyExists e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			sn.addMember("Richard", "richard", "filmeur impulsif");
		} catch (BadEntry | MemberAlreadyExists e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			sn.addMember("Patou", "patou", "filmeur impulsif");
		} catch (BadEntry | MemberAlreadyExists e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//AJOUT D'UN LIVRE
		try {
			sn.addItemBook("Paul", "paul", "la peste ira bien", "fiction", "Portecop Florence", 6);
		} catch (BadEntry | ItemBookAlreadyExists | NotMember e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//REVIEW DU LIVRE
		try {
			sn.reviewItemBook("Paul", "paul", "la peste ira bien", 4.3f, "Très bon livre");
		} catch (BadEntry | NotMember | NotItem e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			System.out.println("Livre apres ReviewBook : "+sn.consultItems("La peste"));
		} catch (BadEntry e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Review pReview = sn.getBookUserReview("la peste ira bien", "Paul");
		System.out.println(pReview.date);
		
		// BadEntry tests
		nbTests++;
		nbErreurs += reviewOpinionBadEntryTest(sn, null, "richard", pReview.date, pReview.titre, 4.3f, "Ce commentaire est utile", "1", "La review d'une opinion avec un pseudo non instancié est accepté");
		nbTests++;
		nbErreurs += reviewOpinionBadEntryTest(sn, "Richard", null, pReview.date, pReview.titre, 4.3f, "Ce commentaire est utile", "2", "La review d'une opinion avec un pseudo non instancié est accepté");
		nbTests++;
		nbErreurs += reviewOpinionBadEntryTest(sn, "Richard", "richard", null, pReview.titre, 4.3f, "Ce commentaire est utile", "3", "La review d'une opinion avec un password non instancié est accepté");
		nbTests++;
		nbErreurs += reviewOpinionBadEntryTest(sn, "Richard", "richard", pReview.date, null, 4.3f, "Ce commentaire est utile", "4", "La review d'une opinion avec une date non instancié est accepté");
		nbTests++;
		nbErreurs += reviewOpinionBadEntryTest(sn, "Richard", "richard", pReview.date, pReview.titre, -4.2f, "Ce commentaire est utile", "5", "La review d'une opinion avec une note non valide est accepté");
		
		//NotMember test
		nbTests++;
		nbErreurs += reviewOpinionNotMemberTest(sn, "Richa", "ricard", pReview.date, pReview.titre, 4, "Ce commentaire est utile", "6", "La review d'une opinion avec un utilisateur non répertorié est accepté");
		
		//NotItem test
		nbTests++;
		nbErreurs += reviewOpinionNotItemTest(sn, "Richard", "richard", pReview.date, "Un item", 4, "Ce commentaire est utile", "7", "La review d'une opinion avec un titre d'item non répertorié est accepté");
		
		//OK test
		nbTests++;
		nbErreurs += reviewOpinionOKTest(sn, "Richard", "richard", pReview.date, "la peste ira bien", 4.2f, "Ce commentaire est utile", "8", "La review d'une opinion avec avec des entrées correctes fonctionne");
		nbTests++;
		nbErreurs += reviewOpinionOKTest(sn, "Patou", "patou", pReview.date, "la peste ira bien", 2.3f, "Ce commentaire est utile", "9", "La review d'une opinion avec avec des entrées correctes fonctionne");

		try {
			System.out.println("Livre apres ReviewOpinion : "+sn.consultItems("La peste"));
		} catch (BadEntry e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//nbErreurs += reviewOpinionBadEntryTest(sn, " ", "BadEntry Recherche 2", "La recherche avec un string de moins de 1 caractère est acceptée");

		//SocialNetwork sn, String pseudo, String password, String date, String titre, float note, String commentaire, String idTest, String messErreur	
		
		// <=> fiche numéro 2
		// ajout de 3 membres avec entrées "correctes"

		//nbTests++;
		//nbErreurs += consultItemOKTest(sn, "La PeSte", "Test OK");

		// ce n'est pas du test, mais cela peut "rassurer"...
		System.out.println(sn);

		// bilan du test de addMember
		
		System.out.println("TestsConsultItems :   " + nbErreurs + " erreur(s) / " +  nbTests + " tests effectués");

	}
}