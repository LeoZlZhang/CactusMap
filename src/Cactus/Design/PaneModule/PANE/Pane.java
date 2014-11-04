package Cactus.Design.PaneModule.PANE;


import Cactus.Design.PaneModule.AXIS.TYPE.Space;
import Cactus.Design.PaneModule.AXIS.Universe;
import Cactus.Design.PaneModule.PANE.FORM.EarthForm;
import Cactus.Design.PaneModule.PANE.FORM.SpaceForm;
import Cactus.Design.PaneModule.PANE.FORM.TYPE.Form;

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
    private EarthForm earthForm;
    private SpaceForm universeForm;
    public final Universe universe;

    protected Pane(Dimension universeForm,Dimension earthForm)
    {
        this.earthForm = new EarthForm(earthForm);
        this.universeForm = new SpaceForm(universeForm);
        universe = new Universe(this.universeForm, this.earthForm);
    }

//    public abstract void addCactus(Object obj);
//
//    public abstract void removeCactus(Object obj);

}
