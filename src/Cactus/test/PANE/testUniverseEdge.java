package Cactus.test.PANE;

import Cactus.Design.PaneModule.AXIS.AxisSuit;
import Cactus.Design.PaneModule.AXIS.POSITION.MousePosition;
import Cactus.Design.PaneModule.AXIS.POSITION.Offset;
import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.*;

import static org.testng.Assert.assertEquals;

public class testUniverseEdge
{
    @Test
    public void testEdgeMoveAxis() throws InterruptedException
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

        pane.universe.moveSpace(new Offset(-3000, 0));
        pane.repaint();
        Thread.sleep(2000);
        assertEquals(pane.universe.getPositionSuit().getPosition(AxisSuit.Direction.EAST_SOUTH).getX(), (double) pane.getWidth());
        assertEquals(pane.universe.getPositionSuit().getPosition(AxisSuit.Direction.EAST_SOUTH).getY(), (double) 2000);

        pane.universe.moveSpace(new Offset(0, -3000));
        pane.repaint();
        Thread.sleep(2000);
        assertEquals(pane.universe.getPositionSuit().getPosition(AxisSuit.Direction.EAST_SOUTH).getX(), (double) pane.getWidth());
        assertEquals(pane.universe.getPositionSuit().getPosition(AxisSuit.Direction.EAST_SOUTH).getY(), (double) pane.getHeight());

        pane.universe.moveSpace(new Offset(3000, 0));
        pane.repaint();
        Thread.sleep(2000);
        assertEquals(pane.universe.getPositionSuit().getPosition(AxisSuit.Direction.EAST_SOUTH).getX(), (double) 2000);
        assertEquals(pane.universe.getPositionSuit().getPosition(AxisSuit.Direction.EAST_SOUTH).getY(), (double) pane.getHeight());

        pane.universe.moveSpace(new Offset(0, 3000));
        pane.repaint();
        Thread.sleep(2000);
        assertEquals(pane.universe.getPositionSuit().getPosition(AxisSuit.Direction.EAST_SOUTH).getX(), (double) 2000);
        assertEquals(pane.universe.getPositionSuit().getPosition(AxisSuit.Direction.EAST_SOUTH).getY(), (double) 2000);
    }

    @Test
    public void testEdgeZoomOut() throws InterruptedException
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

        int N = 30;
        for (int i = 0; i < N; i++)
        {
            Thread.sleep(1000 / 35);
            pane.universe.zoomIn();
            pane.repaint();
        }
        Thread.sleep(2000);

        N = 130;
        for (int i = 0; i < N; i++)
        {
            Thread.sleep(1000 / 35);
            pane.universe.zoomOut();
            pane.repaint();
            System.out.print(pane.universe.getAmplifierValue());
        }
        assertEquals(pane.universe.getPositionSuit().getPosition(AxisSuit.Direction.EAST_SOUTH).getX(), (double) pane.getWidth());
        assertEquals(pane.universe.getPositionSuit().getPosition(AxisSuit.Direction.EAST_SOUTH).getY(), (double) pane.getHeight());
        assertEquals(pane.universe.getPositionSuit().getPosition(AxisSuit.Direction.COR).getX(), 0.0);
        assertEquals(pane.universe.getPositionSuit().getPosition(AxisSuit.Direction.COR).getY(), 0.0);
        Thread.sleep(2000);

        N = 50;
        for (int i = 0; i < N; i++)
        {
            Thread.sleep(1000 / 35);
            pane.universe.zoomIn(new MousePosition(pane.getWidth() / 2, pane.getHeight() / 2));
            pane.repaint();
        }
        Thread.sleep(2000);

        N = 100;
        for (int i = 0; i < N; i++)
        {
            Thread.sleep(1000 / 35);
            pane.universe.zoomOut(new MousePosition(pane.getWidth(), pane.getHeight()));
            pane.repaint();
        }
        assertEquals(pane.universe.getPositionSuit().getPosition(AxisSuit.Direction.EAST_SOUTH).getX(), (double) pane.getWidth());
        assertEquals(pane.universe.getPositionSuit().getPosition(AxisSuit.Direction.EAST_SOUTH).getY(), (double) pane.getHeight());
        assertEquals(pane.universe.getPositionSuit().getPosition(AxisSuit.Direction.COR).getX(), 0.0);
        assertEquals(pane.universe.getPositionSuit().getPosition(AxisSuit.Direction.COR).getY(), 0.0);
        Thread.sleep(2000);

    }

    @Test
    public void testEdgeZoomInAndMove() throws InterruptedException
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

        int N = 30;
        for (int i = 0; i < N; i++)
        {
            Thread.sleep(1000 / 35);
            pane.universe.zoomIn();
            pane.repaint();
        }
        Thread.sleep(2000);
        pane.universe.moveSpace(new Offset(-30000, 0));
        pane.repaint();
        Thread.sleep(2000);
        assertEquals(pane.universe.getPositionSuit().getPosition(AxisSuit.Direction.EAST_SOUTH).getX(), (double) pane.getWidth());
        assertEquals(pane.universe.getPositionSuit().getPosition(AxisSuit.Direction.EAST_SOUTH).getY(), (double) 2000 * pane.universe.getAmplifierValue());

        pane.universe.moveSpace(new Offset(0, -30000));
        pane.repaint();
        Thread.sleep(2000);
        assertEquals(pane.universe.getPositionSuit().getPosition(AxisSuit.Direction.EAST_SOUTH).getX(), (double) pane.getWidth());
        assertEquals(pane.universe.getPositionSuit().getPosition(AxisSuit.Direction.EAST_SOUTH).getY(), (double) pane.getHeight());

        pane.universe.moveSpace(new Offset(30000, 0));
        pane.repaint();
        Thread.sleep(2000);
        assertEquals(pane.universe.getPositionSuit().getPosition(AxisSuit.Direction.EAST_SOUTH).getX(), (double) 2000 * pane.universe.getAmplifierValue());
        assertEquals(pane.universe.getPositionSuit().getPosition(AxisSuit.Direction.EAST_SOUTH).getY(), (double) pane.getHeight());

        pane.universe.moveSpace(new Offset(0, 30000));
        pane.repaint();
        Thread.sleep(2000);
        assertEquals(pane.universe.getPositionSuit().getPosition(AxisSuit.Direction.EAST_SOUTH).getX(), (double) 2000 * pane.universe.getAmplifierValue());
        assertEquals(pane.universe.getPositionSuit().getPosition(AxisSuit.Direction.EAST_SOUTH).getY(), (double) 2000 * pane.universe.getAmplifierValue());
    }
}
