
// 5/10/2018 - Guillaume Bozon
// Classe fille permettant de créer un personnage utilisé par un joueur
// Version : 1.2.0

public class Brick extends Entity {

        // Attribut
        // ------------------------------------------------------------------------------------------

        // Constructeur
        // ------------------------------------------------------------------------------------------
        Brick() {
            super(false, new Coord(0,0), TypeEntity.BRICK, true,true);
        }

        Brick(int xInit, int yInit) {
            super(false, new Coord(xInit,yInit), TypeEntity.BRICK, true,true);
        }

        // Méthode
        // ------------------------------------------------------------------------------------------

}
