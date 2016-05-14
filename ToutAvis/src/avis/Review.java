package avis;

public class Review {
	public String titre;
	public float note;
	public String commentaire;
	public String pseudo;
	
	public Review(String pseudo, String titre, float note, String commentaire) {
		this.note = note;
		this.titre = titre;
		this.commentaire = commentaire;
		this.pseudo = pseudo;
	}
	
	/**
	 */
	public String toString(){
		return "Review de :"+this.pseudo+" sur "+this.titre+", Note : "+this.note+", Commentaire : "+this.commentaire;
	}
}
