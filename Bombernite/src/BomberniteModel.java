import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Timer;


public class BomberniteModel extends Observable implements Observer{

	private Timer timer;
	private int timeBonus = 0;

	private List <Player> players = new ArrayList<>();

	private List <Bomb> bombs = new ArrayList<>();

	private List<Deflagration> deflagrations = new ArrayList<>();

	private List <Items> items = new ArrayList<>();

	private List <Brick> bricks =  new ArrayList<>();

	private List <Wall> walls =  new ArrayList<>();

	private List <Monster> monsters =  new ArrayList<>();
	private int nbMonsterDie =0;
	private int difficulte=1;

	private List <Thread> monstersThread = new ArrayList<>();
	private int nbMonsterThread = 0;

	private int length; // le nombre de lignes et colonnes du damier

	public BomberniteModel(typeBoard board, int nbPlayer,int nbMonster,int length, int difficulte, int listClavier[][] ) {
		this.length = length;
		this.setDifficulte(difficulte);
		
		this.timer = new Timer(1000, new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				timeBonus +=1;
	
				if(timeBonus == 15){
					addItem();
					timeBonus = 0;
				}
			}

		});

		timer.start();

		if(board == typeBoard.CLASSIC){
			for(int k = 1; k<= nbPlayer;k++){
				if(k == 1){
					players.add(new Player(1,1,1,listClavier,k));
				}else if(k == 2){
					players.add(new Player(this.length - 2, this.length - 2,2,listClavier,k));
				}else if(k == 3){
					players.add(new Player(this.length - 2, 1,3,listClavier,k));
				}else if(k == 4){
					players.add(new Player(1, this.length - 2,4,listClavier,k));
				}
			}

			for(int k = 1; k<= nbMonster;k++){
				if(k == 1){
					if(nbPlayer == 1){
						monsters.add(new Monster(this.length - 2, this.length - 3, this));
					}else if(nbPlayer == 2){
						monsters.add(new Monster(this.length - 2, 1, this));
					}else if(nbPlayer == 3){
						monsters.add(new Monster(1, this.length - 2, this));
					}
				}else if(k == 2){
					if(nbPlayer == 1){
						monsters.add(new Monster(this.length - 2, 1, this));
					}else if(nbPlayer == 2){
						monsters.add(new Monster(1, this.length - 2, this));
					}
				}else if(k == 3){
						monsters.add(new Monster(1, this.length - 2, this));
				}
			}

			for(int k = 0; k<= this.length -1;k++){
				walls.add(new Wall(k, 0));
				if(k != 0){
					walls.add(new Wall(this.length-1, k ));
				}
				if(k != this.length-1){
					walls.add(new Wall(k,this.length - 1));
				}
				if(k != 0 && k != this.length-1){
					walls.add(new Wall(0,k));
				}
			}

			for(int x = 1; x<= this.length -2;x++){
				for(int y = 1; y<= this.length -2;y++){
					if(x%2 == 0 && y%2 == 0){
						walls.add(new Wall(x,y));
					}
				}
			}

			for(int x = 1; x<= this.length -2;x++){
				for(int y = 1; y<= this.length -2;y++){
					if(x%2 == 0 && y%2 == 0){

					}else{
						if((x == 1 && y == 1) || (x == 1 && y == 2) || (x == 2 && y == 1) || (x == 1 && y == this.length-2) || (x == 1 && y == this.length-3) || (x == 2 && y == this.length-2) || (x == this.length-2 && y == 1) || (x == this.length-3 && y == 1) ||(x == this.length-2 && y == 2) || (x == this.length-2 && y == this.length-2)|| (x == this.length-3 && y == this.length-2)|| (x == this.length-2 && y == this.length-3)){
						}else{
							bricks.add(new Brick(x,y));

						}
					}
				}
			}
			
			for(Monster monster : monsters) {
				monster.addObserver(this);
				monstersThread.add(new Thread(monster));
				monstersThread.get(nbMonsterThread).start();
				nbMonsterThread++;
			}
		}
		System.out.println(this);
	}



	@Override
	public String toString() {

		int i = 0, j = 0;
		String empty = "-----";
		String player = "--P--";
		String monster = "--M--";
		String wall = "--W--";
		String brick = "--B--";
		String item = "--I--";
		String tabGameFinal = ""; 
		int xPlayer, yPlayer, xMonster, yMonster, xWall, yWall, xBrick, yBrick, xItem, yItem;
		String[][] tabGame = new String[this.length][this.length];

		for (Player playerInit : players) {
			xPlayer = playerInit.getCoord().getX();
			yPlayer = playerInit.getCoord().getY();
			if(playerInit.getAlive()){
				tabGame[xPlayer][yPlayer] = player;
			}
		}

		for (Monster monsterInit : monsters) {
			xMonster = monsterInit.getCoord().getX();
			yMonster = monsterInit.getCoord().getY();
			if(monsterInit.getAlive()){
				tabGame[xMonster][yMonster] = monster;
			}
		}

		for (Wall wallInit : walls) {
			xWall = wallInit.getCoord().getX();
			yWall = wallInit.getCoord().getY();
			if(wallInit.getAlive()){
				tabGame[xWall][yWall] = wall;
			}
		}

		for (Brick brickInit : bricks) {
			xBrick = brickInit.getCoord().getX();
			yBrick = brickInit.getCoord().getY();
			if(brickInit.getAlive()){
				tabGame[xBrick][yBrick] = brick;
			}	
		}

		for (Items itemInit : items) {
			xItem = itemInit.getCoord().getX();
			yItem = itemInit.getCoord().getY();
			if(itemInit.getAlive()){
				tabGame[xItem][yItem] = item;
			}
		}

		for(i = 0; i < this.length; i++){
			for(j = 0; j < this.length; j++){
				if(tabGame[j][i] == null){
					tabGameFinal = tabGameFinal + empty + " ";
				}else{
					tabGameFinal = tabGameFinal + tabGame[j][i] + " ";
				}
			}
			tabGameFinal = tabGameFinal + "\n";
		}
		return tabGameFinal;
	}

	public TypeEntity findTypeEntity(Coord coordonnee){
		TypeEntity pieceRetourne = TypeEntity.EMPTY;
		
		for (Player player : players) {
			if(player.getCoord().equals(coordonnee)){
				pieceRetourne = player.getTypeEntity();
			}
		}
		
		for (Monster monster : monsters) {
			if(monster.getCoord().equals(coordonnee)){
				pieceRetourne = monster.getTypeEntity();
			}
		}
		
		for (Brick brick : bricks) {
			if(brick.getCoord().equals(coordonnee)){
				pieceRetourne = brick.getTypeEntity();
			}
		}
		
		for (Wall wall : walls) {
			if(wall.getCoord().equals(coordonnee)){
				pieceRetourne = wall.getTypeEntity();
			}
		}
		
		for (Items item : items) {
			if(item.getCoord().equals(coordonnee)){
				pieceRetourne = item.getTypeEntity();
			}
		}

        for (Deflagration deflagration : deflagrations) {
            if(deflagration.getCoord().equals(coordonnee)){
                pieceRetourne = deflagration.getTypeEntity();
            }
        }

		for (Bomb bomb : bombs) {
			if(bomb.getCoord().equals(coordonnee)){
					pieceRetourne = bomb.getTypeEntity();
				}
		}
		return pieceRetourne;
	}
	
	public Entity findEntity (Coord coordonnee){
		Entity obj = new Entity(true, coordonnee, TypeEntity.EMPTY, true,true);
		removeDead();
		for (Player player : players) {
			if(player.getCoord().equals(coordonnee)){
				obj = player;
			}
		}
		
		for (Monster monster : monsters) {
			if(monster.getCoord().equals(coordonnee)){
				obj = monster;
			}
		}
		
		for (Brick brick : bricks) {
			if(brick.getCoord().equals(coordonnee)){
				obj = brick;
			}
		}
		
		for (Wall wall : walls) {
			if(wall.getCoord().equals(coordonnee)){
				obj = wall;
			}
		}
		
		for (Items item : items) {
			if(item.getCoord().equals(coordonnee)){
				obj = item;
			}
		}
		List<Deflagration> defl= new ArrayList<Deflagration>();
        for (Deflagration deflagration : deflagrations) {
            if(deflagration.getCoord().equals(coordonnee)){
                defl.add(deflagration);
            }
        }
        
        if(defl.size()>2)
        {
        	obj = stackdeflagration(defl);
        }else if(defl.size() >0){obj = defl.get(0);}
		
		for(Bomb bomb : bombs){
            for(Player player : players) {
                if((bomb.getCoord().equals(coordonnee)) && (bomb.getCoord().equals(player.getCoord()))){
                    obj = player;
                }
            }
            if(obj.getTypeEntity() == TypeEntity.PLAYERBOMB) {
                break;
            }

			if(bomb.getCoord().equals(coordonnee)){
				obj = bomb;
			}
		}
		
		return obj;
	}

