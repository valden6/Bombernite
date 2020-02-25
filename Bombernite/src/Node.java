/*Utilisé par l'IA du monstre, pour l'aglorithme A*
 * Par Marianne, 14/12/18
 */
public class Node{

	private Coord coords;
	private int cout;
	private double heuristique;
	private Direction direction;
	


	Node(int x, int y){
		this.coords = new Coord (x,y);
		this.cout = -1;
		this.heuristique = -1;
	}
	
	Node (Coord coord){
		this.coords = coord;
	}

	public Coord getCoords() {
		return coords;
	}
	
	public int getY() {
		return coords.getY();
	}
	
	public int getX() {
		return coords.getX();
	}

	public double getHeuristique() {
		return heuristique;
	}

	public void setHeuristique(double d) {
		this.heuristique = d;
	}

	public int getCout() {
		return cout;
	}

	public void setCout(int cout) {
		this.cout = cout;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	public int calculateDistance(Node node)
	{
		int distX, distY;
		if(node.getX()>this.getX()){distX = node.getX()-this.getX();} else {distX = this.getX()-node.getX();}
		if(node.getY()>this.getY()){distY = node.getY()-this.getY();} else {distY = this.getY()-node.getY();}
		return distX+distY;
	}
	
	public String toString()
	{
		String s = 	"Coords : [" + this.coords.getX() + "," + this.coords.getY() + "]"
					+ " cout : " + this.cout + "heuristique" + this.heuristique 
					+ " direction" + this.direction;
		return s;
	}
	
}


