package Cactus.Design.PaneModule.PANE.FORM;

import Cactus.Design.PaneModule.PANE.FORM.TYPE.Form;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 10/31/14
 * Time: 4:53 PM
 */
public class SpaceForm extends ShapeForm
{
    public SpaceForm(double width, double height)
    {
        super(width, height);
    }

    public SpaceForm(Dimension dimension)
    {
        super(dimension);
    }

    @Override
    public Form getCopy()
    {
        return new SpaceForm(this.width, this.height);
    }
}
