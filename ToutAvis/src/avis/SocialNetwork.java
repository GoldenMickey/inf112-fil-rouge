package avis;

import java.util.LinkedList;

import exception.*;

/** 
 * @author A. Beugnard, 
 * @author G. Ouvradou
 * @author B. Prou
 * @date février - mars 2011
 * @version V0.6
 */


/** 
 * <p>
 * <b>Système de mutualisation d'opinions portant sur des domaines
 * variés (littérature, cinéma, art, gastronomie, etc.) et non limités.</b>
 * </p>
 * <p>
 * L'accès aux items et aux opinions qui leurs sont associées
 * est public. La création d'item et le dépôt d'opinion nécessite en revanche 
 * que l'utilisateur crée son profil au préalable.
 * </p>
 * <p>
 * Lorsqu'une méthode peut lever deux types d'exception, et que les conditions sont réunies 
 * pour lever l'une et l'autre, rien ne permet de dire laquelle des deux sera effectivement levée.
 * </p>
 * <p>
 * Dans une version avancée (version 2), une opinion peut également
 * être évaluée. Chaque membre se voit dans cette version décerner un "karma" qui mesure
 * la moyenne des notes portant sur les opinions qu'il a émises.
 * L'impact des opinions entrant dans le calcul de la note moyenne attribuée à un item
 * est pondéré par le karma des membres qui les émettent.
 * </p>
 */

public class SocialNetwork {
	LinkedList <Member> members;
	LinkedList <Film> films;
	LinkedList <Book> books;
	LinkedList <Review> filmReviews;
	LinkedList <Review> bookReviews;
	LinkedList <Review> opinionReviews;

	/**
	 * <i>SocialNetwok</i> Constructor
	 * 
	 */
	public SocialNetwork() {
		this.members = new LinkedList <Member>();
		this.films = new LinkedList <Film>();
		this.books = new LinkedList <Book>();
		this.filmReviews = new LinkedList <Review>();
		this.bookReviews = new LinkedList <Review>();
		this.opinionReviews = new LinkedList <Review>();
	}

	/**
	 * Get the number of members of the <i>SocialNetwork</i>
	 * 
	 * @return The number of members
	 */
	public int nbMembers() {
		return this.members.size();
	}

	/**
	 * Get the number of films of the <i>SocialNetwork</i>
	 * 
	 * @return The number of films
	 */
	public int nbFilms() {
		return this.films.size();
	}

	/**
	 * Get the number of books of the <i>SocialNetwork</i>
	 * 
	 * @return The number of books
	 */
	public int nbBooks() {
		return this.books.size();
	}

	/**
	 * Get the number of reviews concerning films, books or opinions of the <i>SocialNetwork</i>
	 * 
	 * @param itemType The type of items attached to the reviews.
	 * 
	 * @return The number of reviews
	 */
	public int nbReviews(String itemType) {
		if(itemType == "film") {
			return this.filmReviews.size();
		} else if(itemType == "book") {
			return this.bookReviews.size();
		} else
			return this.opinionReviews.size();
	}

