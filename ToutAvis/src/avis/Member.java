package avis;

/** 
 * @author F. Portecop, L. Halley
 * @date Mai 2016
 * @version V1.0
 */

/** 
 * <p>
 * <b>Class Member, used to represent a user of a SocialNetwork.</b>
 * </p>
 */
public class Member {
	public static String pseudo;
	public static String password;
	public static String profil;
	public static float karma;
	
	/**
	 * Member constructor
	 * 
	 * @param pseudo the pseudo of the member.
	 * @param password the password of the member.
	 * @param profil the profil of the member.
	 */
	public Member(String pseudo, String password, String profil){
		this.pseudo = pseudo;
		this.password = password;
		this.profil = profil;
		this.karma = 1;
	}
}
