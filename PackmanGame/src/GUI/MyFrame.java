package GUI;
import java.awt.BasicStroke;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import Game.Game;
import Game.MoveManager;
import GameObjects.Fruit;
import GameObjects.GameObjects;
import GameObjects.Packman;
import Geom.Point3D;
import Map.Csvload;
import Map.Csvsave;
import Map.Map;
import Path.Path2KML;
import Path.ShortestPathAlgo;
public class MyFrame extends JFrame implements MouseListener,ComponentListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BufferedImage myImage;
	public BufferedImage theImage;
	String item="";
	String run="";
	double widthAttitude=1;
	double heightAttitude=1;
	private Game game =new Game();
	Iterator<GameObjects> gameIterator =game.getGameIterator();
	String mode="";
	ShortestPathAlgo algo;
	public ShortestPathAlgo getAlgo() {
		return algo;
	}
	public Game getGame() {
		return game;
	}
	/**
	 * constructor for MyFrame
	 * initialize the gui
	 */
	public MyFrame() {
		initializeGui();
		addMouseListener(this);
		addComponentListener(this);
	}
	public  int Width;
	public  int Height;
	/**
	 * initialize the gui
	 */
	private  void initializeGui(){	
		MenuBar menuBar=new MenuBar();
		Menu menu = new Menu("Options");
		//create item 1
		MenuItem item1=new MenuItem("create Packman");
		item1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					theImage = ImageIO.read(new File("C:\\Or\\Moodle\\שנה ב\\תיכנות מונחה עצמים\\מטלות\\מטלה 4\\מטלה 4 להגשה\\Packman.png"));
					item="P";
					mode="notFile";
				}				
				catch (IOException f) {
					System.out.println("cant find the image");
					f.printStackTrace();
				}			
			}		
		});
		//create item2
		MenuItem item2=new MenuItem("create Fruit");
		item2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					theImage = ImageIO.read(new File("C:\\Or\\Moodle\\שנה ב\\תיכנות מונחה עצמים\\מטלות\\מטלה 4\\מטלה 4 להגשה\\Fruit.png"));
					item="F";
					mode="notFile";
				}				
				catch (IOException f) {
					System.out.println("cant find the image");
					f.printStackTrace();
				}			
			}		
		});
		//create on the menu option to start game
		//item string contains sign for fruit or packman
		//mode string contains sign if we load from file or not
		//run string contains the sign if we started the algorithem or not
		MenuItem item3=new MenuItem("start new Game");
		item3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				run="";
				item="";	
				mode="";
				game.getGameArray().clear();
				gameIterator =game.getGameIterator();
				repaint();	


			}		
		});
		menu.add(item1);
		menu.add(item2);
		menu.add(item3);
		//name of menu 2
		Menu menu2=new Menu("Start game from a File");
		//action item4 is the load file 
		MenuItem item4=new MenuItem("Load file");
		item4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Csvload cs=new Csvload();
				game = cs.load();
				mode="load";
				repaint();
			}
		});
		menu2.add(item4);
		//klick on save game will save the game(from menu)
		MenuItem item7=new MenuItem("Save game");
		item7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FileDialog fileDialog = new FileDialog(new Frame(), "Save Game File (CSV)",FileDialog.SAVE);
				fileDialog.setDirectory("/");
				fileDialog.setFile("*.csv");
				fileDialog.setVisible(true);	
				Csvsave cs=new Csvsave();
				cs.save(game,fileDialog.getDirectory()+fileDialog.getFile());		
			}
		});	
		menu2.add(item7);
		//start the algorithem
		MenuItem item8=new MenuItem("Run Game");
		item8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Game Started");
				run="yes";
				algo=new ShortestPathAlgo(game);
				MoveManager mm=new MoveManager(MyFrame.this);
				mm.start();
			
			}	
		});
		menu.add(item8);
		//saving the path of the packmans to kml file 
		//have time stamps of the packman moves
		MenuItem item9 = new MenuItem("Save Path in kml");
		item9.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FileDialog filedialog2 = new FileDialog(new Frame(), "Save Path (KML)",FileDialog.SAVE);
				filedialog2.setDirectory("/");
				filedialog2.setFile("*.kml");
				filedialog2.setVisible(true);	
				Path2KML.types2Kml(algo, filedialog2.getDirectory()+filedialog2.getFile());						
			}			
		});
		menu2.add(item9);
		menuBar.add(menu);
		menuBar.add(menu2);
		this.setMenuBar(menuBar);
		try {
			myImage = ImageIO.read(new File("C:\\Or\\Moodle\\שנה ב\\תיכנות מונחה עצמים\\מטלות\\מטלה 4\\Ex3\\Ariel1.png"));
			Width=myImage.getWidth();
			Height=myImage.getHeight();
		}
		catch (IOException e) {
			System.out.println("cant find the image");
			e.printStackTrace();
		}	
	}
	/**
	 * painting the map,packmans,fruits,paths that calculated by algorithem 
	 */
	int itemWidth=45;
	int  itemHeight=35;
	int x=-1;
	int y=-1;
	public void paint(Graphics image) {
		image.drawImage(myImage, 0, 0,this.getWidth(),this.getHeight(), this);	
		int r=10;
		x = x -(r/2);
		y =y - (r/2);

		synchronized(this.game) { 
			Iterator<GameObjects> current=game.getGameIterator();
			GameObjects currGameOb;
			while(current.hasNext()) {
				currGameOb=current.next();
				//next i times every time			
				if( currGameOb instanceof Packman) {
					try {
						theImage = ImageIO.read(new File("C:\\Or\\Moodle\\שנה ב\\תיכנות מונחה עצמים\\מטלות\\מטלה 4\\מטלה 4 להגשה\\Packman.png"));	
					}
					catch (IOException f) {
						System.out.println("cant find the image");
						f.printStackTrace();
					}
				}
				else {
					try {
						theImage = ImageIO.read(new File("C:\\Or\\Moodle\\שנה ב\\תיכנות מונחה עצמים\\מטלות\\מטלה 4\\מטלה 4 להגשה\\Fruit.png"));	
					}
					catch (IOException f) {
						System.out.println("cant find the image");
						f.printStackTrace();
					}	
				}
				if(mode.equals("load")) {
					image.drawImage(theImage,(int)(currGameOb.getLatitude()*(widthAttitude))-13, (int)(currGameOb.getLontitude()*heightAttitude)-13,(int)(itemWidth*widthAttitude),(int)(itemHeight*heightAttitude), this);
				}
				else {
					image.drawImage(theImage,(int)(currGameOb.getLatitude()*(widthAttitude))-13, (int)(currGameOb.getLontitude()*heightAttitude)-13,(int)(itemWidth*widthAttitude),(int)(itemHeight*heightAttitude), this);
				}
			}
			if(run.equals("yes")) {
				Iterator<Packman> packIter=algo.getPackArray().iterator();
				Packman pack;
				Point3D p1;
				Point3D p2;
				while(packIter.hasNext()) {
					pack=packIter.next();
					for(int i=0;i<pack.getPath().getPathArray().size()-1;i++) {
						p1=pack.getPath().getPathArray().get(i);
						p2=pack.getPath().getPathArray().get(i+1);
						Graphics2D g=(Graphics2D)image;
						g.setStroke(new BasicStroke(5));
						g.drawLine((int)((p1.x()*(widthAttitude))), (int)((p1.y())*(heightAttitude)), (int)((p2.x())*(widthAttitude)),(int)((p2.y())*(heightAttitude)));
					}
				}		
			}		
		}
	}
	/**
	 * using mouse listener to see when the user klicking on the frame and want to creat packman or fruit
	 * we use component listener to see if the user creating game by loading file or by create each packman and each fruit by mouse klicking
	 * if clicked on create fruit so string will be F
	 * if clicked on create packman string will be P
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		x=(int)(e.getX()*(1/widthAttitude));
		y=(int)(e.getY()*(1/heightAttitude));
		//		System.out.println("("+x + "," + y +")"+"point in pixel");
		if(!mode.equals("load")) {
			if(item.equals("P")) {
				Point3D p1=new Point3D(x,y,0);
				Map map=new Map();
				//				System.out.println(p1.x()+" its the x "+p1.y()+"its the y "+p1.z()+" its the z");
				p1=map.Pixels2Polar(p1);
				//				System.out.println(p1.x()+" its the x "+p1.y()+"its the y "+p1.z()+" its the z");
				game.getGameArray().add(new Packman(x,y,0,1,1)); 
				System.out.println("new packman");
				repaint();
			}
			if(item.equals("F")) {
				game.getGameArray().add(new Fruit(x,y,0,1));
				System.out.println("new fruit");
				repaint();
			}
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		if(!mode.equals("load"))
			System.out.println("you can create packman/fruit");
	}
	@Override
	public void mouseExited(MouseEvent e) {
		if(!mode.equals("load"))
			System.out.println("you are not on the map now");
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if(!mode.equals("load"))
			System.out.println("taking point3d");
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		if(!mode.equals("load"))
			System.out.println("craeted packman/fruit");
	}
	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void componentMoved(ComponentEvent e) {	
	}
	/**
	 * widthAttitude is the % of the change in the frame width
	 * heightAttitude is the % of the change in the frame height
	 * we use them to multiple in the x,y and size of packman and fruit 
	 */
	@Override
	public void componentResized(ComponentEvent e) {
		widthAttitude=e.getComponent().getSize().getWidth()/Width;
		heightAttitude=e.getComponent().getSize().getHeight()/Height;
	}
	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
	}
}