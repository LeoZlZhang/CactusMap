package Cactus.Design.PaneModule.PANE.PROFILE.TYPE;

import Cactus.Design.PaneModule.AXIS.POS.Position;
import Cactus.Design.PaneModule.PANE.PROFILE.ShapeForm;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 10/31/14
 * Time: 3:28 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Profile
{
    Position getPosition();

    Form getForm();

    Profile getCopy();
}
