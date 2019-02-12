package com.flowingcode.vaadin.addons.verticalmenu;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

/**
 * @author Martin Lopez / Flowing Code
 */
@Route("")
@SuppressWarnings("serial")
public class DemoView extends Div {
	
	public DemoView() {
		this.setSizeFull();
		Section inbox = new Section(new H1("Inbox"),createDummyInbox());
		inbox.getStyle().set("background-color", "green");
		VerticalMenu vm = new VerticalMenu(inbox,
										   new Section(new H1("Profile")),
										   new Section(new H1("Friends")),
										   new Section(new H1("Messages")),
										   new Section(new H1("Settings")));
		vm.addMenuSelectedListener(ev->{
			Notification.show("Section: " + ev.getSource().getElement().getChild(0).getText() + " clicked.");
		});
		vm.setSizeFull();
		this.add(vm);
	}

	private VerticalLayout createDummyInbox() {
		VerticalLayout vl = new VerticalLayout();
		vl.setHeight("calc(100% - 110px)");
		vl.setWidth("100vv");
		vl.getStyle().set("background-color", "white");
		TextField tf = new TextField("Search");
		tf.setWidth("100%");
		vl.add(tf);
		Grid<String> g = new Grid<>();
		g.addColumn(item->item).setHeader("Subject");
		g.addColumn(item->item).setHeader("From");
		g.addColumn(item->item).setHeader("Date");
		g.setItems("Item 1","Item 1","Item 1","Item 1","Item 1","Item 1","Item 1","Item 1","Item 1","Item 1");
		vl.add(g);
		vl.setMargin(true);
		vl.setSpacing(false);
		vl.setPadding(true);
		return vl;
	}
	
}
