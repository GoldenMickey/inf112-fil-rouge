package avis;

public class Member {
	/**
	 * @uml.property  name="pseudo"
	 */
	public String pseudo;

	/**
	 * @uml.property  name="password"
	 */
	public String password;

	/**
	 * @uml.property  name="profil"
	 */
	public String profil;

	/**
	 * @uml.property  name="karma"
	 */
	public float karma;
	
	
///////////////////////////////////////////////////////////////////////////////////////////
	/**
	 */
	public Member(String pseudo, String password, String profil){
		this.pseudo = pseudo;
		this.password = password;
		this.profil = profil;
		this.karma = 1;
	}

			
	/**
	 */
	public String toString(){
		return "";	
	}

	/**
	 * @uml.property  name="socialNetwork"
	 * @uml.associationEnd  multiplicity="(1 1)" inverse="member:SocialNetwork"
	 */
	private SocialNetwork socialNetwork = null;

	/**
	 * Getter of the property <tt>socialNetwork</tt>
	 * @return  Returns the socialNetwork.
	 * @uml.property  name="socialNetwork"
	 */
	public SocialNetwork getSocialNetwork() {
		return socialNetwork;
	}

	/**
	 * Setter of the property <tt>socialNetwork</tt>
	 * @param socialNetwork  The socialNetwork to set.
	 * @uml.property  name="socialNetwork"
	 */
	public void setSocialNetwork(SocialNetwork socialNetwork) {
		this.socialNetwork = socialNetwork;
	}
}
