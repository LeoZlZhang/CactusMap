package Cactus.Design.InputModule;

import Cactus.CactusXMLParser;
import Cactus.Design.InputModule.TYPE.Event;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 11/22/14
 * Time: 10:15 AM
 */
public class OriginDataFactory
{
    public ArrayList<Event> eventList = new ArrayList<Event>();

    public OriginDataFactory(String path)
    {
        this.eventList.addAll(getInstanceFromXML(path).eventList);
        mapDependent();
    }

    public static OriginDataFactory getInstanceFromXML(String path)
    {
        OriginDataFactory odf = null;
        Logger logger = Logger.getLogger("forERROR");
        try
        {
            odf = (OriginDataFactory) CactusXMLParser.parse(path, OriginDataFactory.class);
        } catch (Exception e)
        {
            logger.error(e.getMessage());
            System.exit(0);
        }
        return odf;
    }

    private void mapDependent()
    {
        for (Event iEvent : eventList)
        {
            for (Event.Consequence jConsequence : iEvent.consequence)
            {
                for (Event kEvent : eventList)
                {
                    if (jConsequence.ledTo.equalsIgnoreCase(kEvent.name))
                    {
                        kEvent.depend.add(iEvent.name);
                    }
                }
            }
        }
    }

    @Test
    public void test()
    {
        OriginDataFactory odf = OriginDataFactory.getInstanceFromXML("C:\\Users\\ezilizh\\Leo\\code\\mygit\\CactusMap\\conf\\cfg.xml");
        odf.mapDependent();
        for (Event eve : odf.eventList)
        {
            System.out.println("Event name=" + eve.name);
            System.out.println("Event type=" + eve.type);
            for (String str : eve.affect)
                System.out.println("Event affect=" + str);
            for (String str : eve.depend)
                System.out.println("Event depend =" + str);
            for (Event.Consequence conse : eve.consequence)
            {
                System.out.println("Event consequence name=" + conse.name);
                System.out.println("Event consequence ledTo=" + conse.ledTo);
            }
            System.out.println();
        }
    }
}
