package avis;

/** 
 * @author F. Portecop, L. Halley
 * @date Mai 2016
 * @version V1.0
 */

/** 
 * <p>
 * <b>Class Film, used to represent a film of a SocialNetwork.</b>
 * </p>
 */
public class Film {
	public String titre;
	public String genre;
	public String realisateur;
	public String scenariste;
	public int duree;
	public float noteMoyenne;
	
	/**
	 * Film constructor
	 * 
	 * @param titre the title of the film.
	 * @param genre the genre of the film.
	 * @param realisateur the film realisator.
	 * @param scenariste the film scriptwriter.
	 * @param duree the length of the film. (strictly positive).
	 */
	public Film(String titre, String genre, String realisateur, String scenariste, int duree) {
		this.titre = titre;
		this.genre = genre;
		this.realisateur = realisateur;
		this.scenariste = scenariste;
		this.duree= duree;
	}
	
	/**
	 * Get the description of the film
	 *
	 * @return a text description of the film
	 */
	public String toString() {
		String noteString = "\n";
		if(this.noteMoyenne > 0.0f && this.noteMoyenne <= 5.0f)
			noteString = " ; Note : "+String.format("%.2f", this.noteMoyenne)+"\n";
		return "Item type : Film\nTitre : "+this.titre+" ; Genre : "+this.genre+" ; Réalisateur : "+this.realisateur+" ; Scenariste : "+this.scenariste+" ; Durée : "+this.duree+" minutes"+noteString;
	}
}