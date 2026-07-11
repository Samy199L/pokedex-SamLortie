package org.example.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import org.example.modele.Pokemon ;


public class PokedexFX {
    public final ListView<Pokemon> liste_pokemon;
    public final TextField champ_id;
    public final TextArea details;
    public final Button bouton;
    public final Button btnSupprimer ;
    public final Label message_erreur;
    public final ImageView image_poke;
    public final Label nom_pokemon;
    private final BorderPane racine;

    public PokedexFX(){
        liste_pokemon =new ListView<>();
        liste_pokemon.setPrefWidth(300);
        liste_pokemon.getStyleClass().add("liste-poke");

        image_poke = new ImageView();
        image_poke.setFitWidth(300);
        image_poke.setPreserveRatio(true);


        nom_pokemon = new Label("PokeDex");
        nom_pokemon.getStyleClass().add("nom-pokemon");
        HBox entete = new HBox(20, image_poke, nom_pokemon);
        entete.setAlignment(Pos.CENTER_LEFT);

        details = new TextArea();
        details.getStyleClass().add("zone-info");
        details.setEditable(false);
        details.setPromptText("Choisisez un Pokemon pour plus de details");
        details.setStyle("-fx-border-radius: 25");

        BorderPane zone_details = new BorderPane();
        zone_details.setTop(entete);
        zone_details.setCenter(details);
        zone_details.getStyleClass().add("zone-details");

        champ_id =new TextField();
        champ_id.setPromptText("ID Pokemon");

        bouton = new Button("Ajouter par id");
        btnSupprimer = new Button(" Supprimer ") ;
        btnSupprimer.setDisable(true) ;
        message_erreur = new Label();
        message_erreur.setStyle("-fx-text-fill: #ffffff");


        HBox info = new HBox(10,champ_id,bouton,btnSupprimer);
        VBox bas =new VBox(6,message_erreur,info);

        racine= new BorderPane();
        racine.getStyleClass().add("back");
        racine.setLeft(liste_pokemon);
        racine.setCenter(zone_details);
        racine.setBottom(bas);
        image_poke.fitWidthProperty().bind(racine.widthProperty().multiply(0.3));
        BorderPane.setMargin(bas,new Insets(8,0,18,0));
        racine.setPadding(new Insets(15));

    }

    public Parent getRoot(){
        return racine;
    }
}
