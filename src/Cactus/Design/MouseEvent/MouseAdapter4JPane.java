package Cactus.Design.MouseEvent;

import Cactus.Design.PaneModule.AXIS.POS.MousePosition;
import Cactus.Design.PaneModule.AXIS.POS.Offset;
import Cactus.Design.PaneModule.AXIS.POS.Position;
import Cactus.test.MouseEvent.Pane4TestMouse;
import Cactus.test.PANE.Pane4Test;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 10/23/14
 * Time: 4:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class MouseAdapter4JPane extends MouseAdapter
{
    private Pane4TestMouse pane;
    private boolean mousePressed = false;
    private Position startPos;
    private Position endPos;

    public MouseAdapter4JPane(Pane4TestMouse pane)
    {
        this.pane = pane;
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        startPos = new MousePosition(e.getPoint());
        endPos = null;
        mousePressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        mousePressed = false;
        startPos = null;
        endPos = null;
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
//        System.out.println("DEBUG mouse moved");
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e)
    {
        if(e.getWheelRotation()>0)
        {
            pane.universe.zoomOut(new MousePosition(e.getPoint()));
            pane.repaint();
        }
        else if(e.getWheelRotation() < 0)
        {
            pane.universe.zoomIn(new MousePosition(e.getPoint()));
            pane.repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        if (mousePressed)
        {
            endPos = new MousePosition(e.getPoint());
            if (pane != null && startPos != null && endPos != null)
            {
                double xOffset = endPos.getX() - startPos.getX();
                double yOffset = endPos.getY() - startPos.getY();
                pane.universe.moveSpace(new Offset(xOffset, yOffset));
                pane.repaint();
                startPos.setX(endPos.getX());
                startPos.setY(endPos.getY());
            }

        }
    }
}
