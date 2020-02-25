import java.util.Comparator;

/*Utilisé par l'IA du monstre, pour l'aglorithme A*
 * Comparateur pour les objets de la classe Node
 * Par Marianne, 14/12/18
 */

public class NodeComparator implements Comparator<Node>{

	//Fonction qui compare la valeur heuristique de deux noeuds.
		// s'appelle de la façon suivante : int val = n1.compareHeur(n2);
		// retourne 1 si n1>n2, 0 si n1=n2, -1 sinon.
		
		@Override
		public int compare (Node node1, Node node2) {
			int res;
			if (node1.getHeuristique() > node2.getHeuristique()){ 
				res = 1;
			}else if (node1.getHeuristique() == node2.getHeuristique()){
				res = 0;
			}else {res = -1;}
			
			return res;
			
			//return this.getHeuristique().compareTo(node2.getHeuristique());
		}
}
