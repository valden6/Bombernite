import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class Fenetre extends JFrame {

	private static final long serialVersionUID = 1L;

	/*
	* params arg
	 */
	private int joueur= 0;
	private JFrame option = new JFrame();
	private JFrame jFrameToucheClacvier =new JFrame();

	private Bouton buttonNbJoueur1 = new Bouton("1",true);
    private Bouton buttonNbJoueur2 = new Bouton("2",false);
    private Bouton buttonNbJoueur3 = new Bouton("3",false);
    private Bouton buttonNbJoueur4 = new Bouton("4",false);

    private Bouton buttonNbMob1 = new Bouton("1",true);
    private Bouton buttonNbMob2 = new Bouton("2",false);
    private Bouton buttonNbMob3 = new Bouton("3",false);
    private Bouton buttonIaFaible =new Bouton("Facile",true);
    private Bouton buttonIaMoyen =new Bouton("Moyen",false);
    private Bouton buttonIaDifficile =new Bouton("Difficile",false);
    private Bouton buttonPetiteMap = new Bouton("Petite",true);
    private Bouton buttonMoyenneMap = new Bouton("Moyenne",false);
    private Bouton buttonGrandeMap = new Bouton("Grande",false);
    private Bouton sonOn = new Bouton("On",true);
    private Bouton sonOff = new Bouton("Off",false);
    private Bouton jouerEnLigne = new Bouton("Jouer en ligne",false);
    private Bouton buttonClavier = new Bouton("Configurer le clavier",false);
    private Bouton buttonclavierJ1= new Bouton ("J1");
    private Bouton buttonclavierJ2= new Bouton ("J2");
    private Bouton buttonclavierJ3= new Bouton ("J3");
    private Bouton buttonclavierJ4= new Bouton ("J4");
    private Bouton buttonOkClavier= new Bouton ("OK");
    private Bouton buttonOkOption= new Bouton ("OK");    
    private JPanel mainPanel =new JPanel();
    private JPanel panel1 = new JPanel();
    private JPanel panel1b = new JPanel();
    private JPanel panel1c = new JPanel();
    private JPanel panel2 = new JPanel();
    private JPanel panel3 = new JPanel();
    private JPanel panel4 = new JPanel();
    private JPanel panel5 = new JPanel();
    private JPanel panel6 = new JPanel();
    private boolean musiqueIsOn = true;
      
    TextField textFieldHaut =new TextField();
    TextField textFieldBas =new TextField();
    TextField textFieldGauche =new TextField();
    TextField textFieldDroite =new TextField();
    TextField textFieldBombe =new TextField();
      
    private JLabel nbJoueur = new JLabel();
    private JLabel nbMob = new JLabel();
    private JLabel Difficulte = new JLabel();
    private JLabel tailleMap = new JLabel();
    private JLabel son = new JLabel();    
    private JLabel clavier =new JLabel();

    
    private Audio audioSon = new Audio();
	Thread musiqueThread =new Thread(audioSon);
	
	
	private int listClavier[][]= {
			{90,83,81,68,32},
			{104,101,100,102,96},
			{73,75,74,76,79},
			{84,71,70,72,89},
			} ;
	

    public Fenetre() throws IOException{
    	
    	//Audio son = new Audio();
        
    	audioSon.start();
    	

    	
        
    	Bouton buttonCommencer = new Bouton("JOUER");
        Bouton buttonQuitter = new Bouton("QUITTER");
        Bouton buttonOption = new Bouton("OPTION");

		JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
    	
    	this.setTitle("Bombernite");
    	this.setSize(100*13,100*13);
        this.setLocationRelativeTo(null);        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        BufferedImage image = ImageIO.read(new File("Graphismes/fond1.gif")); 
        ImagePanel mainPanel = new ImagePanel(image);
        mainPanel.setLayout(new GridLayout(4,0));
        this.setContentPane(mainPanel);
        this.setVisible(true);
        
        panel1.setLayout(new FlowLayout());
        panel1.setOpaque(true);
        panel1.setBackground(new Color(0,0,0,0));
        mainPanel.add(panel1);
        
        panel2.setOpaque(true);
        panel2.setBackground(new Color(0,0,0,0));
        panel2.setLayout(new FlowLayout());  
     
        //Bouton buttonCommencer = new Bouton("JOUER");
        buttonCommencer.setPreferredSize(new Dimension(300,50));
        buttonCommencer.addActionListener(new BoutonJouerListener());

        //buttonCommencer.setBackground(Color.green);
    
        buttonQuitter.setPreferredSize(new Dimension(300,50));
        //buttonQuitter.setBackground(Color.green);
        buttonQuitter.addActionListener(new BoutonQuitterListener());
        
        panel2.add(buttonCommencer);
        panel2.add(buttonQuitter);
    
        mainPanel.add(panel2);
        
        //JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout());
        panel3.setOpaque(true);
        //Bouton buttonOption = new Bouton("OPTION");
        //buttonOption.setBackground(Color.green);
        
        buttonOption.setPreferredSize(new Dimension(200,50));
        buttonOption.addActionListener(new BoutonOptionListener());

        
        panel3.add(buttonOption);
        panel3.setBackground(new Color(0,0,0,0));
        
        mainPanel.add(panel3);
        
        
        //JPanel panel4 = new JPanel();
        panel4.setLayout(new FlowLayout());
        panel4.setOpaque(true);
        panel4.setBackground(new Color(0,0,0,0));
        mainPanel.add(panel4);
		 //TODO Mettre un thread d'une classe musique a lancer
        /*
        JFXPanel jfxPanel = new JFXPanel();
        jFrame.add(jfxPanel);
        String bip = "Graphismes/8-bit.mp3";
        Media hit = new Media(new File(bip).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();
         */

    }
    
    int charToIntTouche(char Char)
    {
    	int res;
    	
		
    	switch (Char){
    	case ' ' :
    		res=32;
    		break;
    	case '0' :
    		res= 96;
    		break;
    	case '1' :
    		res=97;
    		break;
    	case '2' :
    		res=98;
    		break;
    	case '3' :
    		res=99; 
    		break;
    	case '4' :
    		res=100;
    		break;
    	case '5' :
    		res=101;
    		break;
    	case '6' :
    		res=102;
    		break;
    	case '7' :
    		res=103;
    		break;
    	case '8' :
    		res=104;
    		break;
    	case '9' :
    		res=105;
    		break;
    	case 'a' :
    		res=65;
    		break;
   		
    	case 'b' :
    		res=66;
    		break;
    	case 'c' :
    		res=67;
    		break;
    	case 'd' :
    		res=68;
    		break;
    	case 'e' :
    		res=69;
    		break;
    	case 'f' :
    		res=70;
    		break;
    	case 'g' :
    		res=71;
    		break;
    	case 'h' :
    		res=72;
    		break;
    	case 'i' :
    		res=73;
    		break;
    	case 'j' :
    		res=74;
    		break;
    	case 'k' :
    		res=75;
    		break;
    	case 'l' :
    		res=76;
    		break;
    	case 'm' :
    		res=77;
    		break;
    	case 'n' :
    		res=78;
    		break;
    	case 'o' :
    		res=79;
    		break;
    	case 'p' :
    		res=80;
    		break;
    	case 'q' :
    		res=81;
    		break;
    	case 'r' :
    		res=82;
    		break;
    	case 's' :
    		res=83;
    		break;
    	case 't' :
    		res=84;
    		break;
    	case 'u' :
    		res=85;
    		break;
    	case 'v' :
    		res=86;
    		break;
    	case 'w' :
    		res=87;
    		break;
    	case 'x' :
    		res=88;
    		break;
    	case 'y' :
    		res=89;
    		break;
    	case 'z' :
    		res=90;
    		break;
    	case 'A' :
    		res=65;
    		break;
    	default:
    		res = 27;
    		break;
 		    		
    	}
    	
    	
    	
		return res;
    	
    	
    }
    char IntToucheTochar(int Int)
    {
    	char res;
    	
		
    	switch (Int){
    	case 32 :
    		res=' ';
    		break;
    	case 96:
    		res= '0';
    		break;
    	case 97 :
    		res='1';
    		break;
    	case 98 :
    		res='2';
    		break;
    	case 99 :
    		res='3'; 
    		break;
    	case 100 :
    		res='4';
    		break;
    	case 101 :
    		res='5';
    		break;
    	case 102 :
    		res='6';
    		break;
    	case 103 :
    		res='7';
    		break;
    	case 104 :
    		res='8';
    		break;
    	case 105:
    		res='9';
    		break;
    	case 65 :
    		res='a';
    		break;   		
    	case  66:
    		res='b';
    		break;
    	case 67 :
    		res='c';
    		break;
    	case 68 :
    		res='d';
    		break;
    	case  69:
    		res='e';
    		break;
    	case 70 :
    		res='f';
    		break;
    	case 71 :
    		res='g';
    		break;
    	case 72 :
    		res='h';
    		break;
    	case 73 :
    		res='i';
    		break;
    	case 74 :
    		res='j';
    		break;
    	case 75 :
    		res='k';
    		break;
    	case 76 :
    		res='l';
    		break;
    	case 77 :
    		res='m';
    		break;
    	case 78 :
    		res='n';
    		break;
    	case 79 :
    		res='o';
    		break;
    	case 80 :
    		res='p';
    		break;
    	case 81 :
    		res='q';
    		break;
    	case 82 :
    		res='r';
    		break;
    	case 83 :
    		res='s';
    		break;
    	case 84 :
    		res='t';
    		break;
    	case 85 :
    		res='u';
    		break;
    	case 86 :
    		res='v';
    		break;
    	case 87 :
    		res='w';
    		break;
    	case 88 :
    		res='x';
    		break;
    	case 89 :
    		res='y';
    		break;
    	case 90 :
    		res='z';
    		break;
    	
    		
    	default:
    		res = 78;
    		break;
 		    		
    	}
    	
    	
    	
		return res;
    	
    	
    }
  //Classe écoutant notre  bouton quitter

    class BoutonQuitterListener implements ActionListener{

      //Redéfinition de la méthode actionPerformed()

      public void actionPerformed(ActionEvent arg0) {

          musiqueIsOn = false;
    	  System.out.println("On quitte");
    	  System.exit(0);
      }

    }

        

    //Classe écoutant notre bouton jouer

    class BoutonJouerListener implements ActionListener{

      //Redéfinition de la méthode actionPerformed()

      public void actionPerformed(ActionEvent e) {

          musiqueIsOn = false;
    	  System.out.println("On joue");
    	  
    	  try{
  			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
  		}
  		catch(Exception e1){}
  		
  		
  		
 
  		
  		BomberniteModel bomberniteModel = new BomberniteModel(typeBoard.CLASSIC,BomberniteLauncher.playerNb,BomberniteLauncher.mobNb,BomberniteLauncher.lengthBoard,BomberniteLauncher.Difficulte, listClavier);
  		
  		// Objet de données qui définit les couleurs des cases, des pièces sur les cases, la taille du damier
  		BomberniteGUIData bomberniteGUIData = new BomberniteGUIData(BomberniteLauncher.lengthBoard);
  		
  		BomberniteControler bomberniteControler = new BomberniteControler(bomberniteGUIData, bomberniteModel);
  		
  		JFrame window = new BomberniteGUI(bomberniteControler);
  		BomberniteLauncher.setFrameConfig(window, "Bombernite Battle royale Alpha 1.0.0");
  		
  		window.setVisible(true);
  		BomberniteLauncher.startingPage.dispose();

      }

    }      
    
  //Classe écoutant notre bouton jouer

    class BoutonOptionListener implements ActionListener{

      //Redéfinition de la méthode actionPerformed()

      public void actionPerformed(ActionEvent e) {

    	  System.out.println("On va dans option");    	  

    	  option.setTitle("Bombernite");
    	  option.setSize(600,500);
    	  option.setLocationRelativeTo(null);

    	  mainPanel.setBackground(Color.LIGHT_GRAY);
    	  mainPanel.setLayout(new GridLayout(8,0));
    	  option.setContentPane(mainPanel);
    	  option.setVisible(true);

          
          panel1.setOpaque(true);
          panel1b.setOpaque(true);
          panel1c.setOpaque(true);
          panel2.setOpaque(true);
          panel3.setOpaque(true);
          panel4.setOpaque(true);
          panel5.setOpaque(true);
          panel6.setOpaque(true);

          
          
          panel1.setBackground(new Color(0,0,0,0));
          panel1b.setBackground(new Color(0,0,0,0));
          panel1c.setBackground(new Color(0,0,0,0));
          panel2.setBackground(new Color(0,0,0,0));
          panel3.setBackground(new Color(0,0,0,0));
          panel4.setBackground(new Color(0,0,0,0));
          panel5.setBackground(new Color(0,0,0,0));
          panel6.setBackground(new Color(0,0,0,0));

          
          panel1.setLayout(new FlowLayout());
          panel1b.setLayout(new FlowLayout());  
          panel1c.setLayout(new FlowLayout()); 
          panel2.setLayout(new FlowLayout());  
          panel3.setLayout(new FlowLayout());  
          panel4.setLayout(new FlowLayout());  
          panel5.setLayout(new FlowLayout());  
          panel6.setLayout(new FlowLayout());

        
          
          buttonNbJoueur1.setPreferredSize(new Dimension(30,30));
          buttonNbJoueur2.setPreferredSize(new Dimension(30,30));
          buttonNbJoueur3.setPreferredSize(new Dimension(30,30));
          buttonNbJoueur4.setPreferredSize(new Dimension(30,30));
          buttonNbMob1.setPreferredSize(new Dimension(30,30));
          buttonNbMob2.setPreferredSize(new Dimension(30,30));
          buttonNbMob3.setPreferredSize(new Dimension(30,30));
          buttonPetiteMap.setPreferredSize(new Dimension(130,30));
          buttonMoyenneMap.setPreferredSize(new Dimension(130,30));
          buttonGrandeMap.setPreferredSize(new Dimension(130,30));
          jouerEnLigne.setPreferredSize(new Dimension(300,30));
          buttonIaFaible.setPreferredSize(new Dimension(130,30));
          buttonIaMoyen.setPreferredSize(new Dimension(130,30));
          buttonIaDifficile.setPreferredSize(new Dimension(130,30));
          buttonClavier.setPreferredSize(new Dimension(300,30));
          buttonclavierJ1.setPreferredSize(new Dimension(30,30));
          buttonclavierJ2.setPreferredSize(new Dimension(30,30));
          buttonclavierJ3.setPreferredSize(new Dimension(30,30));
          buttonclavierJ4.setPreferredSize(new Dimension(30,30));
          buttonOkOption.setPreferredSize(new Dimension(70,30));


          
          nbJoueur.setText("nb joueur");
          nbMob.setText("nb mob");
          Difficulte.setText("Difficulté");
          tailleMap.setText("Taille de la map");
          son.setText("Musique");
          clavier.setText("Parametre clavier");

          jouerEnLigne.addActionListener(new BoutonJouerEnLigneListener());
          buttonNbJoueur1.addActionListener(new BoutonNbJoueurListener());
          buttonNbJoueur2.addActionListener(new BoutonNbJoueurListener());
          buttonNbJoueur3.addActionListener(new BoutonNbJoueurListener());
          buttonNbJoueur4.addActionListener(new BoutonNbJoueurListener());
          buttonNbMob1.addActionListener(new BoutonNbMobListener());
          buttonNbMob2.addActionListener(new BoutonNbMobListener());
          buttonNbMob3.addActionListener(new BoutonNbMobListener());     
          buttonPetiteMap.addActionListener(new BoutonTailleMapListener());
          buttonMoyenneMap.addActionListener(new BoutonTailleMapListener());
          buttonGrandeMap.addActionListener(new BoutonTailleMapListener());
          sonOn.addActionListener(new BoutonSonListener());
          sonOff.addActionListener(new BoutonSonListener());                    
          buttonIaFaible.addActionListener(new BoutonDifficulteListener());
          buttonIaMoyen.addActionListener(new BoutonDifficulteListener());
          buttonIaDifficile.addActionListener(new BoutonDifficulteListener());
          buttonclavierJ1.addActionListener(new BoutonClavierListener());
          buttonclavierJ2.addActionListener(new BoutonClavierListener());
          buttonclavierJ3.addActionListener(new BoutonClavierListener());
          buttonclavierJ4.addActionListener(new BoutonClavierListener());
          buttonOkOption.addActionListener(new BoutonOkOptionListener());


          


          panel1.add(nbJoueur);
          panel1.add(buttonNbJoueur1);
          panel1.add(buttonNbJoueur2);
          panel1.add(buttonNbJoueur3);
          panel1.add(buttonNbJoueur4);
          
          panel1b.add(nbMob);
          panel1b.add(buttonNbMob1);
          panel1b.add(buttonNbMob2);
          panel1b.add(buttonNbMob3);
          
          panel1c.add(Difficulte);
          panel1c.add(buttonIaFaible);
          panel1c.add(buttonIaMoyen);
          panel1c.add(buttonIaDifficile);
         
         panel2.add(tailleMap);
         panel2.add(buttonPetiteMap);
         panel2.add(buttonMoyenneMap);
         panel2.add(buttonGrandeMap);

         panel3.add(son);
         panel3.add(sonOn);
         panel3.add(sonOff);

         panel4.add(jouerEnLigne);
         
         panel5.add(clavier);
         panel5.add(buttonclavierJ1);
         panel5.add(buttonclavierJ2);
         panel5.add(buttonclavierJ3);
         panel5.add(buttonclavierJ4);
         panel6.add(buttonOkOption);





          mainPanel.add(panel1);
          mainPanel.add(panel1b);    
          mainPanel.add(panel1c);
          mainPanel.add(panel2);
          mainPanel.add(panel3);
          mainPanel.add(panel4);
          mainPanel.add(panel5);
          mainPanel.add(panel6);

          
      }
    }    
    
    
    class BoutonTailleMapListener implements ActionListener{

        //Redéfinition de la méthode actionPerformed()

        public void actionPerformed(ActionEvent e) {
        	
      	  System.out.println("Taille map");
	      	if(e.getSource() == buttonPetiteMap)
	      	{
	        	  System.out.println("Petite");
	        	  BomberniteLauncher.lengthBoard =11;
	        	  buttonPetiteMap.cliquer= true;
	        	  buttonMoyenneMap.cliquer= false;
	        	  buttonGrandeMap.cliquer= false;
		    	  buttonPetiteMap.repaintB();
		    	  buttonMoyenneMap.repaintB();
		    	  buttonGrandeMap.repaintB();
	
	      	}
	      	else if (e.getSource() == buttonMoyenneMap)
	      	{
	      	  System.out.println("Moyenne");
        	  BomberniteLauncher.lengthBoard =15;
        	  buttonPetiteMap.cliquer= false;
        	  buttonMoyenneMap.cliquer= true;
        	  buttonGrandeMap.cliquer= false;
	    	  buttonPetiteMap.repaintB();
	    	  buttonMoyenneMap.repaintB();
	    	  buttonGrandeMap.repaintB();
	
	    	}
	      	else if(e.getSource() == buttonGrandeMap)
	      	{
	        	  System.out.println("Grande");
	        	  BomberniteLauncher.lengthBoard =21;
	        	  buttonPetiteMap.cliquer= false;
	        	  buttonMoyenneMap.cliquer= false;
	        	  buttonGrandeMap.cliquer= true;
		    	  buttonPetiteMap.repaintB();
		    	  buttonMoyenneMap.repaintB();
		    	  buttonGrandeMap.repaintB();

	
	      	}
	      	else
	      	{
	      	  System.out.println("Erreur taille map");
	
	      	}
        }
    }
    
    class BoutonClavierListener implements ActionListener{

        //Redéfinition de la méthode actionPerformed()

        public void actionPerformed(ActionEvent e) {
        	
        	char haut,bas,gauche,droite,bombe;
        	String stringConvChar = "";
        	
      	  System.out.println("On modifie touche clavier");
	      	 JPanel mainClavierPanel =new JPanel();
	         JPanel panelClavier1 = new JPanel();	        
	         JPanel panelClavier2 = new JPanel();
	         JPanel panelClavier3 = new JPanel();
	         JPanel panelClavier4 = new JPanel();
	         JPanel panelClavier5 = new JPanel();
	         JPanel panelClavier6 = new JPanel();

	         
	         JLabel jLabelHaut = new JLabel();
	         JLabel jLabelBas = new JLabel();
	         JLabel jLabelGauche = new JLabel();
	         JLabel jLabelDroite = new JLabel();
	         JLabel jLabelBombe = new JLabel();
	         
	     

	         textFieldHaut.setPreferredSize(new Dimension(30,30));
	         textFieldBas.setPreferredSize(new Dimension(30,30));
	         textFieldGauche.setPreferredSize(new Dimension(30,30));
	         textFieldDroite.setPreferredSize(new Dimension(30,30));
	         textFieldBombe.setPreferredSize(new Dimension(30,30));
	         
	         jLabelHaut.setText("Haut:");
	         jLabelBas.setText("Bas:");
	         jLabelGauche.setText("Gauche:");
	         jLabelDroite.setText("Droite:");
	         jLabelBombe.setText("Bombe:");


      	jFrameToucheClacvier.setTitle("Bombernite");
      	jFrameToucheClacvier.setSize(400,200);
      	jFrameToucheClacvier.setLocationRelativeTo(null);
      	mainClavierPanel.setBackground(Color.LIGHT_GRAY);

      	mainClavierPanel.setLayout(new GridLayout(6,0));
      	jFrameToucheClacvier.setContentPane(mainClavierPanel);
      	
      	panelClavier1.setOpaque(true);        
      	panelClavier2.setOpaque(true);
      	panelClavier3.setOpaque(true);
      	panelClavier4.setOpaque(true);
      	panelClavier5.setOpaque(true);
      	panelClavier6.setOpaque(true);

        
      	panelClavier1.setBackground(new Color(0,0,0,0));       
      	panelClavier2.setBackground(new Color(0,0,0,0));
      	panelClavier3.setBackground(new Color(0,0,0,0));
      	panelClavier4.setBackground(new Color(0,0,0,0));
      	panelClavier5.setBackground(new Color(0,0,0,0));
      	panelClavier6.setBackground(new Color(0,0,0,0));

        
      	panelClavier1.setLayout(new FlowLayout());
      	panelClavier2.setLayout(new FlowLayout());
      	panelClavier3.setLayout(new FlowLayout());  
      	panelClavier4.setLayout(new FlowLayout());  
      	panelClavier5.setLayout(new FlowLayout());
      	panelClavier6.setLayout(new FlowLayout());

      	
      	panelClavier1.add(jLabelHaut);
      	panelClavier2.add(jLabelBas);
      	panelClavier3.add(jLabelGauche);
      	panelClavier4.add(jLabelDroite);
      	panelClavier5.add(jLabelBombe);
      	
      	panelClavier1.add(textFieldHaut);
      	panelClavier2.add(textFieldBas);
      	panelClavier3.add(textFieldGauche);
      	panelClavier4.add(textFieldDroite);
      	panelClavier5.add(textFieldBombe);
      	panelClavier6.add(buttonOkClavier);
        buttonOkClavier.addActionListener(new BoutonOkListener());

      	
      	
      	mainClavierPanel.add(panelClavier1);
      	mainClavierPanel.add(panelClavier2);
      	mainClavierPanel.add(panelClavier3);
      	mainClavierPanel.add(panelClavier4);
      	mainClavierPanel.add(panelClavier5);
      	mainClavierPanel.add(panelClavier6);
      	

  	  //mainPanel.setBackground(Color.LIGHT_GRAY);
  	  //mainPanel.setLayout(new GridLayout(7,0));
  	//jFrameToucheClacvier.setContentPane(mainPanel);
  	jFrameToucheClacvier.setVisible(true);
      	  
      	if(e.getSource() == buttonclavierJ1)
	  	{
      		joueur=0;
	    	  System.out.println("J1");
	    	  //haut = (char)listClavier[joueur][0];
	    	  haut= IntToucheTochar(listClavier[joueur][0]);
	    	  stringConvChar+= haut;
	    	  textFieldHaut.setText(stringConvChar);
	    	  System.out.println(haut);
	    	  stringConvChar="";
	    	  
	    	  //bas = (char)listClavier[joueur][1];
	    	  bas= IntToucheTochar(listClavier[joueur][1]);
	    	  System.out.println(bas);
	    	  stringConvChar+= bas;
	    	  textFieldBas.setText(stringConvChar);
	    	  stringConvChar="";
	    	  
	    	  //gauche = (char)listClavier[joueur][2];
	    	  gauche= IntToucheTochar(listClavier[joueur][2]);
	    	  System.out.println(gauche);
	    	  stringConvChar+= gauche;
	    	  textFieldGauche.setText(stringConvChar);
	    	  stringConvChar="";
	    	  
	    	  //droite = (char)listClavier[joueur][3];
	    	  droite= IntToucheTochar(listClavier[joueur][3]);
	    	  System.out.println(droite);
	    	  stringConvChar+= droite;
	    	  textFieldDroite.setText(stringConvChar);
	    	  stringConvChar="";
	    	  
	    	  //bombe = (char)listClavier[joueur][4];
	    	  bombe= IntToucheTochar(listClavier[joueur][4]);
	    	  System.out.println(bombe);
	    	  stringConvChar+= bombe;
	    	  textFieldBombe.setText(stringConvChar);
	    	  stringConvChar="";
	    	  

	    	  
	
	  	}
	  	else if (e.getSource() == buttonclavierJ2)
	  	{
	  	  System.out.println("J2");
	  	joueur=1;
	
	  	System.out.println(listClavier[joueur][0]);
	  	  System.out.println(listClavier[joueur][1]);
	  	  System.out.println(listClavier[joueur][2]);
	  	  System.out.println(listClavier[joueur][3]);
	  	  System.out.println(listClavier[joueur][4]);
	  	//haut = (char)listClavier[joueur][0];
  	  haut= IntToucheTochar(listClavier[joueur][0]);
  	  stringConvChar+= haut;
  	  textFieldHaut.setText(stringConvChar);
  	  System.out.println(haut);
  	  stringConvChar="";
  	  
  	  //bas = (char)listClavier[joueur][1];
	  bas= IntToucheTochar(listClavier[joueur][1]);
  	  System.out.println(bas);
  	  stringConvChar+= bas;
  	  textFieldBas.setText(stringConvChar);
  	  stringConvChar="";
  	  
  	  //gauche = (char)listClavier[joueur][2];
	  gauche= IntToucheTochar(listClavier[joueur][2]);
  	  System.out.println(gauche);
  	  stringConvChar+= gauche;
  	  textFieldGauche.setText(stringConvChar);
  	  stringConvChar="";
  	  
  	  //droite = (char)listClavier[joueur][3];
	  droite= IntToucheTochar(listClavier[joueur][3]);
  	  System.out.println(droite);
  	  stringConvChar+= droite;
  	  textFieldDroite.setText(stringConvChar);
  	  stringConvChar="";
  	  
  	  //bombe = (char)listClavier[joueur][4];
  	  bombe= IntToucheTochar(listClavier[joueur][4]);
  	  System.out.println(bombe);
  	  stringConvChar+= bombe;
  	  textFieldBombe.setText(stringConvChar);
  	  stringConvChar="";
	   	
		}
	  	else if(e.getSource() == buttonclavierJ3)
	  	{
		  	  System.out.println("J3");
		  	
		  	joueur=2;
		  	System.out.println(listClavier[joueur][0]);
		  	  System.out.println(listClavier[joueur][1]);
		  	  System.out.println(listClavier[joueur][2]);
		  	  System.out.println(listClavier[joueur][3]);
		  	  System.out.println(listClavier[joueur][4]);
		  	//haut = (char)listClavier[joueur][0];
	    	  haut= IntToucheTochar(listClavier[joueur][0]);
	    	  stringConvChar+= haut;
	    	  textFieldHaut.setText(stringConvChar);
	    	  System.out.println("test");

	    	  System.out.println(haut);
	    	  System.out.println("test");

	    	  stringConvChar="";
	    	  
	    	  //bas = (char)listClavier[joueur][1];
	    	  bas= IntToucheTochar(listClavier[joueur][1]);
	    	  System.out.println(bas);
	    	  stringConvChar+= bas;
	    	  textFieldBas.setText(stringConvChar);
	    	  stringConvChar="";
	    	  
	    	  //gauche = (char)listClavier[joueur][2];
	    	  gauche= IntToucheTochar(listClavier[joueur][2]);
	    	  System.out.println(gauche);
	    	  stringConvChar+= gauche;
	    	  textFieldGauche.setText(stringConvChar);
	    	  stringConvChar="";
	    	  
	    	  //droite = (char)listClavier[joueur][3];
	    	  droite= IntToucheTochar(listClavier[joueur][3]);
	    	  System.out.println(droite);
	    	  stringConvChar+= droite;
	    	  textFieldDroite.setText(stringConvChar);
	    	  stringConvChar="";
	    	  
	    	  //bombe = (char)listClavier[joueur][4];
	    	  bombe= IntToucheTochar(listClavier[joueur][4]);
	    	  System.out.println(bombe);
	    	  stringConvChar+= bombe;
	    	  textFieldBombe.setText(stringConvChar);
	    	  stringConvChar="";

	  	}
	  		
  		else if(e.getSource() == buttonclavierJ4)
	  	{
		  


		  	joueur=3;
		  	System.out.println(listClavier[joueur][0]);
		  	  System.out.println(listClavier[joueur][1]);
		  	  System.out.println(listClavier[joueur][2]);
		  	  System.out.println(listClavier[joueur][3]);
		  	  System.out.println(listClavier[joueur][4]);
		  	//haut = (char)listClavier[joueur][0];
	    	  haut= IntToucheTochar(listClavier[joueur][0]);
		  	  System.out.println(listClavier[joueur][0]);

	    	  stringConvChar+= haut;
	    	  textFieldHaut.setText(stringConvChar);
	    	  System.out.println(haut);
	    	  stringConvChar="";
	    	  
	    	  //bas = (char)listClavier[joueur][1];
	    	  bas= IntToucheTochar(listClavier[joueur][1]);

	    	  System.out.println(bas);
	    	  stringConvChar+= bas;
	    	  textFieldBas.setText(stringConvChar);
	    	  stringConvChar="";
	    	  
	    	  //gauche = (char)listClavier[joueur][2];
	    	  gauche= IntToucheTochar(listClavier[joueur][2]);

	    	  System.out.println(gauche);
	    	  stringConvChar+= gauche;
	    	  textFieldGauche.setText(stringConvChar);
	    	  stringConvChar="";
	    	  
	    	  //droite = (char)listClavier[joueur][3];
	    	  droite= IntToucheTochar(listClavier[joueur][3]);
	    	  System.out.println(droite);
	    	  stringConvChar+= droite;
	    	  textFieldDroite.setText(stringConvChar);
	    	  stringConvChar="";
	    	  
	    	  //bombe = (char)listClavier[joueur][4];
	    	  bombe= IntToucheTochar(listClavier[joueur][4]);
	    	  System.out.println(bombe);
	    	  stringConvChar+= bombe;
	    	  textFieldBombe.setText(stringConvChar);
	    	  stringConvChar="";
	  		
		  	 
	  	}
  		else
  		{
		  	  System.out.println("Erreur clavier");

  		}
		  			
  	
        }
    }
    
    class BoutonOkListener implements ActionListener{

        //Redéfinition de la méthode actionPerformed()

        public void actionPerformed(ActionEvent e) {
        	String stringToChar="";
        	char charTouche ;
        	
        	
        	stringToChar =textFieldHaut.getText();
        	charTouche = stringToChar.charAt(0);
		  	System.out.println("test");
		  	//charToIntTouche(charTouche);
		  
			//int result = Integer.parseInt(Integer.toString((int)charTouche, 16));			
		  	//listClavier[joueur][0]=Integer.parseInt(Integer.toString((int)charTouche, 16));
		  listClavier[joueur][0]=charToIntTouche(charTouche);
		  	System.out.println(listClavier[joueur][0]);
		  	System.out.println("test");


		  	
		  	System.out.println((int)charTouche);
		  	
		  	stringToChar =textFieldBas.getText();
        	charTouche = stringToChar.charAt(0);
		  	System.out.println(charTouche);
			  listClavier[joueur][1]=charToIntTouche(charTouche);
		  	
		  	stringToChar =textFieldGauche.getText();
        	charTouche = stringToChar.charAt(0);
		  	System.out.println(charTouche);
			  listClavier[joueur][2]=charToIntTouche(charTouche);
        	
		  	stringToChar =textFieldDroite.getText();
        	charTouche = stringToChar.charAt(0);
		  	System.out.println(charTouche);
			  listClavier[joueur][3]=charToIntTouche(charTouche);
		  	
		  	stringToChar =textFieldBombe.getText();
        	charTouche = stringToChar.charAt(0);
		  	System.out.println(charTouche);
			  listClavier[joueur][4]=charToIntTouche(charTouche);
		  	
		  	//System.out.println(listClavier[0][0]);

		  	
        	jFrameToucheClacvier.dispose();
        	
        	
        	}
        }
    class BoutonJouerEnLigneListener implements ActionListener{

        //Redéfinition de la méthode actionPerformed()

        public void actionPerformed(ActionEvent e) {
        	
      	  System.out.println("On joue en ligne");

        	// mettre truc de Benjamin
        }
    }
    class BoutonOkOptionListener implements ActionListener{

        //Redéfinition de la méthode actionPerformed()

        public void actionPerformed(ActionEvent e) {
        	
      	  option.dispose();

        	
        }
    }
    class BoutonNbJoueurListener implements ActionListener{

        //Redéfinition de la méthode actionPerformed()

        public void actionPerformed(ActionEvent e) {
        	
      	  System.out.println("nb Joueur");
      	  
		  	if(e.getSource() == buttonNbJoueur1)
		  	{
		    	  System.out.println("1 Joueur");
		    	  BomberniteLauncher.playerNb = 1;
		    	  
		    	  buttonNbJoueur1.cliquer= true;
		    	  buttonNbJoueur2.cliquer= false;
		    	  buttonNbJoueur3.cliquer= false;
		    	  buttonNbJoueur4.cliquer= false;
		    	  buttonNbJoueur1.repaintB();
		    	  buttonNbJoueur2.repaintB();
		    	  buttonNbJoueur3.repaintB();
		    	  buttonNbJoueur4.repaintB();

		    	  
		
		  	}
		  	else if (e.getSource() == buttonNbJoueur2)
		  	{
		  	  System.out.println("2 Joueurs");
		  	  if (2 + BomberniteLauncher.mobNb <5)
		  	  {
		  		BomberniteLauncher.playerNb = 2;
				  buttonNbJoueur1.cliquer= false;
		    	  buttonNbJoueur2.cliquer= true;
		    	  buttonNbJoueur3.cliquer= false;
		    	  buttonNbJoueur4.cliquer= false;
		    	  buttonNbJoueur1.repaintB();
		    	  buttonNbJoueur2.repaintB();
		    	  buttonNbJoueur3.repaintB();
		    	  buttonNbJoueur4.repaintB();
		  	  }
		  	  else
		  	  {
			  	  System.out.println("Pas possible");

		  	  }
			  
		
		
			}
		  	else if(e.getSource() == buttonNbJoueur3)
		  	{
		  		
		  		if (3 + BomberniteLauncher.mobNb <5)
			  	  {
		    	  System.out.println("3 Joueurs");
		    	  BomberniteLauncher.playerNb = 3;
		    	  System.out.println("test");
		    	  System.out.println(BomberniteLauncher.playerNb);
		    	  System.out.println(BomberniteLauncher.mobNb);
		    	  buttonNbJoueur1.cliquer= false;
		    	  buttonNbJoueur2.cliquer= false;
		    	  buttonNbJoueur3.cliquer= true;
		    	  buttonNbJoueur4.cliquer= false;
		    	  buttonNbJoueur1.repaintB();
		    	  buttonNbJoueur2.repaintB();
		    	  buttonNbJoueur3.repaintB();
		    	  buttonNbJoueur4.repaintB();
			  	}
			  	  else
			  	  {
				  	  System.out.println("Pas possible");

			  	  }
		
		
		  	}
		  	else if(e.getSource() == buttonNbJoueur4)
		  	{
		  		
		    	  System.out.println("4 Jo Joueurs");
		    	  BomberniteLauncher.playerNb = 4;
		    	  buttonNbJoueur1.cliquer= false;
		    	  buttonNbJoueur2.cliquer= false;
		    	  buttonNbJoueur3.cliquer= false;
		    	  buttonNbJoueur4.cliquer= true;
		    	  buttonNbJoueur1.repaintB();
		    	  buttonNbJoueur2.repaintB();
		    	  buttonNbJoueur3.repaintB();
		    	  buttonNbJoueur4.repaintB();
		
		  	}
		  	else
		  	{
		  	  System.out.println("erreur nb joueur");
		
		  	}

   	   
        	
        }
    }
        
    
    class BoutonSonListener implements ActionListener{

        //Redéfinition de la méthode actionPerformed()

        public void actionPerformed(ActionEvent e) {
        	
      	  System.out.println("Son");
	      	if(e.getSource() == sonOn)
	      	{
	      	    //TODO Mettre le on de la classe musique
                /*
                musiqueIsOn = true;
                 */
                System.out.println("On");
	        	  BomberniteLauncher.son =true;
	        	  audioSon.resume();
	        	  sonOn.cliquer= true;
	        	  sonOff.cliquer= false;		      		
		      	  sonOn.repaintB();
		      	  sonOff.repaintB();
		      		
	      	}
	      	else if (e.getSource() == sonOff)
	      	{
                //TODO Mettre le off de la classe musique
                /*
                musiqueIsOn = false;
                 */
	      	  System.out.println("off");
        	  BomberniteLauncher.son =false;
        	  audioSon.suspend();
        	  sonOn.cliquer= false;
        	  sonOff.cliquer= true;		      		
	      	  sonOn.repaintB();
	      	  sonOff.repaintB();
	
	    	}
	      	
	      	else
	      	{
	      	  System.out.println("Erreur son");
	
	      	}
        }
    }
    
    
    
    
    class BoutonDifficulteListener implements ActionListener{

        //Redéfinition de la méthode actionPerformed()

        public void actionPerformed(ActionEvent e) {
        	
      	  System.out.println("Difficulté");
	      	if(e.getSource() == buttonIaFaible)
	      	{
	        	  System.out.println("Faible");
	        	  BomberniteLauncher.Difficulte= 1;
	        	  buttonIaFaible.cliquer= true;
	        	  buttonIaMoyen.cliquer= false;
	        	  buttonIaDifficile.cliquer= false;
	        	  buttonIaFaible.repaintB();
	        	  buttonIaMoyen.repaintB();
	        	  buttonIaDifficile.repaintB();
	
	      	}
	      	else if (e.getSource() == buttonIaMoyen)
	      	{
	      	  System.out.println("Moyenne");
	      	BomberniteLauncher.Difficulte = 2;
	      	buttonIaFaible.cliquer= false;
      	  buttonIaMoyen.cliquer= true;
      	  buttonIaDifficile.cliquer= false;
      	  buttonIaFaible.repaintB();
      	  buttonIaMoyen.repaintB();
      	  buttonIaDifficile.repaintB();
        	

	
	    	}
	      	else if(e.getSource() == buttonIaDifficile)
	      	{
	      		System.out.println("Difficile");
	      		BomberniteLauncher.Difficulte = 3;
	      		buttonIaFaible.cliquer= false;
	      		buttonIaMoyen.cliquer= false;
	      		buttonIaDifficile.cliquer= true;
	      		buttonIaFaible.repaintB();
	      		buttonIaMoyen.repaintB();
	      		buttonIaDifficile.repaintB();
	        	  

	
	      	}
	      	else
	      	{
	      	  System.out.println("Erreur Diffciulté");
	
	      	}
        }
    }
    
    
    class BoutonNbMobListener implements ActionListener{

        //Redéfinition de la méthode actionPerformed()

        public void actionPerformed(ActionEvent e) {
        	
      	  System.out.println("nb Mob");
      	  
		  	if(e.getSource() == buttonNbMob1)
		  	{
		    	  System.out.println("1 Mob");
		    	  BomberniteLauncher.mobNb = 1;
		    	  buttonNbMob1.cliquer= true;
		    	  buttonNbMob2.cliquer= false;
		    	  buttonNbMob3.cliquer= false;
		    	  buttonNbMob1.repaintB();
		    	  buttonNbMob2.repaintB();
		    	  buttonNbMob3.repaintB();
		    	  
		
		  	}
		  	else if (e.getSource() == buttonNbMob2)
		  	{
		  		if (2 + BomberniteLauncher.playerNb <5)
			  	{
				  	  System.out.println("2 Mobs");
					  BomberniteLauncher.mobNb = 2;
					  buttonNbMob1.cliquer= false;
			    	  buttonNbMob2.cliquer= true;
			    	  buttonNbMob3.cliquer= false;
			    	  buttonNbMob1.repaintB();
			    	  buttonNbMob2.repaintB();
			    	  buttonNbMob3.repaintB();
			  	}
		  		else
		  		{
				  	  System.out.println("Pas possible");

		  		}
	    	
		  	  System.out.println(buttonNbMob2.cliquer);

		
		
			}
		  	else if(e.getSource() == buttonNbMob3)
		  	{
		  		if (3 + BomberniteLauncher.playerNb <5)
			  	{
		    	  System.out.println("3 Mobs");
		    	  BomberniteLauncher.mobNb = 3;
		    	  buttonNbMob1.cliquer= false;
		    	  buttonNbMob2.cliquer= false;
		    	  buttonNbMob3.cliquer= true;
		    	  buttonNbMob1.repaintB();
		    	  buttonNbMob2.repaintB();
		    	  buttonNbMob3.repaintB();
			  	}
		  		else
		  		{
				  	  System.out.println("Pas possible");

		  		}
		  	}
		  	
		  	else
		  	{
		  	  System.out.println("erreur nb Mob");
		
		  	}      	
        }
    }
}
