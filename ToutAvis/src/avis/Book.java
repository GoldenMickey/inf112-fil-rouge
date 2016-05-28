package avis;

/** 
 * @author F. Portecop, L. Halley
 * @date Mai 2016
 * @version V1.0
 */

/** 
 * <p>
 * <b>Class Book, used to represent a book of a SocialNetwork.</b>
 * </p>
 */
public class Book {
	public String titre;
	public String genre;
	public String auteur;
	public int nbPages;
	public float noteMoyenne;
	
	/**
	 * Book constructor
	 * 
	 * @param titre the title of the book.
	 * @param genre the genre of the book.
	 * @param auteur the book author.
	 * @param nbPages the number of pages of the book. (strictly positive).
	 */
	public Book(String titre, String genre, String auteur, int nbPages) {
		this.titre = titre;
		this.genre = genre;
		this.auteur = auteur;
		this.nbPages = nbPages;
	}
	
	/**
	 * Get the description of the book
	 *
	 * @return a text description of the book
	 */
	public String toString() {
		String noteString = "\n";
		if(this.noteMoyenne > 0.0f && this.noteMoyenne <= 5.0f)
			noteString = " ; Note : "+String.format("%.2f", this.noteMoyenne)+"\n";
		return "Item type : Livre\nTitre : "+this.titre+" ; Genre : "+this.genre+" ; Auteur : "+this.auteur+" ; Nombre de pages : "+this.nbPages+noteString;
	}
}