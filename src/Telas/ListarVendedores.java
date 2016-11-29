/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;

import br.aeso.exercicio.cliente.ClienteNaoExncontradoException;
import br.aeso.exercicio.fachada.Fachada;
import br.aeso.exercicio.vendedor.Vendedor;
import br.aeso.exercicio.vendedor.VendedorNaoEncontradoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lab01
 */
public class ListarVendedores extends javax.swing.JInternalFrame {

    /**
     * Creates new form ListarVendedors2
     */
    public ListarVendedores() {
        initComponents();
        this.montarTabela();
    }
    
    public final void montarTabela(){
        DefaultTableModel model;
            model = (DefaultTableModel) this.TabelaVendedores.getModel();
            Fachada fachada = new Fachada();
            ArrayList<Vendedor> lista;
            try {
                lista = fachada.listarVendedor();
                this.Vendedores = lista;
                int i = 0;
                for(Vendedor Vendedor : lista){
                    String email = "";
                    String telefone = "";
                    if(Vendedor.getEmails().size() > 0){
                            email = Vendedor.getEmails().get(0).getEmail(); 
                    }
                    if(Vendedor.getTelefones().size() > 0){
                            telefone = Vendedor.getTelefones().get(0).getTelefone();
                    }
                    model.addRow(new Object[]{Vendedor.getCodigo(), Vendedor.getNome(), email, Vendedor.getRua(), Vendedor.getNumero(), Vendedor.getComplemento(), Vendedor.getBairro(), Vendedor.getCidade(), Vendedor.getCEP(), telefone});
                    i++;
                }
                TabelaVendedores.setVisible(true);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ListarVendedores.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ListarVendedores.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ListarVendedores.class.getName()).log(Level.SEVERE, null, ex);
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
        TabelaVendedores = new javax.swing.JTable();
        editar = new javax.swing.JButton();
        excluir = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        TabelaVendedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nome", "Email", "Rua", "Numero", "Complemento", "Bairro", "Cidade", "CEP", "Telefone"
            }
        ));
        jScrollPane1.setViewportView(TabelaVendedores);

        editar.setText("Editar");
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });

        excluir.setText("Excluir");
        excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 923, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(editar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77)
                .addComponent(excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(327, 327, 327))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(editar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
   
    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
        int row = TabelaVendedores.getSelectedRow();
        int codigo = (int) TabelaVendedores.getValueAt(row, 0);
        Vendedor choosen = null;
        for(Vendedor vendedor : this.Vendedores){
            if(codigo == vendedor.getCodigo()){
                choosen = vendedor;
                break;
            }
        }
        FormularioVendedor form = new FormularioVendedor(choosen);
        Principal.setAreaTrabalhoPrincipal(form);
        form.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_editarActionPerformed

    private void excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excluirActionPerformed
        Fachada fachada = new Fachada();
        int row = TabelaVendedores.getSelectedRow();
        int codigo = (int) TabelaVendedores.getValueAt(row, 0);
        try {
            fachada.removerVendedor(codigo);
            // TODO add your handling code here:
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ListarVendedores.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ListarVendedores.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ListarVendedores.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClienteNaoExncontradoException ex) {
            Logger.getLogger(ListarVendedores.class.getName()).log(Level.SEVERE, null, ex);
        } catch (VendedorNaoEncontradoException ex) {
            Logger.getLogger(ListarVendedores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_excluirActionPerformed

    private ArrayList<Vendedor> Vendedores;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TabelaVendedores;
    private javax.swing.JButton editar;
    private javax.swing.JButton excluir;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
