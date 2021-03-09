package test;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.jdom2.JDOMException;
import org.junit.Test;

import io.GameIO;
import main.Player;

public class GameIOTest {
    @Test
    public void testXmlPlayer()
    {
        Player pl = new Player("Player", 42);
        pl.color = Color.CYAN;
        pl.mouseXPos = 43;
        pl.mouseYPos = 44;
        pl.playerAtTablePosition =4;
        pl.playerAtTableRotation = 2;
        pl.playerAtTableTransform.setTransform(1,2,3,4,5,6);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Player res;
        try {
            GameIO.writePlayerToStream(pl, bos);
            res = GameIO.readPlayerFromStream(new ByteArrayInputStream(bos.toByteArray()));
            res.playerAtTablePosition =4; //TODO Florian is this supposed to be not send?
            res.playerAtTableRotation = 2;
        } catch (IOException e) {
            throw new AssertionError(e);
        } catch (JDOMException e) {
            throw new AssertionError(e);
        }
        try
        {
            assert (res.equals(pl));
        } catch (AssertionError e)
        {
            throw new AssertionError(pl.toStringAdvanced() + "!=" + res.toStringAdvanced(), e);
        }
    }
}
