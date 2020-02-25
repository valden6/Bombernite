
public class Malus extends Items {
	
	public Malus(){
		super();
		this.setChanged();
		notifyObservers();
	}
	
	public Malus(Coord coord, TypeEntity typeEntity){
		super(coord,typeEntity);
		this.setChanged();
		notifyObservers();
	}
	
	@Override
	public void useItem(Player player){	
		switch(this.getTypeEntity()){
		case MALUS_BOMB:
			if(player.getNbBombsMax()>0)
			{player.setNbBombsMax(player.getNbBombsMax() - 1);}
			this.setAlive(false);
			this.setChanged();
			notifyObservers();
			System.out.println("Number Bomb: " + player.getNbBombsMax());
			break;
		case MALUS_RANGE:
			if(player.getRange() >1)
			{player.setRange(player.getRange() - 1);}
			this.setAlive(false);
			this.setChanged();
			notifyObservers();
			System.out.println("Range Explosion: " + player.getRange());
			break;
		default:
			System.out.println("ce n'est pas un malus");
			break;
		}
	}
}
