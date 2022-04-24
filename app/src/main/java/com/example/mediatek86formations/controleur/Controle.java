package com.example.mediatek86formations.controleur;

import android.content.Context;
import com.example.mediatek86formations.modele.AccesDistant;
import com.example.mediatek86formations.modele.AccesLocal;
import com.example.mediatek86formations.modele.Formation;

import java.util.ArrayList;

/**
 * Classe Controle, représentant le contrôleur du modèle MVC.
 * @author Romain
 */
public class Controle {

    private static Controle instance = null ;
    /**
     * Tableau de Formation lesFormations. Evolue en fonction des filtres dessus.
     */
    private ArrayList<Formation> lesFormations = new ArrayList<>();
    /**
     * Tableau de Formation lesFormationsCopie. Permet de garder en permanence une copie consultable
     * de toutes les formations, sans repasser par la base de donnée à chaque fois.
     */
    private ArrayList<Formation> lesFormationsCopie = new ArrayList<>();
    /**
     * Tableau de Formation lesFormationsFavorites. Permet de garder en permanence une copie
     * consultable de toutes les formations favorites, sans repasser par la base de donnée à
     * chaque fois.
     */
    private ArrayList<Formation> lesFormationsFavorites = new ArrayList<>();
    /**
     * Une Formation.
     */
    private Formation formation = null;
    /**
     * Accès à la base de donnée locale.
     */
    private static AccesLocal accesLocal;
    /**
     * Booléen. Si vrai : on affiche uniquement les favoris, si faux : on affiche toutes les
     * foramtion.
     */
    private boolean favori;

    /**
     * Constructeur privé.
     */
    private Controle(){
        super();
    }

    /**
     * Récupération de l'instance unique de Controle.
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

    /**
     * Getter de formation.
     * @return une formation.
     */
    public Formation getFormation() {
        return formation;
    }

    /**
     * Setter de formation.
     * @param formation une formation.
     */
    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    /**
     * Getter du tableau de Formation.
     * @return le tableau de Formation.
     */
    public ArrayList<Formation> getLesFormations() {
        return lesFormations;
    }

    /**
     * Retourne les formations dont le titre contient le filtre.
     * @param filtre String.
     * @return un tableau contenant les formations filtrées.
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

    /**
     * Getter du tableau lesFormationsCopies. Ce tableau permet de garder une sauvegarde de toutes
     * les formations pour réinitialiser le tableau de lesFormations, sans à chaque fois appeler la
     * base de donnée.
     * @return le tableau  de Formation lesFormationsCopie.
     */
    public ArrayList<Formation> getLesFormationsCopie() {
        return lesFormationsCopie;
    }

    /**
     * Setter de lesFormationsCopie.
     * @param lesFormationsCopie le tableau  de Formation à affecter à lesFormationsCopie.
     */
    public void setLesFormationsCopie(ArrayList<Formation> lesFormationsCopie) {
        this.lesFormationsCopie = lesFormationsCopie;
    }

    /**
     * Getter du tableau lesFormationsFavorites. Ce tableau permet de garder une sauvegarde de toutes
     * les formations favorites pour réinitialiser le tableau de lesFormations, sans à chaque fois
     * appeler la base de donnée.
     * @return le tableau de Formation lesFormationsFavorites.
     */
    public ArrayList<Formation> getLesFormationsFavorites() {
        return lesFormationsFavorites;
    }

    /**
     * Setter de lesFormationsFavorites.
     * @param lesFormationsFavorites le tableau  de Formation à affecter à lesFormationsCopie.
     */
    public void setLesFormationsFavorites(ArrayList<Formation> lesFormationsFavorites) {
        this.lesFormationsFavorites = lesFormationsFavorites;
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
        for(Formation formations : lesFormations){
            if(lesIdsFavoris.contains(formations.getId())){
                lesFavoris.add(formations);
            }
        }
        return lesFavoris;
    }
}
