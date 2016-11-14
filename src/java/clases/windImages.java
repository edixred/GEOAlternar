/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author giiee
 */
@ManagedBean(name = "windImages")
@ApplicationScoped
public class windImages {
    private StreamedContent filerose,fileweibull;
    public  windImages()  {  
        
         
    }  

    @PostConstruct
    public void iniciar()  {
        filerose=null;
        fileweibull=null;
    }
    
    public void generar(String n1,String n2){
        InputStream streamrose = null;   
        filerose=null;
        try {
            streamrose = new FileInputStream("/tmp/"+n1);
             System.out.println("entro: "+n1);
             filerose = new DefaultStreamedContent(streamrose, "image/png", n1);
        } catch (Exception ex) {
            System.out.println("err aqui");

                        filerose=null;
        }
         
         InputStream streamweibull = null;   
        try {
            streamweibull = new FileInputStream("/tmp/"+n2);
            System.out.println("entro1: "+n2);
            fileweibull = new DefaultStreamedContent(streamweibull, "image/png", n2);
        } catch (Exception ex) {
            System.out.println(":err aqui 2");

            fileweibull=null;
        }
         
    }
    
    public StreamedContent getFilerose() {
        return filerose;
    }

    public StreamedContent getFileweibull() {
        return fileweibull;
    }
    

    

    
    
    
}
