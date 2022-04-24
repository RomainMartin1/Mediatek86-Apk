package com.example.mediatek86formations.controleur;

import android.content.Context;
import android.util.Log;
import com.example.mediatek86formations.modele.AccesDistant;
import com.example.mediatek86formations.modele.AccesLocal;
import com.example.mediatek86formations.modele.Formation;
import com.example.mediatek86formations.modele.FormationsFavorites;

import java.util.ArrayList;

public class Controle {

    private static Controle instance = null ;
    private ArrayList<Formation> lesFormations = new ArrayList<>();
    private ArrayList<Formation> lesFormationsCopie = new ArrayList<>();
    private Formation formation = null;
    private static AccesLocal accesLocal;
    private boolean favori; // si vrai : on affiche que le favoris, si faux : on affiche tout

    /**
     * constructeur privé
     */
    private Controle(){
        super();
    }

    /**
     * récupération de l'instance unique de Controle
     * @return instance
     */
    public static final Controle getInstance(Context context){
        if(Controle.instance == null){
            Controle.instance = new Controle();
            AccesDistant accesDistant = new AccesDistant();
            accesDistant.envoi("tous", null);
            accesLocal = new AccesLocal(context);
        }
        return Controle.instance;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public ArrayList<Formation> getLesFormations() {
        return lesFormations;
    }

    /**
     * retourne les formations dont le titre contient le filtre
     * @param filtre
     * @return
     */
    public ArrayList<Formation> getLesFormationFiltre(String filtre){
        ArrayList<Formation> lesFormationsFiltre = new ArrayList<>();
        for(Formation uneFormation : lesFormations){
            if(uneFormation.getTitle().toUpperCase().contains(filtre.toUpperCase())){
                lesFormationsFiltre.add(uneFormation);
            }
        }
        return lesFormationsFiltre;
    }
    /**
     * Setter de la liste de formations.
     * @param lesFormations
     */
    public void setLesFormations(ArrayList<Formation> lesFormations) {
        this.lesFormations = lesFormations;
    }

    public ArrayList<Formation> getLesFormationsCopie() {
        return lesFormationsCopie;
    }

    public void setLesFormationsCopie(ArrayList<Formation> lesFormationsFiltered) {
        this.lesFormationsCopie = lesFormationsFiltered;
    }

    /**
     * Getter de favori.
     * @return si true : l'utilisateur a cliqué sur mes formations, si false : il a cliqué sur favoris.
     */
    public boolean getFavori() {
        return favori;
    }

    /**
     * Setter de favori.
     * @param favori si true : l'utilisateur a cliqué sur mes formations, si false : il a cliqué sur favoris.
     */
    public void setFavori(boolean favori) {
        this.favori = favori;
    }

    /**
     * Vérifie si une formation fait partie des formations favorites.
     * @param formationId id de la formation à vérifier.
     * @return true si c'est une formation favorite, false sinon.
     */
    public boolean isFavori(Integer formationId) {
        return accesLocal.exists(formationId);
    }

    /**
     * Supprime un item de la liste de favoris à l'aide de son id.
     * @param formationId id de l'item à supprimer.
     */
    public void removeFavori(int formationId) {
        accesLocal.remove(formationId);
    }

    /**
     * Ajoute un favori à la table FormationsFavoris.
     * @param formation formation à ajouter.
     */
    public void addFavori(Formation formation) {
        accesLocal.add(formation);
    }

    /**
     * Récupère la liste de toutes les formations mise en favori.
     * @return la liste de ces formations.
     */
    public ArrayList<Formation> getFavoris() {
        ArrayList<Formation> lesFavoris = new ArrayList<>();
        ArrayList<Integer> lesIdsFavoris = accesLocal.getFavorisId();
        for(Formation formation : lesFormations){
            if(lesIdsFavoris.contains(formation.getId())){
                lesFavoris.add(formation);
            }
        }
        System.out.println("COUCOU" + lesFavoris.size());
        return lesFavoris;
    }
}
