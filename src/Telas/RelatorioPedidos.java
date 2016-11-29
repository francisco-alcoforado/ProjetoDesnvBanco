/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;

import br.aeso.exercicio.cliente.ClienteNaoExncontradoException;
import br.aeso.exercicio.excel.ManipularExcel;
import br.aeso.exercicio.fachada.Fachada;
import br.aeso.exercicio.pedido.Pedido;
import br.aeso.exercicio.pedido.PedidoNaoEncontradoException;
import br.aeso.exercicio.util.ComboItem;
import br.aeso.exercicio.vendedor.VendedorNaoEncontradoException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author lab01
 */
public class RelatorioPedidos extends javax.swing.JInternalFrame {

    /**
     * Creates new form RelatorioPedidos
     */
    public RelatorioPedidos() {
        this.state = 0;
        initComponents();
        this.montarCombo();
    }
    
    public void montarCombo(){
        DefaultComboBoxModel model = (DefaultComboBoxModel) this.agrupamento.getModel();
        model.removeAllElements();
        ComboItem[] itens;
        itens = new ComboItem[4];
        ComboItem itemMes = new ComboItem();
        itemMes.setLabel("Mes");
        itemMes.setValue(1);
        
        ComboItem itemAno = new ComboItem();
        itemAno.setLabel("Ano");
        itemAno.setValue(2);
        
        ComboItem itemCliente = new ComboItem();
        itemCliente.setLabel("Cliente");
        itemCliente.setValue(3);
        
        ComboItem itemVendedor = new ComboItem();
        itemVendedor.setLabel("Vendedor");
        itemVendedor.setValue(4);
        
        model.removeAllElements();
        
        model.addElement(itemMes);
        model.addElement(itemAno);
        model.addElement(itemCliente);
        model.addElement(itemVendedor);
    }
    public void clearTabela(DefaultTableModel model){
        int quantidade = model.getRowCount();
        System.out.println("Tamanho: " + quantidade);
        for(int i = (quantidade - 1); i >= 0; i--){
            model.removeRow(i);
        }
    }
    public void montarTabela(int tipoAgrupamento){
        String[] meses = new String[]{"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        DefaultTableModel model = (DefaultTableModel) tabelaRelatorio.getModel();
        clearTabela(model);
        Fachada fachada = new Fachada();
        int diffAnt = 0;
        double total = 0;
        double totalPartes = 0;
        int i = 1;
        if(tipoAgrupamento == 1){
            try {
                ArrayList<Pedido> valores = fachada.relatorioPedido("Data_Pedido");
                for(Pedido pedido : valores){
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(pedido.getData_pedido());

                    int diff = cal.get(Calendar.MONTH);
                    if(diff != diffAnt){
                        if(diffAnt != 0){
                            model.addRow(new Object[]{"","","","","Total Mes",totalPartes});
                            totalPartes = 0;
                            i++;
                        }
                        model.addRow(new Object[] {meses[diff],"","","","",""});
                        diffAnt = diff;
                        i++;
                    }

                    model.addRow(new Object[]{"",pedido.getCodigo(), pedido.getCliente().getNome(),pedido.getVendedor().getNome(), pedido.getData_pedido(), pedido.getValor()});
                    total += pedido.getValor();
                    totalPartes += pedido.getValor();
                    i++;
                }
                model.addRow(new Object[]{"","","","","Total Mes",totalPartes});
                model.addRow(new Object[]{"","","","","Total",total});
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (PedidoNaoEncontradoException ex) {
                Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClienteNaoExncontradoException ex) {
                Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (VendedorNaoEncontradoException ex) {
                Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(tipoAgrupamento == 2){
            try {
                ArrayList<Pedido> valores = fachada.relatorioPedido("Data_Pedido");
                for(Pedido pedido : valores){
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(pedido.getData_pedido());

                    int diff = cal.get(Calendar.YEAR);
                    if(diff != diffAnt){
                        if(diffAnt != 0){
                            model.addRow(new Object[]{"","","","","Total Ano",totalPartes});
                            totalPartes = 0;
                            i++;
                        }
                        model.addRow(new Object[] {diff,"","","","",""});
                        diffAnt = diff;
                        i++;
                    }

                    model.addRow(new Object[]{"",pedido.getCodigo(), pedido.getCliente().getNome(),pedido.getVendedor().getNome(), pedido.getData_pedido(), pedido.getValor()});
                    total += pedido.getValor();
                    totalPartes += pedido.getValor();
                    i++;
                }
                model.addRow(new Object[]{"","","","","Total Ano",totalPartes});
                model.addRow(new Object[]{"","","","","Total",total});
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (PedidoNaoEncontradoException ex) {
                Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClienteNaoExncontradoException ex) {
                Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (VendedorNaoEncontradoException ex) {
                Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(tipoAgrupamento == 3){
            try {
                ArrayList<Pedido> valores = fachada.relatorioPedido("Codigo_Cliente");
                for(Pedido pedido : valores){
                    int diff = pedido.getCliente().getCodigo();
                    if(diff != diffAnt){
                        if(diffAnt != 0){
                            model.addRow(new Object[]{"","","","","Total Cliente",totalPartes});
                            totalPartes = 0;
                            i++;
                        }
                        model.addRow(new Object[] {diff + " - " + pedido.getCliente().getNome(),"","","","",""});
                        diffAnt = diff;
                        i++;
                    }
                    model.addRow(new Object[]{"",pedido.getCodigo(), pedido.getCliente().getNome(),pedido.getVendedor().getNome(), pedido.getData_pedido(), pedido.getValor()});
                    total += pedido.getValor();
                    totalPartes += pedido.getValor();
                    i++;
                }
                model.addRow(new Object[]{"","","","","Total Cliente",totalPartes});
                model.addRow(new Object[]{"","","","","Total",total});
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (PedidoNaoEncontradoException ex) {
                Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClienteNaoExncontradoException ex) {
                Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (VendedorNaoEncontradoException ex) {
                Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(tipoAgrupamento == 4){
            try {
                ArrayList<Pedido> valores = fachada.relatorioPedido("Codigo_Vendedor");
                for(Pedido pedido : valores){
                    int diff = pedido.getVendedor().getCodigo();
                    if(diff != diffAnt){
                        if(diffAnt != 0){
                            model.addRow(new Object[]{"","","","","Total Vendedor",totalPartes});
                            totalPartes = 0;
                            i++;
                        }
                        model.addRow(new Object[] {diff + " - " + pedido.getVendedor().getNome(),"","","","",""});
                        diffAnt = diff;
                        i++;
                    }
                    model.addRow(new Object[]{"",pedido.getCodigo(), pedido.getCliente().getNome(),pedido.getVendedor().getNome(), pedido.getData_pedido(), pedido.getValor()});
                    total += pedido.getValor();
                    totalPartes += pedido.getValor();
                    i++;
                }
                model.addRow(new Object[]{"","","","","Total Vendedor",totalPartes});
                model.addRow(new Object[]{"","","","","Total",total});
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (PedidoNaoEncontradoException ex) {
                Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClienteNaoExncontradoException ex) {
                Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (VendedorNaoEncontradoException ex) {
                Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        tabelaRelatorio = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        agrupamento = new javax.swing.JComboBox<>();
        gerarRelatorio = new javax.swing.JButton();
        resposta = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        tabelaRelatorio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Agrupamento", "Codigo", "Cliente", "Vendedor", "Data_Pedido", "Valor"
            }
        ));
        jScrollPane1.setViewportView(tabelaRelatorio);

        jLabel1.setText("Agrupar");

        agrupamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        agrupamento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                agrupamentoItemStateChanged(evt);
            }
        });
        agrupamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agrupamentoActionPerformed(evt);
            }
        });

        gerarRelatorio.setText("Gerar Excel");
        gerarRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gerarRelatorioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(agrupamento, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(387, 387, 387)
                        .addComponent(gerarRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(209, 209, 209)
                        .addComponent(resposta, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(236, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(agrupamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gerarRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resposta, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void gerarRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gerarRelatorioActionPerformed
        String resposta = "";
        ComboItem item = (ComboItem) this.agrupamento.getSelectedItem();
        int tipoAgrupamento = item.getValue();
        Fachada fachada = new Fachada();
        if(tipoAgrupamento == 1){
            try {
                ArrayList<Pedido> valores = null;
                try {
                    valores = fachada.relatorioPedido("Data_Pedido");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (PedidoNaoEncontradoException ex) {
                    Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClienteNaoExncontradoException ex) {
                    Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (VendedorNaoEncontradoException ex) {
                    Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
                }
                ManipularExcel excel = new ManipularExcel();
                HSSFWorkbook work = excel.criar("RelatorioMes");
                HSSFSheet sheet = excel.criarSheet(work, "Relatorio");
                resposta = excel.montarRangePedidosMes(sheet, valores);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(tipoAgrupamento == 2){
            try {
                ArrayList<Pedido> valores = null;
                try {
                    valores = fachada.relatorioPedido("Data_Pedido");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (PedidoNaoEncontradoException ex) {
                    Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClienteNaoExncontradoException ex) {
                    Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (VendedorNaoEncontradoException ex) {
                    Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
                }
                ManipularExcel excel = new ManipularExcel();
                HSSFWorkbook work = excel.criar("RelatorioAno");
                HSSFSheet sheet = excel.criarSheet(work, "Relatorio");
                resposta = excel.montarRangePedidosAno(sheet, valores);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(tipoAgrupamento == 3){
            try {
                ArrayList<Pedido> valores = null;
                try {
                    valores = fachada.relatorioPedido("Codigo_Cliente");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (PedidoNaoEncontradoException ex) {
                    Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClienteNaoExncontradoException ex) {
                    Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (VendedorNaoEncontradoException ex) {
                    Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
                }
                ManipularExcel excel = new ManipularExcel();
                HSSFWorkbook work = excel.criar("RelatorioCliente");
                HSSFSheet sheet = excel.criarSheet(work, "Relatorio");
                resposta = excel.montarRangePedidosCliente(sheet, valores);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(tipoAgrupamento == 4){
            try {
                ArrayList<Pedido> valores = null;
                try {
                    valores = fachada.relatorioPedido("Codigo_Vendedor");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (PedidoNaoEncontradoException ex) {
                    Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClienteNaoExncontradoException ex) {
                    Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (VendedorNaoEncontradoException ex) {
                    Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
                }
                ManipularExcel excel = new ManipularExcel();
                HSSFWorkbook work = excel.criar("RelatorioVendedor");
                HSSFSheet sheet = excel.criarSheet(work, "Relatorio");
                resposta = excel.montarRangePedidosVendedor(sheet, valores);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(RelatorioPedidos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.resposta.setText(resposta);
    }//GEN-LAST:event_gerarRelatorioActionPerformed

    private void agrupamentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_agrupamentoItemStateChanged
        if(state == 0){
            state++;
            return;
        }
        ComboItem item = (ComboItem) this.agrupamento.getSelectedItem();
        int tipoAgrupamento = item.getValue();
        this.montarTabela(tipoAgrupamento);
    }//GEN-LAST:event_agrupamentoItemStateChanged

    private void agrupamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agrupamentoActionPerformed
 
    }//GEN-LAST:event_agrupamentoActionPerformed

    private int state;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> agrupamento;
    private javax.swing.JButton gerarRelatorio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel resposta;
    private javax.swing.JTable tabelaRelatorio;
    // End of variables declaration//GEN-END:variables
}
