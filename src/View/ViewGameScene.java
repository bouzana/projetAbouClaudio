package View;

import Model.Asteroide;
import Model.GameScene;
import Model.projectile;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.stage.Screen;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static Tools.Music.Explosion;
import static Tools.Music.Piou;

public class ViewGameScene {

    private GameScene model;
    private Group root;
    private List<projectile> projectiles;
    private List<Asteroide> astreList;
    private Rectangle2D primaryScreenBounds;





    /**
     * Constructeur du menu principal
     *
     * @param model (ModÃ¨le correspondant au menu)
     * @param root  (Groupe principal de la vue)
     */
    ViewGameScene(GameScene model, Group root, String typeV) {
        this.root = root;
        this.model = model;
        projectiles = new ArrayList<>();
        astreList = new ArrayList<>();
         primaryScreenBounds = Screen.getPrimary().getBounds();

        this.model.initBackground(typeV);
        this.model.initSpaceShipSkills(typeV);
        this.model.getSpaceShip().initSpaceShip(typeV);//recupere v et le cockpit
        this.model.initScore();
    }

    public void majProjectiles() {
        //la fonction stream nous permet de filtrer la liste de nous astre par rapport a la collision
        projectiles = projectiles.stream()
                .filter(projectile -> !collision(projectile, astreList))
                .filter(projectile -> root.getChildren().contains(projectile.getShoot()))
                .collect(Collectors.toList());
    }


    public void shoot(int mark) {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getBounds();
        projectile projec = new projectile(mark, primaryScreenBounds.getWidth()/2,  primaryScreenBounds.getHeight()-220);
        projectiles.add(projec);
        root.getChildren().add(projec.getShoot());
        Piou();
    }

    public void creatAsteroide(int type){
        double positionX = 0;
        //je devise mon ecran par raport a la totalite /1.70 pour avoir plus au moins les astre centrer
        double maxY = primaryScreenBounds.getHeight()/1.70;
        double minY = 0;
        double range = maxY - minY;
        double positionY = (Math.random()*range)+minY;
        //set aleatoirement la direction
        char direction = Math.random()*10 + 1 > 5 ?'+':'-';
        // je dit si la direction et egal a mon caractere + elle commence a -20px de l'ecran
        if (direction=='+') {
            positionX = -20;

        }else {
            //sinon j prend la largeur de mon ecran et je rajoute +20px pour qu'il soit a l'exterieur de mon ecran
            positionX = primaryScreenBounds.getWidth() + 20;
        }
        //je crée ma variable vitesse en aleatoire pour chaque astre
       int vitesse = (int)( Math.random()*10+2);
       // System.out.println(" direction "+direction);
        //j'instencie mon astre
        Asteroide astre = new Asteroide(type,vitesse, direction ,positionX, positionY);
        //je crée ma liste dastre
        astreList.add(astre);
        root.getChildren().add(astre.getSprite());
    }


    void setVueGameScene() {
        //je crée ma vue
        root.getChildren().clear();
        root.getChildren().add(this.model.getImgBg());
        creatAsteroide(1);
        creatAsteroide(2);
        creatAsteroide(3);
        creatAsteroide(4);
        creatAsteroide(5);
        creatAsteroide(6);
        creatAsteroide(7);
        root.getChildren().add(this.model.getSpaceShip().getSprite());
        root.getChildren().add(GameScene.ScoreT);
        this.model.getSpaceShip().getSprite().setViewOrder(-30);
        GameScene.getScoreT().setViewOrder(-30);

    }

    private boolean collision(projectile projec , List<Asteroide> targetList) {
        if (projec.getY() < -10) {
            root.getChildren().remove(projec.getShoot());
           if(GameScene.score> 0) {
              GameScene.score  -= 10;
              GameScene.ScoreT.setText("Score "+GameScene.score);
               System.out.println(GameScene.ScoreT);

           }
            return true;
        }
        for (Asteroide target : targetList) {
            if (projec.getShoot().getX()  < target.getSprite().getX() + 60 && projec.getShoot().getX() > target.getSprite().getX() -60
                    && projec.getShoot().getY() < target.getSprite().getY() + 60 && projec.getShoot().getY()  > target.getSprite().getY() -60) {
                targetList.remove(target);
                Explosion();
                GameScene.score += 10;
                GameScene.ScoreT.setText("Score "+GameScene.score);
               // System.out.println("toucher");
                root.getChildren().removeAll(projec.getShoot(), target.getSprite());
                //une fois toucher en crée à nouveau l'asteroide
             creatAsteroide((int)(Math.random()*5+1));
//             this.model.getSpaceShip().getSprite().setViewOrder(-30);


                return true;
            }

        }
        return false;
    }


    public void setPosition(int i){
        //cette fonction nous permet de faire les asteroide qui viennent de gauche au de droite et changer de position
        for (int counter = 0; counter < astreList.size(); counter++) {
            if (astreList.get(counter).getDirection()=='+' && astreList.get(counter).getX()>primaryScreenBounds.getWidth()) {

                astreList.get(counter).setX(-20);

            } else if (astreList.get(counter).getDirection()=='-' && astreList.get(counter).getX()< 0) {

                astreList.get(counter).setX(primaryScreenBounds.getWidth()+20);

            } else if (astreList.get(counter).getDirection()=='+') {

                astreList.get(counter).setX(astreList.get(counter).getX()+astreList.get(counter).getSpeed());
            }
            else if (astreList.get(counter).getDirection()=='-') {
                astreList.get(counter).setX(astreList.get(counter).getX()-astreList.get(counter).getSpeed());
            }

        }


    }
    public void setPositionProjectile(){
        //permet de faire bouger mon projectiles et lui rajouter sa vitesse
        for (int counter = 0; counter < projectiles.size(); counter++) {
            projectiles.get(counter).setY(projectiles.get(counter).getY()-40);
            //collision(projectiles.get(counter),astreList);

        }
    }
}