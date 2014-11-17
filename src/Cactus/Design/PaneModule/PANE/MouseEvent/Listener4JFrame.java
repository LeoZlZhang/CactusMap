package Cactus.Design.PaneModule.PANE.MouseEvent;

import Cactus.Design.PaneModule.PANE.Pane;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 11/2/14
 * Time: 8:17 PM
 */
public class Listener4JFrame implements ComponentListener
{
    private Pane pane;

    public Listener4JFrame(Pane pane)
    {
        this.pane = pane;
    }

    @Override
    public void componentResized(ComponentEvent e)
    {
        pane.universe.validatePosition(pane.getSize());
        pane.repaint();
    }

    @Override
    public void componentMoved(ComponentEvent e)
    {
    }

    @Override
    public void componentShown(ComponentEvent e)
    {
    }

    @Override
    public void componentHidden(ComponentEvent e)
    {
    }
}
