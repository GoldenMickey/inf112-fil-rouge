package avis;

public class Book {
	public String titre;
	public String genre;
	public String auteur;
	public int nbPages;
	
	public Book(String titre, String genre, String auteur, int nbPages) {
		this.titre = titre;
		this.genre = genre;
		this.auteur = auteur;
		this.nbPages = nbPages;
	}
}