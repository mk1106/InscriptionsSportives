package MenuInscriptions;

import java.util.ArrayList;
import java.util.Scanner;

import commandLineMenus.*;
import commandLineMenus.*;
import commandLineMenus.examples.employees.core.*;
import commandLineMenus.examples.employees.userDialog.PersonnelConsole;
import commandLineMenus.rendering.examples.util.InOut;
import inscriptions.Inscriptions;
import inscriptions.*;

import static commandLineMenus.rendering.examples.util.InOut.*;


public class MenuInscription {

	public void start()
	{
		menuPrincipal().start();
	}
	
	private Menu menuPrincipal()
	{
		Menu menu = new Menu("Menu inscriptions sportives");
//		menu.add(menuInscriptions());
		menu.add(menuCandidats());
		menu.add(menuCompetitions());
		menu.addQuit("q");
		return menu;
	}
	
//	private Menu menuInscriptions() {
//		Menu menu = new Menu("Inscriptions", "i");
//		menu.add(creerEquipe());
//		menu.add(creerPersonne());
//		menu.addBack("r");
//		return menu; 	
//	}
	
	private Menu menuCandidats() {
		Menu menu = new Menu("Candidat", "c");
		menu.add(menuEquipe());
		menu.add(menuPersonne());
		menu.addBack("r");
		return menu;
	}
	
	
	private Option listCompetitions() {
		return new Option("Liste des competitions", "l", () -> { System.out.println(Inscriptions.reinitialiser().getCompetitions());});
	}
	private Menu menuCompetitions() {
		Menu menu = new Menu("Competitions", "n");
		menu.add(listCompetitions());
		menu.add(selectionCompetitions());
		menu.addBack("r");
		return menu;
	}
	
	private List<Competition> selectionCompetitions()
	{
		return new List<Competition>("Selectionner une competition", "s",
				() -> new ArrayList<>(Inscriptions.reinitialiser().getCompetitions()),
				(element) -> menuSelectCompetition(element)
				);
	}
	

//	private Option inscriptionEquipe(Competition c) {
//		return new Option("Inscription equipe", "e", () -> { selectEquipInscript(c); 
//					;
//			} 
//		);
//	}
	
	private List<Equipe> inscriptionEquipe(Competition c)
	{
		return new List<Equipe>("Selectionner une equipe", "z",
				() -> new ArrayList<>(Inscriptions.reinitialiser().getEquipes()),
				(element) -> addEkip(c, element)
				);
	}

	private Option addEkip(Competition c, Equipe e) {
		return new Option("	Ajout de l'equipe" + e.getNom() + "dans la competition" + c.getNom() , "a", () ->   { c.add(e); System.out.println("l'equipe" + e.getNom() + "a bien été inscrite");} );
	}
	
//	private Option inscriptionPersonne(Competition c) {
//		return new Option ("Inscription personne", "p", () -> { selectPersonneInscript(c);
//		}
//	);
//	}
	
	private List<Personne> inscriptionPersonne(Competition c) {
		return new List<Personne>("Selectionner une personne", "p", () -> new ArrayList<>(Inscriptions.reinitialiser().getPersonnes()),
				(element) -> addPersonne(c, element)
				);
	}
	
				
	private Option addPersonne(Competition c, Personne p) {
		return new Option (" Ajout de " + p.getNom() + "dans la competition" + c.getNom(), "a", 
				() -> { c.add(p); System.out.println(p.getNom() + "a bien été inscrit dans la competition."); }); 
	}
	

	
	private Option supprimerCompetition(Competition c) {
		return new Option("Supprimer la competition", "d", () -> { c.delete(); System.out.println("La competition a été supprimer");}
		);
	}

	
	private Menu menuSelectCompetition(Competition c) {
		Menu menu = new Menu ("Competition: " + c.getNom());
		if (c.estEnEquipe()) {
			menu.add(inscriptionEquipe(c));
		}
		else {
			menu.add(inscriptionPersonne(c));
		}
		menu.add(supprimerCompetition(c));
		menu.addBack("r");
		return menu;
	}
	
	


	
	private Option creerEquipe() { 
		return new Option("Creer un candidat equipe", "e", () ->  { 
			System.out.println("Nom de l'equipe:");
			Scanner sc = new Scanner(System.in);
			String nom = sc.next();
			Inscriptions.reinitialiser().createEquipe(nom);
			}
		);
	}

