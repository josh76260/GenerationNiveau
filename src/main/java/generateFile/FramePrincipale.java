package generateFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author Joshua GALIEN
 */
public class FramePrincipale extends JFrame implements ActionListener {
    private JButton buttonGenerate;
    private JComboBox<String> selectCouleur;
    private JComboBox<String> selectType;
    private JButton validEdit;
    private JButton resetBtn;
    private JTextField filename;
    private HashMap<JToggleButton, String> comboBtnCoord;
    private HashMap<JToggleButton, String> comboBtnPion;

    private JToggleButton select;

    public FramePrincipale() {
        initComponents();
    }

    private void initComponents() {
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatDarculaLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        var panelBoutonsAction = new JPanel();
        var label4 = new JLabel("Nom du fichier : ");
        filename = new JTextField();
        buttonGenerate = new JButton("Générer");
        buttonGenerate.addActionListener(this);
        JButton buttonCancel = new JButton();
        JPanel panelContenant = new JPanel();
        JPanel panelPlateau = new JPanel();
        ArrayList<JToggleButton> listButton = new ArrayList<>();
        comboBtnCoord = new HashMap<>();
        comboBtnPion = new HashMap<>();
        ButtonGroup buttonGroup = new ButtonGroup();
        for (int i = 0, ligne = 0, colonne = 0; i < 81; i++) {
            if (i % 9 == 0 && i != 0) {
                ligne++;
                colonne = 0;
            }
            String coord = ";" + ligne + ";" + colonne++;
            JToggleButton button = new JToggleButton();
            listButton.add(button);
            buttonGroup.add(button);
            comboBtnCoord.put(button, coord);
        }
        JPanel panelDetailsPion = new JPanel();
        JLabel labelTitreBox = new JLabel("Propriétés du pion");
        JLabel labelCouleur = new JLabel("Couleur");
        JLabel labelType = new JLabel("Type");
        selectCouleur = new JComboBox<>(new String[]{"B", "N"});
        selectType = new JComboBox<>(new String[]{"P", "D"});
        validEdit = new JButton("Valider");
        validEdit.addActionListener(this);
        resetBtn = new JButton("Reset");
        resetBtn.addActionListener(this);

        //======== this ========
        setTitle("Générateur de niveau - Dames");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== panelAction ========
        {
            //======== panelBoutonsAction ========
            {
                //---- buttonCancel ----
                buttonCancel.setAction(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                });
                buttonCancel.setText("Quitter");

                GroupLayout panelBoutonsActionLayout = new GroupLayout(panelBoutonsAction);
                panelBoutonsAction.setLayout(panelBoutonsActionLayout);
                panelBoutonsActionLayout.setHorizontalGroup(
                        panelBoutonsActionLayout.createParallelGroup()
                                .addGroup(panelBoutonsActionLayout.createSequentialGroup()
                                        .addGap(400, 400, 400)
                                        .addComponent(label4)
                                        .addGap(18, 18, 18)
                                        .addComponent(filename, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(buttonGenerate)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(buttonCancel)
                                        .addGap(5, 5, 5))
                );
                panelBoutonsActionLayout.setVerticalGroup(
                        panelBoutonsActionLayout.createParallelGroup()
                                .addGroup(panelBoutonsActionLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(panelBoutonsActionLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(label4)
                                                .addComponent(filename)
                                                .addComponent(buttonCancel)
                                                .addComponent(buttonGenerate))
                                        .addContainerGap())
                );
            }
            contentPane.add(panelBoutonsAction, BorderLayout.SOUTH);

            //======== PanelContenant ========
            {

                //======== PanelPlateau ========
                {
                    panelPlateau.setLayout(new GridLayout(10, 10, 5, 5));
                    for (JToggleButton bouton :
                            listButton) {
                        panelPlateau.add(bouton);
                        bouton.addActionListener(this);
                    }
                    panelPlateau.add(new JLabel("", SwingConstants.CENTER), 0);
                    for (int i = 1; i < 10; i++) {
                        panelPlateau.add(new JLabel((char) ('A' + i - 1) + "", SwingConstants.CENTER), i);
                    }
                    for (int i = 10; i < 100; i += 10) {
                        panelPlateau.add(new JLabel(i % 100 / 10 + "", SwingConstants.CENTER), i);
                    }

                }

                //======== PanelDetailsPion ========
                {
                    panelDetailsPion.setAutoscrolls(true);

                    GroupLayout PanelDetailsPionLayout = new GroupLayout(panelDetailsPion);
                    panelDetailsPion.setLayout(PanelDetailsPionLayout);
                    PanelDetailsPionLayout.setHorizontalGroup(
                            PanelDetailsPionLayout.createParallelGroup()
                                    .addComponent(labelTitreBox, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(PanelDetailsPionLayout.createSequentialGroup()
                                            .addGap(29, 29, 29)
                                            .addGroup(PanelDetailsPionLayout.createParallelGroup()
                                                    .addComponent(labelCouleur)
                                                    .addComponent(labelType, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(PanelDetailsPionLayout.createParallelGroup()
                                                    .addComponent(selectCouleur)
                                                    .addComponent(selectType))
                                            .addContainerGap())
                                    .addGroup(PanelDetailsPionLayout.createSequentialGroup()
                                            .addComponent(validEdit)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(resetBtn)
                                            .addGap(0, 0, Short.MAX_VALUE))
                    );
                    PanelDetailsPionLayout.setVerticalGroup(
                            PanelDetailsPionLayout.createParallelGroup()
                                    .addGroup(PanelDetailsPionLayout.createSequentialGroup()
                                            .addGap(83, 83, 83)
                                            .addComponent(labelTitreBox)
                                            .addGap(18, 18, 18)
                                            .addGroup(PanelDetailsPionLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                    .addComponent(labelCouleur)
                                                    .addComponent(selectCouleur, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                            .addGap(18, 18, 18)
                                            .addGroup(PanelDetailsPionLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                    .addComponent(labelType)
                                                    .addComponent(selectType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                            .addGap(18, 18, 18)
                                            .addGroup(PanelDetailsPionLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                    .addComponent(validEdit)
                                                    .addComponent(resetBtn))
                                            .addContainerGap(223, Short.MAX_VALUE))
                    );
                }

                GroupLayout panelContenantLayout = new GroupLayout(panelContenant);
                panelContenant.setLayout(panelContenantLayout);
                panelContenantLayout.setHorizontalGroup(
                        panelContenantLayout.createParallelGroup()
                                .addGroup(panelContenantLayout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(panelPlateau, GroupLayout.PREFERRED_SIZE, 700, GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(panelDetailsPion, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addContainerGap())
                );
                panelContenantLayout.setVerticalGroup(
                        panelContenantLayout.createParallelGroup()
                                .addGroup(panelContenantLayout.createSequentialGroup()
                                        .addGroup(panelContenantLayout.createParallelGroup()
                                                .addComponent(panelDetailsPion, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(panelContenantLayout.createSequentialGroup()
                                                        .addContainerGap()
                                                        .addComponent(panelPlateau, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)))
                                        .addContainerGap())
                );
            }
            contentPane.add(panelContenant, BorderLayout.CENTER);
            pack();
            setLocationRelativeTo(getOwner());
            setVisible(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JToggleButton) {
            select = (JToggleButton) e.getSource();
            if (comboBtnPion.get(select) != null) {
                selectType.setSelectedItem(comboBtnPion.get(select).charAt(0) + "");
                selectCouleur.setSelectedItem(comboBtnPion.get(select).charAt(1) + "");
            }
        }
        if (e.getSource() == validEdit) {
            if (select != null) {
                comboBtnPion.put(select, selectType.getSelectedItem() + ";" + selectCouleur.getSelectedItem());
                select.setText(selectType.getSelectedItem() + "" + selectCouleur.getSelectedItem());
            }
        }

        if (e.getSource() == resetBtn) {
            comboBtnPion.forEach((key, value) -> key.setText(""));
            comboBtnPion = new HashMap<>();
        }
        if (e.getSource() == buttonGenerate) {
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
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}