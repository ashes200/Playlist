package Playlists;

import java.io.IOException;

public class Principale {

	public static void main(String[] args) {
		try {
			// test musitheque
			Musitheque musi = new Musitheque();
			musi.charger();
			System.out.println("la musitheque :\n" + musi.toString());

			System.out.println("l'album a la position 03 :\n" + musi.recherche(3));
			System.out.println("les albums de Stromae :\n" + musi.recherche("Stromea").toString());
			System.out.println("le nombre total d'album est : " + musi.nbTotalAlbum());
			System.out.println("la liste des artistes :\n" + musi.listeArtiste());

			//test playlist
			Playlist play = new Playlist("favoris");
			Playlist play2 = new Playlist("les plus ecoutee");
			try {
				play.remplirHasard(musi);
				play2.remplirHasard(musi);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};

			System.out.println("\n\nla playliste play :\n" + play.toString());
			System.out.println("\n\nla playliste play2 :\n" + play.toString());

			//test mesPlayList
			MesPlaylist all = new MesPlaylist();
			all.ajouter(play);
			all.ajouter(play2);
			System.out.println("la liste de playlist : all\n" + all.toString());

			MesPlaylist allToo = new MesPlaylist();

			//test sauvegarde et restauration Gson
			all.ObjetTojson();
			allToo.ajouter(MesPlaylist.jsonToObjet());;
			System.out.println("la liste de playlist : allToo\n" + allToo.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
