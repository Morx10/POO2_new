/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.proyectopoo2;

import Data.ExplorationData;
import Objetos.Exploration;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author sanch
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
    private TableColumn<Exploration, String> Nombre;
    @FXML
    private TableColumn<Exploration,String> Minerales;
    
    private ObservableList<Exploration> datos;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fIniciotxt.setPromptText("dd-mm-yy");
        ffintxt.setPromptText("dd-mm-yy");
        mineralestxt.setPromptText("Mineral");
       
    }    

    @FXML
    private void volverMenuPrincipal(MouseEvent event) throws IOException {
        Parent root = App.loadFXML("VistaPrincipal");
        App.setRoot(root);
    }

    @FXML
    private void BuscarRegistro(MouseEvent event) {
        List<Exploration> exploraciones = new ArrayList<Exploration>();
        exploraciones.add(new Exploration("12-02-2020", "Mead", "Aluminio,Magnesio,Sodio"));
        exploraciones.add(new Exploration("10-01-2022", "Lucerito", "Argón,Potasio,Sodio"));
        exploraciones.add(new Exploration("10-12-2021", "Astra", "Cobre,Potasio,Plata"));
        String FechaI=this.fIniciotxt.getText();
        String FechaF=this.ffintxt.getText();
        String minerales=this.mineralestxt.getText();
        datos=ExplorationData.FiltradoFecha(FechaI,FechaF,exploraciones,minerales);
        System.out.println(datos);
        this.Fecha.setCellValueFactory(new PropertyValueFactory<Exploration, String>("fecha"));
        this.Nombre.setCellValueFactory(new PropertyValueFactory<Exploration, String>("crater_name"));
        this.Minerales.setCellValueFactory(new PropertyValueFactory<Exploration, String>("mineral"));
        this.TablaRegistro.setItems(datos);
    }}
    

