package Cactus.Design.PaneModule.AXIS;

import Cactus.Design.PaneModule.AXIS.POSITION.CorePosition;
import Cactus.Design.PaneModule.AXIS.POSITION.Type.Position;
import Cactus.Design.PaneModule.AXIS.TYPE.Space;
import Cactus.Design.PaneModule.PANE.FORM.SpaceForm;
import Cactus.Design.PaneModule.PANE.FORM.TYPE.Form;
import Cactus.Design.PaneModule.PANE.PROFILE.SpaceProfile;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 10/23/14
 * Time: 6:18 PM
 */
public abstract class LogicSpace implements Space
{
    /**
     * amplifier control the space room out/in
     * should only be able to change by zoomIn() zoomOut();
     */
    protected final Amplifier amplifier;

    /**
     * space profile keep space core position and space width/height(after amplify)
     * space profile should be change by reset core position and reset space form in it.
     *
     */
    protected final SpaceProfile spaceProfile;

    /**
     * space form keep the origin width/height of space.
     */
    protected final Form spaceForm;

    protected LogicSpace(Form spaceForm)
    {
        this.spaceForm = new SpaceForm(spaceForm.getWidth(), spaceForm.getHeight());
        this.amplifier = new Amplifier();
        this.spaceProfile = new SpaceProfile(new CorePosition(0, 0), new SpaceForm(spaceForm.getWidth() * amplifier.get(), spaceForm.getHeight() * amplifier.get()));
    }

//    public void moveSpace(Position offset)
//    {
//        Position pos = spaceProfile.getPosition(SpaceProfile.Direction.COR);
//        pos.setX(pos.getX() + offset.getX());
//        pos.setY(pos.getY() + offset.getY());
//        spaceProfile.setCorPosition(pos);
//    }

    //Expose for test
    public Double getAmplifierValue()
    {
        return amplifier.get();
    }

    //Expose for test
    public SpaceProfile getSpaceProfile()
    {
        return spaceProfile.getCopy();
    }

}
