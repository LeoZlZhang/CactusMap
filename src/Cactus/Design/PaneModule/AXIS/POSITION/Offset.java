package Cactus.Design.PaneModule.AXIS.POSITION;

import Cactus.Design.PaneModule.AXIS.POSITION.Type.Position;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 10/30/14
 * Time: 5:53 PM
 */
public class Offset extends Position
{
    public Offset()
    {
        super();
    }

    public Offset(double x, double y)
    {
        super(x, y);
    }

    @Override
    public Position getCopy()
    {
        return new Offset(super.getX(), super.getY()) ;
    }
}
