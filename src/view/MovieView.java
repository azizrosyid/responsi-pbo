package view;

import entity.Movie;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MovieView extends JFrame {

    private JTextField judulField;
    private JTextField alurField;
    private JTextField penokohanField;
    private JTextField aktingField;
    private DefaultTableModel model;
    private JTable table;
    private String[] columnNames = {"Judul", "Alur", "Penokohan", "Akting", "Nilai"};
    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton clearButton;

    public MovieView() {
        setTitle("Movie App");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setLayout(null);

        this.initComponents();
        this.addActionListener();
    }

    private void initComponents() {
        this.model = new DefaultTableModel(null, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane);
        scrollPane.setBounds(20, 20, 600, 500);

        JLabel judul = new JLabel("Judul");
        this.judulField = new JTextField();

        add(judul);
        judul.setBounds(650, 20, 100, 20);

        add(judulField);
        judulField.setBounds(650, 50, 100, 20);

        JLabel alur = new JLabel("Alur");
        this.alurField = new JTextField();

        add(alur);
        alur.setBounds(650, 80, 100, 20);

        add(alurField);
        alurField.setBounds(650, 110, 100, 20);

        JLabel penokohan = new JLabel("Penokohan");
        this.penokohanField = new JTextField();

        add(penokohan);
        penokohan.setBounds(650, 140, 100, 20);

        add(penokohanField);
        penokohanField.setBounds(650, 170, 100, 20);

        JLabel akting = new JLabel("Akting");
        this.aktingField = new JTextField();

        add(akting);
        akting.setBounds(650, 200, 100, 20);

        add(aktingField);
        aktingField.setBounds(650, 230, 100, 20);


        this.addButton = new JButton("Add");
        add(addButton);

        addButton.setBounds(650, 260, 100, 20);

        this.deleteButton = new JButton("Delete");
        deleteButton.setBackground(new java.awt.Color(255, 0, 0));
        add(deleteButton);
        deleteButton.setBounds(650, 290, 100, 20);

        this.updateButton = new JButton("Update");
        updateButton.setBackground(new java.awt.Color(0, 255, 0));
        add(updateButton);
        updateButton.setBounds(650, 320, 100, 20);

        this.clearButton = new JButton("Clear");
        clearButton.setBackground(new java.awt.Color(0, 0, 255));
        add(clearButton);
        clearButton.setBounds(650, 350, 100, 20);

        this.clearButton.addActionListener(e -> {
            this.resetForm();
        });
    }

    public void resetForm() {
        this.judulField.setText("");
        this.alurField.setText("");
        this.penokohanField.setText("");
        this.aktingField.setText("");
    }

    public void updateAllMovie(Movie[] movies) {
        this.model.setRowCount(0);
        for (Movie movie : movies) {
            model.addRow(new Object[]{movie.getJudul(), movie.getAlur(), movie.getPenokohan(), movie.getAkting(), movie.getNilai()});
        }
    }

    public Movie getMovie() {
        String judul = judulField.getText();
        double alur = Double.parseDouble(alurField.getText());
        double penokohan = Double.parseDouble(penokohanField.getText());
        double akting = Double.parseDouble(aktingField.getText());
        return new Movie(judul, alur, penokohan, akting);
    }

    public void setMovie(Movie movie) {
        judulField.setText(movie.getJudul());
        alurField.setText(String.valueOf(movie.getAlur()));
        penokohanField.setText(String.valueOf(movie.getPenokohan()));
        aktingField.setText(String.valueOf(movie.getAkting()));
    }

    public void addActionListener() {
        this.table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                judulField.setText(model.getValueAt(row, 0).toString());
                alurField.setText(model.getValueAt(row, 1).toString());
                penokohanField.setText(model.getValueAt(row, 2).toString());
                aktingField.setText(model.getValueAt(row, 3).toString());
            }
        });
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void setAddButtonListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    public void setDeleteButtonListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }

    public void setUpdateButtonListener(ActionListener listener) {
        updateButton.addActionListener(listener);
    }
}