	/**
	 * Ajouter un nouveau membre au <i>SocialNetwork</i>
	 * 
	 * @param pseudo son pseudo
	 * @param password son mot de passe 
	 * @param profil un slogan choisi par le membre pour se définir
	 * 
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  si le pseudo n'est pas instancié ou a moins de 1 caractère autre que des espaces .  </li>
	 *  <li>  si le password n'est pas instancié ou a moins de 4 caractères autres que des leadings or trailing blanks. </li>
	 *  <li>  si le profil n'est pas instancié.  </li>
	 * </ul><br>       
	 * 
	 * @throws MemberAlreadyExists membre de même pseudo déjà présent dans le <i>SocialNetwork</i> (même pseudo : indifférent à  la casse  et aux leadings et trailings blanks)
	 * 
	 */
	public void addMember(String pseudo, String password, String profil) throws BadEntry, MemberAlreadyExists  {
		//Tests sur les paramètres d'entrées
		//BadEntry
		if(pseudo == null || pseudo.trim().length() < 1) {
			throw new BadEntry("No value given for pseudo or shorter than 1 character");
		}
		
		if(password == null || password.trim().length() < 4) {
			throw new BadEntry("No value given for password or shorter than 4 characters");
		}
		
		if(profil == null) {
			throw new BadEntry("No value given for profil");
		}
		
		//MemberAlreadyExists
		for (Member m : members) {
			if(m.pseudo.trim().toLowerCase().equals(pseudo.trim().toLowerCase()))
				throw new MemberAlreadyExists();
	    }
		
		//Create and add member
		Member member = new Member(pseudo, password, profil);
		this.members.add(member);
	}
	/**
	 * Ajouter un nouvel item de film au <i>SocialNetwork</i> 
	 * 
	 * @param pseudo le pseudo du membre
	 * @param password le password du membre 
	 * @param titre le titre du film
	 * @param genre son genre (aventure, policier, etc.)
	 * @param realisateur le réalisateur
	 * @param scenariste le scénariste
	 * @param duree sa durée en minutes
	 * 
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  si le pseudo n'est pas instancié ou a moins de 1 caractère autre que des espaces .  </li>
	 *  <li>  si le password n'est pas instancié ou a moins de 4 caractères autres que des leadings or trailing blanks. </li>
	 *  <li>  si le titre n'est pas instancié ou a moins de 1 caractère autre que des espaces.  </li>
	 *  <li>  si le genre n'est pas instancié. </li>
	 *  <li>  si le réalisateur n'est pas instancié. </li>
	 *  <li>  si le scénariste n'est pas instancié. </li>
	 *  <li>  si la durée n'est pas positive.  </li>
	 * </ul><br>       
	 * @throws NotMember : si le pseudo n'est pas celui d'un membre ou si le pseudo et le password ne correspondent pas.
	 * @throws ItemFilmAlreadyExists : item film de même titre  déjà présent (même titre : indifférent à  la casse  et aux leadings et trailings blanks)
	 * 
	 */
	public void addItemFilm(String pseudo, String password, String titre, String genre, String realisateur, String scenariste, int duree) throws BadEntry, NotMember, ItemFilmAlreadyExists {
		if(pseudo == null || pseudo.trim().length() < 1) {
			throw new BadEntry("No value given for pseudo or shorter than 1 character");
		}
		
		if(password == null || password.trim().length() < 4) {
			throw new BadEntry("No value given for password or shorter than 4 characters");
		}
		
		if(titre == null || titre.trim().length() < 1) {
			throw new BadEntry("No value given for titre or shorter than 1 character");
		}
		
		if(genre == null) {
			throw new BadEntry("No value given for genre");
		}
		
		if(realisateur == null) {
			throw new BadEntry("No value given for realisateur");
		}
		
		if(scenariste == null) {
			throw new BadEntry("No value given for scenariste");
		}
		
		if(duree < 1) {
			throw new BadEntry("Unvalid duree");
		}
		
		if(memberExists(pseudo, password) == null)
			throw new NotMember("Wrong pseudo and/or password or Member doesn't exists");
		
		//MemberAlreadyExists
		for (Film f : films) {
			if(f.titre.trim().toLowerCase().equals(titre.trim().toLowerCase()))
				throw new ItemFilmAlreadyExists();
	    }
		
		//Create and add film
		Film film = new Film(titre, genre, realisateur, scenariste, duree);
		films.add(film);
	}
	
	

	/**
	 * Util : memberExists(pseudo, password)
	 */
	private Member memberExists(String pseudo, String password) {
		Member m = null;
		
		for(Member member : members) {
			if(member.pseudo.trim().toLowerCase().equals(pseudo.trim().toLowerCase()) &&
			member.password.equals(password))
				m = member;
		}
		
		return m;
	}

