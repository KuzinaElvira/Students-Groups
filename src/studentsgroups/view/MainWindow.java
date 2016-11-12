package studentsgroups.view;

import studentsgroups.controller.Controller;
import studentsgroups.model.Data;
import studentsgroups.model.Group;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

/**
 * Created by molish on 11.11.2016.
 */
public class MainWindow extends JFrame {

    //region Объявление переменных окна групп
    private class MyMouseListener implements MouseListener{

        private JPopupMenu menu;

        public MyMouseListener(JPopupMenu menu){this.menu = menu;}

        @Override
        public void mouseClicked(MouseEvent e) {
            menu.setVisible(true);
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
    private class AddGroupActionListener implements ActionListener{

        private Frame owner;

        AddGroupActionListener(Frame owner){
            this.owner = owner;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            JDialog dial = new JDialog(owner);
            JTextField groupNumberTextField = new JTextField(15);
            JTextField groupFucultyTextField = new JTextField(15);
            JLabel groupNumberLabel = new JLabel("Group number");
            JLabel groupFacultyLabel = new JLabel("Group faculty");
            JButton addButton = new JButton("Add");

            dial.setBounds(200, 200, 260, 110);
            dial.setResizable(false);
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(Controller.addGroup(groupNumberTextField.getText(), groupFucultyTextField.getText())){
                        refreshTable();
                    }else {
                        JDialog error = new JDialog(dial);
                        error.setName("ERROR");
                        error.setBounds(300, 300, 250, 100);
                        error.setResizable(false);
                        Container container = error.getContentPane();
                        container.setLayout(new BorderLayout(2, 2));
                        container.add(new JLabel("Wrong faculty or group number entered!"),BorderLayout.CENTER);
                        JButton okButton = new JButton("OK");
                        okButton.setMaximumSize(new Dimension(60, 30));
                        okButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                dial.setVisible(false);
                                dial.dispose();
                                error.dispose();
                            }
                        });
                        container.add(okButton, BorderLayout.SOUTH);
                        error.setVisible(true);
                    }
                }
            });
            Container container = dial.getContentPane();
            container.setLayout(new BorderLayout(2, 2));
            JPanel panel = new JPanel(new GridLayout(2, 2));
            panel.add(groupNumberLabel);
            panel.add(groupNumberTextField);
            panel.add(groupFacultyLabel);
            panel.add(groupFucultyTextField);
            container.add(panel, BorderLayout.NORTH);
            container.add(addButton, BorderLayout.SOUTH);

            dial.setVisible(true);
        }
    }
    private class readActionListener implements ActionListener{

        Reader reader;

        public readActionListener(Reader reader){
            this.reader = reader;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int ret = fileChooser.showDialog(null, "Open file");
            if(ret == JFileChooser.APPROVE_OPTION){
                try{
                    if(reader.read(fileChooser.getSelectedFile()))
                        refreshTable();
                    else {
                        showErrorMessage("Error occured while reading file!", 250);
                    }
                }catch (IOException ex){
                    showErrorMessage(ex.getMessage(), 500);
                    ex.printStackTrace();
                }
            }
        }
    }
    private class writeActionListener implements ActionListener{

        private Writer writer;

        public writeActionListener(Writer writer){
            this.writer = writer;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int ret = fileChooser.showDialog(null, "Open file");
            if(ret == JFileChooser.APPROVE_OPTION){
                try{
                    if(!writer.write(fileChooser.getSelectedFile()))
                        showErrorMessage("Error occured while writing!", 250);
                }catch (IOException ex){
                    ex.printStackTrace();
                    showErrorMessage(ex.getMessage(), 500);
                }
            }
        }
    }

    private String[] headers = {"Number", "Faculty"};
    private MyTable groupsTable = new MyTable(getGroupsData(), headers);
    private JLabel groupsLabel = new JLabel("  Groups");
    private JScrollPane tableScrollPane = new JScrollPane(groupsTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private JButton addGroupButton = new JButton("Add group");

    private JPopupMenu popMenu = new JPopupMenu();
    private JMenuItem addMenuItem = new JMenuItem("Add group");
    private JMenuItem removeMenuItem = new JMenuItem("Remove group");

    private JPanel mainPanel = new JPanel(new BorderLayout(4, 4), true);
    private JPanel buttonPanel = new JPanel(new BorderLayout(2, 2));



    private JMenuBar menuBar = new JMenuBar();
    private JMenu menu = new JMenu("File");
        //меню считывания из файла
        private JMenu readMenuItem = new JMenu("Read");
            private JMenuItem readFromXMLFileMenuItem = new JMenuItem("Read from bin file");
            private JMenuItem readFromTextFileMenuItem = new JMenuItem("Read from text file");
        //меню записи в файл
        private JMenu writeToFileMenuItem = new JMenu("Write");
            private JMenuItem wrtiteToBinFileMenuItem = new JMenuItem("Write to bin file");
            private JMenuItem writeToTXTFileMenuItem = new JMenuItem("Write to txt file");
        //кнопка выхода из программы
        private JMenuItem exitMenuItem = new JMenuItem("Exit");
    //endregion
    //region Объявление переменных списка студентов
    private Group currentGroup;

    //endregion


    public MainWindow(){
        super("Group students");
        this.setBounds(100, 100, 400, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        //установка меню
        this.setJMenuBar(menuBar);
        menuBar.add(menu);
        menu.add(readMenuItem);
        menu.add(writeToFileMenuItem);
        menu.addSeparator();
        menu.add(exitMenuItem);
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        writeToFileMenuItem.add(writeToTXTFileMenuItem);
        writeToFileMenuItem.add(wrtiteToBinFileMenuItem);
        readMenuItem.add(readFromTextFileMenuItem);
        readMenuItem.add(readFromXMLFileMenuItem);

        //заполнение формы компонентами
        Container container = this.getContentPane();
        container.add(mainPanel);
        mainPanel.add(tableScrollPane, BorderLayout.EAST);
        mainPanel.add(buttonPanel, BorderLayout.WEST);

        //настройка компонентов
        buttonPanel.setPreferredSize(new Dimension(110, 496));
        buttonPanel.add(addGroupButton, BorderLayout.SOUTH);
        tableScrollPane.setPreferredSize(new Dimension(250, 496));
        buttonPanel.add(groupsLabel, BorderLayout.NORTH);
        groupsTable.setComponentPopupMenu(popMenu);
        popMenu.add(addMenuItem);
        popMenu.add(removeMenuItem);
        groupsTable.getTableHeader().setReorderingAllowed(false);

        //установка listeners
        tableScrollPane.addMouseListener(new MyMouseListener(popMenu));
        ActionListener addGroupActionListener = new AddGroupActionListener(this);
        addGroupButton.addActionListener(addGroupActionListener);
        addMenuItem.addActionListener(addGroupActionListener);
        removeMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(groupsTable.getSelectedRowCount() == 1){
                    String groupNumber = groupsTable.getValueAt(groupsTable.getSelectedRow(), 0).toString();
                    String groupFaculty = groupsTable.getValueAt(groupsTable.getSelectedRow(), 1).toString();
                    Controller.removeGroup(groupNumber, groupFaculty);
                    refreshTable();
                }
            }
        });
        readFromTextFileMenuItem.addActionListener(new readActionListener(file -> Controller.readFromTxtFile(file)));
        readFromXMLFileMenuItem.addActionListener(new readActionListener(file -> Controller.readFromBinFile(file)));
        wrtiteToBinFileMenuItem.addActionListener(new writeActionListener(file -> Controller.writeToBinFile(file)));
        writeToTXTFileMenuItem.addActionListener(new writeActionListener(file -> Controller.writeToTxtFile(file)));


        this.setVisible(true);
    }

    private static String[][] getGroupsData(){
        String[][] result = new String[Data.groups.size()][2];
        int counter = 0;
        for(Group group : Data.groups){
            result[counter][0] = group.getNumberOfGroup();
            result[counter][1] = group.getFaculty();
            counter++;
        }
        return result;
    }
    private void refreshTable(){
        groupsTable.setModel(new DefaultTableModel(getGroupsData(), headers));
    }
    private void showErrorMessage(String errorStr, int width){
        JDialog error = new JDialog();
        error.setName("ERROR");
        error.setBounds(300, 300, width, 100);
        error.setResizable(false);
        Container container = error.getContentPane();
        container.setLayout(new BorderLayout(2, 2));
        container.add(new JLabel(errorStr),BorderLayout.CENTER);
        JButton okButton = new JButton("OK");
        okButton.setMaximumSize(new Dimension(30, 30));
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                error.dispose();
            }
        });
        container.add(okButton, BorderLayout.SOUTH);
        error.setVisible(true);
    }

}
