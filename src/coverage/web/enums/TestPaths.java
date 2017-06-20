package coverage.web.enums;

public enum TestPaths
{
    NodeCoverage("Node Coverage"),
    EdgeCoverage("Edge Coverage"),
    EdgePairCoverage("Edge-Pair Coverage"),
    PrimePathCoverage("Prime Path Coverage");
    
    String buttonName;
    TestPaths(String name)
    {
        this.buttonName = name;
    }    
    
    @Override
    public String toString()
    {
        return this.buttonName;
    }
}
