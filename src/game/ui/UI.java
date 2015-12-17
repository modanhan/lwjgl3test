package game.ui;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class UI {
	private static List<Element> elements = new LinkedList<Element>();
	public static void addElement(Element e){
		elements.add(e);
	}
	public static void removeElement(Element e){
		elements.remove(e);
	}
	public static void clear(){
		elements.clear();
	}
	public static void update(){
		Iterator<Element> i = elements.listIterator();
		while(i.hasNext()){
			Element e = i.next();
			e.update();
			e.render();
		}
	}
}
