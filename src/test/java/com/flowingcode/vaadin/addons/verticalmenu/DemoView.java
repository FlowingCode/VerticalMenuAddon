/*-
 * #%L
 * Vertical Menu Addon
 * %%
 * Copyright (C) 2019 Flowing Code
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.flowingcode.vaadin.addons.verticalmenu;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("")
@SuppressWarnings("serial")
public class DemoView extends VerticalMenu {

	public DemoView() {
		super(new Section(new H1("Inbox"),createDummyInbox()),
			   new Section(new H1("Profile")),
			   new Section(new H1("Friends")),
			   new Section(new H1("Messages")),
			   new Section(new H1("Settings")));
		getSections().get(0).getStyle().set("background-color", "green");
		reloadSections();
		addMenuSelectedListener(ev->{
			Notification.show("Section: " + ev.getSource().getElement().getChild(0).getText() + " clicked.");
		});
	}

	private static VerticalLayout createDummyInbox() {
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
