package Cactus.Design.PaneModule.AXIS;

import Cactus.Design.PaneModule.AXIS.POS.CorePosition;
import Cactus.Design.PaneModule.AXIS.POS.Position;
import Cactus.Design.PaneModule.PANE.PROFILE.TYPE.Form;
import Cactus.Design.PaneModule.PANE.PROFILE.UniverseForm;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 10/30/14
 * Time: 3:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class AxisSuit
{
    public static enum Direction
    {
        COR, WEST_NORTH, WEST_SOUTH, EAST_NORTH, EAST_SOUTH, CENTER
    }

    /*      .WN(COR)----------->.EN
            |
            |        .Center
            |
            .WS                 .ES
     */
    private Position posWN = new CorePosition();
    private Position posWS = new CorePosition();
    private Position posEN = new CorePosition();
    private Position posES = new CorePosition();
    private Position posCenter = new CorePosition();
    private Form universeForm = new UniverseForm(100, 100);

    public AxisSuit(Form f)
    {
        setPositionSuit(new CorePosition(0, 0), f);
    }

    public AxisSuit(Position corPos, Form f)
    {
        setPositionSuit(corPos, f);
    }

    public void setPositionSuit(Position corPos, Form f)
    {
        this.universeForm.setWidth(f.getWidth());
        this.universeForm.setHeight(f.getHeight());
        this.posWN.setX(corPos.getX());
        this.posWN.setY(corPos.getY());
        this.posEN.setX(corPos.getX() + universeForm.getWidth());
        this.posEN.setY(corPos.getY());
        this.posWS.setX(corPos.getX());
        this.posWS.setY(corPos.getY() + universeForm.getHeight());
        this.posES.setX(corPos.getX() + universeForm.getWidth());
        this.posES.setY(corPos.getY() + universeForm.getHeight());
        this.posCenter.setX(corPos.getX() + universeForm.getWidth() / 2);
        this.posCenter.setY(corPos.getY() + universeForm.getHeight() / 2);
    }

    public void setCorPosition(Position corPos)
    {
        this.posWN.setX(corPos.getX());
        this.posWN.setY(corPos.getY());
        this.posEN.setX(corPos.getX() + universeForm.getWidth());
        this.posEN.setY(corPos.getY());
        this.posWS.setX(corPos.getX());
        this.posWS.setY(corPos.getY() + universeForm.getHeight());
        this.posES.setX(corPos.getX() + universeForm.getWidth());
        this.posES.setY(corPos.getY() + universeForm.getHeight());
        this.posCenter.setX(corPos.getX() + universeForm.getWidth() / 2);
        this.posCenter.setY(corPos.getY() + universeForm.getHeight() / 2);
    }

    public void setUniverseForm(Form f)
    {
        this.universeForm.setWidth(f.getWidth());
        this.universeForm.setHeight(f.getHeight());
        this.posEN.setX(posWN.getX() + universeForm.getWidth());
        this.posWS.setY(posWN.getY() + universeForm.getHeight());
        this.posES.setX(posWN.getX() + universeForm.getWidth());
        this.posES.setY(posWN.getY() + universeForm.getHeight());
        this.posCenter.setX(posWN.getX() + universeForm.getWidth() / 2);
        this.posCenter.setY(posWN.getY() + universeForm.getHeight() / 2);
    }

    public Position getPosition(Direction direction)
    {
        CorePosition pos = new CorePosition();
        switch (direction)
        {
            case COR:
                pos.setX(this.posWN.getX());
                pos.setY(this.posWN.getY());
                break;
            case WEST_NORTH:
                pos.setX(this.posWN.getX());
                pos.setY(this.posWN.getY());
                break;
            case WEST_SOUTH:
                pos.setX(this.posWS.getX());
                pos.setY(this.posWS.getY());
                break;
            case EAST_NORTH:
                pos.setX(this.posEN.getX());
                pos.setY(this.posEN.getY());
                break;
            case EAST_SOUTH:
                pos.setX(this.posES.getX());
                pos.setY(this.posES.getY());
                break;
            case CENTER:
                pos.setX(this.posCenter.getX());
                pos.setY(this.posCenter.getY());
                break;
        }
        return pos;
    }

    public Form getUniverseForm()
    {
        return universeForm.getCopy();
    }

    public double getWidth()
    {
        return universeForm.getWidth();
    }

    public double getHeight()
    {
        return universeForm.getHeight();
    }

    public AxisSuit getCopy()
    {
        return new AxisSuit(this.posWN, this.universeForm);
    }
}
