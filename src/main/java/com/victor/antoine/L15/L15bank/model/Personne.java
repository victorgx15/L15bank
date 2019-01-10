package com.victor.antoine.L15.L15bank.model;

public class Personne {
	static private int universalId = 1;
	private int id;
	private String nom, prenom, email;
	
	public Personne(String nom, String prenom, String email) {
		this.id = universalId++;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	}

	@Override
	public String toString() {
		return nom + " " + prenom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
