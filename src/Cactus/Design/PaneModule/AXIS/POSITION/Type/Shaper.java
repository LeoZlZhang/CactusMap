package Cactus.Design.PaneModule.AXIS.POSITION.Type;

import Cactus.Design.PaneModule.AXIS.POS.Position;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 10/31/14
 * Time: 3:23 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Shaper
{
    void zoomIn();

    void zoomIn(Position mousePos);

    void zoomOut();

    void zoomOut(Position mousePos);

    void moveSpace(Position offset);
}
