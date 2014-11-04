package Cactus.Design.PaneModule.PANE.FORM;

import Cactus.Design.PaneModule.PANE.FORM.TYPE.Form;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 11/4/14
 * Time: 6:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class OffsetForm extends ShapeForm
{
    public OffsetForm()
    {
    }

    public OffsetForm(double width, double height)
    {
        super(width, height);
    }

    public OffsetForm(Dimension dimension)
    {
        super(dimension);
    }

    @Override
    public Form getCopy()
    {
        return new OffsetForm(width,height);
    }
}
