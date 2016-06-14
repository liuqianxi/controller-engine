package com.nicholas.engine;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GeneratorFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    JTextField                table_field;
    JTextField                base_field;
    JTextField                target_field;

    public void drawFrame() {
        JButton jButton = new JButton("���ɴ���");
        JLabel jLabel_table = new JLabel("���ݿ����");
        JLabel jLabel_base = new JLabel("����package");
        JLabel jLabel_target = new JLabel("�ļ����package");
        table_field = new JTextField(30);
        base_field = new JTextField(15);
        target_field = new JTextField(15);
        table_field.setText("t_user,t_leave_apply");
        base_field.setText("com.nicholas");
        target_field.setText("com.nicholas.controller");
        
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout());

        jPanel.add(jLabel_table);
        jPanel.add(table_field);
        jPanel.add(jLabel_base);
        jPanel.add(base_field);
        jPanel.add(jLabel_target);
        jPanel.add(target_field);
        jPanel.add(jButton);

        this.add(jPanel);
        this.setSize(1000, 360);
        this.setLocation(300, 300);
        this.setVisible(true);

        jButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String s = table_field.getText();
                String basePath = base_field.getText();
                String targetPath = target_field.getText();
                List<String> list = new ArrayList<String>();
                if (!s.isEmpty()) {
                    String[] tables = s.split(",");

                    int i = 0;
                    if (tables.length >= 1) {
                        for (String table : tables) {
                            list.add(i, table);
                            i++;
                        }
                    }
                    try {
                        new TemplateEngine().Generate(list, basePath, targetPath);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }

            }
        });

        WindowListener wndCloser = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        addWindowListener(wndCloser);
    }

    public static void main(String[] args) {
        new GeneratorFrame().drawFrame();
    }
}
