package Cactus.Design.PaneModule.PANE.MouseEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 11/9/14
 * Time: 11:05 PM
 */
public class AffectActionListener implements ActionListener
{
    private MouseAdapter4JPane adapter;

    public AffectActionListener(MouseAdapter4JPane adp)
    {
        this.adapter = adp;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        for (int i = 0; i < adapter.eveManager.affectEventIndexList.size(); i++)
        {
            adapter.pane.eventList.get(adapter.eveManager.affectEventIndexList.get(i)).shapeManager.transform4AffectEffect();
        }
        for (int i = 0; i < adapter.eveManager.offAffectEventIndexList.size(); )
        {
            if (adapter.pane.eventList.get(adapter.eveManager.offAffectEventIndexList.get(i)).shapeManager.transform4AffectEffect())
            {
                adapter.eveManager.offAffectEventIndexList.remove(i);
            } else
                i++;
        }

        if(adapter.eveManager.affectEventIndexList.isEmpty() && adapter.eveManager.offAffectEventIndexList.isEmpty())
            adapter.eveManager.affectEffectTimer.stop();
    }
}
