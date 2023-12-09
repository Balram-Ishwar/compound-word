import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

class BigWords {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Set<String> words = readWords("Input_02.txt");
        String longestWord = CompoundedWord(words);
        String secondLongestWord = SecondCompoundedWord(words);
        long endTime = System.currentTimeMillis();
        long processingTime = endTime - startTime;
        System.out.println("Longest Compounded Word: " + longestWord);
        System.out.println("Second Longest Compounded Word: " + secondLongestWord);
        System.out.println("Time taken to process: " + processingTime + " milliseconds");
    }

    static Set<String> readWords(String filename) {
        Set<String> words = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    private static String CompoundedWord(Set<String> words) {
        String longestWord = "";
        for (String word : words) {
            if (CompoundWord(words, word)) {
                if (word.length() > longestWord.length()) {
                    longestWord = word;
                }
            }
        }
        return longestWord;
    }

    private static String SecondCompoundedWord(Set<String> words) {
        String longestWord = CompoundedWord(words);
        String secondLongestWord = "";
        for (String word : words) {
            if (!word.equals(longestWord) && CompoundWord(words, word)) {
                if (word.length() > secondLongestWord.length()) {
                    secondLongestWord = word;
                }
            }
        }
        return secondLongestWord;
    }

    static boolean CompoundWord(Set<String> words, String word) {
        if (word.isEmpty()) {
            return false;
        }

        for (int i = 1; i < word.length(); i++) {
            String prefix = word.substring(0, i);
            String suffix = word.substring(i);

            if (words.contains(prefix) && (words.contains(suffix) || CompoundWord(words, suffix))) {
                return true;
            }
        }

        return false;
    }
}
