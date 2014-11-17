package Cactus.Design.PaneModule.PANE;

import Cactus.Design.PaneModule.PANE.FORM.SpaceForm;
import Cactus.Design.PaneModule.PANE.MouseEvent.Listener4JFrame;
import Cactus.Design.PaneModule.PANE.MouseEvent.MouseAdapter4MapPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 11/15/14
 * Time: 10:11 AM
 */
public class TopFrame extends JFrame
{
    public Pane mapPane;
    public Pane contentPane;


    public TopFrame() throws HeadlessException
    {
        super("CactusMap");
        this.mapPane = new MapPane(new SpaceForm(2000,2000));
        this.contentPane = new ContentPane(new SpaceForm(200, 2000));
        initialFrame();
    }

    private void initialFrame()
    {
        /**
        * Set Mouse adapter for CENTER pane
         */
        MouseAdapter4MapPane mouseAdp = new MouseAdapter4MapPane(mapPane, contentPane, this);
        mapPane.addMouseListener(mouseAdp);
        mapPane.addMouseMotionListener(mouseAdp);
        mapPane.addMouseWheelListener(mouseAdp);
        /**
         * Add CENTER pane and EAST pane
         */
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(mapPane, BorderLayout.CENTER);
        this.getContentPane().add(contentPane, BorderLayout.EAST);
        ((ContentPane)contentPane).setTopPane((JPanel) this.getContentPane());
        /**
         * Set Frame style
          */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(700, 600));
        addComponentListener(new Listener4JFrame(mapPane));
        pack();
        setLocationRelativeTo(null);
    }

    public void turnOnContentPane()
    {
        ((ContentPane)contentPane).turnOn();
    }
    public void turnOffContentPane()
    {
        ((ContentPane)contentPane).turnOff();
    }
}
