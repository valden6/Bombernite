
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;

import javax.imageio.ImageIO;


public class BomberniteGUIData extends Observable {

	private int length;

	public BomberniteGUIData(int length) {
		super();
		this.length = length;
	}

	/**
	 * @return the picture
	 */
	public BufferedImage getPicture(Entity entity) {
		TypeEntity type = entity.getTypeEntity();
		int iD = entity.getID();
		String nomdefichier;
		
		BufferedImage typePicture = null;

		switch (type) {
		
		case PLAYERSOUTH:
				nomdefichier = "Graphismes/Joueur"+ iD +"Face.gif";		
			break;

		case PLAYERNORTH:
			nomdefichier = "Graphismes/Joueur"+ iD +"Dos.gif";	
			break;

		case PLAYEREAST:
			nomdefichier = "Graphismes/Joueur"+ iD +"ProfilDroit.gif";	
			break;

		case PLAYERWEST:
			nomdefichier = "Graphismes/Joueur"+ iD +"ProfilGauche.gif";	
			break;
			
		case PLAYERBOMB:
			nomdefichier = "Graphismes/Joueur"+ iD +"Bombe.gif";	
			break;
			
		case BRICK:
			nomdefichier = "Graphismes/Brique.gif";
			break;

		case BOMB:
			nomdefichier = "Graphismes/Bombe0.gif";
			break;

		case MONSTER:
			nomdefichier = "Graphismes/Monstre.gif";
			break;

		case WALL:
			nomdefichier = "Graphismes/Mur.gif";
			break;

		case DEFLAGRATION:
			nomdefichier = "Graphismes/Deflagration_Croix.gif";
			break;

		case DEFLAGRATIONHORIZONTAL:
			nomdefichier = "Graphismes/Deflagration_Horiz.gif";
			break;

		case DEFLAGRATIONVERTICAL:
			nomdefichier = "Graphismes/Deflagration_Vert.gif";
			break;

		case DEFLAGRATIONNORTHEND:
			nomdefichier = "Graphismes/Deflagration_Fin_North.gif";
			break;

		case DEFLAGRATIONSOUTHEND:
			nomdefichier = "Graphismes/Deflagration_Fin_South.gif";
			break;

		case DEFLAGRATIONWESTEND:
			nomdefichier = "Graphismes/Deflagration_Fin_West.gif";
			break;

		case DEFLAGRATIONEASTEND:
			nomdefichier = "Graphismes/Deflagration_Fin_East.gif";
			break;
		
		case BONUS_BOMB:
			nomdefichier = "Graphismes/BombesPlus.gif";
			break;
			
		case BONUS_RANGE:
			nomdefichier = "Graphismes/RangesPlus.gif";
			break;
			
		case MALUS_BOMB:
			nomdefichier = "Graphismes/BombesMoins.gif";
			break;
			
		case MALUS_RANGE:
			nomdefichier = "Graphismes/RangesMoins.gif";
			break;

		default:
			nomdefichier = "Graphismes/Vide.gif";
			break;
		}
		try {
			typePicture = ImageIO.read(new File(nomdefichier));
		} catch (IOException e) {
			e.printStackTrace();
		}
			return typePicture;
		}

		/**
		 * colorWhiteSquare the colorWhiteSquare to set
		 */

		/**
		 * @return the length
		 */
		public int getLength() {
			return length;
		}

		/**
		 * @param length the length to set
		 */
		public void setLength(int length) {
			this.length = length;
			this.notifyObs();
		}

		private void notifyObs() {
			super.setChanged();
			super.notifyObservers();
		}
	}
