// 5/10/2018 - Guillaume Bozon
// Classe permettant de gérer les coordonnées d'un objet
// Version : 1.0.0

public class Coord {

	// Attribut
	// ----------------------------------------------------------------------------------------
	private int x;
	private int y;

	// Méthode
	// ------------------------------------------------------------------------------------------
	public Coord(int i, int j) {
		this.x = i;
		this.y = j;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Coord [x=" + x + ", y=" + y + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coord other = (Coord) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	public static boolean coordonnees_valides(int x, int y, int length) {
		if ((x > length - 1) || (x < 0) || (y > length - 1) || (y < 0))
			return false;
		return true;
	}

	public static void main(String[] args) {
		Coord c1 = new Coord(5, 7);
		Coord c2 = new Coord(5, 7);
		boolean res = coordonnees_valides(c1.x, c1.y, 10);
		System.out.println("c1 = " + c1);
		System.out.println("c2 = " + c2);
		System.out.println("c1.equals(c2) ? " + c1.equals(c2));
		System.out.println("coordonnées valides : " + res);
	}
}