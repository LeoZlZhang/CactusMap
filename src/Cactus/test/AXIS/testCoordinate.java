package Cactus.test.AXIS;

import Cactus.Design.PaneModule.PANE.FORM.TYPE.Form;
import Cactus.Design.PaneModule.PANE.PROFILE.SpaceProfile;
import Cactus.Design.PaneModule.AXIS.POSITION.CorePosition;
import Cactus.Design.PaneModule.AXIS.POSITION.MousePosition;
import Cactus.Design.PaneModule.AXIS.POSITION.Offset;
import Cactus.Design.PaneModule.AXIS.POSITION.ShapePosition;
import Cactus.Design.PaneModule.AXIS.Universe;
import Cactus.Design.PaneModule.AXIS.LogicSpace;
import Cactus.Design.PaneModule.AXIS.POSITION.Type.Position;
import Cactus.Design.PaneModule.PANE.FORM.EarthForm;
import Cactus.Design.PaneModule.PANE.FORM.SpaceForm;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 10/23/14
 * Time: 8:43 PM
 */
public class testCoordinate
{
    @Test
    public void testConvertAxisFromUniverseToEarth()
    {
        LogicSpace universe = new Universe(new SpaceForm(2000, 2000));
        universe.moveSpace(new Offset(-10, -8), new EarthForm(500, 500));
        Position posInUniverse = new ShapePosition(50, 60);
        Position posInRealWorld = new ShapePosition(40, 52);
        assertEquals(posInRealWorld.getX(), universe.trans2EarthView(posInUniverse).getX());
        assertEquals(posInRealWorld.getY(), universe.trans2EarthView(posInUniverse).getY());
    }

    @Test
    public void testZoomOutIn()
    {
        Form paneForm = new EarthForm(500, 500);
        LogicSpace universe = new Universe(new SpaceForm(2000, 2000));
        universe.zoomIn(paneForm);
        Position universePos = new CorePosition(50, 100);
        Position earthPos = universe.trans2EarthView(universePos);
        assertEquals(earthPos.getX(), 50 * 1.05);
        assertEquals(earthPos.getY(), 100 * 1.05);
    }

    @Test
    public void testZoomOutIn1()
    {
        Form paneForm = new EarthForm(500, 500);
        LogicSpace universe = new Universe(new SpaceForm(2000, 2000));
        universe.moveSpace(new Offset(-100, -100), paneForm);
        universe.zoomIn(paneForm);
        Position shapeUniversePos = new ShapePosition(50, 100);
        Position earthPos = universe.trans2EarthView(shapeUniversePos);
        assertEquals(earthPos.getX(), -100 + 50 * 1.05);
        assertEquals(earthPos.getY(), -100 + 100 * 1.05);
    }

    @Test
    public void testZoomOutInWithMousePos()
    {
        Form paneForm = new EarthForm(500, 500);
        LogicSpace universe = new Universe(new SpaceForm(2000, 2000));
        universe.moveSpace(new Offset(-50, -50), paneForm);
        Position mousePos = new MousePosition(50, 50);
        universe.zoomIn(mousePos, paneForm);
        assertEquals(universe.getSpaceProfile().getPosition(SpaceProfile.Direction.COR).getX(), 50 - (50 + 50) * 1.05);
    }

    @Test
    public void testZoomOutInWithMousePos1()
    {
        Form paneForm = new EarthForm(500, 500);
        LogicSpace universe = new Universe(new SpaceForm(2000, 2000));
        universe.moveSpace(new Offset(-50, -50), paneForm);
        Position mousePos = new MousePosition(50, 50);
        Position eventUniversePos = new ShapePosition(50, 50);
        Position eventEarthPos = universe.trans2EarthView(eventUniversePos);
        assertEquals(eventEarthPos.getX(), (double) 0);
        assertEquals(eventEarthPos.getY(), (double) 0);
        universe.zoomIn(mousePos, paneForm);
        eventEarthPos = universe.trans2EarthView(eventUniversePos);
        assertEquals(eventEarthPos.getX(), universe.getSpaceProfile().getPosition(SpaceProfile.Direction.COR).getX() + eventUniversePos.getX() * 1.05);
        assertEquals(eventEarthPos.getY(), universe.getSpaceProfile().getPosition(SpaceProfile.Direction.COR).getY() + eventUniversePos.getY() * 1.05);

    }
}
