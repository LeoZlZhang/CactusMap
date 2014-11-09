package Cactus.Design.PaneModule.AXIS.POSITION.Type;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 10/23/14
 * Time: 7:33 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Position
{
    private double x;
    private double y;

    public Position()
    {
        x = 0;
        y = 0;
    }

    public Position(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public Position(Point pos)
    {
        this.x = pos.getX();
        this.y = pos.getY();
    }

    public Position(Position pos)
    {
        this.x = pos.getX();
        this.y = pos.getY();
    }

    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public abstract Position getCopy();
}
