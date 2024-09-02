package Playlists;

import java.util.ArrayList;
import java.util.Objects;

public class Album {

	// les attributs
	private String nom;
	private int annee;
	private String artiste;
	private ArrayList<Chanson> album;

	/**
	 * constructeur sans parametre qui initialise l'album avec des valeurs par defaut
	 * nom : "album"
	 * annee : 50s
	 * Artiste : Nina Simone
	 */
	public Album() {
		this("album", 2000, "Nina Simone");
	}

	/**
	 * constructeur avec parametre
	 * @param nom : le nom de l'album
	 * @param annee : l'anne de parution de l'album
	 * @param artiste : l'artiste auteur de l'album
	 */
	public Album(String nom, int annee, String artiste) {
		setNom(nom);
		setAnnee(annee);
		setArtiste(artiste);
		album = new ArrayList<>();
	}

	/**
	 * c'est le hashcode
	 */
	@Override
	public int hashCode() {
		return Objects.hash(annee, artiste, nom);
	}

	/**
	 * compare les albums sur leurs nom, leurs annee de sorties et leurs auteur ( :artiste)
	 * @param album : l'album a comparer avec l'album courant sur les parametre defini plus haut
	 * @return
	 */
	public boolean equals(Album album) {
		if (this.getNom().equalsIgnoreCase(album.getNom())
				&& this.getArtiste().equalsIgnoreCase(album.getArtiste())
				&& this.getAnnee() == album.getAnnee()) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Ajoute une chanson a l'album
	 * @param chanson
	 */
	public void ajouter(Chanson chanson) {
		album.add(chanson);
	}

	/**
	 * Ajoute un ArrayList de chanson a l'album
	 * @param chanson
	 */
	public void ajouter(ArrayList<Chanson> chansons) {
		for(Chanson c : chansons) {
			album.add(c);
		}
	}

	/**
	 * @return : retourne le nombre de chanson dans l'album
	 */
	public int nbTotalChanson() {
		return album.size();
	}

	/**
	 * recherche une chanson dans l'album par son titre
	 * @param titre : le titre de la chanson
	 * @return : retourne l'indice de la chanson dans l'album
	 * @throws Exception : renvoie une exception si la chanson n'existe pas dans l'album
	 */
	public int rechercher(String titre) throws Exception {
		int indice = -1;
		for(Chanson c: album) {
			if(c.getTitre().equalsIgnoreCase(titre));
			indice = album.indexOf(c);
		}
		if(indice != -1) {
			return indice;
		}
		else {
			throw new Exception("La chanson n'existe pas dans l'album");
		}
	}

	/**
	 * affiche le contenue de l'album
	 * son auteur/artiste, son nom, son annee d'edition et sa liste de chanson
	 */
	@Override
	public String toString() {
		String tab = "";
		for(Chanson c: album) {
			tab += "\t" + c.toString() + "\n";
		}
		return artiste + " : " + nom + " - " + annee + "\n\n" + tab + "\n\n";
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the annee
	 */
	public int getAnnee() {
		return annee;
	}

	/**
	 * @param annee the annee to set
	 */
	public void setAnnee(int annee) {
		this.annee = annee;
	}

	/**
	 * @return the artiste
	 */
	public String getArtiste() {
		return artiste;
	}

	/**
	 * @param artiste the artiste to set
	 */
	public void setArtiste(String artiste) {
		this.artiste = artiste;
	}

	/**
	 * @return the album
	 */
	public ArrayList<Chanson> getAlbum() {
		return album;
	}

	/**
	 * @param album the album to set
	 */
	public void setAlbum(ArrayList<Chanson> album) {
		this.album = album;
	}

	/**
	 * classe test
	 * @param args
	 */
	public static void main(String[] args) {
		//les albums
		Album defaultAlbum = new Album();
		Album personalisedAlbum = new Album("album", 2000, "auteur");
		Album personalisedAlbum2 = new Album("album 2", 2000, "auteur 2");

		//les chansons
		Chanson defaultSong = new Chanson();
		Chanson personalisedSong = new Chanson("Rasputin", 150);

		//la liste de chanson
		ArrayList<Chanson> chs = new ArrayList<>();
		chs.add(personalisedSong);
		chs.add(defaultSong);

		//l'ajout de chanson dans les albums
		personalisedAlbum.ajouter(defaultSong);
		personalisedAlbum2.ajouter(chs);



		System.out.println("Album par defaut : " + defaultAlbum.toString() +
				"\nAlbum personalisee : " + personalisedAlbum.toString() +
				"\nAlbum 2  personalisee : " + personalisedAlbum2.toString());

		//test du equals
		Album defaultAlbum2 = new Album();
		defaultAlbum2.ajouter(new Chanson());

		if(defaultAlbum2.equals(defaultAlbum)) {
			System.out.println("test equals entre defaultAlbum2 et defaultAlbum : true");
		}
		else {
			System.out.println("test equals entre defaultAlbum2 et defaultAlbum : false");
		}

		if(defaultAlbum2.equals(personalisedAlbum2)) {
			System.out.println("test equals entre defaultAlbum2 et personalisedAlbum2 : true");
		}
		else {
			System.out.println("test equals entre defaultAlbum2 et personalisedAlbum2 : false");
		}

		//test nombre de chanson
		System.out.println("le nombre de chanson dans l'album personalisedAlbum2 : " + personalisedAlbum2.nbTotalChanson());

		//test recherche chanson dans l'album
		try {
			System.out.println("la position de la recherche : " + personalisedAlbum2.rechercher("Rasputin"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
