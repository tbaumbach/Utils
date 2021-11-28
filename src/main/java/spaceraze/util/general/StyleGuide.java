package spaceraze.util.general;

import java.awt.Color;
import java.awt.Font;


public class StyleGuide {

  public static final Color colorMapEditorGrid = new Color(0,255,0);
  public static final Color colorNeutral = new Color(255,191,0); // Light Orange
  public static final Color colorNeutralWhite = new Color(250,250,200); // White
  public static final Color colorBackground = new Color(0,0,0); // black

  public static final Color colorNAVBackground9 = new Color(12,51,105); // black
  public static final Color colorNAVForeground9 = new Color(172,172,222); // black
  
  public static final Color colorGreenBackground9 = new Color(20,102,4); // black
  public static final Color colorGreenForeground9 = new Color(0,254,35); // black
  public static final Color colorGreenBorder = new Color(0,254,35); // black
  
  
  public static final Color colorMapInsets = new Color(0,127,0); // dark green
  public static final Color colorMapPlanetNames = new Color(41,198,255); // light blue
  public static Color colorCurrent = new Color(255,191,0); // Initiates to Neutral color = Light Orange
  public static final Font buttonFont = new Font("Dialog",Font.BOLD,12);
  public static final Color colorMapLongRange = new Color(0,25,108); // long range dark blue
  public static final Color colorMapShortRange = new Color(160,0,40); // short range dark red
  public static final Color colorMapSpacePortsRange = new Color(255,140,80); // short range due to spaceports orange
  
  public static final Color COLOR_DIPLOMACY_ETERNALWAR = new Color(255,127,0);
  public static final Color COLOR_DIPLOMACY_WAR = new Color(255,0,0);
  public static final Color COLOR_DIPLOMACY_CEASEFIRE = new Color(255,255,255);
  public static final Color COLOR_DIPLOMACY_PEACE = new Color(255,255,0);
  public static final Color COLOR_DIPLOMACY_ALLINNCE = new Color(0,255,0);
  public static final Color COLOR_DIPLOMACY_CONFEDERACY = new Color(0,0,255);
  public static final Color COLOR_DIPLOMACY_VASSALL = new Color(0,255,255);
  public static final Color COLOR_DIPLOMACY_LORD = new Color(191,0,255);
  public static final Color COLOR_DIPLOMACY_DISABLED = new Color(63,63,63);

  private StyleGuide(){}

  public static void reset(){
  	colorCurrent = colorNeutral;
  }
}