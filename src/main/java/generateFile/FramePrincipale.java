package generateFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Joshua GALIEN
 */
public class FramePrincipale extends JFrame implements ActionListener {
    private JComboBox<String> selectCouleur;
    private JComboBox<String> selectType;
    private JButton validEdit;
    private JButton resetBtn;
    private JTextField filename;
    private HashMap<JToggleButton, String> comboBtnPion;

    private JToggleButton select;
    private JButton btnChooseFile;

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
        HashMap<JToggleButton, String> comboBtnCoord = new HashMap<>();
        comboBtnPion = new HashMap<>();
        filename = new JTextField();
        filename.setEditable(false);
        filename.setFocusable(false);
        btnChooseFile = new JButton("Choisir");
        btnChooseFile.addActionListener(this);
        JButton buttonGenerate = new JButton(new SaveAction(filename, comboBtnCoord, comboBtnPion));
        JButton buttonCancel = new JButton(new ActionQuitter());
        JPanel panelContenant = new JPanel();
        JPanel panelPlateau = new JPanel();
        ArrayList<JToggleButton> listButton = new ArrayList<>();
        ButtonGroup buttonGroup = new ButtonGroup();
        for (int i = 0, ligne = 0, colonne = 0; i < 100; i++) {
            if (i % 10 == 0 && i != 0) {
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

        //======== menuBar1 ========
        JMenuBar menuBar1 = new JMenuBar();
        {

            //======== menu1 ========
            JMenu menu1 = new JMenu();
            {
                menu1.setText("Fichier");

                //---- menuItem1 ----
                JMenuItem menuItem1 = new JMenuItem(new SaveAction(filename, comboBtnCoord, comboBtnPion));
                menu1.add(menuItem1);

                //---- menuItem2 ----
                JMenuItem menuItem2 = new JMenuItem(new LoadFileAction(filename, comboBtnCoord, comboBtnPion));
                menu1.add(menuItem2);

                //---- menuItem3 ----
                JMenuItem menuItem3 = new JMenuItem(new ActionQuitter());
                menu1.add(menuItem3);
            }
            menuBar1.add(menu1);
        }
        setJMenuBar(menuBar1);

        //======== panelAction ========
        {
            //======== panelBoutonsAction ========
            {
                //---- buttonCancel ----
                GroupLayout panelBoutonsActionLayout = new GroupLayout(panelBoutonsAction);
                panelBoutonsAction.setLayout(panelBoutonsActionLayout);
                panelBoutonsActionLayout.setHorizontalGroup(
                        panelBoutonsActionLayout.createParallelGroup()
                                .addGroup(panelBoutonsActionLayout.createSequentialGroup()
                                        .addGap(196, 196, 196)
                                        .addComponent(label4)
                                        .addGap(18, 18, 18)
                                        .addComponent(filename, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnChooseFile, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                                        .addGap(40, 40, 40)
                                        .addComponent(buttonGenerate)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(buttonCancel)
                                        .addGap(40, 40, 40))
                );

                panelBoutonsActionLayout.setVerticalGroup(
                        panelBoutonsActionLayout.createParallelGroup()
                                .addGroup(panelBoutonsActionLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(panelBoutonsActionLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(label4)
                                                .addComponent(filename)
                                                .addComponent(btnChooseFile)
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
                    panelPlateau.setLayout(new GridLayout(11, 11, 5, 5));
                    for (JToggleButton bouton :
                            listButton) {
                        panelPlateau.add(bouton);
                        bouton.addActionListener(this);
                    }
                    panelPlateau.add(new JLabel("", SwingConstants.CENTER), 0);
                    for (int i = 1; i < 11; i++) {
                        panelPlateau.add(new JLabel((char) ('A' + i - 1) + "", SwingConstants.CENTER), i);
                    }
                    for (int i = 11; i < 120; i += 11) {
                        panelPlateau.add(new JLabel(i % 120 / 11 + "", SwingConstants.CENTER), i);
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
        if (e.getSource() == btnChooseFile) {
            JFileChooser jFileChooser = new JFileChooser("./");
            jFileChooser.setMultiSelectionEnabled(false);
            jFileChooser.showDialog(this, "Choisir");
            if (jFileChooser.getSelectedFile() != null) {
                filename.setText(jFileChooser.getSelectedFile().getPath());
                filename.setCaretPosition(filename.getText().length());
            }
        }
    }
}