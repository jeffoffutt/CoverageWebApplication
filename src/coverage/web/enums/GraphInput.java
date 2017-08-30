package coverage.web.enums;

public enum GraphInput
{
    ActionButton("action"),
    EdgesTextBox("edges"),
    EndNodeTextBox("endNode"),
    CreateGraphButton("createGraph"),
    SelectedMethodDropDown("selectedMethod"),
    InitialNodeTextBox("initialNode"),
    NodeDescriptionTableVisibilityToggleSwitch("nodeDescriptionToggleSwitch"),
    Algorithm2ActionButton("algorithm2");

    
    private final String controlName;
    GraphInput(String controlName)
    {
        this.controlName = controlName;
    }
    public String getControlName()
    {
        return controlName;
    }
}
