package Cactus.Design.PaneModule.AXIS;

import Cactus.Design.PaneModule.AXIS.POSITION.CorePosition;
import Cactus.Design.PaneModule.AXIS.POSITION.Type.Position;
import Cactus.Design.PaneModule.AXIS.TYPE.Space;
import Cactus.Design.PaneModule.PANE.FORM.SpaceForm;
import Cactus.Design.PaneModule.PANE.FORM.TYPE.Form;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 10/23/14
 * Time: 6:18 PM
 */
public abstract class LogicSpace implements Space
{
    protected final Amplifier amplifier;
    protected final AxisSuit positionSuit;
    protected final Form spaceForm;

    protected LogicSpace(Form spaceForm)
    {
        this.spaceForm = new SpaceForm(spaceForm.getWidth(),spaceForm.getHeight());
        this.amplifier = new Amplifier();
        this.positionSuit = new AxisSuit(new CorePosition(0, 0), new SpaceForm(spaceForm.getWidth() * amplifier.get(), spaceForm.getHeight() * amplifier.get()));
    }

//    protected LogicSpace(Position corPos)
//    {
//        this.amplifier = new Amplifier();
//        this.positionSuit = new AxisSuit(corPos, new SpaceForm(spaceForm.getWidth() * amplifier.get(), spaceForm.getHeight() * amplifier.get()));
//    }

    public void zoomIn()
    {
        amplifier.zoomIn();
        positionSuit.setUniverseForm(new SpaceForm(spaceForm.getWidth() * amplifier.get(), spaceForm.getHeight() * amplifier.get()));
    }

    public void zoomOut()
    {
        amplifier.zoomOut();
        positionSuit.setUniverseForm(new SpaceForm(spaceForm.getWidth() * amplifier.get(), spaceForm.getHeight() * amplifier.get()));
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
