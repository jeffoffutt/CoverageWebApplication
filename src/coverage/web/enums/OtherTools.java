package coverage.web.enums;

public enum OtherTools
{
    NewGraph("New Graph"),
    DataFlowCoverage("Data Flow Coverage"),
    LogicCoverage("Logic Coverage"),
    MinimalMUMCUTCoverage("Minimal-MUMCUT Coverage");
    
    String buttonName;
    OtherTools(String name)
    {
        this.buttonName = name;
    }    
    
    @Override
    public String toString()
    {
        return this.buttonName;
    }
}
