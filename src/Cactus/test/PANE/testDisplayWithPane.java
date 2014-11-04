package Cactus.test.PANE;

import Cactus.Design.PaneModule.AXIS.AxisSuit;
import Cactus.Design.PaneModule.AXIS.POSITION.Offset;
import Cactus.Design.PaneModule.PANE.PROFILE.TYPE.Profile;
import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.*;

import static org.testng.Assert.assertEquals;

public class testDisplayWithPane
{
    //    public static void main(String[] args) throws InterruptedException
    @Test
    public void testZoomInAndMove() throws InterruptedException
    {
        Pane4Test pane = new Pane4Test();
        pane.setBackground(new Color(200, 200, 169));
        JFrame frame = new JFrame("test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(pane, BorderLayout.WEST);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        Thread.sleep(2000);
        Profile oldRect1EarthShapeProfile = pane.universe.trans2EarthView(pane.rect1ShapeProfile1);
        int n = 30;
        for (int i = 0; i < n; i++)
        {
            Thread.sleep(42);
            pane.universe.zoomIn(pane.universe.trans2EarthView(oldRect1EarthShapeProfile.getPosition()));
            pane.repaint();
        }
        Profile newRect1EarthShapeProfile = pane.universe.trans2EarthView(pane.rect1ShapeProfile1);
        assertEquals(newRect1EarthShapeProfile.getPosition().getX(), oldRect1EarthShapeProfile.getPosition().getX());
        assertEquals(newRect1EarthShapeProfile.getPosition().getY(), oldRect1EarthShapeProfile.getPosition().getY());
        assertEquals(newRect1EarthShapeProfile.getForm().getWidth(), oldRect1EarthShapeProfile.getForm().getWidth() * (1 + 0.05 * n));
        assertEquals(newRect1EarthShapeProfile.getForm().getHeight(), oldRect1EarthShapeProfile.getForm().getHeight() * (1 + 0.05 * n));

        oldRect1EarthShapeProfile = pane.universe.trans2EarthView(pane.rect1ShapeProfile1);
        n = 5;
        for (int i = 0; i < n; i++)
        {
            Thread.sleep(42);
            pane.universe.moveSpace(new Offset(5, 5));
            pane.repaint();
        }
        newRect1EarthShapeProfile = pane.universe.trans2EarthView(pane.rect1ShapeProfile1);

        assertEquals(newRect1EarthShapeProfile.getPosition().getX(), oldRect1EarthShapeProfile.getPosition().getX() + 5 * n);
        assertEquals(newRect1EarthShapeProfile.getPosition().getY(), oldRect1EarthShapeProfile.getPosition().getY() + 5 * n);
        assertEquals(newRect1EarthShapeProfile.getForm().getWidth(), oldRect1EarthShapeProfile.getForm().getWidth());
        assertEquals(newRect1EarthShapeProfile.getForm().getHeight(), oldRect1EarthShapeProfile.getForm().getHeight());


        pane.universe.moveSpace(new Offset(500, 500));
        pane.repaint();
        assertEquals(pane.universe.getPositionSuit().getPosition(AxisSuit.Direction.COR).getX(), 0.0);
        assertEquals(pane.universe.getPositionSuit().getPosition(AxisSuit.Direction.COR).getY(), 0.0);


        oldRect1EarthShapeProfile = pane.universe.trans2EarthView(pane.rect1ShapeProfile1);
        n = 30;
        for (int i = 0; i < n; i++)
        {
            Thread.sleep(42);
            pane.universe.zoomOut(pane.universe.trans2EarthView(oldRect1EarthShapeProfile.getPosition()));
            pane.repaint();
        }
        newRect1EarthShapeProfile = pane.universe.trans2EarthView(pane.rect1ShapeProfile1);

        assertEquals(newRect1EarthShapeProfile.getPosition().getX(), pane.rect1ShapeProfile1.getPosition().getX());
        assertEquals(newRect1EarthShapeProfile.getPosition().getY(), pane.rect1ShapeProfile1.getPosition().getY());
        assertEquals(newRect1EarthShapeProfile.getForm().getWidth(), oldRect1EarthShapeProfile.getForm().getWidth() / (1 + 0.05 * n));
        assertEquals(newRect1EarthShapeProfile.getForm().getHeight(), oldRect1EarthShapeProfile.getForm().getHeight() / (1 + 0.05 * n));

        Thread.sleep(3000);

    }


}
