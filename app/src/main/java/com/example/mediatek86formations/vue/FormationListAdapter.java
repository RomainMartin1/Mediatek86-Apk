package com.example.mediatek86formations.vue;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.mediatek86formations.*;
import com.example.mediatek86formations.controleur.Controle;
import com.example.mediatek86formations.modele.Formation;

import java.util.List;

/**
 * Vue correspondant à l'affichage de la liste des Formations.
 */
public class FormationListAdapter extends BaseAdapter {
    /**
     * Tableau de Formation.
     */
    private List<Formation> lesFormations;
    private LayoutInflater inflater;
    /**
     * Contrôleur.
     */
    private Controle controle;
    /**
     * Contexte.
     */
    private Context context;

    /**
     * Constructeur de la classe FormationListAdapter.
     * @param lesFormations tableau de Formation.
     * @param context contexte.
     */
    public FormationListAdapter(List<Formation> lesFormations, Context context) {
        this.lesFormations = lesFormations;
        this.controle = Controle.getInstance(context);
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    /**
     * Calcule la taille du tableau de formations.
     * @return le nombre de formations.
     */
    @Override
    public int getCount() {
        return lesFormations.size();
    }

    /**
     * Récupère une Formation se trouvant dans le tableau de Formations à l'indice i passé en
     * paramètre.
     * @param i position de l'item.
     * @return valeur à cette position.
     */
    @Override
    public Object getItem(int i) {
        return lesFormations.get(i);
    }

    /**
     * Récupère un indice passé en paramètre.
     * @param i position de l'item
     * @return id à cette position
     */
    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * Construction de la ligne.
     * @param i int.
     * @param view View.
     * @param viewGroup ViewGroup.
     * @return la vue.
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewProperties viewProperties;
        if(view == null){
            viewProperties = new ViewProperties();
            view = inflater.inflate(R.layout.layout_liste_formations, null);
            viewProperties.txtListeTitle = (TextView)view.findViewById(R.id.txtListTitle);
            viewProperties.txtListPublishedAt = (TextView)view.findViewById(R.id.txtListPublishedAt);
            viewProperties.btnListFavori = (ImageButton)view.findViewById(R.id.btnListFavori);
            view.setTag(viewProperties) ;
        }else{
            viewProperties = (ViewProperties)view.getTag();
        }

        //Affichage du coeur en fonction de s'il est un favori ou non
        if (controle.isFavori(lesFormations.get(i).getId())) {
            viewProperties.btnListFavori.setImageResource(R.drawable.coeur_rouge);
        } else {
            viewProperties.btnListFavori.setImageResource(R.drawable.coeur_gris);
        }

        //Gestion du clic sur le bouton favori
        if(!controle.getFavori()) {
            viewProperties.btnListFavori.setTag(i);
            viewProperties.btnListFavori.setOnClickListener(view1 ->  {
                int position = (int) view1.getTag();
                if (controle.isFavori(lesFormations.get(position).getId())) {
                    viewProperties.btnListFavori.setImageResource(R.drawable.coeur_gris);
                    controle.removeFavori(lesFormations.get(position).getId());
                    notifyDataSetChanged();
                } else {
                    viewProperties.btnListFavori.setImageResource(R.drawable.coeur_rouge);
                    controle.addFavori(lesFormations.get(position));
                    notifyDataSetChanged();
                }
            });
        } else {
            viewProperties.btnListFavori.setTag(i);
            viewProperties.btnListFavori.setOnClickListener(view1 ->  {
                int position = (int) view1.getTag();
                controle.removeFavori(lesFormations.get(position).getId());
                lesFormations.remove(lesFormations.get(position));
                controle.setLesFormationsFavorites(controle.getFavoris());
                notifyDataSetChanged();
            });
        }

        viewProperties.txtListeTitle.setText(lesFormations.get(i).getTitle());
        viewProperties.txtListPublishedAt.setText(lesFormations.get(i).getPublishedAtToString());
        viewProperties.txtListeTitle.setTag(i);
        viewProperties.txtListeTitle.setOnClickListener(this::ouvrirUneFormationActivity);
        viewProperties.txtListPublishedAt.setTag(i);
        viewProperties.txtListPublishedAt.setOnClickListener(this::ouvrirUneFormationActivity);
        return view;
    }

    /**
     * Ouvre la page du détail de la formation.
     * @param v View.
     */
    private void ouvrirUneFormationActivity(View v){
        int indice = (int)v.getTag();
        controle.setFormation(lesFormations.get(indice));
        Intent intent = new Intent(context, UneFormationActivity.class);
        context.startActivity(intent);
    }

    /**
     * Propriétés de la ligne.
     */
    private class ViewProperties{
        ImageButton btnListFavori;
        TextView txtListPublishedAt;
        TextView txtListeTitle;
    }

}
