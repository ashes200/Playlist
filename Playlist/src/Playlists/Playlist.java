package Playlists;

import java.util.ArrayList;
import java.util.Random;

public class Playlist {

	//les attributs
	private String nom;
	private ArrayList<Chanson> playlist;
	//private final int MAX = 10;

	/**
	 * constructeur sans parametre qui initialise la playlist avec des valeurs par defaut :
	 * nom : "nouvelle playlist"
	 */
	public Playlist() {
		this("nouvelle playlist");
	}

	/**
	 * constructeur avec parametres
	 * @param nom : le nom de la playlist
	 */
	public Playlist(String nom) {
		setNom(nom);
		playlist = new ArrayList<>();
	}

	/**
	 * ajoute une chanson a la playlist
	 * @param chanson : chanson a ajouter a la playlist
	 * @throws Exception
	 */
	public void ajouter(Chanson chanson) throws Exception {
		if(playlist.size() <= 10) {
			playlist.add(chanson);
		}
		else {
			throw new Exception("la playlist est pleine");
		}
	}

	/**
	 * ajoute un Arraylist de chanson a la playlist
	 * @param chansons : l'ArrayList de chanson a ajouter a la playlist
	 * @throws Exception
	 */
	public void ajouter(ArrayList<Chanson> chansons) throws Exception {
		for(Chanson c : chansons) {
			if(playlist.size() <= 10) {
				playlist.add(c);
			}
			else {
				throw new Exception("la playlist est pleine");
			}
		}
	}

	/**
	 * remplis la playliste avec des chanson au hasard
	 * @param musi
	 * @throws Exception
	 */
	public void remplirHasard(Musitheque musi) throws Exception {
		//remplit les 10 premieres place de la playlist avec des chanson au hassard
		while(this.nbTotalChanson() != 10) {
			int i = 0;
			Random valeur = new Random();
			
			//choisi un album au hasard
			int nombre1 = (valeur.nextInt(musi.nbTotalAlbum()));
			
			//choisi une chanson au hasard dans l'album choisis plus haut
			int nombre2 = (valeur.nextInt(musi.nbChansonAlbum(nombre1)));
			
			//fait une chanson avec les 2 nombre aleatoire chois plus haut
			Chanson chs = musi.getListe().get(nombre1).getAlbum().get(nombre2);
			
			//remplis la premiere place de la playlist
			if(this.nbTotalChanson() == 0) { // c'est lui qui marche pas
				this.ajouter(chs);
				i++;
			}
			//verifi si la chanson existe deja dans la playlist
			else {
				for(int e = i; e >= 0; e--) {
					if(! this.getPlaylist().get(e).getTitre().equalsIgnoreCase(chs.getTitre())) {
						this.ajouter(chs);
						i++;
					}
					//else {
						//i = i; // je sais il sert a rien
					//}
				}
			}
		}
	}

	/**
	 * supprime une chanson de la playlist grace a son indice
	 * @param indice : l'indice de la chanson a supprimer dans la playlist
	 */
	public void delete(int indice) {
		playlist.remove(indice);
	}

	/**
	 * supprime une chanson de la playlist grace a son titre
	 * @param titre
	 */
	public void delete(String titre) {
		playlist.remove(rechercher(titre));
	}
	
	/**
	 * recherche une chanson dans la playlist grace a son titre
	 * @param titre : titre de la chanson a rechercher
	 * @return : retourne l'indice de la chanson dans la playlist
	 */
	public int rechercher(String titre) {
		int retour = -1;
		for(Chanson c : playlist) {
			if(c.getTitre().equalsIgnoreCase(titre)) {
				retour = playlist.indexOf(c);
			}
		}
		return retour;
	}

	/**
	 * calcule la duree total des chanson dans la playlist
	 * @return : retourne le resultat sur un format : j:h:min:s
	 */
	public String duree() {
		int sec = 0;
		int min = 0;
		int heure = 0;
		int jour = 0;
		//calcule la duree en sec
		for(Chanson c : playlist) {
			sec += c.getDuree();
		}
		//calcule la duree en min
		if(sec > 60) {
			min = sec/60;
			sec = sec%60;
			//calcule la duree en heures
			if(min > 60) {
				heure = min/60;
				min = min%60;
				//calcule la duree en jours
				if(heure > 24) {
					jour = heure/24;
					heure = heure%24;
				}
			}
		}
		return jour + ":" + heure + ":" + min  + ":" + sec;
	}

	/**
	 * @return : le nombre de chanson dans la playlist
	 */
	public int nbTotalChanson() {
		return playlist.size();
	}

	/**
	 * efface toutes les chansons de la playlist
	 */
	public void clear() {
		playlist.clear();
	}

	/**
	 * affiche le contenue de la playlist :
	 * son nom et sa liste de chanson
	 */
	@Override
	public String toString() {
		String texte = "";
		for(Chanson c : playlist) {
			texte += c.toString() + "\n";
		}
		return "nom de la playlist : " + nom + "\n" + texte;
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
	 * @return the playlist
	 */
	public ArrayList<Chanson> getPlaylist() {
		return playlist;
	}
	/**
	 * @param playlist the playlist to set
	 * @throws Exception
	 */
	public void setPlaylist(ArrayList<Chanson> playlist) throws Exception {
		if(playlist.size() <= 10) {
			this.playlist = playlist;
		}
		else {
			throw new Exception("la playlist est pleine");
		}
	}
	
	/**
	 * test de la class Playlist
	 * @param args
	 */
	public static void main(String[] args) {
		Playlist pl = new Playlist();
		System.out.println(pl.getPlaylist().size());
		try {
			pl.ajouter(new Chanson());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(pl.toString());
		System.out.println(pl.getPlaylist().size());
	}
}
