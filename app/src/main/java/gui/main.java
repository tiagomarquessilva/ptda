/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;



import communication.DBConnection;
import communication.SendCommand;
import others.User;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hugo Neves
 */
public class main extends javax.swing.JFrame {

    GridBagLayout layout=new GridBagLayout();
    roomDevices rp1;
    roomOpenings rp2;
    roomPanelInfo rpi;
    roomZones rz;
    roomZonesEdit rze;
    
    kitchenDevices kd;
    kitchenOpenings ko;
    kitchenPanelInfo kpi;
    kitchenZones kz;
    
    bedroomDevices bd;
    bedroomOpenings bo;
    bedroomPanelInfo bpi;
    bedroomZones bz;
    
    bedroomDevices2 bd2;
    bedroomOpenings2 bo2;
    bedroomPanelInfo2 bpi2;
    bedroomZones2 bz2;
    
    wcDevices wd;
    wcOpenings wo;
    wcPanelInfo wpi;
    wcZones wz;
    
    wcDevices2 wd2;
    wcOpenings2 wo2;
    wcPanelInfo2 wpi2;
    wcZones2 wz2;
    
    public main(User userlogged) {
        this.setExtendedState(this.MAXIMIZED_BOTH); 
        initComponents();
        bedroomFrame.hide();
        bedroomFrame2.hide();
        kitchenFrame.hide();
        roomFrame.hide();
        userEdit.hide();
        routine.hide();
        wcFrame.hide();
        wcFrame2.hide();
        
        //Sala
        rp1 = new roomDevices();
        rp2 = new roomOpenings();
        rpi = new roomPanelInfo();
        rz = new roomZones();
        rze = new roomZonesEdit();
        //Cozinha
        kd = new kitchenDevices();
        ko = new kitchenOpenings();
        kpi = new kitchenPanelInfo();
        kz = new kitchenZones();
        //Quarto
        bd = new bedroomDevices();
        bo = new bedroomOpenings();
        bpi = new bedroomPanelInfo();
        bz = new bedroomZones();
        //Quarto2
        bd2 = new bedroomDevices2();
        bo2 = new bedroomOpenings2();
        bpi2 = new bedroomPanelInfo2();
        bz2 = new bedroomZones2();
        //Wc
        wd = new wcDevices();
        wo = new wcOpenings();
        wpi = new wcPanelInfo();
        wz = new wcZones();
        
        //Wc2
        wd2 = new wcDevices2();
        wo2 = new wcOpenings2();
        wpi2 = new wcPanelInfo2();
        wz2 = new wcZones2();
       
        roomPanel.setLayout(layout);
        kitchenPanel.setLayout(layout);
        bedroomPanel.setLayout(layout);
        bedroomPanel2.setLayout(layout);
        wcPanel.setLayout(layout);
        wcPanel2.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx=0;
        c.gridy=0;
        roomPanel.add(rp1,c);
        roomPanel.add(rp2, c);
        roomPanel.add(rpi, c);
        roomPanel.add(rz, c);
        roomPanel.add(rze, c);
        
        kitchenPanel.add(kd, c);
        kitchenPanel.add(ko, c);
        kitchenPanel.add(kpi, c);
        kitchenPanel.add(kz, c);
        
        bedroomPanel.add(bd,c);
        bedroomPanel.add(bo,c);
        bedroomPanel.add(bpi,c);
        bedroomPanel.add(bz,c);
        
        bedroomPanel2.add(bd2,c);
        bedroomPanel2.add(bo2,c);
        bedroomPanel2.add(bpi2,c);
        bedroomPanel2.add(bz2,c);
        
        wcPanel.add(wd,c);
        wcPanel.add(wo,c);
        wcPanel.add(wpi,c);
        wcPanel.add(wz,c);
        
        wcPanel2.add(wd2,c);
        wcPanel2.add(wo2,c);
        wcPanel2.add(wpi2,c);
        wcPanel2.add(wz2,c);
        
        rp1.setVisible(false);
        rp2.setVisible(false);
        rpi.setVisible(true);
        rz.setVisible(false);
        rze.setVisible(false);
        
        kd.setVisible(false);
        ko.setVisible(false);
        kpi.setVisible(true);
        kz.setVisible(false);
        
        bd.setVisible(false);
        bo.setVisible(false);
        bpi.setVisible(true);
        bz.setVisible(false);
        
        bd2.setVisible(false);
        bo2.setVisible(false);
        bpi2.setVisible(true);
        bz2.setVisible(false);
        
        wd.setVisible(false);
        wo.setVisible(false);
        wpi.setVisible(true);
        wz.setVisible(false);
        
        wd2.setVisible(false);
        wo2.setVisible(false);
        wpi2.setVisible(true);
        wz2.setVisible(false);
        
        System.out.println(userlogged.getProfile());
        if (userlogged.getProfile().equals("ADMIN")) {
            userButton.setText("Administrador: " +  userlogged.getName());
        }else if(userlogged.getProfile().equals("USER")){
            userButton.setText("Utilizador: " + userlogged.getName());
        }else{
            userButton.setText("Convidado: " + userlogged.getName());
            userButtonEdit.setVisible(false);
        }
    }
    public boolean verifyTextField(){
        if (perfilComboBox.getSelectedItem().equals("Convidado")) {
            if (nameTextField.getText().equals("") || usernameTextField.getText().equals("")) {
                return true;
            }else{
                System.out.println("Falso");
                return false;
            }
        }else{
            if (nameTextField.getText().equals("") || usernameTextField.getText().equals("") || passwordTextField.equals("") || alarmTextField.getText().equals("")) {
                return true;
            }else{
                return false;
            }
        }
        
    }
    public void panelRefresh(){
        try {
            String[] array = userButton.getText().split(": ");
            String permission = array[0];
            String uname = array[1];
            DBConnection db = new DBConnection();
            DefaultTableModel model = new DefaultTableModel(new String[]{"Nome", "Nome utilizador", "Palavra-passe", "Alarme", "Perfil"}, 0);
            ResultSet rs = db.dbQuery("SELECT * FROM users");
            while(rs.next()){
                if (permission.equals("Administrador")) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String name = rs.getString("name");
                    String alarm = rs.getString("alarmselement_id");
                    String profile = rs.getString("profile");
                    model.addRow(new Object[]{name, username, password, alarm, profile});
                }else if(permission.equals("Utilizador")){
                    if (uname.equals(rs.getString("name"))) {
                        String username = rs.getString("username");
                        String password = rs.getString("password");
                        String name = rs.getString("name");
                        String alarm = rs.getString("alarmselement_id");
                        String profile = rs.getString("profile");
                        model.addRow(new Object[]{name, username, password, alarm, profile});
                    }else{
                        String username = rs.getString("username");
                        String password = "";
                        if (!rs.getString("password").equals("")) {
                            password = "*****";
                        }
                        String name = rs.getString("name");
                        String alarm = "";
                        if (!rs.getString("alarmselement_id").equals("")){
                            alarm = "*****";
                        }
                        String profile = rs.getString("profile");
                        model.addRow(new Object[]{name, username, password, alarm, profile});
                    }
                }
            }
            jTable1.setModel(model);        
        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        roomFrame = new javax.swing.JInternalFrame();
        roomPanel = new javax.swing.JLayeredPane();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu7 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenu11 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        userEdit = new javax.swing.JInternalFrame();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        perfilComboBox = new javax.swing.JComboBox<>();
        alarmTextField = new javax.swing.JTextField();
        passwordTextField = new javax.swing.JTextField();
        nameTextField = new javax.swing.JTextField();
        usernameTextField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        removeButton = new javax.swing.JButton();
        joinButton = new javax.swing.JButton();
        routine = new javax.swing.JInternalFrame();
        jButton3 = new javax.swing.JButton();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu9 = new javax.swing.JMenu();
        jMenu10 = new javax.swing.JMenu();
        kitchenFrame = new javax.swing.JInternalFrame();
        kitchenPanel = new javax.swing.JLayeredPane();
        jMenuBar4 = new javax.swing.JMenuBar();
        jMenu12 = new javax.swing.JMenu();
        jMenu13 = new javax.swing.JMenu();
        jMenu15 = new javax.swing.JMenu();
        jMenu16 = new javax.swing.JMenu();
        bedroomFrame = new javax.swing.JInternalFrame();
        bedroomPanel = new javax.swing.JLayeredPane();
        jMenuBar5 = new javax.swing.JMenuBar();
        jMenu14 = new javax.swing.JMenu();
        jMenu17 = new javax.swing.JMenu();
        jMenu18 = new javax.swing.JMenu();
        jMenu19 = new javax.swing.JMenu();
        bedroomFrame2 = new javax.swing.JInternalFrame();
        bedroomPanel2 = new javax.swing.JLayeredPane();
        jMenuBar6 = new javax.swing.JMenuBar();
        jMenu20 = new javax.swing.JMenu();
        jMenu21 = new javax.swing.JMenu();
        jMenu22 = new javax.swing.JMenu();
        jMenu23 = new javax.swing.JMenu();
        wcFrame = new javax.swing.JInternalFrame();
        wcPanel = new javax.swing.JLayeredPane();
        jMenuBar7 = new javax.swing.JMenuBar();
        jMenu24 = new javax.swing.JMenu();
        jMenu25 = new javax.swing.JMenu();
        jMenu26 = new javax.swing.JMenu();
        jMenu27 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        wcFrame2 = new javax.swing.JInternalFrame();
        wcPanel2 = new javax.swing.JLayeredPane();
        jMenuBar8 = new javax.swing.JMenuBar();
        jMenu28 = new javax.swing.JMenu();
        jMenu29 = new javax.swing.JMenu();
        jMenu30 = new javax.swing.JMenu();
        jMenu31 = new javax.swing.JMenu();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        userButton = new javax.swing.JMenu();
        userButtonEdit = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Casa Inteligente");

        roomFrame.setClosable(true);
        roomFrame.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        roomFrame.setTitle("Sala");
        roomFrame.setVisible(true);

        roomPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                roomPanelComponentShown(evt);
            }
        });

        javax.swing.GroupLayout roomPanelLayout = new javax.swing.GroupLayout(roomPanel);
        roomPanel.setLayout(roomPanelLayout);
        roomPanelLayout.setHorizontalGroup(
            roomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 396, Short.MAX_VALUE)
        );
        roomPanelLayout.setVerticalGroup(
            roomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 244, Short.MAX_VALUE)
        );

        jMenu7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/info.png"))); // NOI18N
        jMenu7.setText("Info");
        jMenu7.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jMenu7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu7MouseClicked(evt);
            }
        });
        jMenuBar2.add(jMenu7);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/light-bulb.png"))); // NOI18N
        jMenu5.setText("Dispositivos");
        jMenu5.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jMenu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu5MouseClicked(evt);
            }
        });
        jMenuBar2.add(jMenu5);

        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stage.png"))); // NOI18N
        jMenu6.setText("Aberturas");
        jMenu6.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jMenu6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu6MouseClicked(evt);
            }
        });
        jMenuBar2.add(jMenu6);

        jMenu11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/select-area-24.png"))); // NOI18N
        jMenu11.setText("Zonas");
        jMenu11.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        jMenuItem8.setText("Visualizar");
        jMenuItem8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem8MouseReleased(evt);
            }
        });
        jMenu11.add(jMenuItem8);

        jMenuItem9.setText("Editar");
        jMenuItem9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem9MouseReleased(evt);
            }
        });
        jMenu11.add(jMenuItem9);

        jMenuBar2.add(jMenu11);

        roomFrame.setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout roomFrameLayout = new javax.swing.GroupLayout(roomFrame.getContentPane());
        roomFrame.getContentPane().setLayout(roomFrameLayout);
        roomFrameLayout.setHorizontalGroup(
            roomFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roomFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roomPanel)
                .addContainerGap())
        );
        roomFrameLayout.setVerticalGroup(
            roomFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roomFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roomPanel)
                .addContainerGap())
        );

        userEdit.setClosable(true);
        userEdit.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        userEdit.setTitle("Utilizador");
        userEdit.setPreferredSize(new java.awt.Dimension(900, 453));
        userEdit.setVisible(true);

        jLabel6.setText("Nome: ");

        jLabel7.setText("Nome de utilizador: ");

        jLabel8.setText("Palavra-passe:");

        jLabel9.setText("Perfil:");

        jLabel10.setText("Alarme:");

        perfilComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Utilizador", "Convidado" }));
        perfilComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                perfilComboBoxItemStateChanged(evt);
            }
        });

        jButton1.setText("Limpar Campos");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton2.setText("Atualizar");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Nome de utilizador", "Palavra-passe", "Alarme", "Perfil"
            }
        ));
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
        }

        removeButton.setText("Remover");
        removeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                removeButtonMouseClicked(evt);
            }
        });

        joinButton.setText("Adicionar");
        joinButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                joinButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout userEditLayout = new javax.swing.GroupLayout(userEdit.getContentPane());
        userEdit.getContentPane().setLayout(userEditLayout);
        userEditLayout.setHorizontalGroup(
            userEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userEditLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(userEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(userEditLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(joinButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeButton)
                        .addGap(22, 22, 22)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(userEditLayout.createSequentialGroup()
                        .addGroup(userEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(userEditLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(alarmTextField))
                            .addGroup(userEditLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(userEditLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(userEditLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(userEditLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(perfilComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        userEditLayout.setVerticalGroup(
            userEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userEditLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(userEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(userEditLayout.createSequentialGroup()
                        .addGroup(userEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(userEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(userEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(userEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(perfilComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(userEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(alarmTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(userEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(removeButton)
                    .addComponent(joinButton))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        routine.setClosable(true);
        routine.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        routine.setTitle("Rotinas");
        routine.setVisible(true);

        jButton3.setText("jButton3");

        jMenu9.setText("Visualizar");
        jMenuBar3.add(jMenu9);

        jMenu10.setText("Editar");
        jMenuBar3.add(jMenu10);

        routine.setJMenuBar(jMenuBar3);

        javax.swing.GroupLayout routineLayout = new javax.swing.GroupLayout(routine.getContentPane());
        routine.getContentPane().setLayout(routineLayout);
        routineLayout.setHorizontalGroup(
            routineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(routineLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jButton3)
                .addContainerGap(92, Short.MAX_VALUE))
        );
        routineLayout.setVerticalGroup(
            routineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(routineLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        kitchenFrame.setClosable(true);
        kitchenFrame.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        kitchenFrame.setTitle("Cozinha");
        kitchenFrame.setVisible(true);

        javax.swing.GroupLayout kitchenPanelLayout = new javax.swing.GroupLayout(kitchenPanel);
        kitchenPanel.setLayout(kitchenPanelLayout);
        kitchenPanelLayout.setHorizontalGroup(
            kitchenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
        );
        kitchenPanelLayout.setVerticalGroup(
            kitchenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jMenu12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/info.png"))); // NOI18N
        jMenu12.setText("Info");
        jMenu12.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jMenu12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu12MouseClicked(evt);
            }
        });
        jMenuBar4.add(jMenu12);

        jMenu13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/light-bulb.png"))); // NOI18N
        jMenu13.setText("Dispositivos");
        jMenu13.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jMenu13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu13MouseClicked(evt);
            }
        });
        jMenuBar4.add(jMenu13);

        jMenu15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stage.png"))); // NOI18N
        jMenu15.setText("Aberturas");
        jMenu15.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jMenu15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu15MouseClicked(evt);
            }
        });
        jMenuBar4.add(jMenu15);

        jMenu16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/select-area-24.png"))); // NOI18N
        jMenu16.setText("Zonas");
        jMenu16.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jMenu16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu16MouseClicked(evt);
            }
        });
        jMenuBar4.add(jMenu16);

        kitchenFrame.setJMenuBar(jMenuBar4);

        javax.swing.GroupLayout kitchenFrameLayout = new javax.swing.GroupLayout(kitchenFrame.getContentPane());
        kitchenFrame.getContentPane().setLayout(kitchenFrameLayout);
        kitchenFrameLayout.setHorizontalGroup(
            kitchenFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kitchenFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(kitchenPanel)
                .addContainerGap())
        );
        kitchenFrameLayout.setVerticalGroup(
            kitchenFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kitchenFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(kitchenPanel)
                .addContainerGap())
        );

        bedroomFrame.setClosable(true);
        bedroomFrame.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        bedroomFrame.setTitle("Quarto 1");
        bedroomFrame.setVisible(true);

        javax.swing.GroupLayout bedroomPanelLayout = new javax.swing.GroupLayout(bedroomPanel);
        bedroomPanel.setLayout(bedroomPanelLayout);
        bedroomPanelLayout.setHorizontalGroup(
            bedroomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
        );
        bedroomPanelLayout.setVerticalGroup(
            bedroomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jMenu14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/info.png"))); // NOI18N
        jMenu14.setText("Info");
        jMenu14.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jMenu14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu14MouseClicked(evt);
            }
        });
        jMenuBar5.add(jMenu14);

        jMenu17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/light-bulb.png"))); // NOI18N
        jMenu17.setText("Dispositivos");
        jMenu17.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jMenu17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu17MouseClicked(evt);
            }
        });
        jMenuBar5.add(jMenu17);

        jMenu18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stage.png"))); // NOI18N
        jMenu18.setText("Aberturas");
        jMenu18.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jMenu18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu18MouseClicked(evt);
            }
        });
        jMenuBar5.add(jMenu18);

        jMenu19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/select-area-24.png"))); // NOI18N
        jMenu19.setText("Zonas");
        jMenu19.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jMenu19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu19MouseClicked(evt);
            }
        });
        jMenuBar5.add(jMenu19);

        bedroomFrame.setJMenuBar(jMenuBar5);

        javax.swing.GroupLayout bedroomFrameLayout = new javax.swing.GroupLayout(bedroomFrame.getContentPane());
        bedroomFrame.getContentPane().setLayout(bedroomFrameLayout);
        bedroomFrameLayout.setHorizontalGroup(
            bedroomFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bedroomFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bedroomPanel)
                .addContainerGap())
        );
        bedroomFrameLayout.setVerticalGroup(
            bedroomFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bedroomFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bedroomPanel)
                .addContainerGap())
        );

        bedroomFrame2.setClosable(true);
        bedroomFrame2.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        bedroomFrame2.setTitle("Quarto 2");
        bedroomFrame2.setVisible(true);

        javax.swing.GroupLayout bedroomPanel2Layout = new javax.swing.GroupLayout(bedroomPanel2);
        bedroomPanel2.setLayout(bedroomPanel2Layout);
        bedroomPanel2Layout.setHorizontalGroup(
            bedroomPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );
        bedroomPanel2Layout.setVerticalGroup(
            bedroomPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jMenu20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/info.png"))); // NOI18N
        jMenu20.setText("Info");
        jMenu20.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jMenu20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu20MouseClicked(evt);
            }
        });
        jMenuBar6.add(jMenu20);

        jMenu21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/light-bulb.png"))); // NOI18N
        jMenu21.setText("Dispositivos");
        jMenu21.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jMenu21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu21MouseClicked(evt);
            }
        });
        jMenuBar6.add(jMenu21);

        jMenu22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stage.png"))); // NOI18N
        jMenu22.setText("Aberturas");
        jMenu22.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jMenu22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu22MouseClicked(evt);
            }
        });
        jMenuBar6.add(jMenu22);

        jMenu23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/select-area-24.png"))); // NOI18N
        jMenu23.setText("Zonas");
        jMenu23.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jMenu23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu23MouseClicked(evt);
            }
        });
        jMenuBar6.add(jMenu23);

        bedroomFrame2.setJMenuBar(jMenuBar6);

        javax.swing.GroupLayout bedroomFrame2Layout = new javax.swing.GroupLayout(bedroomFrame2.getContentPane());
        bedroomFrame2.getContentPane().setLayout(bedroomFrame2Layout);
        bedroomFrame2Layout.setHorizontalGroup(
            bedroomFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bedroomFrame2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bedroomPanel2)
                .addContainerGap())
        );
        bedroomFrame2Layout.setVerticalGroup(
            bedroomFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bedroomFrame2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bedroomPanel2)
                .addContainerGap())
        );

        wcFrame.setClosable(true);
        wcFrame.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        wcFrame.setTitle("Casa de Banho 1");
        wcFrame.setVisible(true);

        javax.swing.GroupLayout wcPanelLayout = new javax.swing.GroupLayout(wcPanel);
        wcPanel.setLayout(wcPanelLayout);
        wcPanelLayout.setHorizontalGroup(
            wcPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 406, Short.MAX_VALUE)
        );
        wcPanelLayout.setVerticalGroup(
            wcPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 275, Short.MAX_VALUE)
        );

        jMenu24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/info.png"))); // NOI18N
        jMenu24.setText("Info");
        jMenu24.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jMenu24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu24MouseClicked(evt);
            }
        });
        jMenuBar7.add(jMenu24);

        jMenu25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/light-bulb.png"))); // NOI18N
        jMenu25.setText("Dispositivos");
        jMenu25.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jMenu25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu25MouseClicked(evt);
            }
        });
        jMenuBar7.add(jMenu25);

        jMenu26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stage.png"))); // NOI18N
        jMenu26.setText("Aberturas");
        jMenu26.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jMenu26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu26MouseClicked(evt);
            }
        });
        jMenuBar7.add(jMenu26);

        jMenu27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/select-area-24.png"))); // NOI18N
        jMenu27.setText("Zonas");
        jMenu27.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jMenu27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu27MouseClicked(evt);
            }
        });

        jMenuItem1.setText("Visualizar");
        jMenu27.add(jMenuItem1);

        jMenuItem7.setText("Editar");
        jMenu27.add(jMenuItem7);

        jMenuBar7.add(jMenu27);

        wcFrame.setJMenuBar(jMenuBar7);

        javax.swing.GroupLayout wcFrameLayout = new javax.swing.GroupLayout(wcFrame.getContentPane());
        wcFrame.getContentPane().setLayout(wcFrameLayout);
        wcFrameLayout.setHorizontalGroup(
            wcFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(wcPanel)
        );
        wcFrameLayout.setVerticalGroup(
            wcFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(wcPanel)
        );

        wcFrame2.setClosable(true);
        wcFrame2.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        wcFrame2.setTitle("Casa de Banho 2");
        wcFrame2.setVisible(true);

        javax.swing.GroupLayout wcPanel2Layout = new javax.swing.GroupLayout(wcPanel2);
        wcPanel2.setLayout(wcPanel2Layout);
        wcPanel2Layout.setHorizontalGroup(
            wcPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        wcPanel2Layout.setVerticalGroup(
            wcPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jMenu28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/info.png"))); // NOI18N
        jMenu28.setText("Info");
        jMenu28.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jMenu28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu28MouseClicked(evt);
            }
        });
        jMenuBar8.add(jMenu28);

        jMenu29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/light-bulb.png"))); // NOI18N
        jMenu29.setText("Dispositivos");
        jMenu29.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jMenu29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu29MouseClicked(evt);
            }
        });
        jMenuBar8.add(jMenu29);

        jMenu30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stage.png"))); // NOI18N
        jMenu30.setText("Aberturas");
        jMenu30.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jMenu30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu30MouseClicked(evt);
            }
        });
        jMenuBar8.add(jMenu30);

        jMenu31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/select-area-24.png"))); // NOI18N
        jMenu31.setText("Zonas");
        jMenu31.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jMenu31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu31MouseClicked(evt);
            }
        });
        jMenuBar8.add(jMenu31);

        wcFrame2.setJMenuBar(jMenuBar8);

        javax.swing.GroupLayout wcFrame2Layout = new javax.swing.GroupLayout(wcFrame2.getContentPane());
        wcFrame2.getContentPane().setLayout(wcFrame2Layout);
        wcFrame2Layout.setHorizontalGroup(
            wcFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wcFrame2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(wcPanel2)
                .addContainerGap())
        );
        wcFrame2Layout.setVerticalGroup(
            wcFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wcFrame2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(wcPanel2)
                .addContainerGap())
        );

        jDesktopPane1.setLayer(roomFrame, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(userEdit, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(routine, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(kitchenFrame, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(bedroomFrame, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(bedroomFrame2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(wcFrame, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(wcFrame2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roomFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(wcFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(wcFrame2)
                    .addComponent(kitchenFrame))
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bedroomFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bedroomFrame2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(routine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(userEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 778, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(kitchenFrame, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roomFrame, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bedroomFrame, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bedroomFrame2, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(wcFrame)
                    .addComponent(wcFrame2)
                    .addComponent(userEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(routine))
                .addGap(0, 21, Short.MAX_VALUE))
        );

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sofa.png"))); // NOI18N
        jMenu1.setText("Salas");
        jMenu1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/frying-pan.png"))); // NOI18N
        jMenu2.setText("Cozinhas");
        jMenu2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bedroom.png"))); // NOI18N
        jMenu3.setText("Quartos");
        jMenu3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jMenuItem3.setText("Quarto 1");
        jMenuItem3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem3MouseReleased(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuItem4.setText("Quarto 2");
        jMenuItem4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem4MouseReleased(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuBar1.add(jMenu3);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/toilet.png"))); // NOI18N
        jMenu4.setText("Casas de Banho");
        jMenu4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenu4MouseReleased(evt);
            }
        });

        jMenuItem5.setText("Casa de Banho 1");
        jMenuItem5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem5MouseReleased(evt);
            }
        });
        jMenu4.add(jMenuItem5);

        jMenuItem6.setText("Casa de Banho 2");
        jMenuItem6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem6MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem6MouseReleased(evt);
            }
        });
        jMenu4.add(jMenuItem6);

        jMenuBar1.add(jMenu4);

        jMenu8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/wall-calendar.png"))); // NOI18N
        jMenu8.setText("Rotinas");
        jMenu8.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jMenu8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu8MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu8);

        userButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user.png"))); // NOI18N
        userButton.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        userButton.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        userButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        userButtonEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit.png"))); // NOI18N
        userButtonEdit.setText("Editar");
        userButtonEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                userButtonEditMouseReleased(evt);
            }
        });
        userButton.add(userButtonEdit);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        jMenuItem2.setText("Sair");
        jMenuItem2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem2MouseReleased(evt);
            }
        });
        userButton.add(jMenuItem2);

        jMenuBar1.add(userButton);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        roomFrame.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (roomFrame.isVisible()) {
                    if (rpi.isVisible()) {
                        rpi.setTemp();
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (roomFrame.isVisible()) {
                    if (rpi.isVisible()) {
                        rpi.setHum();
                    }
                }
            }
        }).start();
        
    }//GEN-LAST:event_jMenu1MouseClicked

    private void jMenu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu5MouseClicked
        rp1.setVisible(true);
        rp2.setVisible(false);
        rpi.setVisible(false);
        rz.setVisible(false);
        rze.setVisible(false);
    }//GEN-LAST:event_jMenu5MouseClicked

    private void jMenu6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu6MouseClicked
        rp1.setVisible(false);
        rp2.setVisible(true);
        rpi.setVisible(false);
        rz.setVisible(false);
        rze.setVisible(false);
    }//GEN-LAST:event_jMenu6MouseClicked

    private void jMenuItem2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem2MouseReleased
        String[] buttons = { "Sim", "Não" };
        int rc = JOptionPane.showOptionDialog(null, "Pretende sair desta conta?", "Sair", JOptionPane.DEFAULT_OPTION, 3, null, buttons, buttons[0]);
        System.out.println(rc);
        if (rc == JOptionPane.YES_OPTION) {
            this.setVisible(false);
            new login().setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem2MouseReleased

    private void userButtonEditMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userButtonEditMouseReleased
        String[] array = userButton.getText().split(": ");
        String permission = array[0];
        if (permission.equals("Utilizador")) {
            joinButton.setEnabled(false);
            removeButton.setEnabled(false);
            joinButton.setSelected(false);
            panelRefresh();
            userEdit.show();
        }else{
            panelRefresh();
            userEdit.show();
        }
    }//GEN-LAST:event_userButtonEditMouseReleased

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        if ((nameTextField.getText().equals("")) && (usernameTextField.getText().equals("")) && (passwordTextField.getText().equals("")) && (alarmTextField.getText().equals(""))) {
            if (jTable1.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Campos já estão vazios!");
            }else{
                jTable1.clearSelection();
                usernameTextField.setEnabled(true);
            }
        }else{
           nameTextField.setText("");
           usernameTextField.setText("");
           passwordTextField.setText("");
           alarmTextField.setText("");
           perfilComboBox.setSelectedItem("Administrador"); 
           jTable1.clearSelection();
           usernameTextField.setEnabled(true);
        }
        
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        if (verifyTextField() == true) {
            System.out.println("");
            JOptionPane.showMessageDialog(null, "Campos vazios!");
        }else{
            String profile;
            if (perfilComboBox.getSelectedItem().toString().equals("Administrador")) {
                profile = "ADMIN";
            }else if(perfilComboBox.getSelectedItem().toString().equals("Utilizador")){
                profile = "USER";
            }else{
                profile = "GUEST";
            }
            DBConnection db = new DBConnection();
            try {
                db.dbUpdate("update users set username = '" + usernameTextField.getText() + "', password = '" + passwordTextField.getText() + "', name = '" + nameTextField.getText() + "', alarmselement_id = " + alarmTextField.getText() + ", profile = '" + profile + "' where username = '" + usernameTextField.getText() + "'");
                panelRefresh();
                JOptionPane.showMessageDialog(null, "Utilizador atualizado!");
            } catch (SQLException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }//GEN-LAST:event_jButton2MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        nameTextField.setText("");
        usernameTextField.setText("");
        passwordTextField.setText("");
        alarmTextField.setText("");
        perfilComboBox.setSelectedItem("Administrador");
        usernameTextField.setEnabled(false);
        try {
            int index = jTable1.getSelectedRow();
            System.out.println(index);
            DBConnection db = new DBConnection();
            String[] array = userButton.getText().split(": ");
            String permission = array[0];
            String uname = array[1];
            String profile;
            ResultSet rs = db.dbQuery("SELECT * FROM users OFFSET " + index);
            rs.next();
            if (permission.equals("Administrador")) {
                usernameTextField.setText(rs.getString("username"));
                passwordTextField.setText(rs.getString("password"));
                nameTextField.setText(rs.getString("name"));
                alarmTextField.setText(rs.getString("alarmselement_id"));
                if (rs.getString("profile").equals("ADMIN")) {
                    profile = "Administrador";
                }else if(rs.getString("profile").equals("USER")){
                    profile = "Utilizador";
                }else{
                    profile = "Convidado";
                }
                perfilComboBox.setSelectedItem(profile);
            }else if (uname.equals(rs.getString("name"))){
                usernameTextField.setText(rs.getString("username"));
                passwordTextField.setText(rs.getString("password"));
                nameTextField.setText(rs.getString("name"));
                alarmTextField.setText(rs.getString("alarmselement_id"));
                if (rs.getString("profile").equals("ADMIN")) {
                    profile = "Administrador";
                }else if(rs.getString("profile").equals("USER")){
                    profile = "Utilizador";
                }else{
                    profile = "Convidado";
                }
                perfilComboBox.setSelectedItem(profile);
            }
        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void removeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeButtonMouseClicked
        String[] array = userButton.getText().split(": ");
        String permission = array[0];
        if (permission.equals("Administrador")) {
            if (verifyTextField() == true) {
            JOptionPane.showMessageDialog(null, "Campos vazios!");
            }else{
                DBConnection db = new DBConnection();
                try {
                    db.dbDelete("Delete from users where username = '" + usernameTextField.getText() + "'");
                    panelRefresh();
                    nameTextField.setText("");
                    usernameTextField.setText("");
                    passwordTextField.setText("");
                    alarmTextField.setText("");
                    perfilComboBox.setSelectedItem("Administrador"); 
                    jTable1.clearSelection();
                    JOptionPane.showMessageDialog(null, "Utilizador removido!");
                } catch (SQLException ex) {
                    Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        
    }//GEN-LAST:event_removeButtonMouseClicked

    private void joinButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_joinButtonMouseClicked
        String[] array = userButton.getText().split(": ");
        String permission = array[0];
        if (permission.equals("Administrador")) {
            if (verifyTextField() == true) {
            JOptionPane.showMessageDialog(null, "Campos vazios!");
            }else if(verifyTextField() == false){
               String profile;
                if (perfilComboBox.getSelectedItem().toString().equals("Administrador")) {
                profile = "ADMIN";
                try {
                    DBConnection db = new DBConnection();
                    db.dbJoin("INSERT INTO USERS VALUES ('" + usernameTextField.getText() + "', '" + passwordTextField.getText() + "', '" + nameTextField.getText() + "', " + alarmTextField.getText() +", '" + profile + "')");
                    panelRefresh();
                    JOptionPane.showMessageDialog(null, "Utilizador adicionado!");
                } catch (SQLException ex) {
                    Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                }
                }else if(perfilComboBox.getSelectedItem().toString().equals("Utilizador")){
                profile = "USER";
                try {
                    DBConnection db = new DBConnection();
                    db.dbJoin("INSERT INTO USERS VALUES ('" + usernameTextField.getText() + "', '" + passwordTextField.getText() + "', '" + nameTextField.getText() + "', " + alarmTextField.getText() +", '" + profile + "')");
                    panelRefresh();
                    JOptionPane.showMessageDialog(null, "Utilizador adicionado!");
                } catch (SQLException ex) {
                    Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                }
                }else{
                profile = "GUEST";
                try {
                    DBConnection db = new DBConnection();
                    db.dbJoin("INSERT INTO USERS VALUES ('" + usernameTextField.getText() + "', '" + passwordTextField.getText() + "', '" + nameTextField.getText() + "',1, '" + profile + "')");
                    panelRefresh();
                    JOptionPane.showMessageDialog(null, "Utilizador adicionado!");
                } catch (SQLException ex) {
                    Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
                
            }
            nameTextField.setText("");
            usernameTextField.setText("");
            passwordTextField.setText("");
            alarmTextField.setText("");
            perfilComboBox.setSelectedItem("Administrador"); 
            jTable1.clearSelection();
        }
        
        
    }//GEN-LAST:event_joinButtonMouseClicked

    private void perfilComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_perfilComboBoxItemStateChanged
        if (perfilComboBox.getSelectedItem().equals("Convidado")) {
            passwordTextField.setText("");
            alarmTextField.setEnabled(false);
            passwordTextField.setEnabled(false);
        }else{
            passwordTextField.setEnabled(true);
            alarmTextField.setEnabled(true);
        }
    }//GEN-LAST:event_perfilComboBoxItemStateChanged

    private void jMenu7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu7MouseClicked
        rpi.setVisible(true);
        rp1.setVisible(false);
        rp2.setVisible(false);
        rz.setVisible(false);
        rze.setVisible(false);
    }//GEN-LAST:event_jMenu7MouseClicked

    private void jMenu8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu8MouseClicked
        routine.show();
    }//GEN-LAST:event_jMenu8MouseClicked

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        kitchenFrame.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (roomFrame.isVisible()) {
                    if (kpi.isVisible()) {
                        kpi.setTemp();
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (roomFrame.isVisible()) {
                    if (kpi.isVisible()) {
                        kpi.setHum();
                    }
                }
            }
        }).start();
    }//GEN-LAST:event_jMenu2MouseClicked

    private void jMenu12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu12MouseClicked
        kd.setVisible(false);
        ko.setVisible(false);
        kpi.setVisible(true);
        kz.setVisible(false);
    }//GEN-LAST:event_jMenu12MouseClicked

    private void jMenu13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu13MouseClicked
        kd.setVisible(true);
        ko.setVisible(false);
        kpi.setVisible(false);
        kz.setVisible(false);
    }//GEN-LAST:event_jMenu13MouseClicked

    private void jMenu15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu15MouseClicked
        kd.setVisible(false);
        ko.setVisible(true);
        kpi.setVisible(false);
        kz.setVisible(false);
    }//GEN-LAST:event_jMenu15MouseClicked

    private void jMenu16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu16MouseClicked
        kd.setVisible(false);
        ko.setVisible(false);
        kpi.setVisible(false);
        kz.setVisible(true);
    }//GEN-LAST:event_jMenu16MouseClicked

    private void jMenuItem3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem3MouseReleased
        bedroomFrame.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (bedroomFrame.isVisible()) {
                    if (bpi.isVisible()) {
                        bpi.setTemp();
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (bedroomFrame.isVisible()) {
                    if (bpi.isVisible()) {
                        bpi.setHum();
                    }
                }
            }
        }).start();
    }//GEN-LAST:event_jMenuItem3MouseReleased

    private void jMenu14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu14MouseClicked
        bd.setVisible(false);
        bo.setVisible(false);
        bpi.setVisible(true);
        bz.setVisible(false);
    }//GEN-LAST:event_jMenu14MouseClicked

    private void jMenu17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu17MouseClicked
        bd.setVisible(true);
        bo.setVisible(false);
        bpi.setVisible(false);
        bz.setVisible(false);
    }//GEN-LAST:event_jMenu17MouseClicked

    private void jMenu18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu18MouseClicked
        bd.setVisible(false);
        bo.setVisible(true);
        bpi.setVisible(false);
        bz.setVisible(false);
    }//GEN-LAST:event_jMenu18MouseClicked

    private void jMenu19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu19MouseClicked
        bd.setVisible(false);
        bo.setVisible(false);
        bpi.setVisible(false);
        bz.setVisible(true);
    }//GEN-LAST:event_jMenu19MouseClicked

    private void jMenuItem4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem4MouseReleased
        bedroomFrame2.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (bedroomFrame2.isVisible()) {
                    if (bpi2.isVisible()) {
                        bpi2.setTemp();
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (bedroomFrame2.isVisible()) {
                    if (bpi2.isVisible()) {
                        bpi2.setHum();
                    }
                }
            }
        }).start();
    }//GEN-LAST:event_jMenuItem4MouseReleased

    private void jMenu20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu20MouseClicked
        bd2.setVisible(false);
        bo2.setVisible(false);
        bpi2.setVisible(true);
        bz2.setVisible(false);
    }//GEN-LAST:event_jMenu20MouseClicked

    private void jMenu21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu21MouseClicked
        bd2.setVisible(true);
        bo2.setVisible(false);
        bpi2.setVisible(false);
        bz2.setVisible(false);
    }//GEN-LAST:event_jMenu21MouseClicked

    private void jMenu22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu22MouseClicked
        bd2.setVisible(false);
        bo2.setVisible(true);
        bpi2.setVisible(false);
        bz2.setVisible(false);
    }//GEN-LAST:event_jMenu22MouseClicked

    private void jMenu23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu23MouseClicked
        bd2.setVisible(false);
        bo2.setVisible(false);
        bpi2.setVisible(false);
        bz2.setVisible(true);
    }//GEN-LAST:event_jMenu23MouseClicked

    private void jMenuItem6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem6MouseClicked
        
    }//GEN-LAST:event_jMenuItem6MouseClicked

    private void jMenu24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu24MouseClicked
        wd.setVisible(false);
        wo.setVisible(false);
        wpi.setVisible(true);
        wz.setVisible(false);
    }//GEN-LAST:event_jMenu24MouseClicked

    private void jMenu25MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu25MouseClicked
        wd.setVisible(true);
        wo.setVisible(false);
        wpi.setVisible(false);
        wz.setVisible(false);
    }//GEN-LAST:event_jMenu25MouseClicked

    private void jMenu26MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu26MouseClicked
        wd.setVisible(false);
        wo.setVisible(true);
        wpi.setVisible(false);
        wz.setVisible(false);
    }//GEN-LAST:event_jMenu26MouseClicked

    private void jMenu27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu27MouseClicked
        wd.setVisible(false);
        wo.setVisible(false);
        wpi.setVisible(false);
        wz.setVisible(true);
    }//GEN-LAST:event_jMenu27MouseClicked

    private void jMenu4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseReleased
        
    }//GEN-LAST:event_jMenu4MouseReleased

    private void jMenuItem5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem5MouseReleased
        wcFrame.show();
    }//GEN-LAST:event_jMenuItem5MouseReleased

    private void jMenuItem6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem6MouseReleased
        wcFrame2.show();
    }//GEN-LAST:event_jMenuItem6MouseReleased

    private void jMenu28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu28MouseClicked
        wd2.setVisible(false);
        wo2.setVisible(false);
        wpi2.setVisible(true);
        wz2.setVisible(false);
    }//GEN-LAST:event_jMenu28MouseClicked

    private void jMenu29MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu29MouseClicked
        wd2.setVisible(true);
        wo2.setVisible(false);
        wpi2.setVisible(false);
        wz2.setVisible(false);
    }//GEN-LAST:event_jMenu29MouseClicked

    private void jMenu30MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu30MouseClicked
        wd2.setVisible(false);
        wo2.setVisible(true);
        wpi2.setVisible(false);
        wz2.setVisible(false);
    }//GEN-LAST:event_jMenu30MouseClicked

    private void jMenu31MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu31MouseClicked
        wd2.setVisible(false);
        wo2.setVisible(false);
        wpi2.setVisible(false);
        wz2.setVisible(true);
    }//GEN-LAST:event_jMenu31MouseClicked

    private void jMenuItem8MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem8MouseReleased
        rze.setVisible(false);
        rpi.setVisible(false);
        rp1.setVisible(false);
        rp2.setVisible(false);
        rz.setVisible(true);
    }//GEN-LAST:event_jMenuItem8MouseReleased

    private void jMenuItem9MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem9MouseReleased
        rze.setVisible(true);
        rpi.setVisible(false);
        rp1.setVisible(false);
        rp2.setVisible(false);
        rz.setVisible(false);
    }//GEN-LAST:event_jMenuItem9MouseReleased

    private void roomPanelComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_roomPanelComponentShown
        
    }//GEN-LAST:event_roomPanelComponentShown

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new main().setVisible(false);
//                new login().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField alarmTextField;
    private javax.swing.JInternalFrame bedroomFrame;
    private javax.swing.JInternalFrame bedroomFrame2;
    private javax.swing.JLayeredPane bedroomPanel;
    private javax.swing.JLayeredPane bedroomPanel2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu13;
    private javax.swing.JMenu jMenu14;
    private javax.swing.JMenu jMenu15;
    private javax.swing.JMenu jMenu16;
    private javax.swing.JMenu jMenu17;
    private javax.swing.JMenu jMenu18;
    private javax.swing.JMenu jMenu19;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu20;
    private javax.swing.JMenu jMenu21;
    private javax.swing.JMenu jMenu22;
    private javax.swing.JMenu jMenu23;
    private javax.swing.JMenu jMenu24;
    private javax.swing.JMenu jMenu25;
    private javax.swing.JMenu jMenu26;
    private javax.swing.JMenu jMenu27;
    private javax.swing.JMenu jMenu28;
    private javax.swing.JMenu jMenu29;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu30;
    private javax.swing.JMenu jMenu31;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JMenuBar jMenuBar4;
    private javax.swing.JMenuBar jMenuBar5;
    private javax.swing.JMenuBar jMenuBar6;
    private javax.swing.JMenuBar jMenuBar7;
    private javax.swing.JMenuBar jMenuBar8;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton joinButton;
    private javax.swing.JInternalFrame kitchenFrame;
    private javax.swing.JLayeredPane kitchenPanel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JTextField passwordTextField;
    private javax.swing.JComboBox<String> perfilComboBox;
    private javax.swing.JButton removeButton;
    private javax.swing.JInternalFrame roomFrame;
    private javax.swing.JLayeredPane roomPanel;
    private javax.swing.JInternalFrame routine;
    private javax.swing.JMenu userButton;
    private javax.swing.JMenuItem userButtonEdit;
    private javax.swing.JInternalFrame userEdit;
    private javax.swing.JTextField usernameTextField;
    private javax.swing.JInternalFrame wcFrame;
    private javax.swing.JInternalFrame wcFrame2;
    private javax.swing.JLayeredPane wcPanel;
    private javax.swing.JLayeredPane wcPanel2;
    // End of variables declaration//GEN-END:variables
}
