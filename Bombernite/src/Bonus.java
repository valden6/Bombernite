
public class Bonus extends Items {
	
	public Bonus(){
		super();
		this.setChanged();
		notifyObservers();
	}
	
	public Bonus(Coord coord, TypeEntity typeEntity){
		super(coord,typeEntity);
		this.setChanged();
		notifyObservers();
	}
	
	@Override
	public void useItem(Player player){	
		switch(this.getTypeEntity()){
		case BONUS_BOMB:
			if(player.getNbBombsMax()<12)
			{player.setNbBombsMax(player.getNbBombsMax() + 1);}
			this.setAlive(false);
			this.setChanged();
			notifyObservers();
			System.out.println("Number Bomb: " + player.getNbBombsMax());
			break;
		case BONUS_RANGE:
			player.setRange(player.getRange() + 1);
			this.setAlive(false);
			this.setChanged();
			notifyObservers();
			System.out.println("Range Explosion: " + player.getRange());
			break;
		default:
			System.out.println("ce n'est pas un bonus");
			break;
		}
	}
}
