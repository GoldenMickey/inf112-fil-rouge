package avis;

public class Film {
	public String titre;
	public String genre;
	public String realisateur;
	public String scenariste;
	public int duree;
	
	public Film(String titre, String genre, String realisateur, String scenariste, int duree) {
		this.titre = titre;
		this.genre = genre;
		this.realisateur = realisateur;
		this.scenariste = scenariste;
		this.duree= duree;
	}
}