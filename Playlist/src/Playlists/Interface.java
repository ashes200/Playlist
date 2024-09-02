package Playlists;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JMenuBar;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.border.CompoundBorder;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.ListSelectionModel;
import java.awt.Dimension;
import javax.swing.JMenuItem;

public class Interface extends JFrame {

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnFichier;
	private JMenu mnGestion;
	private JPanel commandePanel;
	private JPanel comboPanel;
	private JComboBox cbListePlaylist;
	private JButton btnPlaylist;
	private JPanel listePanel;
	private JPanel listeArtistePanel;
	private JScrollPane scrollPane;
	private JList listeArtistes;
	private JPanel listeAlbumPanel;
	private JScrollPane scrollPane_1;
	private JList listeAlbums;
	private JPanel listeChansonPanel;
	private JScrollPane scrollPane_2;
	private JList listeChansons;
	private JPanel playlistPanel;
	private JButton btnAjouterPlaylist;
	private JButton btnSupprimerPlaylist;
	private JButton btnAjouterChanson;
	private JButton btnOterChanson;
	private JPanel playlistAffichagePanel;
	private JScrollPane scrollPane_3;
	private JTextArea textAreaPlaylist;
	private JPanel affichagePanel;
	private DefaultListModel<String> listModelArtiste = new DefaultListModel<>();
	private DefaultListModel<String> listModelAlbum = new DefaultListModel<>();
	private DefaultListModel<String> listModelChanson = new DefaultListModel<>();
	private DefaultComboBoxModel<String> comboModel1 = new DefaultComboBoxModel<>();
	
