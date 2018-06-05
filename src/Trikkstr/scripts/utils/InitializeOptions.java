package Trikkstr.scripts.utils;

import Trikkstr.scripts.goblin_killer.GoblinKiller;

import javax.swing.*;
import java.awt.*;


public class InitializeOptions extends Frame
{
    private int selection;

    public InitializeOptions()
    {
        //setLayout(new FlowLayout());

        selection = JOptionPane.showConfirmDialog(null, "Would you like to pickup and bury bones?",
                "Bury Bones?", JOptionPane.YES_NO_OPTION);
        /*
        Frame frame = new JFrame("TITLE");
        frame.setVisible(true);
        frame.setSize(600, 400);

        Panel pnl = new Panel();          // Panel is a container
        Button btn = new Button("Press"); // Button is a component
        TextField attack = new TextField();
        TextField strength = new TextField();
        TextField defense = new TextField();
        TextField prayer = new TextField();
        pnl.add(btn);
        pnl.add(attack);
        pnl.add(strength);
        pnl.add(defense);
        pnl.add(prayer);

        frame.add(pnl);
        //frame.
        //pnl.setVisible(true);
        */

        /*
        int attackL = Integer.parseInt(attack.getText());
        int strengthL = Integer.parseInt(strength.getText());
        int defenseL = Integer.parseInt(defense.getText());
        int prayerL = Integer.parseInt(prayer.getText());


        System.out.printf("attack: %d\nstrength: %d\ndefense: %d\nprayer: %d\n", attackL, strengthL, defenseL, prayerL);
        */

        if(selection == 0)
        {
            GoblinKiller.bones = true;
        }
        else
        {
           GoblinKiller.bones = false;
        }
    }

}
