package avis;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/** 
 * <p>
 * <b>Class Review, utilisée pour représentér une opinion.</b>
 * </p>
 */
public class Review {
	public String titre;
	public float note;
	public String commentaire;
	public String pseudo;
	private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	public String date;
	public String reviewDate = null;
	public float noteMoyenne = 0.0f;
	
	/**
	 * Constructeur d'un opinion d'item
	 * 
	 * @param pseudo Le pseudo de l'utilisateur qui émet une opinion.
	 * @param titre Le titre de l'item évalué.
	 * @param note La note attribuée à l'item.
	 * @param commentaire Un commentaire sur l'item.
	 */
	public Review(String pseudo, String titre, float note, String commentaire) {
		this.note = note;
		this.titre = titre;
		this.commentaire = commentaire;
		this.pseudo = pseudo;
		this.date = dateFormat.format(new Date());
	}
	
	/**
	 * Constructeur d'un opinion d'opinion
	 * 
	 * @param pseudo Le pseudo de l'utilisateur qui émet une opinion.
	 * @param titre Le titre de l'item sur lequel porte l'opinion évaluée.
	 * @param reviewDate Date de l'opinion évaluée.
	 * @param note La note attribuée à l'opinion.
	 * @param commentaire Un commentaire sur l'opinion.
	 */
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
