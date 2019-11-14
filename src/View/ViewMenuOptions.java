package View;

import Controller.ControllerMenu;
import Model.Menu;
import Tools.Music;
import Tools.Path;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;

import static Tools.Music.Bip;
import static Tools.Music.Piou;


public class ViewMenuOptions {

    private Text titre;
    private Menu model;
    private ImageView imgBg;
    private ImageView  imgbtn;
    private MediaView bgVideo;
    private Group root;
    private Button btnRetour;
    private Slider musicSlider, effetsSlider;


    /**
     * Constructeur du menu principal
     *
     * @param model (Modèle correspondant au menu)
     * @param root  (Groupe principal de la vue)
     */
    ViewMenuOptions(Menu model, Group root) {
        this.root = root;
        this.model = model;
        initTitre();
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getBounds(); // RÃ©cupÃ©ration de la taille de l'Ã©cran
        double posX = (primaryScreenBounds.getWidth() * 11) / 100;
        double posY = (primaryScreenBounds.getHeight() * 28) /100;
        initButtons( posX, posY);

        btnRetour = initButton(posX-100, posY+80);
        btnRetour.setOnMouseEntered(mouseEvent -> Bip());
        initBackground();
        setVueCompleteOptions();
    }

    private void initButtons(double posX, double posY) {



        musicSlider = new Slider(0, 100, 100);
        musicSlider.setBlockIncrement(10);
        musicSlider.setShowTickLabels(true);
        musicSlider.valueProperty().addListener(
                (observable, oldValue, newValue) -> Music.setVolume(newValue.intValue() / 100.));
        musicSlider.setLayoutX(posX);
        musicSlider.setLayoutY(posY);

    }

    private void initTitre() {
        titre = new Text(15, 150, "Option");
        Font policeTitre = Font.loadFont(getClass().getResourceAsStream(Path.fontHeadCase), 40);
        titre.setFont(policeTitre);
        titre.setLayoutX(60);
        titre.setLayoutY(-20);
        titre.setFill(Color.WHITE);
    }

    /**
     * Mise en place de l'image de fond en fonction de la taille de l'écran de l'utilisateur
     */
    private void initBackground() {
        imgBg = new ImageView("Asset/Images/imgOption.jpg");
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getBounds(); // Récupération de la taille de l'écran
        imgBg.setFitHeight((int) primaryScreenBounds.getHeight());
        imgBg.setFitWidth((int) primaryScreenBounds.getWidth());
    }

    public Button initButton(double posX, double posY) {
        ImageView icon = new ImageView("Asset/Images/retourBtn.png");
        // Création d'un bouton
        Button b = new Button();
        b.setGraphic(icon);
        b.setLayoutX(posX);
        b.setLayoutY(posY);
        b.setBackground(null);

        //b.setText(texteB);

        return b;
    }

    /*private void btnBackground() {
        imgbtn= new ImageView("Asset/Images/btn1.png");

    }*/

    void setVueCompleteOptions() {
        root.getChildren().clear();
        root.getChildren().add(imgBg);
        root.getChildren().add(titre);
        root.getChildren().add(btnRetour);
        root.getChildren().add(musicSlider);
        /*root2.getChildren().add(imgbtn);*/
    }

    void setEvents(ControllerMenu mc) {
        btnRetour.setOnMouseClicked(mc);
        //titre.setOnMouseEntered(mc);
    }

    public Object getRetour() {
        return btnRetour;
    }
    /**
     * Ajoute a la vue tous les éléments composant le menu
     */
}