	private Option creerPersonne() {
		
		return new Option("Creer un candidat personne", "p", () -> {
//			System.out.println("Nom");
			Scanner sc = new Scanner(System.in);
			String nom = sc.next();
			String prenom = sc.next();
			String mail = sc.next();
			Inscriptions.reinitialiser().createPersonne(nom, prenom, mail);
			}
		);
	}
	
	
	
	private Menu menuEquipe() {
		Menu menu = new Menu ("Equipe", "e");
		menu.add(creerEquipe());
		menu.add(ListEquipe());
		menu.add(selectionEquipe());
		menu.addBack("r");
		return menu;
	}
	
	private Option ListEquipe() {
		return new Option("Liste des equipes", "l", () -> { System.out.println(Inscriptions.reinitialiser().getEquipes());});
	}
	
	private Option ListPersonne() {
		return new Option("Liste des personnes", "l", () -> { System.out.println(Inscriptions.reinitialiser().getPersonnes());});
	}
	
	private List<Equipe> selectionEquipe()
	{
		return new List<Equipe>("Selectionner une equipe", "s",
				() -> new ArrayList<>(Inscriptions.reinitialiser().getEquipes()),
				(element) -> menuSelectEquipe(element)
				);
	}
	
	private Menu menuSelectEquipe(Equipe e) {
		Menu menu = new Menu ("Equipe: " + e.getNom());
		menu.add(listMembres(e));
		menu.add(supprimerEquipe(e));
		menu.addBack("r");
		return menu;
	}
	
	private Option supprimerEquipe(Equipe e) {
		return new Option ("Supprimer cette equipe", "s", () -> { e.delete();}); 
	}
	
	private Option listMembres(Equipe e) {
		return new Option ("Liste des membres", "m", () ->  { System.out.println(e.getMembres());});
	}
	
	private Menu menuPersonne() {
		Menu menu = new Menu ("Personne", "p");
		menu.add(creerPersonne());
		menu.add(ListPersonne());
		menu.add(selectionPersonne());
		menu.addBack("r");
		return menu;
	}
	
	private List<Personne> selectionPersonne()
	{
		return new List<Personne>("Selectionner une personne", "s",
				() -> new ArrayList<>(Inscriptions.reinitialiser().getPersonnes()),
				(element) -> menuSelectPersonne(element)
				);
	}
	
	private Menu menuSelectPersonne(Personne p) {
		Menu menu = new Menu (p.getPrenom() + " " + p.getNom());
		menu.add(modifNom(p));
		menu.add(modifPrenom(p));
		menu.add(modifMail(p));
		menu.add(supprimer(p));
		menu.addBack("r");
		return menu;
	}
	
	private Option modifNom(Personne p) {
		return new Option ("Modifier le nom", "n", () -> { String nvxNom = InOut.getString("Entrer le nouveau nom "); 
		p.setNom(nvxNom);
		}
		);
	}

	private Option modifPrenom(Personne p) {
		return new Option ("Modifier le prenom", "p", () -> { String nvxPrenom = InOut.getString("Entrer le nouveau prenom "); 
		p.setPrenom(nvxPrenom);
		}
		);
	}
	
	private Option modifMail(Personne p) {
		return new Option ("Modifier l'adresse mail", "m", () -> { String nvxMail = InOut.getString("Entrer le nouveau mail ");
		p.setMail(nvxMail);
		}
		);
	} 
	
	private Option supprimer(Personne p) {
		return new Option ("Supprimer cette personne", "s", () -> { InOut.getString(p.getPrenom() +" " + p.getNom() + " a bien été supprimer"); 
		p.delete();
		}
		);
	}
	
	
	
//	private Option selectPersonne() {
//		
//	}
//	
//	private Option selectEquipe() {
//		
//	}
//	
//	private Option selectCompetition() {
//		
//	}
//	
}
