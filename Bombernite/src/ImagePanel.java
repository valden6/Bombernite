import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ImagePanel extends JPanel {

	
	private static final long serialVersionUID = 1L;
	 
    /**
     * @param args
     */
	private static final double SCALE_FACTOR = .05;
    private static final int PADDING = 10;
    private int scaleW, scaleH;
	private BufferedImage buffImg;
    private Image imgIcon;
	
	
    
 
    public ImagePanel(BufferedImage img){
    	scaleW = (int)(SCALE_FACTOR * img.getWidth());
		scaleH = (int)(SCALE_FACTOR * img.getHeight());
		JLabel jLabel = new JLabel(new ImageIcon(img));
//            this.add(jLabel);


        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });
    	
        this.buffImg = img;

    }
    
    public ImagePanel(Image img){
    	BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
    	scaleW = (int)(SCALE_FACTOR * bimage.getWidth());
		scaleH = (int)(SCALE_FACTOR * bimage.getHeight());
		JLabel jLabel = new JLabel(new ImageIcon(bimage));



        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });
    	
        this.imgIcon = img;
    }
     
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	if(this.buffImg != null) {

            g.drawImage(buffImg, 0, 0, this.getWidth(), this.getHeight(), this);
    	}
    	else if(imgIcon != null) {
    		g.drawImage(imgIcon, 0, 0, this.getWidth(), this.getHeight(), this);
    	}
    	else {
    		System.out.print("Pas d'image défini!");
    	}
    }
}
