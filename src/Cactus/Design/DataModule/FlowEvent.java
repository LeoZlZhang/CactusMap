package Cactus.Design.DataModule;

import Cactus.Design.DataModule.TYPE.CactusEvent;
import Cactus.Design.PaneModule.PANE.PROFILE.TYPE.Profile;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 11/8/14
 * Time: 9:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class FlowEvent extends CactusEvent
{
    public enum DependentType
    {
        Necessary, Optional, Force
    }

    public DependentType dependentType;


    public FlowEvent(Profile profile)
    {
        super(profile);
    }

    public FlowEvent(Profile profile, DependentType dependentType)
    {
        super(profile);
        this.dependentType = dependentType;
    }
}
