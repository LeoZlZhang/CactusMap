package Cactus.Design.PaneModule.PANE.MouseEvent;

import Cactus.Design.PaneModule.AXIS.POSITION.MousePosition;
import Cactus.Design.PaneModule.AXIS.POSITION.Offset;
import Cactus.Design.PaneModule.AXIS.POSITION.Type.Position;
import Cactus.test.MouseEvent.Pane4TestMouse;

import javax.swing.*;
import java.awt.event.*;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 10/23/14
 * Time: 4:49 PM
 */
public class MouseAdapter4JPane extends MouseAdapter
{
    private Pane4TestMouse pane;
    private boolean mousePressed;
    private Position startPos;
    private Position endPos;

    //Inertia effect code
    private boolean dragging;
    private InertiaSuit inertiaSuit;
    private int inertiaCalInterval;
    private int damping;
    private double stopThreshold;
    private Timer inertiaTimer = new Timer(inertiaCalInterval, new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            Double dis;
            Offset offset = new Offset();
            int signal;
            double inertiaA;
            if (inertiaSuit.enableX)
            {
                if (inertiaSuit.V_X > 0)
                    signal = 1;
                else
                    signal = -1;

                inertiaA = inertiaSuit.V_X * signal / damping * -1;

                dis = calDistanceWithAccelerate(inertiaCalInterval, inertiaA, inertiaSuit.V_X * signal) * signal;
                offset.setX(dis);
                inertiaSuit.V_X = inertiaSuit.V_X * signal + inertiaA * inertiaCalInterval;
                if (inertiaSuit.V_X <= stopThreshold)
                {
                    inertiaSuit.V_X = 0;
                    inertiaSuit.enableX = false;
                } else
                {
                    inertiaSuit.V_X = inertiaSuit.V_X * signal;
                }
            }
            if (inertiaSuit.enableY)
            {
                if (inertiaSuit.V_Y > 0)
                    signal = 1;
                else
                    signal = -1;

                inertiaA = inertiaSuit.V_Y / damping * signal * -1;
                dis = calDistanceWithAccelerate(inertiaCalInterval, inertiaA, inertiaSuit.V_Y * signal) * signal;
                offset.setY(dis);
                inertiaSuit.V_Y = inertiaSuit.V_Y * signal + inertiaA * inertiaCalInterval;
                if (inertiaSuit.V_Y <= stopThreshold)
                {
                    inertiaSuit.V_Y = 0;
                    inertiaSuit.enableY = false;
                } else
                {
                    inertiaSuit.V_Y = inertiaSuit.V_Y * signal;
                }
            }
            if (!inertiaSuit.enableX && !inertiaSuit.enableY && !dragging)
            {
                inertiaTimer.stop();
            } else
            {
                pane.universe.moveSpace(offset);
                pane.repaint();
            }
        }

        private double calDistanceWithAccelerate(double t, double a, double v)
        {
            BigDecimal s = new BigDecimal(a * t * t / 2 + v * t);
            if (s.doubleValue() < 0)
            {
                return 0;
            } else
            {
                return s.setScale(2, BigDecimal.ROUND_HALF_EVEN).doubleValue();
            }
        }
    });


    public MouseAdapter4JPane(Pane4TestMouse pane)
    {
        this.pane = pane;
        dragging = false;
        mousePressed = false;
        inertiaSuit = new InertiaSuit();
        inertiaCalInterval = 15;
        damping = 120;
        stopThreshold = 0.02;
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        inertiaSuit.disable();
        startPos = new MousePosition(e.getPoint());
        endPos = null;
        mousePressed = true;

    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        mousePressed = false;
        startPos = null;
        endPos = null;

        if (dragging)
        {
            inertiaSuit.enable();
            dragging = false;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e)
    {
        inertiaSuit.disable();
        if (e.getWheelRotation() > 0)
        {
            pane.universe.zoomOut(new MousePosition(e.getPoint()));
            pane.repaint();
        } else if (e.getWheelRotation() < 0)
        {
            pane.universe.zoomIn(new MousePosition(e.getPoint()));
            pane.repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        if (mousePressed)
        {
            dragging = true;
            endPos = new MousePosition(e.getPoint());
            if (pane != null && startPos != null)
            {
                double xOffset = endPos.getX() - startPos.getX();
                double yOffset = endPos.getY() - startPos.getY();
                pane.universe.moveSpace(new Offset(xOffset, yOffset));
                pane.repaint();
                startPos.setX(endPos.getX());
                startPos.setY(endPos.getY());
            }
            inertiaSuit.T1 = e.getWhen();
            inertiaSuit.axisX1 = e.getPoint().getX();
            inertiaSuit.axisY1 = e.getPoint().getY();
            inertiaSuit.getVAvergae();

            if(!inertiaTimer.isRunning())
            {
                inertiaTimer.start();
            }
        }
        inertiaSuit.T0 = e.getWhen();
        inertiaSuit.axisX0 = e.getPoint().getX();
        inertiaSuit.axisY0 = e.getPoint().getY();


    }

    public int getInertiaCalInterval()
    {
        return inertiaCalInterval;
    }

    public void setInertiaCalInterval(int inertiaCalInterval)
    {
        this.inertiaCalInterval = inertiaCalInterval;
    }

    public int getDamping()
    {
        return damping;
    }

    public void setDamping(int damping)
    {
        this.damping = damping;
    }

    public double getStopThreshold()
    {
        return stopThreshold;
    }

    public void setStopThreshold(double stopThreshold)
    {
        this.stopThreshold = stopThreshold;
    }

    private class InertiaSuit
    {
        public double V_X = 0;
        public double V_Y = 0;
        public long T0 = 0;
        public long T1 = 0;
        public double axisX0 = 0;
        public double axisY0 = 0;
        public double axisX1 = 0;
        public double axisY1 = 0;
        public boolean enableX = false;
        public boolean enableY = false;

        public void getVAvergae()
        {
            V_X = (axisX1 - axisX0) / (T1 - T0);
            V_Y = (axisY1 - axisY0) / (T1 - T0);
        }

        public void enable()
        {
            enableX = true;
            enableY = true;
        }

        public void disable()
        {
            enableX = false;
            enableY = false;
        }
    }
}
