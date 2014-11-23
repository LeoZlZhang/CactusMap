package Cactus.Design.DataModule;

import Cactus.Design.DataModule.TYPE.CactusEvent;
import Cactus.Design.PaneModule.PANE.PROFILE.TYPE.Profile;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 11/8/14
 * Time: 9:26 PM
 */
public class FlowEvent extends CactusEvent
{
    public enum DependentType
    {
        Necessary, Optional, Force
    }

    public DependentType dependentType;


    public FlowEvent(Profile shapeProfile)
    {
        super(shapeProfile);
    }

    public FlowEvent(Profile profile, DependentType dependentType)
    {
        super(profile);
        this.dependentType = dependentType;
    }
}
