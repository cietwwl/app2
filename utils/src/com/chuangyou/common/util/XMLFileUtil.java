package com.chuangyou.common.util;

import java.net.URL;
import java.util.Iterator;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * The <code>XmlUtil</code> class supported your code to read/write xml data
 * from the file. all methods in this class depend on <code>dom4j</code>.
 * 
 */
public class XMLFileUtil {

	private XMLFileUtil() {
	}
	
	/**
	 * read xml content from some file, and load xml data into the Document
	 * object.
	 * 
	 * @param filePath
	 *            String
	 * @return Document
	 */
	public static Document LoadXmlFile(String filePath) {
		SAXReader reader = new SAXReader();
		// try to load xml data into Document object
		Document doc = null;
		try {
			String urlString = null;
			if (filePath.startsWith("/")) {
				urlString = "file://" + filePath;
			} else {
				urlString = "file:///" + filePath;
			}
			Log.debug("XML File's URL :" + urlString);
			doc = reader.read(new URL(urlString));
		} catch (Exception ex) {
			Log.error("Can not load ", ex);
		}
		return doc;
	}

	/**
	 * Get attribute value by name for some xml element.
	 * 
	 * @param element
	 *            Element
	 * @param attributeName
	 *            String
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	public static String getAttributeValue(Element element, String attributeName) {
		String attributeValue = null;
		for (Iterator i = element.attributeIterator(); i.hasNext();) {
			Attribute attribute = (Attribute) i.next();
			if (attribute.getName().equals(attributeName)) {
				attributeValue = (String) attribute.getData();
				break;
			}
		}
		return attributeValue;
	}

	@SuppressWarnings("unchecked")
	public static Element findElement(Element searchedElement, String targetNodePrefix, String targetNodeAttributeName, String targetNodeAttributeValue) {
		Element elementTarget = null;
		for (Iterator i = searchedElement.elementIterator(targetNodePrefix); i.hasNext();) {
			Element element = (Element) i.next();
			String strManagerName = XMLFileUtil.getAttributeValue(element, targetNodeAttributeName);
			if (strManagerName.equals(targetNodeAttributeValue)) {
				elementTarget = element;
				break;
			}
		}
		return elementTarget;
	}
}
