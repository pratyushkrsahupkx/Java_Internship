package Task6;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ToDoSwing extends JFrame implements ActionListener {
    private JTextField taskField;
    private JButton addButton;
    private JButton deleteButton;
    private JList<String> taskList;
    private DefaultListModel<String> listModel;
    private JScrollPane scrollPane;
    private ArrayList<String> tasks;
    public ToDoSwing() {
        initializeComponents();
        setupLayout();
        addEventListeners();
        setWindowProperties();
    }

    private void initializeComponents() {
        tasks = new ArrayList<>();
        taskField = new JTextField(20);
        taskField.setFont(new Font("Arial", Font.PLAIN, 14));
        addButton = new JButton("Add Task");
        addButton.setBackground(new Color(76, 175, 80));
        addButton.setForeground(Color.WHITE);
        addButton.setFont(new Font("Arial", Font.BOLD, 12));
        addButton.setFocusPainted(false);
        deleteButton = new JButton("Delete Task");
        deleteButton.setBackground(new Color(244, 67, 54));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFont(new Font("Arial", Font.BOLD, 12));
        deleteButton.setFocusPainted(false);
        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        taskList.setFont(new Font("Arial", Font.PLAIN, 14));        
        scrollPane = new JScrollPane(taskList);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        scrollPane.setBorder(BorderFactory.createTitledBorder("Tasks"));
    }

    private void setupLayout() {
        setLayout(new BorderLayout());       
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        topPanel.add(new JLabel("Enter Task: "));
        topPanel.add(taskField);
        topPanel.add(addButton);
        topPanel.add(deleteButton);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(new EmptyBorder(0, 10, 10, 10));
        centerPanel.add(scrollPane, BorderLayout.CENTER);        
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
    }

    private void addEventListeners() {
        addButton.addActionListener(this);
        deleteButton.addActionListener(this);        
        taskField.addActionListener(this);
    }

    private void setWindowProperties() {
        setTitle("ToDo App - Java Swing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        pack();
        setLocationRelativeTo(null);     
        setMinimumSize(new Dimension(500, 400));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton || e.getSource() == taskField) {
            addTask();
        } else if (e.getSource() == deleteButton) {
            deleteTask();
        }
    }

    private void addTask() {
        String task = taskField.getText().trim();
        if (!task.isEmpty()) {
            tasks.add(task);
            listModel.addElement(task);
            taskField.setText("");
            taskField.requestFocus();
            JOptionPane.showMessageDialog(this, 
                "Task added successfully!", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
        } else {            
            JOptionPane.showMessageDialog(this, 
                "Please enter a task!", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    
    private void deleteTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            tasks.remove(selectedIndex);
            listModel.remove(selectedIndex);
            JOptionPane.showMessageDialog(this, 
                "Task deleted successfully!", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, 
                "Please select a task to delete!", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            new ToDoSwing().setVisible(true);
        });
    }
}