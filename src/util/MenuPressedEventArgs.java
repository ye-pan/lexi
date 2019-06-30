package util;

import javax.swing.JMenuItem;

public class MenuPressedEventArgs {

	private JMenuItem menuItem;
	
	public MenuPressedEventArgs(JMenuItem item){
		this.menuItem = item;
	}
	
	public JMenuItem getMenuItem(){
		return this.menuItem;
	}
	
	public void setJMenuItem(JMenuItem item){
		this.menuItem = item;
	}

	public boolean isScrollOn() {
		return StringUtils.equals(getMenuItem().getText(), Buttons.SCROLL_ON_TEXT);
	}

	public boolean isScrollOff() {
		return StringUtils.equals(getMenuItem().getText(), Buttons.SCROLL_OFF_TEXT);
	}

	public boolean isSpellCheckOn() {
		return StringUtils.equals(getMenuItem().getText(), Buttons.SPELL_CHECK_ON_TEXT);
	}

	public boolean isSpellCheckOff() {
		return StringUtils.equals(getMenuItem().getText(), Buttons.SPELL_CHECK_OFF_TEXT);
	}
}
