package Cactus.Design.PaneModule.PANE.PROFILE.TYPE;

import Cactus.Design.PaneModule.AXIS.POSITION.Type.Position;
import Cactus.Design.PaneModule.PANE.FORM.TYPE.Form;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 10/31/14
 * Time: 3:28 PM
 */
public interface Profile
{
    Position getPosition();

    Form getForm();

    Profile getCopy();
}
