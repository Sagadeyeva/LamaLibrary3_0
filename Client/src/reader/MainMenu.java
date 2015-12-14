package reader;

import client.Client;
import request.Book;
import request.BookText;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

/**
 * @author Светлана
 */
class MainMenu extends javax.swing.JFrame {

    /**
     * Creates new form MainMenu
     */
    private final Client client;
    private javax.swing.JTextPane jTextPane1;
    private java.awt.List list1;

    private MainMenu(Client client) {
        this.client = client;
        initComponents();


    }

    /**
     * @param client the Client instance
     */
    public static void show(Client client) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new MainMenu(client).setVisible(true));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JLabel jLabel1 = new JLabel();
        JButton jButton1 = new JButton();
        JScrollPane jScrollPane1 = new JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        JButton jButton2 = new JButton();
        list1 = new java.awt.List();
        JButton jButton3 = new JButton();
        JButton jButton4 = new JButton();
        JButton jButton5 = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("MY READER");

        jButton1.setText("Exit");
        jButton1.addActionListener(evt -> jButton1ActionPerformed(evt));

        jScrollPane1.setViewportView(jTextPane1);

        jButton2.setText("SEARCH!");
        jButton2.addActionListener(evt -> jButton2ActionPerformed(evt));

        jButton3.setText("Read");
        jButton3.addActionListener(evt -> jButton3ActionPerformed(evt));

        jButton4.setText("Add book");
        jButton4.addActionListener(evt -> jButton4ActionPerformed(evt));

        jButton5.setText("Delete book");
        jButton5.addActionListener(evt -> jButton5ActionPerformed(evt));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2)
                                .addGap(33, 33, 33))
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jButton1)
                                                .addGap(54, 54, 54)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel1)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(58, 58, 58)
                                                                .addComponent(list1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(23, 23, 23)
                                                                .addComponent(jButton4)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jButton5)
                                                        .addComponent(jButton3))))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jButton3)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jLabel1))
                                                        .addComponent(jButton1))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton2))
                                                .addGap(23, 23, 23)
                                                .addComponent(list1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton4)
                                        .addComponent(jButton5))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.exit(0);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        String searchdata = jTextPane1.getText();
        if ("".equals(searchdata))
            return;
        client.writeString(searchdata);


        ArrayList<Book> books = client.readBookData();


        list1.removeAll();
        if (!books.isEmpty()) {


            for (Book book : books) {
                list1.add(book.getAuthor() + " | " + book.getName());
            }

        } else {
            //  JOptionPane.showMessageDialog(this, "not founded");
            list1.add("Nothing founded");
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        String item = this.list1.getSelectedItem();
        if (item == null)
            return;
        int pos = item.indexOf("|");
        String author = item.substring(0, pos - 1);
        String name = item.substring(pos + 2, item.length());
        Book book = new Book(author, name);
        client.textRequest(book);
        String bookText = client.receiveText().getText();
        this.dispose();
        Reader.show(client, bookText);
    }//GEN-LAST:event_jButton3ActionPerformed

    public void addBook(String author, String bookName, File file) {

        Book book = new Book(author, bookName);
        ArrayList<String> text = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(file), "UTF-8"));

            String line;
            while ((line = reader.readLine()) != null) {
                text.add(line);
            }
        } catch (IOException ex) {
            System.err.println("Cant read file " + ex);
        }
        BookText bText = new BookText(text);

        client.sendBook(book, bText);


    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Adding add = new Adding(MainMenu.this);
        add.show();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String toDel = list1.getSelectedItem();
        if (toDel == null) {
            return;
        }
        int pos = toDel.indexOf("|");
        String author = toDel.substring(0, pos - 1);
        String name = toDel.substring(pos + 2, toDel.length());
        Book book = new Book(author, name);
        client.bookDelete(book);
        list1.remove(toDel);
    }//GEN-LAST:event_jButton5ActionPerformed
    // End of variables declaration//GEN-END:variables


}