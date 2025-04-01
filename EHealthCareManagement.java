import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.table.DefaultTableModel;

class Patient {
    private String name;
    private int age;
    private String disease;

    public Patient(String name, int age, String disease) {
        this.name = name;
        this.age = age;
        this.disease = disease;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDisease() {
        return disease;
    }

    @Override
    public String toString() {
        return "Patient{name='" + name + "', age=" + age + ", disease='" + disease + "'}";
    }
}

class Doctor {
    private String name;
    private String specialization;

    public Doctor(String name, String specialization) {
        this.name = name;
        this.specialization = specialization;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    @Override
    public String toString() {
        return "Doctor{name='" + name + "', specialization='" + specialization + "'}";
    }
}

public class EHealthCareManagement extends JFrame {
    private JTextField patientNameField, patientAgeField, patientDiseaseField;
    private JTextField doctorNameField, doctorSpecializationField;
    private JButton addPatientButton, addDoctorButton, sortPatientButton, sortDoctorButton;
    private JTable patientTable, doctorTable;
    private DefaultTableModel patientTableModel, doctorTableModel;
    private ArrayList<Patient> patients = new ArrayList<>();
    private ArrayList<Doctor> doctors = new ArrayList<>();

    public EHealthCareManagement() {
        setTitle("E-HealthCare Management System");
        setLayout(new BorderLayout());
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center the window on screen

        // Header
        JLabel header = new JLabel("E-HealthCare Management System", JLabel.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 24));
        header.setForeground(Color.DARK_GRAY);
        header.setPreferredSize(new Dimension(800, 40));
        add(header, BorderLayout.NORTH);

        // Patient Panel
        JPanel patientPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        patientPanel.setBorder(BorderFactory.createTitledBorder("Patient Details"));
        patientPanel.add(new JLabel("Patient Name:"));
        patientNameField = new JTextField();
        patientPanel.add(patientNameField);
        patientPanel.add(new JLabel("Age:"));
        patientAgeField = new JTextField();
        patientPanel.add(patientAgeField);
        patientPanel.add(new JLabel("Disease:"));
        patientDiseaseField = new JTextField();
        patientPanel.add(patientDiseaseField);
        addPatientButton = new JButton("Add Patient");
        patientPanel.add(addPatientButton);
        sortPatientButton = new JButton("Sort by Age");
        patientPanel.add(sortPatientButton);

        // Doctor Panel
        JPanel doctorPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        doctorPanel.setBorder(BorderFactory.createTitledBorder("Doctor Details"));
        doctorPanel.add(new JLabel("Doctor Name:"));
        doctorNameField = new JTextField();
        doctorPanel.add(doctorNameField);
        doctorPanel.add(new JLabel("Specialization:"));
        doctorSpecializationField = new JTextField();
        doctorPanel.add(doctorSpecializationField);
        addDoctorButton = new JButton("Add Doctor");
        doctorPanel.add(addDoctorButton);
        sortDoctorButton = new JButton("Sort by Name");
        doctorPanel.add(sortDoctorButton);

        // Tables to display patients and doctors
        patientTableModel = new DefaultTableModel(new Object[]{"Name", "Age", "Disease"}, 0);
        patientTable = new JTable(patientTableModel);
        JScrollPane patientScrollPane = new JScrollPane(patientTable);
        patientScrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        doctorTableModel = new DefaultTableModel(new Object[]{"Name", "Specialization"}, 0);
        doctorTable = new JTable(doctorTableModel);
        JScrollPane doctorScrollPane = new JScrollPane(doctorTable);
        doctorScrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // Action Listeners
        addPatientButton.addActionListener(e -> addPatient());
        addDoctorButton.addActionListener(e -> addDoctor());
        sortPatientButton.addActionListener(e -> sortPatientsByAge());
        sortDoctorButton.addActionListener(e -> sortDoctorsByName());

        // Layout for the frame
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(1, 2, 20, 20));
        centerPanel.add(patientScrollPane);
        centerPanel.add(doctorScrollPane);

        // Footer
        JPanel footer = new JPanel();
        footer.setLayout(new BorderLayout());
        footer.setPreferredSize(new Dimension(800, 30));
        footer.setBackground(Color.LIGHT_GRAY);
        footer.add(new JLabel("Developed by E-HealthCare Management Team", JLabel.CENTER), BorderLayout.CENTER);

        add(patientPanel, BorderLayout.WEST);
        add(doctorPanel, BorderLayout.EAST);
        add(centerPanel, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);
    }

    private void addPatient() {
        String name = patientNameField.getText();
        int age = Integer.parseInt(patientAgeField.getText());
        String disease = patientDiseaseField.getText();
        Patient patient = new Patient(name, age, disease);
        patients.add(patient);
        patientTableModel.addRow(new Object[]{patient.getName(), patient.getAge(), patient.getDisease()});
        clearPatientFields();
    }

    private void addDoctor() {
        String name = doctorNameField.getText();
        String specialization = doctorSpecializationField.getText();
        Doctor doctor = new Doctor(name, specialization);
        doctors.add(doctor);
        doctorTableModel.addRow(new Object[]{doctor.getName(), doctor.getSpecialization()});
        clearDoctorFields();
    }

    private void sortPatientsByAge() {
        Collections.sort(patients, Comparator.comparingInt(Patient::getAge));
        patientTableModel.setRowCount(0);
        for (Patient patient : patients) {
            patientTableModel.addRow(new Object[]{patient.getName(), patient.getAge(), patient.getDisease()});
        }
    }

    private void sortDoctorsByName() {
        Collections.sort(doctors, Comparator.comparing(Doctor::getName));
        doctorTableModel.setRowCount(0);
        for (Doctor doctor : doctors) {
            doctorTableModel.addRow(new Object[]{doctor.getName(), doctor.getSpecialization()});
        }
    }

    private void clearPatientFields() {
        patientNameField.setText("");
        patientAgeField.setText("");
        patientDiseaseField.setText("");
    }

    private void clearDoctorFields() {
        doctorNameField.setText("");
        doctorSpecializationField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EHealthCareManagement frame = new EHealthCareManagement();
            frame.setVisible(true);
        });
    }
}
