import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class Test extends JFrame {

    private JTable carParkTable;
    private DefaultTableModel carParkModel;
    private JTextField searchField;

    public Test() {
        // Set up the frame
        setTitle("Car Park Management System");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Set up the table
        String[] columnNames = {"Carpark Name", "Available Places", "Maintenance Progress", "Crime Sightings"};
        carParkModel = new DefaultTableModel(columnNames, 0);
        carParkTable = new JTable(carParkModel);
        add(new JScrollPane(carParkTable), BorderLayout.CENTER);

        // Add some example data to the table
        carParkModel.addRow(new Object[]{"Block 26 Jalan Membina MSCP", 50, "In Progress", 2});
        carParkModel.addRow(new Object[]{"Block 126A Bukit Merah MSCP", 100, "Completed", 0});
        carParkModel.addRow(new Object[]{"Block 6A Everton Park MSCP", 75, "Not Started", 1});

        // Add buttons to add, remove, and edit rows
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new AddButtonListener());
        buttonPanel.add(addButton);
        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(new RemoveButtonListener());
        buttonPanel.add(removeButton);
        JButton editButton = new JButton("Edit");
        editButton.addActionListener(new EditButtonListener());
        buttonPanel.add(editButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add a search field to filter the data
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new SearchButtonListener());
        buttonPanel.add(searchButton);
        
    }

    private class AddButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Show a dialog to get the data for the new row
            JTextField nameField = new JTextField();
            JTextField placesField = new JTextField();
            JTextField progressField = new JTextField();
            JTextField crimeField = new JTextField();
            Object[] message = {
                "Name:", nameField,
                "Available Places:", placesField,
                "Maintenance Progress:", progressField,
                "Crime Sightings:", crimeField
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
            // Show a dialog to get the new data for the selected row
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
                    "Crime Sightings:", crimeField
                };
                int option = JOptionPane.showConfirmDialog(null, message, "Edit Car Park", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    // Update the selected row with the new data
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
            // Show a dialog to get the search text
            JTextField searchField = new JTextField(1);
            Object[] message = {"Search:", searchField};
            int option = JOptionPane.showConfirmDialog(null, message, "Search", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                // Filter the table based on the text in the search field
                String searchText = searchField.getText().toLowerCase();
                TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(carParkModel);
                carParkTable.setRowSorter(sorter);
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
                searchField.selectAll();
            }

        }
    }

public static void main(String[] args) {
        // Show the frame
        Test app = new Test();
        app.setVisible(true);
    }
}

        
    

