package com.example.mediatek86formations.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mediatek86formations.*;
import com.example.mediatek86formations.controleur.Controle;
import com.example.mediatek86formations.modele.Formation;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Classe FormationsActivity. Vue correspondant à l'affichage des formations.
 * @author Romain
 */
public class FormationsActivity extends AppCompatActivity {
    /**
     * Contrôleur.
     */
    private Controle controle;
    /**
     * Bouton "filtrer".
     */
    private Button btnFiltrer;
    /**
     * Texte dans la zone de saisie du filtre.
     */
    private EditText txtFiltre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formations);
        init();
    }

    /**
     * Initialisations.
     */
    private void init(){
        controle = Controle.getInstance(FormationsActivity.this);
        btnFiltrer = (Button) findViewById(R.id.btnFiltrer);
        txtFiltre = (EditText) findViewById(R.id.txtFiltre);
        if(!controle.getFavori()) {
            controle.setLesFormations(controle.getLesFormationsCopie());
        } else {
            controle.setLesFormations(controle.getFavoris());
        }
        ecouteFiltrer();
        creerListe();
    }

    /**
     * Création de la liste adapter.
     */
    private void creerListe(){
        ArrayList<Formation> lesFormations = controle.getLesFormations();
        if(lesFormations != null){
            Collections.sort(lesFormations, Collections.<Formation>reverseOrder());
            ListView listView = (ListView)findViewById(R.id.lstFormations);
            FormationListAdapter adapter = new FormationListAdapter(lesFormations,FormationsActivity.this);
            listView.setAdapter(adapter);
        }
    }

    /**
     * En cas de clic sur le bouton "filtrer", vérifie si la zone de saisie est vide.
     * Si elle ne l'est pas actualise la liste des formations affichées.
     */
    private void ecouteFiltrer() {
        btnFiltrer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String txt = txtFiltre.getText().toString();
                if(txt.length() > 0) {
                    controle.setLesFormations(controle.getLesFormationFiltre(txt));
                } else {
                    if(!controle.getFavori()) {
                        controle.setLesFormations(controle.getLesFormationsCopie());
                    } else {
                        controle.setLesFormations(controle.getLesFormationsFavorites());
                    }
                }
                creerListe();
            }
        });
    }
}