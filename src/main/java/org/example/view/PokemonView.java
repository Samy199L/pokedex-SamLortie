package org.example.view;

import org.example.modele.Pokemon;

public class PokemonView {
    public void afiicherPokemon (Pokemon p){
        System.out.println("\nNom : " + p.nom);
        System.out.println("Espece : " + p.espece);
        System.out.println("Type : " + p.type_poke);
        System.out.println("Grandeur : " + p.grandeur);
        System.out.println("Poids : " + p.weight);
        System.out.println("Ordre : " + p.ordre);
        System.out.println("Experience : " + p.experience_base );
        System.out.println("Stats : " + p.stats);
    }
    public void afficheErreur(String msg){
        System.err.println(" ERREUR " + msg);
    }
}
