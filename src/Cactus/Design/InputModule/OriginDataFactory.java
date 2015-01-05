package Cactus.Design.InputModule;

import Cactus.CactusXMLParser;
import Cactus.Design.DataModule.FlowEvent;
import Cactus.Design.DataModule.TYPE.CactusEvent;
import Cactus.Design.DataModule.TYPE.Consequent;
import Cactus.Design.InputModule.TYPE.Event;
import Cactus.Design.PaneModule.PANE.PROFILE.RectangleProfile;
import org.apache.log4j.Logger;

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
    public ArrayList<CactusEvent> cactusEventList = new ArrayList<CactusEvent>();

    public OriginDataFactory(String path)
    {
        this.eventList.addAll(getInstanceFromXML(path).eventList);
        eventDependence();
        cactusEventList.addAll(cactusEventList());

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

    private void eventDependence()
    {
        for (Event event : eventList)
        {
            for (Event.Consequence event_consequence : event.consequenceList)
            {
                for (String event_consequence_ledTo : event_consequence.ledToList)
                {
                    for (Event event2AddDependence : eventList)
                    {
                        if (event2AddDependence.name.equalsIgnoreCase(event_consequence_ledTo))
                        {
                            event2AddDependence.dependList.add(event.name);
                        }
                    }
                }
            }
        }
    }

    public ArrayList<CactusEvent> cactusEventList()
    {
        ArrayList<CactusEvent> cactusEventList = new ArrayList<CactusEvent>();
        for (Event event : eventList)
        {
            CactusEvent cactusEvent = new FlowEvent(new RectangleProfile());
            cactusEvent.eventProfile.name = event.name;
            for (String event_dependName : event.dependList)
            {
                for (Event eventInSearchLoop : eventList)
                {
                    if (eventInSearchLoop.name.equalsIgnoreCase(event_dependName))
                    {
                        cactusEvent.eventProfile.dependentEventList.add(eventList.indexOf(eventInSearchLoop));
                        break;
                    }
                }
            }

            for (Event.Consequence consequence : event.consequenceList)
            {
                Consequent tempCon = new Consequent();
                tempCon.consequentName = consequence.name;
                for (String event_consequence_affectBy : consequence.affectByList)
                {
                    for (Event eventInSearchLoop : eventList)
                    {
                        if (eventInSearchLoop.name.equalsIgnoreCase(event_consequence_affectBy))
                        {
                            tempCon.affectByList.add(eventList.indexOf(eventInSearchLoop));
                            break;
                        }
                    }
                }
                for (String event_consequence_ledTo : consequence.ledToList)
                {
                    for (Event eventInSearchLoop : eventList)
                    {
                        if (eventInSearchLoop.name.equalsIgnoreCase(event_consequence_ledTo))
                        {
                            tempCon.ledToList.add(eventList.indexOf(eventInSearchLoop));
                            break;
                        }
                    }
                }
                cactusEvent.eventProfile.consequentList.add(tempCon);
            }

            for (Event.Consequence exception : event.exceptionList)
            {
                Consequent tempCon = new Consequent();
                tempCon.consequentName = exception.name;
                for (String event_exception_affectBy : exception.affectByList)
                {
                    for (Event eventInSearchLoop : eventList)
                    {
                        if (eventInSearchLoop.name.equalsIgnoreCase(event_exception_affectBy))
                        {
                            tempCon.affectByList.add(eventList.indexOf(eventInSearchLoop));
                            break;
                        }
                    }
                }
                for (String event_exception_ledTo : exception.ledToList)
                {
                    for (Event eventInSearchLoop : eventList)
                    {
                        if (eventInSearchLoop.name.equalsIgnoreCase(event_exception_ledTo))
                        {
                            tempCon.ledToList.add(eventList.indexOf(eventInSearchLoop));
                            break;
                        }
                    }
                }
                cactusEvent.eventProfile.exceptionList.add(tempCon);
            }
            cactusEventList.add(cactusEvent);
        }
        return cactusEventList;
    }

//    public void cactusEventWeight()
//    {
//        boolean searchDone = false;
//        while (!searchDone)
//        {
//              for()
//        }
//    }

}
