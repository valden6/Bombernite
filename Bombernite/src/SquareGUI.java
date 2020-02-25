


import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class SquareGUI extends JPanel {

	private BufferedImage picture;
	private static final double SCALE_FACTOR = .05;
	private static final int PADDING = 10;
	private int scaleW, scaleH;


	public SquareGUI(BufferedImage picture) {
		this.picture = picture;
		this.setLayout(new BorderLayout());
		scaleW = (int)(SCALE_FACTOR * picture.getWidth());
		scaleH = (int)(SCALE_FACTOR * picture.getHeight());

		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				super.componentResized(e);
			}
		});
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(picture, 0, 0, this.getWidth(), this.getHeight(), this); // see javadoc for more info on the parameters
	}
}