package Cactus.test.MouseEvent;

import Cactus.Design.PaneModule.PANE.MouseEvent.Listener4JFrame;
import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 11/2/14
 * Time: 5:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class testMoveAndZoomInOutAndFrameResize
{
    @Test
    public void test60Sec() throws InterruptedException
    {
        Pane4TestMouse pane = new Pane4TestMouse();
        pane.setBackground(new Color(200, 200, 169));
        JFrame frame = new JFrame("test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(pane, BorderLayout.WEST);
        frame.setMinimumSize(new Dimension(500, 500));
        frame.addComponentListener(new Listener4JFrame(pane));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        Thread.sleep(60000);
    }
}
