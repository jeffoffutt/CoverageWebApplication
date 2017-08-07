package coverage.web;

import java.util.Collection;

public class WebCoverageUtility
{
    public static WebItem FindFirstWebItemByName(String name, Collection<WebItem> webItems)
    {        
        for(WebItem item : webItems)
        {
            if(name.equals(item.getName()))
            {
                return item;
            }
        }
        
        return null;
    }
    
    
    public static String GetWebValueItemOrNull(String name, Collection<WebItem> webItems)
    {        
        WebItem foundItem = FindFirstWebItemByName(name, webItems);
        //Item not found
        if(foundItem == null)
        {
            return null;
        }
        
        return foundItem.getValue();
    }

}
