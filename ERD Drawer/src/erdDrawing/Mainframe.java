package erdDrawing;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Mainframe extends JFrame implements ActionListener{
	boolean contains = false;
	Entity sample1 = new Entity();
	Entity sample2 = new Entity();
	JPanel relationshipContent = new JPanel(new GridLayout(2,2));
	JComboBox[] entities = {new JComboBox(),new JComboBox()};
	JComboBox [] attributes = {new JComboBox(),new JComboBox()};
	JButton relDone = new JButton("Done");
	CheckboxGroup  many = new CheckboxGroup();
	JLabel manylbl = new JLabel("many");
	Checkbox manyyes = new Checkbox("Yes", many,false);
	Checkbox manyno = new Checkbox("no", many,true);
	JLabel mandetorylblrel = new JLabel("mandetory");
	CheckboxGroup  mandetoryrel = new CheckboxGroup();
	Checkbox mandetoryyesrel = new Checkbox("Yes", mandetoryrel,false);
	Checkbox mandetorynorel = new Checkbox("no", mandetoryrel,true);
	JPanel checkboxesRel = new JPanel(new GridLayout(2,3));
	Point mousePos = new Point();
	Board board = new Board();
	Entity ent = new Entity(), selectedEnt = new Entity();
	Attribute att = new Attribute();
	JPanel southPanel = new JPanel(new GridLayout(1,3));
	JButton addEntity = new JButton("Add Entity");
	JButton addRelationship = new JButton("Add Relationship");
	JButton generateDatabase = new JButton("Generate Database");
	JPanel entityPanel = new JPanel(new GridLayout(1,2));
	JLabel entityNamelbl = new JLabel("Entity Name");
	JTextField entityName = new JTextField(10);
	JLabel attributesTxt = new JLabel();
	JPanel entitySouthPanel = new JPanel(new GridLayout(1,2));
	JButton addAttribute = new JButton("Add Attribute");
	JButton entityDone = new JButton("Done");
	JPanel attributeNorth = new JPanel(new GridLayout(1,2));
	JLabel attributeNamelbl = new JLabel("Attribute Name");
	JTextField attributeNametxt = new JTextField(10);
	JPanel checkBoxPanel = new JPanel(new GridLayout(2,3));
	CheckboxGroup  uID = new CheckboxGroup();
	JLabel uIDlbl = new JLabel("UID");
	Checkbox uIDyes = new Checkbox("Yes", uID,false);
	Checkbox uIDno = new Checkbox("no", uID,true);
	JLabel mandetorylbl = new JLabel("mandetory");
	CheckboxGroup  mandetory = new CheckboxGroup();
	Checkbox mandetoryyes = new Checkbox("Yes", mandetory,false);
	Checkbox mandetoryno = new Checkbox("no", mandetory,true);
	JButton attributeDone = new JButton("Done");
	
	public void populateSamples(){
		sample1.name="person";
		sample1.loc = new Point(500,500);
		Attribute attSample1 = new Attribute();
		attSample1.mandetory = true;
		attSample1.pk = true;
		attSample1.name = "height";
		sample2.name="animal";
		sample2.loc = new Point(0,0);
		Attribute attSample2 = new Attribute();
		attSample2.mandetory = false;
		attSample2.pk = false;
		attSample2.name = "age";
		sample1.addAttribute(attSample1);
		sample2.addAttribute(attSample2);
		board.entities.add(sample1);
		board.entities.add(sample2);
//		Relationship relSample1 = new Relationship(sample1, attSample1, true, true);
//		Relationship relSample2 = new Relationship(sample2, attSample2, true, true);
//		Relationships relSample = new Relationships(relSample1, relSample2);
//		board.relationships.add(relSample);
	}
	
	public void reset(){
		selectedEnt = new Entity();
		mandetoryrel.setCurrent(mandetorynorel);
		many.setCurrent(manyno);
		attributesTxt.setText("");
		entityName.setText("");
		mandetory.setCurrent(mandetoryno);
		uID.setCurrent(uIDno);
		attributeNametxt.setText("");
	}

	public Mainframe(){
		populateSamples();
		relDone.addActionListener(this);
//		addRelationship.setEnabled(false);
		for(int i =0; i < 2; i++){
			entities[i].addActionListener(this);
			attributes[i].addActionListener(this);
		}
		relationshipContent.add(entities[0]);
		relationshipContent.add(entities[1]);
		relationshipContent.add(attributes[0]);
		relationshipContent.add(attributes[1]);
		checkboxesRel.add(manylbl);
		checkboxesRel.add(manyyes);
		checkboxesRel.add(manyno);
		checkboxesRel.add(mandetorylblrel);
		checkboxesRel.add(mandetoryyesrel);
		checkboxesRel.add(mandetorynorel);
		attributeDone.addActionListener(this);
		attributeNorth.add(attributeNamelbl);
		attributeNorth.add(attributeNametxt);
		checkBoxPanel.add(uIDlbl);
		checkBoxPanel.add(uIDyes);
		checkBoxPanel.add(uIDno);
		checkBoxPanel.add(mandetorylbl);
		checkBoxPanel.add(mandetoryyes);
		checkBoxPanel.add(mandetoryno);
		entityDone.addActionListener(this);
		addAttribute.addActionListener(this);
		entitySouthPanel.add(addAttribute);
		entitySouthPanel.add(entityDone);
		entityPanel.add(entityNamelbl);
		entityPanel.add(entityName);
		southPanel.add(addEntity);
		addEntity.addActionListener(this);
		southPanel.add(addRelationship);
		addRelationship.addActionListener(this);
		southPanel.add(generateDatabase);
		generateDatabase.addActionListener(this);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(0,0, 1100, 700);
		this.setTitle("Erd Generator");
		this.getContentPane().addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseDragged(MouseEvent e){
				if(contains){
					selectedEnt.loc.x = selectedEnt.loc.x+(e.getX()-mousePos.x);
					selectedEnt.loc.y = selectedEnt.loc.y+(e.getY()-mousePos.y);
					mousePos = e.getPoint();
					board.repaint();
				}
			}
		});
		this.getContentPane().addMouseListener(new MouseAdapter()
		{
			public void mouseReleased(MouseEvent e){
				contains = false;
			}

			public void mousePressed(MouseEvent e){
				for(int i = 0; i < board.entities.size(); i++){
					selectedEnt = board.entities.get(i);
					if(selectedEnt.contains(e.getPoint())){
						contains = true;
					}
				}
			}
		});
		mainContent();

	}

	public void attributeContent(){
		remove();
		this.add(attributeNorth, BorderLayout.NORTH);
		this.add(checkBoxPanel);
		this.add(attributeDone, BorderLayout.SOUTH);
		this.setVisible(true);
	}

	public void mainContent(){
		remove();
		reset();
		this.add(southPanel, BorderLayout.SOUTH);
		this.add(board);
		this.setVisible(true);
	}

	public void relationshipContent(){
		remove();
		for(int i = 0; i < 2; i++){
			entities[i].removeAllItems();
			attributes[i].removeAllItems();
		}

		for(int i = 0; i < board.entities.size(); i++){
			entities[0].addItem(board.entities.get(i).name);
			entities[1].addItem(board.entities.get(i).name);
		}


		for(int j = 0; j < board.entities.get(0).attributes.size(); j++)
		{
			attributes[0].addItem(board.entities.get(0).attributes.get(j).name);
			attributes[1].addItem(board.entities.get(0).attributes.get(j).name);
		}	
		this.add(relationshipContent, BorderLayout.NORTH);
		this.add(checkboxesRel, BorderLayout.CENTER);
		this.add(relDone, BorderLayout.SOUTH);
		this.setVisible(true);
	}

	public void entityContent(){
		remove();
		this.add(entityPanel, BorderLayout.NORTH);
		this.add(attributesTxt);
		for(int i = 0; i < ent.attributes.size(); i++){
			attributesTxt.setText(attributesTxt.getText()+ent.attributes.get(i).toString() + "\n");
		}
		this.add(entitySouthPanel, BorderLayout.SOUTH);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new Mainframe();
	}

	public void remove(){
		this.getContentPane().removeAll();
		this.getContentPane().repaint();
	}
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i < entities.length; i++){
			if(entities[i]==e.getSource() && entities[i].getSelectedIndex() >= 0){
				for(int j = 0; j < board.entities.get(entities[i].getSelectedIndex()).attributes.size(); j++)
				{
					attributes[i].removeAllItems();
					attributes[i].addItem(board.entities.get(entities[i].getSelectedIndex()).attributes.get(j).name);
				}
			}
		}

		if(e.getSource() == addEntity){
			entityContent();
			ent = new Entity();
		}

		else if(e.getSource() == entityDone){
			ent.setName(entityName.getText());
			board.entities.add(ent);
			mainContent();
		}

		else if(e.getSource() == addAttribute){
			att = new Attribute();
			attributeContent();
		}

		else if(e.getSource() == attributeDone){
			att.name = attributeNametxt.getText();
			if(uID.getSelectedCheckbox()==uIDyes){
				att.pk =true;
			}

			if(mandetory.getSelectedCheckbox()==mandetoryyes){
				att.mandetory =true;
			}
			ent.attributes.add(att);
			entityContent();
		}

		else if(e.getSource() == addRelationship){
			relationshipContent();
		}
		
		else if(relDone == e.getSource()){
			Relationship temprel1 = new Relationship(board.entities.get(entities[0].getSelectedIndex()),board.entities.get(entities[0].getSelectedIndex()).attributes.get(attributes[0].getSelectedIndex()), false, false);
			Relationship temprel2 = new Relationship(board.entities.get(entities[1].getSelectedIndex()),board.entities.get(entities[1].getSelectedIndex()).attributes.get(attributes[1].getSelectedIndex()), false, false);
			board.relationships.add(new Relationships(temprel1, temprel2));
			mainContent();
		}
		
		if(board.entities.size()>=2){
			addRelationship.setEnabled(true);
		}
	}
}
