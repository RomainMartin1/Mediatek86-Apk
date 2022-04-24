package com.example.mediatek86formations.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.mediatek86formations.*;
import com.example.mediatek86formations.controleur.Controle;
import com.example.mediatek86formations.modele.Formation;

/**
 * Vue correspondant à l'affichage d'une vidéo.
 */
public class VideoActivity extends AppCompatActivity {

    /**
     * Objet d'affichage de la vidéo.
     */
    WebView wbvYoutube;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        init();
    }

    /**
     * Affichage  de la vidéo.
     */
    private void init(){
        Controle controle = Controle.getInstance(VideoActivity.this);
        Formation formation = controle.getFormation();
        if(formation!=null) {
            wbvYoutube = findViewById(R.id.wbvYoutube);
            wbvYoutube.getSettings().setJavaScriptEnabled(true);
            wbvYoutube.setWebViewClient(new WebViewClient());
            wbvYoutube.loadUrl("https://www.youtube.com/embed/" + formation.getVideoId());
        }
    }

}