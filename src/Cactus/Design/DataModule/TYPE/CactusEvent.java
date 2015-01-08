package Cactus.Design.DataModule.TYPE;

import Cactus.Design.PaneModule.PANE.PROFILE.TYPE.Profile;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 11/7/14
 * Time: 7:54 PM
 */
public abstract class CactusEvent extends Shape
{
    public EventProfile eventProfile;

    protected CactusEvent(Profile shapeProfile)
    {
        super(shapeProfile);
        eventProfile = new EventProfile();
    }

    public enum EventType
    {
        NORMAL, CONFIG, TIMER, DYNAMIC
    }

    public class EventProfile
    {
        public String name = "";
        public EventType eventType = EventType.NORMAL;
        public ArrayList<Integer> dependentEventList = new ArrayList<Integer>();
        public ArrayList<Integer> resultEventList = new ArrayList<Integer>();//to replace by consequentList, change code in MouseAdapter and SelectActionListener
        public ArrayList<Consequent> consequentList = new ArrayList<Consequent>();
        public ArrayList<Consequent> exceptionList = new ArrayList<Consequent>();
        public int weight = 1;
    }
}
