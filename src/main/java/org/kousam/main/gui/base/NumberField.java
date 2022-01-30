package org.kousam.main.gui.base;



import javafx.geometry.Pos;
import javafx.scene.control.TextField;

public class NumberField extends TextField{


    public NumberField(){
        super();

        this.setAlignment(Pos.BASELINE_RIGHT);
    }

    @Override
    public void replaceText(int start, int end, String text)
    {
        if (validate(text))
        {
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String text)
    {
        if (validate(text))
        {
            super.replaceSelection(text);
        }
    }

    private boolean validate(String text)
    {
        return text.matches("[0-9.]*");
    }


}