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
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    
    private static List<Rovers> rovers;
    
    /**
     * Devuelve la lista de rovers
     * @return rovers Lista de rovers
     */
    public static List<Rovers> getRovers() {
        return rovers;
    }

    /**
     * Inicia la App
     * @param stage
     */
    @Override
    public void start(Stage stage){
        try{
            Parent root = loadFXML("VistaPrincipal");
            scene = new Scene(root, 1200, 720);
            stage.setTitle("Exploración en Marte");
            Image icon = new Image(getClass().getResourceAsStream("icon.png"));
            stage.getIcons().add(icon);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }catch(IOException ex){
                //no hay la imagen buscada
                System.out.println("archivo no encontrado");               
        }
         rovers = RoversData.cargarRovers();
    }
    /**
     * Cambia el contenedor raiz de la escena por el pasado como parámetro
     * @param root contenedor raiz
     * @throws IOException
     */
    public static void setRoot(Parent root) throws IOException {
        scene.setRoot(root);
    }

    /**
     * Carga el archivo FXML a ser mostrado en la vista del programa
     * @param fxmlfile Archivo FXML
     * @return Contenedor raíz
     * @throws IOException
     */
    public static Parent loadFXML(String fxmlfile) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxmlfile + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Método main del programa
     * @param args
     */
    public static void main(String[] args) {
        launch();      
    }

}