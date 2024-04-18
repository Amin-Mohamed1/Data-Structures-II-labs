import java.util.ArrayList;

public class EnglishDictionary {
    private PerfectHashing<String> dic;

    public EnglishDictionary(String s) {
        if ("linear".equals(s))
            dic = new PerfectHashingNMethod<String>();
        else
            dic = new PerfectHashingNSquareMethod<String>();
    }

    public boolean insert(String s) {
        return dic.insert(s);
    }

    public boolean search(String s) {
        return dic.search(s);

    }

    public boolean delete(String s) {
        return dic.delete(s);
    }

    public int[] batchInsertFromFile(ArrayList<String> list) {
        int[] result = new int[2];
        for (String s : list) {
            if (dic.insert(s))
                result[0]++;
            else
                result[1]++;
        }
        return result;
    }

    public int[] batchDeleteFromFile(ArrayList<String> list) {
        int[] result = new int[2];
        for (String s : list) {
            if (dic.delete(s))
                result[0]++;
            else
                result[1]++;
        }
        return result;
    }

}
