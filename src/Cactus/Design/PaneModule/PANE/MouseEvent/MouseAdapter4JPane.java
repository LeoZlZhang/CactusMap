package Cactus.Design.PaneModule.PANE.MouseEvent;

import Cactus.Design.PaneModule.AXIS.POSITION.MousePosition;
import Cactus.Design.PaneModule.AXIS.POSITION.Offset;
import Cactus.Design.PaneModule.AXIS.POSITION.Type.Position;
import Cactus.Design.PaneModule.PANE.Pane;

import javax.swing.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 10/23/14
 * Time: 4:49 PM
 */
public class MouseAdapter4JPane extends MouseAdapter
{
    private enum MouseAction
    {
        PRESS, DRAGGING, RELEASE, SELECT;
    }

    public Pane pane;
    private DragSuit dragSuit;
    private MouseAction mouseStatus;
    //Inertia effect code
    private InertiaSuit inertiaSuit;
    private Timer inertiaTimer;

    public EventManager eveManager;


    public MouseAdapter4JPane(Pane pane)
    {
        this.pane = pane;
        eveManager = new EventManager(this);
        mouseStatus = MouseAction.RELEASE;
        inertiaSuit = new InertiaSuit(15, 130, 0.005);
        dragSuit = new DragSuit();
        inertiaTimer = new Timer(inertiaSuit.inertiaCalInterval, new InertiaActionListener());
    }