	/**
	 * Ajouter un nouvel item de livre au <i>SocialNetwork</i> 
	 * 
	 * @param pseudo le pseudo du membre
	 * @param password le password du membre 
	 * @param titre le titre du livre
	 * @param genre son genre (roman, essai, etc.)
	 * @param auteur l'auteur
	 * @param nbPages le nombre de pages
	 * 
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  si le pseudo n'est pas instancié ou a moins de 1 caractère autre que des espaces .  </li>
	 *  <li>  si le password n'est pas instancié ou a moins de 4 caractères autres que des leadings or trailing blanks. </li>
	 *  <li>  si le titre n'est pas instancié ou a moins de 1 caractère autre que des espaces.  </li>
	 *  <li>  si le genre n'est pas instancié. </li>
	 *  <li>  si l'auteur n'est pas instancié. </li>
	 *  <li>  si le nombre de pages n'est pas positif.  </li>
	 * </ul><br>       
	 * @throws NotMember : si le pseudo n'est pas celui d'un membre ou si le pseudo et le password ne correspondent pas.
	 * @throws ItemBookAlreadyExists item livre de même titre  déjà présent (même titre : indifférent à la casse  et aux leadings et trailings blanks)
	 * 
	 * 
	 */
	public void addItemBook(String pseudo, String password, String titre, String genre, String auteur, int nbPages) throws  BadEntry, NotMember, ItemBookAlreadyExists{
		
		if(pseudo == null || pseudo.trim().length() < 1) {
			throw new BadEntry("No value given for pseudo or shorter than 1 character");
		}
		
		if(password == null || password.trim().length() < 4) {
			throw new BadEntry("No value given for password or shorter than 4 characters");
		}
		
		if(titre == null || titre.trim().length() < 1) {
			throw new BadEntry("No value given for titre or shorter than 1 character");
		}
		
		if(genre == null) {
			throw new BadEntry("No value given for genre");
		}
		
		if(auteur == null) {
			throw new BadEntry("No value given for auteur");
		}
		
		if(nbPages < 1) {
			throw new BadEntry("Negative value for nbPages");
		}

		if(memberExists(pseudo, password) == null)
			throw new NotMember("Wrong pseudo and/or password or Member doesn't exists");
		
		//BookAlreadyExists
		for (Book b : books) {
			if(b.titre.trim().toLowerCase().equals(titre.trim().toLowerCase()))
				throw new ItemBookAlreadyExists();
	    }
		
		//Create and add book
		Book book = new Book(titre, genre, auteur, nbPages);
		books.add(book);
	}

	/**
	 * Consulter les items du <i>SocialNetwork</i> par nom
	 * 
	 * @param nom son titre (eg. titre d'un film, d'un livre, etc.)
	 * 
	 * @throws BadEntry : si le titre n'est pas instancié ou a moins de 1 caractère autre que des espaces.
	 * 
	 * @return la liste des représentations textuelles de tous les items ayant ce titre 
	 * Cette représentation contiendra la note de l'item s'il a été noté.
	 * (une liste vide si aucun item ne correspond) 
	 */
	public LinkedList <String> consultItems(String nom) throws BadEntry {

		if(nom == null || nom.trim().length() < 1) {
			throw new BadEntry("No value given for search string or shorter than 1 character");
		}
		
		LinkedList <String> results = new LinkedList <String> ();
		
		for(Film f : films) {
			if(f.titre.trim().toLowerCase().contains(nom.trim().toLowerCase()))
				results.add(f.toString());
		}
		
		for(Book b : books) {
			if(b.titre.trim().toLowerCase().contains(nom.trim().toLowerCase()))
				results.add(b.toString());
		}
		
		return results;
	}



