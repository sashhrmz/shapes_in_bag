import java.awt.*;
import java.awt.Rectangle;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;

public class Main {
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JPanel controlPanel;
    private JPanel listPanel;
    private Bag<Shape> bag = new Bag<Shape>(100);
    private JList bagList;

    public Main(){
        prepareGUI();
    }

    public static void main(String[] args){
        Main  swingListenerDemo = new Main();
        swingListenerDemo.showActionListenerDemo();
    }

    private void prepareGUI(){
        mainFrame = new JFrame("Bag");

        mainFrame.setSize(480, 500);
        mainFrame.setLayout(new BorderLayout());
        headerLabel = new JLabel("",JLabel.CENTER );

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.PAGE_AXIS));
        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.PAGE_AXIS));

        mainFrame.add(headerLabel, BorderLayout.PAGE_START);
        mainFrame.add(controlPanel,  BorderLayout.PAGE_END);
        mainFrame.add(listPanel, BorderLayout.CENTER);
        mainFrame.getContentPane().setBackground(Color.PINK);
        mainFrame.setVisible(true);
    }

    private void showActionListenerDemo(){
        headerLabel.setText("Free volume of your bag equals 100");
        headerLabel.setFont(new Font("Cabin-Italic", Font.BOLD, 20));
        JPanel panelToChangeVolumeButton = new JPanel();
        JButton changeVolumeButton = new JButton("change the volume");

        JPanel panelToBottonAdd = new JPanel();
        JButton addButton = new JButton("add shape");
        JPanel panelToBottonDelete = new JPanel();
        JButton deleteButton = new JButton("delete shape");

        bagList = new JList();
        bagList.setFont(new Font("Cabin-Italic", Font.BOLD, 16));

        JScrollPane scrollPane = new JScrollPane(bagList);
        scrollPane.setBackground(Color.BLACK);
        bagList.setForeground(Color.PINK);
        listPanel.add(scrollPane);

        changeVolumeButton.addActionListener(new CustomActionListener());
        changeVolumeButton.setBackground(Color.BLACK);
        changeVolumeButton.setForeground(Color.PINK);
        panelToChangeVolumeButton.setBackground(Color.PINK);
        panelToChangeVolumeButton.add(changeVolumeButton);
        controlPanel.add(panelToChangeVolumeButton);


        addButton.addActionListener(new CustomActionListener());
        addButton.setBackground(Color.PINK);
        addButton.setForeground(Color.BLACK);
        panelToBottonAdd.setBackground(Color.BLACK);
        panelToBottonAdd.add(addButton);
        controlPanel.add(panelToBottonAdd);

        deleteButton.addActionListener(new CustomActionListener());
        deleteButton.setBackground(Color.PINK);
        deleteButton.setForeground(Color.BLACK);
        panelToBottonDelete.setBackground(Color.BLACK);
        panelToBottonDelete.add(deleteButton);
        controlPanel.add(panelToBottonDelete);

        bagList.setBackground(new Color(0, 0, 0, 0));
        listPanel.setBackground(Color.PINK);

        mainFrame.setVisible(true);
    }

    class CustomActionListener implements ActionListener{
        public void actionPerformed(ActionEvent event) {
            String command = event.getActionCommand();
            UIManager.put("OptionPane.background", Color.PINK);
            UIManager.put("Panel.background", Color.PINK);
            UIManager.put("Button.background", Color.PINK);
            if(command.equals("add shape")) {
                Icon icon = null;
                Object[] possibilities = {"Ball", "Cube", "Cylinder"};
                String s = (String)JOptionPane.showInputDialog(
                        mainFrame,
                        "What shape do you want to add?",
                        "Choosing the shape",
                        JOptionPane.QUESTION_MESSAGE, icon,
                        possibilities,
                        "Ball");
                Shape shape;
               if(s.equals("Ball")) {
                   UIManager.put("OptionPane.background", Color.PINK);
                   UIManager.put("Panel.background", Color.PINK);
                   UIManager.put("Button.background", Color.PINK);
                   String number = (String)JOptionPane.showInputDialog(
                               "Type the radius of the ball u want to add.");
                   if(number.equals("")) return;
                   double radius = Double.parseDouble(number);
                       shape = new Ball(radius);
                   } else if(s.equals("Cube")) {
                   String number = (String)JOptionPane.showInputDialog(
                               "Type the edge length of the cube u want to add.");
                       if(number.equals("")) return;
                       double side = Double.parseDouble(number);
                       shape = new Cube(side);
                   } else {
                   String number1 = (String)JOptionPane.showInputDialog(
                           "Type the radius of the cylinder u want to add.");
                   if(number1.equals("")) return;

                   String number2 = (String)JOptionPane.showInputDialog(
                           "Type high of the cylinder u want to add.");
                   if(number2.equals("")) return;
                       double radius = Double.parseDouble(number1);
                       double high = Double.parseDouble(number2);
                       shape = new Cylinder(radius, high);
                   }
               try {
                bag.add(shape);
               } catch (MyException exception) {
                   UIManager.put("OptionPane.background", Color.PINK.darker());
                   UIManager.put("Panel.background", Color.PINK.darker());
                   UIManager.put("Button.background", Color.PINK.darker());
                   JOptionPane.showMessageDialog(mainFrame, exception.getMessage(),
                           "EXCEPTION", JOptionPane.ERROR_MESSAGE);
               return;
               }
               RedrawBagList();
            }
            else if(command.equals("delete shape")) {
                Integer index = Integer.parseInt((String)JOptionPane.showInputDialog(
                        "Enter the number of the shape you want to delete.")) - 1;
                if(index >= bag.getList().size()) {
                    UIManager.put("OptionPane.background", Color.PINK.darker());
                    UIManager.put("Panel.background", Color.PINK.darker());
                    UIManager.put("Button.background", Color.PINK.darker());
                    JOptionPane.showMessageDialog(mainFrame, "There is no figure with such number",
                            "EXCEPTION", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                bag.getList().removeElementAt(index);
                RedrawBagList();
            } else if(command.equals("change the volume")) {
                double volume = Double.parseDouble((String)JOptionPane.showInputDialog(
                        "Enter new volume of the bag"));
                try {
                    bag.setVolume(volume);
                } catch(MyException exception) {
                    UIManager.put("OptionPane.background", Color.PINK.darker());
                    UIManager.put("Panel.background", Color.PINK.darker());
                    UIManager.put("Button.background", Color.PINK.darker());
                    JOptionPane.showMessageDialog(mainFrame, exception.getMessage(),
                            "EXCEPTION", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            listPanel.repaint();
        }
    }

    private void RedrawBagList() {
        headerLabel.setText("Free volume of your bag equals " + Double.toString(bag.getFreeVolume()));
        Vector<String> vectorToList = new Vector<String>();
        for(int i = 0; i < bag.getList().size(); ++i) {
            if(bag.getList().elementAt(i) instanceof Ball) {
                vectorToList.add(Integer.toString(i + 1) + ". " + "The ball with volume "
                        + Double.toString(bag.getList().elementAt(i).getVolume()));
            } else if(bag.getList().elementAt(i) instanceof Cube) {
                vectorToList.add(Integer.toString(i + 1) + ". " + "The cube with volume "
                        + Double.toString(bag.getList().elementAt(i).getVolume()));
            } else if(bag.getList().elementAt(i) instanceof Cylinder) {
                vectorToList.add(Integer.toString(i + 1) + ". " + "The cylinder with volume "
                        + Double.toString(bag.getList().elementAt(i).getVolume()));
            }
        }

        bagList.setListData(vectorToList);
    }
}
