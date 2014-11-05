package Cactus.test.MouseEvent;

import Cactus.Design.PaneModule.PANE.FORM.EarthForm;
import Cactus.Design.PaneModule.PANE.FORM.SpaceForm;
import Cactus.Design.PaneModule.PANE.FORM.TYPE.Form;
import Cactus.Design.PaneModule.PANE.MouseEvent.MouseAdapter4JPane;
import Cactus.Design.PaneModule.AXIS.POSITION.ShapePosition;
import Cactus.Design.PaneModule.AXIS.Universe;
import Cactus.Design.PaneModule.PANE.FORM.RectangleForm;
import Cactus.Design.PaneModule.PANE.PROFILE.RectangleProfile;
import Cactus.Design.PaneModule.PANE.PROFILE.TYPE.Profile;
import Cactus.Design.PaneModule.PANE.Pane;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 10/27/14
 * Time: 8:11 PM
 */
public class Pane4TestMouse extends Pane
{
    public final int ageSize = 50;
    public final Profile rectWestNorthShapeProfile = new RectangleProfile(new ShapePosition(0, 0), new RectangleForm(ageSize, ageSize));
    public final Profile rectWestSouthShapeProfile = new RectangleProfile(new ShapePosition(0, 2000 - ageSize), new RectangleForm(ageSize, ageSize));
    public final Profile rectEastNorthShapeProfile = new RectangleProfile(new ShapePosition(2000 - ageSize, 0), new RectangleForm(ageSize, ageSize));
    public final Profile rectEastSouthShapeProfile = new RectangleProfile(new ShapePosition(2000 - ageSize, 2000 - ageSize), new RectangleForm(ageSize, ageSize));


    public final ArrayList<Profile> profileList = new ArrayList<Profile>();

    public Pane4TestMouse()
    {
        super(new SpaceForm(2000,2000), new SpaceForm(500, 500));
        super.initialMouseAdapter(this);

        for (int i = 0; i < this.universeForm.getWidth() / 200; i++)
        {
            for (int j = 0; j < this.universeForm.getHeight() / 200; j++)
            {
                profileList.add(new RectangleProfile(new ShapePosition(i * 200, j * 200), new RectangleForm(50, 50)));
            }
        }
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(Color.black.darker());
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.yellow.brighter());

        for (int i = 0; i < profileList.size(); i++)
        {
            Profile shapeProfile = universe.trans2EarthView(profileList.get(i));
            RoundRectangle2D r = new RoundRectangle2D.Double(
                    shapeProfile.getPosition().getX(),
                    shapeProfile.getPosition().getY(),
                    shapeProfile.getForm().getWidth(),
                    shapeProfile.getForm().getHeight(), 15, 15);
            g2.setColor(new Color((int) profileList.get(i).getPosition().getX() / 8, (int) profileList.get(i).getPosition().getY() / 8, i * 2));
            g2.draw(r);
            g2.fill(r);
        }
        Profile shapeProfileInEarth;
        g2.setColor(Color.red.brighter());

        shapeProfileInEarth = universe.trans2EarthView(rectWestNorthShapeProfile);
        Rectangle2D shape1 = new Rectangle2D.Double(
                shapeProfileInEarth.getPosition().getX(),
                shapeProfileInEarth.getPosition().getY(),
                shapeProfileInEarth.getForm().getWidth(),
                shapeProfileInEarth.getForm().getHeight());
        g2.draw(shape1);
        g2.fill(shape1);

        shapeProfileInEarth = universe.trans2EarthView(rectWestSouthShapeProfile);
        Rectangle2D shape2 = new Rectangle2D.Double(
                shapeProfileInEarth.getPosition().getX(),
                shapeProfileInEarth.getPosition().getY(),
                shapeProfileInEarth.getForm().getWidth(),
                shapeProfileInEarth.getForm().getHeight());
        g2.draw(shape2);
        g2.fill(shape2);

        shapeProfileInEarth = universe.trans2EarthView(rectEastNorthShapeProfile);
        Rectangle2D shape3 = new Rectangle2D.Double(
                shapeProfileInEarth.getPosition().getX(),
                shapeProfileInEarth.getPosition().getY(),
                shapeProfileInEarth.getForm().getWidth(),
                shapeProfileInEarth.getForm().getHeight());
        g2.draw(shape3);
        g2.fill(shape3);

        shapeProfileInEarth = universe.trans2EarthView(rectEastSouthShapeProfile);
        Rectangle2D shape4 = new Rectangle2D.Double(
                shapeProfileInEarth.getPosition().getX(),
                shapeProfileInEarth.getPosition().getY(),
                shapeProfileInEarth.getForm().getWidth(),
                shapeProfileInEarth.getForm().getHeight());
        g2.draw(shape4);
        g2.fill(shape4);
    }

}
