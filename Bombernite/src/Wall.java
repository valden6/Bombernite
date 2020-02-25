
// 5/10/2018 - Guillaume Bozon
// Classe fille permettant de créer un personnage utilisé par un joueur
// Version : 1.2.0

public class Wall extends Entity {

    // Attribut
    // ------------------------------------------------------------------------------------------

    // Constructeur
    // ------------------------------------------------------------------------------------------
    Wall(){
        super(false, new Coord(0,0), TypeEntity.WALL, true,false);
    }

    Wall(int xInit, int yInit) {
        super(false, new Coord(xInit,yInit), TypeEntity.WALL, true,false);
    }

    // Méthode
    // ------------------------------------------------------------------------------------------

}
