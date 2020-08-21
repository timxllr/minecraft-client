package de.crazymemecoke.utils.render.slick.svg.inkscape;

import de.crazymemecoke.utils.render.slick.geom.Rectangle;
import de.crazymemecoke.utils.render.slick.geom.Shape;
import de.crazymemecoke.utils.render.slick.geom.Transform;
import de.crazymemecoke.utils.render.slick.svg.Diagram;
import de.crazymemecoke.utils.render.slick.svg.Figure;
import de.crazymemecoke.utils.render.slick.svg.Loader;
import de.crazymemecoke.utils.render.slick.svg.NonGeometricData;
import de.crazymemecoke.utils.render.slick.svg.ParsingException;
import org.w3c.dom.Element;

/**
 * A processor for the <rect> element.
 *
 * @author kevin
 */
public class RectProcessor implements ElementProcessor {

	/**
	 * @see de.crazymemecoke.utils.render.slick.svg.inkscape.ElementProcessor#process(de.crazymemecoke.utils.render.slick.svg.Loader, Element, de.crazymemecoke.utils.render.slick.svg.Diagram, de.crazymemecoke.utils.render.slick.geom.Transform)
	 */
	public void process(Loader loader, Element element, Diagram diagram, Transform t) throws ParsingException {
		Transform transform = Util.getTransform(element);
	    transform = new Transform(t, transform); 
		
		float width = Float.parseFloat(element.getAttribute("width"));
		float height = Float.parseFloat(element.getAttribute("height"));
		float x = Float.parseFloat(element.getAttribute("x"));
		float y = Float.parseFloat(element.getAttribute("y"));
		
		Rectangle rect = new Rectangle(x,y,width+1,height+1);
		Shape shape = rect.transform(transform);
		
		NonGeometricData data = Util.getNonGeometricData(element);
		data.addAttribute("width", ""+width);
		data.addAttribute("height", ""+height);
		data.addAttribute("x", ""+x);
		data.addAttribute("y", ""+y);
		
		diagram.addFigure(new Figure(Figure.RECTANGLE, shape, data, transform));
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.svg.inkscape.ElementProcessor#handles(Element)
	 */
	public boolean handles(Element element) {
		if (element.getNodeName().equals("rect")) {
			return true;
		}
		
		return false;
	}
}
