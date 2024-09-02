package Playlists;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MesPlaylist {
	
	// les methodes
	private ArrayList<Playlist> mesPlaylist;

	/**
	 * constructeur sans parametre qui initialise la liste de playlist
	 */
	public MesPlaylist(){
		mesPlaylist = new ArrayList<>();
	}

	/**
	 * ajoute a la liste de playlist une playlist
	 * @param playlist : la playlist a ajouter a la liste de playlist
	 */
	public void ajouter(Playlist playlist) {
		mesPlaylist.add(playlist);
	}

	/**
	 * ajoute a la liste de playliste une liste de playlist
	 * @param listes : la liste de playlist a jouter a la liste de playlist
	 */
	public void ajouter(MesPlaylist listes) {
		for(Playlist p : listes.getMesPlaylist()) {
			this.ajouter(p);
		}
	}

	/**
	 * supprime de la liste de playlist une playlist grace a son indice
	 * @param indice : l'indice de la playlist a supprimer
	 */
	public void delete(int indice) {
		mesPlaylist.remove(indice);
	}
	

	/**
	 * recherche une playlist dans la liste de playlist par son nom
	 * @param nom : nom de la playlist a rechercher
	 * @return : la playlist rechercher
	 * @throws Exception
	 */
	public Playlist rechercher(String nom) throws Exception {
		Playlist plt = null;
		int i = -1;
		for(Playlist c : mesPlaylist) {
			i++;
			if(i < mesPlaylist.size()) {
				if(c.getNom().equalsIgnoreCase(nom)) {
					plt = c;
				}
			}
			else if(c.getNom().equalsIgnoreCase(nom)) {
				plt = c;
			}
			else {
				throw new Exception("la playliste rechercher n'existe pas dans la liste des playlist");
			}
		}
		return plt;
	}
	
	/**
	 * recherche une playlist dans la liste de playlist par son indice
	 * @param indice
	 * @return : la playlist rechercher
	 */
	public Playlist rechercher(int indice) {
		return mesPlaylist.get(indice);
	}

	/**
	 * @return : retourne le nombre de playlist present dans la liste de playliste
	 */
	public int all() {
		return mesPlaylist.size();
	}

	/**
	 * calcule la duree total des playlists dans la liste de playlist
	 * @return : retourne le resultat sur un format : j:h:min:s
	 */
	public String dureeCumule() {
		int sec = 0;
		int min = 0;
		int heure = 0;
		int jour = 0;
		//calcule la duree en sec
		for(Playlist c : mesPlaylist) {
			for(Chanson a : c.getPlaylist()){
				sec += a.getDuree();
			}
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
	 * enregistre la liste de playliste dans un fichier Gson
	 * @throws IOException
	 */
	public void ObjetTojson() throws IOException {
		String path = "./ressources/album_texte.Gson";
		// Créer une instance Gson
		Gson js = new GsonBuilder().setPrettyPrinting().create();
		try (Writer out = new FileWriter(path);) {
			// OBJET JAVA --> FICHIER Json
			js.toJson(this, out);
		}
	}

	/**
	 * recupere une liste de playlist depuis un fichier Gson
	 * @return : la liste de playlist contenue dans le fichier Gson
	 * @throws IOException
	 */
	public static MesPlaylist jsonToObjet() throws IOException {
		String path = "./ressources/album_texte.Gson";
		MesPlaylist b = null;
		// Créer une instance Gson
		Gson js = new Gson();
		try (Reader in = new FileReader(path);) {
			// FICHIER Json --> OBJET JAVA
			b = js.fromJson(in, MesPlaylist.class);
		}
		return b;
	}
	
	/**
	 * affiche les information relatif a la playlist :
	 */
	@Override
	public String toString() {
		String texte = "Mes Playlists : { ";
		for(Playlist p : mesPlaylist) {
			texte += p.getNom() + " - ";
		}
		return texte + " } Nombre de playlist : " + mesPlaylist.size() + "\nDuree : " + dureeCumule();
	}
	
	/**
	 * affiche les playlistes contenu dans la liste ainsi que leur chansons
	 * @return
	 */
	public String toString2() {
		String texte = "";
		for(Playlist p : mesPlaylist) {
			texte += p.toString() + "\n";
		}
		return texte;
	}
	
	/**
	 * @return the mesPlaylist
	 */
	public ArrayList<Playlist> getMesPlaylist() {
		return mesPlaylist;
	}

	/**
	 * @param mesPlaylist the mesPlaylist to set
	 */
	public void setMesPlaylist(ArrayList<Playlist> mesPlaylist) {
		mesPlaylist = mesPlaylist;
	}
}
