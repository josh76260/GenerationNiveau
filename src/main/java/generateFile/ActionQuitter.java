package generateFile;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ActionQuitter extends AbstractAction {
    public ActionQuitter(){
        super("Quitter");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
