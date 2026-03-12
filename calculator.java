// march 11 2026
// making a simple calculator project using java
// created by Faiz Khan

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class calculator{
    // dimensions of the window
    int boardWidth = 360;
    int boardHeight = 540;
    
    // setting up colors for the calulator 
    Color lightGray = new Color(212,212,210);
    Color darkGray = new Color(80,80,80);
    Color black = new Color(28,28,28);
    Color orange = new Color(255,149,0);

    JFrame frame = new JFrame("Calculator");
    JLabel displayLabel = new JLabel();
    JPanel displayPlanel = new JPanel();
    JPanel buttonPanel = new JPanel();

    // creating arrays for button values
    String[] buttonValues = {"AC", "+/-", "%", "/",
                             "7",  "8", "9", "x",
                             "4", "5", "6", "-",
                             "1", "2", "3", "+",
                             "0", ".","√", "="
    };

    String[] rightSymbols = {"/", "x", "-", "+", "="};
    String[] topSymbols = {"AC", "+/-", "%"};

    // keeps track of 2 numbers and the opperator : A+B A-B
    String A = "0";
    String operator = null;
    String B = null;


    // setting up the properties of the window
    calculator(){
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // setting up label elements for the result 
        displayLabel.setBackground(black);
        displayLabel.setForeground(Color.white);
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 80));
        displayLabel.setHorizontalAlignment(JLabel.RIGHT);
        displayLabel.setText("0");
        displayLabel.setOpaque(true);

        // making label elements veiwable
        displayPlanel.setLayout(new BorderLayout());
        displayPlanel.add(displayLabel);
        frame.add(displayPlanel, BorderLayout.NORTH); // setting up result space
        // making the button elements veiwable
        buttonPanel.setLayout(new GridLayout(5,4));
        buttonPanel.setBackground(black);
        frame.add(buttonPanel);

        // using a for loop to display the buttons
        for(int i = 0; i < buttonValues.length; i++){
            // setting up the button properties
            JButton button = new JButton();
            String buttonValue = buttonValues[i];
            button.setFont(new Font("Arial", Font.PLAIN, 30));
            button.setText(buttonValue);

            // only way to get the buttons colored on mac
            button.setOpaque(true);
            button.setBorderPainted(false);
            button.setFocusPainted(false);

            button.setBorder(new LineBorder(Color.blue));

            // using conditionals to assign color to certain buttons
            if(Arrays.asList(topSymbols).contains(buttonValue)){
                button.setBackground(lightGray);
                button.setForeground(black);
            }
            else if(Arrays.asList(rightSymbols).contains(buttonValue)){
                button.setBackground(orange);
                button.setForeground(Color.white);
            }
            else{
                button.setBackground(Color.darkGray);
                button.setForeground(Color.white);
            }

            buttonPanel.add(button); // adding button to the pannel

            // setting up the buttons so that they work when clicked
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    JButton button = (JButton) e.getSource(); // getting the source of click
                    String buttonValue = button.getText();

                    if(Arrays.asList(rightSymbols).contains(buttonValue)){
                        
                    }
                    else if(Arrays.asList(topSymbols).contains(buttonValue)){
                        if(buttonValue == "AC"){ // clearing
                            clearAll();
                            displayLabel.setText("0");
                        }
                        else if(buttonValue == "+/-"){ // making the value in displayLabel negative or positive
                            double numDisplay = Double.parseDouble(displayLabel.getText());
                            numDisplay *= -1;
                            displayLabel.setText(removeZeroDecimal(numDisplay));
                        }
                        else if(buttonValue == "%"){

                        }
                    }
                    else{   // digits or .
                        if (buttonValue == "."){
                            if(!displayLabel.getText().contains(buttonValue)) // only 1 . can be used
                                displayLabel.setText(displayLabel.getText() + buttonValue);
                        }
                        else if("0123456789".contains(buttonValue)){
                            if(displayLabel.getText() == "0") // replace 0 with button value
                                displayLabel.setText(buttonValue);
                            else    // append multiple button values
                                displayLabel.setText(displayLabel.getText() + buttonValue);
                        }
                    }
                }
            });
        }

    }

    void clearAll(){
        A = "0";
        operator = null;
        B = null;
    }

    String removeZeroDecimal(double numDisplay){
        if(numDisplay % 1 == 0){
            return Integer.toString((int) numDisplay);
        }
        else
            return Double.toString(numDisplay);
    }
}