	/**
	 * Donner son opinion sur un item film.
	 * Ajoute l'opinion de ce membre sur ce film au <i>SocialNetwork</i> 
	 * S'il préexiste une opinion de ce membre sur ce film, elle est mise à jour avec ces nouvelles valeurs.
	 * 
	 * @param pseudo pseudo du membre émettant l'opinion
	 * @param password son mot de passe
	 * @param titre titre du film  concerné
	 * @param note la note qu'il donne au film 
	 * @param commentaire ses commentaires
	 * 
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  si le pseudo n'est pas instancié ou a moins de 1 caractère autre que des espaces .  </li>
	 *  <li>  si le password n'est pas instancié ou a moins de 4 caractères autres que des leadings or trailing blanks. </li>
	 *  <li>  si le titre n'est pas instancié ou a moins de 1 caractère autre que des espaces.  </li>
	 *  <li>  si la note n'est pas comprise entre 0.0 et 5.0. </li>
	 *  <li>  si le commentaire n'est pas instancié. </li>
	 * </ul><br>       
	 * @throws NotMember : si le pseudo n'est pas celui d'un membre ou si le pseudo et le password ne correspondent pas.
	 * @throws NotItem : si le titre n'est pas le titre d'un film.
	 * 
	 * @return la note moyenne des notes sur ce film  
	 */
	public float reviewItemFilm(String pseudo, String password, String titre, float note, String commentaire) throws BadEntry, NotMember, NotItem {
		if(pseudo == null || pseudo.trim().length() < 1) {
			throw new BadEntry("No value given for pseudo or shorter than 1 character");
		}
		
		if(password == null || password.trim().length() < 4) {
			throw new BadEntry("No value given for password or shorter than 4 characters");
		}
		
		if(titre == null || titre.trim().length() < 1) {
			throw new BadEntry("No value given for titre or shorter than 1 character");
		}
		
		if(commentaire == null) {
			throw new BadEntry("No value given for genre");
		}
		
		if(note < 0.0f || note > 5.0f) {
			throw new BadEntry("Negative duree");
		}
		
		Member user = memberExists(pseudo, password);
		if(user == null)
			throw new NotMember("Wrong pseudo and/or password or Member doesn't exists");

		float sommeNote = 0.0f;
		int nbReviews = 0;
		int alreadyReviewed = 0;
		Film filmToReview = null;
		float sommeKarmas = 0;
		
		for (Film f : films) { //Parcours les films
			if(f.titre.trim().toLowerCase().equals(titre.trim().toLowerCase())) {
				filmToReview = f;
				for(Review r : filmReviews) {
					if(f.titre.trim().toLowerCase().equals(r.titre.trim().toLowerCase())) { //Une review de ce film existe
						if(r.pseudo.trim().toLowerCase().equals(pseudo.trim().toLowerCase())) { //Une review de ce film existe deja par cet user
							r.note = note;
							r.commentaire = commentaire;
							alreadyReviewed = 1;
						}
					
						nbReviews++;
						float uKarma = getUserKarma(pseudo);
						sommeNote = sommeNote + (r.note * uKarma); //Pondération par le karma
						sommeKarmas = sommeKarmas + uKarma;
					}
				}
			}
	    }
		
		if(filmToReview == null)
			throw new NotItem("Not film title");
		
		if(alreadyReviewed == 0) { //User didn't review this film
			filmReviews.add(new Review(pseudo, titre, note, commentaire));
			nbReviews++;
			sommeNote = sommeNote + (note * getUserKarma(pseudo));
		}
		
		user.karma = getUserKarma(pseudo);
		
		filmToReview.noteMoyenne = (sommeNote / (nbReviews + sommeKarmas));
		return filmToReview.noteMoyenne;
	}

