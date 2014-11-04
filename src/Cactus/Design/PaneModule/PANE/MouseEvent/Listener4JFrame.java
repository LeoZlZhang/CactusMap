package Cactus.Design.PaneModule.PANE.MouseEvent;

import Cactus.test.MouseEvent.Pane4TestMouse;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 11/2/14
 * Time: 8:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class Listener4JFrame implements ComponentListener
{
    private Pane4TestMouse pane;
    private int oldWidth = 0;
    private int oldHeight = 0;

    private boolean showFlag = false;

    public Listener4JFrame(Pane4TestMouse pane)
    {
        this.pane = pane;
    }

    @Override
    public void componentResized(ComponentEvent e)
    {
        if (showFlag)
        {
            int widthChange = (int) e.getComponent().getSize().getWidth() - oldWidth;
            int heightChange = (int) e.getComponent().getSize().getHeight() - oldHeight;
            pane.widthOfPane = pane.widthOfPane + widthChange;
            pane.heightOfPane = pane.heightOfPane + heightChange;
            pane.universe.earthWidth = pane.widthOfPane;
            pane.universe.earthHeight = pane.heightOfPane;
            pane.setSize(new Dimension(pane.widthOfPane, pane.heightOfPane));
            pane.setPreferredSize(new Dimension(pane.widthOfPane, pane.heightOfPane));
            pane.universe.validatePosition();
            pane.repaint();
        }
        oldWidth = e.getComponent().getWidth();
        oldHeight = e.getComponent().getHeight();
    }

    @Override
    public void componentMoved(ComponentEvent e)
    {
    }

    @Override
    public void componentShown(ComponentEvent e)
    {
        showFlag = true;
    }

    @Override
    public void componentHidden(ComponentEvent e)
    {
    }
}
