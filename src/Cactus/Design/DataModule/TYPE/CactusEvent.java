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

    protected CactusEvent(Profile profile)
    {
        super(profile);
        eventProfile = new EventProfile();
    }

    public enum EventType
    {
        NORMAL, CONFIG, TIMER
    }

    public class EventProfile
    {
        public ArrayList<Integer> dependentEventList = new ArrayList<Integer>();
        public ArrayList<Integer> resultEventList = new ArrayList<Integer>();
        public String flow = "";
        public EventType eventType = EventType.NORMAL;
        public ArrayList<Consequent> consequentList = new ArrayList<Consequent>();
    }


}
