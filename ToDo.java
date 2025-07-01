import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ToDo {
    public static void main(String[] args) {

        JFrame frame = new JFrame("To-Do App ");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(245, 245, 245));

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBounds(0, 0, 400, 500);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JButton addBtn = new JButton("Add Task");
        JButton showBtn = new JButton("Show Tasks");

        ArrayList<JLabel> taskLabels = new ArrayList<>();
        ArrayList<JTextField> taskFields = new ArrayList<>();
        ArrayList<JButton> deleteButtons = new ArrayList<>();
        ArrayList<JLabel> resultLabels = new ArrayList<>();

        final int btnY = 20;
        final int taskStartY = 80;
        final int gap = 100;
        final int resultGap = 30;
        final int[] yPos = {taskStartY};

        addBtn.setBounds(50, btnY, 120, 30);
        styleButton(addBtn);

        showBtn.setBounds(200, btnY, 120, 30);
        styleButton(showBtn);

        mainPanel.setPreferredSize(new Dimension(380, 1000));
        mainPanel.add(addBtn);
        mainPanel.add(showBtn);

        // Action: Add New Task
        addBtn.addActionListener(e -> {
            int taskCount = taskFields.size() + 1;

            JLabel newLabel = new JLabel("Task " + taskCount + ":");
            JTextField newField = new JTextField();
            JButton delBtn = new JButton("🗑 Delete");

            newLabel.setBounds(30, yPos[0], 200, 30);
            newLabel.setFont(new Font("Arial", Font.BOLD, 12));

            newField.setBounds(30, yPos[0] + 35, 300, 30);
            newField.setBorder(BorderFactory.createLineBorder(new Color(120, 144, 156)));

            delBtn.setBounds(30, yPos[0] + 70, 100, 25);
            styleButton(delBtn);

            mainPanel.add(newLabel);
            mainPanel.add(newField);
            mainPanel.add(delBtn);
            mainPanel.revalidate();
            mainPanel.repaint();

            taskLabels.add(newLabel);
            taskFields.add(newField);
            deleteButtons.add(delBtn);

            yPos[0] += gap;
            mainPanel.setPreferredSize(new Dimension(380, yPos[0] + 100));

            delBtn.addActionListener(ev -> {
                int index = taskLabels.indexOf(newLabel);

                mainPanel.remove(taskLabels.get(index));
                mainPanel.remove(taskFields.get(index));
                mainPanel.remove(deleteButtons.get(index));

                taskLabels.remove(index);
                taskFields.remove(index);
                deleteButtons.remove(index);

                for (JLabel lbl : resultLabels) {
                    mainPanel.remove(lbl);
                }
                resultLabels.clear();


                for (int i = 0; i < taskLabels.size(); i++) {
                    int newY = taskStartY + i * gap;
                    taskLabels.get(i).setText("Task " + (i + 1) + ":");
                    taskLabels.get(i).setBounds(30, newY, 200, 30);
                    taskFields.get(i).setBounds(30, newY + 35, 300, 30);
                    deleteButtons.get(i).setBounds(30, newY + 70, 100, 25);
                }

                yPos[0] = taskStartY + taskLabels.size() * gap;
                mainPanel.setPreferredSize(new Dimension(380, yPos[0] + 100));
                mainPanel.repaint();
            });
        });


        showBtn.addActionListener(e -> {
            for (JLabel lbl : resultLabels) {
                mainPanel.remove(lbl);
            }
            resultLabels.clear();

            int resultY = yPos[0] + 10;

            for (int i = 0; i < taskFields.size(); i++) {
                String taskText = taskFields.get(i).getText().trim();
                if (!taskText.isEmpty()) {
                    JLabel resLabel = new JLabel("Task " + (i + 1) + ": " + taskText);
                    resLabel.setBounds(30, resultY, 300, 25);
                    resLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                    resLabel.setForeground(new Color(33, 33, 33));
                    mainPanel.add(resLabel);
                    resultLabels.add(resLabel);
                    resultY += resultGap;
                }
            }

            if (resultLabels.isEmpty()) {
                JLabel emptyLabel = new JLabel("No tasks filled yet.");
                emptyLabel.setBounds(30, resultY, 200, 25);
                mainPanel.add(emptyLabel);
                resultLabels.add(emptyLabel);
                resultY += resultGap;
            }

            yPos[0] = resultY + 20;
            mainPanel.setPreferredSize(new Dimension(380, yPos[0] + 100));
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        frame.setLayout(null);
        frame.setSize(400, 500);
        frame.add(scrollPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


    private static void styleButton(JButton btn) {
        btn.setBackground(new Color(76, 175, 80));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
