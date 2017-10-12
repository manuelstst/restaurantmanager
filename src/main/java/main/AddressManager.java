package main;

import restaurantmanager.entity.Address;
import restaurantmanager.service.AddressJDBCService;
import restaurantmanager.service.DATABASE;
import restaurantmanager.service.ServiceException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import org.apache.log4j.*;



public class AddressManager extends JFrame
{
    private ListPanel listPanel=new ListPanel(this,DATABASE.MySQL);
    private ButtonPanel buttonPanel=new ButtonPanel(this);
    private  static final Logger logger=Logger.getLogger(AddressManager.class);
    private MessagePanel messagePanel=new MessagePanel();
    private InputPanel inputPanel=new InputPanel(this);
    

    public static void main( String[] args )
    {
        AddressManager a = new AddressManager();
    }

   

    public ListPanel getListPanel() {
        return listPanel;
    }

    public ButtonPanel getButtonPanel() {
        return buttonPanel;
    }
    
     public MessagePanel getMessagePanel() {
        return messagePanel;
    }

    public InputPanel getInputPanel() {
        return inputPanel;
    }
    
    public AddressManager(){
        super("Adressverwaltung");
        BasicConfigurator.configure();
        this.setPreferredSize(new Dimension(500 ,600));
        
        this.add(this.inputPanel,BorderLayout.NORTH);

        this.add(this.listPanel,BorderLayout.CENTER);
        
        JPanel spanel=new JPanel(new GridLayout(2,0));
        spanel.add(this.messagePanel);
        spanel.add(this.buttonPanel);
        this.add(spanel,BorderLayout.SOUTH);
        
        this.getListPanel().addressManagerJTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==1){
                    getInputPanel().clearFields();
                }
            }   
        });

        this.pack();
        this.setVisible(true);
    }
    
    public void displayMessage(String message){
        getMessagePanel().setMessage(message);
    }
    
    /*
    class MessagePanel extends JPanel{
        private JTextField msgField=new JTextField();
        
        public MessagePanel(){
            this.msgField.setSize(100,100);
            this.add(this.msgField);
        }
        
        public void setMessage(String string){
            this.msgField.setText(string);
        }
    }
    */
    
    class AddressManagerModel extends AbstractTableModel{
        private List<Address> customers;
        private AddressManager addressManager;
        private AddressJDBCService addressService;
        
        public AddressManagerModel(AddressManager addressManager,DATABASE database) {
            this.addressManager=addressManager;
            this.addressService=new AddressJDBCService(database);
            try {
                this.customers=this.addressService.findAll();
            } catch (ServiceException ex) {
                System.out.println(ex.getMessage());
            }
        }
        /*
        public AddressManagerModel(AddressManager addressManager,DATABASE database) {
            this.addressManager=addressManager;
            this.addressService=new AddressJDBCService(database);
            try {
                this.customers=this.addressService.findAll();
            } catch (ServiceException ex) {
                System.out.println(ex.getMessage());
            }
        }
        */

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }

        @Override
        public String getColumnName(int column) {
            switch(column){
                case 0: return "Vorname";
                case 1: return "Nachname";
                case 2: return "Adresse";
                default:return "";
            }
        }
        
        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            String s=(String)aValue;
            Address adr=this.customers.get(rowIndex);
            switch(columnIndex){
                case 0: adr.setFirstname(s); this.updateRow(adr); break;
                case 1: adr.setLastname(s); this.updateRow(adr); break;
                case 2: adr.setAddress(s); this.updateRow(adr); break;
            }
        }
        
        public void addRow(Address adr){
            try {
                this.addressService.insert(adr);
                this.customers=this.addressService.findAll();
            } catch (ServiceException ex) {
                System.out.println(ex.getMessage());
            }
            this.fireTableDataChanged();
        }

        public void updateRow(Address adr){
            try {
                this.addressService.update(adr);
                this.customers=this.addressService.findAll();
            } catch (ServiceException ex) {
                System.out.println(ex.getMessage());
            }
            this.fireTableDataChanged();
        }
        
        /*
        public void updateRow(Address adr){
             try {
                this.addressService.delete(id);
                this.customers=this.addressService.findAll();
            } catch (ServiceException ex) {
                System.out.println(ex.getMessage());
            }
            this.fireTableDataChanged()
        }
        */

        public void deleteRow(int id){
            try {
                this.addressService.delete(id);
                this.customers=this.addressService.findAll();
            } catch (ServiceException ex) {
                System.out.println(ex.getMessage());
            }
            this.fireTableDataChanged();
        }
        
        @Override
        public int getRowCount() {
            return this.customers.size();
        }

        public Address getRow(int index){
            return this.customers.get(index);
        }

        @Override
        public int getColumnCount() {
            return 3;
        }
        
        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return true;
        }

        

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
                switch(columnIndex){
                    case 0: return this.customers.get(rowIndex).getFirstname();
                    case 1: return this.customers.get(rowIndex).getLastname();
                    case 2: return this.customers.get(rowIndex).getAddress();
                    default:return null;
                }
        }

     
        
        
    }
    
    class MessagePanel extends JPanel{
        private JTextField msgField=new JTextField();
        
        public MessagePanel(){
            this.msgField.setSize(100,100);
            this.add(this.msgField);
        }
        
        public void setMessage(String string){
            this.msgField.setText(string);
        }
    }
    
 
    class ListPanel extends JPanel{
        private AddressManager addressManager;
        protected AddressManagerModel addressManagerModel;
        protected JTable addressManagerJTable;

        public ListPanel(AddressManager addressManager,DATABASE database) {
            this.addressManager = addressManager;
            this.addressManagerModel=new AddressManagerModel(addressManager,database);
            this.addressManagerJTable=new JTable(addressManagerModel);

            this.add(new JScrollPane(this.addressManagerJTable));
        } 
        /*
        public ListPanel(AddressManager addressManager,DATABASE database) {
            this.addressManager = addressManager;
            this.addressManagerModel=new AddressManagerModel(addressManager,database);
            this.addressManagerJTable=new JTable(addressManagerModel);

            this.add(new JScrollPane(this.addressManagerJTable));
        } 
        
        public void addAddress(Address address){
            this.addressManagerModel.addRow(address);
            logger.info("Adresse hinzugefügt!");  
        }
        
        public void updateAddress(Address address){
            this.addressManagerModel.updateRow(address);
            logger.info("Adresse geändert!");
        }
        
        public void deleteAddress(Address address){
            this.addressManagerModel.deleteRow(address.getId());
            logger.info("Adresse gelöscht!");
        }
        */
        
        public void addAddress(Address address){
            this.addressManagerModel.addRow(address);
            logger.info("Adresse hinzugefügt!");  
        }
        
        public void updateAddress(Address address){
            this.addressManagerModel.updateRow(address);
            logger.info("Adresse geändert!");
        }
        
        public void deleteAddress(Address address){
            this.addressManagerModel.deleteRow(address.getId());
            logger.info("Adresse gelöscht!");
        }
    }
    
    class ButtonPanel extends JPanel implements ActionListener{
        private AddressManager addressManager;
        private JButton deleteButton=new JButton("Löschen");
        private JButton newButton=new JButton("Neu");
        private JButton cancelButton=new JButton("Abbrechen");
        

        public ButtonPanel(AddressManager addressManager) {
            this.addressManager = addressManager;
            
            this.setLayout(new FlowLayout(FlowLayout.RIGHT));
            this.add(cancelButton);
            this.add(deleteButton);
            this.add(newButton);
            
            //add Listeners
            this.newButton.addActionListener(this);
            this.cancelButton.addActionListener(this);
            this.deleteButton.addActionListener(this);
        }
        
        public boolean enableChangeButton(){
            return true;
        }
        
        /*
        public ButtonPanel(AddressManager addressManager) {
            this.addressManager = addressManager;
            
            this.setLayout(new FlowLayout(FlowLayout.RIGHT));
            this.add(cancelButton);
            this.add(deleteButton);
            this.add(newButton);
            
            //add Listeners
            this.newButton.addActionListener(this);
            this.cancelButton.addActionListener(this);
            this.deleteButton.addActionListener(this);
        }
        */
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals(newButton.getActionCommand())){
                if(getInputPanel().validateListEntry()==true){
                    getListPanel().addAddress(getInputPanel().createListEntry());
                    Address adr=getInputPanel().createListEntry();
                    getMessagePanel().setMessage("Kunde "+adr.getFirstname()+" "+adr.getLastname()+" wurde hinzugefügt!");
                }
            }
            else if(e.getActionCommand().equals(cancelButton.getActionCommand())){
                System.exit(0);
            }
            else if(e.getActionCommand().equals(deleteButton.getActionCommand())){
                getListPanel().deleteAddress(getListPanel().addressManagerModel.getRow(getListPanel().addressManagerJTable.getSelectedRow()));
            }
        }
    }

    class InputPanel extends JPanel{
        private AddressManager addressManager;
        private JLabel lblFirstname=new JLabel("Vorname");
        private JLabel lblLastname=new JLabel("Nachname");
        private JLabel lblAddress=new JLabel("Adresse");
        private JTextField firstname=new JTextField();
        private JTextField lastname=new JTextField();
        private JTextField address=new JTextField();

        public InputPanel(AddressManager addressManager) {
            this.addressManager = addressManager;
            
            this.setLayout(new GridLayout(3,2));
            
            this.add(this.lblFirstname);
            this.add(this.firstname);
            this.add(this.lblLastname);
            this.add(this.lastname);
            this.add(this.lblAddress);
            this.add(this.address);
        }

        public Address createListEntry(){
            return new Address(this.firstname.getText(),this.lastname.getText(),this.address.getText());
        }

        public void displayListEntry(Address address){
            this.firstname.setText(address.getFirstname());
            this.lastname.setText(address.getLastname());
            this.address.setText(address.getAddress());
        }

        public void clearFields(){
            this.firstname.setText("");
            this.lastname.setText("");
            this.address.setText("");
        }
        
        /*
         public void clearFields(){
            this.firstname.setText("");
            this.lastname.setText("");
            this.address.setText("");
        }

        private boolean validateField(JTextField field,JLabel label){
            if(field.getText().equals("")){
                this.addressManager.getMessagePanel().setMessage("Bitte "+label.getText()+" eingeben");
                field.requestFocus();
                return false;
            }
            else
                return true;
        }

        public boolean validateListEntry(){
            if(this.validateField(this.firstname,this.lblFirstname)==false)
                return false;
            else if(this.validateField(this.lastname,this.lblLastname)==false)
                return false;
            else if(this.validateField(this.address,this.lblAddress)==false)
                return false;
            else
                return true;
        }
        */
        private boolean validateField(JTextField field,JLabel label){
            if(field.getText().equals("")){
                this.addressManager.getMessagePanel().setMessage("Bitte "+label.getText()+" eingeben");
                field.requestFocus();
                return false;
            }
            else
                return true;
        }

        public boolean validateListEntry(){
            if(this.validateField(this.firstname,this.lblFirstname)==false)
                return false;
            else if(this.validateField(this.lastname,this.lblLastname)==false)
                return false;
            else if(this.validateField(this.address,this.lblAddress)==false)
                return false;
            else
                return true;
        }
    }
}