/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.proyectopoo2;

import Data.CraterData;
import Data.RoversData;
import Objetos.Crater;
import Objetos.Rovers;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
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
        vboxRutas.setSpacing(10);
        vboxRutas.setAlignment(Pos.TOP_CENTER);
        try{ 
            if(tecla == tecla.ENTER){
                vboxRutas.getChildren().clear();
                //Crear una lista con los cráteres que se quieren visitar
                List<Crater> crateres = CraterData.cargarCrateres();
                ArrayList<Crater> crateresPorExplorar = new ArrayList<>();
                ArrayList<Crater> crateresRepetidos = new ArrayList<>();
                ArrayList<String> crateresFake = new ArrayList<>();                
                String cadena = crateresTxt.getText();
                String[] nombresCrateres = cadena.split(", ");
                for(String name: nombresCrateres){
                    for(Crater crater: crateres){
                        //Se verifica que el cráter estuvo escrito correctamente y que no ha sido sensado
                        if(name.toLowerCase().equals(crater.getNombrecrater().toLowerCase())){
                            if(!crateresPorExplorar.contains(crater))
                                crateresPorExplorar.add(crater);
                            else
                                crateresRepetidos.add(crater);
                        }
                    }   
                }               

                /*for(Crater crater: crateresPorExplorar){
                    if(!nombresCrateres.contains(crater.getNombrecrater()))
                        crateresFake.add(crater.getNombrecrater());
                }*/
                
                
                List<Crater> rutaCrateres = new ArrayList<>(crateresPorExplorar);
                for(int i=0;i<rutaCrateres.size();i++){
                    Crater craterCercano = getCraterMasCercano(crateresPorExplorar,
                                        roverExploracion.getValue());
                    rutaCrateres.set(i,craterCercano);
                    crateresPorExplorar.remove(craterCercano);
                    roverExploracion.getValue().setUbicacionx(craterCercano.getLatitud());
                    roverExploracion.getValue().setUbicaciony(craterCercano.getLongitud());
                }
            
                //Se muestran en pantalla las listas finales
            
                //Rutas por explorar
                Label tituloRuta = new Label("Ruta sugerida de exploración");
                tituloRuta.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
                tituloRuta.setAlignment(Pos.CENTER);
                GridPane paneRutas = new GridPane();
                paneRutas.setGridLinesVisible(true);
                int i=1;
                for(Crater c: rutaCrateres){
                    paneRutas.add(new Label(i+".- "+c.getNombrecrater()),0,i);
                    i++;
                }
                paneRutas.setHgap(10);
                paneRutas.setAlignment(Pos.CENTER);

                //Crateres repetidos
                Label tituloRepetidos = new Label("Cráteres repetidos");
                tituloRepetidos.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
                tituloRepetidos.setAlignment(Pos.CENTER);
                GridPane paneRepetidos = new GridPane();
                paneRepetidos.setGridLinesVisible(true);
                if(crateresRepetidos.size()>0){
                    tituloRepetidos.setVisible(true);
                    paneRepetidos.setVisible(true);
                }                  
                else{
                    tituloRepetidos.setVisible(false);
                    paneRepetidos.setVisible(false);
                }                   
                int j=1;
                for(Crater c: crateresRepetidos){
                    paneRepetidos.add(new Label(j+".- "+c.getNombrecrater()),0,j);
                    j++;
                }
                paneRepetidos.setHgap(10);
                paneRepetidos.setAlignment(Pos.CENTER);

                //Cráteres no encontrados
                Label tituloFalsos = new Label("Cráteres no encontrados");
                tituloFalsos.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
                tituloFalsos.setAlignment(Pos.CENTER);
                GridPane paneFalsos = new GridPane();
                paneFalsos.setGridLinesVisible(true);
                if(crateresFake.size()>0){
                    tituloFalsos.setVisible(true);
                    paneFalsos.setVisible(true);
                }                  
                else{
                    tituloFalsos.setVisible(false);
                    paneFalsos.setVisible(false);
                } 
                int k=1;
                for(String c: crateresFake){
                    paneFalsos.add(new Label(k+".- "+c),0,k);
                    k++;
                }
                paneFalsos.setHgap(10);
                paneFalsos.setAlignment(Pos.CENTER);
                
               
                vboxRutas.getChildren().addAll(tituloRuta,paneRutas,tituloRepetidos,
                        paneRepetidos,tituloFalsos,paneFalsos);
                vboxRutas.setVisible(true);
                
                if(rutaCrateres.isEmpty()){
                    vboxRutas.setVisible(false);
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setContentText("Escribir el nombre de los cráteres correctamente");
                    a.showAndWait();
                }
            }
        }catch(NullPointerException ex){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Seleccionar el tipo de rover");
            a.showAndWait();
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
