package Cactus.test.DataModule;

import Cactus.Design.DataModule.TYPE.CactusEvent;
import Cactus.Design.PaneModule.PANE.PROFILE.RectangleProfile;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.testng.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 11/6/14
 * Time: 8:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class testEvent
{
    @Test
    public void testTransColor()
    {
        boolean res;
        CactusEvent eve = new CactusEvent(new RectangleProfile())
        {
        };
        double width = eve.getShapeProfile().getForm().getWidth();
        assertEquals(eve.shapeManager.getColor().getRed(), 131);
        res = eve.shapeManager.transform4SelectEffect();
        assertEquals(res, false);
        res = eve.shapeManager.transform4SelectEffect();
        assertEquals(res, false);
        res = eve.shapeManager.transform4SelectEffect();
        assertEquals(res, false);
        res = eve.shapeManager.transform4SelectEffect();
        assertEquals(res, false);
        res = eve.shapeManager.transform4SelectEffect();
        assertEquals(res, true);
        assertEquals(eve.shapeManager.getColor().getRed(), 17);
        System.out.println(eve.shapeManager.getColor().getGreen());
        res = eve.shapeManager.transform4SelectEffect();
        assertEquals(res, false);
        res = eve.shapeManager.transform4SelectEffect();
        assertEquals(res, false);
        res = eve.shapeManager.transform4SelectEffect();
        assertEquals(res, false);
        res = eve.shapeManager.transform4SelectEffect();
        assertEquals(res, false);
        res = eve.shapeManager.transform4SelectEffect();
        assertEquals(res, true);
        assertEquals(eve.shapeManager.getColor().getRed(), 131);
        assertEquals(width, eve.getShapeProfile().getForm().getWidth());
    }


    class container
    {
        int i = 1;
    }

    @Test
    public void test()
    {

        ArrayList<container> list = new ArrayList<container>();
        ArrayList<container> listnewPointer = new ArrayList<container>();
        ArrayList<container> listnewClone = new ArrayList<container>();
        list.add(new container());
        list.add(new container());
        listnewPointer.addAll(list);
        listnewClone.addAll((Collection<container>) list.clone());
        System.out.println(listnewPointer.get(0).i);
        System.out.println(listnewClone.get(0).i);
        System.out.println();
        list.clear();
        listnewPointer.clear();
        System.out.println(listnewPointer.size());
        System.out.println(listnewClone.size());
        System.out.println(list.size());
        System.out.println();
        list.add(new container());
        listnewPointer.addAll((Collection<? extends container>) list.clone());
        System.out.println(listnewPointer.size());
    }

    @Test
    public void testRemoveArrayList()
    {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list.size() + " " + list.get(1));
        list.remove(1);
        System.out.println(list.size() + " " + list.get(1));
    }

    @Test
    public void testRemoveArrayList1()
    {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        for(int i=0;i<list.size();)
        {
            System.out.println(list.size() + " " + list.get(i));
            list.remove(i);
        }
    }
}