	Musitheque musitheque = new Musitheque();
	MesPlaylist listePlayListe = new MesPlaylist();
	Playlist playList1 = new Playlist("favoris");
	Playlist playList2 = new Playlist("les plus ecoutees");
	private JTextArea textArea2;
	private JPanel panel;
	private JTextField textPlaylist;
	private JMenuItem mnArtistes;
	private JMenuItem mnAlbums;
	private JMenuItem mnSauvegarde;
	private JMenuItem mnCharge;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface frame = new Interface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Interface() {
		initialize();
		// chargement des albums depuis le fichier texte
		musitheque.charger();
		
		//lien des liste et comboBox avec les modeles
		cbListePlaylist.setModel(comboModel1);
		listeArtistes.setModel(listModelArtiste);
		listeChansons.setModel(listModelChanson);
		listeAlbums.setModel(listModelAlbum);
		
		// initialisation de la liste des artistes
		for(String s : musitheque.listeArtiste()) {
			listModelArtiste.addElement(s);
		}
		
		// initialisation des playliste
		try {
			playList1.remplirHasard(musitheque);
			playList2.remplirHasard(musitheque);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
		// initialisation de la liste de playliste
		listePlayListe.ajouter(playList1);
		listePlayListe.ajouter(playList2);
		for(Playlist p : listePlayListe.getMesPlaylist()) {
			comboModel1.addElement(p.getNom());
		}
		
	}
	
	private void initialize() {
		setTitle("Musitheque");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1003, 775);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFichier = new JMenu("Fichier");
		mnFichier.setFont(new Font("Tahoma", Font.BOLD, 12));
		menuBar.add(mnFichier);
		
		mnSauvegarde = new JMenuItem("Sauvegarder Playlists");
		mnSauvegarde.addActionListener(new menuClass());
		mnSauvegarde.setFont(new Font("Tahoma", Font.BOLD, 12));
		mnFichier.add(mnSauvegarde);
		
		mnCharge = new JMenuItem("Ouvrir Playlist");
		mnCharge.addActionListener(new menuClass());
		mnCharge.setFont(new Font("Tahoma", Font.BOLD, 12));
		mnFichier.add(mnCharge);
		
		mnGestion = new JMenu("Gestion");
		mnGestion.setFont(new Font("Tahoma", Font.BOLD, 12));
		menuBar.add(mnGestion);
		
		mnAlbums = new JMenuItem("Tous les albums");
		mnAlbums.addActionListener(new menuClass());
		mnAlbums.setFont(new Font("Tahoma", Font.BOLD, 12));
		mnGestion.add(mnAlbums);
		
		mnArtistes = new JMenuItem("Tous les artistes");
		mnArtistes.addActionListener(new menuClass());
		mnArtistes.setFont(new Font("Tahoma", Font.BOLD, 12));
		mnGestion.add(mnArtistes);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		commandePanel = new JPanel();
		commandePanel.setMaximumSize(new Dimension(32767, 100));
		commandePanel.setMinimumSize(new Dimension(2, 2));
		commandePanel.setBorder(new TitledBorder(null, "Panneau de Commandes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(commandePanel);
		commandePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		comboPanel = new JPanel();
		comboPanel.setPreferredSize(new Dimension(350, 60));
		comboPanel.setMaximumSize(new Dimension(500, 60));
		comboPanel.setBorder(new TitledBorder(null, "Liste Playlist", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		commandePanel.add(comboPanel);
		comboPanel.setLayout(new BoxLayout(comboPanel, BoxLayout.X_AXIS));

		cbListePlaylist = new JComboBox(); 
		cbListePlaylist.addActionListener(new ComboBoxListener());
		cbListePlaylist.setPreferredSize(new Dimension(9, 21));
		comboPanel.add(cbListePlaylist);
		
		btnPlaylist = new JButton("Mes Playlists");
		btnPlaylist.addActionListener(new btnClass());
		btnPlaylist.setFont(new Font("Tahoma", Font.BOLD, 14));
		commandePanel.add(btnPlaylist);
		
		listePanel = new JPanel();
		listePanel.setBorder(null);
		contentPane.add(listePanel);
		listePanel.setLayout(new BoxLayout(listePanel, BoxLayout.X_AXIS));
		
		listeArtistePanel = new JPanel();
		listeArtistePanel.setBorder(new TitledBorder(null, "Listes Artistes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		listePanel.add(listeArtistePanel);
		listeArtistePanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		scrollPane = new JScrollPane();
		listeArtistePanel.add(scrollPane);
		
		listeArtistes = new JList();
		listeArtistes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listeArtistes.addMouseListener(new ListArtisteListener());
		scrollPane.setViewportView(listeArtistes);
		
		listeAlbumPanel = new JPanel();
		listeAlbumPanel.setBorder(new TitledBorder(null, "Listes Albums", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		listePanel.add(listeAlbumPanel);
		listeAlbumPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		scrollPane_1 = new JScrollPane();
		listeAlbumPanel.add(scrollPane_1);
		
		listeAlbums = new JList();
		listeAlbums.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listeAlbums.addListSelectionListener(new ListAlbumListener());
		scrollPane_1.setViewportView(listeAlbums);
		
		listeChansonPanel = new JPanel();
		listeChansonPanel.setBorder(new TitledBorder(null, "Chanson d'un albums", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		listePanel.add(listeChansonPanel);
		listeChansonPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		scrollPane_2 = new JScrollPane();
		listeChansonPanel.add(scrollPane_2);
		
		listeChansons = new JList();
		listeChansons.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listeChansons.addListSelectionListener(new ListChansonListener());
		scrollPane_2.setViewportView(listeChansons);
		
		playlistPanel = new JPanel();
		playlistPanel.setPreferredSize(new Dimension(10, 2));
		playlistPanel.setMinimumSize(new Dimension(10, 2));
		playlistPanel.setBorder(null);
		contentPane.add(playlistPanel);
		playlistPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(null, "Titre playlist", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		playlistPanel.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		textPlaylist = new JTextField();
		panel.add(textPlaylist);
		textPlaylist.setColumns(10);
		
		btnAjouterPlaylist = new JButton("Ajouter Playlist");
		btnAjouterPlaylist.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAjouterPlaylist.addActionListener(new btnClass());
		playlistPanel.add(btnAjouterPlaylist);
		
		btnSupprimerPlaylist = new JButton("Supprimer Playlist");
		btnSupprimerPlaylist.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSupprimerPlaylist.addActionListener(new btnClass());
		playlistPanel.add(btnSupprimerPlaylist);
		
		btnAjouterChanson = new JButton("Ajouter Chanson");
		btnAjouterChanson.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAjouterChanson.addActionListener(new btnClass());
		playlistPanel.add(btnAjouterChanson);
		
		btnOterChanson = new JButton("Oter Chanson");
		btnOterChanson.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnOterChanson.addActionListener(new btnClass());
		playlistPanel.add(btnOterChanson);
		
		playlistAffichagePanel = new JPanel();
		playlistAffichagePanel.setPreferredSize(new Dimension(10, 160));
		playlistAffichagePanel.setFont(new Font("Tahoma", Font.BOLD, 14));
		playlistAffichagePanel.setBorder(new TitledBorder(null, "Affichage Playlist", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(playlistAffichagePanel);
		playlistAffichagePanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		scrollPane_3 = new JScrollPane();
		playlistAffichagePanel.add(scrollPane_3);
		
		textAreaPlaylist = new JTextArea();
		scrollPane_3.setViewportView(textAreaPlaylist);
		
		affichagePanel = new JPanel();
		affichagePanel.setBackground(new Color(0, 255, 255));
		contentPane.add(affichagePanel);
		affichagePanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		textArea2 = new JTextArea();
		textArea2.setBackground(Color.CYAN);
		affichagePanel.add(textArea2);
	}
	
	/**
	 * class qui gere le comboBox.
	 * @author debra
	 *
	 */
	private class ComboBoxListener implements ActionListener {
		@Override
		/**
		 * affiche les chanson de la playlist selectionner dans le comboBox
		 */
		public void actionPerformed(ActionEvent e) {
			listModelChanson.removeAllElements();
			if(comboModel1.getSize() > 0) {
				String nomPlaylistCb = (String) cbListePlaylist.getSelectedItem();
				//change le titre du panel pour matcher avec les info afficher
				listeChansonPanel.setBorder(new TitledBorder(null, "Chanson de la playlist", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				
				Playlist plt = null;
				try {
					plt = listePlayListe.rechercher(nomPlaylistCb);
				} catch (Exception e1) {
					textArea2.setText("" + e1);
				}
				
				// initialisation de la liste des albums
				for(Chanson c : plt.getPlaylist()) {
					listModelChanson.addElement(c.getTitre());
				}
			}
		}
	}
	
	/**
	 * gere les boutton du menu
	 * @author debra
	 *
	 */
	private class menuClass implements ActionListener {
		@Override
		/**
		 * effectue different action en fontion du boutton menu selectionner
		 */
		public void actionPerformed(ActionEvent e) {
			//le boutton menu selectionner
			JMenuItem source = (JMenuItem ) e.getSource();
			
			/**
			 * les action concernant le boutton menu : liste des albums
			 */
			if(source == mnAlbums) {
				listModelChanson.removeAllElements();
				listModelAlbum.removeAllElements();
				
				// initialisation de la liste des albums
				for(Album a : musitheque.getListe()) {
					listModelAlbum.addElement(a.getNom());
				}
			}
			/**
			 * les action concernant le boutton menu : liste des artistes
			 */
			else if(source == mnArtistes) {
				listModelArtiste.removeAllElements();
				listModelChanson.removeAllElements();
				listModelAlbum.removeAllElements();
				
				// initialisation de la liste des artistes
				for(String s : musitheque.listeArtiste()) {
					listModelArtiste.addElement(s);
				}
			}
			/**
			 * les action concernant le boutton menu : sauvegarde
			 */
			else if(source == mnSauvegarde) {
				//sauvegarde les playlistes dans un fichier json
				try {
					listePlayListe.ObjetTojson();
				} catch (IOException e1) {
					textArea2.setText("" + e1);
				}
			}/**
			 * les action concernant le boutton menu : ouvrir playlist
			 */
			else {
				// vide la liste de playliste actuel avnt de la charger avec celui contenue dans le fichier json.
				try {
					comboModel1.removeAllElements();
					listePlayListe = MesPlaylist.jsonToObjet();
				} catch (IOException e1) {
					textArea2.setText("" + e1);
				}
				for(Playlist p : listePlayListe.getMesPlaylist()) {
					comboModel1.addElement(p.getNom());
				}
			}
		}
	}
	
	/**
	 * gere tous les boutton de l'interface
	 * @author debra
	 *
	 */
	private class btnClass implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			//le boutton selectionner
			JButton source = (JButton) e.getSource();
			
			/**
			 * ajoute une playliste a la liste de playliste
			 */
			if(source == btnAjouterPlaylist) {
				String nomPlaylist = textPlaylist.getText();
				Playlist play = new Playlist(nomPlaylist);
				listePlayListe.ajouter(play);
				comboModel1.addElement(play.getNom());
				textArea2.setText("La playlist : " + nomPlaylist + " a ete ajouter");
			}
			/**
			 * efface une playliste de la liste de playlist
			 */
			else if(source == btnSupprimerPlaylist) {
				int indicePlaylist =  cbListePlaylist.getSelectedIndex();
				String nomPlaylist =  (String) cbListePlaylist.getSelectedItem();
					textArea2.setText("La playlist : " + nomPlaylist + " a ete supprimmer");
					listePlayListe.delete(indicePlaylist);
					comboModel1.removeElementAt(indicePlaylist);
			}
			/**
			 * ajoute une chanson a la  playliste selectionner avec le comboBox
			 */
			else if(source == btnAjouterChanson) {
				int indiceChanson = listeChansons.getSelectedIndex();
				int indiceAlbum =  listeAlbums.getSelectedIndex();
				int indicePlaylist = cbListePlaylist.getSelectedIndex();
				String nomAlbum =  (String) listeAlbums.getSelectedValue();
				String nomChanson = (String) listeChansons.getSelectedValue();
				String nomPlaylist = (String) cbListePlaylist.getSelectedItem();
				textArea2.setText("La chanson : " + nomChanson + " a ete ajouter a la playlist : " + nomPlaylist);
				
				try {
					Chanson chs = musitheque.AlbumRechercher(nomAlbum).getAlbum().get(indiceChanson);
					Playlist playl = listePlayListe.rechercher(indiceAlbum);
					listePlayListe.rechercher(indicePlaylist).ajouter(chs);
				} catch (Exception e1) {
					textArea2.setText("" + e1);
				};
			}
			/**
			 * enleve une chanson de la  playliste selectionner avec le comboBox
			 */
			else if(source == btnOterChanson) {
				int indiceChanson = listeChansons.getSelectedIndex();
				int indiceAlbum =  listeAlbums.getSelectedIndex();
				int indicePlaylist = cbListePlaylist.getSelectedIndex();
				String nomAlbum =  (String) listeAlbums.getSelectedValue();
				String nomChanson = (String) listeChansons.getSelectedValue();
				String nomPlaylist = (String) cbListePlaylist.getSelectedItem();
				
				try {
					System.out.println(listePlayListe.rechercher(nomPlaylist).getNom());
					Playlist playl = listePlayListe.rechercher(nomPlaylist);
					playl.delete(indiceChanson);
					textArea2.setText("La chanson : " + nomChanson + " a ete enlever de la playlist : " + nomPlaylist);
					//cbListePlaylist.actionPerformed(e);
				} catch (Exception e1) {
					textArea2.setText("" + e1);
				};
			}
			/**
			 * affiche toutes les playlistes contenue dans la liste de playliste
			 */
			else{
				// affiche les information de la playliste : les noms de playliste, leur nombres, leur duree ...
				textArea2.setText(listePlayListe.toString());
				// affiche les differentes playlistes contenue dans la liste de playliste ainsi que leur contenue/chanson
				textAreaPlaylist.setText(listePlayListe.toString2());
			}
		}
	}
	
	/**
	 * gere la liste des artistes
	 * @author debra
	 *
	 */
	private class ListArtisteListener extends MouseAdapter {
		@Override
		/**
		 * Affiche la liste des album composer par l'artiste selectionner.
		 * affiche l'artiste ayant ecrit l'album selectionner
		 */
		
		public void mouseClicked(MouseEvent e) {
			listModelChanson.removeAllElements();
			listModelAlbum.removeAllElements();
			
			//affiche l'artiste ayant ecrit l'album selectionner
			String nomArtiste = (String) listeArtistes.getSelectedValue();
			
			// initialisation de la liste des albums
			for(Album a : musitheque.recherche(nomArtiste)) {
				listModelAlbum.addElement(a.getNom());
			}
		}
	}
	
	/**
	 * gere la liste des albums
	 * @author debra
	 *
	 */
	private class ListAlbumListener implements ListSelectionListener {
		@Override
		/**
		 * affiche la liste de chanson contenue dans l'album selectionner
		 */
		public void valueChanged(ListSelectionEvent e) {
			listModelChanson.removeAllElements();
			String nomAlbum = (String) listeAlbums.getSelectedValue();
			listeChansonPanel.setBorder(new TitledBorder(null, "Chanson d'un albums", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			listeArtistes.setSelectedIndex(musitheque.nomArtisteAlbum(nomAlbum));
			
			Album albm = null;
			try {
				albm = musitheque.AlbumRechercher(nomAlbum);
			} catch (Exception e1) {
				textArea2.setText("" + e1);
			}
				
			// initialisation de la liste des albums
			for(Chanson c : albm.getAlbum()) {
				listModelChanson.addElement(c.getTitre());
			}
		}
	}
	
	/**
	 * gere la liste de chanson
	 * @author debra
	 *
	 */
	private class ListChansonListener implements ListSelectionListener {
		@Override
		/**
		 * selectionne l'artiste ayant ecris la chanson selectionne (quand on affiche les chanson de la playlist : cette methode ne marche pas)
		 */
		public void valueChanged(ListSelectionEvent e) {
			//listModelChanson.removeAllElements();
			String nomChanson = (String) listeChansons.getSelectedValue();
			System.out.println(nomChanson);
			musitheque.nomArtisteChanson(nomChanson);
			//System.out.println(musitheque.nomArtisteChanson(nomChanson));
			//listeArtistes.setSelectedIndex(musitheque.nomArtisteChanson(nomChanson));
		}
	}
}
