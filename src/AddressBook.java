import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import javax.swing.*;

class Contact
{
    String firstName;
    String lastName;
    String email;
    String address;
    String phoneNumber;

    Contact()
    {
        firstName = lastName = email = address = phoneNumber = "";
    }

    Contact(String firstName, String lastName, String email, String address,
            String phoneNumber)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    String getFirstName()
    {
        return firstName;
    }
    String getLastName()
    {
        return lastName;
    }
    String getEmail()
    {
        return email;
    }
    String getAddress()
    {
        return address;
    }
    String getPhoneNumber()
    {
        return phoneNumber;
    }

    void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    void setEmail(String email)
    {
        this.email = email;
    }
    void setAddress(String address)
    {
        this.address = address;
    }
    void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String toString()
    {
        return " Name: " + firstName + " " + lastName +
                " Email: " + email + " Address: " + address +
                " Phone Number: " + phoneNumber + "\n";
    }
}
public class AddressBook
{
    JFrame jf;

    JPanel p1, p2, p3, p4, p5, p6, mainP;

    JLabel firstNameL, lastNameL, emailL, addressL, phoneNumberL, addressImg;

    JTextField firstNameT, lastNameT, emailT, addressT, phoneNumberT;

    JButton addB, searchB, viewAllB, deleteB, loadB, editB, clearB, exitB;

    JTextArea jta;

    ArrayList<Contact> contact;

