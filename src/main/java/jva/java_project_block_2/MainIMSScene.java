package jva.java_project_block_2;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.*;

public class MainIMSScene {

    @FXML private TextField NAMELABEL;
    @FXML private TextField SURNAMELABEL;
    @FXML private TextField ADDRESSLABEL;
    @FXML private TextField IDNUMLABEL;
    @FXML private TextField AGEFIELD;
    @FXML private ComboBox<String> POLICYtYPEDROPDROWN;
    @FXML private ComboBox<String> SUMINSUREDDROPDOWN;
    @FXML private TextField COVERAGEAMOUNTTEXT;
    @FXML private Label PREMLABEL;
    @FXML private Button SubmitBtn;
    @FXML private TableView <Character> PolicyTable;
    @FXML private Button ViewPolicies;




    void initialize() {
        SubmitBtn.setOnAction(event -> handleSubmit());
        SubmitBtn.setOnAction(event -> handleSubmit());
        PolicyTable.setVisible(false);
        PolicyTable.setManaged(false);
        ViewPolicies.setText("View Policies");

        ViewPolicies.setOnAction(event -> {
            boolean isVisible = PolicyTable.isVisible();
            PolicyTable.setVisible(!isVisible);
            PolicyTable.setManaged(!isVisible); // prevents layout from reserving space when hidden
            ViewPolicies.setText(isVisible ? "View Policies" : "Hide Policies");
        });
    }
    @FXML
    private void handleSubmit() {
        String name = NAMELABEL.getText();
        int age = Integer.parseInt(AGEFIELD.getText());
        String policyType = POLICYtYPEDROPDROWN.getValue();
        double coverageAmount = Double.parseDouble(COVERAGEAMOUNTTEXT.getText());
        double premiumAmount = Double.parseDouble(PREMLABEL.getText());

        String insertCustomerSQL = "INSERT INTO customers (name, age) VALUES (?, ?)";
        String insertPolicySQL = "INSERT INTO policies (customer_id, policy_type, coverage_amount, premium_amount) VALUES (?, ?, ?, ?)";

        try (Connection conn = Database.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement customerStmt = conn.prepareStatement(insertCustomerSQL, Statement.RETURN_GENERATED_KEYS)) {
                customerStmt.setString(1, name);
                customerStmt.setInt(2, age);
                customerStmt.executeUpdate();

                ResultSet generatedKeys = customerStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int customerId = generatedKeys.getInt(1);

                    try (PreparedStatement policyStmt = conn.prepareStatement(insertPolicySQL)) {
                        policyStmt.setInt(1, customerId);
                        policyStmt.setString(2, policyType);
                        policyStmt.setDouble(3, coverageAmount);
                        policyStmt.setDouble(4, premiumAmount);
                        policyStmt.executeUpdate();
                    }

                    conn.commit();
                    System.out.println("✅ Customer and policy inserted successfully.");
                } else {
                    conn.rollback();
                    System.out.println("❌ Failed to retrieve customer ID.");
                }
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
