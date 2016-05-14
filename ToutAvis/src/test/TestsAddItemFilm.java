package test;

import java.util.LinkedList;

import avis.SocialNetwork;

import exception.BadEntry;
import exception.ItemFilmAlreadyExists;
import exception.MemberAlreadyExists;
import exception.NotItem;
import exception.NotMember;

/** 
 * @author B. Prou, E. Cousin
 * @date mars 2015
 * @version V1.0
 */

public class TestsAddItemFilm {
	public static int addItemFilmBadEntryTest (SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String realisateur, String scenariste, int duree, String idTest, String messErreur){
		// vérifie que l'ajout d'un membre (pseudo, pwd, profil) est refusée (levée de l'exception BadEntry et  pas de modification du sn)
		// si c'est bien le cas, ne fait rien
		// sinon, affiche le message d'erreur passé en paramètre
		int nbFilms = sn.nbFilms();
		try {
			sn.addItemFilm(pseudo, pwd, titre, genre,realisateur, scenariste, duree);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (BadEntry e) {
			if (sn.nbFilms() != nbFilms) {
				System.out.println("Test " + idTest + " : l'exception BadEntry a bien été levée mais le nombre de films a été modifié");
				return 1;
			}
			else
				return 0;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}

	public static int addItemFilmOKTest (SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String realisateur, String scenariste, int duree, String idTest){
		int nbFilms = sn.nbFilms();
		try{
			sn.addItemFilm(pseudo, pwd, titre, genre,realisateur,scenariste, duree);
			if (sn.nbFilms() != nbFilms+1) {
				System.out.println("Test " + idTest + " :  le nombre de films n'a pas été correctement incrémenté");
				return 1;
			}
			else 
				System.out.println("Test " + idTest + " :  Nombre de films correctement incrémenté");
				return 0;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}

	public static int addItemFilmAlreadyExistsTest (SocialNetwork sn, String pseudo, String pwd, String titre,String genre,  String realisateur, String scenariste, int duree, String idTest, String messErreur){
		int nbFilms = sn.nbFilms();
		try {
			sn.addItemFilm(pseudo, pwd, titre, genre ,realisateur, scenariste, duree);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (ItemFilmAlreadyExists e) {
			if (sn.nbFilms() != nbFilms) {
				System.out.println("Test " + idTest + " : l'exception ItemFilmAlreadyExists a bien été levée mais le nombre de membres a été modifié");
				return 1;
			}
			else
				return 0;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}

	public static int addItemFilmNotMemberTest (SocialNetwork sn, String pseudo, String pwd, String titre,String genre, String realisateur, String scenariste, int duree, String idTest, String messErreur){
		int nbFilms = sn.nbFilms();
		try {
			sn.addItemFilm(pseudo, pwd, titre, genre, realisateur, scenariste, duree);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (NotMember e) {
			if (sn.nbFilms() != nbFilms) {
				System.out.println("Test " + idTest + " : l'exception NotMember a bien été levée mais le nombre de films a été modifié");
				return 1;
			}
			else
				return 0;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}
	
	public static void main(String[] args) {

		int nbFilms = 0;

		int nbTests = 0;
		int nbErreurs = 0;
		
		System.out.println("Tests  ajouter des films au réseau social  ");

		SocialNetwork sn = new SocialNetwork();
		try {
			sn.addMember("Flo", "12345", "Loool");
		} catch (BadEntry e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MemberAlreadyExists e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// tests de addItemFilm
		nbFilms = sn.nbFilms();

		// <=> fiche numéro 1
		// tentative d'ajout de films avec entrées "incorrectes"
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, null, "12345", "Game of Thrones 1", "Fiction/Porno", "Johnny Depp", "Georges R.R Martin", 5, "1.1", "L'ajout d'un film sous un pseudo pas instancié est accepté");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, " ", "12345", "Game of Thrones 1", "Fiction/Porno", "Johnny Depp", "Georges R.R Martin", 5, "1.2", "L'ajout d'un film avec un membre dont le pseudo ne contient pas un caracteres, autre que des espaces, est accepté");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "Flo", null, "Game of Thrones 1", "Fiction/Porno", "Johnny Depp", "Georges R.R Martin", 5, "1.3", "L'ajout d'un film avec un membre dont le password n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "Flo", "   qwd ", "Game of Thrones 1", "Fiction/Porno", "Johnny Depp", "Georges R.R Martin", 5, "1.4", "L'ajout d'un film avec un membre dont le password ne contient pas au moins 4 caracteres, autre que des espaces de début ou de fin, est accepté");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "Flo", "12345", null, "Fiction/Porno", "Johnny Depp", "Georges R.R Martin", 5, "1.5", "L'ajout d'un film dont le titre n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "Flo", "12345", " ", "Fiction/Porno", "Johnny Depp", "Georges R.R Martin", 5, "1.6", "L'ajout d'un film dont le titre a moins d'un caractère autre que des espaces");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "Flo", "12345", "Game of Thrones 1", null, "Johnny Depp", "Georges R.R Martin", 5, "1.6", "L'ajout d'un film dont le genre n'est pas instancié");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "Flo", "12345", "Game of Thrones 1", "Fiction/Porno", null, "Georges R.R Martin", 5, "1.6", "L'ajout d'un film dont le realisateur n'est pas instancié");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "Flo", "12345", "Game of Thrones 1", "Fiction/Porno", "Johnny Depp", null, 5, "1.6", "L'ajout d'un film dont le scenariste n'est pas instancié");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "Flo", "12345", "Game of Thrones 1", "Fiction/Porno", "Johnny Depp", "Georges R.R Martin", -5, "1.6", "L'ajout d'un film dont la durée est negative est accepté");

		// <=> fiche numéro 2
		// ajout de 2 films avec entrées "correctes"
		nbTests++;
		nbErreurs += addItemFilmOKTest (sn, "Flo", "12345", "Game of Thrones 1", "Fiction/Porno", "Johnny Depp", "Georges R.R Martin", 5, "2.1a");
		nbTests++;
		nbErreurs += addItemFilmOKTest (sn, "Flo", "12345", "Game of Thrones 2", "Fiction/Porno", "Johnny Depp", "Georges R.R Martin", 6, "2.2a");

		nbFilms = sn.nbFilms();
		
		// <=> fiche numéro 3
		// tentative d'ajout de film "existant"
		nbTests++;
		nbErreurs += addItemFilmAlreadyExistsTest(sn, "Flo", "12345", "Game of Thrones 1", "Fiction/Porno", "Johnny Depp", "Georges R.R Martin", 6, "2.3", "L'ajout d'un film avec un titre deja present est accepté");

		// <=> fiche numéro 4
		// tentative d'ajout de film avec un membre non reconnu
		nbTests++;
		nbErreurs += addItemFilmNotMemberTest(sn, "Floc", "12345", "Game of Thrones 3", "Fiction/Porno", "Johnny Depp", "Georges R.R Martin", 6, "2.4", "L'ajout d'un film avec un membre non existant est accepté");
		nbTests++;
		nbErreurs += addItemFilmNotMemberTest(sn, "Flo", "123456", "Game of Thrones 3", "Fiction/Porno", "Johnny Depp", "Georges R.R Martin", 6, "2.5", "L'ajout d'un film avec un membre dont le pseudo et le password ne correspondent pas est accepté");

		nbTests++;
		if (nbFilms != sn.nbFilms()) {
			System.out.println("Erreur  : le nombre de film après utilisation de addItemFilm a été modifié");	
			nbErreurs++;
		}

		// ce n'est pas du test, mais cela peut "rassurer"...
		System.out.println(sn);

		// bilan du test de addMember
		System.out.println("TestsAddItemFilm :   " + nbErreurs + " erreur(s) / " +  nbTests + " tests effectués");

	}
}
