package avis;

public class Book {
	public String titre;
	public String genre;
	public String auteur;
	public int nbPages;
	public float noteMoyenne;
	
	public Book(String titre, String genre, String auteur, int nbPages) {
		this.titre = titre;
		this.genre = genre;
		this.auteur = auteur;
		this.nbPages = nbPages;
	}
	
	public String toString() {
		String noteString = "\n";
		if(this.noteMoyenne > 0.0f && this.noteMoyenne < 5.0f)
			noteString = " ; Note : "+this.noteMoyenne+"\n";
		return "Item type : Livre\nTitre : "+this.titre+" ; Genre : "+this.genre+" ; Auteur : "+this.auteur+" ; Nombre de pages : "+this.nbPages+noteString;
	}
}