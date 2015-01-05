package Cactus.Design.InputModule.TYPE;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 11/22/14
 * Time: 11:19 AM
 */
public class Event
{
    public String name;
    public String type;
    public ArrayList<String> dependList = new ArrayList<String>();
    public ArrayList<Consequence> consequenceList = new ArrayList<Consequence>();
    public ArrayList<Consequence> exceptionList = new ArrayList<Consequence>();
    public int weight = 0;

    public class Consequence
    {
        public String name;
        public ArrayList<String> ledToList = new ArrayList<String>();
        public ArrayList<String> affectByList = new ArrayList<String>();
    }
}
