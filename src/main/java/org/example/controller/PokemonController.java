package org.example.controller;
import javafx.scene.image.Image;
import org.example.modele.Pokemon;
import org.example.modele.PokemonDAO;
import org.example.service.PokedexAPI;
import org.example.view.PokedexFX;
import org.example.view.PokemonView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import java.util.Optional;


public class PokemonController {
    private final PokedexAPI service = new PokedexAPI();
    private final PokemonDAO dao = new PokemonDAO();
    private final PokedexFX view;

    public PokemonController(PokedexFX view){
        this.view = view;

        view.bouton.setOnAction(e -> charger_depuis_api());
        view.liste_pokemon.getSelectionModel().selectedItemProperty()
                .addListener((obs,ancien,nouveau) -> {
                    afficherDetails(nouveau);
                    view.btnSupprimer.setDisable(nouveau == null);
                });
        view.btnSupprimer.setOnAction(e -> supprimer_selection());
        }

private void supprimer_selection(){
     Pokemon selection = view.liste_pokemon.getSelectionModel().getSelectedItem();
     if( selection == null )return;

     Alert alert = new Alert( AlertType.CONFIRMATION);
     alert.setTitle(" Confirmation ") ;
     alert.setHeaderText(" Supprimer ce pokemon ?") ;
     alert.setContentText(selection.nom + " (" + selection.id+ ")");

     Optional<ButtonType> reponse = alert.showAndWait() ;
     if (reponse.isPresent() && reponse.get() == ButtonType.OK) {
         try {
             dao.supprimer(selection.id);
             refreshListe();
             view.details.clear();
             view.image_poke.setImage(null);
             } catch(Exception ex) {
             view.message_erreur.setText(" Erreur : " + ex.getMessage()) ;
            }
         }
     }



    public void afficherDetails(Pokemon p) {

        if (p == null) {
            view.details.clear();
            view.image_poke.setImage(null);
            view.nom_pokemon.setText("");
            return;
        }
        view.nom_pokemon.setText(
                p.nom.toUpperCase());
        String info =
                "Nom : " + p.nom + "\n\nDetails :\n" +
                        "Espece : " + p.espece + "\n" +
                        "Type : " + p.type_poke + "\n" +
                        "Grandeur : " + p.grandeur + "\n" +
                        "Poids : " + p.weight + "\n" +
                        "Ordre : " + p.ordre + "\n" +
                        "Experience : " + p.experience_base + "\n" +
                        "\nStats : \n\n" + p.stats + "\n";
        view.details.setText(info);

        if (p.image != null) {
            Image img = new Image(p.image, true);
            view.image_poke.setImage(img);
        }
    }

    public void charger_depuis_api(){
        int id = Integer.parseInt(view.champ_id.getText());
        try{
            Pokemon p = service.recuperer(id);
            dao.sauvegarder(p);

            refreshListe();
        }catch (Exception e){
            view.message_erreur.setText("Pokemon Introuvable ou Erreur API : "+ e.getMessage());
        }
    }

    public void refreshListe() {
        try {
            view.liste_pokemon.getItems().setAll(dao.lister());
        } catch (Exception e) {
            view.message_erreur.setText("Erreur BDD : "+e.getMessage());
        }
    }


    public void demmarer(){
        refreshListe();
    }


}
