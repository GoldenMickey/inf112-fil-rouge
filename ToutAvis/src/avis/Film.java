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
		String noteString = "";
		if(this.noteMoyenne > 0.0f && this.noteMoyenne < 5.0f)
			noteString = "\nNote : "+this.noteMoyenne;
		return "Item type : Film\nTitre : "+this.titre+"\nGenre : "+this.genre+"\nRéalisateur : "+this.realisateur+"\nScenariste : "+this.scenariste+"\nDurée : "+this.duree+" minutes"+noteString;
	}
}