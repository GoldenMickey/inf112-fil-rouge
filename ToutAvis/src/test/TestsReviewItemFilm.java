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

public class TestsReviewItemFilm {
	
	//BAD ENTRY TEST
	public static int reviewItemFilmBadEntryTest(SocialNetwork sn, String pseudo, String pwd, String titre, float note, String commentaire, String idTest, String messErreur){
		// vérifie que l'ajout d'un membre (pseudo, pwd, profil) est refusée (levée de l'exception BadEntry et  pas de modification du sn)
		// si c'est bien le cas, ne fait rien
		// sinon, affiche le message d'erreur passé en paramètre
		try {
			sn.reviewItemFilm(pseudo, pwd, titre, note, commentaire);
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
	public static int reviewItemFilmNotMemberTest (SocialNetwork sn, String pseudo, String pwd, String titre, float note, String commentaire, String idTest, String messErreur){
		try {
			sn.reviewItemFilm(pseudo, pwd, titre, note, commentaire);
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
	public static int reviewItemFilmNotItemTest (SocialNetwork sn, String pseudo, String pwd, String titre, float note, String commentaire, String idTest, String messErreur){
		int nbMembres = sn.nbMembers();
		try {
			sn.reviewItemFilm(pseudo, pwd, titre, note, commentaire);
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
	public static int reviewItemFilmOKTest (SocialNetwork sn, String pseudo, String pwd, String titre, float note, String commentaire, String idTest){
		try{
			sn.reviewItemFilm(pseudo, pwd, titre, note, commentaire);
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

		// tests de addMember
		int nbReviews = sn.nbReviews("films");
 
		// <=> fiche numéro 1
		try {
			sn.addMember("Paul", "paul", "filmeur impulsif");
		} catch (BadEntry | MemberAlreadyExists e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			sn.addMember("Antoine", "antoine", "lool");
		} catch (BadEntry | MemberAlreadyExists e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			sn.addItemFilm("Paul", "paul", "La vie de Florence", "drame", "Portecop Florence", "Portecop Florence", 9);
		} catch (BadEntry | NotMember | ItemFilmAlreadyExists e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// BadEntry tests
		nbTests++;
		nbErreurs += reviewItemFilmBadEntryTest ( sn, null, "paul", "La vie de Florence", 4.8f, "Emouvant, plein de passion et de dramaturgie", "BadEntry Pseudo 1", "La review d'un film par un utilisateur dont le pseudo n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += reviewItemFilmBadEntryTest ( sn, " ", "paul", "La vie de Florence", 4.8f, "Emouvant, plein de passion et de dramaturgie", "BadEntry Pseudo 2", "La review d'un film par un utilisateur dont le pseudo ne contient pas un caractere, autre que des espaces, est accepté");
		nbTests++;
		nbErreurs += reviewItemFilmBadEntryTest ( sn, "Paul", null, "La vie de Florence", 4.8f, "Emouvant, plein de passion et de dramaturgie", "BadEntry Password 3", "La review d'un film par un utilisateur dont le password n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += reviewItemFilmBadEntryTest ( sn, "Paul", "   qwd ", "La vie de Florence", 4.8f, "Emouvant, plein de passion et de dramaturgie", "BadEntry Password 4", "La review d'un film par un utilisateur dont le password ne contient pas au moins 4 caracteres, autre que des espaces de début ou de fin, est accepté");
		nbTests++;
		nbErreurs += reviewItemFilmBadEntryTest ( sn, "Paul", "paul", null, 4.8f, "Emouvant, plein de passion et de dramaturgie", "BadEntry Title 5", "La review d'un film dont le titre n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += reviewItemFilmBadEntryTest ( sn, "Paul", "paul", "  ", 4.8f, "Emouvant, plein de passion et de dramaturgie", "BadEntry Title 6", "La review d'un film dont le titre contient moins d'un caractère autre que des espaces est acceptée");
		nbTests++;
		nbErreurs += reviewItemFilmBadEntryTest ( sn, "Paul", "paul", "La vie de Florence", 5.1f, "Emouvant, plein de passion et de dramaturgie", "BadEntry Mark 7", "La review d'un film dont la note n'est pas comprise entre 0.0 et 5.0 est acceptée");
		nbTests++;
		nbErreurs += reviewItemFilmBadEntryTest ( sn, "Paul", "paul", "La vie de Florence", 4.8f, null, "BadEntry Comment 8", "La review d'un film dont le commentaire n'est pas instancié est acceptée");

		// NotMember tests
		nbTests++;
		nbErreurs += reviewItemFilmNotMemberTest ( sn, "Paulo", "paul", "La vie de Florence", 4.8f, "Emouvant, plein de passion et de dramaturgie", "NotMember 9", "La review d'un film par un utilisateur non enregistré est acceptée");
		nbTests++;
		nbErreurs += reviewItemFilmNotMemberTest ( sn, "Paul", "paulo", "La vie de Florence", 4.8f, "Emouvant, plein de passion et de dramaturgie", "NotMember 10", "La review d'un film par un utilisateur dont le nom et le mot de passe ne correspondent pas est acceptée");
		
		// NotItem tests
		nbTests++;
		nbErreurs += reviewItemFilmNotItemTest ( sn, "Paul", "paul", "La vie de Florence 2", 4.8f, "Emouvant, plein de passion et de dramaturgie", "NotMember 10", "La review d'un film avec un titre n'existant pas est acceptée");
		
		// <=> fiche numéro 2

		// ajout de 3 membres avec entrées "correctes"

		nbTests++;
		nbErreurs += reviewItemFilmOKTest (sn, "Paul", "paul", "La vie de Florence", 4.8f, "Emouvant, plein de passion et de dramaturgie", "Test OK 11");
		nbTests++;
		nbErreurs += reviewItemFilmOKTest (sn, "Antoine", "antoine", "La vie de Florence", 3.9f, "grand amoureux de la littérature","2.1b");
		
		nbTests++;
		if (nbReviews != sn.nbReviews("film")) {
			System.out.println("Erreur  :  le nombre de films après utilisation de reviewItemFilms a été modifié");	
			nbErreurs++;
		}

		// ce n'est pas du test, mais cela peut "rassurer"...
		System.out.println(sn);

		// bilan du test de addMember
		System.out.println("TestsReviewItemFilm :   " + nbErreurs + " erreur(s) / " +  nbTests + " tests effectués");

	}
}