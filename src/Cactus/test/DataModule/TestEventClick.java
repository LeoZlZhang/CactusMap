package Cactus.test.DataModule;

import Cactus.Design.DataModule.AffectiveEvent;
import Cactus.Design.DataModule.FlowEvent;
import Cactus.Design.DataModule.TYPE.CactusEvent;
import Cactus.Design.PaneModule.AXIS.POSITION.ShapePosition;
import Cactus.Design.PaneModule.PANE.FORM.RectangleForm;
import Cactus.Design.PaneModule.PANE.PROFILE.RectangleProfile;
import Cactus.Design.PaneModule.PANE.TopFrame;
import org.testng.annotations.Test;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 11/11/14
 * Time: 10:55 AM
 */
public class TestEventClick
{
    @Test
    public void testClick() throws InterruptedException
    {
        RectangleForm shapeForm = new RectangleForm(50, 30);
        AffectiveEvent configure1 = new AffectiveEvent(new RectangleProfile(new ShapePosition(100, 100), shapeForm));
        AffectiveEvent configure2 = new AffectiveEvent(new RectangleProfile(new ShapePosition(300, 100), shapeForm));
        FlowEvent flow1 = new FlowEvent(new RectangleProfile(new ShapePosition(100, 200), shapeForm));
        FlowEvent flow2 = new FlowEvent(new RectangleProfile(new ShapePosition(100, 300), shapeForm));
        FlowEvent flow3 = new FlowEvent(new RectangleProfile(new ShapePosition(300, 300), shapeForm));
        FlowEvent flow4 = new FlowEvent(new RectangleProfile(new ShapePosition(100, 400), shapeForm));
        ArrayList<CactusEvent> eventList = new ArrayList<CactusEvent>();
        eventList.add(configure1);
        eventList.add(configure2);
        eventList.add(flow1);
        eventList.add(flow2);
        eventList.add(flow3);
        eventList.add(flow4);
        configure1.eventProfile.resultEventList.add(eventList.indexOf(flow1));
        configure2.eventProfile.resultEventList.add(eventList.indexOf(flow1));
        flow1.eventProfile.resultEventList.add(eventList.indexOf(flow2));
        flow1.eventProfile.resultEventList.add(eventList.indexOf(flow3));
        flow2.eventProfile.dependentEventList.add(eventList.indexOf(flow1));
        flow2.eventProfile.resultEventList.add(eventList.indexOf(flow4));
        flow3.eventProfile.dependentEventList.add(eventList.indexOf(flow1));
        flow3.eventProfile.resultEventList.add(eventList.indexOf(flow4));
        flow4.eventProfile.dependentEventList.add(eventList.indexOf(flow2));
        flow4.eventProfile.dependentEventList.add(eventList.indexOf(flow3));
        TopFrame frame = new TopFrame();
        frame.mapPane.eventList.clear();
        frame.mapPane.eventList.addAll(eventList);
        frame.setVisible(true);
        Thread.sleep(60000);
    }
}
