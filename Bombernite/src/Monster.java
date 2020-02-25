import java.util.Collections;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*5/10/2018 - Julien Laurencin & Marianne Gras
 *21/01/19 -2- Marianne Gras  
 *Classe fille permettant de créer & gérer un monstre
 *Version : 5
 */

public class Monster extends Character  implements Runnable {

	private int LENGTH;
	private Map <Coord, Boolean> map_danger = new HashMap<Coord, Boolean>();
	private Map <Coord, Integer> map_path = new HashMap<Coord, Integer>();
	private Map <Coord, Direction> map_direction = new HashMap<Coord, Direction>();
	private int path;
	private Coord nextCase;
	private Direction nextDirection;
	private boolean flagOBJ;
	private ArrayList<Player> playerList;
	private ArrayList<Node> openList;
	private ArrayList<Node> closedList;
	private ArrayList<Node> playerFinds;
	private String ID;
	private boolean DEBUG = false;
	private BomberniteModel bomberniteModel;
	private int difficulte=1;

	// Constructeur
	// ------------------------------------------------------------------------------------------
	
	Monster(int x, int y, BomberniteModel bomberniteModel) {
		super(false, new Coord(x, y), TypeEntity.MONSTER, true);
		this.ID = "Monstre ["+ x +","+ y + "]";
		this.LENGTH = BomberniteLauncher.lengthBoard;
		this.bomberniteModel = bomberniteModel;
		this.difficulte = bomberniteModel.getDifficulte();
		this.playerList = new ArrayList<Player>(this.bomberniteModel.getPlayers());
		debug(ID + " a été créé.");
	}

	// Méthode
	// ------------------------------------------------------------------------------------------
	
	@Override
	public void run() {
		while (this.getAlive()) {
			debug(ID + " va se déplacer.");
			move();
			this.setChanged();
			notifyObservers();
		}

	}
	