    /**
     * Mouse dragging inertia effect can only by inertia stop, mouse press, and wheel move..
     *
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e)
    {
        switch (mouseStatus)
        {
            case PRESS:
                break;
            case DRAGGING:
                break;
            case RELEASE:
                inertiaSuit.disable();
                mouseStatus = MouseAction.PRESS;
                for (int i = 0; i < pane.eventList.size(); i++)
                {
                    if (pane.eventList.get(i).hover(new MousePosition(e.getPoint()), pane.universe))
                    {
                        eveManager.hoverEventIndex = i;
                        break;
                    } else
                    {
                        eveManager.hoverEventIndex = -1;
                    }
                }
                break;
            case SELECT:
                break;
        }
        /**
         * update mouse status
         */
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        switch (mouseStatus)
        {
            case PRESS:
                mouseStatus = MouseAction.RELEASE;
                if (eveManager.hoverEventIndex >= 0)
                {
                    System.out.println("DEBUG [mouseReleased] hoverEventIndex >=0");
                    if (eveManager.selectedEventIndex < 0)
                    {
                            System.out.println("DEBUG [mouseReleased] select event="+eveManager.hoverEventIndex);
                            //select
                            eveManager.selectedEventIndex = eveManager.hoverEventIndex;
                            //start select timer, transform to next true return.
                            //start effect timer.
                            eveManager.affectEventIndexList.addAll(pane.eventList.get(eveManager.selectedEventIndex).eventProfile.affectiveEventList);
                            //start depend timer, transform to next true return.
                            eveManager.dependEventIndexList.addAll(pane.eventList.get(eveManager.selectedEventIndex).eventProfile.dependentEventList);
                            //start result timer, transform to next true return.
                            eveManager.resultEventIndexList.addAll(pane.eventList.get(eveManager.selectedEventIndex).eventProfile.resultEventList);
                            eveManager.selectEffectTimer.start();
                    } else if (eveManager.hoverEventIndex == eveManager.selectedEventIndex)
                    {
                        System.out.println("DEBUG [mouseReleased] de-select event="+eveManager.hoverEventIndex);
                        //de-celect
                            //start select timer, transform to next true return.
                            eveManager.deSelectEventIndex = eveManager.selectedEventIndex;
                            eveManager.selectedEventIndex = -1;
                            //set all event index to off list, action listener will remove all from list and stop itself
                            eveManager.offAffectEventIndexList.addAll(eveManager.affectEventIndexList);
                            eveManager.affectEventIndexList.clear();
                            //start depend timer, transform to next true return.
                            eveManager.offDependEventIndexList.addAll(eveManager.dependEventIndexList);
                            eveManager.dependEventIndexList.clear();
                            //start result timer, transform to next true return.
                            eveManager.offResultEventIndexList.addAll(eveManager.resultEventIndexList);
                            eveManager.resultEventIndexList.clear();
                            eveManager.selectEffectTimer.start();
                    } else
                    {
                        System.out.println("DEBUG [mouseReleased] re-select new event="+eveManager.hoverEventIndex);
                        System.out.println("DEBUG [mouseReleased] re-select old event="+eveManager.selectedEventIndex);
                            //deselect one and select another
                            eveManager.deSelectEventIndex = eveManager.selectedEventIndex;
                            eveManager.selectedEventIndex = eveManager.hoverEventIndex;

                            //set redundant effect event index  list
                            eveManager.offAffectEventIndexList.addAll(eveManager.affectEventIndexList);
                            eveManager.affectEventIndexList.clear();
                            eveManager.affectEventIndexList.addAll(pane.eventList.get(eveManager.selectedEventIndex).eventProfile.affectiveEventList);
                            //eveManager.removeRedundantOffAffectEventList();

                            //start depend timer, transform to next true return.
                            eveManager.offDependEventIndexList.addAll(eveManager.dependEventIndexList);
                            eveManager.dependEventIndexList.clear();
                            eveManager.dependEventIndexList.addAll(pane.eventList.get(eveManager.selectedEventIndex).eventProfile.dependentEventList);
                            //eveManager.removeRedundantOffDependEventList();

                            //start result timer, transform to next true return.
                            eveManager.offResultEventIndexList.addAll(eveManager.resultEventIndexList);
                            eveManager.resultEventIndexList.clear();
                            eveManager.resultEventIndexList.addAll(pane.eventList.get(eveManager.selectedEventIndex).eventProfile.resultEventList);
                            //eveManager.removeRedundantOffResultEventList();
                            eveManager.selectEffectTimer.start();
                    }
                }
//                if (eveManager.hoverEventIndex >= 0)
//                {
//                    pane.eventList.get(eveManager.hoverEventIndex).statusManager.selected = true;
//                    for (int i = 0; i < eveManager.resultEventIndexList.size(); i++)
//                    {
//                        pane.eventList.get(eveManager.resultEventIndexList.get(i)).statusManager.resulted = true;
//                    }
//                    for (int i = 0; i < eveManager.dependEventIndexList.size(); i++)
//                    {
//                        pane.eventList.get(eveManager.dependEventIndexList.get(i)).statusManager.depended = true;
//                    }
//                    //timer
//                }
                break;
            case DRAGGING:
                inertiaSuit.enable();
                mouseStatus = MouseAction.RELEASE;
                break;
            case RELEASE:
                break;
            case SELECT:
                break;
        }

    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e)
    {
        switch (mouseStatus)
        {
            case PRESS:
                break;
            case DRAGGING:
                break;
            case RELEASE:
                inertiaSuit.disable();
                if (e.getWheelRotation() > 0)
                {
                    pane.universe.zoomOut(new MousePosition(e.getPoint()), pane.paneForm);
                    pane.repaint();
                } else if (e.getWheelRotation() < 0)
                {
                    pane.universe.zoomIn(new MousePosition(e.getPoint()), pane.paneForm);
                    pane.repaint();
                }
                break;
            case SELECT:
                break;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        switch (mouseStatus)
        {
            case PRESS:
                /**
                 * Set start position for dragging
                 */
                dragSuit.startPos = new MousePosition(e.getPoint());
                inertiaSuit.T0 = inertiaSuit.T1;
                inertiaSuit.axisX0 = inertiaSuit.axisX1;
                inertiaSuit.axisY0 = inertiaSuit.axisY1;
                mouseStatus = MouseAction.DRAGGING;
                inertiaTimer.start();
                break;
            case DRAGGING:
                dragSuit.endPos = new MousePosition(e.getPoint());
                if (pane != null && dragSuit.startPos != null)
                {
                    pane.universe.moveSpace(dragSuit.getOffset(), pane.paneForm);
                    pane.repaint();
                    dragSuit.startPos.setX(e.getX());
                    dragSuit.startPos.setY(e.getY());
                }

                inertiaSuit.T1 = e.getWhen();
                inertiaSuit.axisX1 = e.getPoint().getX();
                inertiaSuit.axisY1 = e.getPoint().getY();
                inertiaSuit.getVAverage();
                inertiaSuit.T0 = inertiaSuit.T1;
                inertiaSuit.axisX0 = inertiaSuit.axisX1;
                inertiaSuit.axisY0 = inertiaSuit.axisY1;
                break;
            case RELEASE:
                break;
            case SELECT:
                break;
        }
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
        public int inertiaCalInterval;
        public int damping;
        public double stopThreshold;
        public int signalX;
        public int signalY;

        private InertiaSuit(int inertiaCalInterval, int damping, double stopThreshold)
        {
            this.inertiaCalInterval = inertiaCalInterval;
            this.damping = damping;
            this.stopThreshold = stopThreshold;
        }

        public void getVAverage()
        {

            V_X = (axisX1 - axisX0) / Math.max((T1 - T0), 1);
            V_Y = (axisY1 - axisY0) / Math.max((T1 - T0), 1);
        }

        public void enable()
        {
            enableX = true;
            enableY = true;
            if (inertiaSuit.V_X > 0)
                signalX = 1;
            else
                signalX = -1;
            if (inertiaSuit.V_Y > 0)
                signalY = 1;
            else
                signalY = -1;


        }

        public void disable()
        {
            enableX = false;
            enableY = false;
            inertiaTimer.stop();
        }
    }

    private class DragSuit
    {
        Position startPos = new MousePosition(0, 0);
        Position endPos = new MousePosition(0, 0);

        public Position getOffset()
        {
            return new Offset((endPos.getX() - startPos.getX()), (endPos.getY() - startPos.getY()));
        }
    }

    public class InertiaActionListener implements ActionListener
    {
        long t1;
        long t2;

        @Override
        public void actionPerformed(ActionEvent e)
        {
            Double dis;
            Offset offset = new Offset(0, 0);
            double inertiaA_ABS;
            t2 = System.currentTimeMillis();
            if (inertiaSuit.enableX)
            {
                /**
                 * Re-calculate A refer to V.
                 */
                inertiaA_ABS = inertiaSuit.V_X * inertiaSuit.signalX / inertiaSuit.damping * -1;

                dis = calDistanceWithAccelerate_ABS(
                        (t2 - t1),
                        inertiaA_ABS,
                        inertiaSuit.V_X * inertiaSuit.signalX) * inertiaSuit.signalX;
                offset.setX(dis);
                /**
                 * Re-calculate V after interval
                 */
                inertiaSuit.V_X = inertiaSuit.V_X + inertiaA_ABS * inertiaSuit.signalX * inertiaSuit.inertiaCalInterval;
                if (inertiaSuit.V_X * inertiaSuit.signalX <= inertiaSuit.stopThreshold)
                {
                    inertiaSuit.V_X = 0;
                    inertiaSuit.enableX = false;
                }
            }

            if (inertiaSuit.enableY)
            {
                inertiaA_ABS = inertiaSuit.V_Y * inertiaSuit.signalY / inertiaSuit.damping * -1;
                dis = calDistanceWithAccelerate_ABS(
                        (t2 - t1),
                        inertiaA_ABS,
                        inertiaSuit.V_Y * inertiaSuit.signalY) * inertiaSuit.signalY;
                offset.setY(dis);
                /**
                 * Re-calculate V after interval
                 */
                inertiaSuit.V_Y = inertiaSuit.V_Y + inertiaA_ABS * inertiaSuit.signalY * inertiaSuit.inertiaCalInterval;
                if (inertiaSuit.V_Y * inertiaSuit.signalY <= inertiaSuit.stopThreshold)
                {
                    inertiaSuit.V_Y = 0;
                    inertiaSuit.enableY = false;
                }
            }

            if (mouseStatus == MouseAction.RELEASE)
            {
                if (!inertiaSuit.enableX && !inertiaSuit.enableY)
                {
                    inertiaTimer.stop();
                } else
                {
                    pane.universe.moveSpace(offset, pane.paneForm);
                    pane.repaint();
                }
            }
            t1 = System.currentTimeMillis();
        }

        private double calDistanceWithAccelerate_ABS(double t, double a, double v)
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
    }


    public class EventManager
    {
        private static final int interval = 60;
        public int selectedEventIndex = -1;
        public int hoverEventIndex = -1;
        public ArrayList<Integer> dependEventIndexList = new ArrayList<Integer>();
        public ArrayList<Integer> affectEventIndexList = new ArrayList<Integer>();
        public ArrayList<Integer> resultEventIndexList = new ArrayList<Integer>();

        public int deSelectEventIndex = -1;
        public ArrayList<Integer> offDependEventIndexList = new ArrayList<Integer>();
        public ArrayList<Integer> offAffectEventIndexList = new ArrayList<Integer>();
        public ArrayList<Integer> offResultEventIndexList = new ArrayList<Integer>();

        public Timer selectEffectTimer;

        public EventManager(MouseAdapter4JPane adapter)
        {
            reset();
            selectEffectTimer = new Timer(interval, new SelectActionListener(adapter));
        }

        public void reset()
        {
            selectedEventIndex = -1;
            hoverEventIndex = -1;
            dependEventIndexList.clear();
            affectEventIndexList.clear();
            resultEventIndexList.clear();
        }
    }
}
