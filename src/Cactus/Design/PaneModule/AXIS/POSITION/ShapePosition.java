package Cactus.Design.PaneModule.AXIS.POSITION;

import Cactus.Design.PaneModule.AXIS.POSITION.Type.Position;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 10/30/14
 * Time: 3:53 PM
 */
public class ShapePosition extends Position
{
    public ShapePosition()
    {
        super();
    }

    public ShapePosition(double x, double y)
    {
        super(x, y);
    }

    @Override
    public Position getCopy()
    {
        return new ShapePosition(super.getX(), super.getY());
    }
}
