package Cactus.Design.PaneModule.AXIS;

import Cactus.Design.PaneModule.AXIS.POSITION.CorePosition;
import Cactus.Design.PaneModule.AXIS.POSITION.Type.Position;
import Cactus.Design.PaneModule.PANE.FORM.EarthForm;
import Cactus.Design.PaneModule.PANE.FORM.RectangleForm;
import Cactus.Design.PaneModule.PANE.FORM.ShapeForm;
import Cactus.Design.PaneModule.PANE.FORM.SpaceForm;
import Cactus.Design.PaneModule.PANE.PROFILE.*;
import Cactus.Design.PaneModule.PANE.FORM.TYPE.Form;
import Cactus.Design.PaneModule.PANE.PROFILE.TYPE.Profile;

import java.awt.*;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 10/23/14
 * Time: 6:22 PM
 */
public class Universe extends LogicSpace
{

    public Universe(Form universeForm)
    {
        super(universeForm);
    }

    private static double leaveXDecimal(double src, int X)
    {
        BigDecimal f = new BigDecimal(src);
        return f.setScale(X, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    @Override
    public void zoomIn(Position mousePos, Dimension earthForm)
    {
        Position corePos = spaceProfile.getPosition(SpaceProfile.Direction.COR);
        Double distance2CorX = (mousePos.getX() - corePos.getX()) / amplifier.get();
        Double distance2CorY = (mousePos.getY() - corePos.getY()) / amplifier.get();
        amplifier.zoomIn();
        corePos = new CorePosition(mousePos.getX() - distance2CorX * amplifier.get(), mousePos.getY() - distance2CorY * amplifier.get());
        Form universeForm = new SpaceForm(spaceForm.getWidth() * amplifier.get(), spaceForm.getHeight() * amplifier.get());
        spaceProfile.setSpaceProfile(corePos, universeForm);
        validatePosition(earthForm);
    }

    @Override
    public void zoomIn(Dimension earthForm)
    {
        amplifier.zoomIn();
        spaceProfile.setSpaceForm(new SpaceForm(spaceForm.getWidth() * amplifier.get(), spaceForm.getHeight() * amplifier.get()));
        validatePosition(earthForm);
    }

    @Override
    public void zoomOut(Position mousePos, Dimension earthForm)
    {
        Position corePos = spaceProfile.getPosition(SpaceProfile.Direction.COR);
        Double distance2CorX = (mousePos.getX() - corePos.getX()) / amplifier.get();
        Double distance2CorY = (mousePos.getY() - corePos.getY()) / amplifier.get();
        amplifier.zoomOut();
        validateUniverseSize(earthForm);
        corePos = new CorePosition(mousePos.getX() - distance2CorX * amplifier.get(), mousePos.getY() - distance2CorY * amplifier.get());
        Form universeForm = new SpaceForm(spaceForm.getWidth() * amplifier.get(), spaceForm.getHeight() * amplifier.get());
        spaceProfile.setSpaceProfile(corePos, universeForm);
        validatePosition(earthForm);
    }

    @Override
    public void zoomOut(Dimension earthForm)
    {
        amplifier.zoomOut();
        validateUniverseSize(earthForm);
        Form universeForm = new SpaceForm(spaceForm.getWidth() * amplifier.get(), spaceForm.getHeight() * amplifier.get());
        spaceProfile.setSpaceForm(universeForm);
        validatePosition(earthForm);
    }

    @Override
    public void moveSpace(Position offset, Dimension earthForm)
    {
        Position corePos = spaceProfile.getPosition(SpaceProfile.Direction.COR);
        corePos.setX(corePos.getX() + offset.getX());
        corePos.setY(corePos.getY() + offset.getY());
        spaceProfile.setCorPosition(corePos);
        validatePosition(earthForm);
    }

    @Override
    public Position trans2EarthView(Position universePos)
    {
        return trans2EarthView(universePos, SCALE5);
    }

    public Position trans2EarthView(Position shapePositionInUniverse, int scale)
    {
        Position earthPos = new CorePosition();
        Position universeCorePos = spaceProfile.getPosition(SpaceProfile.Direction.COR);
        earthPos.setX(leaveXDecimal(universeCorePos.getX() + shapePositionInUniverse.getX() * this.amplifier.get(), scale));
        earthPos.setY(leaveXDecimal(universeCorePos.getY() + shapePositionInUniverse.getY() * this.amplifier.get(), scale));
        return earthPos;
    }

    @Override
    public Profile trans2EarthView(Profile shapePorInUniverse)
    {
        Position shapePosInUniverse = trans2EarthView(shapePorInUniverse.getPosition());
        Form shapeFormInUniverse = new RectangleForm();
        shapeFormInUniverse.setWidth(shapePorInUniverse.getForm().getWidth() * this.amplifier.get());
        shapeFormInUniverse.setHeight(shapePorInUniverse.getForm().getHeight() * this.amplifier.get());
        return new RectangleProfile(shapePosInUniverse, shapeFormInUniverse);
    }


    public void validatePosition(Dimension earthForm)
    {
        Boolean reCalFlag = false;
        Position corePos = spaceProfile.getPosition(SpaceProfile.Direction.COR);
        Position opCorePos = spaceProfile.getPosition(SpaceProfile.Direction.EAST_SOUTH);

        if (corePos.getX() >= Math.max(0, (earthForm.getWidth() - spaceProfile.getForm().getWidth())))
        {
            corePos.setX(Math.max(0, (earthForm.getWidth() - spaceProfile.getForm().getWidth())));
            reCalFlag = true;
        } else if (opCorePos.getX() < Math.min(earthForm.getWidth(), spaceProfile.getForm().getWidth()))
        {
            corePos.setX(corePos.getX() + (Math.min(earthForm.getWidth(), spaceProfile.getForm().getWidth()) - opCorePos.getX()));
            reCalFlag = true;
        }

        if (corePos.getY() >= Math.max(0, (earthForm.getHeight() - spaceProfile.getForm().getHeight())))
        {
            corePos.setY(Math.max(0, (earthForm.getHeight() - spaceProfile.getForm().getHeight())));
            reCalFlag = true;
        } else if (opCorePos.getY() < Math.min(earthForm.getHeight(), spaceProfile.getForm().getHeight()))
        {
            corePos.setY(corePos.getY() + (Math.min(earthForm.getHeight(), spaceProfile.getForm().getHeight()) - opCorePos.getY()));
            reCalFlag = true;
        }
        if (reCalFlag)
            spaceProfile.setCorPosition(corePos);
    }

    private void validateUniverseSize(Dimension earthForm)
    {
        if ((spaceForm.getWidth() * amplifier.get() < earthForm.getWidth()) || (spaceForm.getHeight() * amplifier.get() < earthForm.getHeight()))
            amplifier.zoomIn();
    }
}
