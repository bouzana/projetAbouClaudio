package Controller;

import Model.GameScene;
import Model.Menu;
import Timeline.GameTL;
import View.ViewHandler;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class ControllerGameScene implements EventHandler<KeyEvent> {

    private ViewHandler launcher;
    private GameScene model;
    private GameTL gameTimeLine;
    private BooleanProperty EscapeKeyTyped, rightKeyTyped, spaceBarKeyTyped, LeftKeyTyped;

    public ControllerGameScene(ViewHandler launcher, GameScene model) {
        EscapeKeyTyped = new SimpleBooleanProperty(false);
        rightKeyTyped = new SimpleBooleanProperty(false);
        LeftKeyTyped = new SimpleBooleanProperty(false);
        spaceBarKeyTyped = new SimpleBooleanProperty(false);
        this.launcher = launcher;
        this.model = model;
        gameTimeLine = new GameTL(this);


    }

    public void GameTLStart() {
        gameTimeLine.start();
    }

    public void GameTLStoppe() {
        gameTimeLine.stop();
    }


    @Override
    public void handle(KeyEvent event) {

        if (event.getEventType() == KeyEvent.KEY_PRESSED) {

            if (event.getCode() == KeyCode.ESCAPE) {
                EscapeKeyTyped.set(true);
            } else if (event.getCode() == KeyCode.RIGHT) {
                rightKeyTyped.set(true);
            }
            else if (event.getCode() == KeyCode.LEFT) {
                LeftKeyTyped.set(true);
            }
            else if (event.getCode() == KeyCode.SPACE) {

                spaceBarKeyTyped.set(true);
            }
        }else if (event.getEventType() == KeyEvent.KEY_RELEASED) {
            if (event.getCode() == KeyCode.ESCAPE) {
                EscapeKeyTyped.set(false);
            } else if (event.getCode() == KeyCode.RIGHT) {
                rightKeyTyped.set(false);
            }else if (event.getCode() == KeyCode.LEFT) {
                LeftKeyTyped.set(false);
            } else if (event.getCode() == KeyCode.SPACE) {
                spaceBarKeyTyped.set(false);
            }
        }

    }

    public synchronized void majProjectiles() {
        this.launcher.getVgs().majProjectiles();

    }


    public ViewHandler getLauncher() {
        return launcher;
    }

    public void setLauncher(ViewHandler launcher) {
        this.launcher = launcher;
    }

    public GameScene getModel() {
        return model;
    }

    public void setModel(GameScene model) {
        this.model = model;
    }





    public synchronized boolean isEscapeKeyTyped() {
        return EscapeKeyTyped.get();
    }

    public void setEscapeKeyTyped(boolean EscapeKeyTyped) {
        this.EscapeKeyTyped.set(EscapeKeyTyped);
    }

    public synchronized boolean isRightKeyTyped() {
        return rightKeyTyped.get();
    }

    public void setRightKeyTyped(boolean rightKeyTyped) {
        this.rightKeyTyped.set(rightKeyTyped);
    }

    public synchronized boolean isLeftKeyTyped() {
        return LeftKeyTyped.get();
    }

    public void setLeftKeyTyped(boolean LeftKeyTyped) {
        this.LeftKeyTyped.set(LeftKeyTyped);
    }

    public synchronized boolean isSpaceBarKeyTyped() {
        return spaceBarKeyTyped.get();
    }

    public synchronized void setSpaceBarKeyStatut(boolean statut) {
        spaceBarKeyTyped.set(statut);
    }

}

