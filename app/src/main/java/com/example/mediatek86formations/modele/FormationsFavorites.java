package com.example.mediatek86formations.modele;

import java.lang.Integer;

public class FormationsFavorites implements Comparable {

    private Integer id;

    public FormationsFavorites(Integer id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public int compareTo(Object o) {
        return id.compareTo(((FormationsFavorites)o).getId());
    }
}