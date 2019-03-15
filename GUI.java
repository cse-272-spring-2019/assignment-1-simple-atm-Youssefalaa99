import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.Scene;




public class GUI extends Application {
    private Stage window1,window2=new Stage();
    private Scene scene1,scene2,scene3;
    private Button button,withdraw,deposit,inquiry,next,prev;
    ATM client=new ATM();

    public static void main(String []args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window1=primaryStage;
        window1.setTitle("ATM");
        //First scene
        Label label1=new Label("\tWelcome\nPlease enter your card number");
        PasswordField passfield=new PasswordField();
        passfield.setPromptText("Enter your card number here");
        VBox layout1=new VBox(20);
        button=new Button();
        button.setText("Enter");

        button.setOnAction(event -> {
           if(passfield.getText().isEmpty()){
                display("Please type in a card number!");
            }

            else if((passfield.getText().equals(client.getCreditNumber()))==false){
                display("Wrong card number, please try again!");
            }
            else
                window1.setScene(scene2);
            });
        layout1.getChildren().addAll(label1,passfield,button);
        layout1.setAlignment(Pos.CENTER);
        scene1=new Scene(layout1,400,200);

        //Second scene
        withdraw=new Button("Withdraw");
        deposit=new Button("Deposit");
        inquiry=new Button("Balance Inquiry");
        next=new Button("Next");
        prev=new Button("Back");
        History history=new History();


        HBox center=new HBox(40);
        center.getChildren().addAll(withdraw,deposit,inquiry);
        center.setAlignment(Pos.CENTER);

        Label label2=new Label("Select a command");
        label2.setFont(Font.font("Arial",28));
        label2.setTextFill(Color.web("#420DAB"));
        Label label3=new Label("Welcome Mr. Youssef");

        VBox top=new VBox(40);
        top.getChildren().addAll(label2,label3);
        top.setAlignment(Pos.TOP_CENTER);

        HBox bottom=new HBox(400);
        bottom.getChildren().addAll(prev,next);
        bottom.setAlignment(Pos.CENTER);

        BorderPane layout2=new BorderPane();
        layout2.setCenter(center);
        layout2.setTop(top);
        layout2.setBottom(bottom);

        scene2=new Scene(layout2,600,400);

        //Buttons events
        withdraw.setOnAction(event -> {
            Withdraw_Window(label3,history); });
        deposit.setOnAction(event -> {Deposit_Window(label3,history); });
        inquiry.setOnAction(event -> {
            label3.setText("Your current balance: "+client.getBalance());
            history.addString("Your current balance: "+client.getBalance());});

        next.setOnAction(event -> {
            //String info=history.Next(); doesn't work?
            history.Next();
             if(history.getString() != null)
             {label3.setText(history.getString());}});

        prev.setOnAction(event -> {
            //String info=history.Next(); doesn't work?
            history.Prev();
            if(history.getString() != null)
            {label3.setText(history.getString()); } });



        window1.setScene(scene1);
        window1.show();

    }

    public void Withdraw_Window(Label label,History history){
        window2.setTitle("Enter amount");
        Label EnterValue = new Label("Please enter a value");
        Button zero = new Button("0");
        Button one = new Button("1");
        Button two = new Button("2");
        Button three = new Button("3");
        Button four = new Button("4");
        Button five = new Button("5");
        Button six = new Button("6");
        Button seven = new Button("7");
        Button eight = new Button("8");
        Button nine = new Button("9");
        Button backspace = new Button("<-");
        Button ok = new Button("OK");
        Text textfield = new Text();
        one.setOnAction(event -> textfield.setText(textfield.getText() + "1"));
        two.setOnAction(event -> textfield.setText(textfield.getText() + "2"));
        three.setOnAction(event -> textfield.setText(textfield.getText() + "3"));
        four.setOnAction(event -> textfield.setText(textfield.getText() + "4"));
        five.setOnAction(event -> textfield.setText(textfield.getText() + "5"));
        six.setOnAction(event -> textfield.setText(textfield.getText() + "6"));
        seven.setOnAction(event -> textfield.setText(textfield.getText() + "7"));
        eight.setOnAction(event -> textfield.setText(textfield.getText() + "8"));
        nine.setOnAction(event -> textfield.setText(textfield.getText() + "9"));
        zero.setOnAction(event -> textfield.setText(textfield.getText() + "0"));
        backspace.setOnAction(event -> {
            String backspace1=new String();
            backspace1=textfield.getText().substring(0,textfield.getText().length()-1);
            textfield.setText(backspace1);
        });

        VBox top1 = new VBox(20);
        top1.getChildren().addAll(EnterValue, textfield);
        top1.setAlignment(Pos.CENTER);

        GridPane grid = new GridPane();
        grid.add(one, 0, 0);
        grid.add(two, 1, 0);
        grid.add(three, 2, 0);
        grid.add(four, 0, 1);
        grid.add(five, 1, 1);
        grid.add(six, 2, 1);
        grid.add(seven, 0, 2);
        grid.add(eight, 1, 2);
        grid.add(nine, 2, 2);
        grid.add(zero, 1, 3);
        grid.add(backspace,2,3);
        grid.setAlignment(Pos.CENTER);

        HBox bot = new HBox();
        bot.getChildren().add(ok);
        bot.setAlignment(Pos.BOTTOM_RIGHT);


        BorderPane border = new BorderPane();
        border.setTop(top1);
        border.setCenter(grid);
        border.setBottom(bot);

        ok.setOnAction(event -> {
            int amount=Integer.parseInt(textfield.getText());
            if(textfield.getText().isEmpty())
                display("No value entered!");
            else{
                 if(amount>client.getBalance()){
                     display("Insufficient funds!\nCurrent Balance:"+client.getBalance());
                 }
                 else
                 {client.Withdraw(amount);
                     label.setText("Successfull, withdrawn: "+amount);
                     history.addString("Successfull, withdrawn: "+amount);
                window2.close();}}});


        scene3 = new Scene(border, 300, 300);
        window2.setScene(scene3);
        window2.show();

    }


