package extra;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NestedClasses {
    int v;

    public static void m() {
        JButton jb = new JButton();
        jb.addActionListener(e -> {
            System.out.println(e);
            // this would refer to the instance of the class containing method m
            // but since method m is static, this cannot be used
            //System.out.println("action listener: " + this.v);
        });
        ActionListener actionListener = new ActionListener() {
            int v;

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(e);
                // this refers to the instance of the anonymous class
                System.out.println("action listener" + this.v);
                jb.removeActionListener(this);
            }
        };
        jb.addActionListener(actionListener);
    }
}
