package Cactus.test.PANE;

import Cactus.Design.PaneModule.AXIS.POSITION.ShapePosition;
import Cactus.Design.PaneModule.AXIS.Universe;
import Cactus.Design.PaneModule.PANE.FORM.RectangleForm;
import Cactus.Design.PaneModule.PANE.PROFILE.RectangleProfile;
import Cactus.Design.PaneModule.PANE.PROFILE.TYPE.Profile;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Pane4Test extends JPanel
{
    public final Universe universe = new Universe();
    public final Profile rect1ShapeProfile1 = new RectangleProfile(new ShapePosition(50, 50), new RectangleForm(40, 30));

    public final Profile rectWestNorthShapeProfile = new RectangleProfile(new ShapePosition(0, 0), new RectangleForm(5, 5));
    public final Profile rectWestSouthShapeProfile = new RectangleProfile(new ShapePosition(0, universe.spaceHeight -5), new RectangleForm(5, 5));
    public final Profile rectEastNorthShapeProfile = new RectangleProfile(new ShapePosition(universe.spaceWidth -5, 0), new RectangleForm(5, 5));
    public final Profile rectEastSouthShapeProfile = new RectangleProfile(new ShapePosition(universe.spaceWidth -5, universe.spaceHeight -5), new RectangleForm(5, 5));

    public Pane4Test()
    {
        int heightOfPane = 500;
        int widthOfPane = 500;
        this.setSize(new Dimension(widthOfPane, heightOfPane));
        this.setPreferredSize(this.getSize());
        universe.earthWidth = widthOfPane;
        universe.earthHeight = heightOfPane;
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
        Profile shapeProfileInEarth = universe.trans2EarthView(rect1ShapeProfile1);
        Rectangle2D r = new Rectangle2D.Double(
                shapeProfileInEarth.getPosition().getX(),
                shapeProfileInEarth.getPosition().getY(),
                shapeProfileInEarth.getForm().getWidth(),
                shapeProfileInEarth.getForm().getHeight());

        g2.draw(r);
        g2.fill(r);

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
        System.out.print("DEBUG Shape pos (" + shapeProfileInEarth.getPosition().getX() + ","+ shapeProfileInEarth.getPosition().getY() + ")") ;
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