	/**
	 * Donner son opinion sur un item livre.
	 * Ajoute l'opinion de ce membre sur ce livre au <i>SocialNetwork</i> 
	 * Si une opinion de ce membre sur ce livre  préexiste, elle est mise à jour avec ces nouvelles valeurs.
	 * 
	 * @param pseudo pseudo du membre émettant l'opinion
	 * @param password son mot de passe
	 * @param titre titre du livre  concerné
	 * @param note la note qu'il donne au livre 
	 * @param commentaire ses commentaires
	 * 
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  si le pseudo n'est pas instancié ou a moins de 1 caractère autre que des espaces .  </li>
	 *  <li>  si le password n'est pas instancié ou a moins de 4 caractères autres que des leadings or trailing blanks. </li>
	 *  <li>  si le titre n'est pas instancié ou a moins de 1 caractère autre que des espaces.  </li>
	 *  <li>  si la note n'est pas comprise entre 0.0 et 5.0. </li>
	 *  <li>  si le commentaire n'est pas instancié. </li>
	 * </ul><br>       
	 * @throws NotMember : si le pseudo n'est pas celui d'un membre ou si le pseudo et le password ne correspondent pas.
	 * @throws NotItem : si le titre n'est pas le titre d'un livre.
	 * 
	 * @return la note moyenne des notes sur ce livre
	 */
	public float reviewItemBook(String pseudo, String password, String titre, float note, String commentaire) throws BadEntry, NotMember, NotItem {
		if(pseudo == null || pseudo.trim().length() < 1) {
			throw new BadEntry("No value given for pseudo or shorter than 1 character");
		}
		
		if(password == null || password.trim().length() < 4) {
			throw new BadEntry("No value given for password or shorter than 4 characters");
		}
		
		if(titre == null || titre.trim().length() < 1) {
			throw new BadEntry("No value given for titre or shorter than 1 character");
		}
		
		if(commentaire == null) {
			throw new BadEntry("No value given for genre");
		}
		
		if(note < 0.0f || note > 5.0f) {
			throw new BadEntry("Negative duree");
		}
		
		Member user = memberExists(pseudo, password);
		if(user == null)
			throw new NotMember("Wrong pseudo and/or password or Member doesn't exists");

		float sommeNote = 0.0f;
		int nbReviews = 0;
		int alreadyReviewed = 0;
		Book bookToReview = null;
		//float sommeKarmas = 0;
		
		for (Book b : books) { //Parcours les films
			if(b.titre.trim().toLowerCase().equals(titre.trim().toLowerCase())) { //Film trouvé par titre
				bookToReview = b;
				for(Review r : bookReviews) { //Parcours les reviews
					if(b.titre.trim().toLowerCase().equals(r.titre.trim().toLowerCase())) { //Une review de ce film existe (meme titre)
						if(r.pseudo.trim().toLowerCase().equals(pseudo.trim().toLowerCase())) { //Une review de ce film existe deja par cet user (meme pseudo)
							//Mise à jour de des infos de la review
							r.note = note;
							r.commentaire = commentaire;
							alreadyReviewed = 1;
						}
						
						nbReviews++;
						sommeNote = sommeNote + r.note;
					}
				}
			}
	    }
		
		if(bookToReview == null)
			throw new NotItem("Not a book title");
		
		if(alreadyReviewed == 0) { //User didn't review this film
			bookReviews.add(new Review(pseudo, titre, note, commentaire));
			nbReviews++;
			sommeNote = sommeNote + note;
		}
		
		bookToReview.noteMoyenne = (sommeNote / nbReviews);
		
		updateItemsNote();

		return bookToReview.noteMoyenne;
	}
	

	/**
	 * Emit a review about an opinion.
	 * Add the review to the opinion of the <i>SocialNetwork</i> 
	 * If the member already reviewed the opinion, his review will be updated.
	 * 
	 * @param pseudo pseudo of the reviewing member
	 * @param password password of the reviewing member
	 * @param titre title of the opinion to review
	 * @param date the date of the opinion to review
	 * @param note the mark given to the opinion to review
	 * @param commentaire a comment about the opinion to review
	 * 
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  if the pseudo is not instantiated or is shorter than 1 character other than blank spaces.  </li>
	 *  <li>  if the password is not instantiated or is shorter than 4 characters other than leadings or trailing blanks. </li>
	 *  <li>  if the title is not instantiated or is shorter than 1 character other than blank spaces.  </li>
	 *  <li>  if the mark is inferior to zero or superior to five. </li>
	 *  <li>  if the comment is not instantiated. </li>
	 * </ul><br>       
	 * @throws NotMember : If the given pseudo and password don't match a member.
	 * @throws NotItem : If the title and/or the date doesn't match an opinion.
	 * 
	 * @return the average mark of this opinion
	 */
	public float reviewOpinion(String pseudo, String password, String date, String titre, float note, String commentaire) throws BadEntry, NotMember, NotItem {
		if(pseudo == null || pseudo.trim().length() < 1) {
			throw new BadEntry("No value given for pseudo or shorter than 1 character");
		}
		
		if(password == null || password.trim().length() < 4) {
			throw new BadEntry("No value given for password or shorter than 4 characters");
		}
		
		if(titre == null || titre.trim().length() < 1) {
			throw new BadEntry("No value given for titre or shorter than 1 character");
		}
		
		if(date == null || date.trim().length() < 1) {
			throw new BadEntry("No value given for date");
		}
		
		if(commentaire == null) {
			throw new BadEntry("No value given for genre");
		}
		
		if(note < 0.0f || note > 5.0f) {
			throw new BadEntry("Negative duree");
		}
		
		Member user = memberExists(pseudo, password);
		if(user == null)
			throw new NotMember("Wrong pseudo and/or password or Member doesn't exists");

		float sommeNote = 0.0f;
		int nbReviews = 0;
		int alreadyReviewed = 0;
		Review opinionToReview = null;
		
		for (Review review : filmReviews) { //Parcours les opinions de film
			if(review.date.equals(date) && review.titre.trim().toLowerCase().equals(titre.trim().toLowerCase())) { //La review est identifiée par comp des dates et du film correspondant
				opinionToReview = review;
				for(Review r : opinionReviews) { //Parcours les reviews d'opinion
					if(opinionToReview.date.equals(r.reviewDate) && review.titre.trim().toLowerCase().equals(titre.trim().toLowerCase())) { //Une review de cette opinion existe
						if(r.pseudo.trim().toLowerCase().equals(pseudo.trim().toLowerCase())) { //Une review de cette opinion existe deja par cet user
							r.note = note;
							r.commentaire = commentaire;
							alreadyReviewed = 1;
						}
						
						nbReviews++;
						sommeNote = sommeNote + r.note;
					}
				}
			}
	    }
		
		for (Review review : bookReviews) { //Parcours les opinions de film
			if(review.date.equals(date) && review.titre.trim().toLowerCase().equals(titre.trim().toLowerCase())) { //La review est identifiée par comp des dates et du film correspondant
				opinionToReview = review;
				for(Review r : opinionReviews) { //Parcours les reviews d'opinion
					if(opinionToReview.date.equals(r.reviewDate) && review.titre.trim().toLowerCase().equals(titre.trim().toLowerCase())) { //Une review de cette opinion existe
						if(r.pseudo.trim().toLowerCase().equals(pseudo.trim().toLowerCase())) { //Une review de cette opinion existe deja par cet user
							r.note = note;
							r.commentaire = commentaire;
							alreadyReviewed = 1;
						}
						
						nbReviews++;
						sommeNote = sommeNote + r.note;
					}
				}
			}
	    }
		
		if(opinionToReview == null)
			throw new NotItem("No opinion found");
		
		if(alreadyReviewed == 0) { //User didn't review this opinion
			opinionReviews.add(new Review(pseudo, titre, opinionToReview.date, note, commentaire));
			nbReviews++;
			sommeNote = sommeNote + note;
		}
		
		updateItemsNote();
		
		opinionToReview.noteMoyenne = (sommeNote / nbReviews);
		return opinionToReview.noteMoyenne;
	}
	
