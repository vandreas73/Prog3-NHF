package game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 * A megjelenő táblázat stílusáért felelős osztály.
 *
 */
public class BoardTableCellRenderer extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 1L;
	/**
	 * a táblázat default renderere
	 */
	private TableCellRenderer renderer;
  	 /**
  	 * a táblázathoz tartozó modell
  	 */
  	private BoardData data;

  	 /**
  	  * Konstruktor
  	 * @param defRenderer	a táblázat default renderere
  	 * @param d				a táblázathoz tartozó modell
  	 */
  	public BoardTableCellRenderer(TableCellRenderer defRenderer, BoardData d) {
  		 this.renderer = defRenderer;
  		 data = d;
  	 }
  	 
  	
  	 /**
  	 * A színezést és az igazítást valósítja meg.
  	 */
  	@Override
  	public Component getTableCellRendererComponent(JTable table,
	 	Object value, boolean isSelected, boolean hasFocus,
	 	int row, int column) {
		row = table.getRowSorter().convertRowIndexToModel(row);
		 Component component = renderer.getTableCellRendererComponent(
				 table, value, isSelected, hasFocus, row, column);
		 int light = data.getMezoAt(row, column).lighting();
		 switch(light) {
		 case -1:
			 component.setBackground(Color.black);
			 if (data.getMezoAt(row, column).neighbourLampsCheck(data.getBoard(), row, column)) {	// ha megfelelő számú lámpa van a fal mellett, legyen zöld a szám
				 component.setForeground(Color.green); break;
			 }
			 component.setForeground(Color.white); break;
		 case 0: 
			 if (data.getBoard().isLighted(row, column)) {
				 component.setBackground(Color.yellow); break;
			 }
			 component.setBackground(Color.white); component.setForeground(Color.black); break;
		 case 1: 
			 component.setBackground(Color.yellow);
			 if (data.getBoard().isLighted(row, column)) {
				 component.setForeground(Color.red); break;
			 }
			  component.setForeground(Color.black); break;
		 default: component.setBackground(Color.gray); break;
		 }

		 ((JLabel)component).setHorizontalAlignment(SwingConstants.CENTER);
		 ((JLabel)component).setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		 return component;
  	 	}
  	 
}