private Entity stackdeflagration(List<Deflagration> defl) {
	Deflagration a;
	Deflagration b;
	TypeEntity atype = TypeEntity.DEFLAGRATION; 
	TypeEntity btype = TypeEntity.DEFLAGRATION;
	while(defl.size()>1)
	{	
		a = defl.get(0);
		a.getTypeEntity();
		b = defl.get(1);
		b.getTypeEntity();
		
		if(atype==btype)
		{
			defl.remove(b);
		}else if((atype== TypeEntity.DEFLAGRATION || btype == TypeEntity.DEFLAGRATION)
				||(atype == TypeEntity.DEFLAGRATIONHORIZONTAL && btype==TypeEntity.DEFLAGRATIONVERTICAL)
				||(btype == TypeEntity.DEFLAGRATIONHORIZONTAL && atype==TypeEntity.DEFLAGRATIONVERTICAL))
		{
			defl.remove(a);
			defl.remove(b);
			a.setTypeEntity(TypeEntity.DEFLAGRATION);
			defl.add(a);
		}else{
			defl.remove(b);
		}
	}
		return defl.get(0);
	}



public void addItem(){
		
		List <Entity> squareEmpty = new ArrayList<>();
		int nbSquareEmpty = 0; 
		
		for (int x = 1; x<length-1; x++){
			for (int y = 1; y<length-1; y++) {
				if(findTypeEntity(new Coord(x, y)) == TypeEntity.EMPTY){
					squareEmpty.add(new Entity(true, new Coord(x, y), TypeEntity.EMPTY, true, true));
					nbSquareEmpty++;
				}
			}
		}
		
		if(nbSquareEmpty != 0){
			int nombreAleatoire1 = 0 + (int)(Math.random() * ((nbSquareEmpty - 0) + 1));
			int nombreAleatoire2 = 1 + (int)(Math.random() * ((4 - 1) + 1));
			Items itemcree;
			switch (nombreAleatoire2) {
			case 1:
				itemcree = new Bonus((squareEmpty.get(nombreAleatoire1).getCoord()), TypeEntity.BONUS_BOMB);
				break;
			case 2:
				itemcree = new Malus((squareEmpty.get(nombreAleatoire1).getCoord()), TypeEntity.MALUS_RANGE);
				break;
			case 3:
				itemcree = new Bonus((squareEmpty.get(nombreAleatoire1).getCoord()), TypeEntity.BONUS_RANGE);
				break;
			case 4:
				itemcree = new Malus((squareEmpty.get(nombreAleatoire1).getCoord()), TypeEntity.MALUS_BOMB);
				break;
			default:
				itemcree = new Bonus((squareEmpty.get(nombreAleatoire1).getCoord()), TypeEntity.BONUS_BOMB);
				break;
			}
			itemcree.addObserver(this);
			items.add(itemcree);
			this.setChanged();;
			notifyObservers();
		}
	}

