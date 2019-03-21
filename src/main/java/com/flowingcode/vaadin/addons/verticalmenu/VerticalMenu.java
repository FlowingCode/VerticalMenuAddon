package com.flowingcode.vaadin.addons.verticalmenu;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.shared.Registration;

/**
 * 
 * 
 * @author Martin Lopez / Flowing Code
 */
@Tag("div")
@SuppressWarnings("serial")
@StyleSheet("frontend://styles/vertical-menu-styles.css")
@JavaScript("frontend://scripts/vertical-menu-styles.js")
public class VerticalMenu extends Component implements HasSize {
	
	private boolean menuOpened;
	
	public VerticalMenu(Section ... sections) {
		this.getElement().setAttribute("class", "wrapper");
		this.getElement().appendChild(new H1("Menu").getElement());
		Icon button = VaadinIcon.MENU.create();
		button.addClassName("menu-btn");
		button.getElement().setAttribute("onclick", "toggleMenu()");
		button.getElement().addEventListener("click", ev->{
			menuOpened = true;
		});
		this.getElement().appendChild(button.getElement());
		int index = 1;
		for (Section section : sections) {
			addMenu(section, index++);
		}
		UI.getCurrent().getPage().executeJavaScript("goToPage(0)");
	}

	@Override
	protected void onAttach(AttachEvent attachEvent) {
		super.onAttach(attachEvent);
		getUI().ifPresent(ui->ui.getElement().getClassList().add("vertical-menu"));
	}
	
	@SuppressWarnings("unchecked")
	private void addMenu(Section s, int index) {
		s.getElement().setAttribute("onclick", "goToPage(" + (index-1) + ")");
		s.addClassName("class" + index);
		s.addClickListener(ev->{
			if (menuOpened) {
				fireEvent(new MenuSelectedEvent((Section) ev.getSource(), true));
				menuOpened=false;
			}
		});
		this.getElement().appendChild(s.getElement());
	}
	
    public Registration addMenuSelectedListener(
            ComponentEventListener<MenuSelectedEvent> listener) {
        return addListener(MenuSelectedEvent.class, listener);
    }
	
	public static class MenuSelectedEvent extends ComponentEvent<Section> {
	    public MenuSelectedEvent(Section source, boolean fromClient) {
	        super(source, fromClient);
	    }
	}
	
}
