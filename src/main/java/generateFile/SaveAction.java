package generateFile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class SaveAction extends AbstractAction {
    private final JTextField filename;
    private final HashMap<JToggleButton, String> comboBtnCoord;
    private final HashMap<JToggleButton, String> comboBtnPion;
    public SaveAction(JTextField filename, HashMap<JToggleButton, String> comboBtnCoord, HashMap<JToggleButton, String> comboBtnPion){
        super("Sauvegarder");
        this.filename = filename;
        this.comboBtnCoord = comboBtnCoord;
        this.comboBtnPion = comboBtnPion;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!Objects.equals(filename.getText(), "") && filename.getText() != null) {
            try {
                FileWriter fileWriter = new FileWriter(filename.getText(), false);
                comboBtnPion.forEach((key, value) -> {
                    try {
                        fileWriter.append(value).append(comboBtnCoord.get(key)).append("\n");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                fileWriter.flush();
                fileWriter.close();
                JOptionPane.showMessageDialog(null, "Sauvegarde effectuée", "Sauvegarde réussie", JOptionPane.INFORMATION_MESSAGE);

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "La sauvegarde a échouée. Veuillez réessayer.", "Sauvegarde échouée", JOptionPane.ERROR_MESSAGE);

                throw new RuntimeException(ex);
            }
        }
    }
}
