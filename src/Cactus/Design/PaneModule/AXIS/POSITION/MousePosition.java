package Cactus.Design.PaneModule.AXIS.POSITION;

import Cactus.Design.PaneModule.AXIS.POSITION.Type.Position;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 10/30/14
 * Time: 2:58 PM
 */
public class MousePosition extends Position
{
    public MousePosition(double x, double y)
    {
        super(x, y);
    }

    public MousePosition(Point pos)
    {
        super(pos);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public Position getCopy()
    {
        return new MousePosition(super.getX(), super.getY()) ;
    }

}
