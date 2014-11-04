package Cactus.Design.PaneModule.AXIS;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 10/26/14
 * Time: 1:51 PM
 */
public class Amplifier
{
    private BigDecimal amplify;

    public Amplifier()
    {
        setDefaultSize();
    }

    public Amplifier(double amp)
    {
        this.amplify = new BigDecimal(amp);
    }

    public Amplifier(BigDecimal amp)
    {
        this.amplify = amp;
    }

    public void zoomIn()
    {
        if (amplify.floatValue() >= 1)
            amplify = amplify.add(new BigDecimal(0.05)).setScale(5, BigDecimal.ROUND_HALF_EVEN);
        else
            amplify = amplify.add(new BigDecimal(0.01)).setScale(5, BigDecimal.ROUND_HALF_EVEN);
        validation();
    }

    public void zoomOut()
    {
        if (amplify.floatValue() >= 1)
            amplify = amplify.subtract(new BigDecimal(0.05)).setScale(5, BigDecimal.ROUND_HALF_EVEN);
        else
            amplify = amplify.subtract(new BigDecimal(0.01)).setScale(5, BigDecimal.ROUND_HALF_EVEN);
        validation();
    }

    public void setDefaultSize()
    {
        amplify = new BigDecimal(1);
    }

    private void validation()
    {
        if (amplify.floatValue() >= 5)
            amplify = new BigDecimal(5);
        else if (amplify.floatValue() <= 0.1)
            amplify = new BigDecimal(0.1);
    }

    public double get()
    {
        return amplify.setScale(5, BigDecimal.ROUND_HALF_EVEN).doubleValue();
    }
}
