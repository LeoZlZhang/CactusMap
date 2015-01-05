package Cactus.Design.PaneModule.PANE.MouseEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 11/9/14
 * Time: 9:57 PM
 */
public class SelectActionListener4Timer implements ActionListener
{
    private MouseAdapter4MapPane adapter;

    public SelectActionListener4Timer(MouseAdapter4MapPane apd)
    {
        this.adapter = apd;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

        if (handleTurnOffEffect())
        {
            handleTurnOnEffect();
        }
        adapter.mapPane.repaint();
    }

    private boolean handleTurnOffEffect()
    {
        boolean done = true;
        //In select case, select index >0, de select index <0
        //In de select case, select index <0, de select index >0
        //In select one and deselect another case, select index >0, deselect index>0
        if ((adapter.eveShowManager.deSelectEventIndex >= 0)
                && (adapter.mapPane.eventList.get(adapter.eveShowManager.deSelectEventIndex).shapeManager.getTransState4Select() != 0))
        {
            adapter.mapPane.eventList.get(adapter.eveShowManager.deSelectEventIndex).shapeManager.transform4SelectEffect();
            done = false;
        } else
        {
            adapter.eveShowManager.deSelectEventIndex = -1;
        }

        for (int i = 0; i < adapter.eveShowManager.offDependEventIndexList.size(); )
        {
            if (adapter.mapPane.eventList.get(adapter.eveShowManager.offDependEventIndexList.get(i)).shapeManager.getTransState4Depend() != 0)
            {
                adapter.mapPane.eventList.get(adapter.eveShowManager.offDependEventIndexList.get(i)).shapeManager.transform4DependEffect();
                i++;
                done = false;
            } else
            {
                adapter.eveShowManager.offDependEventIndexList.remove(i);
            }
        }

        for (int i = 0; i < adapter.eveShowManager.offResultEventIndexList.size(); )
        {
            if (adapter.mapPane.eventList.get(adapter.eveShowManager.offResultEventIndexList.get(i)).shapeManager.getTransState4Depend() != 0)
            {
                adapter.mapPane.eventList.get(adapter.eveShowManager.offResultEventIndexList.get(i)).shapeManager.transform4DependEffect();
                i++;
                done = false;
            } else
            {
                adapter.eveShowManager.offResultEventIndexList.remove(i);
            }
        }
        return done;
    }


    public void handleTurnOnEffect()
    {
        boolean done = true;
        if ((adapter.eveShowManager.selectedEventIndex >= 0)
                && (adapter.mapPane.eventList.get(adapter.eveShowManager.selectedEventIndex).shapeManager.getTransState4Select() != 1))
        {
            adapter.mapPane.eventList.get(adapter.eveShowManager.selectedEventIndex).shapeManager.transform4SelectEffect();
            done = false;
        }

        for (int i = 0; i < adapter.eveShowManager.dependEventIndexList.size(); i++)
        {
            if (adapter.mapPane.eventList.get(adapter.eveShowManager.dependEventIndexList.get(i)).shapeManager.getTransState4Depend() != 1)
            {
                adapter.mapPane.eventList.get(adapter.eveShowManager.dependEventIndexList.get(i)).shapeManager.transform4DependEffect();
                done = false;
            }
        }

        for (int i = 0; i < adapter.eveShowManager.resultEventIndexList.size(); i++)
        {
            if (adapter.mapPane.eventList.get(adapter.eveShowManager.resultEventIndexList.get(i)).shapeManager.getTransState4Depend() != 1)
            {
                adapter.mapPane.eventList.get(adapter.eveShowManager.resultEventIndexList.get(i)).shapeManager.transform4DependEffect();
                done = false;
            }
        }

        if (done)
        {
            System.out.println("Select timer stop");
            adapter.eveShowManager.selectEffectTimer.stop();
        }
    }
}
