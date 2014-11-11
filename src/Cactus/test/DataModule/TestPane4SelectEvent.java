package Cactus.test.DataModule;

import Cactus.Design.DataModule.AffectiveEvent;
import Cactus.Design.DataModule.FlowEvent;
import Cactus.Design.DataModule.TYPE.CactusEvent;
import Cactus.Design.PaneModule.AXIS.POSITION.ShapePosition;
import Cactus.Design.PaneModule.PANE.FORM.EarthForm;
import Cactus.Design.PaneModule.PANE.FORM.RectangleForm;
import Cactus.Design.PaneModule.PANE.FORM.SpaceForm;
import Cactus.Design.PaneModule.PANE.PROFILE.RectangleProfile;
import Cactus.Design.PaneModule.PANE.PROFILE.TYPE.Profile;
import Cactus.Design.PaneModule.PANE.Pane;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 11/10/14
 * Time: 6:15 PM
 */
public class TestPane4SelectEvent extends Pane
{
    private RectangleForm shapeForm = new RectangleForm(50, 30);
    public AffectiveEvent configure1 = new AffectiveEvent(new RectangleProfile(new ShapePosition(100, 100), shapeForm));
    public AffectiveEvent configure2 = new AffectiveEvent(new RectangleProfile(new ShapePosition(300, 100), shapeForm));
    public FlowEvent flow1 = new FlowEvent(new RectangleProfile(new ShapePosition(100, 200), shapeForm));
    public FlowEvent flow2 = new FlowEvent(new RectangleProfile(new ShapePosition(100, 300), shapeForm));
    public FlowEvent flow3 = new FlowEvent(new RectangleProfile(new ShapePosition(300, 300), shapeForm));
    public FlowEvent flow4 = new FlowEvent(new RectangleProfile(new ShapePosition(100, 400), shapeForm));

    protected TestPane4SelectEvent()
    {
        super(new SpaceForm(2000, 2000), new EarthForm(500, 500));
        this.initialMouseAdapter(this);
        EventRelationShip();
    }

    private void EventRelationShip()
    {
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
        flow1.eventProfile.affectiveEventList.add(eventList.indexOf(configure1));
        flow1.eventProfile.affectiveEventList.add(eventList.indexOf(configure2));

        flow2.eventProfile.dependentEventList.add(eventList.indexOf(flow1));
        flow2.eventProfile.resultEventList.add(eventList.indexOf(flow4));
        flow2.eventProfile.affectiveEventList.add(eventList.indexOf(configure2));

        flow3.eventProfile.dependentEventList.add(eventList.indexOf(flow1));
        flow3.eventProfile.resultEventList.add(eventList.indexOf(flow4));
        flow3.eventProfile.affectiveEventList.add(eventList.indexOf(configure1));

        flow4.eventProfile.dependentEventList.add(eventList.indexOf(flow2));
        flow4.eventProfile.dependentEventList.add(eventList.indexOf(flow3));
        flow4.eventProfile.affectiveEventList.add(eventList.indexOf(configure2));
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);    //To change body of overridden methods use File | Settings | File Templates.
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(Color.black.darker());
        g2.setStroke(new BasicStroke(2));

        for (CactusEvent event : eventList)
        {
            Profile earthPro = universe.trans2EarthView(event.getShapeProfile());
            RoundRectangle2D r = new RoundRectangle2D.Double(
                    earthPro.getPosition().getX(),
                    earthPro.getPosition().getY(),
                    earthPro.getForm().getWidth(),
                    earthPro.getForm().getHeight(),
                    12F * universe.getAmplifierValue(),
                    12F * universe.getAmplifierValue()
            );
            g2.setColor(event.shapeManager.getColor());
            g2.draw(r);
            g2.fill(r);
        }
    }
}
