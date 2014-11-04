package Cactus.Design.PaneModule.PANE.PROFILE;

import Cactus.Design.PaneModule.AXIS.POSITION.Type.Position;
import Cactus.Design.PaneModule.PANE.FORM.TYPE.Form;
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
