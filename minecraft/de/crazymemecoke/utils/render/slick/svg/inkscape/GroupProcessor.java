package de.crazymemecoke.utils.render.slick.svg.inkscape;

import de.crazymemecoke.utils.render.slick.geom.Transform;
import de.crazymemecoke.utils.render.slick.svg.Diagram;
import de.crazymemecoke.utils.render.slick.svg.Loader;
import de.crazymemecoke.utils.render.slick.svg.ParsingException;
import org.w3c.dom.Element;

/**
 * TODO: Document this class
 *
 * @author kevin
 */
public class GroupProcessor implements ElementProcessor {

	/**
	 * @see de.crazymemecoke.utils.render.slick.svg.inkscape.ElementProcessor#handles(Element)
	 */
	public boolean handles(Element element) {
		if (element.getNodeName().equals("g")) {
			return true;
		}
		return false;
	}

	/**O
	 * @see de.crazymemecoke.utils.render.slick.svg.inkscape.ElementProcessor#process(de.crazymemecoke.utils.render.slick.svg.Loader, Element, de.crazymemecoke.utils.render.slick.svg.Diagram, de.crazymemecoke.utils.render.slick.geom.Transform)
	 */
	public void process(Loader loader, Element element, Diagram diagram, Transform t) throws ParsingException {
		Transform transform = Util.getTransform(element);
		transform = new Transform(t, transform);
		
		loader.loadChildren(element, transform);
	}

}
