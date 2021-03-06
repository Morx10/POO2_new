/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.proyectopoo2;

import Data.ExplorationData;
import Objetos.Exploration;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Grupo#1 Paralelo#3 POO
 */
public class VistaReporteController implements Initializable {

    @FXML
    private TextField fIniciotxt;
    @FXML
    private TextField ffintxt;
    @FXML
    private TextField mineralestxt;
    @FXML
    private TableView<Exploration> TablaRegistro;
    @FXML
    private TableColumn<Exploration,String> Fecha;
    @FXML
    private TableColumn<Exploration,String> Nombre;
    @FXML
    private TableColumn<Exploration,String> Minerales;
    
    private List<Exploration> datos;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fIniciotxt.setPromptText("yyyy-mm-dd");
        ffintxt.setPromptText("yyyy-mm-dd");
        mineralestxt.setPromptText("Mineral");
       
    } 
    /**
     * Método que permite al usuario regresar al Vista Principal
     * @param event MouseEvent evento del mouse que indica la acción a realizar
     */

    @FXML
    private void volverMenuPrincipal(MouseEvent event) throws IOException {
        Parent root = App.loadFXML("VistaPrincipal");
        App.setRoot(root);
    }
     /**
     * Método que permite al buscar el registro de la exploraciones
     * @param event MouseEvent evento del mouse que indica la acción a realizar
     */
    @FXML
    private void BuscarRegistro(MouseEvent event) {
        try{
        List<Exploration> exploraciones = ExplorationData.obtenerExploracion();
        String FechaI=fIniciotxt.getText();
        String FechaF=ffintxt.getText();
        String minerales=mineralestxt.getText();
        String primeraLetra = minerales.substring(0, 1).toUpperCase();
        String restoDeLaCadena = minerales.substring(1).toLowerCase();
        String m = primeraLetra + restoDeLaCadena;
        //LocalDate fechafin=ExplorationData.TransformarFecha(FechaF);
        //LocalDate fechaini=ExplorationData.TransformarFecha(FechaI);
        datos=ExplorationData.FiltradoFecha(FechaI,FechaF,exploraciones,m);
        System.out.println(datos);
        this.Fecha.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
        this.Nombre.setCellValueFactory(new PropertyValueFactory<>("NameCrater"));
        this.Minerales.setCellValueFactory(new PropertyValueFactory<>("Mineral"));
        this.TablaRegistro.setItems(FXCollections.observableList(datos));
        if(datos.isEmpty()){
             Alert a=new Alert(Alert.AlertType.INFORMATION);
             a.setContentText("No hay datos que mostrar");
             a.show();}
        }
        catch(NullPointerException |StringIndexOutOfBoundsException ex){
           Alert a = new Alert(Alert.AlertType.WARNING);
           a.setContentText("Por favor llenar todos los campos");
           a.show();
                }     
    }
}

    

