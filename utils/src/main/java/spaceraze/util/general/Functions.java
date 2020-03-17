//Title:        SpaceRaze
//Author:       Paul Bodin
//Description:  Klass-metoder med generella funktioner

package spaceraze.util.general;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;


public class Functions implements Serializable {
    static final long serialVersionUID = 1L;
    static final Random random = new Random();

  /**
   * Returns a value between 1-10, or -1 if n/a
   * @return 1-10, but -1 if not acclicable, for instance a ship without huge ammo returns -1
   */
  public static String getDataValue(int cur, int max){
  	int data = 10;
  	if (max == Integer.MAX_VALUE){
  		data = -1;
  	}else
  	if (cur < max){
  		data = (int)Math.round(cur*10.0/max);
  		if (data == 0){
  			data = 1;
  		}
  	}
  	return String.valueOf(data);
  }
  

    public static int getRandomInt(int min, int max){
      return Math.abs(random.nextInt()%(max-min+1)) + min;
    }

    /**
     * Given a % chance (expressed as an int between 1-100), simulates a d100
     * dice roll and returns seccess or failiure.
     */
    public static boolean getD100(int chance){
    	boolean success = false;
        int d100 = Math.abs(random.nextInt()%100) + 1;
        if (d100 <= chance){
        	success = true;
        }
        return success;
    }
    
  // rekursiv metod som formaterar en sträng till ett visst antal decimaler
	public static String formatString(String input, int decimals){
	    int index = input.indexOf(".");
	    if (index == -1){
	        if (decimals == 0){
    	        return input;
	        }
	        else{
    	        return formatString(input + ".0",decimals);
    	    }
	    }
	    else
	    {
	        if ((index+decimals)==(input.length()-1)){
	            if (input.charAt(input.length()-1) == '.'){
	                return input.substring(0,input.length()-1);
	            }
	            else{
    	            return input;
    	        }
	        }
	        else
	        if ((index+decimals)>(input.length()-1)){
	            return formatString(input + "0",decimals);
	        }
	        else{
	            return formatString(input.substring(0,input.length()-1),decimals);
	        }
	    }
	}

  public static Random getRandom(){
    return random;
  }
  
  public static String getYesNo(boolean b){
  	String answer = "No";
  	if (b){
  		answer = "Yes";
  	}
  	return answer;
  }

  
  /**
   * Returns a copy of the object, or null if the object cannot
   * be serialized.
   * Link: http://javatechniques.com/blog/faster-deep-copies-of-java-objects/
   * date: 070503
   */
  @SuppressWarnings("unchecked")
  public static <A> A deepClone(A original){
	  A obj = null;
	  try {
		  // Write the object out to a byte array
		  ByteArrayOutputStream bos = new ByteArrayOutputStream();
		  ObjectOutputStream out = new ObjectOutputStream(bos);
		  out.writeObject(original);
		  out.flush();
		  out.close();
		  // Make an input stream from the byte array and read
		  // a copy of the object back in.
		  ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
		  obj = (A)in.readObject();
	  }
	  catch(IOException | ClassNotFoundException e) {
		  Logger.finer(e.getMessage());
	  }
	  return obj;
  }
  
  /*
   * Used when testing...
   
  public static void main(String args[]){
	  Alignment a1 = new Alignment("a1");
	  a1.setDescription("a1");
	  Alignment a2 = (Alignment)deepClone(a1);
	  a2.setDescription("a2");
	  System.out.println(a1.getDescription());
	  System.out.println(a2.getDescription());
  }
*/

  /**
   * Used when the first letter always should be lower case
   */
  public static String getDeterminedForm(String aWord){
	  return getDeterminedForm(aWord,false);
  }
	  
  /**
   * Retrun the determined form of the word entered depending on if the first
   * letter is a vocal or not.
   * Example: aWord = "admiral" will return "an"
   * @param aWord the word after who is in determined form
   * @param upperCaseA if "a" should be in upper case
   * @return "a" or "A" or "an" or "An"
   * 
   */
  public static String getDeterminedForm(String aWord, boolean upperCaseA){
	  String prefix = "a";
	  if (upperCaseA){
		  prefix = "A";
	  }
	  if ((aWord != null) && (aWord.length() > 0)){
		  String firstLetter = aWord.substring(0,1);
		  firstLetter = firstLetter.toLowerCase();
		  switch (firstLetter) {
			  case "a":
			  case "e":
			  case "i":
			  case "o":
			  case "u":
			  case "y":
				  prefix += "n";
				  break;
			  default:
			  	break;
		  }
	  }
	  return prefix;
  }

}