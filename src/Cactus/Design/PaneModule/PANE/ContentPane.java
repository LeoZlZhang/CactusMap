package Cactus.Design.PaneModule.PANE;

import Cactus.Design.PaneModule.PANE.FORM.TYPE.Form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 11/15/14
 * Time: 11:32 AM
 */
public class ContentPane extends Pane
{
    private AnimationSuit animationSuit = new AnimationSuit();
    private JPanel topPane = null;

    protected ContentPane(Form universeForm)
    {
        super(universeForm);
        animationSuit.maxWidth = (int) universeForm.getWidth();
        initial();
    }

    private void initial()
    {
        setSize(new Dimension(1, 1));
        setPreferredSize(this.getSize());
        setBackground(new Color(200, 200, 250));
    }

    public void setTopPane(JPanel topPane)
    {
        this.topPane = topPane;
    }

    public void turnOn()
    {
        animationSuit.showOutFlag = true;
        if (!animationSuit.animationTimer4ShowOut.isRunning())
            animationSuit.animationTimer4ShowOut.start();
    }

    public void turnOff()
    {
        animationSuit.showOutFlag = false;
        if (!animationSuit.animationTimer4ShowOut.isRunning())
            animationSuit.animationTimer4ShowOut.start();
    }


    public class AnimationSuit
    {
        public Timer animationTimer4ShowOut = new Timer(animationInterval4ShowOut, new ActionListener4ContentPane());
        public boolean showOutFlag = false;
        public int maxWidth = 200;
        private final static int minWidth = 1;
        private final static int animationInterval4ShowOut = 10;
        private final static double damping4ShowOutAnimation = 0.3;

        private class ActionListener4ContentPane implements ActionListener
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                double V, width;
                if (showOutFlag)
                {
                    V = Math.pow((maxWidth - getWidth()),damping4ShowOutAnimation) - 1;
                    width = Math.max(1, V * animationInterval4ShowOut) + getWidth();
                    System.out.println(V + " " + width + " "+ getWidth());
                    if (width >= maxWidth)
                    {
                        width = maxWidth;
                        animationTimer4ShowOut.stop();
                        System.out.println("DEBUG AnimationSuit@ContentPane timer stop (turn on)");
                    }
                } else
                {
                    V = Math.pow((getWidth() - minWidth),damping4ShowOutAnimation) - 1;
                    width = getWidth() - Math.max(1, V * animationInterval4ShowOut);
                    if (width <= minWidth)
                    {
                        width = minWidth;
                        animationTimer4ShowOut.stop();
                        System.out.println("DEBUG AnimationSuit@ContentPane timer stop (turn off)");
                    }
                }
                setSize((int) width, 1);
                setPreferredSize(getSize());
                if (topPane != null)
                    topPane.doLayout();
            }
        }
    }

}
