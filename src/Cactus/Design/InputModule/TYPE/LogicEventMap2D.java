package Cactus.Design.InputModule.TYPE;

import org.testng.annotations.Test;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 12/1/14
 * Time: 11:06 AM
 */
public class LogicEventMap2D
{
    private ArrayList<ArrayList<Event>> positionEventList = new ArrayList<ArrayList<Event>>();

    public void insertRow(int row, int col, Event eve)
    {
        if (row > positionEventList.size())
        {
            row = positionEventList.size() + 1;
        }
    }

    public void insertColumn(int row, int col, Event eve)
    {
        if (row > positionEventList.size())
        {
            row = positionEventList.size();
        }
    }

    /**
     *
     * @param column2Refer Assume refer column is stander, not need modify
     * @param column2Adjust
     */
    private void adjustColumn(ArrayList<Event> column2Refer, ArrayList<Event> column2Adjust)
    {
        if (column2Refer.size() == 0 || column2Adjust.size() == 0)
            return;

        for (int step = 0; step < Math.max(column2Refer.size(), column2Adjust.size()); step++)
        {
            if (column2Refer.get(step) == null)
            {
                continue;
            } else
            {
                String dependEveName = column2Refer.get(step).dependList.get(0);
            }
        }
    }

//    private getEventIndex()

    public class PositionEvent
    {
        public int xAxis;
        public int yAxis;
        public Event event;

        public PositionEvent(int x, int y, Event event)
        {
            this.xAxis = x;
            this.yAxis = y;
            this.event = event;
        }

        public void moveDown()
        {
            xAxis++;
        }

        public void moveUp()
        {
            xAxis--;
        }

        public void moveRight()
        {
            yAxis++;
        }

        public void moveLeft()
        {
            yAxis--;
        }
    }
}
