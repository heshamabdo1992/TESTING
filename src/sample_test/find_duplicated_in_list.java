package sample_test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class find_duplicated_in_list {
	public static void main(String[] args)
	{
	java.util.List<String> list = Arrays.asList("A", "B","B", "B", "C", "D","B", "D", "Z", "E", "E");
    Map<String,Integer> valueCounter = new HashMap<>();
   
    //Iterate all the elements from list and prepare HashMap, Key is List Elements and value is duplicate element occurences 
    for(String str : list){
        Integer val = valueCounter.get(str);
     System.out.println(val);
        if(valueCounter.get(str)==null){
            valueCounter.put(str,1); //if map does not contains key, put element with value 1
        }else{
            val = val+1;//val++, ++val
            valueCounter.put(str,val); //increment counter if map already exists element
        }
    }
    
   
    Set<Map.Entry<String, Integer>> entrySet = valueCounter.entrySet();
    for(Map.Entry<String,Integer> entry : entrySet){
        if(entry.getValue()>1){
            System.out.println(entry.getKey()+"=>"+entry.getValue());
        }
    }
	}
}
