package coverage.web;

public final class WebItem
{
    private final String name;
    private final String value;
    
    public WebItem(String name, String value)
    {
        this.name = name;
        this.value = value;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public String getValue()
    {
        return this.value;
    }
}
