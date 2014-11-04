package Cactus.Design.PaneModule.PANE.FORM;

import Cactus.Design.PaneModule.PANE.FORM.TYPE.Form;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 10/31/14
 * Time: 4:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class UniverseForm extends ShapeForm
{
    public UniverseForm()
    {
        super();
    }

    public UniverseForm(double width, double height)
    {
        super(width, height);
    }

    @Override
    public Form getCopy()
    {
        return new UniverseForm(this.width, this.height);
    }
}
