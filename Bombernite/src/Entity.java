import java.util.Observable;

public class Entity extends Observable{

    private boolean crossable;
	private boolean alive;
	private Coord coord;
	private boolean destructible;
	private TypeEntity typeEntity;
	private int iD;

    public Entity() {

    	this.crossable = true;
    	this.coord = new Coord(0,0);
    	this.typeEntity = TypeEntity.EMPTY;
    	this.alive = true;
    }
    
    public Entity(boolean crossableInit,Coord coordCaseInit,TypeEntity typeEntityInit, boolean aliveInit, boolean destructible) {

        this.crossable = crossableInit;
        this.coord = coordCaseInit;
        this.typeEntity = typeEntityInit;
        this.alive= aliveInit;
        this.destructible = destructible;
    }
    public Entity(boolean crossableInit,Coord coordCaseInit,TypeEntity typeEntityInit, boolean aliveInit, boolean destructible, int iD) {

        this.crossable = crossableInit;
        this.coord = coordCaseInit;
        this.typeEntity = typeEntityInit;
        this.alive= aliveInit;
        this.destructible = destructible;
        this.iD = iD;
    }
    
    public TypeEntity getTypeEntity() {
		return typeEntity;
	}

	public void setTypeEntity(TypeEntity typeEntity) {
		this.typeEntity = typeEntity;
	}

	public Coord getCoord() {
		return coord;
	}

	public void setCoord(Coord coord) {
		this.coord = coord;
	}

	public int getX(){ return this.coord.getX();}
	public int getY(){ return this.coord.getY();}
	
	void die(){
		this.crossable = true;
		this.coord = new Coord(-1,-1);
		this.typeEntity = TypeEntity.EMPTY;
	}

	public boolean getAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
		if(alive == false){
			this.setCoord(new Coord(-1,-1));
		}
	}

	public boolean getCrossable() {
		return crossable;
	}

	public boolean isDestructible() {
		return destructible;
	}

	public void setDestructible(boolean destructible) {
		this.destructible = destructible;
	}

	public int getID() {
		return iD;
	}
}