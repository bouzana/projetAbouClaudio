package Model;


import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class projectile {

    private static final String smallShoot = "Asset/Images/small_shoot.png";
    private static final String mediumShoot = "Asset/Images/medium_shoot.png";
    private static final String bigShoot = "Asset/Images/big_shoot.png";

    private final ImageView shoot;

    public projectile(int mark, double x, double y) {
        if (mark == 1) {
            shoot = new ImageView(smallShoot);
        } else if (mark == 2) {
            shoot = new ImageView(mediumShoot);
        } else {
            shoot = new ImageView(bigShoot);
/*            shoot.setPreserveRatio(true);
            shoot.setFitWidth(80);
            final KeyFrame tirStartY = new KeyFrame(Duration.ZERO, new KeyValue(shoot.yProperty(), 600));
            final KeyFrame tireEndY = new KeyFrame(Duration.seconds(1), new KeyValue(shoot.yProperty(), 100));


              final KeyFrame tirStartXajust = new KeyFrame(Duration.ZERO, new KeyValue(shoot.xProperty(), 100));
              final KeyFrame tirEndXajust = new KeyFrame(Duration.seconds(1), new KeyValue(shoot.xProperty(), 100));
              final KeyFrame tireDebutWidth = new KeyFrame(Duration.ZERO, new KeyValue(shoot.fitWidthProperty(), 50));
              final KeyFrame tireFinWidth = new KeyFrame(Duration.seconds(1), new KeyValue(shoot.fitWidthProperty(), 1));

            final Timeline timeline = new Timeline(tirStartY, tireDebutWidth, tireEndY, tireFinWidth );


            timeline.play();*/
        }
        shoot.setX(x);
        shoot.setY(y);
    }

    public ImageView getShoot() {
        return shoot;
    }


    public void moveOn() {
        shoot.setY(shoot.getY()-20);
    }


    public double getX() {
        return shoot.getX();
    }

    public double getY() {
        return shoot.getY();
    }
    public void setX(double pos) {
        shoot.setX(pos);
    }

    public void setY(double pos) {
        shoot.setY(pos);
    }


    @Override
    public String toString() {
        return shoot.getX() + " " + shoot.getY();
    }
}