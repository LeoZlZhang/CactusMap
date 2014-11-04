package Cactus.Design.PaneModule.PANE.FORM;

import Cactus.Design.PaneModule.PANE.FORM.TYPE.Form;
import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 10/28/14
 * Time: 5:18 PM
 */
public abstract class ShapeForm implements Form
{
    protected double width = 1;
    protected double height = 1;
    protected static Logger errLogger = Logger.getLogger("forERROR");

    public ShapeForm()
    {
    }

    public ShapeForm(double width, double height)
    {
        this.width = width;
        this.height = height;
        validateForm();
    }

    public double getWidth()
    {
        return width;
    }

    public void setWidth(double width)
    {
        this.width = width;
        validateForm();
    }

    public double getHeight()
    {
        return height;
    }

    public void setHeight(double height)
    {
        this.height = height;
        validateForm();
    }

    private void validateForm()
    {
        if (this.width <= 0)
        {
            this.width = 1;
            errLogger.error("Width(" + this.width + ") invalid, set to 1!");
        }
        if (this.height <= 0)
        {
            this.height = 1;
            errLogger.error("Height(" + this.width + ") invalid, set to 1!");
        }
    }
}
