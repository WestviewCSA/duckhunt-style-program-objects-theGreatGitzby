import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	
	//frame size
	private int screenWidth = 910, screenHeight = 540; //CHANGE TIS TO MATCH BACKGROUND
	private String title = "Duck Hunt";
	private int count = 3;
	
	/**
	 * Declare and instantiate (create) your objects here
	 */
	private Duck duckObject = new Duck();
	private Duck2 greenBook = new Duck2();
	private Background myBackground = new Background();
	private Character myCharacter = new Character();
	private BackgroundLayer myLayer = new BackgroundLayer();
	private Character2 characterB = new Character2();
	private MyCursor cursor = new MyCursor();
	private Ammo ammoObject = new Ammo();
	private Ammo1 ammoObject1 = new Ammo1();
	private Ammo2 ammoObject2 = new Ammo2(); 
	private Ammo3 ammoObject3 = new Ammo3();
	
	public void paint(Graphics pen) {
		
		//this line of code is to force redraw the entire frame
		super.paintComponent(pen);
		
		//background should be drawn before objects
		//or based on how you want to LAYER
		myBackground.paint(pen);
		
		
		
		//call paint for the object
		//for objects, you call methods on them using the dot operator
		//methods use always involve parenthesis
		duckObject.paint(pen);
		greenBook.paint(pen);
		
		myLayer.paint(pen);
		
		if(count == 0) {
			ammoObject.paint(pen);
		}
		if(count == 1) {
			ammoObject1.paint(pen);
		}
		if(count == 2) {
			ammoObject2.paint(pen);
		}
		if(count == 3) {
			ammoObject3.paint(pen);
		}
		if(count == -1){
			ammoObject3.paint(pen);
			count = 3;
		}
		myCharacter.paint(pen);
		characterB.paint(pen);
		cursor.paint(pen);
	}
	
	
	@Override
	public void mouseClicked(MouseEvent mouse) {
	    // Runs when the mouse is clicked (pressed and released quickly).
	    // Example: You could use this to open a menu or select an object.
	}

	@Override
	public void mouseEntered(MouseEvent mouse) {
	    // Runs when the mouse enters the area of a component (like a button).
	    // Example: You could highlight the button when the mouse hovers over it.
	}

	@Override
	public void mouseExited(MouseEvent mouse) {
	    // Runs when the mouse leaves the area of a component.
	    // Example: You could remove the highlight when the mouse moves away.
	}

	@Override
	public void mousePressed(MouseEvent mouse) {
	    // Runs when a mouse button is pressed down.
	    // Example: You could start dragging an object here.
		System.out.println(mouse.getX()+":"+mouse.getY());
		
		duckObject.checkCollision(mouse.getX(), mouse.getY());
		greenBook.checkCollision(mouse.getX(), mouse.getY());
		
		count -= 1;
		System.out.println(count);
	}

	@Override
	public void mouseReleased(MouseEvent mouse) {
	    // Runs when a mouse button is released.
	    // Example: You could stop dragging the object or drop it in place.
	}



	/*
	 * This method runs automatically when a key is pressed down
	 */
	public void keyPressed(KeyEvent key) {
		
		System.out.println("from keyPressed method:"+key.getKeyCode());
		
	}

	/*
	 * This method runs when a keyboard key is released from a pressed state
	 * aka when you stopped pressing it
	 */
	public void keyReleased(KeyEvent key) {
		
	}

	/**
	 * Runs when a keyboard key is pressed then released
	 */
	public void keyTyped(KeyEvent key) {
		
		
	}
	
	
	/**
	 * The Timer animation calls this method below which calls for a repaint of the JFrame.
	 * Allows for our animation since any changes to states/variables will be reflected
	 * on the screen if those variables are being used for any drawing on the screen.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}
	
	/*
	 * Main method to create a Frame (the GUI that you see)
	 */
	public static void main(String[] arg) {
		new Frame();
	}
	
	
	
	public Frame() {
		JFrame f = new JFrame(title);
		f.setSize(new Dimension(screenWidth, screenHeight));
		f.setBackground(Color.blue);
		f.add(this);
		f.setResizable(false);
		f.setLayout(new GridLayout(1,2));
		f.addMouseListener(this);
		f.addKeyListener(this);
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		//cursor icon code
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("cursor.png");
		Cursor a = toolkit.createCustomCursor(image, new Point(this.getX(), this.getY()), "");
		this.setCursor (a);
		
	}

}
