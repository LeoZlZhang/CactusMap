package Cactus.Design.PaneModule.PANE.FORM.TYPE;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 10/31/14
 * Time: 3:30 PM
 */
public interface Form
{
    double getWidth();

    double getHeight();

    void setWidth(double value);

    void setHeight(double value);

    void  offsetForm(Form form);

    Form getCopy();

}
