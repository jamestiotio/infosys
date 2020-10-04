package piwords;

import java.util.HashMap;
import java.util.Map;
import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class Vertex {
  Vertex() {
    children = new Hashtable<>();            
    leaf = false;
    parent = -1;
    suffixLink = -1;
    wordID = -1;
    endWordLink= -1;
  }

  Map<Character, Integer> children;
  boolean leaf;
  int parent;
  char parentChar;
  int suffixLink;
  int endWordLink;
  int wordID;
}

class Aho {
  List<Vertex> trie = new ArrayList<>();
  List<Integer> wordsLength = new ArrayList<>();
  int size = 0;
  int root = 0;

  Aho() {
    trie.add(new Vertex());           
    size++;
  }

  void addString(String s, int newWordID) {
    int curVertex = root;
    for (int i = 0; i < s.length(); ++i) {
      char c = s.charAt(i);

      if (!trie.get(curVertex).children.containsKey(c)) {
        trie.add(new Vertex());
        
        trie.get(size).suffixLink = -1;
        trie.get(size).parent = curVertex;
        trie.get(size).parentChar = c;
        trie.get(curVertex).children.put(c, size);
        size++;
      }

      curVertex = (int)trie.get(curVertex).children.get(c);
    }

    trie.get(curVertex).leaf = true;
    trie.get(curVertex).wordID = newWordID;
    wordsLength.add(s.length());
  }

  void prepareAho() {
    Queue<Integer> vertexQueue = new LinkedList<>();
    vertexQueue.add(root);

    while (!(vertexQueue.isEmpty())) {
      int curVertex = vertexQueue.remove();
      calcSuffLink(curVertex);
      Set<Character> keys = trie.get(curVertex).children.keySet();

      for (char key: keys) {
        vertexQueue.add(trie.get(curVertex).children.get(key));
      }
    }
  }

  private void calcSuffLink(int vertex)
  {
    if (vertex == root) { 
      trie.get(vertex).suffixLink = root;
      trie.get(vertex).endWordLink = root;
      return;
    }

    if (trie.get(vertex).parent == root) { 
      trie.get(vertex).suffixLink = root;
      if (trie.get(vertex).leaf) trie.get(vertex).endWordLink = vertex;
      else trie.get(vertex).endWordLink = trie.get(trie.get(vertex).suffixLink).endWordLink;
      return;
    }

    int curBetterVertex = trie.get(trie.get(vertex).parent).suffixLink;
    char chVertex = trie.get(vertex).parentChar; 

    while (true) {
      if (trie.get(curBetterVertex).children.containsKey(chVertex)) {
        trie.get(vertex).suffixLink = (int)trie.get(curBetterVertex).children.get(chVertex);
          break;
      }

      if (curBetterVertex == root) { 
        trie.get(vertex).suffixLink = root;
          break;
      }

      curBetterVertex = trie.get(curBetterVertex).suffixLink;
    }

    if (trie.get(vertex).leaf) trie.get(vertex).endWordLink = vertex; 
    else trie.get(vertex).endWordLink = trie.get(trie.get(vertex).suffixLink).endWordLink;
  }

  Map<String, Integer> processString(String text, String[] wordList) {
    int currentState = root;
    HashMap<String, Integer> result = new HashMap<>();

    for (int j = 0; j < text.length(); j++) {
      while (true) {
        if (trie.get(currentState).children.containsKey(text.charAt(j))) {
          currentState = (int)trie.get(currentState).children.get(text.charAt(j));
          break;
        }

        if (currentState == root) break;
        currentState = trie.get(currentState).suffixLink;
      }

      int checkState = currentState;

      while (true) {
        checkState = trie.get(checkState).endWordLink;

        if (checkState == root) break;

        if (!(result.containsKey(wordList[trie.get(checkState).wordID]))) {
          int indexOfMatch = j + 1 - wordsLength.get(trie.get(checkState).wordID);
          result.put(wordList[trie.get(checkState).wordID], indexOfMatch);
        }

        checkState = trie.get(checkState).suffixLink;
      }
    }

    return result;
  }
}

// Implement the Aho-Corasick Algorithm for this functionality
public class WordFinder {
  /**
   * Given a String (the haystack) and an array of Strings (the needles),
   * return a Map<String, Integer>, where keys in the map correspond to
   * elements of needles that were found as substrings of haystack, and the
   * value for each key is the lowest index of haystack at which that needle
   * was found. A needle that was not found in the haystack should not be
   * returned in the output map.
   * 
   * @param haystack The string to search into.
   * @param needles The array of strings to search for. This array is not
   *                mutated.
   * @return The list of needles that were found in the haystack.
   */
  public static Map<String, Integer> getSubstrings(String haystack,
                                                    String[] needles) {
    Aho ahoAlg = new Aho();

    for (int i = 0; i < needles.length; i++) {
      ahoAlg.addString(needles[i], i);
    }

    ahoAlg.prepareAho();

    return ahoAlg.processString(haystack, needles);
  }
}