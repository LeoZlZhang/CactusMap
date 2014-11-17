package Cactus.test.DataModule;

import Cactus.Design.DataModule.TYPE.CactusEvent;
import Cactus.Design.PaneModule.PANE.PROFILE.RectangleProfile;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.testng.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 11/6/14
 * Time: 8:17 PM
 */
public class testEvent
{
    @Test
    public void testTransColor()
    {
        boolean res;
        CactusEvent eve = new CactusEvent(new RectangleProfile())
        {
        };
        double width = eve.getShapeProfile().getForm().getWidth();
        assertEquals(eve.shapeManager.getColor().getRed(), 131);
        eve.shapeManager.transform4SelectEffect();
        assertEquals(eve.shapeManager.getTransState4Select(), false);
        eve.shapeManager.transform4SelectEffect();
        assertEquals(eve.shapeManager.getTransState4Select(), false);
        eve.shapeManager.transform4SelectEffect();
        assertEquals(eve.shapeManager.getTransState4Select(), false);
        eve.shapeManager.transform4SelectEffect();
        assertEquals(eve.shapeManager.getTransState4Select(), false);
        eve.shapeManager.transform4SelectEffect();
        assertEquals(eve.shapeManager.getTransState4Select(), true);
        assertEquals(eve.shapeManager.getColor().getRed(), 17);
        System.out.println(eve.shapeManager.getColor().getGreen());
        eve.shapeManager.transform4SelectEffect();
        assertEquals(eve.shapeManager.getTransState4Select(), false);
        eve.shapeManager.transform4SelectEffect();;
        assertEquals(eve.shapeManager.getTransState4Select(), false);
        eve.shapeManager.transform4SelectEffect();
        assertEquals(eve.shapeManager.getTransState4Select(), false);
        eve.shapeManager.transform4SelectEffect();
        assertEquals(eve.shapeManager.getTransState4Select(), false);
        eve.shapeManager.transform4SelectEffect();
        assertEquals(eve.shapeManager.getTransState4Select(), true);
        assertEquals(eve.shapeManager.getColor().getRed(), 131);
        assertEquals(width, eve.getShapeProfile().getForm().getWidth());
    }
}
