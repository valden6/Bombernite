import javax.swing.*;
import java.io.IOException;


public class BomberniteLauncher {
	public static Fenetre startingPage;
	public static int lengthBoard = 11 ;
	public static int playerNb = 1 ;
	public static int mobNb = 1 ;
	public static boolean son = true ;
	public static int Difficulte = 1 ; // 1 = faible ,3 = difficile

	public static int getDifficulte() {
		return Difficulte;
	}

	public static void setDifficulte(int difficulte) {
		Difficulte = difficulte;
	}

	public static void main(String[] args) throws IOException {
		startingPage = new Fenetre();
	}

	public static void setFrameConfig(JFrame window, String name){
		window.setTitle(name);
		window.setSize(1000, 1000);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


}