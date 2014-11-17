package Cactus.Design.PaneModule.AXIS.TYPE;

import Cactus.Design.PaneModule.AXIS.POSITION.Type.Position;
import Cactus.Design.PaneModule.PANE.FORM.TYPE.Form;
import Cactus.Design.PaneModule.PANE.PROFILE.TYPE.Profile;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 10/31/14
 * Time: 3:02 PM
 */
public interface Space extends Scale
{

    void zoomIn(Dimension form);

    void zoomIn(Position pos, Dimension form);

    void zoomOut(Dimension form);

    void zoomOut(Position pos, Dimension form);

    void moveSpace(Position pos, Dimension form);

    Position trans2EarthView(Position shapeUniversePosition);

    Profile trans2EarthView(Profile shapeUniversePosition);
}