public void removeDead (){
	List<Entity> listRemove = new ArrayList<Entity>();
	for (Player entity : players) {
		if(!entity.getAlive()){listRemove.add(entity);}
	}
	for(Entity entity : listRemove){players.remove(entity);};
	listRemove.clear();
	
	for (Monster entity : monsters) {
		if(!entity.getAlive()){listRemove.add(entity);}
	}
	for(Entity entity : listRemove){players.remove(entity);};
	listRemove.clear();
	
	for (Brick entity : bricks) {
		if(!entity.getAlive()){listRemove.add(entity);}
	}
	for(Entity entity : listRemove){players.remove(entity);};
	listRemove.clear();
	
	for (Items entity : items) {
		if(!entity.getAlive()){listRemove.add(entity);}
	}
	for(Entity entity : listRemove){players.remove(entity);};
	listRemove.clear();

	for (Deflagration entity : deflagrations) {
		if(!entity.getAlive()){listRemove.add(entity);}
	}
	for(Entity entity : listRemove){players.remove(entity);};
	listRemove.clear();
	
	for (Bomb entity : bombs) {
		if(!entity.getAlive()){listRemove.add(entity);}
	}
	for(Entity entity : listRemove){players.remove(entity);};
	listRemove.clear();
	
	return ;
}

	public List<Items> getItems() {
		return items;
	}
	
	public int getNbItems() {
		return items.size();
	}

	public List<Player> getPlayers() {
		return players;
	}

	public int getNbPlayer() {
		return players.size();
	}

	public List<Brick> getBricks() {
		return bricks;
	}

	public int getNbBrick() {
		return bricks.size();
	}

	public List<Wall> getWalls() {
		return walls;
	}

	public int getNbWall() {
		return walls.size();
	}

	public List<Monster> getMonsters() {
		return monsters;
	}

	public int getNbMonster() {
		return monsters.size();
	}


	public int getNbMonsterDie() {
		return nbMonsterDie;
	}

	public List<Thread> getMonstersThread() {
		return monstersThread;
	}

	public int getLength() {
		return length;
	}

	public List<Bomb> getBombs() {
		return bombs;
	}

	public int getNbBomb() {
		return bombs.size();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		this.setChanged();
		notifyObservers();
	}
	
	public int getDifficulte() {
		return difficulte;
	}

	private void setDifficulte(int difficulte) {
		this.difficulte = difficulte;
	}

	public void addListDeflagrations (Deflagration deflagration) {
		this.deflagrations.add(deflagration);
	}

	public void removeDeflagrations(Deflagration deflagration) {
		this.deflagrations.remove(deflagration);
	}
}
