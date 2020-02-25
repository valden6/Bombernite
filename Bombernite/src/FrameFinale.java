import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameFinale extends JFrame {

    private boolean victory;
	private Bouton buttonRetourMenu = new Bouton("Exit");

    public FrameFinale(boolean end) {
    	buttonRetourMenu.setPreferredSize(new Dimension(100,30));
        JPanel mainPanel =new JPanel();
         JPanel panel1 = new JPanel();
     
         JPanel panel2 = new JPanel();

        this.victory = end;

        this.setSize(200,200);    
        this.setLocationRelativeTo(null);
        mainPanel.setLayout(new GridLayout(2,0));
        
        panel1.setLayout(new FlowLayout());
        panel1.setOpaque(true);
        panel1.setBackground(new Color(0,0,0,0));
        mainPanel.add(panel1);
        
        panel2.setOpaque(true);
        panel2.setBackground(new Color(0,0,0,0));
        panel2.setLayout(new FlowLayout());
        panel2.add(buttonRetourMenu);
        buttonRetourMenu.addActionListener(new BoutonRetourMenuListener());

        mainPanel.add(panel2);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel text = new JLabel();
        if (victory == true) {
        	mainPanel.setBackground(Color.green);

            text.setHorizontalAlignment(JLabel.CENTER);
            text.setVerticalAlignment(JLabel.CENTER);
            text.setFont(new Font("Lucida", Font.PLAIN,20));
            text.setText("Victoire!");
        }
        else {
        	mainPanel.setBackground(Color.red);

            text.setHorizontalAlignment(JLabel.CENTER);
            text.setVerticalAlignment(JLabel.CENTER);
            text.setFont(new Font("Lucida", Font.PLAIN,20));
            text.setText("Defaite!");
        }
        panel1.add(text);
        
        this.setContentPane(mainPanel);
        this.setVisible(true);
    }

}
class BoutonRetourMenuListener implements ActionListener{

    //Redéfinition de la méthode actionPerformed()

    public void actionPerformed(ActionEvent e) {
    	
  	  System.out.println("Go menu");
  	  System.exit(0);
  	
  	/*try {
		Fenetre startingPage = new Fenetre();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	  System.out.println("test");

  	//BomberniteLauncher.startingPage.dispose();

    
    	  */
    
    }
}
