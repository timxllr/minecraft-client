package de.crazymemecoke.utils.render.slick.svg.inkscape;

import de.crazymemecoke.utils.render.slick.svg.NonGeometricData;
import org.w3c.dom.Element;

/**
 * A custom non-geometric data type that can pass back any attribute
 * on the field.
 * 
 * @author kevin
 */
public class InkscapeNonGeometricData extends NonGeometricData {
	/** The element read from the SVG */
	private Element element;
	
	/**
	 * Create a new non-geometric data holder
	 * 
	 * @param metaData The metadata provided
	 * @param element The XML element from the SVG document
	 */
	public InkscapeNonGeometricData(String metaData, Element element) {
		super(metaData);
		
		this.element = element;
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.svg.NonGeometricData#getAttribute(String)
	 */
	public String getAttribute(String attribute) {
		String result = super.getAttribute(attribute);
		if (result == null) {
			result = element.getAttribute(attribute);
		}
		
		return result;
	}

	/**
	 * Returns the XML element that is wrapped by this instance.
	 *
	 * @return The XML element for this instance
	 */
	public Element getElement() {
		return element;
	} 

}
