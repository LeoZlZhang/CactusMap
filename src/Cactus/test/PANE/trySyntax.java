package Cactus.test.PANE;

import org.testng.annotations.Test;

import javax.swing.text.TabSet;
import java.util.ArrayList;


/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 10/28/14
 * Time: 7:51 PM
 */
public class trySyntax
{
    @Test
    public void testFinal()
    {
        Cell myCell = new Cell();
        myCell.i = 100;
        Container myCon = new Container(myCell);
        System.out.println(myCon.cell.i);
        myCell.i = 99;
        System.out.println(myCon.cell.i);
    }

    class Cell
    {
        public int i = 0;

        Cell(int i)
        {
            this.i = i;
        }
        Cell()
        {

        }
    }

    class Container
    {
        public final Cell cell;

        Container(Cell cell)
        {
            this.cell = cell;
        }
    }

//    private void trapFun(final ArrayList<Integer> list)
//    {
//        list.add(5);
//    }

//    @Test
//    public void testTrapInCopyInstance()
//    {
//        ArrayList<Integer> list1 = new ArrayList<Integer>();
//        list1.add(1);
//        list1.add(2);
//        list1.add(3);
//        System.out.println(list1.size());
//        ArrayList<Integer> list2 = list1;
//        ArrayList<Integer> list3 = new ArrayList<java.lang.Integer>(list1);
//        System.out.println(list2.size());
//        list1.add(4);
//        System.out.println(list1.size());
//        System.out.println(list2.size());
//        System.out.println(list3.size());
//        System.out.println("----");
//        trapFun(list1);
//        System.out.println(list1.size());
//        System.out.println(list2.size());
//        System.out.println(list3.size());
//    }


    @Test
    public void testArrayList()
    {
        ArrayList<Cell> list = new ArrayList<Cell>();
        list.add(new Cell(1));
        list.add(new Cell(2));
        list.add(new Cell(3));
        for(int i=0;i<list.size();i++)
        {
            System.out.println(list.get(i).i);
        }
        list.get(2).i=10;
        for(int i=0;i<list.size();i++)
        {
            System.out.println(list.get(i).i);
        }
    }
}
