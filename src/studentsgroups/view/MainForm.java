package studentsgroups.view;

import studentsgroups.controller.Controller;
import studentsgroups.controller.utils.NotValidValueException;
import studentsgroups.model.Group;
import studentsgroups.model.Student;
import studentsgroups.model.impl.GroupImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.JAXBException;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Created by molish on 17.11.2016.
 */
public class MainForm extends JFrame {

    private static final Group FREE_GROUP = new GroupImpl(" ");

    private Controller controller;
    private Group currentGroup;

    //region переменные окна группы 12 left
    private String[] groupTableHeaders = {"название группы", "число cтудентов"};
    private String[] studentTableHeaders = {"ID", "Name", "Surname", "Patronymic", "Enrollment date"};

    private class ReaderActionListener implements ActionListener{

        private Reader reader;

        public ReaderActionListener(Reader reader){
            this.reader = reader;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    reader.read(fileChooser.getSelectedFile());
                }
            }catch (IOException | ClassNotFoundException | JAXBException ex){ex.printStackTrace();}
        }
    }
    private class WriterActionListener implements ActionListener{

        private Writer writer;

        public WriterActionListener(Writer writer){
            this.writer = writer;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    writer.write(fileChooser.getSelectedFile());
                }
            }catch (FileNotFoundException ex){ex.printStackTrace();}
            catch (IOException | JAXBException ex){ex.printStackTrace();}
        }
    }

    private JTable groupDataJtable;
    private JTable studentDataJTable;

    private JMenuBar mainMenuBar = new JMenuBar();
    private JMenu fileMenu = new JMenu("File");
    private JMenu serializationMenu = new JMenu("Serialization");
    private JMenu XMLMenu = new JMenu("XML");
    private JMenuItem serializeMenuItem = new JMenuItem("Serialize");
    private JMenuItem deserializeMenuItem = new JMenuItem("Deserialize");
    private JMenuItem readFromXMLMenuItem = new JMenuItem("ReadFromXML");
    private JMenuItem writeToXMLMenuItem = new JMenuItem("WriteToXML");
    private JMenuItem exitMenuItem = new JMenuItem("Exit");

    private JTextField groupSearchTextField = new JTextField(14);
    private JTextField studentSearchTextField = new JTextField(14);

    private JLabel searchGroupJLabel = new JLabel("Search group");
    private JLabel searchStudentJLabel = new JLabel("Search student");

    private JLabel groupNameJLabel;

    private JPopupMenu groupTablePopupMenu = new JPopupMenu();
    private JMenuItem groupAddMenuItem = new JMenuItem("Add group");
    private JMenuItem groupRemoveMenuItem = new JMenuItem("Remove group");
    private JMenuItem groupChangeMenuItem = new JMenuItem("Change data");

    private JPopupMenu studentTablePopupMenu = new JPopupMenu();
    private JMenuItem studentAddMenuItem = new JMenuItem("Add student");
    private JMenuItem studentRemoveMenuItem = new JMenuItem("Remove student");
    //private JMenuItem studentChangeMenuItem = new JMenuItem("Change data");

    //endregion

    public MainForm(Controller controller) {
        super("Group students");
        this.controller = controller;
        this.setBounds(100, 100, 750, 500);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        //this.setResizable(false);
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                try {
                    Controller.readFromFile("state.bin");
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void windowClosing(WindowEvent e) {
                //реализовать дял закрытия окна
                try {
                    controller.writeToFile("state.bin");
                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                    System.exit(0);
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        JFrame mainWindow = this;

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(1, 2, 2, 2));

        Group[] groups = controller.getGroups();
        if (groups.length > 0) {
            groupDataJtable = new MyTable(getGroupData(groups), groupTableHeaders, 0, 1);
            currentGroup = groups[0];
        } else {
            groupDataJtable = new MyTable(getGroupData(new Group[0]), groupTableHeaders, 0, 1);
            currentGroup = FREE_GROUP;
        }
        JScrollPane groupsScrollPane = new JScrollPane(groupDataJtable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        studentDataJTable = new MyTable(getStudentsdata(currentGroup.getStudents()), studentTableHeaders, 0);
        JScrollPane studentScrollPane = new JScrollPane(studentDataJTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        groupNameJLabel = new JLabel(currentGroup.getNumberOfGroup());
        JPanel studentsPanel = new JPanel(new BorderLayout(2, 2));
        studentsPanel.add(groupNameJLabel, BorderLayout.NORTH);
        studentsPanel.add(studentScrollPane, BorderLayout.CENTER);

        mainMenuBar.add(fileMenu);
        mainMenuBar.add(searchGroupJLabel);
        mainMenuBar.add(groupSearchTextField);
        mainMenuBar.add(searchStudentJLabel);
        mainMenuBar.add(studentSearchTextField);
        fileMenu.add(serializationMenu);
        fileMenu.add(XMLMenu);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.writeToFile("state.bin");
                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                    System.exit(0);
                }
            }
        });
        serializationMenu.add(serializeMenuItem);
        serializeMenuItem.addActionListener(new ReaderActionListener(file -> controller.readFile(file)));
        serializationMenu.add(deserializeMenuItem);
        deserializeMenuItem.addActionListener(new WriterActionListener(file -> controller.writeToFile(file)));
        XMLMenu.add(readFromXMLMenuItem);
        readFromXMLMenuItem.addActionListener(new ReaderActionListener(file -> controller.readXML(file)));
        XMLMenu.add(writeToXMLMenuItem);
        writeToXMLMenuItem.addActionListener(new WriterActionListener(file -> controller.writeToXML(file)));

        groupTablePopupMenu.add(groupAddMenuItem);
        groupAddMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.setEnabled(false);
                JDialog addGroupDialog = new JDialog();
                addGroupDialog.setTitle("Add group");
                addGroupDialog.setBounds(200, 200, 200, 100);
                addGroupDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
                addGroupDialog.addWindowListener(new WindowListener() {
                    @Override
                    public void windowOpened(WindowEvent e) {

                    }

                    @Override
                    public void windowClosing(WindowEvent e) {
                        mainWindow.setEnabled(true);
                        addGroupDialog.dispose();
                    }

                    @Override
                    public void windowClosed(WindowEvent e) {

                    }

                    @Override
                    public void windowIconified(WindowEvent e) {

                    }

                    @Override
                    public void windowDeiconified(WindowEvent e) {

                    }

                    @Override
                    public void windowActivated(WindowEvent e) {

                    }

                    @Override
                    public void windowDeactivated(WindowEvent e) {

                    }
                });
                Container addGroupContainer = addGroupDialog.getContentPane();
                JLabel groupNameLabel = new JLabel("NAme: ");
                JTextField groupNameTextField = new JTextField();
                JButton okButton = new JButton("OK");
                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            Group addingGroup = new GroupImpl(groupNameTextField.getText());
                            controller.addGroup(addingGroup);
                            refreshTableGroupsData(controller.getGroups());
                            currentGroup = addingGroup;
                            groupNameJLabel.setText(currentGroup.getNumberOfGroup());
                            refreshTableStudentData(currentGroup.getStudents());
                            int newROwCount = groupDataJtable.getRowCount() - 1;
                            groupDataJtable.setRowSelectionInterval(newROwCount, newROwCount);
                            mainWindow.setEnabled(true);
                            addGroupDialog.dispose();
                        } catch (NotValidValueException ex) {
                            ex.printStackTrace();
                            //TODO: сделать окно ошибки
                        }
                    }
                });
                JButton cancelButton = new JButton("Cancel");
                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mainWindow.setEnabled(true);
                        addGroupDialog.dispose();
                    }
                });
                JPanel addGroupPanel = new JPanel(new GridLayout(2, 2, 2, 2));
                addGroupPanel.add(groupNameLabel);
                addGroupPanel.add(groupNameTextField);
                addGroupPanel.add(okButton);
                addGroupPanel.add(cancelButton);
                addGroupContainer.add(addGroupPanel);
                addGroupDialog.setVisible(true);
            }
        });
        groupTablePopupMenu.add(groupRemoveMenuItem);
        groupRemoveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (groupDataJtable.getRowCount() != 0) {
                    int selectedRow = groupDataJtable.getSelectedRow();
                    Group deletingGroup = controller.getGroup(groupDataJtable.getValueAt(selectedRow, 0).toString());
                    controller.deleteGroup(deletingGroup);
                    refreshTableGroupsData(controller.getGroups());
                    if (selectedRow == groupDataJtable.getRowCount() && groupDataJtable.getRowCount() != 0)
                        groupDataJtable.setRowSelectionInterval(selectedRow - 1, selectedRow - 1);
                    else if (selectedRow < groupDataJtable.getRowCount())
                        groupDataJtable.setRowSelectionInterval(selectedRow, selectedRow);
                    if (groupDataJtable.getRowCount() == 0) {
                        currentGroup = FREE_GROUP;
                        groupNameJLabel.setText(FREE_GROUP.getNumberOfGroup());
                        refreshTableStudentData(currentGroup.getStudents());
                    } else {
                        currentGroup = controller.getGroup(groupDataJtable.getValueAt(groupDataJtable.getSelectedRow(), 0).toString());
                        groupNameJLabel.setText(FREE_GROUP.getNumberOfGroup());
                        refreshTableStudentData(currentGroup.getStudents());
                    }
                }
            }
        });
        groupTablePopupMenu.add(groupChangeMenuItem);
        groupChangeMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(groupDataJtable.getRowCount() > 0) {
                    mainWindow.setEnabled(false);
                    JDialog changeGroupDialog = new JDialog();
                    changeGroupDialog.setTitle("Change group");
                    changeGroupDialog.setBounds(200, 200, 200, 100);
                    changeGroupDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
                    changeGroupDialog.addWindowListener(new WindowListener() {
                        @Override
                        public void windowOpened(WindowEvent e) {

                        }

                        @Override
                        public void windowClosing(WindowEvent e) {
                            mainWindow.setEnabled(true);
                            changeGroupDialog.dispose();
                        }

                        @Override
                        public void windowClosed(WindowEvent e) {

                        }

                        @Override
                        public void windowIconified(WindowEvent e) {

                        }

                        @Override
                        public void windowDeiconified(WindowEvent e) {

                        }

                        @Override
                        public void windowActivated(WindowEvent e) {

                        }

                        @Override
                        public void windowDeactivated(WindowEvent e) {

                        }
                    });
                    Container addGroupContainer = changeGroupDialog.getContentPane();
                    JLabel groupNameLabel = new JLabel("Name: ");
                    String oldGroupName = groupDataJtable.getValueAt(groupDataJtable.getSelectedRow(), 0).toString();
                    JTextField groupNameTextField = new JTextField();
                    groupNameTextField.setText(oldGroupName);
                    JButton okButton = new JButton("OK");
                    okButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                String newGroupName = groupNameTextField.getText();
                                if( !oldGroupName.equals(newGroupName) && controller.setGroupName(oldGroupName, newGroupName)) {
                                    groupDataJtable.setValueAt(newGroupName, groupDataJtable.getSelectedRow(), 0);
                                    groupNameJLabel.setText(newGroupName);
                                }
                                mainWindow.setEnabled(true);
                                changeGroupDialog.dispose();
                            } catch (NotValidValueException ex) {
                                ex.printStackTrace();
                                //TODO: сделать окно ошибки
                            }
                        }
                    });
                    JButton cancelButton = new JButton("Cancel");
                    cancelButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            mainWindow.setEnabled(true);
                            changeGroupDialog.dispose();
                        }
                    });
                    JPanel addGroupPanel = new JPanel(new GridLayout(2, 2, 2, 2));
                    addGroupPanel.add(groupNameLabel);
                    addGroupPanel.add(groupNameTextField);
                    addGroupPanel.add(okButton);
                    addGroupPanel.add(cancelButton);
                    addGroupContainer.add(addGroupPanel);
                    changeGroupDialog.setVisible(true);
                }
            }
        });


        studentTablePopupMenu.add(studentAddMenuItem);
        studentTablePopupMenu.add(studentRemoveMenuItem);
        //groupTablePopupMenu.add(studentChangeMenuItem);

        groupsScrollPane.setComponentPopupMenu(groupTablePopupMenu);
        groupDataJtable.setComponentPopupMenu(groupTablePopupMenu);
        //groupDataJtable.setRowSelectionInterval(0, 0);
        groupDataJtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        groupDataJtable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    currentGroup = controller.getGroup(groupDataJtable.getValueAt(groupDataJtable.getSelectedRow(), 0).toString());
                    groupNameJLabel.setText(currentGroup.getNumberOfGroup());
                    refreshTableStudentData(currentGroup.getStudents());
                }
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
        });
        groupDataJtable.getTableHeader().setReorderingAllowed(false);

        studentScrollPane.setComponentPopupMenu(studentTablePopupMenu);
        studentDataJTable.setComponentPopupMenu(studentTablePopupMenu);
        studentDataJTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        studentDataJTable.getTableHeader().setReorderingAllowed(false);

        container.add(groupsScrollPane);
        container.add(studentsPanel);
        this.setJMenuBar(mainMenuBar);
        this.pack();
        this.setVisible(true);
        if (currentGroup != FREE_GROUP) {
            groupDataJtable.setRowSelectionInterval(0, 0);
            if(currentGroup.getSizeOfGroup() != 0)
                studentDataJTable.setRowSelectionInterval(0,0);
        }
    }
    private String[][] getGroupData(Group[] groups){
        String[][] result = new String[groups.length][2];
        int counter = 0;
        for(Group group : groups){
            result[counter][0] = group.getNumberOfGroup();
            result[counter][1] = Integer.toString(group.getSizeOfGroup());
            counter++;
        }
        return result;
    }

    private void refreshTableGroupsData(Group[] groups){
        groupDataJtable.setModel(new DefaultTableModel(getGroupData(groups), groupTableHeaders));
    }

    private String[][] getStudentsdata(Student[] students){
        String[][] result = new String[students.length][5];
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
        int counter = 0;
        for(Student student : students){
            result[counter][0] = Integer.toString(student.getIdStudent());
            result[counter][1] = student.getName();
            result[counter][2] = student.getSurname();
            result[counter][3] = student.getPatronymic();
            result[counter][4] = sdf.format(student.getEnrollmentDate());
            counter++;
        }
        return result;
    }

    private void refreshTableStudentData(Student[] students){
        studentDataJTable.setModel(new DefaultTableModel(getStudentsdata(currentGroup.getStudents()), studentTableHeaders));
        if(studentDataJTable.getRowCount() != 0)
            studentDataJTable.setRowSelectionInterval(0,0);
    }

}
