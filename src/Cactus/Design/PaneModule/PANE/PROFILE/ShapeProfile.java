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
 * To change this template use File | Settings | File Templates.
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
        this.position = new ShapePosition();
        this.position.setX(position.getX());
        this.position.setY(position.getY());
        this.shapeForm = new RectangleForm();
        this.shapeForm.setWidth(shapeForm.getWidth());
        this.shapeForm.setHeight(shapeForm.getHeight());
    }


    public Position getPosition()
    {
        return this.position.getCopy();
    }


    public Form getForm()
    {
        return this.shapeForm.getCopy();
    }
}
