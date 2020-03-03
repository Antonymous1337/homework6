import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyMiniSearchEngine {
    // default solution. OK to change.
    // do not change the signature of index()
    private Map<String, List<List<Integer>>> indexes;


    // disable default constructor
    private MyMiniSearchEngine() {
    }

    public MyMiniSearchEngine(List<String> documents) {
        indexes = new HashMap<>();
        index(documents);
    }

    // each item in the List is considered a document.
    // assume documents only contain alphabetical words separated by white spaces.
    private void index(List<String> texts) {
        //homework

        for (int i = 0; i < texts.size(); i++) { // for every single document // i would be for document
            String[] keyWords = texts.get(i).split(" ");
            for (int j = 0; j < keyWords.length; j++) { // for every single word in a document // j would be for index of word in document
                if (indexes.containsKey(keyWords[j])) {
                    indexes.get(keyWords[j]).get(i).add(j);
                } else {
                    List<List<Integer>> temp = new ArrayList<>();
                    List<Integer> temp2 = new ArrayList<>();
                    temp2.add(j);
                    for (int k = 0; k < texts.size(); k++) {
                        if (k == i) temp.add(k, temp2);
                        else temp.add(new ArrayList<>());
                    }
                    indexes.put(keyWords[j], temp);
                }
            }
        }
    }

    // search(key) return all the document ids where the given key phrase appears.
    // key phrase can have one or two words in English alphabetic characters.
    // return an empty list if search() finds no match in all documents.
    public List<Integer> search(String keyPhrase) {
        // homework
        String[] keyWords = keyPhrase.split(" ");
        List<Integer> possibleIDs = new ArrayList<>();
        List<Integer> cur = new ArrayList<>(); // for keeping track of indexes
        for (int i = 0; i < keyWords.length; i++) {
            if (!indexes.containsKey(keyWords[i])) return new ArrayList<>();
            List<List<Integer>> thisResult = indexes.get(keyWords[i]);
            if (possibleIDs.equals(new ArrayList<>())) {
                for (int j = 0; j < thisResult.size(); j++) {
                    if (!thisResult.get(j).equals(new ArrayList<>())) {
                        possibleIDs.add(j);
                        for (int k = 0; k < thisResult.get(j).size(); k++) {
                            cur.add(thisResult.get(j).get(k));
                        }
                    }
                }
            } else {
                /*
                for (int j = 0; j < thisResult.size(); j++) {
                    if (thisResult.get(j).equals(new ArrayList<>())) {
                        possibleIDs.remove(j);
                        cur.remove(j);
                    }
                    boolean found = false;
                    for (int k = 0; j < thisResult.get(j).size(); k++) {
                        if (thisResult.get(j).get(k) == cur.get(k) + i) {
                            found = true;
                        }
                    }
                    if (!found) {
                        possibleIDs.remove(j);
                        cur.remove(j);
                    }*/
                }
            }
        }

        return possibleIDs;
    }
}
