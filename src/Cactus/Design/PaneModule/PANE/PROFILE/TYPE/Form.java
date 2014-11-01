package Cactus.Design.PaneModule.PANE.PROFILE.TYPE;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 10/31/14
 * Time: 3:30 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Form
{
    double getWidth();

    double getHeight();

    void setWidth(double value);

    void setHeight(double value);

    Form getCopy();
}
