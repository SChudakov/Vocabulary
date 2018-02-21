/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sschudakov.ui;

import com.sschudakov.request.UserRequestManager;

import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

/**
 * @author Semen
 */
public class InputWordsJFrame extends javax.swing.JFrame {

    /**
     * Creates new form InputWrodsJFrame
     */
    public InputWordsJFrame() {
        initComponents();

        collectionsJT.getColumnModel().getColumn(0).setMinWidth(220);
        collectionsJT.getColumnModel().getColumn(1).setMinWidth(60);
        collectionsJT.getColumnModel().getColumn(1).setPreferredWidth(60);
        collectionsJT.getColumnModel().getColumn(0).setPreferredWidth(220);
        this.userRequestManager = new UserRequestManager();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        TabbedPane = new javax.swing.JTabbedPane();
        WordPanel = new javax.swing.JPanel();
        languageJCB = new javax.swing.JComboBox<>();
        wordJTF = new javax.swing.JTextField();
        meaningsJLabel = new javax.swing.JLabel();
        collectionsJLabel = new javax.swing.JLabel();
        meaningsLanguageJCB = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        meaningsJL = new javax.swing.JList<>();
        saveWordJB = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        collectionsJT = new javax.swing.JTable();
        separator = new javax.swing.JSeparator();
        addMeaningJB = new javax.swing.JButton();
        createCollectionJB = new javax.swing.JButton();
        collectionJTF = new javax.swing.JTextField();
        wordClassJLabel = new javax.swing.JLabel();
        wordClassJCB = new javax.swing.JComboBox<>();
        meaningJTF = new javax.swing.JTextField();
        deleteMeaningJB = new javax.swing.JButton();
        wordFoundIndidcator = new com.sschudakov.ui.IndicatorLed();
        CollectionsPanel = new javax.swing.JPanel();
        GroupsComboBox = new javax.swing.JComboBox<>();
        AddCollectionTextField = new javax.swing.JTextField();
        AddCollectionButton = new javax.swing.JButton();
        GroupsWordList = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        MegaListPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        MegaList = new javax.swing.JList<>();
        DeleteWordButton = new javax.swing.JButton();
        MegaListLanguageComboBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TabbedPane.setPreferredSize(new java.awt.Dimension(600, 500));
        TabbedPane.setRequestFocusEnabled(false);

        WordPanel.setPreferredSize(new java.awt.Dimension(600, 400));

        languageJCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Russian", "English", "Ukrainian", "1337", "Dutch"}));
        languageJCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                languageJCBActionPerformed(evt);
            }
        });

        wordJTF.setText("geroi");
        wordJTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                wordJTFKeyPressed(evt);
            }
        });

        meaningsJLabel.setText("Meanings");

        collectionsJLabel.setText("Collections");

        meaningsLanguageJCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"English", "Russian", "Ukrainian", "1337", "Dutch"}));
        meaningsLanguageJCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meaningsLanguageJCBActionPerformed(evt);
            }
        });

        meaningsJL.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {"Hero", " "};

            public int getSize() {
                return strings.length;
            }

            public String getElementAt(int i) {
                return strings[i];
            }
        });
        jScrollPane1.setViewportView(meaningsJL);

        saveWordJB.setText("Save word");
        saveWordJB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveWordJBActionPerformed(evt);
            }
        });

        jScrollPane2.setPreferredSize(new java.awt.Dimension(280, 326));

        collectionsJT.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {"My words", null},
                        {"Lesson1", null},
                        {"Lesson2", null},
                        {null, null},
                        {null, null}
                },
                new String[]{
                        "Collection", ""
                }
        ) {
            Class[] types = new Class[]{
                    java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });
        collectionsJT.setPreferredSize(new java.awt.Dimension(286, 298));
        jScrollPane2.setViewportView(collectionsJT);

        separator.setOrientation(javax.swing.SwingConstants.VERTICAL);

        addMeaningJB.setText("Add meaning");
        addMeaningJB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMeaningJBActionPerformed(evt);
            }
        });

        createCollectionJB.setText("Add collection");
        createCollectionJB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createCollectionJBActionPerformed(evt);
            }
        });

        collectionJTF.setText("Collection");

        wordClassJLabel.setText("Class");

        wordClassJCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));

        meaningJTF.setText("meaning");

        deleteMeaningJB.setText("Delete meaning");
        deleteMeaningJB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteMeaningJBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout wordFoundIndidcatorLayout = new javax.swing.GroupLayout(wordFoundIndidcator);
        wordFoundIndidcator.setLayout(wordFoundIndidcatorLayout);
        wordFoundIndidcatorLayout.setHorizontalGroup(
                wordFoundIndidcatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 20, Short.MAX_VALUE)
        );
        wordFoundIndidcatorLayout.setVerticalGroup(
                wordFoundIndidcatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 29, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout WordPanelLayout = new javax.swing.GroupLayout(WordPanel);
        WordPanel.setLayout(WordPanelLayout);
        WordPanelLayout.setHorizontalGroup(
                WordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, WordPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(WordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(languageJCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(saveWordJB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, WordPanelLayout.createSequentialGroup()
                                                .addGroup(WordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(wordClassJLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(wordClassJCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, WordPanelLayout.createSequentialGroup()
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(WordPanelLayout.createSequentialGroup()
                                                                .addGroup(WordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(WordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                                .addComponent(meaningsLanguageJCB, javax.swing.GroupLayout.Alignment.LEADING, 0, 280, Short.MAX_VALUE)
                                                                                .addComponent(addMeaningJB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(meaningsJLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(meaningJTF))
                                                                        .addComponent(deleteMeaningJB, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(0, 0, Short.MAX_VALUE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(separator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(WordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(collectionJTF, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(WordPanelLayout.createSequentialGroup()
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addComponent(collectionsJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(createCollectionJB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, WordPanelLayout.createSequentialGroup()
                                                .addComponent(wordJTF)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(wordFoundIndidcator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(207, 207, 207))
        );
        WordPanelLayout.setVerticalGroup(
                WordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(WordPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(languageJCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(WordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(wordJTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(wordFoundIndidcator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(WordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(WordPanelLayout.createSequentialGroup()
                                                .addGap(2, 2, 2)
                                                .addGroup(WordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(WordPanelLayout.createSequentialGroup()
                                                                .addComponent(wordClassJLabel)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(wordClassJCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(meaningsJLabel)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(meaningsLanguageJCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jScrollPane1)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(deleteMeaningJB)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(meaningJTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(addMeaningJB))
                                                        .addGroup(WordPanelLayout.createSequentialGroup()
                                                                .addComponent(collectionsJLabel)
                                                                .addGap(1, 1, 1)
                                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(collectionJTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(createCollectionJB)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(WordPanelLayout.createSequentialGroup()
                                                .addGap(32, 32, 32)
                                                .addComponent(separator)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addComponent(saveWordJB, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        TabbedPane.addTab("Words", WordPanel);

        GroupsComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));

        AddCollectionTextField.setToolTipText("Collection name");

        AddCollectionButton.setText("Add collection");

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};

            public int getSize() {
                return strings.length;
            }

            public String getElementAt(int i) {
                return strings[i];
            }
        });
        GroupsWordList.setViewportView(jList1);

        javax.swing.GroupLayout CollectionsPanelLayout = new javax.swing.GroupLayout(CollectionsPanel);
        CollectionsPanel.setLayout(CollectionsPanelLayout);
        CollectionsPanelLayout.setHorizontalGroup(
                CollectionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(CollectionsPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(CollectionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(GroupsComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(CollectionsPanelLayout.createSequentialGroup()
                                                .addComponent(AddCollectionTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(AddCollectionButton))
                                        .addComponent(GroupsWordList))
                                .addContainerGap())
        );
        CollectionsPanelLayout.setVerticalGroup(
                CollectionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(CollectionsPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(CollectionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(AddCollectionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(AddCollectionButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(GroupsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(GroupsWordList, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                                .addContainerGap())
        );

        TabbedPane.addTab("Collections", CollectionsPanel);

        MegaList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};

            public int getSize() {
                return strings.length;
            }

            public String getElementAt(int i) {
                return strings[i];
            }
        });
        jScrollPane3.setViewportView(MegaList);

        DeleteWordButton.setText("Delete");

        MegaListLanguageComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));

        javax.swing.GroupLayout MegaListPanelLayout = new javax.swing.GroupLayout(MegaListPanel);
        MegaListPanel.setLayout(MegaListPanelLayout);
        MegaListPanelLayout.setHorizontalGroup(
                MegaListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(MegaListPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(MegaListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane3)
                                        .addComponent(DeleteWordButton, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
                                        .addComponent(MegaListLanguageComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        MegaListPanelLayout.setVerticalGroup(
                MegaListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(MegaListPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(MegaListLanguageComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(DeleteWordButton)
                                .addContainerGap())
        );

        TabbedPane.addTab("MegaList", MegaListPanel);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(TabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(TabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        TabbedPane.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deleteMeaningJBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteMeaningJBActionPerformed
        throw new UnsupportedOperationException();
    }//GEN-LAST:event_deleteMeaningJBActionPerformed

    private void addMeaningJBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMeaningJBActionPerformed
        throw new UnsupportedOperationException();
    }//GEN-LAST:event_addMeaningJBActionPerformed

    private void saveWordJBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveWordJBActionPerformed
        String word = this.wordJTF.getText();
        String language = (String) this.wordClassJCB.getSelectedItem();
        String wordClass = (String) this.languageJCB.getSelectedItem();
        try {
            this.userRequestManager.saveWordInformation(
                    word,
                    wordClass,
                    language
                    );
            this.userRequestManager.addMeanings(
                    word,
                    language,
                    extractMeaningsToBeAdded()
            );
            this.userRequestManager.removeMeanings(
                    word,
                    language,
                    extractMeaningsToBeDeleted()
            );
            this.userRequestManager.putInCollections(
                    word,
                    language,
                    extractCollectionsToBeAddedTo()
            );
            this.userRequestManager.removeFromCollections(
                    word,
                    language,
                    extractCollectionsToBeRemovedFrom()
            );
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_saveWordJBActionPerformed

    private Map<String, Collection<String>> extractMeaningsToBeAdded() {
        throw new UnsupportedOperationException();
    }

    private Map<String, Collection<String>> extractMeaningsToBeDeleted() {
        throw new UnsupportedOperationException();
    }

    private Collection<String> extractCollectionsToBeAddedTo() {
        throw new UnsupportedOperationException();
    }

    private Collection<String> extractCollectionsToBeRemovedFrom() {
        throw new UnsupportedOperationException();
    }


    private void createCollectionJBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createCollectionJBActionPerformed
        try {
            this.userRequestManager.createCollection(this.collectionJTF.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_createCollectionJBActionPerformed

    private void meaningsLanguageJCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meaningsLanguageJCBActionPerformed
        throw new UnsupportedOperationException();
    }//GEN-LAST:event_meaningsLanguageJCBActionPerformed

    private void languageJCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_languageJCBActionPerformed
        throw new UnsupportedOperationException();
    }//GEN-LAST:event_languageJCBActionPerformed

    private void wordJTFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_wordJTFKeyPressed
        //todo: this is test implementation, write normal implementation later
        if (wordJTF.getText().length() % 2 == 0) {
            wordFoundIndidcator.setColor(IndicatorLed.LedColor.GREEN);
        } else {
            wordFoundIndidcator.setColor(IndicatorLed.LedColor.RED);
        }
        wordFoundIndidcator.repaint();
    }//GEN-LAST:event_wordJTFKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InputWordsJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InputWordsJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InputWordsJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InputWordsJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InputWordsJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddCollectionButton;
    private javax.swing.JTextField AddCollectionTextField;
    private javax.swing.JPanel CollectionsPanel;
    private javax.swing.JButton DeleteWordButton;
    private javax.swing.JComboBox<String> GroupsComboBox;
    private javax.swing.JScrollPane GroupsWordList;
    private javax.swing.JList<String> MegaList;
    private javax.swing.JComboBox<String> MegaListLanguageComboBox;
    private javax.swing.JPanel MegaListPanel;
    private javax.swing.JTabbedPane TabbedPane;
    private javax.swing.JPanel WordPanel;
    private javax.swing.JButton addMeaningJB;
    private javax.swing.JTextField collectionJTF;
    private javax.swing.JLabel collectionsJLabel;
    private javax.swing.JTable collectionsJT;
    private javax.swing.JButton createCollectionJB;
    private javax.swing.JButton deleteMeaningJB;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JComboBox<String> languageJCB;
    private javax.swing.JTextField meaningJTF;
    private javax.swing.JList<String> meaningsJL;
    private javax.swing.JLabel meaningsJLabel;
    private javax.swing.JComboBox<String> meaningsLanguageJCB;
    private javax.swing.JButton saveWordJB;
    private javax.swing.JSeparator separator;
    private javax.swing.JComboBox<String> wordClassJCB;
    private javax.swing.JLabel wordClassJLabel;
    private com.sschudakov.ui.IndicatorLed wordFoundIndidcator;
    private javax.swing.JTextField wordJTF;
    // End of variables declaration//GEN-END:variables
    private UserRequestManager userRequestManager;

}
