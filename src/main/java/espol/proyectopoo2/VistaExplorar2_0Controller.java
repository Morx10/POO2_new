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
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author marit
 */
public class VistaExplorar2_0Controller implements Initializable {
    
    private static Rovers rover;

    @FXML
    private Pane marteimage;
    @FXML
    private ComboBox<Rovers> cbrovers;
    @FXML
    private TextField comandos;
    @FXML
    private TextArea cajadeComandos;
    
    private static double DELTA_MOVIMIENTO=20;
    private static ImageView imgview;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            // TODO
        List<Rovers> rovers = RoversData.cargarRovers();
        cbrovers.getItems().addAll(rovers);
        
        List<Crater> crateres = CraterData.cargarCrateres();
        
        for(Crater i:crateres){
            Circle c= new Circle(i.isRadiocrater(),Color.TRANSPARENT);
            c.setStrokeWidth(2);
            c.setStroke(Color.BLUE);
            StackPane st= new StackPane();
            st.getChildren().addAll(c);
            marteimage.getChildren().addAll(st);
            
            st.setLayoutX(i.isLatitud());
            st.setLayoutY(i.isLongitud());
            
        }
        // TODO
    }    
    
    @FXML
    private void cargarRovers(ActionEvent event) throws IOException {
        rover=cbrovers.getValue();
        
        
        try{
                InputStream input = App.class.getResource("rover.png").openStream();
                Image img = new Image(input, 50,50,true,true);
                imgview = new ImageView(img);
            }catch(NullPointerException | IOException ex){
                //no hay la imagen buscada
                System.out.println("no esta la imagen");
                imgview = new ImageView();
            }
        if((marteimage.getChildren().get(marteimage.getChildren().size()-1)) instanceof ImageView){
        marteimage.getChildren().remove(marteimage.getChildren().size()-1);
    }       
        marteimage.getChildren().addAll(imgview);
            
        imgview.setLayoutX(rover.getUbicacionx());
        imgview.setLayoutY(rover.getUbicaciony());
    }

    @FXML
    private void comandosnow(KeyEvent event) {
        comandos.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
            if(event.getCode() == KeyCode.ENTER){
                String text= comandos.getText();
                comandos.clear();
                cajadeComandos.appendText(text);
                cajadeComandos.appendText("\n");
                
                
                //FUNCION CARGAR ROVER
                if(text.equals("cargar")){
                    
                    rover.cargar();
                    
                }
         
            }           
        }
        });
    }

    @FXML
    private void volerMenuPrincipal(ActionEvent event) throws IOException {
        Parent root = App.loadFXML("VistaPrincipal");
        App.setRoot(root);   
    }
    
    private static void moverobjeto(double ubicacionX, double ubicacionY){
        double destinoX= rover.getUbicacionx();
        double destinoY= rover.getUbicaciony();
        
        int banderax=0;
        int banderay=0;
        
        double nuevaubicacionx;
        double nuevaubicaciony;
        //ubicacion en X 

        
        while(ubicacionX<destinoX){
            nuevaubicacionx= ubicacionX+DELTA_MOVIMIENTO;
            imgview.setLayoutX(nuevaubicacionx);               
        }
        while(ubicacionX > destinoX){ 
            if(banderax==0){
                banderax=1;
                rover.girar(180);
            }
            nuevaubicacionx= ubicacionX-DELTA_MOVIMIENTO;
            imgview.setLayoutX(nuevaubicacionx);
            
        }
        
        if(banderax==1){
            rover.girar(180);
        }
        
        //ubicacion en Y
        
        while(ubicacionY<destinoY){
            
            if(banderay==0){
                rover.girar(90);
            }
            
            nuevaubicaciony= ubicacionX+DELTA_MOVIMIENTO;
            imgview.setLayoutY(nuevaubicaciony);               
        }
        while(ubicacionY > destinoY){
            if(banderay==0){
                rover.girar(-90);
            }
            rover.girar(180);
            nuevaubicaciony= ubicacionX-DELTA_MOVIMIENTO;
            imgview.setLayoutY(nuevaubicaciony);
        }
        
        
        
    }
   
    
}
