package de.crazymemecoke.utils.render.slick.svg.inkscape;

import de.crazymemecoke.utils.render.slick.geom.Ellipse;
import de.crazymemecoke.utils.render.slick.geom.Shape;
import de.crazymemecoke.utils.render.slick.geom.Transform;
import de.crazymemecoke.utils.render.slick.svg.Diagram;
import de.crazymemecoke.utils.render.slick.svg.Figure;
import de.crazymemecoke.utils.render.slick.svg.Loader;
import de.crazymemecoke.utils.render.slick.svg.NonGeometricData;
import de.crazymemecoke.utils.render.slick.svg.ParsingException;
import org.w3c.dom.Element;

/**
 * Processor for <ellipse> and <path> nodes marked as arcs
 *
 * @author kevin
 */
public class EllipseProcessor implements ElementProcessor {
	
	/**
	 * @see ElementProcessor#process(de.crazymemecoke.utils.render.slick.svg.Loader, Element, de.crazymemecoke.utils.render.slick.svg.Diagram, de.crazymemecoke.utils.render.slick.geom.Transform)
	 */
	public void process(Loader loader, Element element, Diagram diagram, Transform t) throws ParsingException {
		Transform transform = Util.getTransform(element);
		transform = new Transform(t, transform);
		
		float x = Util.getFloatAttribute(element,"cx");
		float y = Util.getFloatAttribute(element,"cy");
		float rx = Util.getFloatAttribute(element,"rx");
		float ry = Util.getFloatAttribute(element,"ry");
		
		Ellipse ellipse = new Ellipse(x,y,rx,ry);
		Shape shape = ellipse.transform(transform);

		NonGeometricData data = Util.getNonGeometricData(element);
		data.addAttribute("cx", ""+x);
		data.addAttribute("cy", ""+y);
		data.addAttribute("rx", ""+rx);
		data.addAttribute("ry", ""+ry);
		
		diagram.addFigure(new Figure(Figure.ELLIPSE, shape, data, transform));
	}

	/**
	 * @see ElementProcessor#handles(Element)
	 */
	public boolean handles(Element element) {
		if (element.getNodeName().equals("ellipse")) {
			return true;
		}
		if (element.getNodeName().equals("path")) {
			if ("arc".equals(element.getAttributeNS(Util.SODIPODI, "type"))) {
				return true;
			}
		}
		
		return false;
	}

}
