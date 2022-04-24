package com.example.mediatek86formations.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.mediatek86formations.*;
import com.example.mediatek86formations.controleur.Controle;

/**
 * Vue lors du démarrage de l'application.
 * @author Romain
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    /**
     * Initialisations.
     */
    private void init(){
        Controle.getInstance(MainActivity.this);
        creerMenu();
    }

    /**
     * Appelle les procédures événementielles pour gérer le menu.
     */
    private void creerMenu(){
        ecouteMenu((ImageButton)findViewById(R.id.btnFormations), false);
        ecouteMenu((ImageButton)findViewById(R.id.btnFavoris), true);
    }

    /**
     * Procédure événementielle sur le clic d'une image du menu.
     * @param btn btnFormations ou btnFavoris.
     * @param isFavori si true : ouvre les favoris, si false : ouvre toute la liste de formations.
     */
    private void ecouteMenu(ImageButton btn, boolean isFavori){
        btn.setOnClickListener(view ->  {
            Controle.getInstance(MainActivity.this).setFavori(isFavori);
            Activity activity = MainActivity.this;
            Intent intent = new Intent(activity, FormationsActivity.class);
            activity.startActivity(intent);
        });
    }

}