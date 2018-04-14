package com.sschudakov.ui.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class HintTextField extends JTextField implements FocusListener {

    private String hint;
    private boolean showingHint;

    public HintTextField() {
        super();
        this.hint = "";
        this.showingHint = true;
        super.setForeground(Color.GRAY);
        super.addFocusListener(this);
    }

    public void setHint(String hint) {
        this.hint = hint;
        super.setText(hint);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (this.getText().isEmpty()) {
            showingHint = false;
            super.setText("");
            super.setForeground(Color.BLACK);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (this.getText().isEmpty()) {
            clear();
        }
    }

    @Override
    public String getText() {
        return showingHint ? "" : super.getText();
    }

    public void clear() {
        showingHint = true;
        super.setText(hint);
        super.setForeground(Color.GRAY);
    }
}