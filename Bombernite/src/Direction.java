// 11/01/19 - Marianne Gras
// Enum permettant de gerer les differentes directions du joueur sur la case
// Et deux trois autres trucs
// Version : 3.0.0

public enum Direction {
	NORTH("NORTH", 0, -1), SOUTH("SOUTH", 0, +1), EAST("EAST", +1, 0), WEST("WEST", -1, 0), NONE("NONE", 0, 0);

	private String name;
	private int moveX;
	private int moveY;

	Direction(String name, int moveX, int moveY) {
		this.name = name;
		this.moveX = moveX;
		this.moveY = moveY;
	}

	public String getName() {
		return name;
	}

	public int getMoveX() {
		return moveX;
	}

	public int getMoveY() {
		return moveY;
	}


}
