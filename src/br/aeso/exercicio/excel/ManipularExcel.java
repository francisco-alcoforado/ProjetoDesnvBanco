/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.aeso.exercicio.excel;

import br.aeso.exercicio.pedido.Pedido;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 *
 * @author lab01
 */
public class ManipularExcel {
    private FileOutputStream stream;
    private File file;
    private String[] meses = new String[]{"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
    public HSSFWorkbook criar(String nome) throws FileNotFoundException{
        this.file = new File(nome + ".xls");
        this.stream = new FileOutputStream(this.file);
        HSSFWorkbook work = new HSSFWorkbook();
        return work;
    }
    public HSSFSheet criarSheet(HSSFWorkbook work, String nome){
        return work.createSheet(nome);
    }
    public String montarRangePedidosMes(HSSFSheet sheet, ArrayList<Pedido> valores){
        HSSFRow firShRow = sheet.createRow(0);
        firShRow.createCell(0).setCellValue("Codigo");
        firShRow.createCell(1).setCellValue("Cliente");
        firShRow.createCell(2).setCellValue("Vendedor");
        firShRow.createCell(3).setCellValue("Data Pedido");
        firShRow.createCell(4).setCellValue("Valor");
        
        HSSFRow shRow;
        int diffAnt = 0;
        double total = 0;
        double totalPartes = 0;
        int i = 1;
        for(Pedido pedido : valores){
            shRow = sheet.createRow(i);
            
            Calendar cal = Calendar.getInstance();
            cal.setTime(pedido.getData_pedido());
            
            int diff = cal.get(Calendar.MONTH);
            if(diff != diffAnt){
                if(diffAnt != 0){
                    sheet.addMergedRegion(new CellRangeAddress(i, i, 0, 3));
                    shRow.createCell(0).setCellValue("Total Mes");
                    shRow.getCell(0).getCellStyle().setAlignment(HorizontalAlignment.RIGHT);
                    shRow.createCell(4).setCellValue(totalPartes);
                    totalPartes = 0;
                    i++;
                    shRow = sheet.createRow(i);
                }
                sheet.addMergedRegion(new CellRangeAddress(i, i, 0, 4));
                shRow.createCell(0).getCellStyle().setAlignment(HorizontalAlignment.LEFT);
                shRow.getCell(0).setCellValue(this.meses[diff]);
                diffAnt = diff;
                i++;
                shRow = sheet.createRow(i);
            }
            
            shRow.createCell(0).setCellValue(pedido.getCodigo());
            shRow.createCell(1).setCellValue(pedido.getCliente().getNome());
            shRow.createCell(2).setCellValue(pedido.getVendedor().getNome());
            shRow.createCell(3).setCellValue(pedido.getData_pedido());
            shRow.createCell(4).setCellValue(pedido.getValor());
            total += pedido.getValor();
            totalPartes += pedido.getValor();
            i++;
        }
        shRow = sheet.createRow(i);
        sheet.addMergedRegion(new CellRangeAddress(i, i, 0, 3));
        shRow.createCell(0).setCellValue("Total Mes");
        shRow.getCell(0).getCellStyle().setAlignment(HorizontalAlignment.RIGHT);
        shRow.createCell(4).setCellValue(totalPartes);
        
        shRow = sheet.createRow(i);
        sheet.addMergedRegion(new CellRangeAddress(i + 1, i + 1, 0, 3));
        shRow.createCell(0).setCellValue("Total");
        shRow.getCell(0).getCellStyle().setAlignment(HorizontalAlignment.RIGHT);
        shRow.createCell(4).setCellValue(total);
        
        try {
            sheet.getWorkbook().write(stream);
            stream.close();
            sheet.getWorkbook().close();
            return file.getAbsolutePath();
        } catch (IOException ex) {
            Logger.getLogger(ManipularExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public String montarRangePedidosAno(HSSFSheet sheet, ArrayList<Pedido> valores){
        HSSFRow firShRow = sheet.createRow(0);
        firShRow.createCell(0).setCellValue("Codigo");
        firShRow.createCell(1).setCellValue("Cliente");
        firShRow.createCell(2).setCellValue("Vendedor");
        firShRow.createCell(3).setCellValue("Data Pedido");
        firShRow.createCell(4).setCellValue("Valor");
        
        HSSFRow shRow;
        int diffAnt = 0;
        double total = 0;
        double totalPartes = 0;
        int i = 1;
        for(Pedido pedido : valores){
            shRow = sheet.createRow(i);
            
            Calendar cal = Calendar.getInstance();
            cal.setTime(pedido.getData_pedido());
            
            int diff = cal.get(Calendar.YEAR);
            if(diff != diffAnt){
                if(diffAnt != 0){
                    sheet.addMergedRegion(new CellRangeAddress(i, i, 0, 3));
                    shRow.createCell(0).setCellValue("Total Ano");
                    shRow.getCell(0).getCellStyle().setAlignment(HorizontalAlignment.RIGHT);
                    shRow.createCell(4).setCellValue(totalPartes);
                    totalPartes = 0;
                    i++;
                    shRow = sheet.createRow(i);
                }
                sheet.addMergedRegion(new CellRangeAddress(i, i, 0, 4));
                shRow.createCell(0).getCellStyle().setAlignment(HorizontalAlignment.LEFT);
                shRow.getCell(0).setCellValue(diff);
                diffAnt = diff;
                i++;
                shRow = sheet.createRow(i);
            }
            
            shRow.createCell(0).setCellValue(pedido.getCodigo());
            shRow.createCell(1).setCellValue(pedido.getCliente().getNome());
            shRow.createCell(2).setCellValue(pedido.getVendedor().getNome());
            shRow.createCell(3).setCellValue(pedido.getData_pedido());
            shRow.createCell(4).setCellValue(pedido.getValor());
            total += pedido.getValor();
            totalPartes += pedido.getValor();
            i++;
        }
        shRow = sheet.createRow(i);
        sheet.addMergedRegion(new CellRangeAddress(i, i, 0, 3));
        shRow.createCell(0).setCellValue("Total Ano");
        shRow.getCell(0).getCellStyle().setAlignment(HorizontalAlignment.RIGHT);
        shRow.createCell(4).setCellValue(totalPartes);
        
        shRow = sheet.createRow(i + 1);
        sheet.addMergedRegion(new CellRangeAddress(i + 1, i + 1, 0, 3));
        shRow.createCell(0).setCellValue("Total");
        shRow.getCell(0).getCellStyle().setAlignment(HorizontalAlignment.RIGHT);
        shRow.createCell(4).setCellValue(total);
        
        try {
            sheet.getWorkbook().write(stream);
            stream.close();
            sheet.getWorkbook().close();
            return file.getAbsolutePath();
        } catch (IOException ex) {
            Logger.getLogger(ManipularExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public String montarRangePedidosCliente(HSSFSheet sheet, ArrayList<Pedido> valores){
        HSSFRow firShRow = sheet.createRow(0);
        firShRow.createCell(0).setCellValue("Codigo");
        firShRow.createCell(1).setCellValue("Cliente");
        firShRow.createCell(2).setCellValue("Vendedor");
        firShRow.createCell(3).setCellValue("Data Pedido");
        firShRow.createCell(4).setCellValue("Valor");
        
        HSSFRow shRow;
        int diffAnt = 0;
        double total = 0;
        double totalPartes = 0;
        int i = 1;
        for(Pedido pedido : valores){
            shRow = sheet.createRow(i);
            
            int diff = pedido.getCliente().getCodigo();
            if(diff != diffAnt){
                if(diffAnt != 0){
                    sheet.addMergedRegion(new CellRangeAddress(i, i, 0, 3));
                    shRow.createCell(0).setCellValue("Total Cliente");
                    shRow.getCell(0).getCellStyle().setAlignment(HorizontalAlignment.RIGHT);
                    shRow.createCell(4).setCellValue(totalPartes);
                    totalPartes = 0;
                    i++;
                    shRow = sheet.createRow(i);
                }
                
                shRow.createCell(0).setCellValue(diff + " - " + pedido.getCliente().getNome());
                sheet.addMergedRegion(new CellRangeAddress(i, i, 0, 4));
                shRow.getCell(0).getCellStyle().setAlignment(HorizontalAlignment.CENTER_SELECTION);
                diffAnt = diff;
                i++;
                shRow = sheet.createRow(i);
            }
            
            shRow.createCell(0).setCellValue(pedido.getCodigo());
            shRow.createCell(1).setCellValue(pedido.getCliente().getNome());
            shRow.createCell(2).setCellValue(pedido.getVendedor().getNome());
            shRow.createCell(3).setCellValue(pedido.getData_pedido());
            shRow.createCell(4).setCellValue(pedido.getValor());
            total += pedido.getValor();
            totalPartes += pedido.getValor();
            i++;
        }
        
        shRow = sheet.createRow(i);
        sheet.addMergedRegion(new CellRangeAddress(i, i, 0, 3));
        shRow.createCell(0).setCellValue("Total Cliente");
        shRow.getCell(0).getCellStyle().setAlignment(HorizontalAlignment.RIGHT);
        shRow.createCell(4).setCellValue(totalPartes);
        
        shRow = sheet.createRow(i + 1);
        sheet.addMergedRegion(new CellRangeAddress(i + 1, i + 1, 0, 3));
        shRow.createCell(0).setCellValue("Total");
        shRow.getCell(0).getCellStyle().setAlignment(HorizontalAlignment.RIGHT);
        shRow.createCell(4).setCellValue(total);
        
        try {
            sheet.getWorkbook().write(stream);
            stream.close();
            sheet.getWorkbook().close();
            return file.getAbsolutePath();
        } catch (IOException ex) {
            Logger.getLogger(ManipularExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public String montarRangePedidosVendedor(HSSFSheet sheet, ArrayList<Pedido> valores){
        HSSFRow firShRow = sheet.createRow(0);
        firShRow.createCell(0).setCellValue("Codigo");
        firShRow.createCell(1).setCellValue("Cliente");
        firShRow.createCell(2).setCellValue("Vendedor");
        firShRow.createCell(3).setCellValue("Data Pedido");
        firShRow.createCell(4).setCellValue("Valor");
        
        HSSFRow shRow;
        int diffAnt = 0;
        double total = 0;
        double totalPartes = 0;
        int i = 1;
        for(Pedido pedido : valores){
            shRow = sheet.createRow(i);
            
            int diff = pedido.getVendedor().getCodigo();
            if(diff != diffAnt){
                if(diffAnt != 0){
                    sheet.addMergedRegion(new CellRangeAddress(i, i, 0, 3));
                    shRow.createCell(0).setCellValue("Total Vendedor");
                    shRow.getCell(0).getCellStyle().setAlignment(HorizontalAlignment.RIGHT);
                    shRow.createCell(4).setCellValue(totalPartes);
                    totalPartes = 0;
                    i++;
                    shRow = sheet.createRow(i);
                }
                sheet.addMergedRegion(new CellRangeAddress(i, i, 0, 4));
                shRow.createCell(0).getCellStyle().setAlignment(HorizontalAlignment.LEFT);
                shRow.getCell(0).setCellValue(diff + " - " + pedido.getVendedor().getNome());
                diffAnt = diff;
                i++;
                shRow = sheet.createRow(i);
                
            }
            
            shRow.createCell(0).setCellValue(pedido.getCodigo());
            shRow.createCell(1).setCellValue(pedido.getCliente().getNome());
            shRow.createCell(2).setCellValue(pedido.getVendedor().getNome());
            shRow.createCell(3).setCellValue(pedido.getData_pedido());
            shRow.createCell(4).setCellValue(pedido.getValor());
            total += pedido.getValor();
            totalPartes += pedido.getValor();
            i++;
        }
        shRow = sheet.createRow(i);
        sheet.addMergedRegion(new CellRangeAddress(i, i, 0, 3));
        shRow.createCell(0).setCellValue("Total Vendedor");
        shRow.getCell(0).getCellStyle().setAlignment(HorizontalAlignment.RIGHT);
        shRow.createCell(4).setCellValue(totalPartes);
        
        shRow = sheet.createRow(i + 1);
        sheet.addMergedRegion(new CellRangeAddress(i + 1, i + 1, 0, 3));
        shRow.createCell(0).setCellValue("Total");
        shRow.getCell(0).getCellStyle().setAlignment(HorizontalAlignment.RIGHT);
        shRow.createCell(4).setCellValue(total);
        
        try {
            sheet.getWorkbook().write(stream);
            stream.close();
            sheet.getWorkbook().close();
            return file.getAbsolutePath();
        } catch (IOException ex) {
            Logger.getLogger(ManipularExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
