//UPDATED LATEST 214AM
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class Test extends JFrame {

    private JLabel title;
    private JTable carParkTable;
    private DefaultTableModel carParkModel;
    private JTextField searchField;

    public Test() {   
        JLabel title = new JLabel("HDB Park Kings");
        title.setForeground(new Color(254,253,237));
        title.setBackground(new Color(45,45,39));
        title.setOpaque(true);
        title.setFont(new Font("Courier", Font.PLAIN, 20));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title, BorderLayout.NORTH);
    
        setTitle("Car Park Management System");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        String[] columnNames = {"Carpark Name", "Available Places", "Maintenance Progress", "Time to Completion(Days)"};
        carParkModel = new DefaultTableModel(columnNames, 0);
        carParkTable = new JTable(carParkModel);
        
        // carParkTable.setBackground();
        carParkTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JTable target = (JTable) e.getSource();
                int row = target.getSelectedRow();
                                ImageIcon image = new ImageIcon("./empty.png");
                switch (row) {
                    case 0: image = new ImageIcon("./26JM.png"); break;
                    case 1: image = new ImageIcon("./126BM.png"); break;
                    case 2: image = new ImageIcon("./6AE.png"); break;
                }
                Dimension screenSize2 = Toolkit.getDefaultToolkit().getScreenSize();
                int width2 = (int) (screenSize2.getWidth() * 0.25);
                int height2 = (int) (screenSize2.getHeight() * 0.25);

                Image im = image.getImage().getScaledInstance(width2, height2, Image.SCALE_SMOOTH);

                JLabel imageLabel = new JLabel(new ImageIcon(im));
                if(imageLabel!=null) remove(imageLabel);
                add(imageLabel, BorderLayout.EAST);
                revalidate();
                repaint();
            }
        });
        
        

        add(new JScrollPane(carParkTable), BorderLayout.CENTER);
        carParkModel.addRow(new Object[]{"Block 26 Jalan Membina MSCP", 50, "In Progress", 3});
        carParkModel.addRow(new Object[]{"Block 126A Bukit Merah MSCP", 100, "Completed", 0});
        carParkModel.addRow(new Object[]{"Block 6A Everton Park MSCP", 75, "Not Started", 7});
        carParkModel.addRow(new Object[]{"29 Toa Payoh East HDB", 50, "Completed", 0});
        carParkModel.addRow(new Object[]{"78B Toa Payoh Central TPMN", 100, "Completed", 0});
        carParkModel.addRow(new Object[]{"261A Toa Payoh TPMR", 75, "Completed", 0});
        carParkModel.addRow(new Object[]{"116A Bedok North", 50, "In Progress", 1});
        carParkModel.addRow(new Object[]{"Block 126A Bukit Merah MSCP", 100, "Completed", 0});
        carParkModel.addRow(new Object[]{"Block 94A Bedok North MSCP", 75, "Completed", 0});
        carParkModel.addRow(new Object[]{"98/100 Bedok North Ave 4", 50, "Completed", 0});
        carParkModel.addRow(new Object[]{"21A Chai Chee RD B83", 100, "In Progress", 5});
        carParkModel.addRow(new Object[]{"900 Serangoon Rd KB12", 75, "Completed", 0});
        carParkModel.addRow(new Object[]{"Block 28A Multi Storey Carpark", 50, "Completed", 0});
        carParkModel.addRow(new Object[]{"429A CCK Ave 4 CKM1", 100, "Not Started", 7});
        carParkModel.addRow(new Object[]{"430 Clementi Ave 3 C16", 75, "Completed", 0});
        carParkModel.addRow(new Object[]{"812 Jurong West St 81 J71", 50, "Completed", 0});
        carParkModel.addRow(new Object[]{"528 Jurong West St 52 J29", 100, "Completed", 0});
        carParkModel.addRow(new Object[]{"508 Jurong West St 51 J35", 75, "In Progress", 5});
        carParkModel.addRow(new Object[]{"29A Marsiling MSCP", 50, "Completed", 0});
        carParkModel.addRow(new Object[]{"2A Marsiling W8M", 100, "In Progress", 5});
        carParkModel.addRow(new Object[]{"747 Yishun St 72 Y9", 75, "Completed", 0});
        carParkModel.addRow(new Object[]{"935 Yishun Central 1 Y56", 50, "Completed", 0});
        carParkModel.addRow(new Object[]{"925 HDB Yishun MSCP", 100, "Completed", 0});
        carParkModel.addRow(new Object[]{"347 Yishun Ave 11", 75, "Completed", 0});
        carParkModel.addRow(new Object[]{"Yishun Ave 4 MSCP", 50, "Completed", 0});
        carParkModel.addRow(new Object[]{"504 Yishun St 51 Y68M", 100, "Completed", 0});
        carParkModel.addRow(new Object[]{"506 Yishun Ave 4 Y70M", 75, "In Progress", 5});
        carParkModel.addRow(new Object[]{"306 Anchorvale Link SK40", 50, "Completed", 0});
        carParkModel.addRow(new Object[]{"Sengkang E Rd 200 SK32", 100, "Not Started", 7});
        carParkModel.addRow(new Object[]{"281 Sengkang East Ave MSCP", 75, "Completed", 0});
        carParkModel.addRow(new Object[]{"273 Punggol Place Carpark PL41", 50, "Completed", 0});
        carParkModel.addRow(new Object[]{"168 Punggol Field PL15", 100, "Completed", 0});
        carParkModel.addRow(new Object[]{"256 Sumang Walk PL62", 75, "Completed", 0});
        carParkModel.addRow(new Object[]{"54 Chin Swee MSCP", 50, "Completed", 0});
        carParkModel.addRow(new Object[]{"6 Upper Circular Rd C0133", 100, "Completed", 0});
        carParkModel.addRow(new Object[]{"502 Jurong West St 51 J35", 75, "Completed", 0});


        JPanel westPanel = new JPanel(new GridLayout(0,1));
        westPanel.setBackground(new Color(45, 45, 39));
        add(westPanel, BorderLayout.WEST);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.getWidth() * 0.0105);
        int height = (int) (screenSize.getHeight() * 0.015);

        JButton addButton = new JButton();
        ImageIcon addIcon = new ImageIcon("./add.png");
        Image addImage = addIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        addButton.setIcon(new ImageIcon(addImage));
        addButton.setToolTipText("Add");
        addButton.addActionListener(new AddButtonListener());
        westPanel.add(addButton);

        JButton removeButton = new JButton();
        ImageIcon removeIcon = new ImageIcon("./delete.png");
        Image removeImage = removeIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        removeButton.setIcon(new ImageIcon(removeImage));
        removeButton.setToolTipText("Remove");
        removeButton.addActionListener(new RemoveButtonListener());
        westPanel.add(removeButton);

        JButton editButton = new JButton();
        ImageIcon editIcon = new ImageIcon("./edit.png");
        Image editImage = editIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        editButton.setIcon(new ImageIcon(editImage));     
        editButton.setToolTipText("Edit");
        editButton.addActionListener(new EditButtonListener());
        westPanel.add(editButton);

        JButton searchButton = new JButton();
        ImageIcon searchIcon = new ImageIcon("./search.png");
        Image searchImage = searchIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        searchButton.setIcon(new ImageIcon(searchImage));   
        searchButton.setToolTipText("Search");
        searchButton.addActionListener(new SearchButtonListener());
        westPanel.add(searchButton);
        
    }

    private class AddButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JTextField nameField = new JTextField();
            JTextField placesField = new JTextField();
            JTextField progressField = new JTextField();
            JTextField crimeField = new JTextField();
            Object[] message = {
                "Name:", nameField,
                "Available Places:", placesField,
                "Maintenance Progress:", progressField,
                "Time to Completion(Days):", crimeField
            };
            int option = JOptionPane.showConfirmDialog(null, message, "Add Car Park", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                // Add the new row to the table
                carParkModel.addRow(new Object[]{nameField.getText(), placesField.getText(), progressField.getText(), crimeField.getText()});
            }
        }
    }

    private class RemoveButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Remove the selected row from the table
            int selectedRow = carParkTable.getSelectedRow();
            if (selectedRow != -1) {
                carParkModel.removeRow(selectedRow);
            }
        }
    }

    private class EditButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int selectedRow = carParkTable.getSelectedRow();
            if (selectedRow != -1) {
                JTextField nameField = new JTextField((String) carParkTable.getValueAt(selectedRow, 0));
                JTextField placesField = new JTextField((String) carParkTable.getValueAt(selectedRow, 1));
                JTextField progressField = new JTextField((String) carParkTable.getValueAt(selectedRow, 2));
                JTextField crimeField = new JTextField((String) carParkTable.getValueAt(selectedRow, 3));
                Object[] message = {
                    "Name:", nameField,
                    "Available Places:", placesField,
                    "Maintenance Progress:", progressField,
                    "Time to Completion(Days):", crimeField
                };
                int option = JOptionPane.showConfirmDialog(null, message, "Edit Car Park", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    carParkTable.setValueAt(nameField.getText(), selectedRow, 0);
                    carParkTable.setValueAt(placesField.getText(), selectedRow, 1);
                    carParkTable.setValueAt(progressField.getText(), selectedRow, 2);
                    carParkTable.setValueAt(crimeField.getText(), selectedRow, 3);
                }
            }
        }
    }

    private class SearchButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JTextField searchField = new JTextField(1);
            Object[] message = {"Search:", searchField};
            int option = JOptionPane.showConfirmDialog(null, message, "Search", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                String searchText = searchField.getText().toLowerCase();
                TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(carParkModel);
                carParkTable.setRowSorter(sorter);
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
                searchField.selectAll();
            }
        }
    }

public static void main(String[] args) {
        Test app = new Test();
        app.setVisible(true);
    }
}

        
    

