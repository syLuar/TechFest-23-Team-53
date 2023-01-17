import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

import java.io.FileWriter;
import java.io.IOException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;  
// import java.io.IOException;
import java.nio.file.Files;

public class Test2 extends JFrame {

    private JTable carParkTable;
    private DefaultTableModel carParkModel;
    private JTextField searchField;

    public Test2() {
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
        //carParkModel.addRow(new Object[]{"Block 26 Jalan Membina MSCP", 50, "In Progress", 2});
        //carParkModel.addRow(new Object[]{"Block 126A Bukit Merah MSCP", 100, "Completed", 0});
        //carParkModel.addRow(new Object[]{"Block 6A Everton Park MSCP", 75, "Not Started", 1});
        String line = "";  
        //String splitBy = ",";  
        try   
        {  
            //parsing a CSV file into BufferedReader class constructor  
            BufferedReader br = new BufferedReader(new FileReader("Carpark_data.csv"));  
            while ((line = br.readLine()) != null)   //returns a Boolean value  
        {  
            String[] rowS = line.split(", ");    // use comma as separator  
            carParkModel.addRow(new Object[]{rowS[0], rowS[1], rowS[2], rowS[3]});
        }  
        br.close();
        }   
        catch (IOException e)   
        {  
        e.printStackTrace();  
        }  

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
            try {
                FileWriter fw = new FileWriter("Carpark_data.csv",true);
                fw.write(nameField.getText() + ", " + placesField.getText() + ", " + progressField.getText() + ", " + crimeField.getText() + "\n");
                fw.close();
                }
            catch (Exception f){
                f.getStackTrace();
            }
                //catch (Exception e) {
                //    e.getStackTrace();
                //}
        }
    }

    private class RemoveButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Remove the selected row from the table
            int selectedRow = carParkTable.getSelectedRow();
            //System.out.println(carParkTable.getValueAt(selectedRow, 0));
            String remove = carParkTable.getValueAt(selectedRow, 0).toString();
            if (selectedRow != -1) {
                carParkModel.removeRow(selectedRow);
            }
            try   
            {  
            String line = "";
                //parsing a CSV file into BufferedReader class constructor  
                BufferedReader br = new BufferedReader(new FileReader("Carpark_data.csv"));  
                while ((line = br.readLine()) != null)   //returns a Boolean value  
            {  
                String[] rowS = line.split(", ");    // use comma as separator  
                //carParkModel.addRow(new Object[]{rowS[0], rowS[1], rowS[2], rowS[3]});
                if (rowS[0].matches(remove)) {
                    continue;
                }
                    try {
                        FileWriter fw = new FileWriter("Carpark_data2.csv",true);
                        fw.write(rowS[0] + ", " + rowS[1] + ", " + rowS[2] + ", " + rowS[3] + "\n");
                        fw.close();
                    }
                    catch (Exception f){
                        f.getStackTrace();
                    }
                        //catch (Exception e) {
                        //    e.getStackTrace();
                        //}
            } 
            br.close();
            }   
            catch (IOException m)   
            {  
            m.printStackTrace();  
            }
            String fileNameold = "Carpark_data2.csv";
            String fileNamenew = "Carpark_data.csv";
            try {
                Files.move(new File(fileNameold).toPath(), new File(fileNamenew).toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                //Logger.getLogger(SomeClass.class.getName()).log(Level.SEVERE, null, ex);
                ex.getStackTrace();
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
                String remove = carParkTable.getValueAt(selectedRow, 0).toString();
                int option = JOptionPane.showConfirmDialog(null, message, "Edit Car Park", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    // Update the selected row with the new data
                    carParkTable.setValueAt(nameField.getText(), selectedRow, 0);
                    carParkTable.setValueAt(placesField.getText(), selectedRow, 1);
                    carParkTable.setValueAt(progressField.getText(), selectedRow, 2);
                    carParkTable.setValueAt(crimeField.getText(), selectedRow, 3);
                }
                try   
                {  
                String line = "";
                    //parsing a CSV file into BufferedReader class constructor  
                    BufferedReader br = new BufferedReader(new FileReader("Carpark_data.csv"));  
                    while ((line = br.readLine()) != null)   //returns a Boolean value  
                {  
                    String[] rowS = line.split(", ");    // use comma as separator  
                    //carParkModel.addRow(new Object[]{rowS[0], rowS[1], rowS[2], rowS[3]});
                    if (rowS[0].matches(remove)) {
                        try {
                            FileWriter fw = new FileWriter("Carpark_data2.csv",true);
                            fw.write(nameField.getText() + ", " + placesField.getText() + ", " + progressField.getText() + ", " + crimeField.getText() + "\n");
                            // fw.write("\n");
                            fw.close();
                            }
                        catch (Exception f){
                            f.getStackTrace();
                        }
                            //catch (Exception e) {
                            //    e.getStackTrace();
                            //}
                        //continue;
                    }
                    else    try {
                            FileWriter fw = new FileWriter("Carpark_data2.csv",true);
                            fw.write(rowS[0] + ", " + rowS[1] + ", " + rowS[2] + ", " + rowS[3] + "\n");
                            fw.close();
                        }
                        catch (Exception f){
                            f.getStackTrace();
                        }
                            //catch (Exception e) {
                            //    e.getStackTrace();
                            //}
                } 
                br.close();
                }   
                catch (IOException m)   
                {  
                m.printStackTrace();  
                }
                String fileNameold = "Carpark_data2.csv";
                String fileNamenew = "Carpark_data.csv";
                try {
                    Files.move(new File(fileNameold).toPath(), new File(fileNamenew).toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException ex) {
                    //Logger.getLogger(SomeClass.class.getName()).log(Level.SEVERE, null, ex);
                    ex.getStackTrace();
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
        Test2 app = new Test2();
        app.setVisible(true);
    }
}

        
    
