package Cactus.Design.PaneModule.PANE.MouseEvent;

import Cactus.Design.PaneModule.AXIS.Universe;
import Cactus.Design.PaneModule.PANE.FORM.OffsetForm;
import Cactus.Design.PaneModule.PANE.Pane;
import Cactus.test.MouseEvent.Pane4TestMouse;

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
    private int oldWidth = 0;
    private int oldHeight = 0;

    private boolean showFlag = false;

    public Listener4JFrame(Pane pane)
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
            pane.paneForm.offsetForm(new OffsetForm(widthChange, heightChange));
            Dimension dim = new Dimension(
                    (int) pane.paneForm.getWidth(),
                    (int) pane.paneForm.getHeight());
            pane.setSize(dim);
            pane.setPreferredSize(dim);
//            pane.universe.validatePosition();
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
