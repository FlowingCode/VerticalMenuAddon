package com.flowingcode.vaadin.addons.verticalmenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.DetachEvent;
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
 * Vaadin component that represents a vertical menu with sections.
 *
 * How to use this component:
 *
 * <ol>
 * <li>Create your sections. Sections are created using the class {@link Section}.
 *
 * <li>Create the items of your sections. To add items to a section, simply call
 * <code>{@linkplain Section#Section(Component...) new Section(...)}</code> and pass the section
 * content as arguments.
 *
 * <li>Create an instance of this component. To create an instance, call
 * <code>{@linkplain #VerticalMenu(Section...) new VerticalMenu(...)}</code> and pass your sections
 * as arguments. Note: you can also add or remove sections later using the methods
 * {@link #getSections()} and {@link #setSections(List)}.
 *
 * <li>Add the component to your layout.
 *
 * <li>Listen for menu selected events. You can listen for menu selected events by adding a
 * {@link MenuSelectedEvent} listener to the component instance. To add a listener use the method
 * {@link #addMenuSelectedListener(ComponentEventListener)}.
 * </ol>
 * 
 * 
 * @author Martin Lopez / Flowing Code
 */
@Tag("div")
@SuppressWarnings("serial")
@StyleSheet("context://frontend/styles/vertical-menu-styles.css")
@JavaScript("./scripts/vertical-menu-styles.js")
public class VerticalMenu extends Component implements HasSize {

	private boolean menuOpened;
	
	private List<Section> sections = new ArrayList<>();

  /**
   * Creates an instance of VerticalMenu with the provided sections. When the menu is created, it
   * sets its initial size, appends a title, adds a menu button, and loads the sections that were
   * passed as arguments.
   *
   * @param sections The initial sections to display. These sections can be added or removed later
   *        using the method {@link #setSections(List)}
   */
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

	/**
	 * Reloads all the sections from {@link #getSections()}.
	 */
	public void reloadSections() {
		int index = 1;
		for (Section section : getSections()) {
			addMenu(section, index++);
		}
	}

	/**
	 * Attaches the component to the view.
	 * When the component is attached to a UI view, it appends a style tag to the document's head with the specified classes.
	 * A {@code vertical-menu} CSS class is added to the document head.
	 */
	@Override
	protected void onAttach(AttachEvent attachEvent) {
		super.onAttach(attachEvent);
		getUI().ifPresent(ui -> ui.getElement().getClassList().add("vertical-menu"));
	}

    /**
     * Detaches the component from the view.
     * When the component is detached, the added styles will also be removed.
     */
    @Override
    protected void onDetach(DetachEvent detachEvent) {
      getUI().ifPresent(ui -> ui.getElement().getClassList().remove("vertical-menu"));
      super.onDetach(detachEvent);
    }

	private void addMenu(Section s, int index) {
		s.getElement().setAttribute("onclick", "goToPage(" + (index - 1) + ")");
		s.addClassName("class" + index);
		s.addClickListener(ev -> {
			if (menuOpened) {
				fireEvent(new MenuSelectedEvent(ev.getSource(), true));
				menuOpened = false;
			}
		});
		getElement().appendChild(s.getElement());
	}

	/**
	 * Adds a new MenuSelectedEvent listener to the component.
	 *
	 * @param listener The new MenuSelectedEvent listener
	 * @return A registration object from the listener that can be used later to remove the listener
	 */
	public Registration addMenuSelectedListener(ComponentEventListener<MenuSelectedEvent> listener) {
		return addListener(MenuSelectedEvent.class, listener);
	}

	/**
	 * Returns the current list of sections in the component.
	 *
	 * @return The current list of sections in the component
	 */
	public List<Section> getSections() {
		return sections;
	}

	/**
	 * Sets the new list of sections to display on the component. All changes are immediately updated on this instance and its view.
	 * Also, it reloads all sections, effectively repainting the component.
	 *
	 * @param sections The new list of sections
	 */
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
