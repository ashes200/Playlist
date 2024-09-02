package Playlists;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Musitheque {

	//les attributs
	private ArrayList <Album> liste;

	/**
	 * constructeur sans parametre qui initialise la musitheque
	 */
	public Musitheque() {
		liste = new ArrayList<>();
	}

	/**
	 * charge la musitheque avec les albums contenue dans le fichier texte
	 */
	public void charger() {
		Album albm;
		Chanson csh;
		String path = "./ressources/album_texte.txt";
		String ligne = null, artiste = null, nom = null, titre = null;
		int duree = 0, annee = 0;
		try {
			// ouvrir en lecture
			Scanner scan = new Scanner(new File(path));
			while (scan.hasNext()) {
				// lire ligne par ligne
				ligne = scan.nextLine();
				//System.out.println(ligne);

				// extraire mot
				StringTokenizer str = new StringTokenizer(ligne, "-");
				artiste = str.nextToken().trim();
				nom = str.nextToken().trim();
				annee =  Integer.parseInt(str.nextToken().trim());

				//chansons
				titre = str.nextToken().trim();
				duree = Integer.parseInt(str.nextToken().trim());

				csh = new Chanson(titre, duree);
				albm = new Album(nom, annee, artiste);
				int index = -1;

				//verifie si l'album existe deja dans la musitheque
				for(int i = 0; i < liste.size(); i++) {
					if(liste.get(i).equals(albm)) {
						index = i;
					}
				}
				//si l'album existe deja, juste ajouter la hanson
				if(index != -1) {
					liste.get(index).ajouter(csh);
				}
				//sinon, ajouter le nouvel album
				else {
					albm.ajouter(csh);
					liste.add(albm);
				}
			}
		}
		catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * ajoute une liste d'album a la musitheque
	 * @param album : la liste d'album a ajouter dans la musitheque
	 */
	public void ajouter(ArrayList<Album> album) {
		for(Album a : album) {
			liste.add(a);
		}
	}

	/**
	 * supprime un album de la musitheque
	 * @param indice
	 */
	public void delete(int indice) {
		liste.remove(indice);
	}

	/**
	 * @return : retourne le nombre d'album contenue dans la musitheque
	 */
	public int nbTotalAlbum() {
		return liste.size();
	}

	/**
	 * @return : retourne le nombre de chanson d'un album de la musitheque
	 */
	public int nbChansonAlbum(int indice) {
		return liste.get(indice).nbTotalChanson();
	}
	
	/**
	 * recherche le nom de l'auteur d'un album grace au nom de son album
	 * @param nomAlbum
	 * @return : l'indice de l'artiste dans la methode getArtiste
	 */
	public int nomArtisteAlbum(String nomAlbum) {
		int i = -1;
		String nomArtiste = "";
		int indice = -1;
		for(Album a : liste) {
			
			if(a.getNom().equalsIgnoreCase(nomAlbum)) {
				nomArtiste = a.getArtiste();
			}
		}
		for(String s : this.listeArtiste()) {
			i++;
			if(s.equalsIgnoreCase(nomArtiste)) {
				indice = i;
			}
		}
		return indice;
	}
	
	/**
	 * recherche le nom de l'auteur d'un album grace au nom de sa chanson
	 * @param nomAlbum
	 * @return : l'indice de l'artiste dans la methode getArtiste
	 */
	public int nomArtisteChanson(String nomChanson) {
		String nomArtiste = "";
		int indice = -1;
		for(Album a : liste) {
			for(Chanson c : a.getAlbum()) {
				String st = c.getTitre();
				if(st.equalsIgnoreCase(nomChanson)) {
					nomArtiste = a.getArtiste();
				}
			}
		}
		System.out.println("le nom de l'artiste : " + nomArtiste);
		for(int i = 0; i < this.listeArtiste().size(); i++) {
			String nomArt = this.listeArtiste().get(2);
			if(nomArt.equalsIgnoreCase(nomArtiste)) {
				System.out.println("l'indice : " + i);
				indice = i;
			}
		}
		return indice;
	}
	
	/**
	 * recherche un album de la musitheque par son nom
	 * @param artiste
	 * @return
	 * @throws Exception 
	 */
	public Album AlbumRechercher(String album) throws Exception {
		Album alb = new Album();
		int  i = -1;
		for(Album a : this.getListe()) {
			i++;
			if(i < liste.size()) {
				if(a.getNom().equalsIgnoreCase(album)) {
					alb = a;
				}
			}
			else if(a.getNom().equalsIgnoreCase(album)) {
				alb = a;
			}
			else {
				throw new Exception("l'album n'existe pas");
			}
		}
		return alb;
	}
	
	/**
	 * recherche un ou plusieur album de la musitheque par son auteur/artiste
	 * @param artiste
	 * @return
	 */
	public ArrayList<Album> recherche(String artiste) {
		ArrayList<Album> alb = new ArrayList<>();
		for(Album a : liste) {
			if(a.getArtiste().equalsIgnoreCase(artiste)) {
				alb.add(a);
			}
		}
		return alb;
	}

	/**
	 * recherche un album de la musitheque par son indice dans la musitheque
	 * @param indice : indice de la musitheque rechercher dans la musitheque
	 * @return : la musitheque rechercher
	 */
	public Album recherche(int indice) {
		return liste.get(indice);
	}

	/**
	 * @return : la liste des artistes ayant un album dans la musitheque
	 */
	public ArrayList<String> listeArtiste() {
		ArrayList<String> artiste = new ArrayList<>();
		for(Album a : liste) {
			if(!artiste.contains(a.getArtiste())) {
				artiste.add(a.getArtiste());
			}
		}
		return artiste;
	}

	/**
	 * affiche le contenue de la musitheque:
	 * sa liste des albums
	 */
	@Override
	public String toString() {
		String musitheque = "";
		for(Album a : liste) {
			int nb = nbChansonAlbum(liste.indexOf(a));
			musitheque += a.toString();
		}
		return musitheque;
	}

	/**
	 * @return the liste
	 */
	public ArrayList<Album> getListe() {
		return liste;
	}

	/**
	 * @param liste the liste to set
	 */
	public void setListe(ArrayList<Album> liste) {
		this.liste = liste;
	}
	
	/**
	 * test de la class Musitheque
	 * @param args
	 */
	public static void main(String[] args) {
		Musitheque musi = new Musitheque();
		musi.charger();
		try {
			//System.out.println(musi.AlbumRechercher("pressure").toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		musi.nomArtisteChanson("fils de la joie");
		
	}
}
