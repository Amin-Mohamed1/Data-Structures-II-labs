import java.util.ArrayList;

public class EnglishDictionary {
    private PerfectHashing<String> dic;

    public EnglishDictionary(String s) {
        if ("linear".equals(s))
            dic = new PerfectHashingNMethod<String>(1);
        else
            dic = new PerfectHashingNSquareMethod<String>();
    }

    public int[] insert(String s) {
        int[] result = new int[2];
            result[0]=dic.insert(s);
            result[1]=dic.getNumberOfRehashing();
        return result;
    }

    public boolean search(String s) {
        return dic.search(s);
    }

    public int[] delete(String s) {
        int[] result = new int[2];
        if(dic.delete(s))
            result[0]=1;
        else
            result[0]=0;
        result[1]=dic.getNumberOfRehashing();
        return result;
    }

    public int[] batchInsertFromFile(ArrayList<String> list) {
        int[] result = new int[3];
        for (String s : list) {
            if (dic.insert(s)==0)
                result[0]++;
            else
                result[1]++;
        }
        result[2]=dic.getNumberOfRehashing();
        return result;
    }

    public int[] batchDeleteFromFile(ArrayList<String> list) {
        int[] result = new int[3];
        for (String s : list) {
            if (dic.delete(s))
                result[0]++;
            else
                result[1]++;
        }
        result[2]=dic.getNumberOfRehashing();
        return result;
    }

}
