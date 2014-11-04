package Cactus.Design.PaneModule.AXIS.TYPE;

import Cactus.Design.PaneModule.AXIS.POSITION.Type.Position;
import Cactus.Design.PaneModule.PANE.PROFILE.TYPE.Profile;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 10/31/14
 * Time: 3:02 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Space extends Scale, Shaper
{

    void zoomIn();

    void zoomIn(Position pos);

    void zoomOut();

    void zoomOut(Position pos);

    void moveSpace(Position pos);

    Position trans2EarthView(Position shapeUniversePosition);

    Profile trans2EarthView(Profile shapeUniversePosition);
}
