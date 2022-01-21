/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.proyectopoo2;

import Data.CraterData;
import Data.RoversData;
import Objetos.Crater;
import java.lang.Math;
import Objetos.Rovers;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * FXML Controller class
 *
 * @author sanch
 */
public class VistaPlanificarController implements Initializable {
    
    @FXML
    private VBox vboxRutas;
    @FXML
    private TextField crateresTxt;
    @FXML
    private ComboBox<Rovers> roverExploracion;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vboxRutas.setVisible(false);
        List<Rovers> rovers = RoversData.cargarRovers();
        roverExploracion.getItems().addAll(rovers);
    }    

    @FXML
    private void volverMenuPrincipal(MouseEvent event) throws IOException {
        Parent root = App.loadFXML("VistaPrincipal");
        App.setRoot(root);
    }
    
    @FXML
    private void generarRuta(KeyEvent event) {
        KeyCode tecla = event.getCode(); 
        vboxRutas.getChildren().clear();
        vboxRutas.setSpacing(10);
        if(tecla == tecla.ENTER){
            //Crear una lista con los cráteres que se quieren visitar
            List<Crater> crateres = CraterData.cargarCrateres();
            ArrayList<Crater> crateresPorExplorar = new ArrayList<>();
            String nombres = crateresTxt.getText();
            String[] nombresCrateres = nombres.split(",");
            for(String name: nombresCrateres){//falta manejar excepciones
                for(Crater crater: crateres){
                    //Se verifica que el cráter estuvo escrito correctamente y que no ha sido visitado
                    if(name.equals(crater.getNombrecrater())){//if(name.equals(crater.getNombrecrater()) && !(isExplored(crater)))
                        crateresPorExplorar.add(crater);
                    }   
                }
            }
            List<Crater> rutaCrateres = new ArrayList<>(crateresPorExplorar);
            System.out.println(rutaCrateres.size());
            for(int i=0;i<rutaCrateres.size();i++){
                Crater craterCercano = getCraterMasCercano(crateresPorExplorar,
                                        roverExploracion.getValue());
                rutaCrateres.set(i,craterCercano);
                crateresPorExplorar.remove(craterCercano);
                roverExploracion.getValue().setUbicacionx(craterCercano.getLatitud());
                roverExploracion.getValue().setUbicaciony(craterCercano.getLongitud());
                System.out.println(rutaCrateres.size());
            }
            
            //Se muestra en pantalla la lista final
            GridPane paneRutas = new GridPane();
            paneRutas.setGridLinesVisible(true);
            int i=0;
            for(Crater c: rutaCrateres){
                paneRutas.add(new Label((i+1)+".- "+c.getNombrecrater()),0,i);
                i++;
            }
            Label titulo = new Label("Ruta sugerida de exploración");
            titulo.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
            paneRutas.setHgap(10);
            paneRutas.setAlignment(Pos.CENTER);
            paneRutas.setMaxHeight(100);
            paneRutas.setMaxWidth(2000);
            vboxRutas.getChildren().addAll(titulo,paneRutas);
            vboxRutas.setVisible(true);
        }
    }
    
    public double calcularDistancia(Rovers rover, Crater crater){
        double x1 = rover.getUbicacionx();
        double y1 = rover.getUbicaciony();
        double x2 = crater.getLatitud();
        double y2 = crater.getLongitud();
        return Math.pow(Math.pow(x2-x1,2) + Math.pow(y2-y1,2),0.5);
    }
    
    public Crater getCraterMasCercano(ArrayList<Crater> crateres, Rovers rover){
        List<Double> distancias = new ArrayList<>();
        for(Crater crater: crateres){
            distancias.add(calcularDistancia(rover, crater));
        }
        return crateres.get(distancias.indexOf(Collections.max(distancias)));
    }

    
}
