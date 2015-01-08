package Cactus.test.InputModule;

import Cactus.Design.DataModule.TYPE.CactusEvent;
import Cactus.Design.InputModule.OriginDataFactory;
import Cactus.Design.InputModule.TYPE.Event;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.assertEquals;

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
        ArrayList<CactusEvent> cactusEventList = odf.cactusEventList;
        for (CactusEvent eve : cactusEventList)
        {
            System.out.println("Event name=" + eve.eventProfile.name + " weight=" + eve.eventProfile.weight);
            for (Integer index : eve.eventProfile.dependentEventList)
                System.out.println("<" + eve.eventProfile.name + "> dependent on <" + cactusEventList.get(index).eventProfile.name + "> index=" + index);
            if (eve.eventProfile.consequentList.size() > 0)
            {
                for (Integer index : eve.eventProfile.consequentList.get(0).ledToList)
                    System.out.println("<" + eve.eventProfile.name + "> led to <" + cactusEventList.get(index).eventProfile.name + "> index=" + index);
            }
            System.out.println();
        }
    }

    @Test
    public void testHeadEventIDInLongestEventChain()
    {
        String path = "C:\\Users\\ezilizh\\Desktop\\Leo\\Leo\\Code\\mygit\\CactusMap\\conf\\cfg.xml";
        OriginDataFactory odf = new OriginDataFactory(path);
        ArrayList<CactusEvent> cactusEventList = odf.cactusEventList;
        ArrayList<Integer> resList = odf.headEventIDInLongestEventChain(cactusEventList);
        for (int heaviestEventIndex : resList)
        {
            assertEquals(0, heaviestEventIndex);
            assertEquals(8, cactusEventList.get(heaviestEventIndex).eventProfile.weight);
            System.out.println("[Test] Heaviest event index=" + heaviestEventIndex + ", weight=" + cactusEventList.get(heaviestEventIndex).eventProfile.weight);
        }
    }

    @Test
    public void Map2DAddLongestEventIndexChain()
    {
        String path = "C:\\Users\\ezilizh\\Desktop\\Leo\\Leo\\Code\\mygit\\CactusMap\\conf\\cfg.xml";
        OriginDataFactory odf = new OriginDataFactory(path);
        ArrayList<CactusEvent> cactusEventList = odf.cactusEventList;
        ArrayList<CactusEvent> remainCactusEventList = new ArrayList<CactusEvent>();
        int[][] map2D = odf.Map2DAddLongestEventIndexChain(cactusEventList, remainCactusEventList);
        System.out.println("Test map row number=" + map2D.length);
        for (int i = 0; i < map2D.length; i++)
        {
            for (int j = 0; j < map2D[i].length; j++)
            {
                if (map2D[i][j] >=0)
                {
                    System.out.println("Test row" + i + " column" + j + " " + cactusEventList.get(map2D[i][j]).eventProfile.name);
                }
            }
        }
        System.out.println();
        for (CactusEvent remainEvent : remainCactusEventList)
        {
            System.out.println("Test remain event name=" + remainEvent.eventProfile.name);
        }
        assertEquals("Add Device", cactusEventList.get(map2D[0][0]).eventProfile.name);
        assertEquals("Add content", cactusEventList.get(map2D[5][0]).eventProfile.name);
    }

    @Test
    public void testMap2DAddRestEventIndexChain()
    {
        String path = "C:\\Users\\ezilizh\\Desktop\\Leo\\Leo\\Code\\mygit\\CactusMap\\conf\\cfg.xml";
        OriginDataFactory odf = new OriginDataFactory(path);
        ArrayList<CactusEvent> cactusEventList = odf.cactusEventList;
        ArrayList<CactusEvent> remainCactusEventList = new ArrayList<CactusEvent>();
        int[][] map2D = odf.Map2DAddLongestEventIndexChain(cactusEventList, remainCactusEventList);

        assertEquals("Add Device", cactusEventList.get(map2D[0][0]).eventProfile.name);
        assertEquals("Add content", cactusEventList.get(map2D[5][0]).eventProfile.name);
        map2D = odf.Map2DAddRestEventIndexChain(cactusEventList, remainCactusEventList,map2D);
        System.out.println("Test map row number=" + map2D.length);
        for (int i = 0; i < map2D.length; i++)
        {
            for (int j = 0; j < map2D[i].length; j++)
            {
                if (map2D[i][j] >=0)
                {
                    System.out.println("Test row" + i + " column" + j + " " + cactusEventList.get(map2D[i][j]).eventProfile.name);
                }
            }
        }
        System.out.println();
        for (CactusEvent remainEvent : remainCactusEventList)
        {
            System.out.println("Test remain event name=" + remainEvent.eventProfile.name);
        }
    }
}
