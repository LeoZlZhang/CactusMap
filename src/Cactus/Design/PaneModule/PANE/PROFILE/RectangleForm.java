package Cactus.Design.PaneModule.PANE.PROFILE;

import Cactus.Design.PaneModule.PANE.PROFILE.TYPE.Form;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 10/31/14
 * Time: 3:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class RectangleForm extends ShapeForm
{
    public RectangleForm()
    {
        super();
    }

    public RectangleForm(double width, double height)
    {
        super(width, height);
    }

    @Override
    public Form getCopy()
    {
        return new RectangleForm(this.width, this.height);
    }
}