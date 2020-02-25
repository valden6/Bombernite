import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


public class BomberniteControler extends Observable implements Observer{

	private BomberniteGUIData bomberniteGUIData;
	private BomberniteModel bomberniteModel;

	public BomberniteModel getBomberniteModel() {
		return bomberniteModel;
	}

	public BomberniteControler(BomberniteGUIData bomberniteGUIData, BomberniteModel bomberniteModel) {
		this.bomberniteGUIData = bomberniteGUIData;
		this.bomberniteModel = bomberniteModel;
		this.bomberniteModel.addObserver(this);
	}

	/**
	 * @return the picture
	 */
	public BufferedImage getPicture(int i, int j) {
		Entity objet = bomberniteModel.findEntity(new Coord(i,j));
		if(!objet.getAlive()){
			objet = new Entity(true, new Coord(i,j),TypeEntity.EMPTY,true,true);
		}
		return this.bomberniteGUIData.getPicture(objet);
	}

	public void keyPressed(int keyboardKey) {

		List<Player> players = this.bomberniteModel.getPlayers();

		for(Player player :players) {
			if(keyboardKey == player.getKeyboardKeyBomb()) {
				if(player.hasBomb())
				{
					Bomb bomb = new Bomb(player.getCoord(), player.getRange(), bomberniteModel);
					bomb.addObserver(this);
					bomberniteModel.getBombs().add(bomb);
					player.increaseBomb(bomb);
					player.setTypeEntity(TypeEntity.PLAYERBOMB);
				}
				return; // Pour juste poser une bombe et pas faire tout le reste de l'algo
			}
		}


		Direction direction = null;

		//Savoir quelle direction est indiquée par la touche pressée

		for(Player player :players) {
			if(keyboardKey == player.getKeyboardKeyEast()) {
				direction = Direction.EAST;
				break;
			}
			if(keyboardKey == player.getKeyboardKeyNorth()) {
				direction = Direction.NORTH;
				break;
			}
			if(keyboardKey == player.getKeyboardKeyWest()) {
				direction = Direction.WEST;
				break;
			}
			if(keyboardKey == player.getKeyboardKeySouth()) {
				direction = Direction.SOUTH;
				break;
			}
		}

		if(direction != null) {
			String nameDirection = direction.getName();
			int x = direction.getMoveX();
			int y = direction.getMoveY();

			//Choix du joueur a deplacer en fonction des touches appuyées
			for (Player player : players) {
				//Pour chaque joueur, teste de la touche pressée pour savoir quel joueur a déplacé

				if (player.getAlive() == true) {


					//----------------Case de touche == NORD-----------------
					if (nameDirection.equals(Direction.NORTH.getName())) {
						if (player.getKeyboardKeyNorth() == keyboardKey) {
							//On va tester si la case de destination est crossable
							x = x + player.getX();
							y = y + player.getY();

							Entity objectif = bomberniteModel.findEntity(new Coord(x, y));
                            player.setTypeEntity(TypeEntity.PLAYERNORTH);
							if (objectif.getCrossable()) {
								//Crossable

								if (objectif.getClass().equals(Bonus.class) || objectif.getClass().equals(Malus.class)) {
									if (objectif.getAlive()) {
										((Items) objectif).useItem(player);
									}
									player.move(new Coord(x, y));
								} else {
									player.move(new Coord(x, y));
								}
								break;
							}
						}
					}


					//----------------Case de touche == SUD-----------------
					if (nameDirection.equals(Direction.SOUTH.getName())) {
						if (player.getKeyboardKeySouth() == keyboardKey) {
							//On va tester si la case de destination est crossable
							x = x + player.getX();
							y = y + player.getY();

							Entity objectif = bomberniteModel.findEntity(new Coord(x, y));
                            player.setTypeEntity(TypeEntity.PLAYERSOUTH);
							if (objectif.getCrossable()) {
								//Crossable

								if (objectif.getClass().equals(Bonus.class) || objectif.getClass().equals(Malus.class)) {
									if (objectif.getAlive()) {
										((Items) objectif).useItem(player);
									}
									player.move(new Coord(x, y));
								} else {
									player.move(new Coord(x, y));
								}
								break;
							}
						}
					}


					//----------------Case de touche == OUEST-----------------
					if (nameDirection.equals(Direction.WEST.getName())) {
						if (player.getKeyboardKeyWest() == keyboardKey) {
							//On va tester si la case de destination est crossable
							x = x + player.getX();
							y = y + player.getY();

							Entity objectif = bomberniteModel.findEntity(new Coord(x, y));
                            player.setTypeEntity(TypeEntity.PLAYERWEST);
							if (objectif.getCrossable()) {
								//Crossable

								if (objectif.getClass().equals(Bonus.class) || objectif.getClass().equals(Malus.class)) {
									if (objectif.getAlive()) {
										((Items) objectif).useItem(player);
									}
									player.move(new Coord(x, y));
								} else {
									player.move(new Coord(x, y));
								}
								break;
							}
						}
					}


					//----------------Case de touche == EST-----------------
					if (nameDirection.equals(Direction.EAST.getName())) {
						if (player.getKeyboardKeyEast() == keyboardKey) {
							//On va tester si la case de destination est crossable
							x = x + player.getX();
							y = y + player.getY();

							Entity objectif = bomberniteModel.findEntity(new Coord(x, y));
                            player.setTypeEntity(TypeEntity.PLAYEREAST);
							if (objectif.getCrossable()) {
								//Crossable

								if (objectif.getClass().equals(Bonus.class) || objectif.getClass().equals(Malus.class)) {

									if (objectif.getAlive()) {
										((Items) objectif).useItem(player);
									}
									player.move(new Coord(x, y));
								} else {
									player.move(new Coord(x, y));
								}
								break;
							}
						}
					}
				}
			}
		}
		//System.out.println(bomberniteModel.toString());
	}

	/**
	 * @return the length
	 */
	public int getLength() {
		return this.bomberniteGUIData.getLength();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		this.setChanged();
		notifyObservers();
	}

}
