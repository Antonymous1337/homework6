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

        for (int docNum = 0; docNum < texts.size(); docNum++) { // for every single document // i would be for document
            String[] keyWords = texts.get(docNum).toLowerCase().split(" ");
            for (int keyNum = 0; keyNum < keyWords.length; keyNum++) { // for every single word in a document // j would be for index of word in document
                if (indexes.containsKey(keyWords[keyNum])) {
                    indexes.get(keyWords[keyNum]).get(docNum).add(keyNum);
                } else {
                    List<List<Integer>> temp = new ArrayList<>();
                    List<Integer> temp2 = new ArrayList<>();
                    temp2.add(keyNum);
                    for (int k = 0; k < texts.size(); k++) {
                        if (k == docNum) temp.add(k, temp2);
                        else temp.add(new ArrayList<>());
                    }
                    indexes.put(keyWords[keyNum], temp);
                }
            }
        }
    }

    // search(key) return all the document ids where the given key phrase appears.
    // key phrase can have one or two words in English alphabetic characters.
    // return an empty list if search() finds no match in all documents.
    public List<Integer> search(String keyPhrase) {
        // homework
        String[] keyWords = keyPhrase.toLowerCase().split(" ");
        List<Integer> possibleIDs = new ArrayList<>();
        List<Integer> possibleIDsIndexOfFirstSearchedWord = new ArrayList<>();
        for (int keyNum = 0; keyNum < keyWords.length; keyNum++) {
            if (!indexes.containsKey(keyWords[keyNum])) return new ArrayList<>();
            List<List<Integer>> thisResult = indexes.get(keyWords[keyNum]);
            if (possibleIDs.equals(new ArrayList<>())) {
                for (int j = 0; j < thisResult.size(); j++) {
                    if (!thisResult.get(j).equals(new ArrayList<>())) {
                        possibleIDs.add(j);
                        for (int k = 0; k < thisResult.get(j).size(); k++) {
                            possibleIDsIndexOfFirstSearchedWord.add(thisResult.get(j).get(k));
                        }
                    }
                }
            } else {
                for (int j = 0; j < possibleIDs.size(); j++) { // checks every possible ID
                    boolean foundNextWordInProperIndex = false;
                    for (int k = 0; k < thisResult.get(possibleIDs.get(j)).size(); k++) { // checks every word index in array
                        if (thisResult.get(possibleIDs.get(j)).get(k) - keyNum == possibleIDsIndexOfFirstSearchedWord.get(j)) {
                            foundNextWordInProperIndex = true;
                            break;
                        }
                    }
                    if (!foundNextWordInProperIndex) {
                        possibleIDs.remove(j);
                        possibleIDsIndexOfFirstSearchedWord.remove(j);
                        j--;
                    }
                }
            }
        }

        return possibleIDs;
    }
}

