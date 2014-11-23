package Cactus.Design.PaneModule.PANE;

import Cactus.Design.DataModule.TYPE.CactusEvent;
import Cactus.Design.PaneModule.PANE.FORM.TYPE.Form;
import Cactus.Design.PaneModule.PANE.PROFILE.TYPE.Profile;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 11/15/14
 * Time: 10:25 AM
 */
public class MapPane extends Pane
{
    public final ArrayList<CactusEvent> eventList = new ArrayList<CactusEvent>();

    protected MapPane(Form universeForm)
    {
        super(universeForm);
        initial();
    }

    protected MapPane(Form universeForm, ArrayList<CactusEvent> list)
    {
        super(universeForm);
        eventList.addAll(list);
        initial();
    }

    private void initial()
    {
        setSize(new Dimension(1, 1));
        setPreferredSize(this.getSize());
        setBackground(new Color(200, 200, 169));
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

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
            g2.fill(r);
        }
    }
}
