/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sschudakov.ui;

import com.sschudakov.database.DatabaseManager;
import com.sschudakov.factory.UserRequestManagerFactory;
import com.sschudakov.request.UserRequestManager;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Semen
 */
public class InputWordsJFrame extends javax.swing.JFrame {

    private boolean wordFound = false; //true if entered by user word is found in database

    /**
     * Creates new form InputWrodsJFrame
     */
    public InputWordsJFrame() {
        this.userRequestManager = UserRequestManagerFactory.createRequestManager();

        initComponents();

        wordsCollectionsJT.getColumnModel().getColumn(0).setMinWidth(220);
        wordsCollectionsJT.getColumnModel().getColumn(1).setMinWidth(60);
        wordsCollectionsJT.getColumnModel().getColumn(1).setPreferredWidth(60);
        wordsCollectionsJT.getColumnModel().getColumn(0).setPreferredWidth(220);

        //-------------- JTF listeners ---------------//

        wordsWordValueJTF.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                wordsInputWordValueOrLanguageChanged();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                wordsInputWordValueOrLanguageChanged();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                wordsInputWordValueOrLanguageChanged();
            }
        });

        wordsMeaningJTF.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                wordsInputMeaningValueOrLanguageChanged();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                wordsInputMeaningValueOrLanguageChanged();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                wordsInputMeaningValueOrLanguageChanged();
            }
        });

        wordsCollectionNameJTF.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                wordsInputCollectionsNameChanged();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                wordsInputCollectionsNameChanged();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                wordsInputCollectionsNameChanged();
            }
        });

        collectionsCollectionNameJTF.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                collectionsInputCollectionNameChanged();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                collectionsInputCollectionNameChanged();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                collectionsInputCollectionNameChanged();
            }
        });


        wordsMeaningsJL.setModel(new DefaultListModel<>());
        languagesWordsJL.setModel(new DefaultListModel<>());
        collectionsWordsJL.setModel(new DefaultListModel<>());

        wordsCollectionsJT.getModel().addTableModelListener(e -> {
            try {
                if (e.getColumn() == 1) {
                    int row = e.getFirstRow();
                    TableModel model = (TableModel) e.getSource();
                    boolean checked = (Boolean) model.getValueAt(row, 1);
                    String collection = (String) model.getValueAt(row, 0);
                    if (checked) {
                        userRequestManager.putInCollection(wordsGetInputWordValue(), wordsGetSelectedWordLanguage(), collection);
                    } else {
                        userRequestManager.removeFromCollection(wordsGetInputWordValue(), wordsGetSelectedWordLanguage(), collection);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                //todo: showMessageDialog?
            }
        });

        wordsMeaningsJL.addListSelectionListener(e -> wordsSelectedMeaningValueChanged());
        collectionsWordsJL.addListSelectionListener(e -> collectionsSelectedWordValueChanged());

        collectionsCreateCollectionJB.setEnabled(false);

        this.setFoundStatus(false);

        this.loadLanguages(wordsLanguageJCB);
        this.loadLanguages(languagesWordLanguageJCB);
        this.loadLanguages(languagesMeaningsLanguageJCB);
        this.loadLanguages(collectionsLanguageJCB);
        this.loadWordClasses();

        this.collectionsLoadCollections();

        wordsWordValueJTF.setHint("Word");
        wordsMeaningJTF.setHint("Meaning");
        wordsCollectionNameJTF.setHint("Collection name");
        collectionsCollectionNameJTF.setHint("Collection name");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        TabbedPane = new javax.swing.JTabbedPane();
        WordPanel = new javax.swing.JPanel();
        wordsLanguageJCB = new javax.swing.JComboBox<>();
        wordsWordValueJTF = new com.sschudakov.ui.HintTextField();
        meaningsJLabel = new javax.swing.JLabel();
        wordCollectionsJL = new javax.swing.JLabel();
        wordsMeaningsLanguageJCB = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        wordsMeaningsJL = new javax.swing.JList<>();
        wordsSaveWordJB = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        wordsCollectionsJT = new javax.swing.JTable();
        separator = new javax.swing.JSeparator();
        wordsAddMeaningJB = new javax.swing.JButton();
        wordsCreateCollectionJB = new javax.swing.JButton();
        wordsCollectionNameJTF = new com.sschudakov.ui.HintTextField();
        wordsWordClassJCB = new javax.swing.JComboBox<>();
        wordsMeaningJTF = new com.sschudakov.ui.HintTextField();
        wordsDeleteMeaningJB = new javax.swing.JButton();
        wordFoundIndidcator = new com.sschudakov.ui.IndicatorLed();
        wordsDeleteWordJB = new javax.swing.JButton();
        wordsLanguageJL = new javax.swing.JLabel();
        wordsWordClassJL = new javax.swing.JLabel();
        wordsMeaningsLanguageJL = new javax.swing.JLabel();
        CollectionsPanel = new javax.swing.JPanel();
        collectionsCollectionNameJCB = new javax.swing.JComboBox<>();
        collectionsCollectionNameJTF = new com.sschudakov.ui.HintTextField();
        collectionsCreateCollectionJB = new javax.swing.JButton();
        GroupsWordList = new javax.swing.JScrollPane();
        collectionsWordsJL = new javax.swing.JList<>();
        collectionDeleteCollectionJB = new javax.swing.JButton();
        collectionsDeleteWordsJB = new javax.swing.JButton();
        collectionsLanguageJCB = new javax.swing.JComboBox<>();
        collectionsCollectionJLabel = new javax.swing.JLabel();
        collectionsLanguageJLabel = new javax.swing.JLabel();
        LanguagesPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        languagesWordsJL = new javax.swing.JList<>();
        languagesWordLanguageJCB = new javax.swing.JComboBox<>();
        languagesRemoveWordsJB = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        languagesWordsJT = new javax.swing.JTable();
        languagesMeaningsLanguageJCB = new javax.swing.JComboBox<>();
        languagesWordLanguageJLabel = new javax.swing.JLabel();
        languagesMeaningsLanguageJLabel = new javax.swing.JLabel();

        jLabel3.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        TabbedPane.setPreferredSize(new java.awt.Dimension(600, 500));
        TabbedPane.setRequestFocusEnabled(false);
        TabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                TabbedPaneStateChanged(evt);
            }
        });

        WordPanel.setPreferredSize(new java.awt.Dimension(600, 500));

        wordsLanguageJCB.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                wordsLanguageJCBItemStateChanged(evt);
            }
        });

        meaningsJLabel.setText("Meanings");

        wordCollectionsJL.setText("Collections");

        wordsMeaningsLanguageJCB.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                wordsMeaningsLanguageJCBItemStateChanged(evt);
            }
        });

        jScrollPane1.setViewportView(wordsMeaningsJL);

        wordsSaveWordJB.setText("Save word");
        wordsSaveWordJB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wordsSaveWordJBActionPerformed(evt);
            }
        });

        jScrollPane2.setPreferredSize(new java.awt.Dimension(280, 326));

        wordsCollectionsJT.setModel(new javax.swing.table.DefaultTableModel(
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
        wordsCollectionsJT.setPreferredSize(new java.awt.Dimension(286, 298));
        jScrollPane2.setViewportView(wordsCollectionsJT);

        separator.setOrientation(javax.swing.SwingConstants.VERTICAL);

        wordsAddMeaningJB.setText("Add meaning");
        wordsAddMeaningJB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wordsAddMeaningJBActionPerformed(evt);
            }
        });

        wordsCreateCollectionJB.setText("Create collection");
        wordsCreateCollectionJB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wordsCreateCollectionJBActionPerformed(evt);
            }
        });

        wordsWordClassJCB.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                wordsWordClassJCBItemStateChanged(evt);
            }
        });

        wordsDeleteMeaningJB.setText("Remove meaning");
        wordsDeleteMeaningJB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wordsDeleteMeaningJBActionPerformed(evt);
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

        wordsDeleteWordJB.setText("Delete word");
        wordsDeleteWordJB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wordsDeleteWordJBActionPerformed(evt);
            }
        });

        wordsLanguageJL.setText("Language");

        wordsWordClassJL.setText("Word class");

        wordsMeaningsLanguageJL.setText("Meanings language");

        javax.swing.GroupLayout WordPanelLayout = new javax.swing.GroupLayout(WordPanel);
        WordPanel.setLayout(WordPanelLayout);
        WordPanelLayout.setHorizontalGroup(
                WordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(WordPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(WordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, WordPanelLayout.createSequentialGroup()
                                                .addComponent(wordsLanguageJL, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(WordPanelLayout.createSequentialGroup()
                                                .addComponent(wordsWordValueJTF)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(wordFoundIndidcator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(wordsLanguageJCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(WordPanelLayout.createSequentialGroup()
                                                .addGroup(WordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(meaningsJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(wordsMeaningsLanguageJCB, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(WordPanelLayout.createSequentialGroup()
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addComponent(wordsDeleteMeaningJB, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(wordsMeaningsLanguageJL, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(wordsWordClassJL, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(wordsWordClassJCB, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(wordsSaveWordJB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(wordsMeaningJTF, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(wordsAddMeaningJB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(separator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(WordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(WordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                .addComponent(wordsDeleteWordJB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(wordsCollectionNameJTF, javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(wordsCreateCollectionJB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addComponent(wordCollectionsJL, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(186, 186, 186))
        );
        WordPanelLayout.setVerticalGroup(
                WordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(WordPanelLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(WordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(separator, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(WordPanelLayout.createSequentialGroup()
                                                .addComponent(wordsLanguageJL, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(wordsLanguageJCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(WordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(wordsWordValueJTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(wordFoundIndidcator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(2, 2, 2)
                                                .addGroup(WordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(wordsDeleteWordJB)
                                                        .addComponent(wordsSaveWordJB))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(WordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(wordCollectionsJL, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(wordsWordClassJL, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(WordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addGroup(WordPanelLayout.createSequentialGroup()
                                                                .addGap(8, 8, 8)
                                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(8, 8, 8)
                                                                .addComponent(wordsCollectionNameJTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(wordsCreateCollectionJB))
                                                        .addGroup(WordPanelLayout.createSequentialGroup()
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(wordsWordClassJCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(wordsMeaningsLanguageJL, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(wordsMeaningsLanguageJCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(meaningsJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jScrollPane1)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(wordsDeleteMeaningJB)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(wordsMeaningJTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(wordsAddMeaningJB)))))
                                .addContainerGap(18, Short.MAX_VALUE))
        );

        TabbedPane.addTab("Words", WordPanel);

        collectionsCollectionNameJCB.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                collectionsCollectionNameJCBItemStateChanged(evt);
            }
        });

        collectionsCollectionNameJTF.setToolTipText("Collection name");

        collectionsCreateCollectionJB.setText("Create collection");
        collectionsCreateCollectionJB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                collectionsCreateCollectionJBActionPerformed(evt);
            }
        });

        GroupsWordList.setViewportView(collectionsWordsJL);

        collectionDeleteCollectionJB.setText("Delete collection");
        collectionDeleteCollectionJB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                collectionDeleteCollectionJBActionPerformed(evt);
            }
        });

        collectionsDeleteWordsJB.setText("Remove word");
        collectionsDeleteWordsJB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                collectionsDeleteWordsJBActionPerformed(evt);
            }
        });

        collectionsLanguageJCB.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                collectionsLanguageJCBItemStateChanged(evt);
            }
        });

        collectionsCollectionJLabel.setText("Collection");

        collectionsLanguageJLabel.setText("Language");

        javax.swing.GroupLayout CollectionsPanelLayout = new javax.swing.GroupLayout(CollectionsPanel);
        CollectionsPanel.setLayout(CollectionsPanelLayout);
        CollectionsPanelLayout.setHorizontalGroup(
                CollectionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(CollectionsPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(CollectionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(CollectionsPanelLayout.createSequentialGroup()
                                                .addComponent(collectionsCollectionNameJTF, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(collectionsCreateCollectionJB, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
                                        .addComponent(GroupsWordList)
                                        .addGroup(CollectionsPanelLayout.createSequentialGroup()
                                                .addComponent(collectionDeleteCollectionJB, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(10, 10, 10)
                                                .addComponent(collectionsDeleteWordsJB, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
                                        .addGroup(CollectionsPanelLayout.createSequentialGroup()
                                                .addGroup(CollectionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(CollectionsPanelLayout.createSequentialGroup()
                                                                .addComponent(collectionsCollectionJLabel)
                                                                .addGap(247, 247, 247))
                                                        .addGroup(CollectionsPanelLayout.createSequentialGroup()
                                                                .addComponent(collectionsCollectionNameJCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGap(10, 10, 10)))
                                                .addGroup(CollectionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(CollectionsPanelLayout.createSequentialGroup()
                                                                .addComponent(collectionsLanguageJLabel)
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                        .addComponent(collectionsLanguageJCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addContainerGap())
        );
        CollectionsPanelLayout.setVerticalGroup(
                CollectionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(CollectionsPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(CollectionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(collectionsCollectionNameJTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(collectionsCreateCollectionJB))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(CollectionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(collectionsCollectionJLabel)
                                        .addComponent(collectionsLanguageJLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(CollectionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(collectionsLanguageJCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(collectionsCollectionNameJCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(CollectionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(collectionDeleteCollectionJB)
                                        .addComponent(collectionsDeleteWordsJB))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(GroupsWordList, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                                .addContainerGap())
        );

        TabbedPane.addTab("Collections", CollectionsPanel);

        jScrollPane3.setViewportView(languagesWordsJL);

        languagesWordLanguageJCB.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                languagesWordLanguageJCBItemStateChanged(evt);
            }
        });

        languagesRemoveWordsJB.setText("Remove word");
        languagesRemoveWordsJB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                languagesRemoveWordsJBActionPerformed(evt);
            }
        });

        languagesWordsJT.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "Word", "Meanings"
                }
        ) {
            Class[] types = new Class[]{
                    java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                    false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jScrollPane4.setViewportView(languagesWordsJT);

        languagesMeaningsLanguageJCB.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                languagesMeaningsLanguageJCBItemStateChanged(evt);
            }
        });

        languagesWordLanguageJLabel.setText("Word language");

        languagesMeaningsLanguageJLabel.setText("Meanings language");

        javax.swing.GroupLayout LanguagesPanelLayout = new javax.swing.GroupLayout(LanguagesPanel);
        LanguagesPanel.setLayout(LanguagesPanelLayout);
        LanguagesPanelLayout.setHorizontalGroup(
                LanguagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(LanguagesPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(LanguagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
                                        .addComponent(jScrollPane3)
                                        .addGroup(LanguagesPanelLayout.createSequentialGroup()
                                                .addGroup(LanguagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(LanguagesPanelLayout.createSequentialGroup()
                                                                .addGroup(LanguagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(languagesRemoveWordsJB, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(languagesWordLanguageJLabel))
                                                                .addGap(34, 34, 34))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LanguagesPanelLayout.createSequentialGroup()
                                                                .addComponent(languagesWordLanguageJCB, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                                .addGroup(LanguagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(LanguagesPanelLayout.createSequentialGroup()
                                                                .addComponent(languagesMeaningsLanguageJLabel)
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                        .addComponent(languagesMeaningsLanguageJCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addContainerGap())
        );
        LanguagesPanelLayout.setVerticalGroup(
                LanguagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(LanguagesPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(LanguagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(languagesWordLanguageJLabel)
                                        .addComponent(languagesMeaningsLanguageJLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(LanguagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(languagesMeaningsLanguageJCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(languagesWordLanguageJCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(languagesRemoveWordsJB)
                                .addContainerGap())
        );

        TabbedPane.addTab("Languages", LanguagesPanel);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(TabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
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
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    //-------------- Set found status method ---------------//

    private void setFoundStatus(boolean found) {
        wordFound = found;

        if (found) {
            wordFoundIndidcator.setColor(IndicatorLed.LedColor.GREEN);
        } else {
            wordFoundIndidcator.setColor(IndicatorLed.LedColor.RED);
        }

        wordFoundIndidcator.repaint();


        wordsSaveWordJB.setEnabled(!found && !wordsGetInputWordValue().isEmpty());
        wordsDeleteWordJB.setEnabled(found);

        wordsWordClassJCB.setEnabled(found);

        wordsCollectionNameJTF.setEnabled(found);
        wordsMeaningJTF.setEnabled(found);

        wordsCollectionNameJTF.clear();
        wordsMeaningJTF.clear();

        if (!found) {
            wordsWordClassJCB.setSelectedIndex(-1);
            ((DefaultTableModel) wordsCollectionsJT.getModel()).setRowCount(0);
            ((DefaultListModel) wordsMeaningsJL.getModel()).setSize(0);

            wordsAddMeaningJB.setEnabled(false);
            wordsCreateCollectionJB.setEnabled(false);
            wordsDeleteMeaningJB.setEnabled(false);
        }
        wordsMeaningsLanguageJCB.setEnabled(found);

        wordsCollectionsJT.setEnabled(found);

        wordsMeaningsJL.setEnabled(found);
        jScrollPane1.setEnabled(found);
        jScrollPane2.setEnabled(found);
    }


    //-------------- word value or language changed method ---------------//

    private void wordsInputWordValueOrLanguageChanged() {
        String language = wordsGetSelectedWordLanguage();
        if (language != null) {
            try {
                if (userRequestManager.wordExists(wordsGetInputWordValue(), language)) {
                    loadWordData();
                    setFoundStatus(true);
                } else {
                    setFoundStatus(false);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private void wordsInputMeaningValueOrLanguageChanged() {
        String meaningValue = wordsGetInputMeaningValue();
        String meaningLanguage = wordsGetSelectedMeaningsLanguage();
        try {
            if (meaningValue.length() == 0 || !userRequestManager.wordExists(meaningValue, meaningLanguage)) {
                wordsAddMeaningJB.setEnabled(false);
            } else {
                wordsAddMeaningJB.setEnabled(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void wordsInputCollectionsNameChanged() {
        String collectionName = wordsGetInputCollectionName();
        try {
            if (collectionName.length() == 0 || userRequestManager.wordCollectionExists(collectionName)) {
                wordsCreateCollectionJB.setEnabled(false);
            } else {
                wordsCreateCollectionJB.setEnabled(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void wordsSelectedMeaningValueChanged() {
        if (wordsMeaningsJL.getSelectedValue() != null) {
            wordsDeleteMeaningJB.setEnabled(true);
        } else {
            wordsDeleteMeaningJB.setEnabled(false);
        }
    }

    private void collectionsSelectedWordValueChanged() {
        if (collectionsWordsJL.getSelectedValue() != null) {
            collectionsDeleteWordsJB.setEnabled(true);
        } else {
            collectionsDeleteWordsJB.setEnabled(false);
        }
    }

    private void collectionsInputCollectionNameChanged() {
        String collectionName = collectionsCollectionNameJTF.getText();//todo:
        try {
            if (collectionName.isEmpty() || userRequestManager.wordCollectionExists(collectionName)) {
                collectionsCreateCollectionJB.setEnabled(false);
            } else {
                collectionsCreateCollectionJB.setEnabled(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //-------------- Load methods ---------------//

    private void loadLanguages(JComboBox<String> languagesJcb) {
        languagesJcb.removeAllItems();
        try {
            for (String language : userRequestManager.getLanguages()) {
                languagesJcb.addItem(language);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }

    private void loadWordClasses() {
        wordsWordClassJCB.removeAllItems();
        try {
            for (String s : userRequestManager.getClasses()) {
                wordsWordClassJCB.addItem(s);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }


    private void loadWordData() {
        try {
            String word = wordsGetInputWordValue();
            String language = wordsGetSelectedWordLanguage();
            String wordClass = this.userRequestManager.getWordClassByWord(word, language);

            if (wordClass != null) {
                wordsWordClassJCB.setSelectedItem(wordClass);
            } else {
                wordsWordClassJCB.setSelectedIndex(-1);
            }

            this.loadMeaningsLanguages();
            this.loadMeaningsList(word, language);
            this.loadCollections(word, language);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }

    private void loadMeaningsLanguages() {
        wordsMeaningsLanguageJCB.removeAllItems();
        String selectedLanguage = wordsGetSelectedWordLanguage();
        try {
            for (String language : userRequestManager.getLanguages()) {
                if (!language.equals(selectedLanguage)) {
                    wordsMeaningsLanguageJCB.addItem(language);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }

    private void loadMeaningsList(String word, String language) {
        try {
            String meaningsLanguage = wordsGetSelectedMeaningsLanguage();
            if (meaningsLanguage != null) {
                List<String> meaningsList = this.userRequestManager.getWordMeanings(word, language, meaningsLanguage);
                DefaultListModel<String> model = (DefaultListModel<String>) wordsMeaningsJL.getModel();
                model.setSize(0);
                for (String meaning : meaningsList) {
                    model.addElement(meaning);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }

    private void loadCollections(String word, String language) {
        try {
            Map<String, Boolean> collections = this.userRequestManager.getWordCollections(word, language);
            DefaultTableModel model = (DefaultTableModel) wordsCollectionsJT.getModel();
            model.setRowCount(0);//TODO I am not sure that it is necessary
            for (Map.Entry<String, Boolean> collectionData : collections.entrySet()) {
                model.addRow(new Object[]{collectionData.getKey(), collectionData.getValue()});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //-------------- Access to ui elements ---------------//


    private String wordsGetInputWordValue() {
        return wordsWordValueJTF.getText();
    }

    private String wordsGetSelectedWordLanguage() {
        return (String) wordsLanguageJCB.getSelectedItem();
    }

    private String wordsGetSelectedWordClass() {
        return (String) this.wordsWordClassJCB.getSelectedItem();
    }

    private String wordsGetInputMeaningValue() {
        return this.wordsMeaningJTF.getText();
    }

    private String wordsGetSelectedMeaningValue() {
        return wordsMeaningsJL.getSelectedValue();
    }

    private String wordsGetSelectedMeaningsLanguage() {
        return (String) wordsMeaningsLanguageJCB.getSelectedItem();
    }

    private String wordsGetInputCollectionName() {
        return this.wordsCollectionNameJTF.getText();
    }

    private String collectionsGetInputCollectionName() {
        return collectionsCollectionNameJTF.getText();
    }

    private String collectionsGetSelectedLanguage() {
        return (String) collectionsLanguageJCB.getSelectedItem();
    }

    private String collectionsGetSelectedCollectionName() {
        return (String) collectionsCollectionNameJCB.getSelectedItem();
    }

    private String languagesGetSelectedWordsLanguage() {
        return (String) languagesWordLanguageJCB.getSelectedItem();
    }

    private String languagesGetSelectedMeaningsLanguage() {
        return (String) languagesMeaningsLanguageJCB.getSelectedItem();
    }

    //-------------- Words Tab ---------------//


    private void wordsSaveWordJBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wordsSaveWordJBActionPerformed
        try {
            this.userRequestManager.createWord(
                    wordsGetInputWordValue(),
                    wordsGetSelectedWordClass(),
                    wordsGetSelectedWordLanguage()
            );
            this.wordsInputWordValueOrLanguageChanged();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_wordsSaveWordJBActionPerformed

    private void wordsDeleteWordJBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wordsDeleteWordJBActionPerformed
        String message =
                "Are you sure you want to delete word \"" +
                        wordsGetInputWordValue() +
                        "\"? All relative information will also be deleted.";
        if (JOptionPane.showConfirmDialog(this, message) == JOptionPane.YES_OPTION) {
            try {
                this.userRequestManager.deleteWord(
                        wordsGetInputWordValue(),
                        wordsGetSelectedWordLanguage()
                );
                this.wordsInputWordValueOrLanguageChanged();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }//GEN-LAST:event_wordsDeleteWordJBActionPerformed


    private void wordsAddMeaningJBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wordsAddMeaningJBActionPerformed
        try {
            this.userRequestManager.addMeaning(
                    wordsGetInputWordValue(),
                    wordsGetSelectedWordLanguage(),
                    wordsGetInputMeaningValue(),
                    wordsGetSelectedMeaningsLanguage()
            );
            addMeaningToMeaningsList(wordsGetInputMeaningValue());
            wordsMeaningJTF.clear();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_wordsAddMeaningJBActionPerformed

    private void wordsDeleteMeaningJBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wordsDeleteMeaningJBActionPerformed
        if (wordsGetSelectedMeaningValue() != null) {
            try {
                this.userRequestManager.removeMeaning(
                        wordsGetInputWordValue(),
                        wordsGetSelectedWordLanguage(),
                        wordsGetSelectedMeaningValue(),
                        wordsGetSelectedMeaningsLanguage()
                );
                removeMeaningFromMeaningsList(wordsGetSelectedMeaningValue());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
                e.printStackTrace();
            }
        } else {
            String message = "Please, select the value you want to delete from the list";
            JOptionPane.showMessageDialog(this, message);
        }
    }//GEN-LAST:event_wordsDeleteMeaningJBActionPerformed

    private void addMeaningToMeaningsList(String meaning) {
        ((DefaultListModel<String>) wordsMeaningsJL.getModel()).insertElementAt(meaning, 0);
        //todo: insert in front because database loads in reversed order
    }

    private void removeMeaningFromMeaningsList(String meaning) {
        ((DefaultListModel<String>) wordsMeaningsJL.getModel()).removeElement(meaning);
    }


    private void wordsCreateCollectionJBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wordsCreateCollectionJBActionPerformed
        try {
            this.userRequestManager.createCollection(wordsGetInputCollectionName());
            addCollectionToCollectionsTable(wordsGetInputCollectionName());
            collectionsAddCollectionToJCB(wordsGetInputCollectionName());
            wordsCollectionNameJTF.clear();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_wordsCreateCollectionJBActionPerformed

    private void addCollectionToCollectionsTable(String collectionsName) {
        ((DefaultTableModel) wordsCollectionsJT.getModel()).insertRow(0, new Object[]{collectionsName, false});
    }

    private void wordsMeaningsLanguageJCBItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_wordsMeaningsLanguageJCBItemStateChanged
        if (this.wordFound) {
            try {
                this.loadMeaningsList(
                        wordsGetInputWordValue(),
                        wordsGetSelectedWordLanguage()
                );
                wordsInputMeaningValueOrLanguageChanged();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_wordsMeaningsLanguageJCBItemStateChanged

    private void wordsLanguageJCBItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_wordsLanguageJCBItemStateChanged
        this.wordsInputWordValueOrLanguageChanged();
    }//GEN-LAST:event_wordsLanguageJCBItemStateChanged

    private void wordsWordClassJCBItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_wordsWordClassJCBItemStateChanged
        if (wordFound && wordsGetSelectedWordClass() != null) {
            try {
                this.userRequestManager.updateWord(
                        wordsGetInputWordValue(),
                        wordsGetSelectedWordClass(),
                        wordsGetSelectedWordLanguage()
                );
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_wordsWordClassJCBItemStateChanged


    //-------------- Collections Tab ---------------//


    private void collectionsCreateCollectionJBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_collectionsCreateCollectionJBActionPerformed
        try {
            String collection = collectionsGetInputCollectionName();
            this.userRequestManager.createCollection(collection);
            collectionsAddCollectionToJCB(collection);
            collectionsCollectionNameJTF.clear();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_collectionsCreateCollectionJBActionPerformed

    private void collectionDeleteCollectionJBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_collectionDeleteCollectionJBActionPerformed
        String message =
                "Are you sure you want to delete collection \"" +
                        collectionsGetSelectedCollectionName() +
                        "\"? All relative information will also be deleted.";
        if (JOptionPane.showConfirmDialog(this, message) == JOptionPane.YES_OPTION) {
            try {
                this.userRequestManager.deleteCollection(collectionsGetSelectedCollectionName());
                collectionsLoadCollections();
                collectionsInputCollectionNameChanged();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }//GEN-LAST:event_collectionDeleteCollectionJBActionPerformed

    private void collectionsDeleteWordsJBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_collectionsDeleteWordsJBActionPerformed
        String word = collectionsWordsJL.getSelectedValue();
        String language = collectionsGetSelectedLanguage();
        String collection = collectionsGetSelectedCollectionName();
        if (word != null && language != null && collection != null) {
            try {
                this.userRequestManager.removeFromCollection(
                        word,
                        language,
                        collection
                );
                ((DefaultListModel) collectionsWordsJL.getModel()).removeElement(word);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }//GEN-LAST:event_collectionsDeleteWordsJBActionPerformed

    private void collectionsCollectionNameJCBItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_collectionsCollectionNameJCBItemStateChanged
        collectionsLoadWordsForCollection();
    }//GEN-LAST:event_collectionsCollectionNameJCBItemStateChanged

    private void collectionsLanguageJCBItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_collectionsLanguageJCBItemStateChanged
        collectionsLoadWordsForCollection();
    }//GEN-LAST:event_collectionsLanguageJCBItemStateChanged

    private void collectionsAddCollectionToJCB(String collection) {
        collectionsInputCollectionNameChanged();
        collectionsCollectionNameJCB.insertItemAt(collection, 0);
        if (collectionsCollectionNameJCB.getSelectedIndex() == -1) {
            collectionsCollectionNameJCB.setSelectedIndex(0);
        }
        collectionDeleteCollectionJB.setEnabled(true);
        //collectionsCollectionNameJCB.setSelectedIndex(collectionsCollectionNameJCB.getSelectedIndex() + 1);
    }

    private void collectionsLoadCollections() {
        collectionsCollectionNameJCB.removeAllItems();
        collectionDeleteCollectionJB.setEnabled(false);
        try {
            for (String collection : userRequestManager.getCollections()) {
                collectionsCollectionNameJCB.addItem(collection);
                collectionDeleteCollectionJB.setEnabled(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void collectionsLoadWordsForCollection() {
        String collection = collectionsGetSelectedCollectionName();
        String language = collectionsGetSelectedLanguage();
        collectionsDeleteWordsJB.setEnabled(false);
        DefaultListModel model = (DefaultListModel) collectionsWordsJL.getModel();
        model.setSize(0);
        if (collection != null && language != null) {
            try {
                for (String word : userRequestManager.getCollectionWords(collection, language)) {
                    model.addElement(word);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //-------------- Languages Tab ---------------//


    private void languagesWordLanguageJCBItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_languagesWordLanguageJCBItemStateChanged
        languagesLoadWordsAndMeanings();
    }//GEN-LAST:event_languagesWordLanguageJCBItemStateChanged

    private void languagesRemoveWordsJBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_languagesRemoveWordsJBActionPerformed
        String message = "Are you sure you want to delete selected words? " +
                "All relative information will also be deleted.";

        if (JOptionPane.showConfirmDialog(this, message) == JOptionPane.YES_OPTION) {
            String language = (String) this.languagesWordLanguageJCB.getSelectedItem();
            try {
                for (String word : languagesExtractSelectedWords()) {
                    this.userRequestManager.deleteWord(word, language);
                }
                DefaultTableModel model = ((DefaultTableModel) languagesWordsJT.getModel());
                int[] rows = languagesWordsJT.getSelectedRows();
                for (int i = 0; i < rows.length; i++) {
                    model.removeRow(rows[i] - i);
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }//GEN-LAST:event_languagesRemoveWordsJBActionPerformed

    private List<String> languagesExtractSelectedWords() {
        List<String> selectedWords = new ArrayList<>();
        for (int i : languagesWordsJT.getSelectedRows()) {
            selectedWords.add((String) languagesWordsJT.getValueAt(i, 0));
        }
        return selectedWords;
    }

    private void languagesMeaningsLanguageJCBItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_languagesMeaningsLanguageJCBItemStateChanged
        languagesLoadWordsAndMeanings();
    }//GEN-LAST:event_languagesMeaningsLanguageJCBItemStateChanged

    private void languagesLoadWordsAndMeanings() {
        DefaultTableModel model = (DefaultTableModel) languagesWordsJT.getModel();
        model.setRowCount(0);
        String delimiter = ", ";
        String wordsLanguage = languagesGetSelectedWordsLanguage();
        String meaningsLanguage = languagesGetSelectedMeaningsLanguage();
        if (wordsLanguage != null && meaningsLanguage != null) {
            try {
                for (String word : userRequestManager.getWordsByLanguageName(languagesGetSelectedWordsLanguage())) {
                    String meanings = String.join(delimiter, userRequestManager.getWordMeanings(
                            word,
                            wordsLanguage,
                            meaningsLanguage
                    ));
                    model.addRow(new Object[]{word, meanings});
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //-------------- Tabbed Pane ------------------//

    private void TabbedPaneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_TabbedPaneStateChanged
        if (TabbedPane.getSelectedIndex() == 0) {
            wordsInputWordValueOrLanguageChanged();
        }
        if (TabbedPane.getSelectedIndex() == 1) {
            collectionsLoadWordsForCollection();
        }
        if (TabbedPane.getSelectedIndex() == 2) {
            languagesLoadWordsAndMeanings();
        }
    }//GEN-LAST:event_TabbedPaneStateChanged


    //-------------- Window closing action ---------------//

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            DatabaseManager.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        this.setVisible(false);
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CollectionsPanel;
    private javax.swing.JScrollPane GroupsWordList;
    private javax.swing.JPanel LanguagesPanel;
    private javax.swing.JTabbedPane TabbedPane;
    private javax.swing.JPanel WordPanel;
    private javax.swing.JButton collectionDeleteCollectionJB;
    private javax.swing.JLabel collectionsCollectionJLabel;
    private javax.swing.JComboBox<String> collectionsCollectionNameJCB;
    private com.sschudakov.ui.HintTextField collectionsCollectionNameJTF;
    private javax.swing.JButton collectionsCreateCollectionJB;
    private javax.swing.JButton collectionsDeleteWordsJB;
    private javax.swing.JComboBox<String> collectionsLanguageJCB;
    private javax.swing.JLabel collectionsLanguageJLabel;
    private javax.swing.JList<String> collectionsWordsJL;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JComboBox<String> languagesMeaningsLanguageJCB;
    private javax.swing.JLabel languagesMeaningsLanguageJLabel;
    private javax.swing.JButton languagesRemoveWordsJB;
    private javax.swing.JComboBox<String> languagesWordLanguageJCB;
    private javax.swing.JLabel languagesWordLanguageJLabel;
    private javax.swing.JList<String> languagesWordsJL;
    private javax.swing.JTable languagesWordsJT;
    private javax.swing.JLabel meaningsJLabel;
    private javax.swing.JSeparator separator;
    private javax.swing.JLabel wordCollectionsJL;
    private com.sschudakov.ui.IndicatorLed wordFoundIndidcator;
    private javax.swing.JButton wordsAddMeaningJB;
    private com.sschudakov.ui.HintTextField wordsCollectionNameJTF;
    private javax.swing.JTable wordsCollectionsJT;
    private javax.swing.JButton wordsCreateCollectionJB;
    private javax.swing.JButton wordsDeleteMeaningJB;
    private javax.swing.JButton wordsDeleteWordJB;
    private javax.swing.JComboBox<String> wordsLanguageJCB;
    private javax.swing.JLabel wordsLanguageJL;
    private com.sschudakov.ui.HintTextField wordsMeaningJTF;
    private javax.swing.JList<String> wordsMeaningsJL;
    private javax.swing.JComboBox<String> wordsMeaningsLanguageJCB;
    private javax.swing.JLabel wordsMeaningsLanguageJL;
    private javax.swing.JButton wordsSaveWordJB;
    private javax.swing.JComboBox<String> wordsWordClassJCB;
    private javax.swing.JLabel wordsWordClassJL;
    private com.sschudakov.ui.HintTextField wordsWordValueJTF;
    // End of variables declaration//GEN-END:variables
    private UserRequestManager userRequestManager;
}
