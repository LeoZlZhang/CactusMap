package Cactus.Design.PaneModule.PANE;


import Cactus.Design.MouseEvent.MouseAdapter4Universe;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 10/23/14
 * Time: 6:01 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Pane extends JPanel //implements MouseAdapter4Universe
{
    public abstract void addCactus(Object obj);
    public abstract void removeCactus(Object obj);
}
