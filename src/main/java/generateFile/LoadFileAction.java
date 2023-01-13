package generateFile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class LoadFileAction extends AbstractAction {
    private final JTextField filename;
    private final HashMap<JToggleButton, String> comboBtnCoord;
    private final HashMap<JToggleButton, String> comboBtnPion;


    public LoadFileAction(JTextField filename, HashMap<JToggleButton, String> comboBtnCoord, HashMap<JToggleButton, String> comboBtnPion) {
        super("Charger");
        this.filename = filename;
        this.comboBtnCoord = comboBtnCoord;
        this.comboBtnPion = comboBtnPion;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jFileChooser = new JFileChooser("./");
        jFileChooser.setMultiSelectionEnabled(false);
        jFileChooser.showDialog(null, "Choisir");
        if (jFileChooser.getSelectedFile() != null) {
            filename.setText(jFileChooser.getSelectedFile().getPath());
            filename.setCaretPosition(filename.getText().length());
            try {
                Scanner sc = new Scanner(new File(filename.getText()));
                while (sc.hasNext()) {
                    var ligne = sc.nextLine();
                    var tab = ligne.split(";");
                    System.out.println("tab.length = " + tab.length);
                    if (tab.length == 1)
                        throw new Exception();
                    var coord = ";" + tab[2] + ";" + tab[3];
                    var bouton = comboBtnCoord.entrySet().stream().filter(entry -> entry.getValue().equals(coord)).toList().get(0).getKey();
                    var pion = tab[0] + ";" + tab[1];
                    var textePion = tab[0] + tab[1];
                    bouton.setText(textePion);
                    comboBtnPion.put(bouton, pion);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Le chargement a échoué. Veuillez réessayer.", "Chargement échoué", JOptionPane.ERROR_MESSAGE);
                filename.setText("");
            }
            JOptionPane.showMessageDialog(null, "Le Fichier a bien été chargé.", "Chargement réussi", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
