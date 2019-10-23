package com.flowingcode.vaadin.addons.verticalmenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
@StyleSheet("./styles/vertical-menu-styles.css")
@JavaScript("./scripts/vertical-menu-styles.js")
public class VerticalMenu extends Component implements HasSize {

	private boolean menuOpened;
	
	private List<Section> sections = new ArrayList<>();

	public VerticalMenu(Section... sections) {
		this.setSizeFull();
		this.getElement().setAttribute("class", "wrapper");
		this.getElement().appendChild(new H1("Menu").getElement());
		Icon button = VaadinIcon.MENU.create();
		button.addClassName("menu-btn");
		button.getElement().setAttribute("onclick", "toggleMenu()");
		button.getElement().addEventListener("click", ev -> menuOpened = true);
		this.getElement().appendChild(button.getElement());
		this.getSections().addAll(Arrays.asList(sections));
		reloadSections();
		UI.getCurrent().getPage().executeJs("goToPage(0)");
	}

	public void reloadSections() {
		int index = 1;
		for (Section section : getSections()) {
			addMenu(section, index++);
		}
	}

	@Override
	protected void onAttach(AttachEvent attachEvent) {
		super.onAttach(attachEvent);
		getUI().ifPresent(ui -> ui.getElement().getClassList().add("vertical-menu"));
	}

	private void addMenu(Section s, int index) {
		s.getElement().setAttribute("onclick", "goToPage(" + (index - 1) + ")");
		s.addClassName("class" + index);
		s.addClickListener(ev -> {
			if (menuOpened) {
				fireEvent(new MenuSelectedEvent((Section) ev.getSource(), true));
				menuOpened = false;
			}
		});
		this.getElement().appendChild(s.getElement());
	}

	public Registration addMenuSelectedListener(ComponentEventListener<MenuSelectedEvent> listener) {
		return addListener(MenuSelectedEvent.class, listener);
	}

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
		reloadSections();
	}

	public static class MenuSelectedEvent extends ComponentEvent<Section> {
		public MenuSelectedEvent(Section source, boolean fromClient) {
			super(source, fromClient);
		}
	}
	
}
