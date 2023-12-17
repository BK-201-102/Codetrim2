/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package CodeTrim;

/**
 *
 * @author Katharsis
 */
// MainApplication.java
import java.util.HashMap;
import javax.swing.*;

public class MainApplication {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame(new HashMap<>());
            loginFrame.setVisible(true);
        });
    }
}

