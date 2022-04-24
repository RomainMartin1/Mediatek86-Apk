package com.example.mediatek86formations.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.mediatek86formations.R;
import com.example.mediatek86formations.controleur.Controle;

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
        ecouteMenu((ImageButton)findViewById(R.id.btnFormations), FormationsActivity.class, false);
        ecouteMenu((ImageButton)findViewById(R.id.btnFavoris), FormationsActivity.class, true);
    }

    /**
     * Procédure événementielle sur le clic d'une image du menu.
     * @param btn
     * @param classe
     * @param isFavori si true : ouvre les favoris, si false : ouvre toute la liste de formations.
     */
    private void ecouteMenu(ImageButton btn, final Class classe, boolean isFavori){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controle.getInstance(MainActivity.this).setFavori(isFavori);
                Activity activity = MainActivity.this;
                Intent intent = new Intent(activity, classe);
                activity.startActivity(intent);
            }
        });
    }

}