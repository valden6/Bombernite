
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BomberniteGUI extends JFrame implements Observer {

	private JPanel bomberniteBoard ; 	
	private BomberniteControler bomberniteControler;
	private boolean onJoue =true;
	private boolean MonsterEnVie=true;


	public BomberniteGUI( BomberniteControler bomberniteControler) {
		super();
		this.bomberniteControler = bomberniteControler;
		this.bomberniteControler.addObserver(this);
		this.bomberniteBoard = new BomberniteGUIBoard(bomberniteControler);
		this.addKeyListener(this.new KeyBoardListener());

		this.setContentPane(bomberniteBoard);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		this.bomberniteBoard = new BomberniteGUIBoard(bomberniteControler);
		setContentPane(this.bomberniteBoard);
		revalidate();
		List<Player> player =this.bomberniteControler.getBomberniteModel().getPlayers() ;
		List<Monster> monster =this.bomberniteControler.getBomberniteModel().getMonsters() ;
		
		if (onJoue == true)
		{
			System.out.println(monster.size());

			for ( Monster monsters: monster ) {
				if(monsters.getAlive()== false ){
					MonsterEnVie = false;
				}
				else
				{
					MonsterEnVie = true;
					break;
	
				}
			}
			
			if(player.size()== 0 ){
				System.out.println("Perdu");
				onJoue=false;

				FrameFinale frameFinale = new FrameFinale(false);
				


			}
			else if (MonsterEnVie == false )
			{
				System.out.println(monster.size());

				System.out.println("Gagné");
				onJoue=false;

				FrameFinale frameFinale = new FrameFinale(true);

				

			}
			else{
				
				System.out.println("Le jeu continue");

			}
		}
	}

	private class KeyBoardListener implements KeyListener{

		@Override
		public void keyPressed(KeyEvent arg0) {
			
			int keyboardKey = arg0.getKeyCode();
			//System.out.println("Touche : " + keyboardKey);

			BomberniteGUI.this.bomberniteControler.keyPressed(keyboardKey);

			bomberniteBoard = new BomberniteGUIBoard(bomberniteControler);
			setContentPane(bomberniteBoard);
			revalidate();
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

}

