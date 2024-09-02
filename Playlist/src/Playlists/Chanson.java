package Playlists;

public class Chanson {

	// les attributs
	private String titre;
	private int duree;

	/**
	 * constructeur sans parametre qui initialise la chanson avec des valeurs par defaut
	 * Titre : "feeling good"
	 * duree : 50 s
	 */
	public Chanson() {
		this("feeling good",150);
	}

	/**
	 * constructeur avec parametres
	 * @param titre : le titre de la chanson
	 * @param duree : la duree de la chanson en sec
	 */
	public Chanson(String titre, int duree) {
		setTitre(titre);
		setDuree(duree);
	}

	/**
	 * affiche le contenue de la chanson : son titre et sa duree
	 */
	@Override
	public String toString() {
		return titre + " - " + duree;
	}

	/**
	 * @return the titre
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * @param titre the titre to set
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}

	/**
	 * @return the duree
	 */
	public int getDuree() {
		return duree;
	}

	/**
	 * @param duree the duree to set
	 */
	public void setDuree(int duree) {
		this.duree = duree;
	}

	/**
	 * classe test
	 * @param args
	 */
	public static void main(String[] args) {
		Chanson defaultSong = new Chanson();
		Chanson personalisedSong = new Chanson("Rasputin", 150);

		System.out.println("chanson par defaut : " + defaultSong.toString() +
				"\nchanson personalisee : " + personalisedSong.toString());
	}
}
