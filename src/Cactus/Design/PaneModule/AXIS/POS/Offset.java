package Cactus.Design.PaneModule.AXIS.POS;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 10/30/14
 * Time: 5:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class Offset extends Position
{
    public Offset()
    {
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
