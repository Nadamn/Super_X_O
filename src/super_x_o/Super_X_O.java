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
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
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
import javafx.scene.layout.FlowPane;
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
public class Super_X_O extends Application implements EventHandler<ActionEvent> {
    
    Scene currentScene;
    int currentMove=0;
     
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
    
        public void GameWinInit(){
        BorderPane borderPane;
        GridPane gamePane;
        FlowPane btnsPane;
        Button signOut;
        Button quitGame;
        Button newGame;
       // Button b1,b2,b3,b4,b5,b6,b7,b8,b9;
        Button[] Buttons=new Button[9];
        newGame=new Button("New Game");
        quitGame=new Button("Quit Game");
        signOut=new Button("Sign Out");
        btnsPane=new FlowPane(Orientation.VERTICAL);
        borderPane=new BorderPane();
        gamePane=new GridPane();
        
        
        btnsPane.getChildren().addAll(newGame,quitGame,signOut);
        btnsPane.setColumnHalignment(HPos.LEFT);
        btnsPane.setAlignment(Pos.CENTER);
        btnsPane.setVgap(50);
        borderPane.setLeft(btnsPane);
        borderPane.setCenter(gamePane);
         
        for(int i=0;i<9;i++){
           Buttons[i] = new Button();
           Buttons[i].setMinSize(100,100);
           Buttons[i].setStyle("-fx-background-color:lightblue");
           Buttons[i].setOnAction(this);
        }
       
        
        gamePane.add(Buttons[0],0,0);
        gamePane.add(Buttons[1],1,0);
        gamePane.add(Buttons[2],2,0);
        gamePane.add(Buttons[3],0,1);
        gamePane.add(Buttons[4],1,1);
        gamePane.add(Buttons[5],2,1);
        gamePane.add(Buttons[6],0,2);
        gamePane.add(Buttons[7],1,2);
        gamePane.add(Buttons[8],2,2);
        gamePane.setHgap(5);
        gamePane.setVgap(5);
        gamePane.setAlignment(Pos.CENTER);
        currentScene=new Scene(borderPane,500,320);
    }
    
    
    public void handle(ActionEvent e){ 
       currentMove++;
       ((Button)e.getTarget()).setStyle("-fx-background-color: lightblue;-fx-font-size :4em;-fx-text-fill: red");
       if (currentMove%2==0)
       ((Button)e.getTarget()).setText("O");
       else 
           ((Button)e.getTarget()).setText("X");
           
       ((Button)e.getTarget()).setDisable(true);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        //initMainWindow();
        //invitationWindow();
        GameWinInit();
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
