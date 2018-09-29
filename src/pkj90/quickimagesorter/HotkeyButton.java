package pkj90.quickimagesorter;

import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;

public class HotkeyButton extends Button
{
    private KeyCode keyCode;

    public void resetText()
    {
        if (keyCode == null)
        {
            this.setText("Configure");
        }
        else
            this.setText(this.getKeyCode().getName().toUpperCase());
    }

    public KeyCode getKeyCode()
    {
        return keyCode;
    }

    public void setKeyCode(KeyCode keyCode)
    {
        this.keyCode = keyCode;
    }


}
