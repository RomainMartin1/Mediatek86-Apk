package com.example.mediatek86formations.modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mediatek86formations.outils.MySQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Accès à la base de données locale.
 * @author Romain
 */
public class AccesLocal {
    private String nomBase="bdFavoris.sqlite";
    private Integer versionBase=1;
    private MySQLiteOpenHelper accesBD;
    private SQLiteDatabase bd;

    /**
     * Constructeur de la classe AccesLocal.
     * @param context
     */
    public AccesLocal(Context context) {
        accesBD = new MySQLiteOpenHelper(context, nomBase, versionBase);
    }

    /**
     * Vérifie si une formation est une formation favorite.
     * @param id indice de la formation que l'on cherche.
     * @return true si la formation existe, false sinon.
     */
    public boolean exists(Integer id) {
        bd = accesBD.getReadableDatabase();
        Cursor curseur = bd.query("FormationsFavorites", null, "id =?", new String[]{String.valueOf(id)}, null, null, null);
        boolean favoriExiste = !curseur.isAfterLast();
        curseur.close();
        return favoriExiste;
    }

    /**
     * Ajoute une formation dans la base de données FormationsFavorites.
     * @param formation formation à ajouter à la base de données.
     */
    public void add(Formation formation) {
        bd = accesBD.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", formation.getId());
        bd.insert("FormationsFavorites", null, values);
        bd.close();
    }

    /**
     * Supprime une formation de la base de données FormationsFavorites.
     * @param id indice de la formation à supprimer.
     */
    public void remove(Integer id) {
        bd = accesBD.getWritableDatabase();
        String req = "delete from FormationsFavorites where id=" + id;
        bd.execSQL(req);
        bd.close();
    }

    /**
     * Récupère les indices des formations favorites.
     * @return les indices des formations favorites.
     */
    public List<Integer> getFavorisId() {
        bd = accesBD.getReadableDatabase();
        String req = "select * from FormationsFavorites";
        List<Integer> favoris = new ArrayList<>();
        Cursor curseur = bd.rawQuery(req, null);
        curseur.moveToFirst();

        while(!curseur.isAfterLast()){
            favoris.add(curseur.getInt(0));
            curseur.moveToNext();
        }

        curseur.close();
        bd.close();
        return favoris;
    }
}
