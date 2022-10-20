package game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

/**
 * A megjelenítésért felelős osztály.
 *
 */
public class AkariFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	/**
	 * Az ablakhoz tartozó modell
	 */
	private BoardData data;
	/**
	 * Az ablakban megjelenő táblázat
	 */
	private JTable table;
	/**
	 * Az újrakezdés gomb
	 */
	private JButton ujra;
	/**
	 * A mentés gomb
	 */
	private JButton ments;
	/**
	 * A feladat elvégzésekor megdicsérő üzenet panele
	 */
	private JPanel ugyes;
	
	
	/**
	 * A program main függvénye.
	 * @param Args	ahogy minden main függvényben
	 */
	public static void main(String[] Args) {
		AkariFrame a = new AkariFrame();
		
		a.setVisible(true);
	}
	
	
	/**
	 * Konstruktor, elvégzi az alapvető beállításokat.
	 */
	public AkariFrame() {
		super("Akari");
		
		
//		m.add(new Mezo()); m.add(new Mezo()); m.add(new Mezo()); m.add(new Mezo()); m.add(new Mezo()); m.add(new Mezo()); m.add(new Mezo());
//		data.addRow(m); m.clear();
//		m.add(new Mezo()); m.add(new Mezo()); m.add(new Mezo()); m.add(new Mezo()); m.add(new Mezo()); m.add(new Mezo()); m.add(new Mezo());
//		data.addRow(m); m.clear();
//		m.add(new Mezo()); m.add(new Mezo()); m.add(new Mezo()); m.add(new Mezo()); m.add(new Mezo()); m.add(new Mezo()); m.add(new Mezo()); 
//		data.addRow(m); m.clear();
//		m.add(new Mezo()); m.add(new Mezo()); m.add(new Mezo()); m.add(new Mezo()); m.add(new Mezo()); m.add(new Mezo()); m.add(new Mezo()); 
//		data.addRow(m); m.clear();
//		m.add(new Mezo()); m.add(new Mezo()); m.add(new Mezo()); m.add(new Mezo()); m.add(new Mezo()); m.add(new Mezo()); m.add(new Mezo()); 
//		data.addRow(m); m.clear();
//		m.add(new Mezo()); m.add(new Mezo()); m.add(new Mezo()); m.add(new Mezo()); m.add(new Mezo()); m.add(new Mezo()); m.add(new Mezo()); 
//		data.addRow(m); m.clear();
		
		
		setMinimumSize(new Dimension(550, 650));
		initComponents();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
//		//Bezáráskor mentjük az adatokat
//        addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                data.serialize("10x10.1.dat");
//            }
//        });
	}

	/**
	 * Inicializáló függvény, mely elhelyezi a különböző elemeket a frame-en.
	 */
	public void initComponents() {
		data = new BoardData(this);
		this.setLayout(new BorderLayout());
		JPanel p = new JPanel();
		JButton szabalyok = new JButton("Játékszabályok");
		szabalyok.addActionListener(new RulesListener(this));
		p.add(szabalyok);
		ujra = new JButton("Újrakezdés");
		p.add(ujra);
		ujra.setVisible(false);
		JButton ujat = new JButton("Új játék");
		ujat.addActionListener(new NewGameListener(this));
		p.add(ujat);
		ments = new JButton("Mentés");
		ments.setVisible(false);
		p.add(ments);
		JButton visszatolt = new JButton("Visszatöltés");
		visszatolt.addActionListener(new LoadListener(visszatolt, this, data));
		p.add(visszatolt);
		
		this.add(p, BorderLayout.NORTH);
		
		ugyes = new JPanel();
        JLabel kesz = new JLabel("Ügyes megoldás!");
		kesz.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
		ugyes.add(kesz);
        this.add(ugyes, BorderLayout.SOUTH);
        ugyes.setVisible(false);
        setLocationRelativeTo(null);
		
		this.pack();
	}
	
	
	/**
	 * Meghívják, ha kész a feladvány, megdicséri a játékost.
	 */
	public void ready() {
		ugyes.setVisible(true);
        this.validate();
	}
	
	
	/**
	 * Létrehozza a táblát (táblázatot), ahol lehet játszani, hozzáad listenereket, renderert.
	 * @param filename	melyik file-ból nyissa meg a pályát
	 * @param mentett	mentett játék visszatöltése vagy új játék
	 */
	public void createBoard(String filename, boolean mentett) {
		data = new BoardData(this);
		data.getBoard().deserialize(filename, mentett);
//		ArrayList<Mezo> m = new ArrayList<Mezo>();
//		m.add(new FalMezoSzammal(2)); m.add(new FeherMezo()); m.add(new FalMezo()); m.add(new FeherMezo()); m.add(new FeherMezo());
//		data.addRow(m); m.clear();
//		m.add(new FeherMezo()); m.add(new FalMezo()); m.add(new FeherMezo()); m.add(new FeherMezo()); m.add(new FeherMezo());
//		data.addRow(m); m.clear();
//		m.add(new FeherMezo()); m.add(new FalMezo()); m.add(new FeherMezo()); m.add(new FeherMezo()); m.add(new FeherMezo());
//		data.addRow(m); m.clear();
		
		ujra.setVisible(true);
		ujra.addActionListener(new RestartListener(data, ugyes));
		ments.setVisible(true);
		ments.addActionListener(new SaveListener(ments, data));
		
		table = new JTable();
	    JScrollPane pane = new JScrollPane(table);
	    table.setFillsViewportHeight(true);
	    table.setModel(data);
	    this.add(pane, BorderLayout.CENTER);
	    table.setRowHeight(50);
	    TableColumnModel columnModel = table.getColumnModel();
	    for (int i = 0; i < data.getColumnCount(); ++i) columnModel.getColumn(i).setWidth(50);
	    table.removeMouseListener(table.getMouseListeners()[0]);
	    table.addMouseListener(new TableListener(table, data));
	    
	    TableRowSorter<BoardData> sorter = new TableRowSorter<BoardData>(data);
	    for (int i = 0; i < data.getColumnCount(); ++i) {        	
	    	sorter.setSortable(i, false);
	    }
	    table.setRowSorter(sorter);
	    JTable t = new JTable();
	    BoardTableCellRenderer stcr = new BoardTableCellRenderer(t.getDefaultRenderer(String.class), data);
	    table.setDefaultRenderer(String.class, stcr);
	    
	    this.validate();
	}

}
