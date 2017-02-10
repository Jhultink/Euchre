package view;

import controller.*;
import models.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Render extends JFrame { // implements ActionListener {

    JMenuBar menu;
    JMenu fileMenu;
    JMenuItem newGameItem, quitGameItem;
    
    /**
     * Constructor for the class instantiates GUI and game model.
     */
    Render() {
	fileMenu = new JMenu("File");
	quitGameItem = new JMenuItem();
    }
    
    public static void main(String[] args) {
	// TODO Auto-generated method stub
	Render gameRender = new Render();
	gameRender.setDefaultCloseOperation(EXIT_ON_CLOSE);
	gameRender.setTitle("CIS 350 Euchre");
	gameRender.pack();
	gameRender.setVisible(true);
    }

}
