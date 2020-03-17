/*
 * Created on 2005-jul-16
 */
package spaceraze.util.properties;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @author WMPABOD
 *
 * Own class to read properties files and return Properties objects.
 * 
 * The reason to write my own class is that ResourceBundle cashes data so that updated
 * properties files can not be read without restarting Tomcat.
 */
public class PropertiesReader{
	private static String basePath;
	private static String dataPath;

	private PropertiesReader(){}
	
	public static Properties getProperties(String propFileName, boolean isStaticData) {
//		System.out.println("PropertiesReader.getProperties called, propFileName: " + propFileName);
		getPaths();
		// create the correct file path to the file
		String exactPath = getExactPath(null,propFileName + ".properties", isStaticData);
		// read the file
		return loadFile(exactPath);
	}
	
	public static String getExactPath(String folderPath, String propFileName, boolean isStaticData){
//		System.out.println("getExactPath, folderPath: " + folderPath + " propFileName: " + propFileName);
		String completePath = isStaticData ? basePath + "WEB-INF\\classes\\" : dataPath;
		if (folderPath != null){
			completePath = completePath + folderPath + File.separator;
		}
		completePath = completePath + propFileName;		
//		System.out.println("completePath = " + completePath);
		File tmp = new File(completePath);
		if (!tmp.exists()){
//			System.out.println("!tmp.exists()");
			int index = propFileName.indexOf(".");
			if (index != -1){
				String firstPart = propFileName.substring(0,index);
				String lastPart = propFileName.substring(index + 1);
				if (lastPart.equalsIgnoreCase("properties")){
					completePath = null;
				}else{
					// recursve call with first part of propFileName moved to folderPath
					String tmpFolderPath;
					if (folderPath == null){
						tmpFolderPath = firstPart;
					}else{
						tmpFolderPath = folderPath + File.separator + firstPart;
					}
					completePath = getExactPath(tmpFolderPath,lastPart, isStaticData);
				}
			}else{
				completePath = null;
			}
		}
		return completePath;
	}
	
	private static void getPaths(){
		if (basePath == null || dataPath == null){
			final String DEFAULT_PROPERTIES_NAME = "spaceraze";
			ResourceBundle bundle = ResourceBundle.getBundle(DEFAULT_PROPERTIES_NAME);
			Enumeration<String> tmpenum = bundle.getKeys();
			String key;
			while (tmpenum.hasMoreElements()) {
				key = tmpenum.nextElement();
				if (key.equals("basepath")){
					basePath = (String)bundle.getObject(key);
				}
				if (key.equals("datapath")){
					dataPath = (String)bundle.getObject(key);
				}
			}
//			System.out.println("Reading from spaceraze.properties, basePath = " + basePath);
		}
	}
	
	private static Properties loadFile(String aFileName){
//		System.out.println("loadFile, aFileName: " + aFileName);
		Properties prop = new Properties();
		try(FileReader fr = new FileReader(aFileName)){
			BufferedReader br = new BufferedReader(fr);
			String aLine = br.readLine();
			while (aLine != null){
//				System.out.println(aLine);
				aLine = br.readLine();
				if (isKeyValueRow(aLine)){
					String key = getKey(aLine);
					String value = getValue(aLine);
//					System.out.println("Key: " + key + " Value: " + value);
					prop.put(key, value);					
				}
			}
			br.close();
		}
		catch(FileNotFoundException fnfe){
			System.out.println("FileNotFoundException: " + fnfe.toString());
		}
		catch(IOException ioe){
			System.out.println("IOException: " + ioe.toString());
		}
		return prop;
	}
	
	private static boolean isKeyValueRow(String aLine){
		return !(aLine == null || aLine.startsWith("#") || !aLine.contains("="));
	}

	private static String getKey(String aLine){
		int indexEqualSign = aLine.indexOf("=");
		String keyPart = aLine.substring(0,indexEqualSign);
		keyPart = keyPart.trim();
		return keyPart;
	}
	
	private static String getValue(String aLine){
		int indexEqualSign = aLine.indexOf("=");
		String valuePart = aLine.substring(indexEqualSign + 1);
		valuePart = valuePart.trim();
		return valuePart;
	}
}
