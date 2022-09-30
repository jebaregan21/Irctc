package utility;

import model.Carriage;
import model.Train;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CloneUtility {
    private CloneUtility(){}
    public static Map<String, List<Carriage>> cloneCarriageMap(Map<String,List<Carriage>> carriageMap){
        Map<String,List<Carriage>> resultMap = new HashMap<>();
        for(String type : carriageMap.keySet()){
            List<Carriage> original = carriageMap.get(type);
            List<Carriage> copy = cloneCarriageList(original);
            resultMap.put(type,copy);
        }
        return resultMap;
    }

    private static Carriage cloneCarriage(Carriage carriage){
        return new Carriage(carriage);
    }

    private static List<Carriage> cloneCarriageList(List<Carriage> list){
        List<Carriage> result = new ArrayList<>();
        for(Carriage carriage : list){
            result.add(cloneCarriage(carriage));
        }
        return result;
    }
}
