package Cactus.Design.InputModule;

import Cactus.CactusXMLParser;
import Cactus.Design.InputModule.TYPE.Event;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.math.BigDecimal;
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
    private ArrayList<ArrayList<Event>> geoEvent2DList = new ArrayList<ArrayList<Event>>();

    public OriginDataFactory(String path)
    {
        this.eventList.addAll(getInstanceFromXML(path).eventList);
        mapDependent();
    }

    public OriginDataFactory()
    {

    }

    private OriginDataFactory getInstanceFromXML(String path)
    {
        System.out.println("DEBUG <OriginDataFactory> path=" + path);
        OriginDataFactory odf = null;
        Logger logger = Logger.getLogger("forERROR");
        try
        {
            odf = (OriginDataFactory) CactusXMLParser.parse(path, OriginDataFactory.class);
        } catch (Exception e)
        {
            logger.error(e);
            System.exit(0);
        }
        return odf;
    }

    private void mapDependent()
    {
        for (Event eve2CheckConsequence : eventList)
        {
            for (Event.Consequence consequenceOfCheckingEve : eve2CheckConsequence.consequenceList)
            {
                for (Event eve2SetDepend : eventList)
                {
                    if (consequenceOfCheckingEve.ledTo.equalsIgnoreCase(eve2SetDepend.name))
                    {
                        eve2SetDepend.dependList.add(eve2CheckConsequence.name);
                    }
                }
            }
        }
    }

    private void getEventMap()
    {
        for (Event eve2Insert : eventList)
        {
            boolean foundDependEve = false;
            for (String dependEve : eve2Insert.dependList)
            {

                for (ArrayList<Event> column : geoEvent2DList)
                {
                    for (Event eveInPosition : column)
                    {
                        int rowIndex = geoEvent2DList.indexOf(column);
                        int columnIndex = column.indexOf(eveInPosition);
                        if(dependEve.equalsIgnoreCase(eveInPosition.name))
                        {
                            foundDependEve = true;
                        }
                    }
                }
            }
        }
    }
}
