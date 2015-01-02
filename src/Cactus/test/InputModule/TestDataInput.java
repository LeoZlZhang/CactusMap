package Cactus.test.InputModule;

import Cactus.Design.InputModule.OriginDataFactory;
import Cactus.Design.InputModule.TYPE.Event;
import org.testng.annotations.Test;

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
        String path = "C:\\Users\\ezilizh\\Leo\\code\\mygit\\CactusMap\\conf\\cfg.xml";
        System.out.println("DEBUG <TestDataInput> path=" + path);
        OriginDataFactory odf = new OriginDataFactory(path);
        System.out.println("DEBUG event length=" + odf.eventList.size());
        for (Event eve : odf.eventList)
        {
            System.out.println("Event name=" + eve.name);
            System.out.println("Event type=" + eve.type);
            for (String str : eve.affectList)
                System.out.println("Event affect=" + str);
            for (String str : eve.dependList)
                System.out.println("Event depend =" + str);
            for (Event.Consequence conse : eve.consequenceList)
            {
                System.out.println("Event consequence name=" + conse.name);
                System.out.println("Event consequence ledTo=" + conse.ledTo);
            }
            System.out.println();
        }
    }
}
