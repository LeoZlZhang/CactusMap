package Cactus.Design.DataModule;

import Cactus.Design.PaneModule.AXIS.LogicSpace;
import Cactus.Design.PaneModule.AXIS.POSITION.Type.Position;
import Cactus.Design.PaneModule.PANE.PROFILE.RectangleProfile;
import Cactus.Design.PaneModule.PANE.PROFILE.TYPE.Profile;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 10/23/14
 * Time: 5:46 PM
 */
public abstract class Event
{
    public final Profile profile;
    private Color color = new Color(200,200,169);

    protected Event(Profile profile)
    {
        this.profile = new RectangleProfile(profile.getPosition(), profile.getForm());
    }

    public boolean selected(Position mousePos, LogicSpace universe)
    {
        Profile profile = universe.trans2EarthView(this.profile);
        if ((mousePos.getX() >= profile.getPosition().getX())
                && (mousePos.getX() <= (profile.getPosition().getX() + profile.getForm().getWidth()))
                && (mousePos.getY() >= profile.getPosition().getY())
                && (mousePos.getY() <= (profile.getPosition().getY() + profile.getForm().getHeight())))
            return true;
        else
            return false;
    }

    public Color getColor()
    {
        return color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }
}
