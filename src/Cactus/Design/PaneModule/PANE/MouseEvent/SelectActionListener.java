package Cactus.Design.PaneModule.PANE.MouseEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 11/9/14
 * Time: 9:57 PM
 */
public class SelectActionListener implements ActionListener
{
    private MouseAdapter4JPane adapter;
    private boolean doTurnOnEffect = true;

    public SelectActionListener(MouseAdapter4JPane apd)
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
        adapter.pane.repaint();
    }

    private boolean handleTurnOffEffect()
    {
        boolean done = true;
        //In select case, select index >0, de select index <0
        //In de select case, select index <0, de select index >0
        //In select one and deselect another case, select index >0, deselect index>0
        if ((adapter.eveManager.deSelectEventIndex >= 0)
                && (adapter.pane.eventList.get(adapter.eveManager.deSelectEventIndex).shapeManager.getTransState4Select() != 0))
        {
            adapter.pane.eventList.get(adapter.eveManager.deSelectEventIndex).shapeManager.transform4SelectEffect();
            done = false;
        } else
        {
            adapter.eveManager.deSelectEventIndex = -1;
            doTurnOnEffect = true;
        }

        for (int i = 0; i < adapter.eveManager.offAffectEventIndexList.size(); )
        {
            if (adapter.pane.eventList.get(adapter.eveManager.offAffectEventIndexList.get(i)).shapeManager.getTransState4Affect() != 0)
            {
                adapter.pane.eventList.get(adapter.eveManager.offAffectEventIndexList.get(i)).shapeManager.transform4AffectEffect();
                i++;
                done = false;
            } else
            {
                adapter.eveManager.offAffectEventIndexList.remove(i);
            }
        }

        for (int i = 0; i < adapter.eveManager.offDependEventIndexList.size(); )
        {
            if (adapter.pane.eventList.get(adapter.eveManager.offDependEventIndexList.get(i)).shapeManager.getTransState4Depend() != 0)
            {
                adapter.pane.eventList.get(adapter.eveManager.offDependEventIndexList.get(i)).shapeManager.transform4DependEffect();
                i++;
                done = false;
            } else
            {
                adapter.eveManager.offDependEventIndexList.remove(i);
            }
        }

        for (int i = 0; i < adapter.eveManager.offResultEventIndexList.size(); )
        {
            if (adapter.pane.eventList.get(adapter.eveManager.offResultEventIndexList.get(i)).shapeManager.getTransState4Depend() != 0)
            {
                adapter.pane.eventList.get(adapter.eveManager.offResultEventIndexList.get(i)).shapeManager.transform4DependEffect();
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
        if (doTurnOnEffect)
        {
            boolean done = true;
            if ((adapter.eveManager.selectedEventIndex >= 0)
                    && (adapter.pane.eventList.get(adapter.eveManager.selectedEventIndex).shapeManager.getTransState4Select() != 1))
            {
                adapter.pane.eventList.get(adapter.eveManager.selectedEventIndex).shapeManager.transform4SelectEffect();
                done = false;
            }

            for (int i = 0; i < adapter.eveManager.dependEventIndexList.size(); i++)
            {
                if (adapter.pane.eventList.get(adapter.eveManager.dependEventIndexList.get(i)).shapeManager.getTransState4Depend() != 1)
                {
                    adapter.pane.eventList.get(adapter.eveManager.dependEventIndexList.get(i)).shapeManager.transform4DependEffect();
                    done = false;
                }
            }

            for (int i = 0; i < adapter.eveManager.resultEventIndexList.size(); i++)
            {
                if (adapter.pane.eventList.get(adapter.eveManager.resultEventIndexList.get(i)).shapeManager.getTransState4Depend() != 1)
                {
                    adapter.pane.eventList.get(adapter.eveManager.resultEventIndexList.get(i)).shapeManager.transform4DependEffect();
                    done = false;
                }
            }
            doTurnOnEffect = !done;
        }
        for (int i = 0; i < adapter.eveManager.affectEventIndexList.size(); i++)
        {
            adapter.pane.eventList.get(adapter.eveManager.affectEventIndexList.get(i)).shapeManager.transform4AffectEffect();
        }

        if(!doTurnOnEffect && adapter.eveManager.affectEventIndexList.isEmpty())
        {
            System.out.println("Select timer stop");
            adapter.eveManager.selectEffectTimer.stop();
        }
    }
}
