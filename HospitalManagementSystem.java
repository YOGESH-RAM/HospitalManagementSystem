import java.util.*;

class Patient {
    int id;
    String name;
    String disease;
    ArrayList<String[]> appointments = new ArrayList<>();

    Patient(int id, String name, String disease) {
        this.id = id;
        this.name = name;
        this.disease = disease;
    }

    void showInfo() {
        System.out.println("ID: " + id + ", Name: " + name + ", Disease: " + disease);
    }

    void showAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments booked.");
        } else {
            for (String[] record : appointments) {
                System.out.println("Date: " + record[0] + " - Doctor: " + record[1]);
            }
        }
    }
}

class Doctor {
    String name;
    ArrayList<String> schedule = new ArrayList<>();

    Doctor(String name) {
        this.name = name;
    }

    void showSchedule() {
        if (schedule.isEmpty()) {
            System.out.println("No schedule assigned.");
        } else {
            System.out.println("Doctor's Schedule:");
            for (String day : schedule) {
                System.out.println(day);
            }
        }
    }
}

public class HospitalManagementSystem {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Patient> patients = new ArrayList<>();
    static ArrayList<Doctor> doctors = new ArrayList<>();
    static ArrayList<String[]> appointmentSchedule = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n---- Login ----");
            System.out.println("1. Admin");
            System.out.println("2. Doctor");
            System.out.println("3. Patient");
            System.out.println("4. Exit");
            System.out.print("Select role: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: adminPanel(); break;
                case 2: doctorPanel(); break;
                case 3: patientPanel(); break;
                case 4: System.out.println("Exiting..."); return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    static void adminPanel() {
        while (true) {
            System.out.println("\n-- Admin Panel --");
            System.out.println("1. Add Patient");
            System.out.println("2. View Patients");
            System.out.println("3. Add Doctor Schedule");
            System.out.println("4. Set Appointment");
            System.out.println("5. Back");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt(); sc.nextLine();

            switch (ch) {
                case 1: addPatient(); break;
                case 2: viewPatients(); break;
                case 3: addDoctorSchedule(); break;
                case 4: setAppointment(); break;
                case 5: return;
                default: System.out.println("Invalid option.");
            }
        }
    }

    static void addPatient() {
        System.out.print("Enter ID: ");
        int id = sc.nextInt(); sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Disease: ");
        String disease = sc.nextLine();
        patients.add(new Patient(id, name, disease));
        System.out.println("Patient added.");
    }

    static void viewPatients() {
        if (patients.isEmpty()) {
            System.out.println("No patients available.");
        } else {
            for (Patient p : patients) {
                p.showInfo();
            }
        }
    }

    static void addDoctorSchedule() {
        System.out.print("Enter Doctor Name: ");
        String doctorName = sc.nextLine();
        Doctor doctor = new Doctor(doctorName);

        System.out.print("How many days to add to schedule? ");
        int count = sc.nextInt(); sc.nextLine();
        for (int i = 0; i < count; i++) {
            System.out.print("Enter Day: ");
            String day = sc.nextLine();
            doctor.schedule.add(day);
        }
        doctors.add(doctor);
        System.out.println("Doctor schedule added.");
    }

    static void setAppointment() {
        System.out.print("Enter Patient ID: ");
        int id = sc.nextInt(); sc.nextLine();
        Patient p = null;
        for (Patient pt : patients) {
            if (pt.id == id) {
                p = pt;
                break;
            }
        }

        if (p == null) {
            System.out.println("Patient not found.");
            return;
        }

        System.out.print("Enter Doctor Name: ");
        String doctorName = sc.nextLine();

        System.out.print("Enter Appointment Date (dd-mm-yyyy): ");
        String date = sc.nextLine();

        appointmentSchedule.add(new String[]{date, doctorName});
        p.appointments.add(new String[]{date, doctorName});
        System.out.println("Appointment booked.");
    }

    static void doctorPanel() {
        System.out.print("Enter Doctor Name: ");
        String name = sc.nextLine();
        Doctor doctor = null;
        for (Doctor doc : doctors) {
            if (doc.name.equalsIgnoreCase(name)) {
                doctor = doc;
                break;
            }
        }

        if (doctor == null) {
            System.out.println("Doctor not found.");
            return;
        }

        while (true) {
            System.out.println("\n-- Doctor Panel --");
            System.out.println("1. View Schedule");
            System.out.println("2. View Appointments");
            System.out.println("3. Back");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt(); sc.nextLine();

            switch (ch) {
                case 1: doctor.showSchedule(); break;
                case 2: viewAppointmentsForDoctor(name); break;
                case 3: return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    static void viewAppointmentsForDoctor(String doctorName) {
        System.out.println("Appointments for " + doctorName + ":");
        for (String[] appointment : appointmentSchedule) {
            if (appointment[1].equalsIgnoreCase(doctorName)) {
                System.out.println("Date: " + appointment[0]);
            }
        }
    }

    static void patientPanel() {
        System.out.print("Enter Patient ID: ");
        int id = sc.nextInt(); sc.nextLine();
        Patient p = null;
        for (Patient pt : patients) {
            if (pt.id == id) {
                p = pt;
                break;
            }
        }

        if (p == null) {
            System.out.println("Patient not found.");
            return;
        }

        while (true) {
            System.out.println("\n-- Patient Panel --");
            System.out.println("1. View My Details");
            System.out.println("2. View Appointments");
            System.out.println("3. Book an Appointment");
            System.out.println("4. Back");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt(); sc.nextLine();

            switch (ch) {
                case 1: p.showInfo(); break;
                case 2: p.showAppointments(); break;
                case 3: bookAppointment(p); break;
                case 4: return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    static void bookAppointment(Patient p) {
        System.out.print("Enter Doctor Name: ");
        String doctorName = sc.nextLine();
        System.out.print("Enter Appointment Date (dd-mm-yyyy): ");
        String date = sc.nextLine();

        p.appointments.add(new String[]{date, doctorName});
        appointmentSchedule.add(new String[]{date, doctorName});
        System.out.println("Appointment booked.");
    }
}