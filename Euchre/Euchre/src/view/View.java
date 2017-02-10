package view;
import javax.swing.JFrame;
import controller.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;

import controller.*;
import models.*;

public class View extends JFrame {
    private static JMenuBar menu;
    private static JMenu fileMenu;
    private static JMenuItem newGameItem, quitGameItem;
	
    /**
     * Render class renders UI for the Euchre game.
     */
    public static void Render(GameModel model) {
	
    	JFrame frame = new JFrame("Euchre");
    	frame.setSize(800, 600);
	fileMenu = new JMenu("File");
	quitGameItem = new JMenuItem();
  
    	
    }

    public static void main(String[] args) {
	Render gameRender = new Render();
	gameRender.setDefaultCloseOperation(EXIT_ON_CLOSE);
	gameRender.setTitle("CIS 350 Euchre");
	gameRender.pack();
	gameRender.setVisible(true);
    }
}
