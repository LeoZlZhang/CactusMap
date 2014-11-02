package Cactus.Design.PaneModule.AXIS;

import Cactus.Design.PaneModule.AXIS.POS.CorePosition;
import Cactus.Design.PaneModule.AXIS.POS.Position;
import Cactus.Design.PaneModule.PANE.PROFILE.*;
import Cactus.Design.PaneModule.PANE.PROFILE.TYPE.Form;
import Cactus.Design.PaneModule.PANE.PROFILE.TYPE.Profile;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 10/23/14
 * Time: 6:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class Universe extends LogicSpace
{
    public double earthWidth = 100;
    public double earthHeight = 100;

    public Universe()
    {
        super();
    }

    public Universe(Position pos)
    {
        super(pos);
    }

    private static double leaveXDecimal(double src, int X)
    {
        BigDecimal f = new BigDecimal(src);
        return f.setScale(X, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    public void zoomIn(Position mousePos)
    {
        Position pos = positionSuit.getPosition(AxisSuit.Direction.COR);
        Double distance2CorX = (mousePos.getX() - pos.getX()) / amplifier.get();
        Double distance2CorY = (mousePos.getY() - pos.getY()) / amplifier.get();
        amplifier.zoomIn();
        pos = new CorePosition(mousePos.getX() - distance2CorX * amplifier.get(), mousePos.getY() - distance2CorY * amplifier.get());
        Form shapeForm = new UniverseForm(super.spaceWidth * amplifier.get(), super.spaceHeight * amplifier.get());
        positionSuit.setPositionSuit(pos, shapeForm);
        validatePosition();
    }

    @Override
    public void zoomIn()
    {
        super.zoomIn();    //To change body of overridden methods use File | Settings | File Templates.
        validatePosition();
    }

    public void zoomOut(Position mousePos)
    {
        Position pos = positionSuit.getPosition(AxisSuit.Direction.COR);
        Double distance2CorX = (mousePos.getX() - pos.getX()) / amplifier.get();
        Double distance2CorY = (mousePos.getY() - pos.getY()) / amplifier.get();
        amplifier.zoomOut();
        validateUniverseSize();
        pos = new CorePosition(mousePos.getX() - distance2CorX * amplifier.get(), mousePos.getY() - distance2CorY * amplifier.get());
        ShapeForm shapeForm = new UniverseForm(super.spaceWidth * amplifier.get(), super.spaceHeight * amplifier.get());
        positionSuit.setPositionSuit(pos, shapeForm);
        validatePosition();
    }

    @Override
    public void zoomOut()
    {
        super.zoomOut();    //To change body of overridden methods use File | Settings | File Templates.
        validateUniverseSize();
        validatePosition();
    }


    @Override
    public void moveSpace(Position offset)
    {
        Position pos = positionSuit.getPosition(AxisSuit.Direction.COR);
        pos.setX(pos.getX() + offset.getX());
        pos.setY(pos.getY() + offset.getY());
        positionSuit.setCorPosition(pos);
        validatePosition();
    }


    public Position trans2EarthView(Position universePos)
    {
        return trans2EarthView(universePos, SCALE5);
    }

    public Position trans2EarthView(Position shapePositionInUniverse, int scale)
    {
        Position earthPos = new CorePosition();
        Position universeCorePos = positionSuit.getPosition(AxisSuit.Direction.COR);
        earthPos.setX(leaveXDecimal(universeCorePos.getX() + shapePositionInUniverse.getX() * this.amplifier.get(), scale));
        earthPos.setY(leaveXDecimal(universeCorePos.getY() + shapePositionInUniverse.getY() * this.amplifier.get(), scale));
        return earthPos;
    }

    public Profile trans2EarthView(Profile shapePorInUniverse)
    {
        Position shapePosInUniverse = trans2EarthView(shapePorInUniverse.getPosition());
        Form shapeFormInUniverse = new RectangleForm();
        shapeFormInUniverse.setWidth(shapePorInUniverse.getForm().getWidth() * this.amplifier.get());
        shapeFormInUniverse.setHeight(shapePorInUniverse.getForm().getHeight() * this.amplifier.get());
        Profile shapeProfileInUniverse = new RectangleProfile(shapePosInUniverse, shapeFormInUniverse);
        return shapeProfileInUniverse;
    }


    public void validatePosition()  // resize frame and move universe bugs here, change code.
    {
        Boolean reCalFlag = false;
        Position corePos = positionSuit.getPosition(AxisSuit.Direction.COR);
        Position opCorePos = positionSuit.getPosition(AxisSuit.Direction.EAST_SOUTH);
        System.out.println("DEBUG Math.max(0, (earthWidth - super.spaceWidth)) = " + Math.max(0, (earthWidth - spaceWidth * amplifier.get())));
        if (corePos.getX() >= Math.max(0, (earthWidth - spaceWidth * amplifier.get())))
        {
            corePos.setX(Math.max(0, (earthWidth - spaceWidth * amplifier.get())));
            reCalFlag = true;
        } else if (opCorePos.getX() < Math.min(earthWidth, spaceWidth * amplifier.get()))
        {
            corePos.setX(corePos.getX() + (Math.min(earthWidth, spaceWidth * amplifier.get()) - opCorePos.getX()));
            reCalFlag = true;
        }
        if (corePos.getY() >= Math.max(0, (earthHeight - spaceHeight * amplifier.get())))
        {
            corePos.setY(Math.max(0, (earthHeight - spaceHeight * amplifier.get())));
            reCalFlag = true;
        } else if (opCorePos.getY() < Math.min(earthHeight, spaceHeight * amplifier.get()))
        {
            corePos.setY(corePos.getY() + (Math.min(earthHeight, spaceHeight * amplifier.get()) - opCorePos.getY()));
            reCalFlag = true;
        }
        if (reCalFlag)
            positionSuit.setCorPosition(corePos);
    }

    private void validateUniverseSize()
    {
        if ((this.spaceWidth * amplifier.get() < this.earthWidth) || (this.spaceHeight * amplifier.get() < this.earthHeight))
            zoomIn();
    }

}