	private float getUserKarma(String pseudo) {
		LinkedList <Review> reviews = new LinkedList <Review>();
		
		int nOpinionReviews;
		float nSomme = 0;
		
		if(opinionReviews.size() > 0) {
			for(Review r : filmReviews) { //On cherche les reviews de cet utilisateur sur les films
				if(r.pseudo.trim().toLowerCase().equals(pseudo.trim().toLowerCase())) { //Review de cet utilisateur trouvée
					nOpinionReviews = 0;
					for(Review or : opinionReviews) { //On cherche les reviews de cette opinion par date et titre
						if(or.reviewDate == r.date && or.titre == r.titre) {
							nOpinionReviews++;
							nSomme = nSomme + or.note;
						}
					}
					
					r.noteMoyenne = nSomme/nOpinionReviews;
					reviews.add(r);
				}
			}
			
			for(Review r : bookReviews) { //On cherche les reviews de cet utilisateur sur les films
				if(r.pseudo.trim().toLowerCase().equals(pseudo.trim().toLowerCase())) { //Review de cet utilisateur trouvée
					nOpinionReviews = 0;
					for(Review or : opinionReviews) { //On cherche les reviews de cette opinion par date et titre
						if(or.reviewDate == r.date && or.titre == r.titre) {
							nOpinionReviews++;
							nSomme = nSomme + or.note;
						}
					}
					
					r.noteMoyenne = nSomme/nOpinionReviews;
					reviews.add(r);
				}
			}
		}
		
		if(reviews.size() > 0) {
			float nmSomme = 0;
			
			for(Review r : reviews) {
				nmSomme = nmSomme + r.noteMoyenne;
			}
			
			return nmSomme/reviews.size();
		} else
			return 1;
	}
	
