package Cactus.test.MouseEvent;

import Cactus.Design.PaneModule.PANE.MouseEvent.MouseAdapter4JPane;
import Cactus.Design.PaneModule.AXIS.POSITION.ShapePosition;
import Cactus.Design.PaneModule.AXIS.Universe;
import Cactus.Design.PaneModule.PANE.FORM.RectangleForm;
import Cactus.Design.PaneModule.PANE.PROFILE.RectangleProfile;
import Cactus.Design.PaneModule.PANE.PROFILE.TYPE.Profile;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 10/27/14
 * Time: 8:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class Pane4TestMouse extends JPanel
{
    public int widthOfPane = 500;
    public int heightOfPane = 500;
    public final Universe universe = new Universe();
    public final int ageSize = 50;
    public final Profile rectWestNorthShapeProfile = new RectangleProfile(new ShapePosition(0, 0), new RectangleForm(ageSize, ageSize));
    public final Profile rectWestSouthShapeProfile = new RectangleProfile(new ShapePosition(0, universe.spaceHeight - ageSize), new RectangleForm(ageSize, ageSize));
    public final Profile rectEastNorthShapeProfile = new RectangleProfile(new ShapePosition(universe.spaceWidth - ageSize, 0), new RectangleForm(ageSize, ageSize));
    public final Profile rectEastSouthShapeProfile = new RectangleProfile(new ShapePosition(universe.spaceWidth - ageSize, universe.spaceHeight - ageSize), new RectangleForm(ageSize, ageSize));


    public final ArrayList<Profile> profileList = new ArrayList<Profile>();

    public Pane4TestMouse()
    {
        this.setSize(new Dimension(widthOfPane, heightOfPane));
        this.setPreferredSize(this.getSize());
        universe.earthWidth = widthOfPane;
        universe.earthHeight = heightOfPane;
        MouseAdapter4JPane mouseAdp = new MouseAdapter4JPane(this);
        this.addMouseListener(mouseAdp);
        this.addMouseMotionListener(mouseAdp);
        this.addMouseWheelListener(mouseAdp);

        for (int i = 0; i < universe.spaceWidth / 200; i++)
        {
            for (int j = 0; j < universe.spaceHeight / 200; j++)
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
            Rectangle2D r = new Rectangle2D.Double(
                    shapeProfile.getPosition().getX(),
                    shapeProfile.getPosition().getY(),
                    shapeProfile.getForm().getWidth(),
                    shapeProfile.getForm().getHeight());
            g2.setColor(new Color(i * 2, 255 - i * 2, i * 2));
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
