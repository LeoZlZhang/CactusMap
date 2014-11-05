package Cactus.Design.PaneModule.AXIS.TYPE;

import Cactus.Design.PaneModule.AXIS.POSITION.Type.Position;
import Cactus.Design.PaneModule.PANE.FORM.TYPE.Form;
import Cactus.Design.PaneModule.PANE.PROFILE.TYPE.Profile;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 10/31/14
 * Time: 3:02 PM
 */
public interface Space extends Scale
{

    void zoomIn(Form form);

    void zoomIn(Position pos, Form form);

    void zoomOut(Form form);

    void zoomOut(Position pos, Form form);

    void moveSpace(Position pos, Form form);

    Position trans2EarthView(Position shapeUniversePosition);

    Profile trans2EarthView(Profile shapeUniversePosition);
}
