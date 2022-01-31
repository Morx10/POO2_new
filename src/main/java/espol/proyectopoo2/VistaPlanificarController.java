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
import javafx.geometry.Insets;
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
 * Clase controladora FXML para la Vista Planificar
 *
 * @author Grupo#1 Paralelo#3 POO
 */
public class VistaPlanificarController implements Initializable {
    
    @FXML
    private VBox vboxRutas;
    @FXML
    private TextField crateresTxt;
    @FXML
    private ComboBox<Rovers> roverExploracion;
    @FXML
    private VBox vboxCrateres;
    
    private List<Crater> crateres = CraterData.cargarCrateres();
    /**
     * Inicializa la clase controladora y crea los distintos nodos dentro de la vista
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        crateresTxt.setPromptText("xxxx, yyyy, zzzz");
        vboxRutas.setVisible(false);
        List<Rovers> rovers = RoversData.cargarRovers();
        
        vboxCrateres.setSpacing(10);
        vboxCrateres.setPadding(new Insets(3));
        
        vboxCrateres.setAlignment(Pos.TOP_CENTER);       
        Label tituloCrateres = new Label("Cráteres disponibles");
        tituloCrateres.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        tituloCrateres.setAlignment(Pos.CENTER);
        GridPane paneCrateres = new GridPane();
        paneCrateres.setGridLinesVisible(true);
        int i=1;
        for(Crater c: crateres){
            Label crater = new Label(i+".- "+c.getNombrecrater());
            paneCrateres.add(crater,0,i);
            crater.setFont(Font.font("Verdana", FontWeight.NORMAL, 10));
            i++;
        }
        paneCrateres.setAlignment(Pos.CENTER);
        vboxCrateres.getChildren().addAll(tituloCrateres, paneCrateres);
        roverExploracion.getItems().addAll(rovers);
    }    
    
    /**
     * Retorna a la vista del Menu Principal
     * @param event Evento de tipo OnMouseClicked
     * @throws IOException Excepción si no se encuentra el archivo
     */
    @FXML
    private void volverMenuPrincipal(MouseEvent event) throws IOException {
        Parent root = App.loadFXML("VistaPrincipal");
        App.setRoot(root);
    }
    
    /**
     * Genera la lista de crateres por explorar en orden del mas cercano al mas lejano
     * @param event Evento de tipo OnKeyPressed
     */
    @FXML
    private void generarRuta(KeyEvent event) {
        KeyCode tecla = event.getCode();         
        vboxRutas.setSpacing(10);
        vboxRutas.setAlignment(Pos.TOP_CENTER);
        try{ 
            if(tecla == tecla.ENTER){
                vboxRutas.getChildren().clear();
                //Crear una lista con los cráteres que se quieren visitar
                ArrayList<Crater> crateresPorExplorar = new ArrayList<>();
                ArrayList<Crater> crateresRepetidos = new ArrayList<>();
                ArrayList<String> crateresFake = new ArrayList<>();                
                String cadena = crateresTxt.getText();
                String[] nombresCrateres = cadena.split(", ");
                for(String name: nombresCrateres){
                    for(Crater crater: crateres){
                        //Se verifica que el cráter estuvo escrito correctamente y que no esté repetido
                        if(name.toLowerCase().equals(crater.getNombrecrater().toLowerCase())){
                            if(!crateresPorExplorar.contains(crater))
                                crateresPorExplorar.add(crater);
                            else if(!crateresRepetidos.contains(crater))
                                crateresRepetidos.add(crater);
                        }
                    }   
                }  
                
                /*Se crea una lista de los nombres de los cráteres por explorar para verificar 
                los cráteres ingresados por el usuario que no han sido encontrados*/
                ArrayList<String> nombresCrateresPorExplorar = new ArrayList();
                for(Crater crater: crateresPorExplorar){
                    nombresCrateresPorExplorar.add(crater.getNombrecrater().toLowerCase());
                }
                for(String crater: nombresCrateres){
                    if(!nombresCrateresPorExplorar.contains(crater.toLowerCase()))
                        crateresFake.add(crater);
                }
                
                
                ArrayList<Crater> rutaCrateres = new ArrayList<>();
                int size = crateresPorExplorar.size();
                double roverX = roverExploracion.getValue().getUbicacionx();
                double roverY = roverExploracion.getValue().getUbicaciony();
                for(int i=0;i<size;i++){
                    Crater craterCercano = getCraterMasCercano(crateresPorExplorar,
                                        roverX, roverY);
                    rutaCrateres.add(craterCercano);
                    crateresPorExplorar.remove(craterCercano);
                    roverX = craterCercano.getLongitud();
                    roverY = craterCercano.getLatitud();
                    
                }
            
                //Se muestran en pantalla las listas finales
            
                //Rutas por explorar
                Label tituloRuta = new Label("Ruta de exploración");
                tituloRuta.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
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
                tituloRepetidos.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
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
                tituloFalsos.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
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
                    a.setContentText("Ingresar correctamente el nombre de los cráteres");
                    a.showAndWait();
                }
            }
        }catch(NullPointerException ex){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Seleccionar el tipo de rover");
            a.showAndWait();
        }
    }
    
    /**
     * Calcula la distancia entre un crater y un rover
     * @param x1 Posición en x del rover
     * @param y1 Posición en y del rover
     * @param crater Crater del que se conocerá su ubicacion
     * @return distancia Distancia entre el crater y un rover
     */
    public double calcularDistancia(double x1, double y1, Crater crater){
        double x2 = crater.getLongitud();
        double y2 = crater.getLatitud();
        double distancia = Math.pow(Math.pow(x2-x1,2) + Math.pow(y2-y1,2),0.5);
        return distancia;
    }
    
    /**
     * Extrae el crater mas cercano que hay en un grupo de crateres
     * @param crateres ArrayList de crateres por analizar
     * @param x Posicion x del rover
     * @param y Posicion y del rover
     * @return Crater mas cercano con respecto a la ubicacion del rover
     */
    public Crater getCraterMasCercano(ArrayList<Crater> crateres, double x, double y){
        ArrayList<Double> distancias = new ArrayList<>();
        for(Crater crater: crateres){
            distancias.add(calcularDistancia(x, y, crater));
        }
        return crateres.get(distancias.indexOf(Collections.min(distancias)));
    }   
}
