package Cactus.Design.DataModule;

import Cactus.Design.PaneModule.AXIS.LogicSpace;
import Cactus.Design.PaneModule.AXIS.POSITION.Type.Position;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 10/23/14
 * Time: 4:24 PM
 */
public abstract class Field
{
    public abstract void setSize(Dimension size);

    public abstract Dimension getSize();

    public abstract void setAxis(Position pos);

    public abstract Position getAxis();

    public abstract Event getNode();

    public abstract void setNode(Event node);

    public abstract void setCoordinate(LogicSpace cor);

    public abstract LogicSpace getCoordinate();
}
