package Cactus.Design.PaneModule.PANE.PROFILE;

import Cactus.Design.PaneModule.AXIS.POS.Position;
import Cactus.Design.PaneModule.PANE.PROFILE.TYPE.Form;
import Cactus.Design.PaneModule.PANE.PROFILE.TYPE.Profile;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 10/31/14
 * Time: 4:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class RectangleProfile extends ShapeProfile
{
    public RectangleProfile(Position position, Form shapeForm)
    {
        super(position, shapeForm);
    }

    public RectangleProfile()
    {
        super();
    }

    public Profile getCopy()
    {
        return new RectangleProfile(this.position, this.shapeForm);
    }
}
