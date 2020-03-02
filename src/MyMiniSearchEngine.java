import java.util.ArrayList;
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
        List<List<List<Integer>>> allOccurances = new ArrayList<>();
        for (String keyWord : keyWords) {
            if (!indexes.containsKey(keyWord)) return new ArrayList<>();
            allOccurances.add(indexes.get(keyWord));
        }
        for (int i = 0; i < allOccurances.get(0).get(0).size(); i++) {
            if (!allOccurances.get(0).get(0).get(i).equals(new ArrayList<>())) {
                possibleIDs.add(i);
            }
        }
        for (int i = 1; i < keyWords.length; i++) {
            for (int j = 0; j < allOccurances.get(i).size(); j++) {
                int val = possibleIDs.get(j);
                for (int k = 0; k < allOccurances.get(i).get(j).size(); k++) {
                    if (val+i != allOccurances.get(i).get(j).get(k)) {
                        possibleIDs.remove(j);
                    }
                }
            }
        }
        return possibleIDs; // place holder
    }
}
