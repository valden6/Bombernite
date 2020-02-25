import java.util.Timer;
import java.util.TimerTask;

public class TimerGame extends java.util.Timer {

    // Attribut
    // ----------------------------------------------------------------------------------------
    public static boolean k; // Variable permettant d'arreter le timer
    private java.util.Timer t;
    private TimerTask timerTask;

    // Constructeur
    // -------------------------------------------------------------------------------------
    
  /*  public TimerGame(String name,final Player player) {
        k = false;
        t = new Timer(name);
        timerTask = new TimerTask() {
            @Override
            public void run() {
                player.decreaseBomb();
            }
        };
    }*/
    
    public TimerGame(String name,final Bomb bomb) {
        k = false;
        t = new Timer(name);
        timerTask = new TimerTask() {
            @Override
            public void run() {
              bomb.explosionBomb();
            }
        };
    }

    public TimerGame(String name,final Deflagration deflagration) {
        k = false;
        t = new Timer(name);
        timerTask = new TimerTask() {
            @Override
            public void run() {
                deflagration.clean();
            }
        };
    }

    public TimerGame(String name,final Coord coord ) {
        k = false;
        t = new Timer(name);



        timerTask = new TimerTask() {
            @Override
            public void run() {
            /*	Tableau.deleteExplosion(coord);
                Tableau.getJetonSemaphore();
                Tableau.afficherTableau();
                Tableau.releaseJetonSemaphore();*/
            }
        };
    }

    // Méthode
    // ------------------------------------------------------------------------------------------*
    public TimerTask getTimerTask() {
        return timerTask;
    }

    public static boolean getK() {
        return k;
    }
}