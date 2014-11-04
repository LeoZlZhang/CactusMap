package Cactus.Design.PaneModule.AXIS;

import Cactus.Design.PaneModule.AXIS.POSITION.CorePosition;
import Cactus.Design.PaneModule.AXIS.POSITION.Type.Position;
import Cactus.Design.PaneModule.AXIS.TYPE.Space;
import Cactus.Design.PaneModule.PANE.FORM.UniverseForm;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 10/23/14
 * Time: 6:18 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class LogicSpace implements Space
{
    protected final Amplifier amplifier;
    protected final AxisSuit positionSuit;
    public final int spaceWidth = 2000;
    public final int spaceHeight = 2000;

    protected LogicSpace()
    {
        this.amplifier = new Amplifier();
        this.positionSuit = new AxisSuit(new CorePosition(0, 0), new UniverseForm(spaceWidth * amplifier.get(), spaceHeight * amplifier.get()));
    }

    protected LogicSpace(Position corPos)
    {
        this.amplifier = new Amplifier();
        this.positionSuit = new AxisSuit(corPos, new UniverseForm(spaceWidth * amplifier.get(), spaceHeight * amplifier.get()));
    }

    public void zoomIn()
    {
        amplifier.zoomIn();
        positionSuit.setUniverseForm(new UniverseForm(spaceWidth * amplifier.get(), spaceHeight * amplifier.get()));
    }

    public void zoomOut()
    {
        amplifier.zoomOut();
        positionSuit.setUniverseForm(new UniverseForm(spaceWidth * amplifier.get(), spaceHeight * amplifier.get()));
    }

    public void moveSpace(Position offset)
    {
        Position pos = positionSuit.getPosition(AxisSuit.Direction.COR);
        pos.setX(pos.getX() + offset.getX());
        pos.setY(pos.getY() + offset.getY());
        positionSuit.setCorPosition(pos);
    }

    public Double getAmplifierValue()
    {
        return amplifier.get();
    }

    public abstract Position trans2EarthView(Position posInUniverse);

    public AxisSuit getPositionSuit()
    {
        return positionSuit.getCopy();
    }
}
