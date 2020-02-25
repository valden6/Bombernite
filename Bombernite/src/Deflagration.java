public class Deflagration extends Entity {


    public Deflagration(Coord coord, TypeEntity typeExplosion) {
        super(true,coord,typeExplosion,true,true);
        TimerGame timer = new TimerGame("DEFLAGRATION", this); // Création du timer
        timer.schedule(timer.getTimerTask(), 500);
    }

    public void clean() {
        this.setAlive(false);
   //     this.bomberniteModel.removeDeflagrations(this);
        this.setChanged();
		notifyObservers();
    }
}
