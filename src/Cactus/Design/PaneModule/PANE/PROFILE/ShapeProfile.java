package Cactus.Design.PaneModule.PANE.PROFILE;

import Cactus.Design.PaneModule.AXIS.POSITION.Type.Position;
import Cactus.Design.PaneModule.AXIS.POSITION.ShapePosition;
import Cactus.Design.PaneModule.PANE.FORM.RectangleForm;
import Cactus.Design.PaneModule.PANE.FORM.TYPE.Form;
import Cactus.Design.PaneModule.PANE.PROFILE.TYPE.Profile;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 10/27/14
 * Time: 8:23 PM
 */
public abstract class ShapeProfile implements Profile
{
    protected final Position position;
    protected final Form shapeForm;

    public ShapeProfile()
    {
        this.position = new ShapePosition();
        this.shapeForm = new RectangleForm(1, 1);
    }

    public ShapeProfile(Position position, Form shapeForm)
    {
        this.position = new ShapePosition(position.getX(), position.getY());
        this.shapeForm = new RectangleForm(shapeForm.getWidth(), shapeForm.getHeight());
    }

    public ShapeProfile(Profile profile)
    {
        this.position = new ShapePosition(profile.getPosition().getCopy());
        this.shapeForm = new RectangleForm(profile.getForm().getCopy());
    }


    @Override
    public Position getPosition()
    {
        return this.position;
    }

    @Override
    public Form getForm()
    {
        return this.shapeForm;
    }


}
