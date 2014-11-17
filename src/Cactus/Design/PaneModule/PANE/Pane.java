package Cactus.Design.PaneModule.PANE;


import Cactus.Design.DataModule.TYPE.CactusEvent;
import Cactus.Design.PaneModule.AXIS.Universe;
import Cactus.Design.PaneModule.PANE.FORM.SpaceForm;
import Cactus.Design.PaneModule.PANE.FORM.TYPE.Form;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 10/23/14
 * Time: 6:01 PM
 */
public abstract class Pane extends JPanel
{
    public final Universe universe;
    protected final Form universeForm;
    public final ArrayList<CactusEvent> eventList = new ArrayList<CactusEvent>();

    protected Pane(Form universeForm)
    {
        this.universeForm = new SpaceForm(universeForm);
        universe = new Universe(universeForm);
    }

    protected Pane(Form universeForm, ArrayList<CactusEvent> list)
    {
        eventList.addAll(list);
        this.universeForm = new SpaceForm(universeForm);
        this.universe = new Universe(universeForm);

    }
}
