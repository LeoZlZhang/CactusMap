package Cactus.Design.PaneModule.AXIS.POSITION;

import Cactus.Design.PaneModule.AXIS.POSITION.Type.Position;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 10/30/14
 * Time: 3:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class CorePosition extends Position
{
    public CorePosition()
    {
        super();
    }

    public CorePosition(double x, double y)
    {
        super(x, y);
    }

    @Override
    public Position getCopy()
    {
        return new CorePosition(super.getX(), super.getY()) ;
    }


}
