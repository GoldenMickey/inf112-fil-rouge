package avis;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Review {
	public String titre;
	public float note;
	public String commentaire;
	public String pseudo;
	private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	public String date;
	public String reviewDate = null;
	public float noteMoyenne = 0.0f;
	
	public Review(String pseudo, String titre, float note, String commentaire) {
		this.note = note;
		this.titre = titre;
		this.commentaire = commentaire;
		this.pseudo = pseudo;
		this.date = dateFormat.format(new Date());
	}
	
	public Review(String pseudo, String titre, String reviewDate, float note, String commentaire) {
		this.note = note;
		this.titre = titre;
		this.commentaire = commentaire;
		this.pseudo = pseudo;
		this.date = dateFormat.format(new Date());
		this.reviewDate = reviewDate;
	}
	
	/**
	 */
	public String toString(){
		String noteString = "\n";
		if(this.noteMoyenne > 0.0f && this.noteMoyenne <= 5.0f)
			noteString = " ; Ce commentaire a été évalué : "+String.format("%.2f", this.noteMoyenne)+"\n";
		return "Review de :"+this.pseudo+" sur "+this.titre+", Note : "+this.note+", Commentaire : "+this.commentaire+noteString;
	}
}
