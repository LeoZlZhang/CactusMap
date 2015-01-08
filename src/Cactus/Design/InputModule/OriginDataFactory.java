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
        cactusEventList();
        cactusEventWeight();
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

    public void cactusEventList()
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
        this.cactusEventList.addAll(cactusEventList);
    }

    public void cactusEventWeight()
    {
        boolean searchDone = false;
        while (!searchDone)
        {
            searchDone = true;
            for (CactusEvent noChainCactusEvent : cactusEventList)
            {
                if (noChainCactusEvent.eventProfile.weight == 1)
                {
                    if (noChainCactusEvent.eventProfile.consequentList.size() == 0)
                    {
                        noChainCactusEvent.eventProfile.weight = 1;
                    }
                    if (noChainCactusEvent.eventProfile.consequentList.size() > 0)
                    {
                        searchDone = false;
                        boolean allLedToEventHasWeight = true;
                        for (Consequent cactusEvent_consequent : noChainCactusEvent.eventProfile.consequentList)
                        {
                            for (Integer ledToEventID : cactusEvent_consequent.ledToList)
                            {
                                noChainCactusEvent.eventProfile.weight += cactusEventList.get(ledToEventID).eventProfile.weight;
                                if ((cactusEventList.get(ledToEventID).eventProfile.weight == 1)
                                        && (cactusEventList.get(ledToEventID).eventProfile.consequentList.size() != 0))
                                {
                                    allLedToEventHasWeight = false;
                                }
                            }
                        }
                        if (!allLedToEventHasWeight)
                        {
                            noChainCactusEvent.eventProfile.weight = 1;
                        }
                    }
                    if (noChainCactusEvent.eventProfile.exceptionList.size() > 0)
                    {
                        //do nothing, led to event in exception should not be count in weight
                    }
                    if (noChainCactusEvent.eventProfile.dependentEventList.size() > 0)
                    {
                        //do nothing, only accumulate weight from leo to event in consequent
                    }
                }
            }
        }
    }

    public int[][] Map2DAddLongestEventIndexChain(ArrayList<CactusEvent> weightCactusEventList, ArrayList<CactusEvent> remainCactusEventList)
    {
        int[][] map2D = new int[weightCactusEventList.size()][weightCactusEventList.size()];
        for (int i = 0; i < weightCactusEventList.size(); i++)
        {
            for (int j = 0; j < weightCactusEventList.size(); j++)
            {
                map2D[i][j] = -1;
            }
        }

        ArrayList<Integer> nextCactusEventIndexList2PutIntoMap = headEventIDInLongestEventChain(weightCactusEventList);
        ArrayList<Integer> tempIndexList2PutIntoMap = new ArrayList<Integer>();

        remainCactusEventList.clear();
        remainCactusEventList.addAll(weightCactusEventList);
        int nextRow = 0, nextColumn;

        while (!theEnd(nextCactusEventIndexList2PutIntoMap, weightCactusEventList))
        {
            nextColumn = 0;
            tempIndexList2PutIntoMap.clear();
            tempIndexList2PutIntoMap.addAll(nextCactusEventIndexList2PutIntoMap);
            nextCactusEventIndexList2PutIntoMap.clear();
            for (int cactusEventIndex2PutIntoMap : tempIndexList2PutIntoMap)
            {
                map2D[nextRow][nextColumn++] = cactusEventIndex2PutIntoMap;
                remainCactusEventList.remove(weightCactusEventList.get(cactusEventIndex2PutIntoMap));
                for (Consequent cactusEvent2PutIntoMap_Consequent : weightCactusEventList.get(cactusEventIndex2PutIntoMap).eventProfile.consequentList)
                {
                    for (int ledToEventId_Consequent : cactusEvent2PutIntoMap_Consequent.ledToList)
                    {
                        if (!containInMap2D(ledToEventId_Consequent, map2D)
                                && (!nextCactusEventIndexList2PutIntoMap.contains(ledToEventId_Consequent)))
                        {
                            nextCactusEventIndexList2PutIntoMap.add(ledToEventId_Consequent);
                        }
                    }
                }
            }
            nextRow++;

        }
        nextColumn = 0;
        for (int cactusEventIndex2PutIntoMap : nextCactusEventIndexList2PutIntoMap)
        {
            map2D[nextRow][nextColumn++] = cactusEventIndex2PutIntoMap;
            remainCactusEventList.remove(weightCactusEventList.get(cactusEventIndex2PutIntoMap));
        }

        return map2D;
    }

    private boolean containInMap2D(int indexValue, int[][] map2D)
    {

        for (int[] nextColumn : map2D)
        {
            for (int index : nextColumn)
            {
                if (indexValue == index)
                {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean theEnd(ArrayList<Integer> cactusEventIndexList2PutIntoMap, ArrayList<CactusEvent> cactusEventList)
    {
        boolean theEnd = true;
        for (int cactusEventIndex : cactusEventIndexList2PutIntoMap)
        {
            if (cactusEventList.get(cactusEventIndex).eventProfile.consequentList.size() > 0)
            {
                theEnd = false;
            }
        }
        return theEnd;
    }

    public ArrayList<Integer> headEventIDInLongestEventChain(ArrayList<CactusEvent> noWeightCactusEventList)
    {
        ArrayList<Integer> heaviestEventIDList = new ArrayList<Integer>();
        int maxWeight = 1;
        for (CactusEvent event2FindMaxWeight : noWeightCactusEventList)
        {
            if (event2FindMaxWeight.eventProfile.weight > maxWeight)
            {
                maxWeight = event2FindMaxWeight.eventProfile.weight;
            }
        }
        for (CactusEvent maxWeightEvent : noWeightCactusEventList)
        {
            if (maxWeightEvent.eventProfile.weight == maxWeight)
            {
                heaviestEventIDList.add(cactusEventList.indexOf(maxWeightEvent));
            }
        }
        return heaviestEventIDList;
    }

    public int[][] Map2DAddRestEventIndexChain(ArrayList<CactusEvent> weightCactusEventList,
                                               ArrayList<CactusEvent> remainCactusEventList,
                                               int[][] map2D)
    {
        while (remainCactusEventList.size() > 0)
        {
            for (int nexRow = 0; nexRow < map2D.length; nexRow++)
            {
                for (int nextColumn = 0; nextColumn < map2D[nexRow].length; nextColumn++)
                {
                    if (map2D[nexRow][nextColumn] >= 0)
                    {
                        for (int dependEventId : weightCactusEventList.get(map2D[nexRow][nextColumn]).eventProfile.dependentEventList)
                        {
                            if (remainCactusEventList.contains(weightCactusEventList.get(dependEventId)))
                            {
                                //LoopPoint1
                                for (int columnIndex2PutIntoMap = nextColumn; columnIndex2PutIntoMap < map2D[nexRow - 1].length; columnIndex2PutIntoMap++)
                                {
                                    if (map2D[nexRow - 1][columnIndex2PutIntoMap] < 0)
                                    {
                                        map2D[nexRow - 1][columnIndex2PutIntoMap] = dependEventId;
                                        remainCactusEventList.remove(weightCactusEventList.get(dependEventId));
                                        break;//break to LoopPoint1
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return map2D;
    }
}
