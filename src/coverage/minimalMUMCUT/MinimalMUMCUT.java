
package coverage.minimalMUMCUT;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MinimalMUMCUT
{
	static long time = 0;
	/*Nan*/ static boolean isUpper = false;
	
	String predicateExpr;
	static String data;
	static String mutant;
	static String mutantTypes;
	static String minimizationData;
	static String maximizationData;
	static int maxTestSetSize;
	
   static String semicolonSepInf = "";
   static String commaSepInfVals = "";
   
   static boolean[] isTermMutpFeasible = null;
   static String expression = "";
   static String alphaExpression = "", uniqueLiterals = "";
   static int numTrue, numFalse, numTofs, numTrfs, numTifs;
   static Set<String> _binaryStrings = new LinkedHashSet<String>();
   static Set<String> allBinaryStrings = new LinkedHashSet<String>();
   static  Set<String> lifLifMutants = new LinkedHashSet<String>();	  
   static Map<String,String> lifLifPoints = new LinkedHashMap<String,String>();
   static Map<Integer,String> literalsThatMakeATermMutpInfeasible = new LinkedHashMap<Integer,String>();
   static String[] alphaTerms = null;   
   static String feasibilityText = "";
   static Set<String> allNfps = new HashSet<String>();
   static Set<String> allUtps = new HashSet<String>();
   static Set<String> allOtps = new HashSet<String>();
   static Map<String,Integer> allNfpCountMap = new LinkedHashMap<String,Integer>();
   static Map<String,Set<String>> utpsMap = new LinkedHashMap<String,Set<String>>();
   static Map<String,Set<String>> nfpsMap = new LinkedHashMap<String,Set<String>>();
   static Map<String,Set<String>> nfpsTermMap = new LinkedHashMap<String,Set<String>>();
	  
   private static final int NONE = -1;
   private static final int ZERO = 0;
   private static final int ONE = 1;
   private static final int BOTH = 2;

   /*Nan - the next two methods are new*/
   private static String toUpper(String s)
   {
	   if (isUpper) return s.toUpperCase();
	   
	   return s;
   }
   
   private static String toUpperTermsAndLiterals(String s)
   {
	   if (isUpper)
	   {
		   int commaIndex = 0; int returnIndex = 0; int endTermIndex;		   
		   int termIndex = 0;  int literalIndex = 0; int inIndex = 0;
		   int andIndex = 0;
		   
		   while (s.indexOf("term ",termIndex+1) != -1)
		   {
			   termIndex = s.indexOf("term ",termIndex+1);
			   
			   commaIndex = s.indexOf(",",termIndex);
			   returnIndex = s.indexOf("\n",termIndex);
			   andIndex = s.indexOf(" and ", termIndex);
			   
			   endTermIndex = returnIndex;
			   if (commaIndex != -1 && commaIndex < endTermIndex) endTermIndex = commaIndex;
			   if (andIndex != -1 && andIndex < endTermIndex) endTermIndex = andIndex;
			   
			   s = s.substring(0,termIndex + 5) + 
			       s.substring(termIndex + 5,endTermIndex).toUpperCase() +
			       s.substring(endTermIndex);
		   }
		   
		   while (s.indexOf("literal ",literalIndex+1) != -1)
		   {
			   literalIndex = s.indexOf("literal ",literalIndex+1);
			   
			   inIndex = s.indexOf(" in ",literalIndex);
			   
			   s = s.substring(0,literalIndex + 8) + 
			       s.substring(literalIndex + 8,inIndex).toUpperCase() +
			       s.substring(inIndex);
		   }
		   return s;
	   }
	   return s;
   }
   
   public MinimalMUMCUT(String expr, String infeasibleLiterals, String choice, int size) throws InvalidException
   {
		predicateExpr = expr;
		data = "";
		mutant = "";
		mutantTypes = "";
		minimizationData = "";
		maxTestSetSize = size;
		maximizationData = "";
		semicolonSepInf = infeasibleLiterals;
		
	   time = 0;
		semicolonSepInf = ""; 
		/*Nan*/ isUpper = false;
		commaSepInfVals = "";
	   isTermMutpFeasible = null;
	   expression = ""; 
	   alphaExpression = ""; uniqueLiterals = "";
	   numTrue = 0; numFalse = 0; numTofs = 0; numTrfs = 0; numTifs = 0;
       _binaryStrings = new LinkedHashSet<String>();
       allBinaryStrings = new LinkedHashSet<String>();
   	lifLifMutants = new LinkedHashSet<String>();	  
   	lifLifPoints = new LinkedHashMap<String,String>();
        literalsThatMakeATermMutpInfeasible = new LinkedHashMap<Integer,String>();
        alphaTerms = null;
        allNfps = new HashSet<String>();
        allUtps = new HashSet<String>();
        allOtps = new HashSet<String>();
        allNfpCountMap = new LinkedHashMap<String,Integer>();
        utpsMap = new LinkedHashMap<String,Set<String>>();
        nfpsMap = new LinkedHashMap<String,Set<String>>();
        nfpsTermMap = new LinkedHashMap<String,Set<String>>();
       
   
      /*Nan*/ if (predicateExpr.indexOf("A") != -1) isUpper = true; 
      /*Nan*/expression = predicateExpr.toLowerCase();
      /*Nan*/ semicolonSepInf = infeasibleLiterals.toLowerCase();
     // String choice = choice;
      boolean doSmotp =true;
      //System.out.println("predicate: " + expression);
      //System.out.println("infeasible literals: " + semicolonSepInf);
      //System.out.println("choice: " + choice);
      //System.out.println("integer: " + size);
            alphaExpression = reverseConvertAlphaExpression(expression,true);            
            String alphabet = "abcdefghijklmnopqrstuvwxyz";
            alphaTerms = alphaExpression.split(" \\+ ");
            isTermMutpFeasible = new boolean[alphaTerms.length];
            
            for (int w=0; w < alphabet.length(); w++)
            {
               if (alphaExpression.toLowerCase().contains(alphabet.substring(w,w+1)))
               {
                  uniqueLiterals = uniqueLiterals + alphabet.substring(w,w+1);
               }
            }            

            /*Nan - this if statement is now commented out */
            /*if (!uniqueLiterals.equals(alphabet.substring(0,uniqueLiterals.length())))
            {
			JDialog dialog = new JDialog(new JFrame(),"Invalid Predicate Format");
                         dialog.setSize(500,100);

                         JTextArea textArea = new JTextArea(
                        	 	 "\nLiterals do not appear in alphabetical order." +
                                 "\nOne or more letters in the alphabet have been skipped.");
                        
                         textArea.setEditable(false);                         
                         dialog.add(textArea);
                         dialog.setVisible(true);  return;
            }*/
            
            String[] alphaTermsToCheckInf = alphaExpression.split(" \\+ ");

            if (!commaSepInfVals.equals(""))
            {
               for (int x=1; x <= alphaTermsToCheckInf.length; x++)
               {
                  String termToCheckInf = alphaTermsToCheckInf[x-1];
                  String[] infVals = commaSepInfVals.split(",");

                  for (String infVal : infVals)
                  {
                     if (alphabetize(termToCheckInf).indexOf(alphabetize(infVal)) != -1)
                     	throw new InvalidException("Invalid Predicate Format" +
                     			"\nTerm " + x + " in the predicate can never be true given the infeasible literal combinations." +
                              "\nEither remove the term or change the infeasible literal combinations as no UTP exists for the term.");
                  }
                   
                  for (int y=1; y <= alphaTermsToCheckInf.length; y++)
                     {
                        if (y==x) continue;

                        String otherTermToCheck = alphaTermsToCheckInf[y-1];
                        
                        for (String infVal2 : infVals)
                        {
                           for (int z=0; z < infVal2.length(); z++)
                           {
                              if (termToCheckInf.indexOf(infVal2.substring(z,z+1)) != -1)
                              {                              
                                 for (int a=0; a < infVal2.length(); a++)
                                 {
                                     otherTermToCheck = otherTermToCheck.replace(reverseCase(infVal2.substring(a,a+1)),infVal2.substring(z,z+1));                                    

					if (otherTermToCheck.equals(termToCheckInf))
						 throw new InvalidException("Invalid Predicate Format" +
               			 "\nTerm " + x + " and term " + y + " can never be uniquely true given the infeasible literal combinations." +
                     	 "\nEither combine the terms or change the infeasible literal combinations as no UTP exists for either term.");			                                      			                                     		      
                                 }
                              }  
                           }
                        }                         
                     } 

                  for (int y=1; y <= termToCheckInf.length(); y++)
                  {
                     String termToCheckInfCopy = termToCheckInf.replace(termToCheckInf.substring(y-1,y),reverseCase(termToCheckInf.substring(y-1,y)));

                     for (String infVal : infVals)
                     {
                        if (alphabetize(termToCheckInfCopy).indexOf(alphabetize(infVal)) != -1)
                        	throw new InvalidException("Invalid Predicate Format" +
                        			"\nLiteral " + y + " in term " + x + " in the predicate can never be false when all other literals in its term are true given the infeasible literal combinations." +
                                 "\nEither remove the literal in the term or change the infeasible literal combinations as no NFP exists for this literal.");
                     }
                  }
               }
            }                 
            
            Set<String> copyOfAlphaTerms = new HashSet<String>();
            
            for (int i=0; i < alphaTerms.length; i++)
            {
            	Set<String> nfpsForTerm = new LinkedHashSet<String>();
            	
            	String alphaTerm = alphaTerms[i];
            	
            	if (!copyOfAlphaTerms.add(alphaTerm))
            		throw new InvalidException("Invalid Predicate Format" + "\nTerm " + convertAlphaExpression(alphaTerm) + " cannot repeat.");
            	
            	String literals = "";
            	
            	for (int y=0; y < alphaTerm.length(); y++)
            	{
            		if (literals.indexOf(alphaTerm.substring(y,y+1).toLowerCase()) == -1)
            		{
            			literals = literals + alphaTerm.substring(y,y+1).toLowerCase();
            		}
            		else
            		{
            			throw new InvalidException("Invalid Predicate Format" + "\nLiterals cannot repeat in term " + toUpper(convertAlphaExpression(alphaTerm)) + ".");

            		}
            	}
            	
            	for (int j=0; j < Math.pow(2,uniqueLiterals.length()); j++)
                {
                    String potential = padWithLeadingZeros(Integer.toBinaryString(j),uniqueLiterals.length());
                    if (isPointFeasible(potential))
                    {           
                    	allBinaryStrings.add(potential);
                    }
                }
            	
            	Set<String> utps = getUtps(alphaTerm);
            	utpsMap.put(alphaTerm,utps);
            	for (String utp : utps) allUtps.add(utp);
            	if (utps.size() == 0)
            	{           		
            		throw new InvalidException("Invalid Predicate Format" + "\nNo UTP exists for term " + toUpper(convertAlphaExpression(alphaTerm)) + ".");               
            	}
            	
            	for (int y=0; y < alphaTerm.length(); y++)
            	{
            		Set<String> nfps = getNfps(alphaTerm,alphaTerm.substring(y,y+1));            		
            		nfpsMap.put(alphaTerm + "_" + alphaTerm.substring(y,y+1), nfps);
            		for (String nfp : nfps) 
            		{
            			nfpsForTerm.add(nfp);
            			allNfps.add(nfp);
            			if (!allNfpCountMap.containsKey(nfp)) allNfpCountMap.put(nfp,1);
            			else allNfpCountMap.put(nfp, allNfpCountMap.get(nfp) + 1);
            		}
            		if (nfps.size() == 0)
            		{
            			throw new InvalidException("Invalid Predicate Format" + "\nNo NFP exists for literal " + 
            					toUpper(convertAlphaExpression(alphaTerm.substring(y,y+1))) + " in term " + toUpper(convertAlphaExpression(alphaTerm)) + ".");
                    
            		}
            	}
            	nfpsTermMap.put(alphaTerm, nfpsForTerm);
            }
            
           
            for (int j=0; j < Math.pow(2,uniqueLiterals.length()); j++)
            {
                String potential = padWithLeadingZeros(Integer.toBinaryString(j),uniqueLiterals.length());
                if (isPointFeasible(potential))
                {           
                	if (choice.indexOf("SMOTP") == -1 && (allUtps.contains(potential) || allNfps.contains(potential)))
                	{
   	                   _binaryStrings.add(potential);
                	}
                	else if (choice.indexOf("SMOTP") != -1 && (isATruePoint(potential,null) || allNfps.contains(potential)))
                	{
                		_binaryStrings.add(potential);
                	}
                	
                	if (choice.indexOf("SMOTP") != -1 && isATruePoint(potential,null) && !allUtps.contains(potential))
                	{
                		allOtps.add(potential);
                	}
                }
   	        }    
           // System.out.println(totUtps); System.out.println(allNfps.size());
            //System.out.println(totTrue-totUtps); System.out.println(totFalse-allNfps.size());
            
           time = System.currentTimeMillis() - time;
           
           if (choice.equals("NFP Minimization"))
           {
               getMinimization("NFP");  return;
           } 
           if (choice.equals("MUTP Minimization"))
           {
              getMinimization("MUTP");  return;
           } 
           if (choice.equals("CUTPNFP Minimization"))
           {
              getMinimization("CUTPNFP"); return;
           } 
           if (choice.equals("MNFP Minimization"))
           {
              getMinimization("MNFP");  return;
           }  
           if (choice.equals("Minimal-MUMCUT/SMOTP Minimization"))
           {
              getMinimization("Minimal-MUMCUT/SMOTP"); return;
           }  
           if (choice.equals("Minimal-MUMCUT Minimization"))
           {
         	  getMinimization("Minimal-MUMCUT");  return;
           }
           if (choice.equals("Fault Detection Maximization"))
           {
         	  getMaximization(); return;
           }
           if (choice.equals("MUTP"))
           {
              getMutpTests(true,false,-1);  return;
           }
           if (choice.equals("CUTPNFP"))
           {
              getCutpnfpTests(true);  return;
           }
           if (choice.equals("MNFP"))
           {
              getMnfpTests();  return;
           }
           if (choice.equals("Minimal-MUMCUT") || choice.equals("NFP"))
           {
              doSmotp = false;
           }
      // if we get here the user wants either a NFP or Minimal-MUMCUT or Minimal-MUMCUT/SMOTP test set
        if (!choice.equals("NFP") && (alphaExpression.length() == 1 || alphaExpression.indexOf(" + ") == -1 || isEveryTermAOneLiteralTerm()))
        {
              // if the predicate contains a single literal, a single term, or only terms containing one literal
              // a Minimal-MUMCUT test set is also a cutpnfp test set set
              getCutpnfpTests(false);
        }
        else
        { 
        	String[] terms = alphaExpression.split(" \\+ ");
        	if (!choice.equals("NFP"))
        	{
            getMutpTests(false,false,-1); // Minmal-MUMCUT always requires mutp
        	}
           isTermMutpFeasible = new boolean[terms.length];
           
           Map<String,Set<String>> nfpMap = new LinkedHashMap<String,Set<String>>(); //key=a literal,value=set of nfps for that literal such that 
                                                                                     // any nfp can be chosen from the set to satisfy Minimal-MUMCUT) 
     
           Map<String,Integer> nfpCountMap = new LinkedHashMap<String,Integer>();  //(key=a nfp,value=number of literals that can use that nfp to 
                                                                                   //satisfy Minimal-MUMCUT) 

           Map<String,Set<String>> mnfpMap = new LinkedHashMap<String,Set<String>>();//(key=a literal,value=mnfp test set for that literal)
            
           for (int i=0; i < alphaTerms.length; i++)
           {
        	String term = alphaTerms[i];        	   
        	if (!choice.equals("NFP")) 
        	{
        	setMutpFeasibilityForTerm(term,i);
        	}

        	if (isTermMutpFeasible[i] || choice.equals("NFP"))
               {
                   // populate the nfp maps based on any nfp since MUTP is feasible and thus it will detect all LRFs
            	   for (int t=0; t < term.length(); t++)
            	   {
            	     String key = "literal " + term.substring(t,t+1).toLowerCase() + " in term " + convertUppercaseToNegation(term);
            	    
            	      Set<String> nfps = nfpsMap.get(term + "_" + term.substring(t,t+1));
            	      
            	      nfpMap.put(key,nfps);
            	      
            	      for (String nfp : nfps)
            	      {
	    			     if (!nfpCountMap.containsKey(nfp))
	    			     {
	    			        nfpCountMap.put(nfp,1);
	    			     }
	    			     else
	    			     {
	    				    Integer intVal = nfpCountMap.get(nfp);
	    				    intVal++;
	    				    nfpCountMap.put(nfp, intVal);
	    			     }
	    			  }
            	   }
               }
               else
               {
                  // we need to populate the nfp maps based on specific nfps needed to detect a LRF since MUTP is infeasible
            	  for (int t=0; t < term.length(); t++)
            	  {
            		  if (isCutpnfpFeasible(term,term.substring(t,t+1)))
            		  {
                 	     Set<String> nfps = nfpsMap.get(term + "_" + term.substring(t,t+1));                 	     
                 	     Set<String> utps = utpsMap.get(term);
                 	     
                 	     int index = uniqueLiterals.indexOf(term.substring(t,t+1).toLowerCase());
                 	     
                 	     for (String utp : utps)
                 	     {
                 	    	if (index != uniqueLiterals.length() - 1)
                 	    	{
                 	    	 utp = utp.substring(0,index) + utp.substring(index+1);
                 	    	}
                 	    	else
                 	    	{
                 	    		utp = utp.substring(0,index);
                 	    	}
                 	    	 
                 	    	 for (String nfp : nfps)
                 	    	 {
                 	    		 String origNfp = nfp;
                 	    		 
                 	    		if (index != uniqueLiterals.length() - 1)
                     	    	{
                     	    	 nfp = nfp.substring(0,index) + nfp.substring(index+1);
                     	    	}
                     	    	else
                     	    	{
                     	    		nfp = nfp.substring(0,index);
                     	    	}
                 	    		
                 	    		if (utp.equals(nfp))
                 	    		{
                 	    			String key = "literal " + term.substring(t,t+1).toLowerCase() + " in term " + convertUppercaseToNegation(term);
                 	    			if (!nfpMap.containsKey(key))
                 	    			{
                 	    			   Set<String> nfpSet = new HashSet<String>();                 	    			   
                 	    			   nfpSet.add(origNfp);                 	    			
                 	    			   nfpMap.put(key,nfpSet);
                 	    			   
                 	    			   nfpCountMap.put(origNfp,1);
                 	    			}
                 	    			else
                 	    			{
                 	    				Set<String> nfpSet = nfpMap.get(key);
                 	    				nfpSet.add(origNfp);                 	    				
                 	    				nfpMap.put(key,nfpSet);
                 	    				
                 	    				Integer intVal = nfpCountMap.get(origNfp);
                 	    				if (intVal == null) intVal = 0;
                 	    				intVal++;
                 	    				nfpCountMap.put(origNfp, intVal);
                 	    			}
                 	    		} // if
                 	    	 }  // for nfps   
                 	     } // for utps
            		  } // if cutpnfp feasible
            		  else
            		  { // satisfy mnfp for the literal at index t in term i since cutpnfp is infeasible for this literal
 
            	           Set<String> nfps = nfpsMap.get(term + "_" + term.substring(t,t+1));            	           
            	           Set<Integer> indicesOfLiterals = new LinkedHashSet<Integer>();
            	           Set<String> abbrevNfps = new LinkedHashSet<String>();
            	           
            	           for (int x=0; x < term.length(); x++)
            	           {
            	           	indicesOfLiterals.add(uniqueLiterals.indexOf(term.substring(x,x+1).toLowerCase()));
            	           }
            	                    	           
            	           for (String nfp : nfps)
            	           {
            	           	   String abbrevNfp = "";
            	           	
            	           	   for (int x=0; x < nfp.length(); x++)
            	           	   {
            	           		  if (!indicesOfLiterals.contains(x))
            	           		  {
            	           			abbrevNfp = abbrevNfp + nfp.substring(x,x+1);
            	           		  }
            	           	   }            	           	
            	           	   abbrevNfps.add(abbrevNfp);
            	           }
            	           
            	           Set<String> abbrevPointsForTerm = new LinkedHashSet<String>(getMutpOrMnfpTestsBasedOnMutpGreedy(new ArrayList<String>(abbrevNfps),uniqueLiterals.length()-term.length(),null));
            	                              	           
            	           for (String abbrevPoint : abbrevPointsForTerm)
            	           {
            	           	String point = ""; int termIndex = -1;  int abbrevPointIndex = -1;
            	           	
            	           	for (int x=0; x < uniqueLiterals.length(); x++)
            	           	{
            	           		if (term.toLowerCase().indexOf(uniqueLiterals.substring(x,x+1)) != -1)
            	           		{
            	           			termIndex++;
            	           			if (term.substring(t,t+1).toLowerCase().equals(uniqueLiterals.substring(x,x+1)) &&
            	           				Character.isUpperCase(alphabetize(term).charAt(termIndex)))
            	           			{
            	           				point = point + "1";
            	           				continue;
            	           			}
            	           			else if (term.substring(t,t+1).toLowerCase().equals(uniqueLiterals.substring(x,x+1)) &&
            	               				Character.isLowerCase(alphabetize(term).charAt(termIndex)))
            	           			{
            	           				point = point + "0";
            	           				continue;
            	           			}
            	           			if (Character.isUpperCase(alphabetize(term).charAt(termIndex)))
            	           			{
            	           				point = point + "0";
            	           			}
            	           			else
            	           			{
            	           				point = point + "1";
            	           			}
            	           		}
            	           		else
            	           		{
            	           			abbrevPointIndex++;
            	           			point = point + abbrevPoint.substring(abbrevPointIndex,abbrevPointIndex+1);
            	           		}
            	           	} //for
            	            
         	    			String key = "literal " + term.substring(t,t+1).toLowerCase() + " in term " + convertUppercaseToNegation(term);
         	    			if (!mnfpMap.containsKey(key))
         	    			{
         	    			   Set<String> nfpSet = new HashSet<String>();                 	    			   
         	    			   nfpSet.add(point);                 	    			
         	    			   mnfpMap.put(key,nfpSet);
         	    			}
         	    			else
         	    			{
         	    				Set<String> nfpSet = mnfpMap.get(key);
         	    				nfpSet.add(point);                 	    				
         	    				mnfpMap.put(key,nfpSet);
         	    			}
            	          } // for
            	           
            		  } //else
            	  } // for (int t=0; t < term.length(); t++)
                 } // else
               }//  for (int i=0; i < terms.length; i++)
           
            if (doSmotp) createLifLifMutants(false);

// For any literal x that has a nfp which overlaps with one of the nfps already chosen to satisfy mnfp for another literal y, we remove
// literal x from the set of literals needing a nfp.  Say literal x has the following nfps: 0000, 0001, and 0010 and that 0000 is already
// needed above to satisfy mnfp for literal y.  Then for the points 0001 and 0010 we reduce by one the number of literals remaining that can 
// be covered by these points since literal x has already been chosen to be covered by 0000.
           
           Set<String> mutants = new LinkedHashSet<String>();
       	   String ret = "";
           Map<String,String> pointsMap = new LinkedHashMap<String,String>();           
           
                  for (String mnfpKey : mnfpMap.keySet())
            	  {
            		  Set<String> mnfps = mnfpMap.get(mnfpKey);
            		  
            		  for (String mnfp : mnfps)
            		  {
            			  String point = mnfp + " - NFP for " + mnfpKey;
            			  
            		      Set<String> keysToRemove = new HashSet<String>();
            		  
            		     for (String nfpKey : nfpMap.keySet())
            		     {
            			     Set<String> nfps = nfpMap.get(nfpKey);
            			     
            				    if (nfps.contains(mnfp))
            				    {
            					   keysToRemove.add(nfpKey);            					   
                     			           nfpCountMap.remove(mnfp);
            					   point = point + ", " + nfpKey;
            				    }
            			  }
            		         
            		     if (point.endsWith(", ")) point = point.substring(0,point.length()-2);
            		     if (!pointsMap.containsKey(mnfp))
            		     {
            		        pointsMap.put(mnfp,point);
            		     }
            		     else
            		     {
            		    	 pointsMap.put(mnfp,pointsMap.get(mnfp) + ", " + point.substring(point.indexOf("literal")));
            		     }

                             if (mutants.add(alphaExpression + " + " + convertPointToTerm(mnfp))) numTifs++;
                   	
            		     for (String keyToRemove : keysToRemove)
            		     {
                                Set<String> nfps = nfpMap.get(keyToRemove);
            		  
            		        for (String nfp : nfps)
            		        {
            			  Integer intVal = nfpCountMap.get(nfp);
            			  if (intVal != null)
            			  {
            				  intVal--;
            				  nfpCountMap.put(nfp, intVal);
            			  }
            		        }
            		    	nfpMap.remove(keyToRemove);
            		     }
            		  }
            	  }

                 List<Object> optimals = doGreedyOverlap(nfpMap, nfpCountMap, pointsMap, mutants, "NFP", null);
                 
                  numTifs = numTifs + ((Set<String>)optimals.get(0)).size() - mutants.size() + 1;
                  mutants = (Set<String>)optimals.get(0);
                  pointsMap = (Map<String,String>)optimals.get(1);
                  String optimalKey =   (String)optimals.get(2);

                  for (String nfpMapKey : nfpMap.keySet())
                  {
                     boolean literalCovered = false;

                     for (String nfpPoint : nfpMap.get(nfpMapKey))
                     {
                        if (pointsMap.keySet().contains(nfpPoint))
                        {
                           literalCovered = true;  break;
                        }
                     }
                     if (!literalCovered)
                     {
                        for (String nfpPoint : nfpMap.get(nfpMapKey))
                        {
                           pointsMap.put(nfpPoint,nfpPoint + " - NFP for " + nfpMapKey);
                           numTifs++;
                           mutants.add(alphaExpression + " + " + convertPointToTerm(nfpPoint));
                           break;
                        }
                     }
                  }
                  
                  mutants.add(alphaExpression + " + " + convertPointToTerm(optimalKey));

                  // reorder the points so that they match the mutant order                     
                     String optVal = pointsMap.get(optimalKey);                     
                     pointsMap.remove(optimalKey);  pointsMap.put(optimalKey, optVal);                     

                 	   Set<String> keys = pointsMap.keySet();
                 	   
                 	   for (String key : keys)
                 	   {
                 	      ret = ret + pointsMap.get(key) + "\n";
                 	   }
          	 int numNon = numTrue + numFalse + numTofs + numTifs + numTrfs;    

             if (doSmotp)
             {
                String allMutants = "";
                
                for (String mutant : lifLifMutants)
                {
               	 allMutants = allMutants + convertAlphaExpression(mutant) + "\n";
                }
               // /*Nan*/gui.mutantArea.setText(toUpper(gui.mutantArea.getText() + allMutants));
                mutant = toUpper(mutant + allMutants);
                String allPoints = "";
                
                for (String key : lifLifPoints.keySet())
                {
               	 allPoints = allPoints + lifLifPoints.get(key) + "\n";
                }
                
              //  /*Nan*/ gui.dataArea.setText(toUpperTermsAndLiterals(gui.dataArea.getText() + allPoints));
                data = toUpperTermsAndLiterals(data + allPoints);
             }
             // /*Nan*/ gui.dataArea.setText(toUpperTermsAndLiterals(gui.dataArea.getText() + ret) + "\nNumber of tests: " + numNon);
             data = toUpperTermsAndLiterals(data + ret + "\nNumber of tests: " + numNon);   
              String mutText = "";
              for (String mutant : mutants)
       	      {
       		      mutText = mutText + convertAlphaExpression(mutant) + "\n";
       	      }
       	 //  /*Nan*/gui.mutantArea.setText(toUpper(gui.mutantArea.getText() + mutText));   
              mutant = toUpper(mutant + mutText);
           String text = 
     "Number of False mutants generated: " + numFalse + "\n" +
     "Number of True mutants generated: " + numTrue + "\n" +
     "Number of TOF mutants generated: " + numTofs + "\n" +
     "Number of TIF mutants generated: " + numTifs + "\n" +
     "Number of TRF mutants generated: " + numTrfs + "\n" + 
     "Total Number of Non-Equivalent Mutants Generated: " +    numNon;

         //  gui.mutantTypesArea.setText(text);  
           mutantTypes = text;
           }//else           
       
           //System.out.println(System.currentTimeMillis() - time);
        }
	//get data
	public String getData(){
		return data;
	}
	
	//get mutant
	public String getMutant(){
		return mutant;
	}
	
	//get mutant Types
	public String getMutantTypes(){
		return mutantTypes;
	}
	
	//get minimization data
	public String getMinimizationData(){
		return minimizationData;
	}
	
	//get maximization data
	public String getMaximizationData(){
		return maximizationData;
	}
// returns true if the given binary string input is a true point
// for example, if the predicate is a + b, then the input 01 returns true
// if mutatedTerms is not null then instead of testing the input on the original predicate, 
// it tests the input on the mutated predicate represented by mutatedTerms   
private static boolean isATruePoint(String input, String mutatedTerms)
{
   String[] terms = null;
   
   if (mutatedTerms != null)
   {
	   terms = mutatedTerms.split(" \\+ ");
   }
   else
   {
	   terms = alphaTerms;
   }
           outer: for (int i=0; i < terms.length; i++)
           {
              String alphaTerm = terms[i];

              for (int j=0; j < alphaTerm.length(); j++)
              {
                 int index = uniqueLiterals.indexOf(alphaTerm.substring(j,j+1).toLowerCase());

                 if (Character.isUpperCase(alphaTerm.charAt(j)))
                 {
                    if (input.substring(index,index+1).equals("1"))  continue outer;
                 }
                 else
                 {
                    if (input.substring(index,index+1).equals("0"))  continue outer;
                 }
              }
              return true;
           }
            return false;
        }

// converts a!b + !cd to aB + Cd
        private static String convertNegationToUpperCase(String input)
        {
           String temp = "";
           if (input.indexOf("!") == -1)  return input;
              for (int j=0; j < input.length(); j++)
              {
                 if (input.charAt(j) == '!')
                 {
                    if (j==input.length()-2) temp = ""; else temp = input.substring(j+2);

                    input = input.substring(0,j) + input.substring(j+1,j+2).toUpperCase() + temp;
                 }
              }
           return input;
        }

// records whether mutp is feasible for a given term
// note that terms with only one unique literal or terms with just one literal are implicitly considered as mutp feasible
// if mutp is not feasible, a map is populated where the key is the term index and the value is 
          private static void setMutpFeasibilityForTerm(String term, int indexOfTerm)
          {
             isTermMutpFeasible[indexOfTerm] = true;
             
             if (term.length() == uniqueLiterals.length() || term.length() ==1) return;
             
    		 String literalsNotInTerm = getLiteralsNotInTerm(term);
             String infLiterals = "";
    		
    		 for (int i=0; i < alphaTerms.length; i++)
    		 {
    			if (alphaTerms[i].length() == 1)
    			{
    				literalsNotInTerm = literalsNotInTerm.replace(alphaTerms[i].toLowerCase(), "");
    			}
    		 }    		    		
    		for (int j=0; j < literalsNotInTerm.length(); j++)
    		{
    			for (int k=0; k < 2; k++)
    			{
    			   String mutant = k==0 ? alphaExpression.replace(term, term + literalsNotInTerm.substring(j,j+1))
    			                        : alphaExpression.replace(term, term + literalsNotInTerm.substring(j,j+1).toUpperCase());    			   
    			   boolean detected = false;
    		   
    			   for (String bs : utpsMap.get(term))
    			   {
    				  if (!isATruePoint(bs,mutant))
    				  {
    				     detected = true; break;
    				  }
    			   }
    			   if (!detected)
    			   {
    				   isTermMutpFeasible[indexOfTerm] = false;
                       String literalToAdd = k == 0 ? literalsNotInTerm.substring(j,j+1) : literalsNotInTerm.substring(j,j+1).toUpperCase();
                       infLiterals = infLiterals + literalToAdd;
    			   }
    			}    		
    		}
            literalsThatMakeATermMutpInfeasible.put(indexOfTerm,infLiterals);
          }          

// converts each lower case letter in s to upper case and vice-versa
        private static String reverseCase(String s)
        {
           String ret = "";
           for (int i=0; i < s.length(); i++)
           {
              if (Character.isLowerCase(s.charAt(i))) ret = ret + s.substring(i,i+1).toUpperCase();
              else ret = ret + s.substring(i,i+1).toLowerCase();
           }
           return ret;
        }
        
// converts a string to alphabetical order ignoring case - dBc is converted to Bcd
        private static String alphabetize(String s)
        {
           String ret = "";
           String lower = "abcdefghijklmnopqrstuvwxyz";
           String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
           for (int i=0; i < lower.length(); i++)
           {
              if (s.indexOf(lower.substring(i,i+1)) != -1)  ret = ret + lower.substring(i,i+1);
              else if (s.indexOf(upper.substring(i,i+1)) != -1)  ret = ret + upper.substring(i,i+1);
           }
           return ret;
        }

// pads a string with leading zeros
// used for creating binary strings - for example when there are three unique literals 0 is padded to 000
        private static String padWithLeadingZeros(String s, int length)
        {
           while (s.length() < length)  s = "0" + s;
           return s;
        }     

// return a string of literals where each literal return is in term2 but not term1
         private static String getLiteralsInTerm2NotInTerm1(String term1, String term2)
         {
            String ret = "";
            for (int i=0; i < term2.length(); i++)
            {
               if (term1.indexOf(term2.substring(i,i+1)) == -1)  ret = ret + term2.substring(i,i+1);
            }
            return ret;
         }        
   
// returns true if the point is feasible; false otherwise
// for example if a=1,d=1 is infeasible, then 1000 is feasible but 1001 is not
private static boolean isPointFeasible(String point) 
{
	if (semicolonSepInf.equals("")) return true;
	
	String pointAsTerm = "";
	
	for (int i=0; i < point.length(); i++)
	{
		if (point.substring(i,i+1).equals("1"))
		{
		   pointAsTerm += uniqueLiterals.substring(i,i+1);
		}
		else
		{
			pointAsTerm += uniqueLiterals.substring(i,i+1).toUpperCase();
		}
	}
	
	String[] infVals = commaSepInfVals.split(",");
	
	outer: for (String infVal : infVals)
	{
		for (int i=0; i < infVal.length(); i++)
		{
			if (pointAsTerm.indexOf(infVal.substring(i,i+1)) == -1)
			{
				continue outer;
			}
		}
		return false;
	}	
	return true;
}

// return a set of all utps for a given term         
private static Set<String> getUtps(String term)
{  
   Set<String> utps = new LinkedHashSet<String>();
   Set<String> tps = new LinkedHashSet<String>();
   String[] valuesThatMustHold = new String[uniqueLiterals.length()];
   for (int i=0; i < term.length(); i++)
   {
      int index = uniqueLiterals.indexOf(term.substring(i,i+1).toLowerCase());
      if (Character.isUpperCase(term.charAt(i))) valuesThatMustHold[index] = "0";
      else valuesThatMustHold[index] = "1";
   }
   Iterator<String> binaryStringsIter = allBinaryStrings.iterator();
   while (binaryStringsIter.hasNext())
   {
      String binaryString = binaryStringsIter.next();
      for (int i=0; i < valuesThatMustHold.length; i++)
      {
         if (valuesThatMustHold[i] != null)
         {
            if (i != valuesThatMustHold.length -1)
            {
               binaryString = binaryString.substring(0,i) + valuesThatMustHold[i] + binaryString.substring(i+1);
            }
            else
            {
               binaryString = binaryString.substring(0,i) + valuesThatMustHold[i];
            }
         }
      }
      tps.add(binaryString);
   }
   Iterator<String> tpsIter = tps.iterator();
   
   if (alphaTerms == null)
   {
	   alphaTerms = alphaExpression.split(" \\+ ");
   }
   while (tpsIter.hasNext())
   {
      String tp = tpsIter.next();
      boolean[] isAlphaTermFalse = new boolean[alphaTerms.length];
      for (int i=0; i < alphaTerms.length; i++)
      {
         if (alphaTerms[i].equals(term))
         {
            isAlphaTermFalse[i] = true;  continue;
         }
         for (int j=0; j < alphaTerms[i].length(); j++)
         {
            int index = uniqueLiterals.indexOf(alphaTerms[i].substring(j,j+1).toLowerCase());
            if (tp.substring(index,index+1).equals("0") &&
                Character.isLowerCase(alphaTerms[i].charAt(j)) ||
                tp.substring(index,index+1).equals("1") &&
                Character.isUpperCase(alphaTerms[i].charAt(j)))
            {
                isAlphaTermFalse[i] = true;
                break;
            }
         }
      }
      boolean areAllTermsFalse = true;
      for (int i=0; i < isAlphaTermFalse.length; i++)
      {
         if (!isAlphaTermFalse[i])
         {
            areAllTermsFalse = false;  break;
         }
      }
      if (areAllTermsFalse && isPointFeasible(tp))  utps.add(tp);
   }   
   return utps;      
   }

// convert aBcD to a!bc!d
private static String convertUppercaseToNegation(String input)
{
	String ret = "";
	
	for (int i=0; i < input.length(); i++)
	{
		if (Character.isUpperCase(input.charAt(i)))
		{
			ret = ret + "!" + input.substring(i,i+1).toLowerCase();
		}
		else
		{
			ret = ret + input.substring(i,i+1).toLowerCase();
		}
	}	
	return ret;
}

// produce an optimization model to minimize the number of tests needed to satisfy (or satisfy as much as possible) a criterion
private static void getMinimization(String type)
{
	String s = "";        
	String[] alphaTerms = alphaExpression.split(" \\+ ");
        String literalsOrTerms = type.equals("MUTP") ? "terms" : "literals";
        if (type.equals("MUTP") || type.equals("CUTPNFP") || type.equals("MNFP"))
        {
           feasibilityText = type + " is feasible for all " + literalsOrTerms + ".";
        }
        else
        {
        	feasibilityText = "";
        }
        //Set<String> utpsOrNfps = new LinkedHashSet<String>();
        //Map<String,String> constraintMap = new LinkedHashMap<String,String>();
        //Set<String> constraintSet = new LinkedHashSet<String>();
        
	if (alphaTerms.length == 1 && alphaTerms[0].length() ==1)
	{
		if (type.equals("Minimal-MUMCUT") || type.equals("Minimal-MUMCUT/SMOTP") ||
			type.equals("CUTPNFP"))
		{
		s += "MIN x0 + x1;\r\n\r\n";
		s += "SUBJECT TO\r\n\r\n";
		s += "x0 >= 1;\r\n";
		s += "x1 >= 1;\r\n\r\n";
		s += "BINARY\r\n\r\n";
		s += "x0\r\nx1\r\n\r\n";
		s += "END";
		}
		else if (type.equals("MUTP") || type.equals("MNFP") || type.equals("NFP"))
		{
			String point = "";
			if (type.equals("MUTP")) point = alphaTerms[0].equals("a") ? "x1" : "x0";
			else point = alphaTerms[0].equals("A") ? "x1" : "x0";
			s += "MIN " + point + ";\r\n\r\n";
			s += "SUBJECT TO\r\n\r\n";
			s += point + " >= 1;\r\n\r\n";
			s += "BINARY\r\n\r\n";
			s += point + "\r\n\r\n";
			s += "END";			
		}
	}
	else if (alphaTerms.length == 1)
	{			                
		 s = s + "SUBJECT TO\r\n\r\n";
		 
		 if (!type.equals("MNFP") && !type.equals("NFP"))
		 {
		    for (String bs : allUtps)
            {
	        	s  =  s + "x" + bs + " >= 1;\r\n";
            }
		 }
		 if (!type.equals("MUTP"))
		 {
		 for (String bs : allNfps)
         {
             s  =  s + "x" + bs + " >= 1;\r\n";
		 }		
		 }
		 String binary = "\r\nBINARY\r\n\r\n";			
		 String points = "";
			
			 for (String bs : _binaryStrings)
		     {
				if (s.indexOf(bs) != -1)
		        {
				   binary = binary + "x" + bs + " ";
		           points = points + "x" + bs + " + ";
		        }
		     }
			 binary = binary + "\r\n\r\n";
			 points = points.substring(0,points.length()-3) + ";\r\n\r\n";
			 
			 s = "MIN " + points + s + binary + "END";
	}
	else if (isEveryTermAOneLiteralTerm())
	{
		 s = s + "SUBJECT TO\r\n\r\n";
			
		 if (!type.equals("MUTP"))
		 {
		    for (String bs : allNfps)
            {
	           s  =  s + "x" + bs + " >= 1;\r\n";  break;
            }
		 }		 
		 if (!type.equals("MNFP") && !type.equals("NFP"))
		 {
                for (String bs : allUtps)
                {
	        	     s  =  s + "x" + bs + " >= 1;\r\n";
                }
		 }
		  String binary = "\r\nBINARY\r\n\r\n";			
		 String points = "";
		 
			 for (String bs : _binaryStrings)
		     {
				if (s.indexOf(bs) != -1)
		        {
				   binary = binary + "x" + bs + " ";
		           points = points + "x" + bs + " + ";
		        }
		     }
			 binary = binary + "\r\n\r\n";
			 points = points.substring(0,points.length()-3) + ";\r\n\r\n";
			 
			 s = "MIN " + points + s + binary + "END";
	}	
	else
	{	           
		Set<String> constraintSet = new HashSet<String>();
		int Mcount = 0;
		 s = s + "SUBJECT TO\r\n\r\n";
		 		 
		 for (int i=0; i < alphaTerms.length; i++)
		 {   
			 if (alphaTerms[i].length() == uniqueLiterals.length())
			 {
				 if (!type.equals("MNFP") && !type.equals("NFP"))
				 {
				 for (String bs : utpsMap.get(alphaTerms[i]))
		         {
			        s  =  s + "x" + bs + " >= 1;\r\n";
		         }
				 }
				 
				 if (!type.equals("MUTP"))
				 {
	                for (int z=0; z < alphaTerms[i].length(); z++)
		            {
	            	    for (String bs : nfpsMap.get(alphaTerms[i] + "_" + alphaTerms[i].substring(z,z+1)))
		                {
			        	     s  =  s + "x" + bs + " >= 1;\r\n";
		                }
		            }
				 }
			 }
			 /*else
			 {				 	
			 // this code implements the MUTP model using the fact that external literals must be 0 and 1
			 // it is commented out because the MUTP model based on LRF detection is equivalent			    
				if (type.equals("MUTP"))
				{
                    utpsOrNfps = utpsMap.get(alphaTerms[i]);                    
					 String literalsNotInTerm = getLiteralsNotInTerm(alphaTerms[i]);
					 					 
					 for (int z=0; z < literalsNotInTerm.length(); z++)
					 {
						 String literal = literalsNotInTerm.substring(z,z+1);
						 int index = uniqueLiterals.indexOf(literal);
                            constraintMap.put(alphaTerms[i]+literal+"0",""); 
                            constraintMap.put(alphaTerms[i]+literal+"1","");						
	                   
	                     for (String point : utpsOrNfps)
	                     {
	                        	   String key = alphaTerms[i]+literal + point.substring(index,index+1);
	                        	   
	                        	   String val = constraintMap.get(key);
	                        	   
	                        	   constraintMap.put(key, val + "x" + point + " + ");
						 }
					 }
					 
		                for (String key : constraintMap.keySet())
			            {
		                	String val = constraintMap.get(key);
		                	
		                	if (!val.equals(""))
		                	{
		                		constraintSet.add(val.substring(0,val.length() -3) + " >= 1;\r\n");
		                	}
		                	else
		                	{
		                		feasibilityText = "MUTP is not feasible for all terms.";
		                	}
			            }      
				}
				// this code implements the MNFP model using the fact that external literals must be 0 and 1
				// it is commented out because the MNFP model based on LRF detection is equivalent
				else if (type.equals("MNFP"))
			    { 
				    String literalsNotInTerm = getLiteralsNotInTerm(alphaTerms[i]);
				    
					for (int j=0; j < alphaTerms[i].length(); j++)
					{         
				       for (int z=0; z < literalsNotInTerm.length(); z++)
				       {
					      String literal = literalsNotInTerm.substring(z,z+1);					 
                          constraintMap.put(alphaTerms[i]+alphaTerms[i].substring(j,j+1)+literal+"0",""); 
                          constraintMap.put(alphaTerms[i]+alphaTerms[i].substring(j,j+1)+literal+"1","");
                          
                           int index = uniqueLiterals.indexOf(literal);
					       utpsOrNfps = nfpsMap.get(alphaTerms[i] + "_" + alphaTerms[i].substring(j,j+1));
					 			
                           for (String point : utpsOrNfps)
                           {
                     	      String key = alphaTerms[i]+alphaTerms[i].substring(j,j+1) + literal + point.substring(index,index+1);
                     	   
                     	      String val = constraintMap.get(key);
                     	      constraintMap.put(key, val + "x" + point + " + ");
                           }
					    }
					 }
				 
	                for (String key : constraintMap.keySet())
		            {
	                	String val = constraintMap.get(key);
	                	
	                	if (!val.equals(""))
	                	{
	                		constraintSet.add(val.substring(0,val.length() -3) + " >= 1;\r\n");
	                	}
	                	else
	                	{
	                		feasibilityText = "MNFP is not feasible for all literals.";
	                	}
		            }      
			}*/
			 else
			 {				 		
				// this code implements the MUTP model using TRF fault detection
				// it generates fewer constraints than the MUTP model based on LRF detection and the
				 // MUTP model based on external literals being 0 and 1
				if (type.equals("MUTP"))
				{					
					String[] trfMutants = getMutpTests(false,true,i).split("\n"); 
	    	       	   
			        setMutpFeasibilityForTerm(alphaTerms[i],i);
			        
		        	for (String trfMutant : trfMutants)
		        	{			        
			           for (String bs : utpsMap.get(alphaTerms[i]))
				       {
		                   if (!isATruePoint(bs,reverseConvertAlphaExpression(trfMutant,false)))
			               {
			        	      s  =  s + "x" + bs + " >= 1;\r\n"; break;
			               }
                        }
				    }

			        if (!isTermMutpFeasible[i])
			        {
			        	feasibilityText = "MUTP is not feasible for all terms.";
			        }
				}
				// this code implements the MUTP and MNFP model using LRF fault detection
				else if (/*type.equals("MUTP") || */type.equals("MNFP"))
				{					
					 String literalsNotInTerm = getLiteralsNotInTerm(alphaTerms[i]);
					 
					 for (int z=0; z < literalsNotInTerm.length(); z++)
					 {
	                    Set<String> lrfs = getLrfs(alphaTerms[i],literalsNotInTerm.substring(z,z+1));
					 
		                for (String lrf : lrfs)
			            {
		                	String constraint = "";  boolean isLrfEquiv = true;
		            	    for (String bs : nfpsTermMap.get(alphaTerms[i])) //need to change nfpsTermMap.get(alphaTerms[i]) to _binaryStrings if uncommenting the type.equals("MUTP") clause above
			                {
		            	    	//if ((type.equals("MUTP") && isATruePoint(bs,null) && !isATruePoint(bs,lrf)) ||
		            	    	//	(type.equals("MNFP") && !isATruePoint(bs,null) && isATruePoint(bs,lrf)))
		            	    	if (isATruePoint(bs,lrf))
				               {
		            	    		constraint = constraint + "x" + bs + " + "; isLrfEquiv = false;
				               }
			                }
                                    //if (isLrfEquiv && type.equals("MUTP")) feasibilityText = "MUTP is not feasible for all terms.";
                                    //else if (isLrfEquiv && type.equals("MNFP")) feasibilityText = "MNFP is not feasible for all literals.";
		            	           if (type.equals("MNFP") && isLrfEquiv) feasibilityText = "MNFP is not feasible for all literals.";
		            	    
                                       if (!constraint.equals("") && constraintSet.add(constraint) && constraint.endsWith(" + "))
					              s = s + constraint.substring(0,constraint.length()-3) + " >= 1;\r\n";
	                    }
					 }
					 for (int z=0; z < literalsNotInTerm.length(); z++)
					 {
	                    Set<String> lrfs = getLrfs(alphaTerms[i],literalsNotInTerm.substring(z,z+1).toUpperCase());
					 
		                for (String lrf : lrfs)
			            {
		                	String constraint = ""; boolean isLrfEquiv = true;
		                	for (String bs : nfpsTermMap.get(alphaTerms[i])) //need to change nfpsTermMap.get(alphaTerms[i]) to _binaryStrings if uncommenting the type.equals("MUTP") clause above
			                {
		            	    	//if ((type.equals("MUTP") && isATruePoint(bs,null) && !isATruePoint(bs,lrf)) ||
		            	    	//	(type.equals("MNFP") && !isATruePoint(bs,null) && isATruePoint(bs,lrf)))
		            	    	if (isATruePoint(bs,lrf))
				               {
		            	    		constraint = constraint + "x" + bs + " + "; isLrfEquiv = false;
					            }
				            }
                                    //if (isLrfEquiv && type.equals("MUTP")) feasibilityText = "MUTP is not feasible for all terms.";
                                    //else if (isLrfEquiv && type.equals("MNFP")) feasibilityText = "MNFP is not feasible for all literals.";
                                    
		                	if (type.equals("MFNP") && isLrfEquiv) feasibilityText = "MNFP is not feasible for all literals.";
			            	    if (!constraint.equals("") && constraintSet.add(constraint) && constraint.endsWith(" + "))
						              s = s + constraint.substring(0,constraint.length()-3) + " >= 1;\r\n";
	                    }
					 }
				}
				else if (type.equals("CUTPNFP"))
				{		
					Set<String> utps = utpsMap.get(alphaTerms[i]);  
					boolean isLrfEquiv = false;	
					
					for (String utp : utps)
					{										
						String temp = s;						
						temp = temp + "x" + utp + " - M" + ++Mcount + " <= 0;\r\n";
					       
					    for (int z=0; z < alphaTerms[i].length(); z++)
					    {
					       Set<String> nfps = nfpsMap.get(alphaTerms[i] + "_" + alphaTerms[i].substring(z,z+1));

                           boolean feasible = false;
					
                           String nonCorrespondingNfp = "";
                           
			                for (String nfp : nfps)
							{					            	    
				               int count = 0;
				                
			                   for (int u=0; u < utp.length(); u++)
			                   {
			                      if (nfp.substring(u,u+1).equals(utp.substring(u,u+1)))
			                      {
			                         count++;
			                      }
			                   }
			                   if (count == nfp.length()-1)
			                   {
			                	   temp = temp + "x" + nfp + " + ";
			                	   feasible = true;
			                   }
			                   else
			                   {
			                	   nonCorrespondingNfp = nfp;
			                   }
							}
					     if (!feasible)
					    	 {
					    		 temp = temp + "x" + nonCorrespondingNfp +  " + ";  isLrfEquiv = true;
					    	 }
					     }	
					     s = temp.substring(0,temp.length()-3) + " - " + alphaTerms[i].length() + "M" + Mcount + " >= 0;\r\n";					     
					}                     

					for (String utp : utps)
					{
						s  =  s + "x" + utp + " + "; 
					}
                    if (isLrfEquiv) feasibilityText = "CUTPNFP is not feasible for all literals.";
					if (s.endsWith(" + "))
					s = s.substring(0,s.length()-3) + " >= 1;\r\n";
				}
				else if (type.equals("NFP")) 
				{
					if (alphaTerms[i].length() != 1)
					{
							 Set<String> lofs = getLofs(alphaTerms[i],alphaTerms[i].length());						 

							 for (String lof : lofs)
							 {
					              for (String bs : nfpsTermMap.get(alphaTerms[i]))
					              {
					                   if (isATruePoint(bs,lof))
						               {
					            	          s  =  s + "x" + bs + " + ";
					            	    }
					               }
					               s = s.substring(0,s.length()-3) + " >= 1;\r\n"; 
							 }	
					}
				}
			    else 
				{
			    	String[] trfMutants = getMutpTests(false,true,i).split("\n"); 
			    	       	   
			        setMutpFeasibilityForTerm(alphaTerms[i],i);

		        	for (String trfMutant : trfMutants)
		        	{			        
			           for (String bs : utpsMap.get(alphaTerms[i]))
				       {
		                   if (!isATruePoint(bs,reverseConvertAlphaExpression(trfMutant,false)))
			               {
			        	      s  =  s + "x" + bs + " >= 1;\r\n"; break;
			               }
                        }
				    }

			        if (!isTermMutpFeasible[i])
			        {
			        	String extLiterals = literalsThatMakeATermMutpInfeasible.get(i);
			        	
			        	for (int x=0; x < extLiterals.length(); x++)
			        	{
						  Set<String> lrfs = getLrfs(alphaTerms[i],extLiterals.substring(x,x+1));
						
						  for (String lrf : lrfs)
				          {
						    boolean lrfEquiv = true;
							 					 
						    for (String bs : nfpsTermMap.get(alphaTerms[i]))
						    {
				                if (isATruePoint(bs,lrf))
					            {
					        	   s  =  s + "x" + bs + " + ";  lrfEquiv = false;
                                }
				      		 }							 
							 if (!lrfEquiv)
							 {
							    s = s.substring(0,s.length()-3) + " >= 1;\r\n";
							 }
							 else
							 {
								 String literal = "";								 
								 String lrfTerm = lrf.split(" \\+ ")[i];
								 
								 for (int k=0; k < alphaTerms[i].length(); k++)
								 {
									 if (lrfTerm.indexOf(alphaTerms[i].substring(k,k+1)) == -1)
									 {
										 literal = alphaTerms[i].substring(k,k+1); break;
									 }
								 }
								
								 for (String bs : nfpsMap.get(alphaTerms[i] + "_" + literal))
						         {
									 s  =  s + "x" + bs + " + ";
						         }
						         s = s.substring(0,s.length()-3) + " >= 1;\r\n"; 
				            }
				          }
					  }
		         }
				else if (alphaTerms[i].length() != 1)
				{
						 Set<String> lofs = getLofs(alphaTerms[i],alphaTerms[i].length());						 

						 for (String lof : lofs)
						 {
				              for (String bs : nfpsTermMap.get(alphaTerms[i]))
				              {
				                   if (isATruePoint(bs,lof))
					               {
				            	          s  =  s + "x" + bs + " + ";
				            	    }
				               }
				               s = s.substring(0,s.length()-3) + " >= 1;\r\n"; 
						 }	
				}
			 }
		 }			 
		 } 
						 if (type.indexOf("SMOTP") != -1)
						 {
							 for (int x=0; x < alphaTerms.length; x++)
							 {
							 setMutpFeasibilityForTerm(alphaTerms[x], x);
							 }
							 for (int x=0; x < alphaTerms.length; x++)
							 {
							 lifLifPoints.clear();
                             createLifLifMutants(true,x); 
							 
                             String oldTermsInLifLif = null;
                             String newTermsInLifLif = null;
							 for (String point : lifLifPoints.keySet())
							 {
								 newTermsInLifLif = point.split(",")[1];
								 if (oldTermsInLifLif == null || newTermsInLifLif.equals(oldTermsInLifLif))
								 {
								 s  =  s + "x" + point.split(",")[0] + " + ";								 
								 }
								 else
								 {
									 if (s.endsWith(" + "))
										 s = s.substring(0,s.length()-3)  + " >= 1;\r\n";
								 }
								 oldTermsInLifLif = newTermsInLifLif;
							 }
							 if (s.endsWith(" + "))
							 s = s.substring(0,s.length()-3)  + " >= 1;\r\n";
							 }
						 }

			String binary = "\r\nBINARY\r\n\r\n";			
			String points = "";
			int varCounter=0;
			
			 for (String bs : _binaryStrings)
		     {
				if (s.indexOf(bs) != -1)
		        {
					varCounter++;
				   binary = binary + "x" + bs + "\r\n";
		           points = points + "x" + bs + " + ";
		         }
		     }
			 //System.out.println(varCounter + Mcount);
			 //System.out.println(s.split(";").length - 1);
			 for (int x=1; x <= Mcount; x++)
			 {
				 binary = binary + "M" + x + "\r\n";
			 }
			 binary = binary + "\r\n";
			 points = points.substring(0,points.length()-3) + ";\r\n\r\n";
			 
			 s = "MIN " + points + s + binary + "END";
	}
	//displaySolutionOrGenerateModel(s,type + " Minimization");
	minimizationData = s;
}

// return a list of literals (in lower case) that are in the predicate as a whole but not in the given term
private static String getLiteralsNotInTerm(String term)
{
   String literalsNotInTerm = "";

   for (int j=0; j < uniqueLiterals.length(); j++)
   {
	  if (term.toLowerCase().indexOf(uniqueLiterals.substring(j,j+1)) == -1)
	  {
		literalsNotInTerm = literalsNotInTerm + uniqueLiterals.substring(j,j+1);
	  }
   }
   return literalsNotInTerm;
}

// produce an optimization model to maximize the number of faults detected subject to a certain max test set size
private static void getMaximization()
{
	String s = "";
    Map<String,String> faultAndPoints = new HashMap<String,String>();
	String[] alphaTerms = alphaExpression.split(" \\+ ");
	
	if (alphaTerms.length == 1 && alphaTerms[0].length() ==1)
	{
		s = "{Total number of faults that can be detected = 2}\r\n\r\n" +
	    "MAX FALSE + TRUE;\r\n\r\nSUBJECT TO\r\n\r\nx0 + x1 <= " + maxTestSetSize + ";\r\n" +
	    "x0 >= MTRUE;\r\nTRUE <= MTRUE;\r\nx1 >= MFALSE;\r\nFALSE <= MFALSE;\r\n\r\n" +
	    "BINARY\r\n\r\n" +
	    "x0\r\nx1\r\nFALSE\r\nTRUE\r\nMFALSE\r\nMTRUE\r\n\r\nEND";
	}
	else if (alphaTerms.length == 1)
	{
		String obj = "MAX FALSE + ";		
		String truePoint = "";
		
		for (String bs : allUtps)
		{
				truePoint =bs; break;
		}		
		String constraints = "x" + truePoint + " >= MFALSE;\r\nFALSE <= MFALSE;\r\n";		
		String binary = "\r\n\r\nBINARY\r\n\r\nFALSE\r\nMFALSE\r\n";
		
		addLofPoints(faultAndPoints);
		addLnfPoints(faultAndPoints);
		addEnfPoints(faultAndPoints);
		addCorfPoints(faultAndPoints);
		
		Set<String> keySet = faultAndPoints.keySet();
		
		for (String key : keySet)
		{
			binary = binary + key + "\r\nM" + key + "\r\n";
			
			obj = obj + key + " + ";
			
			constraints = constraints + 
			              faultAndPoints.get(key) + " - M" + key + " >= 0;\r\n" +
			              key                     + " - M" + key + " <= 0;\r\n";
		}		
		int size = keySet.size()+1;
		String comment = "{Total number of faults that can be detected = " + size + "}\r\n\r\n";
		s = comment + obj.substring(0,obj.length()-3) + ";\r\n\r\n";
		
		for (String bs : _binaryStrings)
		{
			constraints = constraints + "x" + bs + " + ";
			binary = binary + "x" + bs + "\r\n";
		}		
		constraints = constraints.substring(0,constraints.length()-3) + " <= " + maxTestSetSize + ";";
		
		s = s + "SUBJECT TO\r\n\r\n" + constraints + binary + "\r\nEND";	
	}
	else if (isEveryTermAOneLiteralTerm())
	{
		String obj = "MAX TRUE + ";		
		String falsePoint = "";
		
		for (String bs : allNfps)
		{
				falsePoint =bs; break;
		}
		
		String constraints = "x" + falsePoint + " >= MTRUE;\r\nTRUE <= MTRUE;\r\n";		
		String binary = "\r\n\r\nBINARY\r\n\r\nTRUE\r\nMTRUE\r\n";
		
		addTofPoints(faultAndPoints);
		addLnfPoints(faultAndPoints);
		addEnfPoints(faultAndPoints);
		addTnfPoints(faultAndPoints);
		addDorfPoints(faultAndPoints);
		
		Set<String> keySet = faultAndPoints.keySet();
		
		for (String key : keySet)
		{
			binary = binary + key + "\r\nM" + key + "\r\n";
			
			obj = obj + key + " + ";
			
			constraints = constraints + 
			              faultAndPoints.get(key) + " - M" + key + " >= 0;\r\n" +
                          key                     + " - M" + key + " <= 0;\r\n";
		}
		
		int size = keySet.size()+1;
		String comment = "{Total number of faults that can be detected = " + size + "}\r\n\r\n";
		s = comment + obj.substring(0,obj.length()-3) + ";\r\n\r\n";
		
		for (String bs : _binaryStrings)
		{
			constraints = constraints + "x" + bs + " + ";
			binary = binary + "x" + bs + "\r\n";
		}
		constraints = constraints.substring(0,constraints.length()-3) + " <= " + maxTestSetSize + ";";
		
		s = s + "SUBJECT TO\r\n\r\n" + constraints + binary + "\r\nEND";
	}	
	else
	{
	    String obj = "MAX ";				
		String constraints = "";		
		String binary = "\r\n\r\nBINARY\r\n\r\n";
		
		addTofPoints(faultAndPoints);
		addLnfPoints(faultAndPoints);
		addEnfPoints(faultAndPoints);
		addTnfPoints(faultAndPoints);
		addDorfPoints(faultAndPoints);
		addLofPoints(faultAndPoints);
		addCorfPoints(faultAndPoints);
		addLifPoints(faultAndPoints);
		addLrfPoints(faultAndPoints);
				
		Set<String> keySet = faultAndPoints.keySet();
		
		for (String key : keySet)
		{
			binary = binary + key + "\r\nM" + key + "\r\n";
			
			obj = obj + key + " + ";
			
			constraints = constraints + 
			              faultAndPoints.get(key) + " - M" + key + " >= 0;\r\n" +
                          key                     + " - M" + key + " <= 0;\r\n";
		}
		
		String comment = "{Total number of faults that can be detected = " + keySet.size() + "}\r\n\r\n";
		s = comment + obj.substring(0,obj.length()-3) + ";\r\n\r\n";		
				
		for (String bs : _binaryStrings)
		{
				constraints = constraints + "x" + bs + " + ";			
			   binary = binary + "x" + bs + "\r\n";
		}
		
		constraints = constraints.substring(0,constraints.length()-3) + " <= " + maxTestSetSize +";";
		
		s = s + "SUBJECT TO\r\n\r\n" + constraints + binary + "\r\nEND";
		
		//System.out.println(_binaryStrings.size() + keySet.size()*2);
		//System.out.println(s.split(";").length - 2);
	}
	//displaySolutionOrGenerateModel(s,"Fault Detection Maximization");
	maximizationData = s;
}

// parses the mpl solution file to get the test data, mutants, and mutant types
private static List<String> parseFile(String fileName, String type)
{
	String contents = readFile(fileName);
	
	int objIndex = contents.indexOf("Objective");
	int colonIndex = contents.indexOf(":",objIndex);
	int decimalIndex = contents.indexOf(".",colonIndex);
	String obj = contents.substring(colonIndex+1,decimalIndex).trim();
	
	String tests = "";
	String mutants = "";
	int varIndex = contents.indexOf("Variable Name");
	int xIndex = contents.indexOf("x",varIndex);
	while (xIndex != -1)
	{
	if (contents.indexOf("1.0",xIndex) < contents.indexOf("0.0",xIndex))
	{
		String point = contents.substring(xIndex+1,contents.indexOf("1.0",xIndex)).trim();
		
		List<String> testAndMutant = getTestAndMutantForPoint(point,type);
		tests = tests + point + testAndMutant.get(0);
		mutants = mutants + convertAlphaExpression(testAndMutant.get(1)) + "\n";		
	}
	xIndex = contents.indexOf("x",xIndex+1);
	}

        if (type.equals("Fault Detection Maximization"))
        {
	   tests = tests + "\nNumber of faults detected by this test set: " + obj;	
        }
        else
        {
           tests = tests + feasibilityText + "\nNumber of tests: " + obj;
        }
		
	int numNon = numFalse + numTrue + numTofs + numTifs + numTrfs;
	String text = "Number of False mutants generated: " + numFalse + "\n" +
	   "Number of True mutants generated: " + numTrue + "\n" +
	   "Number of TOF mutants generated: " + numTofs + "\n" +
	   "Number of TIF mutants generated: " + numTifs + "\n" +	
	   "Number of TRF mutants generated: " + numTrfs + "\n" +  
	   "Total Number of Non-Equivalent Mutants Generated: " +      numNon;

	List<String> retList = new ArrayList<String>();
	retList.add(tests);  retList.add(mutants); retList.add(text);
	return retList;
}

// for term ab and point 1100 in ab + cd, this returns abc + abd + cd
private static String generateTrfMutant(String point, Set<String> overlapTermsSet)
{
   String mutant = alphaExpression;	String trf="";  

   for (String term : overlapTermsSet)
   {
      for (int x=0; x < uniqueLiterals.length(); x++)
      {
	  if (term.toLowerCase().indexOf(uniqueLiterals.substring(x,x+1)) == -1)
	  {
		if (point.substring(x,x+1).equals("0")) 
		{
			trf = trf + term + uniqueLiterals.substring(x,x+1) + " + ";
		}
		else
		{
			trf = trf + term + uniqueLiterals.substring(x,x+1).toUpperCase() + " + ";
		}
	  }
      }
      trf = trf.substring(0,trf.length()-3);

      mutant = mutant.replace(term,trf);

      trf = "";
   }
   return mutant;
}

// for a given point, return whether it is a UTP or NFP and the mutant it kills
private static List<String> getTestAndMutantForPoint(String point, String type)
{
	List<String> retList = new ArrayList<String>();
	String nfpText= " - NFP for ";
	String otpText = " - OTP for ";
	String[] terms = alphaExpression.split(" \\+ ");
    Set<String> overlapTermsSet = new LinkedHashSet<String>();
    Set<String> uniqueTermsSet = new LinkedHashSet<String>();
	
	for (int i=0; i < terms.length; i++)
	{
		String term = terms[i];  
		
		if (!type.equals("MNFP") && utpsMap.get(term).contains(point))
		{
			retList.add(" - UTP for term " + convertUppercaseToNegation(term) + "\n"); 
			if (term.length() == uniqueLiterals.length())
			{
				if (terms.length == 1)
				{
					retList.add("(false)"); numFalse++;
				}
				else if (i < terms.length - 1)
				{
					retList.add(alphaExpression.replace(term + " + ", "")); numTofs++;
				}
				else
				{
					retList.add(alphaExpression.replace(" + " + term, "")); numTofs++;
				}
			}
			else
			{
			   uniqueTermsSet.add(term);	
			   retList.add(generateTrfMutant(point,uniqueTermsSet));
			   numTrfs++;
			}
			break;
		}
		if (!type.equals("MUTP"))
		{
		   boolean isANfp = false;
		   
		   for (int j=0; j < term.length(); j++)
		   {
			  if (nfpsMap.get(term + "_" + term.substring(j,j+1)).contains(point))
			  {				
				 isANfp = true;
				 nfpText = nfpText + "literal " + term.substring(j,j+1).toLowerCase() + " in term " + convertUppercaseToNegation(term) + ", ";
			  }
		   }
		   
		   if (!isANfp && type.indexOf("SMOTP") != -1)
		   {
			   String overlapTerms = getOverlappingTerms(point,term);
			   			   
			   if (!overlapTerms.equals(""))
			   {
				   overlapTermsSet.add(term);
				   otpText = otpText + "term " + convertUppercaseToNegation(term); 
				  			   
			      for (String overlapTerm : overlapTerms.split(","))
			      {
                        if (overlapTermsSet.add(overlapTerm))
                        {
			            otpText = otpText + " and term " + convertUppercaseToNegation(overlapTerm);
                        }
			      }
		      }
		   }		
	    }
	}
	if (!nfpText.equals(" - NFP for "))  
	{
		retList.add(nfpText.substring(0,nfpText.length()-2) + "\n");
		if ((terms.length == 1 && terms[0].length() == 1) || isEveryTermAOneLiteralTerm())
		{
			retList.add("(true)"); numTrue++;
		}
		else
		{
		   retList.add(alphaExpression + " + " + convertPointToTerm(point));
		   numTifs++;
		}
	}	
	if (!otpText.equals(" - OTP for "))
	{
		retList.add(otpText + "\n");
		retList.add(generateTrfMutant(point,overlapTermsSet));
		numTrfs++;
	}
	return retList;
}

// returns terms that shares an overlapping true point with another term
private static String getOverlappingTerms(String point, String term)
{
	if (!isATruePoint(point,null)) return "";
	
	if (utpsMap.get(term).contains(point)) return "";
	
	String overlapTerms = "";
	boolean reachedTerm = false;
	
	for (String alphaTerm : alphaExpression.split(" \\+ "))
	{
		if (!reachedTerm && !alphaTerm.equals(term)) continue;
		else reachedTerm = true;
		
		if (!term.equals(alphaTerm) && isATruePoint(point,alphaTerm + term))
		{
			overlapTerms = overlapTerms + alphaTerm + ",";
		}
	}
	if (overlapTerms.equals("")) return "";
	return overlapTerms.substring(0,overlapTerms.length()-1);
}

// reads a file into a string
private static String readFile(String fileName)
{
   int fileSize = 0;   char[] buf = null;   String contents = "";
   try
   {
      FileReader fileReader = new FileReader(fileName);  FileReader fileReader2 = new FileReader(fileName);

      while (true)
      {
         if (fileReader.read() == -1) break;
         fileSize++;
      }
      buf = new char[fileSize];  fileReader2.read(buf);

      for (int j=0; j < buf.length; j++)  { contents += buf[j]; }
   }
   catch (Exception e) { e.printStackTrace(); System.exit(0); }
   return contents;
}

// calls the mpl solver
/*private static class Solver implements Runnable
{
	String type;
	
	private Solver(String type)
	{
		this.type = type;
	}
	public void run()
	{
		try
		{   Process p4 = Runtime.getRuntime().exec("C:\\Mplwin4\\Mplwin42.exe SOLVE C:\\Mplwin4\\opt.mpl");
            p4.waitFor();
		    List<String> data = parseFile("C:\\Mplwin4\\opt.sol",type);*/
		 //   /*Nan*/ gui.dataArea.setText(toUpperTermsAndLiterals(data.get(0)));
		  //  /*Nan*/ gui.mutantArea.setText(toUpper(data.get(1)));
/*    gui.mutantTypesArea.setText(data.get(2));
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			JDialog dialog = new JDialog(new JFrame(),"MPL Solver Error");
            dialog.setSize(500,100);

            JTextArea textArea = new JTextArea(
           	 	 "\nThe MPL Solver could not parse the problem model." +
                    "\nIt is likely that the size of the model is too large." +
                    "\nPlease try a predicate with fewer literals and terms.");
           
            textArea.setEditable(false);                         
            dialog.add(textArea);
            dialog.setVisible(true);  return;
		}
	}
}*/
// populate a map where each key is a description of a LOF and the value is a String of points, each of which can detect the LOF key
private static void addLofPoints(Map<String,String> faultAndPoints)
{		
	for (int i=0; i < alphaTerms.length; i++)
	{		
		int I=i+1;		
		String term = alphaTerms[i];		
		if (term.length() == 1) continue;
		
		for (int j=0; j < term.length(); j++)
		{			
			int J=j+1;
			
			String points = "";
			
			for (String bs : nfpsMap.get(alphaTerms[i] + "_" + term.substring(j,j+1)))
			{
				   points = points + "x" + bs + " + ";
			}
			faultAndPoints.put("LOF_" + I + "_" + J,points.substring(0,points.length()-3));			
		}
	}
}

// populate a map where each key is a description of an ORF. and the value is a String of points, each of which can detect the ORF. key
private static void addCorfPoints(Map<String,String> faultAndPoints)
{		
	for (int i=0; i < alphaTerms.length; i++)
	{
		int I=i+1;	
		String term = alphaTerms[i];
		
		for (int j=0; j < term.length(); j++)
		{
			int J=j+1;			
			String mutant = "";
			
			if (j < term.length() -1)
			{
				mutant = alphaExpression.replace(term,term.substring(0,j) + " + " + term.substring(j+1));
			}
			else
			{
				break;
			}
			
			String points = "";
			
			for (String bs : _binaryStrings)
			{
				if (!isATruePoint(bs,null) && isATruePoint(bs,mutant))
				{
				   points = points + "x" + bs + " + ";
				}
			}
			faultAndPoints.put("ORF_AND_" + I + "_" + J,points.substring(0,points.length()-3));			
		}
	}
}

// populate a map where each key is a description of an ENF and the value is a String of points, each of which can detect the ENF key
private static void addEnfPoints(Map<String,String> faultAndPoints)
{
	String points = "";
			
			for (String bs : _binaryStrings)
			{
				points = points + "x" + bs + " + ";
			}
			faultAndPoints.put("ENF",points.substring(0,points.length()-3));
}

// populate a map where each key is a description of a TNF and the value is a String of points, each of which can detect the TNF key
private static void addTnfPoints(Map<String,String> faultAndPoints)
{		
	for (int i=0; i < alphaTerms.length; i++)
	{
		int I=i+1;		
		String term = alphaTerms[i];		
		String negatedTerm = "";
		
		for (int j=0; j < term.length(); j++)
		{
			negatedTerm = negatedTerm + reverseCase(term.substring(j,j+1)) + " + ";
		}
		
		negatedTerm = negatedTerm.substring(0,negatedTerm.length()-3);
			
		String mutant = alphaExpression.replace(term,negatedTerm);
		
			String points = "";
			
			for (String bs : _binaryStrings)
			{
				if ((!isATruePoint(bs,null) && isATruePoint(bs,mutant)) ||
					(isATruePoint(bs,null) && !isATruePoint(bs,mutant))	)
				{
				   points = points + "x" + bs + " + ";
				}
			}
			faultAndPoints.put("TNF_" + I,points.substring(0,points.length()-3));			
	}
}

// populate a map where each key is a description of an ORF+ and the value is a String of points, each of which can detect the ORF+ key
private static void addDorfPoints(Map<String,String> faultAndPoints)
{		
	for (int i=0; i < alphaTerms.length; i++)
	{
		int I=i+1;		
		String term = alphaTerms[i];
		String mutant = "";
		
		if (i < alphaTerms.length - 1)
		{
			mutant = alphaExpression.replace(term + " + ", term);
		}
		else
		{
			break;
		}
				
		String points = "";
			
			for (String bs : allUtps)
			{
				if (!isATruePoint(bs,mutant))
				{
				   points = points + "x" + bs + " + ";
				}
			}
			faultAndPoints.put("ORF_OR_" + I,points.substring(0,points.length()-3));			
	}
}

// populate a map where each key is a description of a TOF and the value is a String of points, each of which can detect the TOF key
private static void addTofPoints(Map<String,String> faultAndPoints)
{		
	for (int i=0; i < alphaTerms.length; i++)
	{
		int I=i+1;		
		String term = alphaTerms[i];
		
		String points = "";
			
			for (String bs : utpsMap.get(term))
			{
				   points = points + "x" + bs + " + ";
			}
			faultAndPoints.put("TOF_" + I,points.substring(0,points.length()-3));			
	}
}

// populate a map where each key is a description of an LIF and the value is a String of points, each of which can detect the LIF key
private static void addLifPoints(Map<String,String> faultAndPoints)
{		
	for (int i=0; i < alphaTerms.length; i++)
	{		
		int I=i+1;		
		String term = alphaTerms[i];		
		String literalsNotInTerm = getLiteralsNotInTerm(term);
		
		for (int j=0; j < literalsNotInTerm.length(); j++)
		{
		   String mutant = alphaExpression.replace(term, term + literalsNotInTerm.substring(j,j+1));				
		   String points = "";
		   
			for (String bs : utpsMap.get(term))
			{
				if (!isATruePoint(bs,mutant))
				{
				   points = points + "x" + bs + " + ";
				}
			}
			if (!points.equals(""))
			{
			faultAndPoints.put("LIF_" + I + "_" + literalsNotInTerm.substring(j,j+1),
					           points.substring(0,points.length()-3));
			}
	    }
		
		for (int j=0; j < literalsNotInTerm.length(); j++)
		{
		   String mutant = alphaExpression.replace(term, term + literalsNotInTerm.substring(j,j+1).toUpperCase());				
		   String points = "";
			
			for (String bs : utpsMap.get(term))
			{
				if (!isATruePoint(bs,mutant))
				{
				   points = points + "x" + bs + " + ";
				}
			}			
			if (!points.equals(""))
			{
			faultAndPoints.put("LIF_" + I + "_" + literalsNotInTerm.substring(j,j+1).toUpperCase(),
					           points.substring(0,points.length()-3));	
			}
	    }
	}
}

// populate a map where each key is a description of an LRF and the value is a String of points, each of which can detect the LRF key
private static void addLrfPoints(Map<String,String> faultAndPoints)
{		
	for (int i=0; i < alphaTerms.length; i++)
	{
		int I=i+1;		
		String term = alphaTerms[i];		
		String literalsNotInTerm = getLiteralsNotInTerm(term);
		
		for (int j=0; j < term.length(); j++)
		{
			int J=j+1;			
			String mutant = "";
			
			for (int k=0; k < literalsNotInTerm.length(); k++)
		    {
				if (j < term.length() -1)
				{
					mutant = alphaExpression.replace(term,
							term.substring(0,j) + literalsNotInTerm.substring(k,k+1) + term.substring(j+1));
				}
				else
				{
					mutant = alphaExpression.replace(term,
							term.substring(0,j) + literalsNotInTerm.substring(k,k+1));
				}				
				
		        String points = "";
			
			    for (String bs : _binaryStrings)
			    {
				   if ((isATruePoint(bs,null) && !isATruePoint(bs,mutant)) ||
					   (!isATruePoint(bs,null) && isATruePoint(bs,mutant)))
				   {
				      points = points + "x" + bs + " + ";
				   }
			   }
			    if (!points.equals(""))
			    {
			       faultAndPoints.put("LRF_" + I + "_" + J + "_" + literalsNotInTerm.substring(k,k+1),
					           points.substring(0,points.length()-3));
			    }
		    }
			
			for (int k=0; k < literalsNotInTerm.length(); k++)
		    {
				if (j < term.length() -1)
				{
					mutant = alphaExpression.replace(term,
							term.substring(0,j) + literalsNotInTerm.substring(k,k+1).toUpperCase() + term.substring(j+1));
				}
				else
				{
					mutant = alphaExpression.replace(term,
							term.substring(0,j) + literalsNotInTerm.substring(k,k+1).toUpperCase());
				}
				
		        String points = "";
			
			    for (String bs : _binaryStrings)
			    {
				   if ((isATruePoint(bs,null) && !isATruePoint(bs,mutant)) ||
					   (!isATruePoint(bs,null) && isATruePoint(bs,mutant)))
				   {
				      points = points + "x" + bs + " + ";
				   }
			   }
			    if (!points.equals(""))
			    {
			      faultAndPoints.put("LRF_" + I + "_" + J + "_" + literalsNotInTerm.substring(k,k+1).toUpperCase(),
					           points.substring(0,points.length()-3));
			    }
		    }
	    }
	}
}

// populate a map where each key is a description of an LNF and the value is a String of points, each of which can detect the LNF key
private static void addLnfPoints(Map<String,String> faultAndPoints)
{		
	for (int i=0; i < alphaTerms.length; i++)
	{
		int I=i+1;		
		String term = alphaTerms[i];		
		if (term.length() ==1)  continue;
		
		for (int j=0; j < term.length(); j++)
		{
			int J=j+1;			
			String mutant = "";
			
			if (j < term.length() -1)
			{
				mutant = alphaExpression.replace(term,
						term.substring(0,j) + reverseCase(term.substring(j,j+1)) + term.substring(j+1));
			}
			else
			{
				mutant = alphaExpression.replace(term,term.substring(0,j) + reverseCase(term.substring(j,j+1)));
			}
			
			String points = "";
			
			for (String bs : _binaryStrings)
			{
				if ((!isATruePoint(bs,null) && isATruePoint(bs,mutant)) ||
					(isATruePoint(bs,null) && !isATruePoint(bs,mutant)))	
				{
				   points = points + "x" + bs + " + ";
				}
			}
			if (!points.equals(""))
			faultAndPoints.put("LNF_" + I + "_" + J,points.substring(0,points.length()-3));			
		}
	}
}

// return all LIF mutants for a term
private static Set<String> getLifs(String term)
{
	Set<String> lifs = new LinkedHashSet<String>();	
	String[] alphaTerms = alphaExpression.split( " \\+ ");
	
	for (int i=0; i < alphaTerms.length; i++)
	{
		String literals = getLiteralsInTerm2NotInTerm1(term.toLowerCase(),alphaTerms[i].toLowerCase());
		
		for (int j=0; j < literals.length(); j++)
		{
			lifs.add(alphaExpression.replace(term,term+literals.substring(j,j+1)));
			lifs.add(alphaExpression.replace(term,term+reverseCase(literals.substring(j,j+1))));
		}
	}	
	return lifs;
}

// return all LRF mutants for a term and external literal
private static Set<String> getLrfs(String term, String literal)
{
	Set<String> lrfs = new LinkedHashSet<String>();
	
	for (int i=0; i < term.length(); i++)
	{
		lrfs.add(alphaExpression.replace(term,term.replace(term.substring(i,i+1),literal)));
	}	
	return lrfs;
}

// return a LOF mutant 
private static String getLof(String term, String literal)
{
	String lof = term.replace(literal, "");	
	return alphaExpression.replace(term,lof);
}

// return LOF mutants for a given term
private static Set<String> getLofs(String term, int length)
{
	Set<String> lofs = new LinkedHashSet<String>();
	
	for (int i=0; i < length; i++)
    {
		if (i < length - 1)
		{
		lofs.add(alphaExpression.replace(term,term.substring(0,i) + term.substring(i+1)));
		}
		else
		{
			lofs.add(alphaExpression.replace(term,term.substring(0,i)));
		}
    }
	return lofs;
}

// return a TOF mutant
private static String getTof(String term, int index)
{
	if (index != alphaExpression.split(" \\+ ").length - 1)
	{
	   return alphaExpression.replace(term + " + ","");
	}
	else
	{
		return alphaExpression.replace(" + " + term,"");
    }
}

// get tests to achieve mutp and mutants that can only be killed by a mutp test set
private static String getMutpTests(boolean outputStats, boolean forModel, int index)
{
	boolean isFeasible = true;
	
   Map<String,String> pointsMap = new LinkedHashMap<String,String>();
   Set<String> mutants = new LinkedHashSet<String>();
    
   String[] alphaTerms = alphaExpression.split(" \\+ ");
   
   for (int i=0; i < alphaTerms.length; i++)
   {	   
       if (index != i && index != -1) continue;
       
	   String term = alphaTerms[i];       
        Set<String> utps = utpsMap.get(term);        
        Set<Integer> indicesOfLiterals = new LinkedHashSet<Integer>();
        Set<String> abbrevUtps = new LinkedHashSet<String>();
        
        for (int t=0; t < term.length(); t++)
        {
        	indicesOfLiterals.add(uniqueLiterals.indexOf(term.substring(t,t+1).toLowerCase()));
        }
        
        for (String utp : utps)
        {
        	String abbrevUtp = "";
        	
        	for (int t=0; t < utp.length(); t++)
        	{
        		if (!indicesOfLiterals.contains(t))
        		{
        			abbrevUtp = abbrevUtp + utp.substring(t,t+1);
        		}
        	}
        	
        	abbrevUtps.add(abbrevUtp);
        }
        Set<String> abbrevPointsForTerm = new LinkedHashSet<String>(getMutpOrMnfpTestsBasedOnMutpGreedy(new ArrayList<String>(abbrevUtps),uniqueLiterals.length()-term.length(),null));
        
        if (!isMutpOrMnfpFeasible(abbrevPointsForTerm,uniqueLiterals.length()-term.length())) isFeasible = false;
                
        for (String abbrevPoint : abbrevPointsForTerm)
        {
        	String mutant = "";            
        	String point = ""; int termIndex = -1;  int abbrevPointIndex = -1;
        	
        	for (int x=0; x < uniqueLiterals.length(); x++)
        	{
        		if (term.toLowerCase().indexOf(uniqueLiterals.substring(x,x+1)) != -1)
        		{
        			termIndex++;
        			if (Character.isUpperCase(alphabetize(term).charAt(termIndex)))
        			{
        				point = point + "0";
        			}
        			else
        			{
        				point = point + "1";
        			}
        		}
        		else
        		{
        			abbrevPointIndex++;
        			point = point + abbrevPoint.substring(abbrevPointIndex,abbrevPointIndex+1);
        			if (abbrevPoint.substring(abbrevPointIndex,abbrevPointIndex+1).equals("0"))
        			{
        				mutant = mutant + term + uniqueLiterals.substring(x,x+1) + " + ";
        			}
        			else
        			{
        				mutant = mutant + term + uniqueLiterals.substring(x,x+1).toUpperCase() + " + ";
        			}
        		}
        	}
        	pointsMap.put(point, point + " - UTP for term " + convertUppercaseToNegation(term));
        	        	        	
        	if (term.length() == uniqueLiterals.length() && alphaTerms.length != 1)
        	{
        		if (i < alphaTerms.length - 1)
        		{        	
        	       if (mutants.add(alphaExpression.replace(term + " + ", ""))) if (!forModel) numTofs++;
        		}
        		else
        		{
        			if (mutants.add(alphaExpression.replace(" + " + term, ""))) if (!forModel) numTofs++;
        		}
        	}
        	else if (alphaTerms.length != 1)
        	{
        		if (i < alphaTerms.length - 1)
        		{        	
        	       if (mutants.add(alphaExpression.replace(term + " + ", mutant))) if (!forModel) numTrfs++;
        		}
        		else
        		{
        			if (mutants.add(alphaExpression.replace(term, mutant.substring(0,mutant.length()-3)))) if (!forModel) numTrfs++;
        		}
        	}
        }
   }
   
   if (alphaTerms.length == 1)
   {
	   //gui.mutantArea.setText("(false)");
   	mutant = "(false)";
	   numFalse++;
   }
   else
   {
	   String text = "";
		   
	   for (String mutant : mutants)
	   {
		   text = text + convertAlphaExpression(mutant) + "\n";
	   }
	   if (forModel) return text.substring(0,text.length()-1);
	   
	  // /*Nan*/ gui.mutantArea.setText(toUpper(text));
	   mutant = toUpper(text);
   }
   
   String ret = "";
   Set<String> keys = pointsMap.keySet();
   
   for (String key : keys)
   {
      ret = ret + pointsMap.get(key) + "\n";
   }
   
   String s = "";
   
   if (!isFeasible)
   {
	   s = "not";
   }
if (outputStats)
{
   ret = ret + "MUTP is " + s + " feasible for all terms.\nNumber of tests: " + keys.size();
}   

///*Nan*/ gui.dataArea.setText(toUpperTermsAndLiterals(ret));
data = toUpperTermsAndLiterals(ret);
	   
   if (outputStats)
   {
      int numNon = numTrue + numFalse + numTofs + numTifs + numTrfs;
	   
	   String text = "Number of False mutants generated: " + numFalse + "\n" +
	   "Number of True mutants generated: " + numTrue + "\n" +
	   "Number of TOF mutants generated: " + numTofs + "\n" +
	   "Number of TIF mutants generated: " + numTifs + "\n" +
	   "Number of TRF mutants generated: " + numTrfs + "\n" +   
	   "Total Number of Non-Equivalent Mutants Generated: " +      numNon;

	     // gui.mutantTypesArea.setText(text);
	   mutantTypes = text;
   }   
   
   return null;
}

// converts a string such as aB + cD to a && !b || c && !d        
private static String convertAlphaExpression(String input)
{
	if (input.equals("(false)") || input.equals("(true)")) return input;
	
	String s = "";		
	String[] terms = input.split(" \\+ ");
	
	for (String term : terms)
	{
		for (int i=0; i < term.length(); i++)
		{
			if (Character.isUpperCase(term.charAt(i)))
			{
			   s = s + "!" + term.substring(i,i+1).toLowerCase() + " & ";
			}
			else
			{
				s = s + term.substring(i,i+1) + " & ";
		    }
		}		
		s = s.substring(0,s.length()-3) + " | ";
	}	
	s = s.substring(0,s.length()-3);
	
	return s;
}

// converts a string such as a && !b || c to aB + c
private static String reverseConvertAlphaExpression(String input, boolean updateInfVals)
{
	String s = "";		

    String[] terms = input.split(" \\| ");
   
    for (int i=0; i < terms.length; i++)
    {
       String[] literals = terms[i].split(" & ");
       for (int j=0; j < literals.length; j++)
       {
    	  String literal = "";
          if (literals[j].charAt(0) == '!')
          {
             literal = literals[j].substring(0,2);
          }
          else
          {
        	  literal = literals[j].substring(0,1);;
          }
          s = s + literal;                  
       }
       s = s + " + ";
    }
    if (updateInfVals)  updateCommaSepInfVals();
    
    return convertNegationToUpperCase(s.substring(0,s.length()-3));
}


// returns true if cutpnfp is feasible; false otherwise
private static boolean isCutpnfpFeasible(String term, String literal)
{
    Set<String> utps = utpsMap.get(term);
    Set<String> nfps = nfpsMap.get(term + "_" + literal);     
    boolean added = false;
      
     outer: for (String utp : utps)
      {
         for (String nfp : nfps)
         {
            int count = 0;

            for (int u=0; u < utp.length(); u++)
            {
               if (nfp.substring(u,u+1).equals(utp.substring(u,u+1)))
               {
                  count++;
               }
            }
            if (count == nfp.length()-1)
            { 
               added=true;
               break outer;
            }
         }
      }
      return added;
}

// get tests to achieve cutpnfp and mutants that can only be killed by a cutpnfp test set
private static void getCutpnfpTests(boolean forCutpnfp)
{
   Map<String,String> pointsMap = new LinkedHashMap<String,String>();
   Set<String> mutants = new LinkedHashSet<String>();
   String inf = "";
   boolean isFeasible = true;
   String[] alphaTerms = alphaExpression.split(" \\+ ");
   
   for (int i=0; i < alphaTerms.length; i++)
   {	   
	   String term = alphaTerms[i];	   
        Set<String> utps = utpsMap.get(term);
        
	for (int t=0; t < term.length(); t++)
    {
           Set<String> nfps = nfpsMap.get(term + "_" + term.substring(t,t+1)); 
           
          int outerLoopCounter = 0; 
          
         outer: for (String utp : utps)
          {   
        	 outerLoopCounter++;
        	         	 
        	 int innerLoopCounter = 0;
        	 
             for (String nfp : nfps)
             {
            	 innerLoopCounter++;
                int count = 0;

                for (int u=0; u < utp.length(); u++)
                {
                   if (nfp.substring(u,u+1).equals(utp.substring(u,u+1)))
                   {
                      count++;
                   }
                }
                if (count == nfp.length() -1 || 
                	(innerLoopCounter == nfps.size() && outerLoopCounter == utps.size()))
                {                      
                     if (innerLoopCounter == nfps.size() && outerLoopCounter == utps.size() &&
                    		 count != nfp.length() -1)
                    	 isFeasible = false;

                     pointsMap.put(utp, utp + " - UTP for term " + convertUppercaseToNegation(term));
                 	
                     if (!pointsMap.containsKey(nfp))
                	{
                		pointsMap.put(nfp, nfp + " - NFP for literal " +  term.substring(t,t+1).toLowerCase() + " in term " + convertUppercaseToNegation(term));
                	}
                	else
                	{
                		pointsMap.put(nfp, pointsMap.get(nfp) + ", literal " +  term.substring(t,t+1).toLowerCase() + " in term " + convertUppercaseToNegation(term));
                	}                     
                   
                   String mutant = "";
                                      
                   if (term.length() == uniqueLiterals.length() &&
                	  (alphaTerms.length > 1 || alphaTerms[0].length() > 1))
               	  {
                	if (alphaTerms.length == 1)
                	{
                		if (mutants.add("(false)")) numFalse++;
                	}
                	else if (i < alphaTerms.length - 1)
               		{        	
               	       if (mutants.add(alphaExpression.replace(term + " + ", ""))) numTofs++;
               		}
               		else
               		{
               			if (mutants.add(alphaExpression.replace(" + " + term, ""))) numTofs++;
               		}
               		if (mutants.add(alphaExpression + " + " + convertPointToTerm(nfp))) numTifs++;
               		
               	}
               	else if (alphaTerms.length > 1)
               	{
               		String extLiterals = "";
               		
               		for (int z=0; z < uniqueLiterals.length(); z++)
               		{
               			if (term.toLowerCase().indexOf(uniqueLiterals.substring(z,z+1)) == -1)
               			{
               				if (utp.substring(z,z+1).equals("1"))
               				{
               				extLiterals = extLiterals + uniqueLiterals.substring(z,z+1).toUpperCase();
               				}
               				else
               				{
               					extLiterals = extLiterals + uniqueLiterals.substring(z,z+1);
               				}
               			}
               		}
               		
               		for (int z=0; z < extLiterals.length(); z++)
               		{
        				mutant = mutant + term + extLiterals.substring(z,z+1) + " + ";
        			}
               		
               		if (i < alphaTerms.length - 1)
            		{        	
            	       if (mutants.add(alphaExpression.replace(term + " + ", mutant))) numTrfs++;
            		}
            		else
            		{
            			if (mutants.add(alphaExpression.replace(term, mutant.substring(0,mutant.length()-3)))) numTrfs++;
            		}
               		if (mutants.add(alphaExpression + " + " + convertPointToTerm(nfp))) numTifs++;
               	}                
                   break outer;                      
                }
             }             
          }
}
}
   String ret = "";
   Set<String> keys = pointsMap.keySet();
   
   for (String key : keys)
   {
      ret = ret + pointsMap.get(key) + "\n";
   }

String s = "";
   
   if (!isFeasible)
   {
	   s = "not";
   }
if (forCutpnfp)
{
   ret =  ret + "CUTPNFP is " + s + " feasible for all literals." + inf;
}
   ret = ret + "\nNumber of tests: " + keys.size();

   ///*Nan*/ gui.dataArea.setText(toUpperTermsAndLiterals(ret));
   data = toUpperTermsAndLiterals(ret);
   
   if (alphaTerms.length == 1 && alphaTerms[0].length() == 1)
   {
	   //gui.mutantArea.setText("(false)\n(true)"); 
   	mutant = "(false)\n(true)";
   	numFalse++; numTrue++;
   }
   else 
   {
	   String text = "";
		   
	   for (String mutant : mutants)
	   {
		   text = text + convertAlphaExpression(mutant) + "\n";
	   }
	 //  /*Nan*/ gui.mutantArea.setText(toUpper(text));
	   mutant = toUpper(text);
   }
	   
   int numNon = numTrue + numFalse + numTofs + numTifs + numTrfs;
	   
	   String text = "Number of False mutants generated: " + numFalse + "\n" +
	   "Number of True mutants generated: " + numTrue + "\n" +
	   "Number of TOF mutants generated: " + numTofs + "\n" +
	   "Number of TIF mutants generated: " + numTifs + "\n" +
	   "Number of TRF mutants generated: " + numTrfs + "\n" +   
	   "Total Number of Non-Equivalent Mutants Generated: " +   numNon;

	    //  gui.mutantTypesArea.setText(text);   
	      mutantTypes = text;
}

//takes a binary string such as 10101 and converts it to a term such as aBcDe
private static String convertPointToTerm(String point)
{
	String s = "";
	
	for (int i=0; i < point.length(); i++)
	{
		if (point.substring(i,i+1).equals("0"))
		{
			s = s + uniqueLiterals.substring(i,i+1).toUpperCase();
		}
		else
		{
			s = s + uniqueLiterals.substring(i,i+1);
		}
	}	
	return s;
}

// returns a set of all nfps for the given term and literal
private static Set<String> getNfps(String term, String literal)
{
   String fixedLiterals = null;

   if (Character.isUpperCase(literal.charAt(0)))
   {
      fixedLiterals = term.replace(literal,literal.toLowerCase());
   }
   else
   {
      fixedLiterals = term.replace(literal,literal.toUpperCase());
   }
   
    Set<String> retStrings = new LinkedHashSet<String>();       
    
     for (String binaryString : allBinaryStrings)
     {  
		for (int t=0; t< fixedLiterals.length(); t++)
        {
		int indexToReplace = uniqueLiterals.indexOf(fixedLiterals.substring(t,t+1).toLowerCase());
		
        if (Character.isUpperCase(fixedLiterals.charAt(t)))
        {
            if (indexToReplace != binaryString.length()-1)
            {
               binaryString = binaryString.substring(0,indexToReplace) + "0" + binaryString.substring(indexToReplace+1);
            }
            else
            {
               binaryString = binaryString.substring(0,indexToReplace) + "0";
            }
         }
         else
         {
             if (indexToReplace != binaryString.length()-1)
             {
                 binaryString = binaryString.substring(0,indexToReplace) + "1" + binaryString.substring(indexToReplace+1);
             }
             else
             {
                binaryString = binaryString.substring(0,indexToReplace) + "1";
             }
          }
       }

       if (!isATruePoint(binaryString,null) && isPointFeasible(binaryString))
	   {
            retStrings.add(binaryString);
       }
   }
   return retStrings;
}

// get tests to achieve mnfp and mutants that can only be killed by a mnfp test set
private static void getMnfpTests()
{
   Map<String,String> pointsMap = new LinkedHashMap<String,String>();
   Set<String> mutants = new LinkedHashSet<String>();   
   boolean isFeasible = true;
    String[] alphaTerms = alphaExpression.split(" \\+ ");

   for (String term : alphaTerms)
   {
        for (int t=0; t < term.length(); t++)
        {
           Set<String> nfps = nfpsMap.get(term + "_" + term.substring(t,t+1));
           
          Set<Integer> indicesOfLiterals = new LinkedHashSet<Integer>();
          
          for (int x=0; x < term.length(); x++)
          {
          	indicesOfLiterals.add(uniqueLiterals.indexOf(term.substring(x,x+1).toLowerCase()));
          }
          
          Set<String> abbrevNfps = new LinkedHashSet<String>();
          Map<String,Integer> abbrevNfpCountMap = new LinkedHashMap<String,Integer>();
          
          for (String nfp : nfps)
          {
          	String abbrevNfp = "";
          	
          	for (int x=0; x < nfp.length(); x++)
          	{
          		if (!indicesOfLiterals.contains(x))
          		{
          			abbrevNfp = abbrevNfp + nfp.substring(x,x+1);
          		}
          	}          	
          	abbrevNfps.add(abbrevNfp);
          	abbrevNfpCountMap.put(abbrevNfp, allNfpCountMap.get(nfp));
          }
         
          Set<String> abbrevPointsForTerm = new LinkedHashSet<String>(getMutpOrMnfpTestsBasedOnMutpGreedy(new ArrayList<String>(abbrevNfps),uniqueLiterals.length()-term.length(),abbrevNfpCountMap));
          
          if (!isMutpOrMnfpFeasible(abbrevPointsForTerm,uniqueLiterals.length()-term.length()))
          {
        	  isFeasible = false;
          }
          
          for (String abbrevPoint : abbrevPointsForTerm)
          {
          	String point = ""; int termIndex = -1;  int abbrevPointIndex = -1;
          	
          	for (int x=0; x < uniqueLiterals.length(); x++)
          	{
          		if (term.toLowerCase().indexOf(uniqueLiterals.substring(x,x+1)) != -1)
          		{
          			termIndex++;
          			if (term.substring(t,t+1).toLowerCase().equals(uniqueLiterals.substring(x,x+1)) &&
          				Character.isUpperCase(alphabetize(term).charAt(termIndex)))
          			{
          				point = point + "1";  continue;
          			}
          			else if (term.substring(t,t+1).toLowerCase().equals(uniqueLiterals.substring(x,x+1)) &&
              				Character.isLowerCase(alphabetize(term).charAt(termIndex)))
          			{
          				point = point + "0";  continue;
          			}
          			if (Character.isUpperCase(alphabetize(term).charAt(termIndex)))
          			{
          				point = point + "0";
          			}
          			else
          			{
          				point = point + "1";
          			}
          		}
          		else
          		{
          			abbrevPointIndex++;
          			point = point + abbrevPoint.substring(abbrevPointIndex,abbrevPointIndex+1);
          		}
          	}
         	
            if (!pointsMap.containsKey(point))
       	{
       		pointsMap.put(point, point + " - NFP for literal " +  term.substring(t,t+1).toLowerCase() + " in term " + convertUppercaseToNegation(term));
       	}
       	else
       	{
       		pointsMap.put(point, pointsMap.get(point) + ", literal " +  term.substring(t,t+1).toLowerCase() + " in term " + convertUppercaseToNegation(term));
       	}      	            
        if (alphaTerms.length > 1 || alphaTerms[0].length() > 1)
        {
         	  if (mutants.add(alphaExpression + " + " + convertPointToTerm(point))) numTifs++;
        }
   }
   }
   }

   String ret = "";
   Set<String> keys = pointsMap.keySet();
   
   for (String key : keys)
   {
      ret = ret + pointsMap.get(key) + "\n";
   }

   String s = "";
   
   if (!isFeasible)
   {
	   s = "not";
   }
   ret =  ret + "MNFP is " + s + " feasible for all literals.\nNumber of tests: " + keys.size();
   
  // /*Nan*/ gui.dataArea.setText(toUpperTermsAndLiterals(ret));
   data = toUpperTermsAndLiterals(ret);
   
   if (alphaTerms.length == 1 && alphaTerms[0].length() == 1)
   {
	  // gui.mutantArea.setText("(true)");
   	mutant = "(true)";
   	numTrue++; 
   }
   else 
   {
	   String text = "";
		   
	   for (String mutant : mutants)
	   {
		   text = text + convertAlphaExpression(mutant) + "\n";
	   }
     //  /*Nan*/ gui.mutantArea.setText(toUpper(text));
	   mutant = toUpper(text);
   }

   int numNon = numTrue + numFalse + numTofs + numTifs + numTrfs;
	   
	   String text = "Number of False mutants generated: " + numFalse + "\n" +
	   "Number of True mutants generated: " + numTrue + "\n" +
	   "Number of TOF mutants generated: " + numTofs + "\n" +
	   "Number of TIF mutants generated: " + numTifs + "\n" +
	   "Number of TRF mutants generated: " + numTrfs + "\n" +   
	   "Total Number of Non-Equivalent Mutants Generated: " +    numNon;

	   //   gui.mutantTypesArea.setText(text); 
	   mutantTypes = text;
}

// Chen and Lau's mutp greedy algorithm to attempt to minimize a test set that achieves mutp or mnfp
// In other words, reduce test set size subject to the constraint that each external literal achieves 0 and 1
private static Set<String> getMutpOrMnfpTestsBasedOnMutpGreedy(List<String> binaryStrings, int length,
		                                                       Map<String,Integer> abbrevNfpCountMap)
{   
   Set<Set<String>> pointSets = new LinkedHashSet<Set<String>>();
   Set<String> points = new LinkedHashSet<String>();
   
   if (binaryStrings.size() == 1 || binaryStrings.size() ==2)
   {
	   for (String binaryString : binaryStrings)
	   {
		   points.add(binaryString);
	   }
	   return points;
   }
   for (int x=0; x < binaryStrings.size(); x++)
   {
	   boolean[] selected = new boolean[binaryStrings.size()];
	   int[] count = new int[binaryStrings.size()];
	   int[] state = new int[length];
	   for (int i=0; i < state.length; i++)   state[i] = NONE;
	   int max = 0;
	   
       selected[x] = true;
       String selectedBinaryString = binaryStrings.get(x);
       points.add(selectedBinaryString);
       for (int i=0; i < state.length; i++)  state[i] = stateUpdate(i,state[i],selectedBinaryString);
       
   do
   {
      for (int i=0; i < selected.length; i++)
      {
         if (!selected[i])
         {
            String binaryString = binaryStrings.get(i);
            count[i] = 0;
            for (int j=0; j < state.length; j++)
            {
               if (state[j] == NONE ||
                 (state[j] == ZERO && binaryString.substring(j,j+1).equals("1")) ||
                 (state[j] == ONE && binaryString.substring(j,j+1).equals("0")))       count[i] = count[i] + 1;
            }
         }
      }
      max = 0;  int maxIndex = -1; int maxOverlapToBreakTies = -1;
      for (int i=0; i < selected.length; i++)
      {
    	  if (abbrevNfpCountMap != null)
    	  {
             if (!selected[i] && count[i] == max)  
             { 
            	 if (abbrevNfpCountMap.get(binaryStrings.get(i)) > maxOverlapToBreakTies)
            	 {
            	 max = count[i];  
            	 maxIndex = i;
            	 maxOverlapToBreakTies = abbrevNfpCountMap.get(binaryStrings.get(i));
            	 }
             }
             else if (!selected[i] && count[i] > max)  { max = count[i];  maxIndex = i; }
    	  }
    	  else
    	  {
    		  if (!selected[i] && count[i]  > max)  { max = count[i];  maxIndex = i; }
    	  }
      }
      if (max > 0)
      {
         selected[maxIndex] = true;
         selectedBinaryString = binaryStrings.get(maxIndex);
         points.add(selectedBinaryString);
         for (int i=0; i < state.length; i++)  state[i] = stateUpdate(i,state[i],selectedBinaryString);
      }
   }
   while (max != 0 && !allSelected(selected) && !isMUTPAchieved(state));
   pointSets.add(points);
   points = new LinkedHashSet<String>();
   }
      
   boolean first = true;   
   Set<String> min = null;
   
   for (Set<String> s : pointSets)
   {
	   if (first) min = s;
	   
	   else if (s.size() < min.size()) min = s;
	   
	   first = false;
   }   
   return min;
}

// returns true if every external literal can be 0 or 1 in an utp or can be 0 or 1 in an nfp
private static boolean isMutpOrMnfpFeasible(Set<String> binaryStrings, int length)
{
   boolean[] equalsZero = new boolean[length];
   boolean[] equalsOne = new boolean[length];
   
   for (String s : binaryStrings)
   {
	   for (int i=0; i < s.length(); i++)
	   {
		   if (s.substring(i,i+1).equals("0")) equalsZero[i] = true;
		   else equalsOne[i] = true;
	   }
   }   
   for (boolean b : equalsZero)
   {
	   if (!b) return false;
   }
   for (boolean b : equalsOne)
   {
	   if (!b) return false;
   }
   return true;
}

// updates the state to indicate whether the external literal at index i has achieved 0, 1, or both
 private static int stateUpdate(int i, int oldState, String selectedBinaryString)
 {
    int newState = oldState;
    if (oldState == NONE && selectedBinaryString.substring(i,i+1).equals("0"))         newState = ZERO;
    else if (oldState == NONE && selectedBinaryString.substring(i,i+1).equals("1"))  newState = ONE;
    else if (oldState == ZERO && selectedBinaryString.substring(i,i+1).equals("1"))  newState = BOTH;
    else if (oldState == ONE && selectedBinaryString.substring(i,i+1).equals("0"))    newState =  BOTH;
    return newState;
 }

 // returns true if every element in the selected array has a value of true
 private static boolean allSelected(boolean[] selected)
 {
    for (int i=0; i < selected.length; i++)   if (!selected[i])  return false;
    return true;
 }

 // returns true if every element in the state array has a value Of BOTH
 private static boolean isMUTPAchieved(int[] state)
 {
    for (int i=0; i < state.length; i++)  if (state[i] != BOTH)  return false;
    return true;
 }

 // returns true if every term consists of one literl; otherwise return false
 private static boolean isEveryTermAOneLiteralTerm()
 {
    String[] alphaTerms = alphaExpression.split(" \\+ ");
    for (int i=0; i < alphaTerms.length; i++)
    {
       if (alphaTerms[i].length() != 1)  return false;
    }
    return true;
 }   

 private static void createLifLifMutants(boolean forMinimization)
 {
	 createLifLifMutants(forMinimization,0);
 }
 
  // generates mutants where a LIF occurs in two terms and generates the overlapping true points that kill each mutant
  private static void createLifLifMutants(boolean forMinimization, int termIndex)
  {
     Map<String,Set<String>> otpMap = new LinkedHashMap<String,Set<String>>();
     Map<String,Integer> countMap = new LinkedHashMap<String,Integer>();
     Map<String,String> lifLifTerms = new LinkedHashMap<String,String>();   
     
     for (int i=termIndex; i < alphaTerms.length - 1; i++)
     {
        String term1 = alphaTerms[i];

        if (isTermMutpFeasible[i]) continue;

        for (int j=i+1; j < alphaTerms.length; j++)
        {
            String term2 = alphaTerms[j];
 
            if (isTermMutpFeasible[j]) continue;

            String infLiteralsForTerm1 = literalsThatMakeATermMutpInfeasible.get(i);
            String infLiteralsForTerm2 = literalsThatMakeATermMutpInfeasible.get(j);

            for (int k=0; k < infLiteralsForTerm1.length(); k++)
            {
               String literal1 = infLiteralsForTerm1.substring(k,k+1);

               for (int l=0; l < infLiteralsForTerm2.length(); l++)
               {
                  String literal2 = infLiteralsForTerm2.substring(l,l+1);

                  String mutant = alphaExpression.replace(term1,term1+literal1).replace(term2,term2+literal2);
                  
                  for (String bs : allOtps)
                  {
                     if (!isATruePoint(bs,mutant))
                     {
                          if (forMinimization)
            			  {
            				  lifLifPoints.put(bs + "," + mutant,null);
            			  }
            			  else
            			  {
                                      if (!otpMap.containsKey("term " + convertUppercaseToNegation(term1) + " and term " + convertUppercaseToNegation(term2)))
                                      {
                                         Set<String> otps = new LinkedHashSet<String>();
                                         otps.add(bs);
                                         otpMap.put("term " + convertUppercaseToNegation(term1) + " and term " + convertUppercaseToNegation(term2),otps);
                                      }
                                      else
                                      {
                                         Set<String> otps = otpMap.get("term " + convertUppercaseToNegation(term1) + " and term " + convertUppercaseToNegation(term2));
                                         otps.add(bs);
                                         otpMap.put("term " + convertUppercaseToNegation(term1) + " and term " + convertUppercaseToNegation(term2),otps);                                             
                                      }
                                      if (!countMap.containsKey(bs)) countMap.put(bs,1);
                                      else 
                                      {
                                         int intVal = countMap.get(bs); intVal++; countMap.put(bs,intVal);
                                      }
                                      if (!lifLifTerms.containsKey(bs))
                                      {
                                         lifLifTerms.put(bs,term1+literal1 + " " + term2+literal2);
                                      }
                                      else
                                      {
                                         String val = lifLifTerms.get(bs) + "," + term1+literal1 + " " + term2+literal2;
                                         lifLifTerms.put(bs,val);
                                      }                                      
            			  } // else
            		  } // true point test
            	  } // for binary strings
               } // inf literals 2
            } // inf literals 1
         }
         if (forMinimization) break;
      }
      if (!forMinimization && otpMap.size() != 0)
      {
                  List<Object> optimals = doGreedyOverlap(otpMap, countMap, new LinkedHashMap<String,String>(), new LinkedHashSet<String>(), "OTP", lifLifTerms);
                 
                  numTrfs = numTrfs + ((Set<String>)optimals.get(0)).size() + 1;
                  lifLifMutants = (Set<String>)optimals.get(0);
                  lifLifPoints = (Map<String,String>)optimals.get(1);
                  String optimalKey =   (String)optimals.get(2);

                  String mutant = alphaExpression;

                  String[] vals = lifLifTerms.get(optimalKey).split(",");

                  for (String val : vals)
                  {
                      String[] termsWithLifs = val.split(" ");

                       mutant = mutant.replace(termsWithLifs[0].substring(0,termsWithLifs[0].length()-1),termsWithLifs[0]).
                                       replace(termsWithLifs[1].substring(0,termsWithLifs[1].length()-1),termsWithLifs[1]);
                  }

                  lifLifMutants.add(mutant);

                  // reorder the points so that they match the mutant order                     
                     String optVal = lifLifPoints.get(optimalKey);                     
                     lifLifPoints.remove(optimalKey);  lifLifPoints.put(optimalKey, optVal);
      }
   }

  // converts infeasible literal values to infeasible terms
  // for example "a=1,b=0;c=0;d=1" is converted to "aB,cD"
  private static void updateCommaSepInfVals()
  {	  
  if (!semicolonSepInf.equals(""))
  {
     String[] commaSepInf = semicolonSepInf.split(";");
     
     for (int i=0; i < commaSepInf.length; i++)
     {
    	 String[] individuals = commaSepInf[i].split(",");
    	 
    	 for (int j=0; j < individuals.length; j++)
    	 {
    		 if (individuals[j].indexOf("=0") != -1)
    		 {
    			 commaSepInfVals = commaSepInfVals + individuals[j].substring(0,1).toUpperCase();
    		 }
    		 else
    		 {
    			 commaSepInfVals = commaSepInfVals + individuals[j].substring(0,1);
    		 }
    	 }
    	 commaSepInfVals = commaSepInfVals + ",";
     }       
     commaSepInfVals = commaSepInfVals.substring(0,commaSepInfVals.length()-1);
    }
  }

// This is the greedy overlap algorithm to reduce the nfp test set size or otp test set size.  For nfps, it will select a nfp to 
// start with and then of the remaining nfps, it will add the nfp that covers the most number of uncovered literals, continuing in this cycle
// until all literals are covered.  Then it repeats the process by seleting a different nfp as the starting nfp until all nfps
// have been tried once as the starting nfp. For otps, it will select an otp to start with
// and then of the remaining undetected lif-lif faults, it will add the otp that detects the most of them, continuing in this cycle until
// all lif-lif faults are detected.  Then it repeats the process by seleting a different ofp as the starting ofp until all otps
// have been tried once as the starting otp. This is based on the Nearest Neighbor Algorithm.  

private static List<Object> doGreedyOverlap(Map<String,Set<String>> nfpOrOtpMap, Map<String,Integer> countMap, 
                                            Map<String,String> pointsMap, Set<String> mutants, String type, Map<String,String> lifLifTerms)
{
                  Map<String,Set<String>> nfpOrOtpMapClone = new LinkedHashMap<String,Set<String>>(nfpOrOtpMap);
                  Map<String,Integer> countMapClone = new LinkedHashMap<String,Integer>(countMap);
                  Map<String,String> optimalPointsMap = new LinkedHashMap<String,String>(pointsMap);
                  Map<String,String> pointsMapClone = new LinkedHashMap<String,String>(pointsMap);
                  Set<String> cloneCountKeys = countMapClone.keySet();
                  Set<String> mutantsClone = new LinkedHashSet<String>(mutants);
                  Set<String> optimalMutants = new LinkedHashSet<String>(mutants);
                  String optimalKey = null;
                  int min = Integer.MAX_VALUE;

                  for (String cloneCountKey : cloneCountKeys)
                  {
                     pointsMapClone = new LinkedHashMap<String,String>(pointsMap);
                     nfpOrOtpMap = new LinkedHashMap<String,Set<String>>(nfpOrOtpMapClone);
                     countMap = new LinkedHashMap<String,Integer>(countMapClone);
                     mutantsClone = new LinkedHashSet<String>(mutants);
                     countMap.remove(cloneCountKey);

                     Set<String> cloneNfpOrOtpKeys = nfpOrOtpMapClone.keySet();

                     for (String cloneNfpOrOtpKey : cloneNfpOrOtpKeys)
                     {
                        if (nfpOrOtpMap.get(cloneNfpOrOtpKey).contains(cloneCountKey))
                        {
                           nfpOrOtpMap.remove(cloneNfpOrOtpKey);

                           if (!pointsMapClone.containsKey(cloneCountKey))
                           {
                              pointsMapClone.put(cloneCountKey,cloneCountKey + " - " + type + " for " + cloneNfpOrOtpKey);
                           }
                           else
                           {
                              pointsMapClone.put(cloneCountKey, pointsMapClone.get(cloneCountKey) + ", " + cloneNfpOrOtpKey);
                           }
                        }
                     }
                  
                     while (nfpOrOtpMap.keySet().size() != 0)
                     {
                   	  int max=0; String maxKey = null;
                   	  
                   	  Set<String> countKeySet = countMap.keySet();
                   	  
                   	  for (String key : countKeySet)
                   	  {
                   		  int count = countMap.get(key);
                   		  
                   		  if (count > max)
                   		  {
                   			  max = count; maxKey = key;
                   		  }
                   	  }
                   	  
                   	  if (maxKey == null) break;
                   	  
                   	  countMap.remove(maxKey);
                   	  
                   	  String point = maxKey + " - " + type + " for ";
                   	  
                   	  Set<String> nfpOrOtpKeySet = nfpOrOtpMap.keySet();
                   	  
                   	  Set<String> keysToRemove = new HashSet<String>();
                   	              	  
                   	  for (String key : nfpOrOtpKeySet)
                   	  {
                   		  if (nfpOrOtpMap.get(key).contains(maxKey))
                   		  {
                   			  keysToRemove.add(key);
                   			  point = point + key + ", ";
                   		  }
                   	  }
                   	  
                   	  if (keysToRemove.size()==0)
                   	  {
                   		  continue;
                   	  }
                   	  
                   	  if (point.endsWith(", ")) point = point.substring(0,point.length()-2);
                   	  pointsMapClone.put(maxKey, point);

                          if (type.equals("NFP"))
                          {
                   	     mutantsClone.add(alphaExpression + " + " + convertPointToTerm(maxKey)); 
                          }
                          else
                          {                         
                             String mutant = alphaExpression;

                             String[] vals = lifLifTerms.get(maxKey).split(",");

                             for (String val : vals)
                             {
                                String[] termsWithLifs = val.split(" ");

                                mutant = mutant.replace(termsWithLifs[0].substring(0,termsWithLifs[0].length()-1),termsWithLifs[0]).
                                       replace(termsWithLifs[1].substring(0,termsWithLifs[1].length()-1),termsWithLifs[1]);
                             }
                             mutantsClone.add(mutant);
                          } 
                   	  for (String key : keysToRemove)
                   	  {
                   		  Set<String> nfpOrOtps = nfpOrOtpMap.get(key);
                   		  
                   		  for (String nfpOrOtp : nfpOrOtps)
                   		  {
                   			  Integer intVal = countMap.get(nfpOrOtp);
                   			  if (intVal != null)
                   			  {
                   				  intVal--;
                   				  countMap.put(nfpOrOtp, intVal);
                   			  }
                   		  }
                   		  nfpOrOtpMap.remove(key);
                   	  }
                     }  // while

                     if (pointsMapClone.size() < min)
                     {
                        min = pointsMapClone.size();                        
                        optimalKey = cloneCountKey;                        
                        optimalPointsMap = pointsMapClone;                        
                        optimalMutants = mutantsClone;
                     }
                  }// for (String cloneCountKey : cloneCountKeys)

                  List<Object> retList = new ArrayList<Object>();
                  retList.add(optimalMutants);
                  retList.add(optimalPointsMap);
                  retList.add(optimalKey);               
                  return retList;
       }
}