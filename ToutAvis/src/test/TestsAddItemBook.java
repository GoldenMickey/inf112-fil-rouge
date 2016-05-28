package test;

import avis.SocialNetwork;

import exception.BadEntry;
import exception.ItemBookAlreadyExists;
import exception.MemberAlreadyExists;
import exception.NotMember;

/** 
 * @author F. Portecop, L. Halley
 * @date Mai 2016
 * @version V1.0
 */

public class TestsAddItemBook {
	public static int addItemBookBadEntryTest (SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String auteur, int nbPages, String idTest, String messErreur){
		// vérifie que l'ajout d'un membre (pseudo, pwd, profil) est refusée (levée de l'exception BadEntry et  pas de modification du sn)
		// si c'est bien le cas, ne fait rien
		// sinon, affiche le message d'erreur passé en paramètre
		int nbBooks = sn.nbBooks();
		try {
			sn.addItemBook(pseudo, pwd, titre, genre, auteur, nbPages);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (BadEntry e) {
			if (sn.nbBooks() != nbBooks) {
				System.out.println("Test " + idTest + " : l'exception BadEntry a bien été levée mais le nombre de books a été modifié");
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

	public static int addItemBookOKTest (SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String auteur, int nbPages, String idTest){
		int nbBooks = sn.nbBooks();
		try{
			sn.addItemBook(pseudo, pwd, titre, genre, auteur, nbPages);
			if (sn.nbBooks() != nbBooks+1) {
				System.out.println("Test " + idTest + " :  le nombre de books n'a pas été correctement incrémenté");
				return 1;
			}
			else 
				System.out.println("Test " + idTest + " :  Nombre de books correctement incrémenté");
				return 0;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}

	public static int addItemBookAlreadyExistsTest (SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String auteur, int nbPages, String idTest, String messErreur){
		int nbBooks = sn.nbBooks();
		try {
			sn.addItemBook(pseudo, pwd, titre, genre, auteur, nbPages);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (ItemBookAlreadyExists e) {
			if (sn.nbBooks() != nbBooks) {
				System.out.println("Test " + idTest + " : l'exception ItemBookAlreadyExists a bien été levée mais le nombre de membres a été modifié");
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

	public static int addItemBookNotMemberTest (SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String auteur, int nbPages, String idTest, String messErreur){
		int nbBooks = sn.nbBooks();
		try {
			sn.addItemBook(pseudo, pwd, titre, genre, auteur, nbPages);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (NotMember e) {
			if (sn.nbBooks() != nbBooks) {
				System.out.println("Test " + idTest + " : l'exception NotMember a bien été levée mais le nombre de books a été modifié");
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

		int nbBooks = 0;

		int nbTests = 0;
		int nbErreurs = 0;
		
		System.out.println("Tests  ajouter des books au réseau social  ");

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

		// tests de addItemBook
		nbBooks = sn.nbBooks();

		// <=> fiche numéro 1
		// tentative d'ajout de books avec entrées "incorrectes"
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, null, "12345", "Game of Thrones 1", "Fiction/Porno", "Georges R.R Martin", 5, "1.1", "L'ajout d'un book sous un pseudo pas instancié est accepté");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, " ", "12345", "Game of Thrones 1", "Fiction/Porno", "Georges R.R Martin", 5, "1.2", "L'ajout d'un book avec un membre dont le pseudo ne contient pas un caracteres, autre que des espaces, est accepté");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, "Flo", null, "Game of Thrones 1", "Fiction/Porno", "Georges R.R Martin", 5, "1.3", "L'ajout d'un book avec un membre dont le password n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, "Flo", "   qwd ", "Game of Thrones 1", "Fiction/Porno", "Georges R.R Martin", 5, "1.4", "L'ajout d'un book avec un membre dont le password ne contient pas au moins 4 caracteres, autre que des espaces de début ou de fin, est accepté");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, "Flo", "12345", null, "Fiction/Porno", "Georges R.R Martin", 5, "1.5", "L'ajout d'un book dont le titre n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, "Flo", "12345", " ", "Fiction/Porno", "Georges R.R Martin", 5, "1.6", "L'ajout d'un book dont le titre a moins d'un caractère autre que des espaces");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, "Flo", "12345", "Game of Thrones 1", null, "Georges R.R Martin", 5, "1.6", "L'ajout d'un book dont le genre n'est pas instancié");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, "Flo", "12345", "Game of Thrones 1", "Fiction/Porno", null, 5, "1.6", "L'ajout d'un book dont l'auteur n'est pas instancié");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, "Flo", "12345", "Game of Thrones 1", "Fiction/Porno", "Georges R.R Martin", 0, "1.6", "L'ajout d'un book dont le nb de pages est inférieur à 1 est accepté");

		// <=> fiche numéro 2
		// ajout de 2 books avec entrées "correctes"
		nbTests++;
		nbErreurs += addItemBookOKTest (sn, "Flo", "12345", "Game of Thrones 1", "Fiction/Porno", "Georges R.R Martin", 5, "2.1a");
		nbTests++;
		nbErreurs += addItemBookOKTest (sn, "Flo", "12345", "Game of Thrones 2", "Fiction/Porno", "Georges R.R Martin", 6, "2.2a");

		nbBooks = sn.nbBooks();
		
		// <=> fiche numéro 3
		// tentative d'ajout de book "existant"
		nbTests++;
		nbErreurs += addItemBookAlreadyExistsTest(sn, "Flo", "12345", "Game of Thrones 1", "Fiction/Porno", "Georges R.R Martin", 6, "2.2", "L'ajout d'un book avec un titre deja present est accepté");

		// <=> fiche numéro 4
		// tentative d'ajout de book avec un membre non reconnu
		nbTests++;
		nbErreurs += addItemBookNotMemberTest(sn, "Floc", "12345", "Game of Thrones 3", "Fiction/Porno", "Georges R.R Martin", 6, "2.2", "L'ajout d'un book avec un membre non existant est accepté");
		nbTests++;
		nbErreurs += addItemBookNotMemberTest(sn, "Flo", "123456", "Game of Thrones 3", "Fiction/Porno", "Georges R.R Martin", 6, "2.2", "L'ajout d'un book avec un membre dont le pseudo et le password ne correspondent pas est accepté");

		nbTests++;
		if (nbBooks != sn.nbBooks()) {
			System.out.println("Erreur  :  le nombre de book après utilisation de addItemBook a été modifié");	
			nbErreurs++;
		}

		// ce n'est pas du test, mais cela peut "rassurer"...
		System.out.println(sn);

		// bilan du test de addMember
		System.out.println("TestsAddItemBook :   " + nbErreurs + " erreur(s) / " +  nbTests + " tests effectués");

	}
}
