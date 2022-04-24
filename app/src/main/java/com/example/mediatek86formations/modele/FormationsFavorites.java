package com.example.mediatek86formations.modele;

/**
 * Classe correspondant à la base de données locale FormationsFavorites.
 * @author Romain
 */
public class FormationsFavorites implements Comparable<FormationsFavorites> {
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
    public int compareTo(FormationsFavorites formationsFavorites) {
        return id.compareTo(formationsFavorites.getId());
    }
}