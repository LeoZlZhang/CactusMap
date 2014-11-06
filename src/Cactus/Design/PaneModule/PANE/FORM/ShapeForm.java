package Cactus.Design.PaneModule.PANE.FORM;

import Cactus.Design.PaneModule.PANE.FORM.TYPE.Form;
import org.apache.log4j.Logger;

import java.awt.*;

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

    public ShapeForm()
    {
    }

    public ShapeForm(double width, double height)
    {
        this.width = width;
        this.height = height;
        validateForm();
    }

    public ShapeForm(Dimension dimension)
    {
        this.width = dimension.width;
        this.height = dimension.height;
        validateForm();
    }

    public ShapeForm(Form form)
    {
        this.width = form.getWidth();
        this.height = form.getHeight();
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
        if (this.width < 0)
        {
            System.out.println("Width(" + this.width + ") invalid, set to 0!");
            this.width = 0;
        }
        if (this.height < 0)
        {
            System.out.println("Height(" + this.height + ") invalid, set to 0!");
            this.height = 0;
        }
    }

    public void offsetForm(Form form)
    {
        this.width = width + form.getWidth();
        this.height = height + form.getHeight();
        validateForm();
    }
}
