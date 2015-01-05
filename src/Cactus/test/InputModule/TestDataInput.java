package Cactus.test.InputModule;

import Cactus.Design.DataModule.TYPE.CactusEvent;
import Cactus.Design.InputModule.OriginDataFactory;
import Cactus.Design.InputModule.TYPE.Event;
import org.testng.annotations.Test;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 11/28/14
 * Time: 4:56 PM
 */
public class TestDataInput
{
    @Test
    public void test()
    {
        String path = "C:\\Users\\ezilizh\\Desktop\\Leo\\Leo\\Code\\mygit\\CactusMap\\conf\\cfg.xml";
        System.out.println("DEBUG <TestDataInput> path=" + path);
        OriginDataFactory odf = new OriginDataFactory(path);
        System.out.println("DEBUG event length=" + odf.eventList.size());
        ArrayList<CactusEvent> cactusEventList = odf.cactusEventList();
        for (CactusEvent eve : cactusEventList)
        {
            System.out.println("Event name=" + eve.eventProfile.name);
            for (Integer index : eve.eventProfile.dependentEventList)
                System.out.println("<" + eve.eventProfile.name + "> dependent on <" + cactusEventList.get(index).eventProfile.name + "> index=" + index);
            for (Integer index : eve.eventProfile.consequentList.get(0).ledToList)
                System.out.println("<" + eve.eventProfile.name + "> led to <" + cactusEventList.get(index).eventProfile.name + "> index=" + index);
            System.out.println();
        }
    }
}
