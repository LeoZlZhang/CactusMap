package Cactus.Design.PaneModule.AXIS.TYPE;

import Cactus.Design.PaneModule.AXIS.POSITION.Type.Position;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 10/31/14
 * Time: 3:23 PM
 */
public interface Shaper
{
    void zoomIn();

    void zoomIn(Position mousePos);

    void zoomOut();

    void zoomOut(Position mousePos);

    void moveSpace(Position offset);
}
