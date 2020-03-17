/*
 * Created on 2005-jan-03
 */
package spaceraze.util.general;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WMPABOD
 *
 * Improved version of the StringTokenizer class which can handle empty tokens
 */
public class StringTokenizerPlusPlus {
  private List<String> tokens = new ArrayList<>();
  private int currentToken = 0;

  public StringTokenizerPlusPlus(String inData, String delimiter) {

    if (inData.length() > 0) {

      int tmpIndex = inData.indexOf(delimiter);
      int currentIndex = 0;
      while (tmpIndex > -1) {

        tokens.add(currentIndex != tmpIndex ? inData.substring(currentIndex, tmpIndex) : "");
        currentIndex = tmpIndex + delimiter.length();

        if (currentIndex == inData.length()) {
            tmpIndex = -1;
        } else {
          tmpIndex = inData.substring(currentIndex).indexOf(delimiter);
          if (tmpIndex > -1){
            tmpIndex += currentIndex;
          }
        }
      }

      tokens.add(currentIndex == inData.length() ? "" : inData.substring(currentIndex));
    }
  }

  public String nextToken() {
    String returnString;
    returnString = tokens.get(currentToken);
    currentToken++;
    return returnString;

  }

  public int countTokens() {
    return tokens.size() - currentToken;
  }

  public boolean hasMoreTokens(){
    return currentToken < tokens.size();
  }

  public void reset(){
    currentToken = 0;
  }
}
