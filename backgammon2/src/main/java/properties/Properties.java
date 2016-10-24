/**
 * 
 */
package properties;

import java.io.File;
import java.util.LinkedHashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import bg.backgammon2.App;

/**
 * Diese Klasse stellt dem Rest des Spiels die Config daten zur Verfügung.
 * @author philipp
 * 
 */
public class Properties {
	private static LinkedHashMap<String, Object> config;
	protected static final Logger log = LogManager.getLogger(App.class);
	
	/**
	 * Gibt das zur property zugehörige objekt zurück
	 * @param property Key mit dem das zugehörige Objekt ermittelt wird.
	 * @return Objekt das zu property gespeichert wurde
	 */
	public static Object getObject(String property)
	{
		Object o = null;
		if(config == null)
		{
			log.error("Konfiguration angefordert obwohl config nicht initialisiert wurde.");
			return null;
		}
		else if(!config.containsKey(property))
		{
			log.error("Ungültige Eigenschaft angefordert: " + property);
			return null;
		}
		else
		{
			o = config.get(property);
		}
		return o;
	}
	
	/**
	 * Gibt den zur property zugehörigen Integer zurück
	 * @param property key mit dem der zugehörige Integer ermittelt wurde.
	 * @return Integer der zu property gespeichert wurde
	 */
	public static Integer getInteger(String property)
	{
		return (Integer) getObject(property);
	}
	

	/**
	 * Gibt den zur property zugehörigen String zurück
	 * @param property key mit dem der zugehörige String ermittelt wurde.
	 * @return String der zu property gespeichert wurde
	 */
	public static String getString(String property)
	{
		return (String) getObject(property);
	}
	
	/**
	 * Lädt die config daten aus der angegebenen Datei;
	 * @param fileName Dateiname der Konfigurationsdatei
	 */
	public static void init(String fileName)
	{
		try {

			File file = new File("res/config1.xml");

			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
		                             .newDocumentBuilder();

			Document doc = dBuilder.parse(file);

			
			if (doc.hasChildNodes()) {

				NodeList nodeList=doc.getChildNodes().item(0).getChildNodes();
				config = new LinkedHashMap<String, Object>();
				  for (int count = 0; count < nodeList.getLength(); count++) {
					  	
						Node tempNode = nodeList.item(count);
						if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

							System.out.println(tempNode.getNodeName()+ " " + tempNode.getTextContent());
							if(((String)tempNode.getTextContent()).matches("^-?\\d+$"))
							config.put(tempNode.getNodeName(), Integer.parseInt(tempNode.getTextContent()));
							else
							{
								config.put(tempNode.getNodeName(), tempNode.getTextContent());
							}
						}
						
				  }

			}

		    } catch (Exception e) {
			System.out.println(e.getMessage());
		    }

	}
}
