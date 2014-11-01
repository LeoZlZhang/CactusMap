package Cactus.Design.DataModule;

import Cactus.Design.PaneModule.AXIS.LogicSpace;
import Cactus.Design.PaneModule.AXIS.POS.Position;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 10/23/14
 * Time: 4:24 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Field
{
    public abstract void setSize(Dimension size);

    public abstract Dimension getSize();

    public abstract void setAxis(Position pos);

    public abstract Position getAxis();

    public abstract Node getNode();

    public abstract void setNode(Node node);

    public abstract void setCoordinate(LogicSpace cor);

    public abstract LogicSpace getCoordinate();
}
