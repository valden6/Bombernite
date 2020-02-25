
import java.awt.*;
import javax.swing.*;

public class BomberniteGUIBoard extends JPanel {
	
	private BomberniteControler bomberniteControler;
	private int length ;
	public BomberniteGUIBoard(BomberniteControler bomberniteControler) {
		super();
		this.bomberniteControler = bomberniteControler;
		this.length = bomberniteControler.getLength();

			this.setBackGroudBomberniteBoard();
	}

	private void setBackGroudBomberniteBoard() {
		this.setLayout(new GridLayout(length, length));

		for (int i = 0; i<length; i++){
			for (int j = 0; j<length; j++) {
				this.add( new SquareGUI(this.bomberniteControler.getPicture(j,i)));
			}
		}
	}
}
