package com.example.mediatek86formations.controleur;

import android.util.Log;
import com.example.mediatek86formations.modele.AccesDistant;
import com.example.mediatek86formations.modele.Formation;

import java.util.ArrayList;

public class Controle {

    private static Controle instance = null ;
    private ArrayList<Formation> lesFormations = new ArrayList<>();
    private ArrayList<Formation> lesFormationsCopie = new ArrayList<>();
    private Formation formation = null;

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
    public static final Controle getInstance(){
        if(Controle.instance == null){
            Controle.instance = new Controle();
            AccesDistant accesDistant = new AccesDistant();
            accesDistant.envoi("tous", null);
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
}
