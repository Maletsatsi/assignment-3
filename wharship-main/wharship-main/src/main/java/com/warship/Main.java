package com.warship;

import javafx.animation.AnimationTimer;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    TranslateTransition t1=new TranslateTransition();
    TranslateTransition t2=new TranslateTransition();
    TranslateTransition t3=new TranslateTransition();
    TranslateTransition t4=new TranslateTransition();
    @Override
   public void start(Stage stage) {

    Pane root = new Pane();

    Scene scene = new Scene(root, 700, 360);
    scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());


    ImageView ship = createShip(scene);

    ImageView cloud1=createCloud1(scene);

    ImageView cloud2=createCloud2(scene);

    ImageView cloud3=createCloud3(scene);

    ImageView cloud4=createCloud4(scene);

    Text  OVER=new Text();
    OVER.setX(20.0f);
    OVER.setY(60.0f);
    OVER.setCache(true);
    OVER.setText("");
    OVER.setFill(Color.WHITESMOKE);
    OVER.setFont(Font.font(null, FontWeight.BOLD,60));

    root.getChildren().addAll(ship, cloud1,cloud2,cloud3,cloud4,OVER);

    scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
        double y = ship.getY();
        double x = ship.getX();


        switch (event.getCode()){
            case UP -> ship.setY(y - 10);
            case DOWN -> ship.setY(y +10);
            case RIGHT -> ship.setX(x + 10);
            case LEFT -> ship.setX(x - 10);

        }});

        AnimationTimer collision = new AnimationTimer() {
            @Override
            public void handle(long l) {
                CheckCollision(cloud1,cloud2, cloud3, cloud4);
            }

            private void CheckCollision(ImageView cloud1, ImageView cloud2, ImageView cloud3, ImageView cloud4) {



        if(ship.getBoundsInParent().intersects(cloud1.getBoundsInParent()) || ship.getBoundsInParent().intersects(cloud2.getBoundsInParent()) || ship.getBoundsInParent().intersects(cloud3.getBoundsInParent()) || ship.getBoundsInParent().intersects(cloud4.getBoundsInParent()))
        {
            t1.stop();
            t2.stop();
            t3.stop();
            t4.stop();
            OVER.setText("OOPS! GAME OVER!");
            System.out.println("game over");
            ship.setImage(new Image("Plane-Explosion.png"));


            scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
                double x = ship.getX();
                double y = ship.getY();

                switch (event.getCode()){
                    case UP -> ship.setY(y - 0);
                    case DOWN -> ship.setY(y +0);
                    case RIGHT -> ship.setX(x + 0);
                    case LEFT -> ship.setX(x - 0);

                     }
            });
             }
            }
        };
        stage.setTitle("CLOUD GAME");
        collision.start();
        stage.setScene(scene);
        stage.show();
}



    private ImageView createShip(Scene scene) {
        ImageView image = new ImageView(new Image("ship.jpg"));
        image.setFitWidth(70);
        image.setFitHeight(70);
        image.setY(scene.getHeight() - image.getFitHeight());

        return image;
    }
    public ImageView createCloud1(Scene scene) {
        ImageView clouds1=new ImageView(new Image("cloud1.jpg"));

        clouds1.setFitHeight(100);
        clouds1.setFitWidth(100);

        clouds1.setX(1800);
        clouds1.setY(100);


        t1.setNode(clouds1);
        t1.setDuration(Duration.millis(8000));
        t1.setByX(-1900);
        t1.setCycleCount(Integer.MAX_VALUE);
        t1.play();

        return clouds1;

    }
    private ImageView createCloud2(Scene scene) {
        ImageView clouds2=new ImageView(new Image("cloud2.jpg"));
        clouds2.setFitHeight(100);
        clouds2.setFitWidth(100);
        clouds2.setX(1800);
        clouds2.setY(600);
        t2.setNode(clouds2);
        t2.setDuration(Duration.millis(6000));
        t2.setByX(-1900);
        t2.setCycleCount(Integer.MAX_VALUE);
        t2.play();

        return clouds2;

    }
    private ImageView createCloud3(Scene scene) {
        ImageView clouds3=new ImageView(new Image("cloud3.jpg"));
        clouds3.setFitHeight(100);
        clouds3.setFitWidth(100);
        clouds3.setX(1800);
        clouds3.setY(0);
        t3.setNode(clouds3);
        t3.setDuration(Duration.millis(8000));
        t3.setByX(-1900);
        t3.setCycleCount(Integer.MAX_VALUE);
        t3.play();
        return clouds3;

    }
    private ImageView createCloud4(Scene scene) {
        ImageView clouds4=new ImageView(new Image("cloud4.jpg"));
        clouds4.setFitHeight(150);
        clouds4.setFitWidth(100);
        clouds4.setX(1800);
        clouds4.setY(300);
        t4.setNode(clouds4);
        t4.setDuration(Duration.millis(5000));
        t4.setByX(-1900);
        t4.setCycleCount(Integer.MAX_VALUE);
        t4.play();

        return clouds4;

    }


    public static void main(String[] args) {
        launch();
    }
}