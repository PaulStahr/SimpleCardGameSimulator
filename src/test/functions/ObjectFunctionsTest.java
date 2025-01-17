package test.functions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.awt.image.BufferedImage;
import java.io.IOException;

import data.Texture;
import gameObjects.definition.GameObjectToken;
import gameObjects.instance.ObjectInstance;
import org.jdom2.JDOMException;
import org.junit.Test;

import data.DataHandler;
import gameObjects.action.player.PlayerAddAction;
import gameObjects.functions.ObjectFunctions;
import gameObjects.instance.Game;
import gameObjects.instance.GameInstance;
import io.GameIO;
import main.Player;
import util.data.IntegerArrayList;

public class ObjectFunctionsTest {


    int id = (int)System.nanoTime();
    @Test
    public void makeStack() throws IOException, JDOMException
    {
        GameInstance gi = new GameInstance(new Game(), "Foo");
        GameIO.readSnapshotFromZip(DataHandler.getResourceAsStream("test/games/Test.zip"), gi);
        Player pl = new Player("Max", 4);
        gi.addPlayer(new PlayerAddAction(id, pl));
        int[] idList = new int[] {0, 1, 2, 3, 4};
        int[] idList2 = new int[] {0, 1};
        IntegerArrayList ial = new IntegerArrayList(idList);
        ObjectFunctions.makeStack(id, gi, pl, ial, null, ObjectFunctions.SIDE_TO_FRONT);
        ObjectFunctions.getStack(gi, gi.getObjectInstanceById(0), ial);
        assertEquals(5, ial.size());
        ObjectFunctions.removeStackRelations(id, gi, pl, gi.getObjectInstanceById(0));
        for (int i : idList) {
            ObjectFunctions.getStack(gi, gi.getObjectInstanceById(i), ial);
            assertEquals(1, ial.size());
        }
        ObjectFunctions.makeStack(id, gi, pl, new IntegerArrayList(idList2), null, ObjectFunctions.SIDE_TO_FRONT);
        ObjectFunctions.getStack(gi, gi.getObjectInstanceById(0), ial);
        assertEquals(2, ial.size());

    }

    @Test
    public void mergeStacks() throws IOException, JDOMException
    {
        GameInstance gi = new GameInstance(new Game(), "Foo");
        GameIO.readSnapshotFromZip(DataHandler.getResourceAsStream("test/games/Test.zip"), gi);
        Player pl = new Player("Max", 4);
        gi.addPlayer(new PlayerAddAction(id, pl));
        int[] idList = new int[] {0, 1, 2, 3, 4};
        int[] idList2 = new int[] {5, 6, 7};
        IntegerArrayList ial = new IntegerArrayList(idList);
        ObjectFunctions.makeStack(id, gi, pl, ial, null, ObjectFunctions.SIDE_TO_FRONT);
        ial = new IntegerArrayList(idList2);
        ObjectFunctions.makeStack(id, gi, pl, ial, null, ObjectFunctions.SIDE_TO_FRONT);
        ObjectFunctions.mergeStacks(id, gi, pl, gi.getObjectInstanceById(7), gi.getObjectInstanceByIndex(4));
        ObjectFunctions.getStack(gi, gi.getObjectInstanceById(0), ial);
        assertEquals(8, ial.size());
        assertEquals(7, ObjectFunctions.getStackTop(gi, gi.getObjectInstanceById(0)).id);
        assertEquals(0, ObjectFunctions.getStackBottom(gi, gi.getObjectInstanceById(0)).id);
    }


    @Test
    public void automaticMerge() throws IOException, JDOMException
    {
        //TODO
    }

}
