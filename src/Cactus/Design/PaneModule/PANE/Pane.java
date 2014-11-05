package Cactus.Design.PaneModule.PANE;


import Cactus.Design.PaneModule.AXIS.Universe;
import Cactus.Design.PaneModule.PANE.FORM.EarthForm;
import Cactus.Design.PaneModule.PANE.FORM.SpaceForm;
import Cactus.Design.PaneModule.PANE.FORM.TYPE.Form;
import Cactus.Design.PaneModule.PANE.MouseEvent.MouseAdapter4JPane;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 10/23/14
 * Time: 6:01 PM
 */
public abstract class Pane extends JPanel //implements MouseAdapter4JPane
{
    public final Universe universe;
    public final Form paneForm;
    protected final Form universeForm;

    protected Pane(Form universeForm, Form paneForm)
    {
        this.paneForm = new EarthForm(paneForm);
        this.universeForm = new SpaceForm(universeForm);
        universe = new Universe(universeForm);
        this.setSize(
                new Dimension(
                        (int) this.paneForm.getWidth(),
                        (int) this.paneForm.getHeight()));
        this.setPreferredSize(this.getSize());
    }

    public void initialMouseAdapter(Pane pane)
    {
        MouseAdapter4JPane mouseAdp = new MouseAdapter4JPane(pane);
        this.addMouseListener(mouseAdp);
        this.addMouseMotionListener(mouseAdp);
        this.addMouseWheelListener(mouseAdp);
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);    //To change body of overridden methods use File | Settings | File Templates.
    }

    //Profile

//    public abstract void addCactus(Object obj);
//    public abstract void removeCactus(Object obj);

}
