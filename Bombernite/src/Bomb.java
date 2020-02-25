import java.util.Observable;
import java.util.Observer;

public class Bomb extends Entity implements Observer{
	// Attribut
	// ----------------------------------------------------------------------------------------
	
	private int range;
	private BomberniteModel bomberniteModel;
	private boolean exploding;

	public boolean isExploding() {
		return exploding;
	}

	// Constructeur
	// -----------------------------------------------------------------------------------
	Bomb(Coord coordInit, int rangeInit, BomberniteModel bomberniteModel) {
		super(false,coordInit,TypeEntity.BOMB, true,true); // Appel le constructeur parametré de Entity
		this.bomberniteModel = bomberniteModel;
		range = rangeInit;
		System.out.println("Bomb Placed: " + coordInit);
		this.exploding = false;
		bombPlaced();
	}

	// Méthode
	// ------------------------------------------------------------------------------------------

	// fonction permettant d'effectuer le timer avant explosion
	public void bombPlaced() {

        TimerGame timer = new TimerGame("BOMB", this); // Création du timer
		timer.schedule(timer.getTimerTask(), 2000); // Lance le timer avec une fin dans 3 seconde
	}


	public void explosionBomb() {
		this.exploding = true;
		//Après le délai de la bombe
		System.out.println("Explosion de la bombe....");
		boolean flag = false;
		//Explosion du centre
		Deflagration deflagration = new Deflagration(this.getCoord(),TypeEntity.DEFLAGRATION);
		deflagration.addObserver(this);
		bomberniteModel.addListDeflagrations(deflagration);
		for(Direction d : Direction.values()) {
			Boucle:
			for (int cptRange = 1; cptRange <= range ; cptRange++ ) {
				flag = false;
				Coord coordTarget = new Coord(this.getX() + (cptRange * d.getMoveX()), this.getY() + (cptRange * d.getMoveY()));
				Entity entity = 	bomberniteModel.findEntity(coordTarget);

				if(entity.isDestructible() && (entity.getTypeEntity()!= TypeEntity.BRICK)) {
					if(entity.getAlive()) {
						if((!entity.equals(this))&&(entity.getTypeEntity()==TypeEntity.BOMB))
						{
						}else
						//-----------------------------------ICI ON TUE LES ENTITES!!!----------------------
						{entity.setAlive(false);}
					}
					switch (d) {
							case EAST:
								if (cptRange == range) {
									deflagration = new Deflagration(coordTarget, TypeEntity.DEFLAGRATIONEASTEND);
								}
								else {
                                    deflagration =new Deflagration(coordTarget, TypeEntity.DEFLAGRATIONHORIZONTAL);
								}
								break;

							case WEST:
								if (cptRange == range) {
                                    deflagration =new Deflagration(coordTarget, TypeEntity.DEFLAGRATIONWESTEND);
								}
								else {
                                    deflagration = new Deflagration(coordTarget, TypeEntity.DEFLAGRATIONHORIZONTAL);
								}
								break;

							case SOUTH:
								if (cptRange == range) {
                                    deflagration = new Deflagration(coordTarget, TypeEntity.DEFLAGRATIONSOUTHEND);
								}
								else {
                                    deflagration = new Deflagration(coordTarget, TypeEntity.DEFLAGRATIONVERTICAL);
								}
								break;

							case NORTH:
								if (cptRange == range) {
                                    deflagration = new Deflagration(coordTarget, TypeEntity.DEFLAGRATIONNORTHEND);
								}
								else {
                                    deflagration = new Deflagration(coordTarget, TypeEntity.DEFLAGRATIONVERTICAL);
								}
								break;

							default:
								break;
						}
				} else {
					if(entity.getTypeEntity() == TypeEntity.BRICK)
					{
						//-----------------------------------ICI ON TUE LES ENTITES!!!----------------------
						entity.setAlive(false);
					}else{coordTarget = new Coord(coordTarget.getX()-d.getMoveX(), coordTarget.getY()-d.getMoveY());}
					
					if(coordTarget.equals(this.getCoord()))
					{	
						deflagration = new Deflagration(this.getCoord(),TypeEntity.DEFLAGRATION);
						deflagration.addObserver(this);
						bomberniteModel.addListDeflagrations(deflagration);
					}else{
						switch (d) {
							case EAST:
		                        deflagration = new Deflagration(coordTarget, TypeEntity.DEFLAGRATIONEASTEND);
								break;
		
							case WEST:
		                        deflagration = new Deflagration(coordTarget, TypeEntity.DEFLAGRATIONWESTEND);
								break;
		
							case SOUTH:
		                        deflagration = new Deflagration(coordTarget, TypeEntity.DEFLAGRATIONSOUTHEND);
								break;
		
							case NORTH:
		                        deflagration = new Deflagration(coordTarget, TypeEntity.DEFLAGRATIONNORTHEND);
								break;
		
							default:
								break;
						}
					}
					flag = true;
				}
				
                if (d != Direction.NONE) {
                	deflagration.addObserver(this);
                    bomberniteModel.addListDeflagrations(deflagration);
                }
                if(flag)
                {
                	break Boucle;
                }
            }
		}
		for(Direction d : Direction.values()) {
			for (int cptRange = 1; cptRange <= range ; cptRange++ ) {
				Coord coordTarget = new Coord(this.getX() + (cptRange * d.getMoveX()), this.getY() + (cptRange * d.getMoveY()));
				Entity entity = 	bomberniteModel.findEntity(coordTarget);
				if((!entity.equals(this))&&(entity.getTypeEntity()==TypeEntity.BOMB))
					{
						if(!((Bomb) entity).isExploding()){((Bomb) entity).explosionBomb();}
					}
			}
		}
		this.setChanged();
		notifyObservers();
	}

	public int getRange() {
		return range;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		this.setChanged();
		notifyObservers();
		
	}
}

