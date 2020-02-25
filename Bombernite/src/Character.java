// 5/10/2018 - Guillaume Bozon
// Classe fille permettant de créer un squelette d'un personnage ou d'un monstre
// Version : 1.2.0

public abstract class Character extends Entity {

	// Attribut
	// ----------------------------------------------------------------------------------------
	

	public Character(boolean crossableInit, Coord coordInit, TypeEntity typeEntityInit, boolean aliveInit) {
		super(crossableInit, coordInit, typeEntityInit, aliveInit,true);
	};
	public Character(boolean crossableInit, Coord coordInit, TypeEntity typeEntityInit, boolean aliveInit,int iD) {
		super(crossableInit, coordInit, typeEntityInit, aliveInit,true,iD);
	};

	// Méthode
	// ------------------------------------------------------------------------------------------
	void move(Coord newCoord) {
		System.out.println("\nLe character c'est déplacé en : " + newCoord);
		this.setCoord(newCoord);
	}
}