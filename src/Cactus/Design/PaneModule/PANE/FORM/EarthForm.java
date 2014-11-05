package Cactus.Design.PaneModule.PANE.FORM;

import Cactus.Design.PaneModule.PANE.FORM.TYPE.Form;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 11/4/14
 * Time: 4:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class EarthForm extends ShapeForm
{
    public EarthForm(double width, double height)
    {
        super(width, height);
    }

    public EarthForm(Form form)
    {
        super(form);
    }

    @Override
    public Form getCopy()
    {
        return new EarthForm(this.width, this.height);
    }

}
