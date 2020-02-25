import java.util.ArrayList;
import java.util.List;

// 5/10/2018 - Guillaume Bozon
// Classe fille permettant de créer un personnage utilisé par un joueur
// Version : 1.2.0

public class Player extends Character{

	// Attribut
	// ------------------------------------------------------------------------------------------
	private int range;
	private int nbBombsMax;
	private int nbBombs;
	private int keyboardKeyNorth;
	private int keyboardKeySouth;
	private int keyboardKeyWest;
	private int keyboardKeyEast;
	private int keyboardKeyBomb;
	private int nbrPlayer;
	private List<Bomb> listBomb;
	private boolean DEBUG = false;


	// Constructeur
	// ------------------------------------------------------------------------------------------
	Player() {
		super(true, new Coord(0,0), TypeEntity.PLAYERSOUTH, true);
		this.range = 1;
		this.nbBombsMax = 1;
		this.nbBombs = 0;
		listBomb = new ArrayList<Bomb>();
	}

	Player(int xInit, int yInit, int nbrPlayer,int listClavier[][], int iD) {
		super(true, new Coord(xInit,yInit), TypeEntity.PLAYERSOUTH, true,iD);
		listBomb = new ArrayList<Bomb>();
		this.nbrPlayer = nbrPlayer;
		switch (this.nbrPlayer) {
		case 1 :
			
			debug("test");

			debug(Integer.toString(listClavier[0][0], 16));
			
			debug(listClavier[0][0]);
			debug(listClavier[0][1]);
			debug(listClavier[0][2]);
			debug(listClavier[0][3]);
			debug(listClavier[0][4]);
			this.keyboardKeyNorth = listClavier[0][0];
			this.keyboardKeySouth = listClavier[0][1];
			this.keyboardKeyWest = listClavier[0][2];
			this.keyboardKeyEast = listClavier[0][3];
			this.keyboardKeyBomb = listClavier[0][4];
			
			
			break;

		case 2:
			debug("joueur2:");
			debug(listClavier[1][0]);
			debug(listClavier[1][1]);
			debug(listClavier[1][2]);
			debug(listClavier[1][3]);
			debug(listClavier[1][4]);
			
			this.keyboardKeyNorth = listClavier[1][0];
			this.keyboardKeySouth = listClavier[1][1];
			this.keyboardKeyWest = listClavier[1][2];
			this.keyboardKeyEast = listClavier[1][3];
			this.keyboardKeyBomb = listClavier[1][4];
			
			
			break;
		case 3:
			debug("joueur3:");
			debug(listClavier[2][0]);
			debug(listClavier[2][1]);
			debug(listClavier[2][2]);
			debug(listClavier[2][3]);
			debug(listClavier[2][4]);
			
			this.keyboardKeyNorth = listClavier[2][0];
			this.keyboardKeySouth = listClavier[2][1];
			this.keyboardKeyWest = listClavier[2][2];
			this.keyboardKeyEast = listClavier[2][3];
			this.keyboardKeyBomb = listClavier[2][4];
			break;
			
			
		case 4:
			debug("joueur4:");
			debug(listClavier[3][0]);
			debug(listClavier[3][1]);
			debug(listClavier[3][2]);
			debug(listClavier[3][3]);
			debug(listClavier[3][4]);
			
			this.keyboardKeyNorth = listClavier[3][0];
			this.keyboardKeySouth = listClavier[3][1];
			this.keyboardKeyWest = listClavier[3][2];
			this.keyboardKeyEast = listClavier[3][3];
			this.keyboardKeyBomb = listClavier[3][4];
			
			
			break;
		}
		this.range = 1;
		this.nbBombsMax = 1;
		this.nbBombs = 0;
	}

	// Méthode
	// ------------------------------------------------------------------------------------------
	
	@Override
	void move(Coord newCoord) {
		if (this.getAlive()){
			debug("\nLe Player c'est déplacé en : " + newCoord);
			this.setCoord(newCoord);
		}
		else {
			debug("\nLe Player ne peut pas se déplacer!");

		}

	}

	public boolean hasBomb()
	{
		List<Bomb> listRemove= new ArrayList<Bomb>();
		for(Bomb bomb : listBomb)
		{
			if(!bomb.getAlive()){listRemove.add(bomb);}
		}
		
		for(Bomb bomb : listRemove)
		{
			listBomb.remove(bomb);
		}
		if(listBomb.size() < nbBombsMax){return true;}
		else{return false;}
	}
	public void increaseBomb(Bomb bomb){
        listBomb.add(bomb);
    }

	public int getKeyboardKeyNorth() {
		return keyboardKeyNorth;
	}

	public int getKeyboardKeySouth() {
		return keyboardKeySouth;
	}

	public int getKeyboardKeyWest() {
		return keyboardKeyWest;
	}

	public int getKeyboardKeyEast() {
		return keyboardKeyEast;
	}

	public int getKeyboardKeyBomb() {
		return keyboardKeyBomb;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public int getNbBombsMax() {
		return nbBombsMax;
	}

	public void setNbBombsMax(int nbBombsMax) {
		this.nbBombsMax = nbBombsMax;
	}
	
	public int getNbBombs() {
		return nbBombs;
	}
	
	void debug (String s)
	{
		if(DEBUG){System.out.println(s);}
	}
	
	void debug (int s)
	{
		if(DEBUG){System.out.println(s);}
	}
}
