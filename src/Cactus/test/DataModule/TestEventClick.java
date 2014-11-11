package Cactus.test.DataModule;

import Cactus.Design.PaneModule.PANE.MouseEvent.Listener4JFrame;
import Cactus.test.MouseEvent.Pane4TestMouse;
import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 11/11/14
 * Time: 10:55 AM
 */
public class TestEventClick
{
    @Test
    public void testClick() throws InterruptedException
    {
        TestPane4SelectEvent pane = new TestPane4SelectEvent();
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
