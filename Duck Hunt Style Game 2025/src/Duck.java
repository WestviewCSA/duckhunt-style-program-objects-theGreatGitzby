import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

// The Duck class represents a picture of a duck that can be drawn on the screen.
public class Duck {
    // Instance variables (data that belongs to each Duck object)
    private Image img;               // Stores the picture of the duck
    
    private Image normal; //normal look
    private Image stunned;
    
    private Dog dog = new Dog("zoeWitchReal.gif");
    
    private AffineTransform tx;      // Used to move (translate) and resize (scale) the image

    // Variables to control the size (scale) of the duck image
    private double scaleX;           
    private double scaleY;           

    // Variables to control the location (x and y position) of the duck
    private double x;                
    private double y;        
    
    //variables for speed
    private int vx;
    private int vy;
    
    //debugging variable
    public boolean debugging = true;
    

    // Constructor: runs when you make a new Duck object
    public Duck() {
        normal = getImage("/imgs/redBookFly.gif"); // Load the image file
        
        stunned = getImage("/imgs/stunnedBookRed.png");
        
        //img will point to current state object for image
        img = normal;
        

        
        tx = AffineTransform.getTranslateInstance(0, 0); // Start with image at (0,0)
        
        // Default values
        scaleX = 0.3;
        scaleY = 0.3;
        x = 0;
        y = 0;
        
        vx = 5;
        vy = 3;

        //init the vx and vy variables
        init(x, y); // Set up the starting location and size
    }
    
    //2nd constructor to initialize location and scale!
    public Duck(int x, int y, int scaleX, int scaleY) {
    	this();
    	this.x 		= x;
    	this.y 		= y;
    	this.scaleX = scaleX;
    	this.scaleY = scaleY;
    	init(x,y);
    }
    
    //2nd constructor to initialize location and scale!
    public Duck(int x, int y, int scaleX, int scaleY, int vx, int vy) {
    	this();
    	this.x 		= x;
    	this.y 		= y;
    	this.scaleX = scaleX;
    	this.scaleY = scaleY;
    	this.vx 	= vx; 
    	this.vy 	= vy;
    	init(x,y);
    }
    
    public void setVelocityVariables(int vx, int vy) {
    	this.vx = vx;
    	this.vy = vy;
    }
    
    
    // Changes the picture to a new image file
    public void changePicture(String imageFileName) {
        img = getImage("/imgs/"+ imageFileName);
        init(x, y); // keep same location when changing image
    }
    
    //update any variables for the object such as x, y, vx, vy
    public void update() {
    	
    	//x position updates ased o vx
    	x += vx;
    	y += vy;
    	if(x >= 800) {
    		vx*= -1;
    	}
    	if(x<=-50) {
    		vx *= -1;
    	}
 
    	
    	if(y<=0) {
    		vy *=-1;
    	}
    	//object falling
    	if(vx == 0 && vy > 10) {
    		if(y >= 300) {
    			vy = -(int)(Math.random()*6+3);
    			vx = (int)(Math.random()*6+3);
    			
    			//50% of the time vx is negative
    			if(Math.random()<0.5) {
    				vx *= -1;
    			}
    			img = normal;
    		}
    		
    	}
    	//regular behavior
    	if(y>= 500 && vx != 0) vy *= -1;
    }
    
    
    
    // Draws the duck on the screen
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;   // Graphics2D lets us draw images
       /* tx.scale(.5, .5); //original down or up scale
        if(vx<0) {
        	//flip around the duck's center
        	tx.scale(-1,  1);
        	//move it back so it stays in the right place after flipping
        	tx.translate(-img.getWidth(null), 0); */
        //}
        g2.drawImage(img, tx, null);      // Actually draw the duck image
        update();
        init(x,y);
        
		dog.paint(g);
        
        //create a green hitbox
        //if(debugging) {
        //g.setColor(Color.green);
        //g.drawRect((int) x+50,  (int) y+40, 100, 100);
        

        }
    //}
    
    // Setup method: places the duck at (a, b) and scales it
    private void init(double a, double b) {
        tx.setToTranslation(a, b);        // Move the image to position (a, b)
        tx.scale(scaleX, scaleY);         // Resize the image using the scale variables
    }

    // Loads an image from the given file path
    private Image getImage(String path) {
        Image tempImage = null;
        try {
            URL imageURL = Duck.class.getResource(path);
            tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tempImage;
    }

    // NEW: Method to set scale
    public void setScale(double sx, double sy) {
        scaleX = sx;
        scaleY = sy;
        init(x, y);  // Keep current location
    }

    // NEW: Method to set location
    public void setLocation(double newX, double newY) {
        x = newX;
        y = newY;
        init(x, y);  // Keep current scale
    }
    //Collision and collision logic
    public boolean checkCollision(int mX, int mY) {
    	
    	//represent the mouse as a rectangle
    	Rectangle mouse = new Rectangle(mX, mY, 50, 50);
    	
    	//represent this object as a Rectangle
    	Rectangle thisObject = new Rectangle((int)x+50, (int)y+40, 100, 100);
    	
    	if(mouse.intersects(thisObject)) {
     		//logic if colliding   		
    		vx = 0;
    		vy = 13;
    		img = stunned;
    		
    		this.dog.x = (int) x;
    		this.dog.y = 430;
    		this.dog.vy = -3;
    		
		
    		return true;
    	}else {
    		
    		return false;
    	}
    }
    
   
}
