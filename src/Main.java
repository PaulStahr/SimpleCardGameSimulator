import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.jdom2.JDOMException;

import gui.GameWindow;
import io.GameIO;
import main.Game;
import main.GameInstance;

public class Main {
    public static final void main (String args[]){
    	FileInputStream fis;
		try {
			fis = new FileInputStream("SchreckenDesTempels.zip");
			GameInstance game = GameIO.readGame(fis);
	    	fis.close();
	    	GameWindow gw = new GameWindow(game);
	    	gw.setVisible(true);
    	} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