    public void Deposit_Window(Label label,History history){
        window2.setTitle("Enter amount");
        Label EnterValue = new Label("Please enter a value");
        Button zero = new Button("0");
        Button one = new Button("1");
        Button two = new Button("2");
        Button three = new Button("3");
        Button four = new Button("4");
        Button five = new Button("5");
        Button six = new Button("6");
        Button seven = new Button("7");
        Button eight = new Button("8");
        Button nine = new Button("9");
        Button backspace = new Button("<-");
        Button ok = new Button("OK");
        Text textfield = new Text();
        one.setOnAction(event -> textfield.setText(textfield.getText() + "1"));
        two.setOnAction(event -> textfield.setText(textfield.getText() + "2"));
        three.setOnAction(event -> textfield.setText(textfield.getText() + "3"));
        four.setOnAction(event -> textfield.setText(textfield.getText() + "4"));
        five.setOnAction(event -> textfield.setText(textfield.getText() + "5"));
        six.setOnAction(event -> textfield.setText(textfield.getText() + "6"));
        seven.setOnAction(event -> textfield.setText(textfield.getText() + "7"));
        eight.setOnAction(event -> textfield.setText(textfield.getText() + "8"));
        nine.setOnAction(event -> textfield.setText(textfield.getText() + "9"));
        zero.setOnAction(event -> textfield.setText(textfield.getText() + "0"));
        backspace.setOnAction(event -> {
            String backspace1=new String();
            backspace1=textfield.getText().substring(0,textfield.getText().length()-1);
            textfield.setText(backspace1);
        });


        VBox top1 = new VBox(20);
        top1.getChildren().addAll(EnterValue, textfield);
        top1.setAlignment(Pos.CENTER);

        GridPane grid = new GridPane();
        grid.add(one, 0, 0);
        grid.add(two, 1, 0);
        grid.add(three, 2, 0);
        grid.add(four, 0, 1);
        grid.add(five, 1, 1);
        grid.add(six, 2, 1);
        grid.add(seven, 0, 2);
        grid.add(eight, 1, 2);
        grid.add(nine, 2, 2);
        grid.add(zero, 1, 3);
        grid.add(backspace,2,3);
        grid.setAlignment(Pos.CENTER);

        HBox bot = new HBox();
        bot.getChildren().add(ok);
        bot.setAlignment(Pos.BOTTOM_RIGHT);


        BorderPane border = new BorderPane();
        border.setTop(top1);
        border.setCenter(grid);
        border.setBottom(bot);

        ok.setOnAction(event -> {
            int amount=Integer.parseInt(textfield.getText());
            if(textfield.getText().isEmpty())
                display("Please enter a value!");
            else{
                client.Deposit(amount);
                label.setText("Successfull, deposited: "+amount);
                history.addString("Successfull, deposited: "+amount);
                window2.close();}});


        scene3 = new Scene(border, 300, 300);
        window2.setScene(scene3);
        window2.show();

    }


    //Error pop-up message:
    public static void display(String text){
        Stage window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Error");
        Button button =new Button("OK");
        button.setOnAction(event -> window.close());
        Label label=new Label(text);
        VBox layout=new VBox(20);
        layout.getChildren().addAll(label,button);
        layout.setAlignment(Pos.CENTER);
        Scene scene=new Scene(layout,300,250);
        window.setScene(scene);
        window.show();

    }

}
