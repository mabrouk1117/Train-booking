import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class EditTrainForm extends JFrame {

    private JTextField textFieldID;
    private JTextField textFieldname;
    private JTextField textFieldnumber;
    private JTextField textFieldnumSear;
    private JButton saveButton;
    private JButton cancelButton;
    private JPanel EditTrainForm;


public EditTrainForm(Connection connection, int AdminID) {
    setContentPane(EditTrainForm);
    setTitle("Update a Train in our System");
    setSize(450,300);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setVisible(true);
    saveButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Train train = new Train(connection);
            int trainID = Integer.parseInt(textFieldID.getText());
            String trainName = textFieldname.getText();
            int trainNumber = Integer.parseInt(textFieldnumber.getText());
            int seats = Integer.parseInt(textFieldnumSear.getText());

            train.EditTrain(trainID, trainName, trainNumber, seats,AdminID);

            // Close the edit train form after saving the changes
            dispose();
        }

        }
    );
    cancelButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    });
}
}


