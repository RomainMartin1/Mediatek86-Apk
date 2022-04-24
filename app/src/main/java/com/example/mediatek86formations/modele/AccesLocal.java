package com.example.mediatek86formations.modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mediatek86formations.outils.MesOutils;
import com.example.mediatek86formations.outils.MySQLiteOpenHelper;
import com.example.mediatek86formations.modele.Formation;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Date;

public class AccesLocal {
    private String nomBase="bdFavoris.sqlite";
    private Integer versionBase=1;
    private MySQLiteOpenHelper accesBD;
    private SQLiteDatabase bd;

    public AccesLocal(Context context) {
        accesBD = new MySQLiteOpenHelper(context, nomBase, versionBase);
    }

    public boolean exists(Integer id) {
        bd = accesBD.getReadableDatabase();
        Cursor curseur = bd.query("FormationsFavorites", null, "id =?", new String[]{String.valueOf(id)}, null, null, null);
        boolean favori_existe = !curseur.isAfterLast();
        curseur.close();
        return favori_existe;
    }

    public void add(Formation formation) {
        bd = accesBD.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", formation.getId());
        bd.insert("FormationsFavorites", null, values);
        bd.close();
    }

    public void remove(Integer id) {
        bd = accesBD.getWritableDatabase();
        String req = "delete from FormationsFavorites where id=" + id;
        bd.execSQL(req);
        bd.close();
    }

    public ArrayList<Integer> getFavorisId() {
        bd = accesBD.getReadableDatabase();
        String req = "select * from FormationsFavorites";
        ArrayList<Integer> favoris = new ArrayList<Integer>();
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
