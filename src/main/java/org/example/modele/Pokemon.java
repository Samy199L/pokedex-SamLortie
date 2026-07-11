package org.example.modele;

public class Pokemon {


    public int experience_base;
    public String crie;
    public int grandeur;
    public int id;
    public Boolean is_default;
    public String nom;
    public int ordre;
    public String espece;
    public String image;
    public String stats;
    public String type_poke;
    public int weight;

    public Pokemon(){}

    @Override
    public String toString() {
        return nom.toUpperCase();
    }
}
