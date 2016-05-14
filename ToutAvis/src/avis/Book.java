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
		String noteString = "";
		if(this.noteMoyenne > 0.0f && this.noteMoyenne < 5.0f)
			noteString = "\nNote : "+this.noteMoyenne;
		return "Item type : Livre\nTitre : "+this.titre+"\nGenre : "+this.genre+"\nAuteur : "+this.auteur+"\nNombre de pages : "+this.nbPages+noteString;
	}
}