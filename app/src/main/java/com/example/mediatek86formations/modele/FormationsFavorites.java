package com.example.mediatek86formations.modele;

import java.lang.Integer;

/**
 * Classe correspondant à la base de données locale FormationsFavorites.
 * @author Romain
 */
public class FormationsFavorites implements Comparable {
    /**
     * Entier. Indice de la formation.
     */
    private Integer id;

    /**
     * Constructeur de la classe FormationsFavorites.
     * @param id Entier. Indice de la Formation.
     */
    public FormationsFavorites(Integer id) {
        this.id = id;
    }

    /**
     * Retourne l'indice de la formation.
     * @return int. Indice de la formation.
     */
    public int getId() {
        return id;
    }

    @Override
    public int compareTo(Object o) {
        return id.compareTo(((FormationsFavorites)o).getId());
    }
}