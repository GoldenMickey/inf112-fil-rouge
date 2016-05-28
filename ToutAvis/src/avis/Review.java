package avis;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/** 
 * @author F. Portecop, L. Halley
 * @date Mai 2016
 * @version V1.0
 */

/** 
 * <p>
 * <b>Class Review, used to represent an opinion.</b>
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
	 * Item opinion constructor
	 * 
	 * @param pseudo the pseudo of the reviewing.
	 * @param titre the title of the reviewed item.
	 * @param note the mark.
	 * @param commentaire a comment about the item.
	 */
	public Review(String pseudo, String titre, float note, String commentaire) {
		this.note = note;
		this.titre = titre;
		this.commentaire = commentaire;
		this.pseudo = pseudo;
		this.date = dateFormat.format(new Date());
	}
	
	/**
	 * Opinion review constructor
	 * 
	 * @param pseudo the pseudo of the reviewing.
	 * @param titre the title of the reviewed item.
	 * @param reviewDate the date of the opinion to review.
	 * @param note the mark.
	 * @param commentaire a comment about the opinion.
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
	 * Get the description of the opinion
	 *
	 * @return a text description of the opinion
	 */
	public String toString(){
		String noteString = "\n";
		if(this.noteMoyenne > 0.0f && this.noteMoyenne <= 5.0f)
			noteString = " ; Cette opinion a été évaluée : "+String.format("%.2f", this.noteMoyenne)+"\n";
		return "Review de :"+this.pseudo+" sur "+this.titre+", Note : "+this.note+", Commentaire : "+this.commentaire+noteString;
	}
}
