package espol.proyectopoo2;

import Data.RoversData;
import Objetos.Rovers;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    
    private static List<Rovers> rovers;

    public static List<Rovers> getRovers() {
        return rovers;
    }

    @Override
    public void start(Stage stage){
        try{
            Parent root = loadFXML("VistaPrincipal");
            scene = new Scene(root, 1200, 720);
            stage.setScene(scene);
            stage.show();
        }catch(IOException ex){
            ex.printStackTrace();
        }
        
         rovers = RoversData.cargarRovers();
    }
    /**
     * Cambia el contenedor raiz de la escena por el pasado como parametro
     * @param root : contenedor raiz
     * @throws IOException 
     */
    public static void setRoot(Parent root) throws IOException {
        scene.setRoot(root);
    }

    public static Parent loadFXML(String fxmlfile) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxmlfile + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}