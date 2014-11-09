package Cactus.Design.PaneModule.PANE.MouseEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 11/9/14
 * Time: 9:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class SelectActionListener implements ActionListener
{
    private MouseAdapter4JPane adapter;
    private boolean stopFlag4SelectEffect = false;
    private boolean stopFalg4DeSelectEffect = false;

    public SelectActionListener(MouseAdapter4JPane apd)
    {
        this.adapter = apd;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        //In select case, select index >0, de select index <0
        //In de select case, select index <0, de select index >0
        //In select one and deselect another case, select index >0, deselect index>0
        if ((adapter.eveManager.selectedEventIndex >= 0) && (!stopFlag4SelectEffect))
        {
            if (!adapter.pane.eventList.get(adapter.eveManager.selectedEventIndex).shapeManager.transform4SelectEffect())
                stopFlag4SelectEffect = true;
        }
        if ((adapter.eveManager.deSelectEventIndex >= 0) && (!stopFalg4DeSelectEffect))
        {
            if (!adapter.pane.eventList.get(adapter.eveManager.deSelectEventIndex).shapeManager.transform4SelectEffect())
            {
                adapter.eveManager.deSelectEventIndex = -1;
                stopFalg4DeSelectEffect = true;
            }
        }
        if (stopFlag4SelectEffect && stopFalg4DeSelectEffect)
        {
            adapter.eveManager.selectEffectTimer.stop();
            stopFlag4SelectEffect = false;
            stopFalg4DeSelectEffect = false;
        }
    }
}
