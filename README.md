[![Build Status](https://jenkins.flowingcode.com/job/VerticalMenu-addon/badge/icon)](https://jenkins.flowingcode.com/job/VerticalMenu-addon)
[![Javadoc](https://img.shields.io/badge/javadoc-00b4f0)](https://javadoc.flowingcode.com/artifact/com.flowingcode.addons/vertical-menu)

# Vertical Menu Addon

Colorful vertical menu for Vaadin 10+ based on https://codepen.io/iamturner/pen/RaqoPX

## Online demo

[Online demo here](http://addonsv14.flowingcode.com/vertical-menu)

## Download release

Official releases of this add-on are available at Vaadin Directory. For Maven instructions, download and reviews, go to https://vaadin.com/directory/component/vertical-menu-addon

## Building and running demo

- git clone repository
- mvn clean install jetty:run

To see the demo, navigate to http://localhost:8080/

## Release notes
- **Version 2.0.2** Fix compatibility [issue](https://github.com/FlowingCode/VerticalMenuAddon/issues/3) with Vaadin 16.
- **Version 2.0.1** Support for modifying sections dynamically and fixed [issue #1](https://github.com/FlowingCode/VerticalMenuAddon/issues/1)
- **Version 2.0.0** Support for Vaadin 14 NPM mode
- **Version 1.0.0** Initial Version

## Issue tracking

The issues for this add-on are tracked on its github.com page. All bug reports and feature requests are appreciated. 

## Contributions

Contributions are welcome, but there are no guarantees that they are accepted as such. Process for contributing is the following:

- Fork this project
- Create an issue to this project about the contribution (bug or feature) if there is no such issue about it already. Try to keep the scope minimal.
- Develop and test the fix or functionality carefully. Only include minimum amount of code needed to fix the issue.
- Refer to the fixed issue in commit
- Send a pull request for the original project
- Comment on the original issue that you have implemented a fix for it

## License & Author

This add-on is distributed under Apache License 2.0. For license terms, see LICENSE.txt.

VerticalMenu Addon is written by Flowing Code S.A.


# Developer Guide

## Using the component

- Basic usage
```
		VerticalMenu vm = new VerticalMenu(new Section(new H1("Inbox"),
										   new Section(new H1("Profile")),
										   new Section(new H1("Friends")),
										   new Section(new H1("Messages")),
										   new Section(new H1("Settings")));

```
- Menu selected listener
```
		vm.addMenuSelectedListener(ev->{
			Notification.show("Section: " + ev.getSource().getElement().getChild(0).getText() + " clicked.");
		});
```
