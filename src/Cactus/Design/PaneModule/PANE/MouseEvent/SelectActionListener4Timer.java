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
        if ((adapter.eveManager.deSelectEventIndex >= 0)
                && (adapter.mapPane.eventList.get(adapter.eveManager.deSelectEventIndex).shapeManager.getTransState4Select() != 0))
        {
            adapter.mapPane.eventList.get(adapter.eveManager.deSelectEventIndex).shapeManager.transform4SelectEffect();
            done = false;
        } else
        {
            adapter.eveManager.deSelectEventIndex = -1;
        }

        for (int i = 0; i < adapter.eveManager.offDependEventIndexList.size(); )
        {
            if (adapter.mapPane.eventList.get(adapter.eveManager.offDependEventIndexList.get(i)).shapeManager.getTransState4Depend() != 0)
            {
                adapter.mapPane.eventList.get(adapter.eveManager.offDependEventIndexList.get(i)).shapeManager.transform4DependEffect();
                i++;
                done = false;
            } else
            {
                adapter.eveManager.offDependEventIndexList.remove(i);
            }
        }

        for (int i = 0; i < adapter.eveManager.offResultEventIndexList.size(); )
        {
            if (adapter.mapPane.eventList.get(adapter.eveManager.offResultEventIndexList.get(i)).shapeManager.getTransState4Depend() != 0)
            {
                adapter.mapPane.eventList.get(adapter.eveManager.offResultEventIndexList.get(i)).shapeManager.transform4DependEffect();
                i++;
                done = false;
            } else
            {
                adapter.eveManager.offResultEventIndexList.remove(i);
            }
        }
        return done;
    }


    public void handleTurnOnEffect()
    {
        boolean done = true;
        if ((adapter.eveManager.selectedEventIndex >= 0)
                && (adapter.mapPane.eventList.get(adapter.eveManager.selectedEventIndex).shapeManager.getTransState4Select() != 1))
        {
            adapter.mapPane.eventList.get(adapter.eveManager.selectedEventIndex).shapeManager.transform4SelectEffect();
            done = false;
        }

        for (int i = 0; i < adapter.eveManager.dependEventIndexList.size(); i++)
        {
            if (adapter.mapPane.eventList.get(adapter.eveManager.dependEventIndexList.get(i)).shapeManager.getTransState4Depend() != 1)
            {
                adapter.mapPane.eventList.get(adapter.eveManager.dependEventIndexList.get(i)).shapeManager.transform4DependEffect();
                done = false;
            }
        }

        for (int i = 0; i < adapter.eveManager.resultEventIndexList.size(); i++)
        {
            if (adapter.mapPane.eventList.get(adapter.eveManager.resultEventIndexList.get(i)).shapeManager.getTransState4Depend() != 1)
            {
                adapter.mapPane.eventList.get(adapter.eveManager.resultEventIndexList.get(i)).shapeManager.transform4DependEffect();
                done = false;
            }
        }

        if (done)
        {
            System.out.println("Select timer stop");
            adapter.eveManager.selectEffectTimer.stop();
        }
    }
}
