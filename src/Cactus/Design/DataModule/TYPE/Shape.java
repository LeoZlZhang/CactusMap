package Cactus.Design.DataModule.TYPE;

import Cactus.Design.PaneModule.AXIS.LogicSpace;
import Cactus.Design.PaneModule.AXIS.POSITION.Type.Position;
import Cactus.Design.PaneModule.PANE.PROFILE.RectangleProfile;
import Cactus.Design.PaneModule.PANE.PROFILE.TYPE.Profile;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: ezilizh
 * Date: 11/8/14
 * Time: 8:43 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Shape
{
    private final Profile profile;
    public ShapeManager shapeManager;

    private enum Trans
    {
        S0, S1, S2, S3, S4, S5,
        D0, D1, D2, D3, D4, D5,
        A0, A1, A2, A3, A4, A5,
    }

    protected Shape(Profile profile)
    {
        this.shapeManager = new ShapeManager();
        this.profile = new RectangleProfile(profile);
    }

    public boolean hover(Position mousePos, LogicSpace universe)
    {
        Profile profile = universe.trans2EarthView(this.profile);
        return (mousePos.getX() >= profile.getPosition().getX())
                && (mousePos.getX() <= (profile.getPosition().getX() + profile.getForm().getWidth()))
                && (mousePos.getY() >= profile.getPosition().getY())
                && (mousePos.getY() <= (profile.getPosition().getY() + profile.getForm().getHeight()));
    }

    public Profile getShapeProfile()
    {
        return profile.getCopy();
    }

    public class ShapeManager
    {
        private Color color;
        private final static int R0 = 131;
        private final static int G0 = 175;
        private final static int B0 = 155;
        private final static int R1 = 17;
        private final static int G1 = 63;
        private final static int B1 = 61;
        private final Color COL0 = new Color(R0, G0, B0);
        private final Color COL1 = new Color(R0 + (R1 - R0) / 5, G0 + (G1 - G0) / 5, B0 + (B1 - B0) / 5);
        private final Color COL2 = new Color(R0 + (R1 - R0) * 2 / 5, G0 + (G1 - G0) * 2 / 5, B0 + (B1 - B0) * 2 / 5);
        private final Color COL3 = new Color(R0 + (R1 - R0) * 3 / 5, G0 + (G1 - G0) * 3 / 5, B0 + (B1 - B0) * 3 / 5);
        private final Color COL4 = new Color(R0 + (R1 - R0) * 4 / 5, G0 + (G1 - G0) * 4 / 5, B0 + (B1 - B0) * 4 / 5);
        private final Color COL5 = new Color(R1, G1, B1);
        private Trans transState4Select = Trans.S0;
        private Trans transState4Affect = Trans.A0;
        private Trans transState4Depend = Trans.D0;

        private boolean direction = true;

        public ShapeManager()
        {
            color = COL0;
        }

        public boolean transform4SelectEffect()
        {
            switch (transState4Select)
            {
                case S0:
                    transState4Select = Trans.S1;
                    color = COL1;
                    ((RectangleProfile) profile).bigger();
                    direction = true;
                    break;
                case S1:
                    if (direction)
                    {
                        transState4Select = Trans.S2;
                        color = COL2;
                        ((RectangleProfile) profile).bigger();
                    } else
                    {
                        transState4Select = Trans.S0;
                        color = COL0;
                        ((RectangleProfile) profile).smaller();
                    }
                    break;
                case S2:
                    if (direction)
                    {
                        transState4Select = Trans.S3;
                        color = COL3;
                        ((RectangleProfile) profile).bigger();
                    } else
                    {
                        transState4Select = Trans.S1;
                        color = COL1;
                        ((RectangleProfile) profile).smaller();
                    }
                    break;
                case S3:
                    if (direction)
                    {
                        transState4Select = Trans.S4;
                        color = COL4;
                        ((RectangleProfile) profile).bigger();
                    } else
                    {
                        transState4Select = Trans.S2;
                        color = COL2;
                        ((RectangleProfile) profile).smaller();
                    }
                    break;
                case S4:
                    if (direction)
                    {
                        transState4Select = Trans.S5;
                        color = COL5;
                        ((RectangleProfile) profile).bigger();
                    } else
                    {
                        transState4Select = Trans.S3;
                        color = COL3;
                        ((RectangleProfile) profile).smaller();
                    }
                    break;
                case S5:
                    transState4Select = Trans.S4;
                    color = COL4;
                    ((RectangleProfile) profile).smaller();
                    direction = false;
                    break;
            }

            return transState4Select == Trans.S5 || transState4Select == Trans.S0;
        }

        public boolean transform4AffectEffect()
        {
            switch (transState4Affect)
            {
                case A0:
                    transState4Affect = Trans.A1;
                    color.brighter();
                    direction = true;
                    break;
                case A1:
                    if (direction)
                    {
                        transState4Affect = Trans.A2;
                        color.brighter();
                    } else
                    {
                        transState4Affect = Trans.A0;
                        color.darker();
                    }
                    break;
                case A2:
                    if (direction)
                    {
                        transState4Affect = Trans.A3;
                        color.brighter();
                    } else
                    {
                        transState4Affect = Trans.A1;
                        color.darker();
                    }
                    break;
                case A3:
                    if (direction)
                    {
                        transState4Affect = Trans.A4;
                        color.brighter();
                    } else
                    {
                        transState4Affect = Trans.A2;
                        color.darker();
                    }
                    break;
                case A4:
                    if (direction)
                    {
                        transState4Affect = Trans.A5;
                        color.brighter();
                    } else
                    {
                        transState4Affect = Trans.A3;
                        color.darker();
                    }
                    break;
                case A5:
                    transState4Affect = Trans.A4;
                    color.darker();
                    direction = false;
                    break;
            }

            return transState4Affect == Trans.A5 || transState4Affect == Trans.A0;
        }

        public boolean transform4DependEffect()
        {
            switch (transState4Depend)
            {
                case D0:
                    transState4Depend = Trans.D1;
                    color = COL1;
                    direction = true;
                    break;
                case D1:
                    if (direction)
                    {
                        transState4Depend = Trans.D2;
                        color = COL2;
                    } else
                    {
                        transState4Depend = Trans.D0;
                        color = COL0;
                    }
                    break;
                case D2:
                    if (direction)
                    {
                        transState4Depend = Trans.D3;
                        color = COL3;
                    } else
                    {
                        transState4Depend = Trans.D1;
                        color = COL1;
                    }
                    break;
                case D3:
                    if (direction)
                    {
                        transState4Depend = Trans.D4;
                        color = COL4;
                    } else
                    {
                        transState4Depend = Trans.D2;
                        color = COL2;
                    }
                    break;
                case D4:
                    if (direction)
                    {
                        transState4Depend = Trans.D5;
                        color = COL5;
                    } else
                    {
                        transState4Depend = Trans.D3;
                        color = COL3;
                    }
                    break;
                case D5:
                    transState4Depend = Trans.D4;
                    color = COL4;
                    direction = false;
                    break;
            }

            return transState4Depend == Trans.D5 || transState4Depend == Trans.D0;
        }

        public Color getColor()
        {
            return color;
        }
    }
}