	private void updateItemsNote() {
		float nSomme;
		float kSomme;
		int nRev;
		
		for(Film f : films) {
			nSomme = 0;
			kSomme = 0;
			nRev = 0;
			
			for(Review r : filmReviews) {
				if(f.titre.trim().toLowerCase().equals(r.titre.trim().toLowerCase())) {
					nRev++;
					for(Member m : members) {
						if(m.pseudo.trim().toLowerCase().equals(r.pseudo.trim().toLowerCase())) {
							m.karma = getUserKarma(m.pseudo);
							nSomme = nSomme + (r.note * m.karma);
							kSomme = kSomme + m.karma;
						}
					}	
				}
			}
			
			f.noteMoyenne = nSomme/(nRev+kSomme);
		}

		for(Book b : books) {
			nSomme = 0;
			kSomme = 0;
			nRev = 0;
			
			for(Review r : bookReviews) {
				if(b.titre.trim().toLowerCase().equals(r.titre.trim().toLowerCase())) {
					nRev++;
					for(Member m : members) {
						if(m.pseudo.trim().toLowerCase().equals(r.pseudo.trim().toLowerCase())) {
							m.karma = getUserKarma(m.pseudo);
							nSomme = nSomme + (r.note * m.karma);
							kSomme = kSomme + m.karma;
						}
					}	
				}
			}
			
			b.noteMoyenne = nSomme/(nRev+kSomme);
		}
	}

	/**
	 * Get the reviews of a film of the <i>SocialNetwork</i> 
	 * @param titre the title of the film
	 * 
	 * @return a list of the reviews concerning the film (empty if there are no reviews)
	 */
	public LinkedList <Review> getFilmReviews(String titre) {
		LinkedList <Review> results = new LinkedList <Review>();
		
		for(Review r : filmReviews) {
			if(r.titre == titre)
				results.add(r);
		}
		
		return results;
	}

	/**
	 * Get the reviews of a book of the <i>SocialNetwork</i> 
	 * @param titre the title of the book
	 * 
	 * @return a list of the reviews concerning the book (empty if there are no reviews)
	 */
	public LinkedList <Review> getBookReviews(String titre) {
		LinkedList <Review> results = new LinkedList <Review>();
		
		for(Review r : bookReviews) {
			if(r.titre == titre)
				results.add(r);
		}
		
		return results;
	}

	/**
	 * Get the reviews of an opinion of the <i>SocialNetwork</i> 
	 * @param titre the title of the opinion
	 * 
	 * @return a list of the reviews concerning the opinion (empty if there are no reviews)
	 */
	public LinkedList <Review> getOpinionReviews(String titre) {
		LinkedList <Review> results = new LinkedList <Review>();
		
		for(Review r : opinionReviews) {
			if(r.titre == titre)
				results.add(r);
		}
		
		return results;
	}

	/**
	 * Get the review emitted by a member on a film <i>SocialNetwork</i> 
	 * @param titre the title of a film 
	 * @param pseudo the pseudo of a member
	 * 
	 * @return a list of the reviews emitted by a member concerning a film (empty if there are no reviews)
	 */
	public Review getFilmUserReview(String titre, String pseudo) {
		Review review = null;
		
		for(Review r : filmReviews) {
			if(r.titre == titre && r.pseudo.trim().toLowerCase().equals(pseudo.trim().toLowerCase()))
				review = r;
		}
		
		return review;
	}

	/**
	 * Get the review emitted by a member on a book <i>SocialNetwork</i> 
	 * @param titre the title of a book 
	 * @param pseudo the pseudo of a member
	 * 
	 * @return a list of the reviews emitted by a member concerning a book (empty if there are no reviews)
	 */
	public Review getBookUserReview(String titre, String pseudo) {
		Review review = null;
		
		for(Review r : bookReviews) {
			if(r.titre == titre && r.pseudo.trim().toLowerCase().equals(pseudo.trim().toLowerCase()))
				review = r;
		}
		
		return review;
	}


	/**
	 * Obtenir une représentation textuelle du <i>SocialNetwork</i>.
	 * 
	 * @return la chaîne de caractères représentation textuelle du <i>SocialNetwork</i> 
	 */
	public String toString() {
		return "Ce SocialNetwork est constitué de "+this.nbFilms()+" films, "+this.nbBooks()+" livres et "+(this.nbReviews("film")+this.nbReviews("book")+this.nbReviews("opinion"))+" opinions données par "+this.nbMembers()+" membres.";
	}
}