    AddressBook()
    {
        contact = new ArrayList<Contact>();

        jf = new JFrame("Address Book");
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p4 = new JPanel();
        p5 = new JPanel();
        p6 = new JPanel();
        mainP = new JPanel();
        p1.setBackground(Color.YELLOW);
        p2.setBackground(Color.YELLOW);
        p3.setBackground(Color.YELLOW);
        p6.setBackground(Color.MAGENTA);
        firstNameL = new JLabel("Enter First Name: ");
        lastNameL = new JLabel("Enter Last Name: ");
        emailL = new JLabel("Enter Email: ");
        addressL = new JLabel("Enter Address: ");
        phoneNumberL = new JLabel("Enter Phone Number: ");
        addressImg = new JLabel();
        addressImg.setIcon(new ImageIcon("https://images.news18.com/ibnlive/uploads/2021/06/1622715559_disha.jpg?impolicy=website&width=510&height=356"));
        firstNameT = new JTextField(10);
        lastNameT = new JTextField(10);
        emailT = new JTextField(10);
        addressT = new JTextField(10);
        phoneNumberT = new JTextField(10);

        firstNameT.setBackground(Color.RED);
        lastNameT.setBackground(Color.GREEN);
        emailT.setBackground(Color.CYAN);
        addressT.setBackground(Color.LIGHT_GRAY);
        phoneNumberT.setBackground(Color.WHITE);

        jta = new JTextArea(13, 40);

        addB = new JButton("Add");
        searchB = new JButton("Search");
        viewAllB = new JButton("View All");
        deleteB = new JButton("Delete");
        loadB = new JButton("Load");
        editB = new JButton("Edit");
        clearB = new JButton("Clear");
        exitB = new JButton("Exit");

        addB.setBackground(Color.CYAN);
        loadB.setBackground(Color.CYAN);
        searchB.setBackground(Color.GREEN);
        viewAllB.setBackground(Color.GREEN);
        editB.setBackground(Color.RED);
        deleteB.setBackground(Color.RED);
        clearB.setBackground(Color.BLUE);
        exitB.setBackground(Color.BLUE);

        p1.add(addressImg);

        p2.add(firstNameL);
        p2.add(lastNameL);
        p2.add(emailL);
        p2.add(addressL);
        p2.add(phoneNumberL);

        p2.setLayout(new GridLayout(8, 1));
        p3.add(firstNameT);
        p3.add(lastNameT);
        p3.add(emailT);
        p3.add(addressT);
        p3.add(phoneNumberT);
        p3.setLayout(new GridLayout(8, 1));

        p4.add(p1);
        p4.add(p2);
        p4.add(p3);
        p4.setLayout(new GridLayout(1, 3));

        p5.add(loadB);
        p5.add(addB);
        p5.add(searchB);
        p5.add(viewAllB);
        p5.add(editB);
        p5.add(deleteB);
        p5.add(exitB);
        p5.add(clearB);
        p5.setLayout(new GridLayout(5, 2));

        p6.add(jta);

        mainP.add(p4);
        mainP.add(p5);
        mainP.add(p6);
        mainP.setLayout(new GridLayout(3, 1));

        jf.add(mainP);
        jf.setSize(500, 700);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        clearB.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                jta.setText("");
                firstNameT.setText("");
                lastNameT.setText("");
                emailT.setText("");
                addressT.setText("");
                phoneNumberT.setText("");
            }
        });

        addB.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                addContact();
            }
        });

        editB.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent ae)
            {
                editContact();
            }
        });

        deleteB.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                removeContact();
            }
        });

        searchB.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {

                String fn = JOptionPane.showInputDialog(null,
                        "Enter First Name to search: ");
                int pos = searchContact(fn);

                if(pos == -1)

                    JOptionPane.showMessageDialog(null, "No record found.",
                            "ERROR", JOptionPane.ERROR_MESSAGE);

                else
                {
                    firstNameT.setText(contact.get(pos).firstName);
                    lastNameT.setText(contact.get(pos).lastName);
                    emailT.setText(contact.get(pos).email);
                    addressT.setText(contact.get(pos).address);
                    phoneNumberT.setText(contact.get(pos).phoneNumber);
                }
            }
        });

        viewAllB.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent ae)
            {
                if(contact.size() == 0)
                    loadContact();
                viewContact();
            }
        });

        loadB.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent ae)
            {
                loadContact();
            }
        });

        exitB.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent ae)
            {
                backupContact();
                System.exit(0);
            }
        });
    }


    void editContact()
    {

        String fn = JOptionPane.showInputDialog(null, "Enter First Name: ");

        int pos = searchContact(fn);

        if(pos == -1)

            JOptionPane.showMessageDialog(null, "No record found on name: " + fn,
                    "ERROR", JOptionPane.WARNING_MESSAGE);
        else
        {

            int choice = Integer.parseInt(JOptionPane.showInputDialog(null,
                    " 1 - Last name \n 2 - Email \n 3 - Address \n 4 - Phone number" +
                            "\n What is your choice: "));

            if(choice == 1)
            {
                String data = JOptionPane.showInputDialog(null,
                        "Enter Last Name to edit: ");
                contact.get(pos).setLastName(data);
            }

            else if(choice == 2)
            {
                String data = JOptionPane.showInputDialog(null,
                        "Enter Email address to edit: ");
                contact.get(pos).setEmail(data);
            }

            else if(choice == 3)
            {

                String data = JOptionPane.showInputDialog(null,
                        "Enter Address to edit: ");
                contact.get(pos).setAddress(data);
            }

            else if(choice == 4)
            {
                String data = JOptionPane.showInputDialog(null,
                        "Enter Phone number to edit: ");
                contact.get(pos).setPhoneNumber(data);
            }

            else
                JOptionPane.showMessageDialog(null, "Invalid choice to edit.",
                        "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    void loadContact() {
        Scanner fileRead = null;
        try {
            fileRead = new Scanner(new File("contact.txt"));
            while (fileRead.hasNext()) {
                contact.add(new Contact(fileRead.next(), fileRead.next(),
                        fileRead.next(), fileRead.next(), fileRead.next()));
            }
            JOptionPane.showMessageDialog(null, "Contact successfully loaded",
                    "LOAD", JOptionPane.INFORMATION_MESSAGE);

            fileRead.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Unable to open the file for reading.",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    void viewContact()
    {

        jta.setText("");
        for(int x = 0; x < contact.size(); x++)
            jta.setText(jta.getText() + contact.get(x));
    }
    void addContact()
    {
        String firstName = firstNameT.getText();
        String lastName = lastNameT.getText();
        String email = emailT.getText();
        String address = addressT.getText();
        String phoneNumber = phoneNumberT.getText();
        contact.add(new Contact(firstName, lastName, email, address, phoneNumber));

        JOptionPane.showMessageDialog(null, "Contact successfully added",
                "ADDED", JOptionPane.INFORMATION_MESSAGE);
    }
    int searchContact(String fn)
    {
        for(int c = 0; c < contact.size(); c++)

            if(fn.equalsIgnoreCase(contact.get(c).firstName))
                return c;
        return -1;
    }
    void removeContact()
    {
        String fn = JOptionPane.showInputDialog(null, "Enter First Name: ");

        int pos = searchContact(fn);

        if(pos == -1)
            JOptionPane.showMessageDialog(null, "No record found.",
                    "ERROR", JOptionPane.WARNING_MESSAGE);

        else
        {
            contact.remove(pos);
            JOptionPane.showMessageDialog(null, "Contact successfully removed",
                    "DEL", JOptionPane.WARNING_MESSAGE);
        }
    }
    void backupContact()
    {
        File file = new File("contact.txt");

        PrintWriter pw = null;

        try
        {
            pw = new PrintWriter (file);

            for(int c = 0; c < contact.size(); c++)
            {
                pw.printf(contact.get(c).firstName + " " +
                        contact.get(c).lastName + " ");
                pw.printf(contact.get(c).email + " " +
                        contact.get(c).address + " ");
                pw.printf(contact.get(c).phoneNumber);
                pw.println();
            }
            JOptionPane.showMessageDialog(null, "Contact backup successfully done",
                    "BACKUP", JOptionPane.WARNING_MESSAGE);
        }
        catch (FileNotFoundException e)
        {
            JOptionPane.showMessageDialog(null, "Unable to open the file for writing.",
                    "ERROR", JOptionPane.WARNING_MESSAGE);
        }
        pw.close();
    }
    public static void main(final String[] args) {
        new AddressBook();
    }
}