	void move() {
		path = 1;
		init_tab(); // initialise le tableau à 0 ou false;
		remplis_tab(); // ajoute les infos de Tableau dans les différents tableaux
		nextDirection = Direction.NONE;
		debug(ID + " Init complete");
		switch(difficulte)
		{
		case 1:
			nextDirection = randomSafeDir();//une direction random safe, sinon il reste sur place.
			break;
		case 2:
			nextDirection = findCaseSafe(); // se dirige vers une case safe, peut faire plusieurs "pas" 
			break;								// pour se mettre en sécurité		
		case 3:
			if (map_danger.get(this.getCoord())){	// s'il est en danger se met en sécurité, sinon 
				nextDirection = findCaseSafe();		// cherche le joueur le plus proche
			}else{nextDirection = findPathPlayer();}
			break;
		default : 
			nextDirection = randomSafeDir();//une direction random safe, sinon il reste sur place.
			break;
		}
		
		
		

		// On se déplace vers la nextDirection, sauf si on doit rester sur place.
		if(nextDirection != Direction.NONE){
			
			nextCase = new Coord(this.getX() + nextDirection.getMoveX(), this.getY() + nextDirection.getMoveY());
			Entity entityNextCase = bomberniteModel.findEntity(nextCase);
			if(entityNextCase.getTypeEntity() != TypeEntity.EMPTY){
				entityNextCase.setAlive(false);
			}

			this.setCoord(nextCase);
			debug(ID +" s'est déplacé en : " + this.getX() + ";"
					+ this.getY() + "(" + nextDirection + ").");
		}else
		{// on reste sur place.
			debug(ID + " est resté sur place.");
		}
		
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private Direction findCaseSafe(){
		int x, y;
		Direction tab_Obj[] = new Direction[5]; 
		Direction result;
		int index_tab_Obj = 0;
		flagOBJ = false;
		Random rand = new Random();
		// trouver le chemin le plus court vers une zone safe
		while (!flagOBJ) // tant qu'on a pas trouvé
		{
			for (x = 0; x < LENGTH && !flagOBJ; x++) // on parcourt tout le
														// tableau
			{
				for (y = 0; y < LENGTH && !flagOBJ; y++) {
				
					if(map_path.get(new Coord(x,y))==path) // si trouve une case avec
														// l'indice le plus haut
					{
						// Random des [1-4] directions safes
						for(Direction d : Direction.values())
						{
							result = calculation_case(new Coord(x, y), new Coord( x + d.getMoveX() , y + d.getMoveY()));
							if(result != Direction.NONE)
							{
								tab_Obj[index_tab_Obj] = result;
								index_tab_Obj ++;
								debug(ID + " a trouvé que la direction " + result + " est safe.");				
							}

						}
					}
				}
			}
			path++;
			
		}
		if(index_tab_Obj>0) // s'il y a une ou plusieur direction safes, 
		{
			return tab_Obj[rand.nextInt(index_tab_Obj)];
		}else return Direction.NONE;
		
	}
	
	/* Calculation_Case
	 * 
	 * Utilisée par la partie "Eviter les bombes" pour regarder si une case passé en paramètre est safe.
	 * (C'est a dire crossable et pas dans le champ d'action d'une bombe)
	 * 
	 * Si la case est safe, on l'enregistre en nouvel objectif pour le monstre.
	 * 
	 * Sinon, on ne fait rien.
	 */


	// fonction regardant si une case est safe / faisant les étapes nécessaires.
	private Direction calculation_case(Coord position, Coord caseCalcul) { //pos =xy, casecalcul = xydir
		Entity obj = bomberniteModel.findEntity(caseCalcul);
		if (obj.getCrossable()) // si la case est traversable
		{
			if(map_path.get(caseCalcul)==0) // si cette case n'a pas encore été
											// calculée
			{
				map_path.put(caseCalcul, path + 1);// on lui affecte une valeur
				if (path != 1) {
					map_direction.put(caseCalcul, map_direction.get(position));
						// on note la direction dans laquelle on doit aller.
				}
				if (!map_danger.get(caseCalcul)) // si la case est safe
				{														// l'objectif 
					flagOBJ = true;
					return map_direction.get(caseCalcul); // on retourne la direction
				}
			}
		}
		return Direction.NONE;
	}

/* FINDPATHPLAYER
 * On utilise l'algorithme A* pour trouver le chemin le plus court, s'il existe.
 * S'il n'existe pas, on se déplace dans une direction safe aléatoire, s'il y en a au moins une.
 * Sinon on reste sur place.
 */
	
	private Direction findPathPlayer() {
		this.playerList = new ArrayList<Player>(this.bomberniteModel.getPlayers());
		closedList = new ArrayList<Node>();
		openList = new ArrayList<Node>();
		playerFinds = new ArrayList<Node>();
		Node start = new Node(this.getCoord()); 
		Node neigh;									// le noeud en cours, utile plus tard
		boolean outOfBound = false;
		debug(ID + " cherche le chemin vers le joueur.");
		
		//Initialisation du point de départ
		playerFinds.add(start);
		playerFinds.remove(start);
		start.setCout(0); // définis le point de départ = la position du monstre
		while(!playerList.isEmpty()){
			Node player = new Node(playerList.get(0).getCoord());
			start.setHeuristique(start.calculateDistance(player));
			openList.add(start);
			boucle1joueur:
			while (!openList.isEmpty()) {
				
				Collections.sort(openList, new NodeComparator());
				Node u = openList.get(0);
				debug(ID + " prends le noeud " + u.toString());
				if(!(u.equals(start))){ 
					if (u.getCoords().equals(player.getCoords())) {
						playerFinds.add(u); //on ajoute le noeud de ce joueur à la liste
						break boucle1joueur;
				//		direction = u.getDirection();
				//		return direction;
					}
				}//else ya un bug et on est sur le joueur
				
	
				
				for (Direction d : Direction.values()) {
					outOfBound = false;
					switch (d) {
					case EAST:
						neigh = new Node(u.getX() + d.getMoveX(), u.getY() + d.getMoveY());
						if((neigh.getX() < LENGTH)&&(neigh.getX() > 0)){
							if (u.equals(start)) { // u ne crée pas de node, donc à la première itération u référencera le même objet que start
								neigh.setDirection(d);
							}
						}else{ outOfBound = true;}
						break;
					case WEST:
						neigh = new Node(u.getX() + d.getMoveX(), u.getY() + d.getMoveY());
						if((neigh.getX() < LENGTH)&&(neigh.getX() > 0)){
							if (u.equals(start)) {
								neigh.setDirection(d);
							}
						}else{ outOfBound = true;}
						break;
					case NORTH:
						neigh = new Node(u.getX() + d.getMoveX(), u.getY() + d.getMoveY());
						if((neigh.getY() < LENGTH)&&(neigh.getY() > 0)){
							if (u.equals(start)) {
								neigh.setDirection(d);
							}
						}else{ outOfBound = true;}
						break;
					case SOUTH:
						neigh = new Node(u.getX() + d.getMoveX(), u.getY() + d.getMoveY());
						if((neigh.getY() < LENGTH)&&(neigh.getY() > 0)){
							if (u.equals(start)) {
								neigh.setDirection(d);
							}
						}else{ outOfBound = true;}
						break;
					default :
						neigh = new Node (u.getX() + d.getMoveX(), u.getY() + d.getMoveY());
						outOfBound = true;
						break;
					}
					if(outOfBound == false)
					{
						if (inferiorHeuristique(closedList, neigh))
						{	
							if( inferiorHeuristique(openList, neigh))
							{
								if ((bomberniteModel.findEntity(neigh.getCoords()).getCrossable()) ||(neigh.getCoords().equals(player.getCoords()))) 
								{
									neigh.setCout(u.getCout() + 1);
									neigh.setHeuristique(neigh.getCout() + neigh.calculateDistance(player) + 4);
									//on mets +4 pour que l'algorithme cherche en priorité les nodes proches du joueur
									if (!(u.equals(start))) 
									{
										neigh.setDirection(u.getDirection());
									}
									remove_alias(openList, neigh);
									openList.add(neigh);
									debug(ID + "On ajoute" + neigh.toString());
								}
							}
						}
					}
				}
				openList.remove(u);
				debug(ID + "On enlève" + u.toString());
				closedList.add(u);
				
			}
			playerList.remove(0);
		}
		
		if(!playerFinds.isEmpty()){
			Collections.sort(playerFinds, new NodeComparator());
			Node u = playerFinds.get(0);
			debug(ID + " le joueur le plus près est " + u.toString());
			return u.getDirection();
		}
		//Si on a trouvé un chemin on est sorti de la fonction, et le code ci-dessous n'est pas exécuté.
		//Si l'on a pas trouvé de chemin direct vers le joueur, on choisit au hasard une case safe à proximité. A défaut, on reste sur place.: 
		debug(ID + " n'a pas trouvé de chemin");
		return randomSafeDir();
	}
	
/*InferiorHeuristique
 * 
 * Parcours l'ArrayList passé en paramètre et retourne TRUE si aucun node n'a les mêmes coordonnées 
 * que celui passé en paramètre ou, s'il y a un node avec les mêmes coordonnées, qu'il ait une
 * heuristique supérieure à celui passé en paramètre.
 * 
 * 
 */

	private boolean inferiorHeuristique(ArrayList<Node> liste, Node node) {
		boolean res = true;
		for (int i = 0; i < liste.size(); i++) {
			Node nodeFromL = liste.get(i);
			// si un node avec les mêmes coords est présent
			if ((nodeFromL.getX() == node.getX()) && ( nodeFromL.getY() == node.getY())) {  
				// si son heuristique est inférieur à celle du node testé
				if (nodeFromL.getHeuristique() > node.getHeuristique()) {
					res = false;
				}
			}
		}
		return res;
	}
	
	private void remove_alias(ArrayList<Node> liste, Node node)
	{
		for (int i = 0; i < liste.size(); i++) {
			Node nodeFromL = liste.get(i);
			// si un node avec les mêmes coords est présent
			if ((nodeFromL.getX() == node.getX()) && ( nodeFromL.getY() == node.getY())) {  
				liste.remove(i);
				i--;
			}
		}
	}
/*Init_Tab
 * Initialise les tableaux à une valeur nulle (0, false ou Direction.NONE Selon le besoin)
 */

	private void init_tab() {
		int x,y;
				
		for (x = 0; x < LENGTH; x++) {
			for (y = 0; y < LENGTH; y++) {
				map_path.put(new Coord(x,y), 0);
				map_direction.put(new Coord(x,y), Direction.NONE);
				map_danger.put(new Coord(x,y), false);	
			}
		}
	}
	
/**
 * Remplis_Tab
 * 
 * Enregistre les informations suivantes dans les tableaux de données : 
 * tab_path : 		la case aux coordonnées du joueur contient "1"(int)
 * tab_direction : 	les quatre cases entourant le joueur contiennent une direction, de la façon suivante : 
 * 							NORTH
 * 					WEST	Player	EAST
 * 							SOUTH
 * tab_danger : 	Pour chaque case dans le champ d'action d'une bombe, la case correspondante contiendra
 * 					TRUE. 								
 */
	
	private void remplis_tab(){
	int x, y;	
	Object obj;
	map_path.put(new Coord(this.getX(),this.getY()),1);
	for(Direction d : Direction.values()) // ecrit dans les cases à cotés de la pos actuelle : 				NORTH
	{																					//			EAST	NONE(xy)	WEST
		x = this.getX() + d.getMoveX();													//					SOUTH
		if((x > 0) && (x < LENGTH))
		{
			y = this.getY() + d.getMoveY();
			if((y > 0) && (y < LENGTH)){map_direction.put(new Coord(x,y), d);}
		}
	}
	
	// Remplis le tab_danger
		for (x = 0; x < LENGTH; x++) {
			for (y = 0; y < LENGTH; y++) {
				obj = bomberniteModel.findEntity(new Coord (x,y));
				switch(((Entity) obj).getTypeEntity()){
				case BOMB :
					map_danger.put(new Coord (x,y), true);
					for(Direction d : Direction.values())
					{
						Boucle:
						for(int cpt = 0;cpt< ((Bomb) obj).getRange(); cpt++)
						{
							Coord coordonnées = new Coord(x + (cpt *d.getMoveX()),y + (cpt *d.getMoveY()));
							if(bomberniteModel.findEntity(coordonnées).getCrossable()){
								map_danger.put(coordonnées, true);
							}else{break Boucle;}
						}
					}
					// TODO ajouter le stop de déflagration (genre quand il y a une brique)
					break;
				case DEFLAGRATION:
					map_danger.put(new Coord (x,y),true);
					break;
				default:
					break; 
					
				}
				
			}
		}
	}
	
	void debug (String s)
	{
		if(DEBUG){System.out.println(s);}
	}
	
	private Direction randomSafeDir(){
		Random rand = new Random();
		Direction tab_safe[] = new Direction[5]; 
		int indexSafe =0;
		Entity obj ;
		for(Direction d : Direction.values())  // pour ne choisir qu'entre des directions safe
		{
			obj = bomberniteModel.findEntity(new Coord(this.getX()+d.getMoveX(), this.getY()+d.getMoveY()));
			switch(d){
			case WEST: 
				if(((obj.getX() < LENGTH-1)&&(obj.getX() > 0))&&((obj.getY() < LENGTH-1)&&(obj.getY() > 0))){
					if((obj.getCrossable() == true) && (map_danger.get(obj.getCoord()) == false)){
						tab_safe[indexSafe] = d;
						indexSafe++;
						debug(ID + " : " + d + " est safe.");
					}
				}
				break;
			case EAST: 
				if(((obj.getX() < LENGTH-1)&&(obj.getX() > 0))&&((obj.getY() < LENGTH-1)&&(obj.getY() > 0))){
					if((obj.getCrossable() == true) && (map_danger.get(obj.getCoord()) == false)){
						tab_safe[indexSafe] = d;
						indexSafe++;
						debug(ID + " : " + d + "est safe.");
					}
				}
				break;
			case NORTH: 
				if(((obj.getX() < LENGTH-1)&&(obj.getX() > 0))&&((obj.getY() < LENGTH-1)&&(obj.getY() > 0))){
					if((obj.getCrossable() == true) && (map_danger.get(obj.getCoord()) == false)){
						tab_safe[indexSafe] = d;
						indexSafe++;
						debug(ID + " : " + d + "est safe.");
					}
				}
				break;
			case SOUTH:
				if(((obj.getX() < LENGTH-1)&&(obj.getX() > 0))&&((obj.getY() < LENGTH-1)&&(obj.getY() > 0))){
					if((obj.getCrossable() == true) && (map_danger.get(obj.getCoord()) == false)){
						tab_safe[indexSafe] = d;
						indexSafe++;
						debug(ID + " : " + d + "est safe.");
					}
				}
				break;
			default:
				if(((obj.getX() < LENGTH-1)&&(obj.getX() > 0))&&((obj.getY() < LENGTH-1)&&(obj.getY() > 0))){
					if((obj.getCrossable() == true) && (map_danger.get(obj.getCoord()) == false)){
						tab_safe[indexSafe] = d;
						indexSafe++;
						debug(ID + " : " + d + "est safe.");
					}
				}
				break;
			}
		}
		
		if(indexSafe>0){
			return tab_safe[rand.nextInt(indexSafe)];
		}else
		{
			debug(ID + " n'a pas trouvé de case safe a proximité.");
			return Direction.NONE;	
		}
	}
}
