package Cactus.Design.PaneModule.PANE.PROFILE;

import Cactus.Design.PaneModule.AXIS.POSITION.Type.Position;
import Cactus.Design.PaneModule.PANE.FORM.TYPE.Form;
import Cactus.Design.PaneModule.PANE.PROFILE.TYPE.Profile;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 10/31/14
 * Time: 4:49 PM
 */
public class RectangleProfile extends ShapeProfile
{

    public RectangleProfile()
    {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public RectangleProfile(Position position, Form shapeForm)
    {
        super(position, shapeForm);
    }

    public RectangleProfile(Profile profile)
    {
        super(profile);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public void bigger()
    {
        this.shapeForm.offsetForm(1,1);
    }

    public void smaller()
    {
        this.shapeForm.offsetForm(-1,-1);
    }

    public Profile getCopy()
    {
        return new RectangleProfile(this.position, this.shapeForm);
    }
}
