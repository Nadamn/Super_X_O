/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package super_x_o;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Nada
 */
public class Super_X_O extends Application {
    
       Scene currentScene;
     
    public void initMainWindow(){        
        BorderPane mainWindowPane = new BorderPane();
        
        //----------------------------top bar-------------------------------------------------------------------------------------//
        Button signOut = new Button("Sign Out");
        signOut.setPrefSize(170, 30);
        
        HBox topBar = new HBox();        
        topBar.getChildren().addAll(signOut);
        topBar.setAlignment(Pos.CENTER_RIGHT);
        topBar.setStyle("-fx-background-color: #000000; -fx-padding: 20px;");
        topBar.prefHeightProperty().bind(mainWindowPane.heightProperty().multiply(0.05));
     
        mainWindowPane.setTop(topBar);
        
        //----------------------------friends list--------------------------------------------------------------------------------//
        //next block of code relating to arraylist will be replaced from db    
        Map<String, Color> playersFromDB = new HashMap<>();
        playersFromDB.put("Nada", Color.GRAY);
        playersFromDB.put("Bahaa", Color.GREEN);
        playersFromDB.put("AbdelRahman", Color.RED);
        playersFromDB.put("Mostafa", Color.RED);
        playersFromDB.put("David", Color.GREEN);
        //end
        
        VBox friendsListPane = new VBox();
        playersFromDB.entrySet().stream().forEach((player) -> {
            Button invite = new Button("invite");
            invite.setPrefSize(90, 30);
            Button cancel = new Button("cancel");
            cancel.setPrefSize(90, 30);
            Text text = new Text(player.getKey());
            text.setFont(Font.font("Monotype Corsiva", FontWeight.BOLD, 20));
            Circle playerStatus = new Circle(5, player.getValue());
            
            HBox hbox = new HBox(playerStatus, text,invite, cancel); 
            
            hbox.setSpacing(10);
            hbox.setStyle("-fx-padding: 20px;");
            friendsListPane.getChildren().add(hbox);
            cancel.setDisable(true);
            if(player.getValue() != Color.GREEN){
                invite.setDisable(true);
            }
            invite.setOnAction((ActionEvent event) -> {
                cancel.setDisable(false);
                for(int i = 0; i< friendsListPane.getChildren().size(); i++){
                    HBox temp = (HBox) friendsListPane.getChildren().get(i);
                    temp.getChildren().get(2).setDisable(true);
                }
            });
            cancel.setOnAction((ActionEvent event) -> {
                invite.setDisable(false);
                cancel.setDisable(true);
                for(int i = 0; i< friendsListPane.getChildren().size(); i++){
                    HBox temp = (HBox) friendsListPane.getChildren().get(i);
                    Circle cirTemp = (Circle) temp.getChildren().get(0);
                    if(cirTemp.getFill() == Color.GREEN){
                        temp.getChildren().get(2).setDisable(false);
                    }
                }
            });
        });

        ScrollPane friendlistPane = new ScrollPane(friendsListPane);
        friendlistPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));        
        
        mainWindowPane.setLeft(friendlistPane);        
        
        //----------------------------Score-------------------------------------------------------------------------------------//
        VBox scorePane = new VBox(10);
        Text heading = new Text("Currents Score: ");
        heading.setFont(Font.font("Monotype Corsiva", FontWeight.BOLD, 40));
        Text score = new Text("7");
        score.setFont(Font.font("Monotype Corsiva", FontWeight.BOLD, 40));
        scorePane.getChildren().addAll(heading, score);
        scorePane.setAlignment(Pos.CENTER);
        
        mainWindowPane.setRight(scorePane);
        
        //----------------------------Score-------------------------------------------------------------------------------------//
        Button playWithMachine = new Button("Play With Machine");
        playWithMachine.setAlignment(Pos.CENTER);
        playWithMachine.setPrefSize(300, 300);
        playWithMachine.setFont(Font.font("Monotype Corsiva", FontWeight.BOLD, 40));
        playWithMachine.setWrapText(true);
        
        mainWindowPane.setCenter(playWithMachine);
        
        ScrollPane mainWindowPaneScrolled = new ScrollPane(mainWindowPane);
        mainWindowPaneScrolled.setFitToHeight(true);
        mainWindowPaneScrolled.setFitToWidth(true);
        currentScene = new Scene(mainWindowPaneScrolled);        
    }
    
    public void invitationWindow(){ 
        Alert invitation = new Alert(Alert.AlertType.CONFIRMATION);
        invitation.setTitle("Invitation Message");
        invitation.setHeaderText("*playerx* wants to play with you");
        invitation.setContentText("do you want to play with him?");
        ButtonType yes = new ButtonType("Yes");
        ButtonType no = new ButtonType("No");
        invitation.getButtonTypes().setAll(yes, no);
        Optional<ButtonType> result = invitation.showAndWait();
        if (result.get() == yes){
            System.out.println("yes");
        } else {
            System.out.println("yes");
        }
    }
    
    @Override
    public void init() {
        initMainWindow();
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
//        invitationWindow();
        primaryStage.setScene(currentScene);
        primaryStage.setTitle("Super_XO");
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
