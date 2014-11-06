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

    public OffsetForm(double width, double height)
    {
        this.width = width;
        this.height = height;
    }

    public OffsetForm(Dimension dimension)
    {
        this.width = dimension.width;
        this.height = dimension.height;
    }

    @Override
    public void setWidth(double width)
    {
        this.width = width;
    }

    @Override
    public void setHeight(double height)
    {
        this.height = height;
    }

    @Override
    public Form getCopy()
    {
        return new OffsetForm(width,height);
    }
}
