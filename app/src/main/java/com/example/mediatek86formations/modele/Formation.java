package com.example.mediatek86formations.modele;

import com.example.mediatek86formations.outils.MesOutils;

import java.util.Date;

/**
 * Classe représentant la base de données Formation.
 * @author Romain
 */
public class Formation implements Comparable<Formation> {
    /**
     * Entier. Indice de la Formation.
     */
    private int id;
    /**
     * Date. Date de publication de la Formation.
     */
    private Date publishedAt;
    /**
     * Chaîne de caractères. Titre de la Formation.
     */
    private String title;
    /**
     * Chaîne de caractères. Description de la Formation.
     */
    private String description;
    /**
     * Chaîne de caractères. Lien vers la miniature de la vidéo  de la Formation.
     */
    private String miniature;
    /**
     * Chaîne de caractères. Lien vers l'image de la Formation.
     */
    private String picture;
    /**
     * Chaîne de caractères. Indice de la vidéo.
     */
    private String videoId;

    /**
     * Constructeur de la classe Formation.
     * @param id int.
     * @param publishedAt Date.
     * @param title String.
     * @param description String.
     * @param miniature String.
     * @param picture String.
     * @param videoId String.
     */
    public Formation(int id, Date publishedAt, String title, String description, String miniature, String picture, String videoId) {
        this.id = id;
        this.publishedAt = publishedAt;
        this.title = title;
        this.description = description;
        this.miniature = miniature;
        this.picture = picture;
        this.videoId = videoId;
    }

    /**
     * Retourne l'indice d'une Formation.
     * @return un entier. L'indice d'une Formation.
     */
    public int getId() {
        return id;
    }

    /**
     * Retourne la date d'une Formation.
     * @return Date.
     */
    public Date getPublishedAt() {
        return publishedAt;
    }

    /**
     * Retourne la date en String au format jj/MM/yyyy.
     * @return la date en String au format jj/MM/yyyy.
     */
    public String getPublishedAtToString() {
        return MesOutils.convertDateToString(this.publishedAt);
    }

    /**
     * Retourne le titre d'une Formation.
     * @return String. le titre d'une Formation
     */
    public String getTitle() {
        return title;
    }

    /**
     * Retourne la description d'une Formation.
     * @return String. la description d'une Formation.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retourne le lien vers la miniature d'une Formation.
     * @return String. Le lien vers la miniature d'une Formation.
     */
    public String getMiniature() {
        return miniature;
    }

    /**
     * Retourne le lien vers l'image d'une Formation.
     * @return String. Le lien vers l'image d'une Formation.
     */
    public String getPicture() {
        return picture;
    }

    /**
     * Retourne l'indice de la vidéo d'une Formation.
     * @return String. L'indice de la vidéo d'une Formation.
     */
    public String getVideoId() {
        return videoId;
    }

    @Override
    public int compareTo(Formation formation) {
        return publishedAt.compareTo(formation.getPublishedAt());
    }
}
