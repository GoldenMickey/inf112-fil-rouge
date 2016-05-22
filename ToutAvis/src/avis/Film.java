package avis;

public class Film {
	public String titre;
	public String genre;
	public String realisateur;
	public String scenariste;
	public int duree;
	public float noteMoyenne;
	
	public Film(String titre, String genre, String realisateur, String scenariste, int duree) {
		this.titre = titre;
		this.genre = genre;
		this.realisateur = realisateur;
		this.scenariste = scenariste;
		this.duree= duree;
	}
	
	public String toString() {
		String noteString = "\n";
		if(this.noteMoyenne > 0.0f && this.noteMoyenne < 5.0f)
			noteString = " ; Note : "+this.noteMoyenne+"\n";
		return "Item type : Film\nTitre : "+this.titre+" ; Genre : "+this.genre+" ; Réalisateur : "+this.realisateur+" ; Scenariste : "+this.scenariste+" ; Durée : "+this.duree+" minutes"+noteString;
	}
}