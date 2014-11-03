package Cactus.Design.MouseEvent;

import Cactus.Design.PaneModule.AXIS.POS.MousePosition;
import Cactus.Design.PaneModule.AXIS.POS.Offset;
import Cactus.Design.PaneModule.AXIS.POS.Position;
import Cactus.test.MouseEvent.Pane4TestMouse;

import javax.swing.*;
import java.awt.event.*;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 10/23/14
 * Time: 4:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class MouseAdapter4JPane extends MouseAdapter
{
    private Pane4TestMouse pane;
    private boolean mousePressed = false;
    private Position startPos;
    private Position endPos;

    //Inertia effect code
    private boolean dragging = false;
    private InertiaSuit inertiaSuit = new InertiaSuit();
    private final int inertiaCalInterval = 30;
    private double inertiaA;
    private int damping = 100;
    private double stopThreshold = 0.05;
    private Timer inertiaTimer = new Timer(inertiaCalInterval, new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            Double dis;
            Offset offset = new Offset();
            int signal;
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
                System.out.println("DEBUG speed X=" + inertiaSuit.V_X);
                System.out.println("DEBUG enableX=" + inertiaSuit.enableX);

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
            if (!inertiaSuit.enableX && !inertiaSuit.enableY)
            {
                System.out.println("Timer stoped");
                inertiaTimer.stop();
            } else
            {
                pane.universe.moveSpace(offset);
                pane.repaint();
                System.out.println("Repain by timer X" + inertiaSuit.enableX + " Y" + inertiaSuit.enableY);
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
            inertiaTimer.start();
            dragging = false;
            inertiaSuit.enable();
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
            if (pane != null && startPos != null && endPos != null)
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

        }
        inertiaSuit.T0 = e.getWhen();
        inertiaSuit.axisX0 = e.getPoint().getX();
        inertiaSuit.axisY0 = e.getPoint().getY();


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
