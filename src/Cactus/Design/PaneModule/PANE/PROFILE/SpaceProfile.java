package Cactus.Design.PaneModule.PANE.PROFILE;

import Cactus.Design.PaneModule.AXIS.POSITION.CorePosition;
import Cactus.Design.PaneModule.AXIS.POSITION.Type.Position;
import Cactus.Design.PaneModule.PANE.FORM.TYPE.Form;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 10/30/14
 * Time: 3:02 PM
 */
public class SpaceProfile extends ShapeProfile
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

    public SpaceProfile(Position corPos, Form f)
    {
        super(corPos, f);
        setSpaceProfile(corPos, f);
    }

    public void setSpaceProfile(Position corPos, Form f)
    {
        this.shapeForm.setWidth(f.getWidth());
        this.shapeForm.setHeight(f.getHeight());
        this.posWN.setX(corPos.getX());
        this.posWN.setY(corPos.getY());
        this.posEN.setX(corPos.getX() + shapeForm.getWidth());
        this.posEN.setY(corPos.getY());
        this.posWS.setX(corPos.getX());
        this.posWS.setY(corPos.getY() + shapeForm.getHeight());
        this.posES.setX(corPos.getX() + shapeForm.getWidth());
        this.posES.setY(corPos.getY() + shapeForm.getHeight());
        this.posCenter.setX(corPos.getX() + shapeForm.getWidth() / 2);
        this.posCenter.setY(corPos.getY() + shapeForm.getHeight() / 2);
    }

    public void setCorPosition(Position corPos)
    {
        this.posWN.setX(corPos.getX());
        this.posWN.setY(corPos.getY());
        this.posEN.setX(corPos.getX() + shapeForm.getWidth());
        this.posEN.setY(corPos.getY());
        this.posWS.setX(corPos.getX());
        this.posWS.setY(corPos.getY() + shapeForm.getHeight());
        this.posES.setX(corPos.getX() + shapeForm.getWidth());
        this.posES.setY(corPos.getY() + shapeForm.getHeight());
        this.posCenter.setX(corPos.getX() + shapeForm.getWidth() / 2);
        this.posCenter.setY(corPos.getY() + shapeForm.getHeight() / 2);
    }

    public void setSpaceForm(Form f)
    {
        this.shapeForm.setWidth(f.getWidth());
        this.shapeForm.setHeight(f.getHeight());
        this.posEN.setX(posWN.getX() + shapeForm.getWidth());
        this.posWS.setY(posWN.getY() + shapeForm.getHeight());
        this.posES.setX(posWN.getX() + shapeForm.getWidth());
        this.posES.setY(posWN.getY() + shapeForm.getHeight());
        this.posCenter.setX(posWN.getX() + shapeForm.getWidth() / 2);
        this.posCenter.setY(posWN.getY() + shapeForm.getHeight() / 2);
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

    public SpaceProfile getCopy()
    {
        return new SpaceProfile(this.posWN, this.shapeForm);
    }
}
