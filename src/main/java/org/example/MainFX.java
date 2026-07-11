package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import javafx.stage.Stage;

import org.example.controller.PokemonController;
import org.example.view.PokedexFX;


public class MainFX extends Application {
    @Override
    public void start(Stage stage){

        PokedexFX view = new PokedexFX();
        PokemonController ctrl = new PokemonController(view);

        ctrl.demmarer();

        Scene scene =new Scene(view.getRoot(),1750,900);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        stage.setTitle("Pokedex");

        Image appIcon = new Image(getClass().getResourceAsStream("/Poke_ball_icon.png"));
        stage.getIcons().add(appIcon);

        stage.setScene(scene);
        stage.show();

    }

public static void main(String[] args) {
    launch(args);


    }

}
