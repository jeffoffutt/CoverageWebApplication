package coverage.web.enums;

public enum OtherTools
{
    NewGraph("New Graph", null),
    DataFlowCoverage("Data Flow Coverage", "DFGraphCoverage"),
    LogicCoverage("Logic Coverage", "LogicCoverage"),
    MinimalMUMCUTCoverage("Minimal-MUMCUT Coverage", "MinimalMUMCUTCoverage");
    
    String buttonName;
    String redirect;
    OtherTools(String name, String redirect)
    {
        this.buttonName = name;
        this.redirect = redirect;
    }    
    
    public String getRedirect()
    {
        return this.redirect;
    }
    
    @Override
    public String toString()
    {
        return this.buttonName;
    }
}
