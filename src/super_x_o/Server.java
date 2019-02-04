/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package super_x_o;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Nada
 */

public class Server extends Application {
   
    Color red = Color.RED;
    Color green = Color.CHARTREUSE;
    Color gray = Color.LIGHTGREY;
    DataBaseManager DBM;
    //String users[] =  new String[]{ "Abdelrahman" , "Bahaa" , "David" , "Mostafa" , "Nada"};
    Vector<String> users= new Vector <String>();
    Vector<Integer> status= new Vector <Integer>();
    //int status[] = new int[]{ 2 , 1 , 1 , 0 , 2, 0, 0};     // 0 : off      1:on    2:busy 
    
    private Color state(int s){
        switch (s){
            case 0 :
                return gray;
            case 1 :
                return green;
            case 2 :
                return red;
        }
        return gray;
    }   
    
    public HBox addHBox() {
            HBox hbox = new HBox();
            hbox.setPadding(new Insets(15, 12, 15, 12));
            hbox.setSpacing(10);
            hbox.setStyle("-fx-background-color: #000000;");

            Button buttonStart = new Button("Start");
            buttonStart.setPrefSize(100, 20);
            buttonStart.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            System.out.println("start!");
                        }
                    });
            
            Button buttonStop = new Button("Stop");
            buttonStop.setPrefSize(100, 20);
            buttonStop.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                        public void handle(ActionEvent event) {
                            System.out.println("stop!");
                        }
                    });
            
            hbox.getChildren().addAll(buttonStart, buttonStop);

            return hbox;
        }

    public VBox addVBox() {
            VBox vbox = new VBox();
            vbox.setPadding(new Insets(10));
            vbox.setSpacing(8);

            Text title = new Text("Users");
            title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            vbox.getChildren().add(title);

            
            ArrayList<Hyperlink> options = new ArrayList();
            for (int i=0; i< users.size(); i++) {
                options.add(new Hyperlink(users.get(i)));
            }
            
            for (int i=0; i<users.size(); i++) {
                VBox.setMargin(options.get(i), new Insets(0, 0, 0, 8));
                vbox.getChildren().add(options.get(i));
            }

            return vbox;
        }

    public VBox addVBox2() {
            VBox vbox = new VBox();
            vbox.setPadding(new Insets(10));
            vbox.setSpacing(8);

            Text title = new Text("Status");
            title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            vbox.getChildren().add(title);
            
            ArrayList<Circle> options = new ArrayList();
            for (int i=0; i< users.size(); i++) {
               options.add(new Circle(6,state(status.get(i))));
            }
            
            for (int i=0; i<users.size(); i++) {
                VBox.setMargin(options.get(i), new Insets(4, 0, 8, 15));
                vbox.getChildren().add(options.get(i));
            }

            
            
            return vbox;
        }    
    
    
    @Override
    public void start(Stage primaryStage) throws SQLException {
           DBM=new DataBaseManager();
           users=DBM.getPlayerNames();
           
           for (int i=0;i<users.size();i++)
           {
             status.add((new Random()).nextInt(3));
           
           }

        
        

        BorderPane border = new BorderPane();
        HBox hbox = addHBox();
        border.setTop(hbox);
        border.setLeft(addVBox());
        border.setRight(addVBox2());

        
        Scene scene = new Scene(border);
        
        primaryStage.setTitle("X_O Server");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}





