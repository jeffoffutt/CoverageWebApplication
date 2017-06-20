package coverage.web.enums;

public enum TestRequirements
{
    Nodes("Nodes"),
    Edges("Edges"),
    EdgePair("Edge-Pair"),
    SimplePaths("Simple Paths"),
    PrimePaths("Prime Paths");
    
    String buttonName;
    
    TestRequirements(String name)
    {
        this.buttonName = name;
    }
    
    @Override
    public String toString()
    {
        return this.buttonName;
    }
}
