
  import javafx.application.Application;
  import javafx.geometry.Insets;
  import javafx.scene.Scene;
  import javafx.scene.canvas.Canvas;
  import javafx.scene.canvas.GraphicsContext;
  import javafx.scene.control.Button;
  import javafx.scene.control.Label;
  import javafx.scene.layout.BorderPane;
  import javafx.scene.layout.GridPane;
  import javafx.scene.paint.Color;
  import javafx.stage.Stage;
  import javafx.application.Application;
  import javafx.geometry.Insets;
  import javafx.geometry.Pos;
  import javafx.scene.Scene;
  import javafx.scene.control.Button;
  import javafx.scene.control.Label;
  import javafx.scene.layout.GridPane;
  import javafx.scene.layout.HBox;
  import javafx.scene.paint.Color;
  import javafx.scene.text.Font;
  import javafx.stage.Stage;
  import javafx.scene.layout.VBox;
  import javafx.scene.layout.Background;
  import javafx.scene.layout.BackgroundFill;
  import javafx.scene.layout.CornerRadii;
  import javafx.geometry.Insets;
  import javafx.scene.paint.Color;
  import javafx.scene.text.FontWeight;
  import javafx.scene.text.Font;
  import javafx.event.ActionEvent;
  import javafx.event.EventHandler;



  public class Project extends Application
  {
    
     private GamePane[][] gamePanes = new GamePane[4][4];
    
     @Override
     public void start(Stage primaryStage)
     {
        
           // Create the root layout
        BorderPane root = new BorderPane();
        
           // Set up the top label
        int numBalls = 15;
        int numMoves = 2;
        Label label = new Label("Balls: " + numBalls + "  Possible Moves: " + numMoves);
        label.setFont(Font.font(20));
        HBox hbox = new HBox(label);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(10));
        
           // Set up the center grid
        GridPane centerGrid = new GridPane();
        centerGrid.setAlignment(Pos.CENTER);
        centerGrid.setHgap(10);
        centerGrid.setVgap(10);
        centerGrid.setPadding(new Insets(10));
        root.setCenter(centerGrid);
        
           // Create the game panes
        for (int i = 0; i < 4; i++)
        {
           for (int j = 0; j < 4; j++)
           {
              GamePane gamePane = new GamePane(3,3);
              gamePanes[i][j] = gamePane;
              centerGrid.add(gamePane, j, i);
           }
        }
       gamePanes[2][0].setVisible(false);
       
        for (int i = 0; i < 4; i++)
        {
           for (int j = 0; j < 4; j++)
           {
               gamePanes[i][j].setRightButtonVisible(false);
               gamePanes[i][j].setLeftButtonVisible(false);
               gamePanes[i][j].setTopButtonVisible(false);
               gamePanes[i][j].setBallVisible(true);
           }
        }
        gamePanes[0][0].setTopButtonVisible(true);
        gamePanes[2][2].setRightButtonVisible(true);
       
       
      
              
        // Set up the scene and show the stage
        Scene scene = new Scene(new javafx.scene.layout.VBox(hbox, centerGrid), 600, 600);
        primaryStage.setTitle("Ball Game");
        primaryStage.setScene(scene);
        primaryStage.show();
      
     

      
      
     }
   


    
       // Inner class for the game pane
     public class GamePane extends GridPane
     {
        
        private Button topButton = new Button();
        private Button leftButton = new Button();
        private Button rightButton = new Button();
        private Button bottomButton = new Button();
        private Canvas canvas = new Canvas(80, 80);
        private boolean ballVisible;
        private boolean topButtonVisible;
        private boolean leftButtonVisible;
        private boolean rightButtonVisible;
        private boolean bottomButtonVisible;
        private int x;
        private int y;
       // rightButton.setOnAction(new ButtonListener());
      
        public class RightButtonListener implements EventHandler<ActionEvent>
        {
           public void handle(ActionEvent e)
           {
           if(e.getSource()== rightButton)
             {
               gamePanes[x-1][y-1].setVisible(false);
               gamePanes[x-1][y-2].setVisible(false);
               gamePanes[x-1][y-3].setVisible(true);
               
             }
              
           }
        }
        public class LeftButtonListener implements EventHandler<ActionEvent>
        {
           public void handle(ActionEvent e)
           {
           if(e.getSource()== leftButton)
             {
               gamePanes[x-1][y-1].setVisible(false);
               gamePanes[x-1][y-2].setVisible(false);
               gamePanes[x-1][y-3].setVisible(true);               
             }

           }
        }
        public class BottomButtonListener implements EventHandler<ActionEvent>
        {
           public void handle(ActionEvent e)
           {
           if(e.getSource()== bottomButton)
             {
               gamePanes[x-1][y-1].setVisible(false);
               gamePanes[x-1][y-2].setVisible(false);
               gamePanes[x-1][y-3].setVisible(true);
               
             }


           }
        }
        public class TopButtonListener implements EventHandler<ActionEvent>
        {
           public void handle(ActionEvent e)
           {
             if(e.getSource()== topButton)
             {
               System.out.println(x);
               System.out.println(y);
               gamePanes[x-1][y-1].setVisible(false);
               gamePanes[x-2][y-1].setVisible(false);
               gamePanes[x-3][y-1].setVisible(true);               
             }
             
              updateButtonVisibility();
              draw();
           }
        }

        public GamePane(int row, int col)
        {
           y = row;
           x = col;
           topButton.setOnAction(new TopButtonListener());
           bottomButton.setOnAction(new BottomButtonListener());
           leftButton.setOnAction(new LeftButtonListener());
           rightButton.setOnAction(new RightButtonListener());
         
               // Set up the grid pane
           setHgap(3);
           setVgap(3);
           setPadding(new Insets(3));
            
               // Set up the buttons
           topButton.setPrefSize(80, 20);
           leftButton.setPrefSize(20, 80);
           rightButton.setPrefSize(20, 80);
           bottomButton.setPrefSize(80, 20);
           add(topButton, 1, 0);
           add(leftButton, 0, 1);
           add(rightButton, 2, 1);
           add(bottomButton, 1, 2);
            
               // Set up the canvas
           GraphicsContext gc = canvas.getGraphicsContext2D();
           gc.setFill(Color.BLACK);
           gc.fillOval(0, 0, 80, 80);
           add(canvas, 1, 1);
            
               //visibility of buttons
           boolean bottomButtonVisible;
           boolean leftButtonVisible;
           boolean topButtonVisible;
           boolean rightButtonVisible;
            
               //Ball visible
            
           ballVisible = false;
        }
        public boolean isBallVisible()
        {
           return ballVisible;
        }
      
        public void setBallVisible(boolean ballVisible)
        {
           this.ballVisible = ballVisible;
           draw();
        }
      
        public boolean isTopButtonVisible()
        {
           return topButtonVisible;
        }
      
        public void setTopButtonVisible(boolean topButtonVisible)
        {
           this.topButtonVisible = topButtonVisible;
           draw();
          
        }
      
        public boolean isLeftButtonVisible()
        {
           return leftButtonVisible;
        }
      
        public void setLeftButtonVisible(boolean leftButtonVisible)
        {
           this.leftButtonVisible = leftButtonVisible;
           draw();
        }
      
        public boolean isRightButtonVisible()
        {
           return rightButtonVisible;
        }
      
        public void setRightButtonVisible(boolean rightButtonVisible)
        {
           this.rightButtonVisible = rightButtonVisible;
           draw();
        }
      
        public boolean isBottomButtonVisible()
        {
           return bottomButtonVisible;
        }
      
        public void setBottomButtonVisible(boolean bottomButtonVisible)
        {
           this.bottomButtonVisible = bottomButtonVisible;
           draw();
           //updateButtonVisibility();
          
        }
            
        public void draw()
        {
           GraphicsContext gc = canvas.getGraphicsContext2D();
           Canvas centerCanvas = (Canvas) getChildren().get(4);
           if (ballVisible = true)
           {
              gc.setFill(Color.BLACK);
              gc.fillOval(0, 0, 80, 80);
           }
           else if (ballVisible = false)
           {
              gc.clearRect(0, 0, centerCanvas.getWidth(), centerCanvas.getHeight());
                    
           }
               
           topButton.setVisible(topButtonVisible);
           leftButton.setVisible(leftButtonVisible);
           rightButton.setVisible(rightButtonVisible);
           bottomButton.setVisible(bottomButtonVisible);
            
        } 
        
        public void updateButtonVisibility()
        {
        for (int i = 0; i < gamePanes.length; i++)
         {
         for (int j = 0; j < gamePanes[i].length; j++)
          {
             GamePane currentPane = gamePanes[i][j];

             // Check if button above can be displayed
             if (i > 1 && currentPane.isBallVisible() && gamePanes[i-1][j].isBallVisible() && !gamePanes[i-2][j].isBallVisible())
             {
                 currentPane.setTopButtonVisible(true);
             }
             else
             {
                 currentPane.setTopButtonVisible(false);
             }

             // Check if button below can be displayed
             if (i < gamePanes.length - 2 && currentPane.isBallVisible() && gamePanes[i+1][j].isBallVisible() && !gamePanes[i+2][j].isBallVisible())
              {
                 currentPane.setBottomButtonVisible(true);
             }
             else
             {
                 currentPane.setBottomButtonVisible(false);
             }

             // Check if button to the left can be displayed
             if (j > 1 && currentPane.isBallVisible() && gamePanes[i][j-1].isBallVisible() && !gamePanes[i][j-2].isBallVisible())
             {
                 currentPane.setLeftButtonVisible(true);
             }
             else
             {
                 currentPane.setLeftButtonVisible(false);
             }

             // Check if button to the right can be displayed
             if (j < gamePanes[i].length - 2 && currentPane.isBallVisible() && gamePanes[i][j+1].isBallVisible() && !gamePanes[i][j+2].isBallVisible())
             {
                 currentPane.setRightButtonVisible(true);
             }
             else
             {
                 currentPane.setRightButtonVisible(false);
             }
           }
        }
      }        
    }
  }

