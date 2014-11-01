package Cactus.test.AXIS;

import Cactus.Design.PaneModule.AXIS.AxisSuit;
import Cactus.Design.PaneModule.AXIS.POS.CorePosition;
import Cactus.Design.PaneModule.AXIS.POS.MousePosition;
import Cactus.Design.PaneModule.AXIS.POS.ShapePosition;
import Cactus.Design.PaneModule.AXIS.POSITION.Type.Space;
import Cactus.Design.PaneModule.AXIS.Universe;
import Cactus.Design.PaneModule.AXIS.LogicSpace;
import Cactus.Design.PaneModule.AXIS.POS.Position;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 10/23/14
 * Time: 8:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class testCoordinate
{
    @Test
    public void testConvertAxisFromUniverseToEarth()
    {
        Space universe = new Universe(new CorePosition(10, 8));
        Position posInUniverse = new ShapePosition(50, 60);
        Position posInRealWorld = new ShapePosition(60, 68);
        assertEquals(posInRealWorld.getX(), universe.trans2EarthView(posInUniverse).getX());
        assertEquals(posInRealWorld.getY(), universe.trans2EarthView(posInUniverse).getY());
    }

    @Test
    public void testZoomOutIn()
    {
        Space universe = new Universe();
        universe.zoomIn();
        Position universePos = new CorePosition(50, 100);
        Position earthPos = universe.trans2EarthView(universePos);
        assertEquals(earthPos.getX(), 50 * 1.05);
        assertEquals(earthPos.getY(), 100 * 1.05);
    }

    @Test
    public void testZoomOutIn1()
    {
        LogicSpace universe = new Universe(new CorePosition(-100, -100));
        universe.zoomIn();
        Position shapeUniversePos = new ShapePosition(50, 100);
        Position earthPos = universe.trans2EarthView(shapeUniversePos);
        assertEquals(earthPos.getX(), -100 + 50 * 1.05);
        assertEquals(earthPos.getY(), -100 + 100 * 1.05);
    }

    @Test
    public void testZoomOutInWithMousePos()
    {
        Universe universe = new Universe(new CorePosition(-50, -50));
        Position mousePos = new MousePosition(50, 50);
        universe.zoomIn(mousePos);
        assertEquals(universe.getPositionSuit().getPosition(AxisSuit.Direction.COR).getX(), 50 - (50 + 50) / 1 * 1.05);
    }

    @Test
    public void testZoomOutInWithMousePos1()
    {
        Universe universe = new Universe(new CorePosition(-50, -50));
        Position mousePos = new MousePosition(50, 50);
        Position eventUniversePos = new ShapePosition(50, 50);
        Position eventEarthPos = universe.trans2EarthView(eventUniversePos);
        assertEquals(eventEarthPos.getX(), (double) 0);
        assertEquals(eventEarthPos.getY(), (double) 0);
        universe.zoomIn(mousePos);
        eventEarthPos = universe.trans2EarthView(eventUniversePos);
        assertEquals(eventEarthPos.getX(), universe.getPositionSuit().getPosition(AxisSuit.Direction.COR).getX() + eventUniversePos.getX() * 1.05);
        assertEquals(eventEarthPos.getY(), universe.getPositionSuit().getPosition(AxisSuit.Direction.COR).getY() + eventUniversePos.getY() * 1.05);

    }

    public void mathLeave2Decimal(double dou)
    {
        dou = (double) ((int) (dou * 100)) / 100.0;
    }

    public void mathLeave2Decimal(Double dou)
    {
        dou = (double) ((int) (dou.doubleValue() * 100)) / 100.0;
    }

    public void mathLeave2Decimal(Container con)
    {
        con.dou = (double) ((int) (con.dou * 100)) / 100.0;
    }

    class Container
    {
        public double dou = 0.0;

        Container(double dou)
        {
            this.dou = dou;
        }
    }

    @Test
    public void testDecimal()
    {
        double dou = 10.0 / 3;
        Double Dou = new Double(dou);
        Container container = new Container(dou);

        mathLeave2Decimal(dou);
        mathLeave2Decimal(container);
        mathLeave2Decimal(Dou);
        System.out.println("double " + dou);
        System.out.println("container " + container.dou);
        System.out.println("Double  " + Dou.doubleValue());
    }



}
