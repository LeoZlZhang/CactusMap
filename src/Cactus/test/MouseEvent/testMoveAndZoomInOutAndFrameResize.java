package Cactus.test.MouseEvent;

import Cactus.Design.DataModule.FlowEvent;
import Cactus.Design.DataModule.TYPE.CactusEvent;
import Cactus.Design.PaneModule.AXIS.POSITION.ShapePosition;
import Cactus.Design.PaneModule.PANE.FORM.RectangleForm;
import Cactus.Design.PaneModule.PANE.PROFILE.RectangleProfile;
import Cactus.Design.PaneModule.PANE.PROFILE.TYPE.Profile;
import Cactus.Design.PaneModule.PANE.TopFrame;
import org.testng.annotations.Test;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 11/2/14
 * Time: 5:43 PM
 */
public class testMoveAndZoomInOutAndFrameResize
{
    @Test
    public void test60Sec() throws InterruptedException
    {
        ArrayList<CactusEvent> eventList = new ArrayList<CactusEvent>();
        for (int i = 0; i < 2000 / 200; i++)
        {
            for (int j = 0; j < 2000 / 200; j++)
            {
                eventList.add(new FlowEvent(new RectangleProfile(new ShapePosition(i * 200, j * 200), new RectangleForm(50, 50))));
            }
        }
        final int ageSize = 50;
        final Profile rectWestNorthShapeProfile = new RectangleProfile(new ShapePosition(0, 0), new RectangleForm(ageSize, ageSize));
        final Profile rectWestSouthShapeProfile = new RectangleProfile(new ShapePosition(0, 2000 - ageSize), new RectangleForm(ageSize, ageSize));
        final Profile rectEastNorthShapeProfile = new RectangleProfile(new ShapePosition(2000 - ageSize, 0), new RectangleForm(ageSize, ageSize));
        final Profile rectEastSouthShapeProfile = new RectangleProfile(new ShapePosition(2000 - ageSize, 2000 - ageSize), new RectangleForm(ageSize, ageSize));
        eventList.add(new FlowEvent(rectWestNorthShapeProfile));
        eventList.add(new FlowEvent(rectWestSouthShapeProfile));
        eventList.add(new FlowEvent(rectEastNorthShapeProfile));
        eventList.add(new FlowEvent(rectEastSouthShapeProfile));

        TopFrame frame = new TopFrame();
        frame.mapPane.eventList.addAll(eventList);
        frame.setVisible(true);
        Thread.sleep(60000);
    }
}
