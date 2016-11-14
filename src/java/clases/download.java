package clases;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean(name = "download")
 public class download {
     
     private StreamedContent filebiomassyear,filebiomassmonth;
     private StreamedContent filesunyear,filesunmonth;
     private StreamedContent filesunyearModis,filesunmonthModis;
     private StreamedContent filewindyear,filewindmonth,fileideam;
     private StreamedContent filebiomass,filewind,filesun,filesunModis;  
     private StreamedContent filerose,fileweibull;
     private StreamedContent fileManual;
     
     public download() throws FileNotFoundException {  
         InputStream stream  = new FileInputStream("/opt/maps/MapsCSV/Biomass2000-2014.csv");   
         filebiomassyear = new DefaultStreamedContent(stream, "application/csv", "Biomass2000-2014.csv");  
         InputStream stream2  = new FileInputStream("/opt/maps/MapsCSV/BiomassJanuary-December.csv");   
         filebiomassmonth = new DefaultStreamedContent(stream2, "application/csv", "BiomassJanuary-December.csv");
         InputStream stream3  = new FileInputStream("/opt/maps/MapsCSV/Sun2000-2014.csv");   
         filesunyear = new DefaultStreamedContent(stream3, "application/csv", "Sun2000-2014.csv");  
         InputStream stream4  = new FileInputStream("/opt/maps/MapsCSV/SunJanuary-December.csv");   
         filesunmonth = new DefaultStreamedContent(stream4, "application/csv", "SunJanuary-December.csv");
         InputStream stream5  = new FileInputStream("/opt/maps/MapsCSV/Wind2000-2014.csv");   
         filewindyear = new DefaultStreamedContent(stream5, "application/csv", "Wind2000-2014.csv");  
         InputStream stream6  = new FileInputStream("/opt/maps/MapsCSV/WindJanuary-December.csv");   
         filewindmonth = new DefaultStreamedContent(stream6, "application/csv", "WindJanuary-December.csv");
         InputStream stream7  = new FileInputStream("/opt/maps/MapsCSV/VientoPromedioNarino.csv");   
         filewind = new DefaultStreamedContent(stream7, "application/csv", "VientoPromedioNarino.csv");
         InputStream stream8  = new FileInputStream("/opt/maps/MapsCSV/IrradiacionPromedioNarino.csv");   
         filesun = new DefaultStreamedContent(stream8, "application/csv", "IrradiacionPromedioNarino.csv");
         InputStream stream9  = new FileInputStream("/opt/maps/MapsCSV/BiomasaPromedioNarino.csv");   
         filebiomass = new DefaultStreamedContent(stream9, "application/csv", "BiomasaPromedioNarino.csv");
         InputStream stream10  = new FileInputStream("/opt/maps/MapsCSV/SunMODIS2000-2014.csv");   
         filesunyearModis = new DefaultStreamedContent(stream10, "application/csv", "SunMODIS2000-2014.csv");  
         InputStream stream11  = new FileInputStream("/opt/maps/MapsCSV/SunMODISJanuary-December.csv");   
         filesunmonthModis = new DefaultStreamedContent(stream11, "application/csv", "SunMODISJanuary-December.csv");
         InputStream stream12  = new FileInputStream("/opt/maps/MapsCSV/IrradiacionPromedioNarinoMODIS.csv");   
         filesunModis = new DefaultStreamedContent(stream12, "application/csv", "IrradiacionPromedioNarinoMODIS.csv");
         InputStream stream13  = new FileInputStream("/opt/maps/MapsCSV/ManualUsuario_GEOAlternar.pdf");   
         fileManual = new DefaultStreamedContent(stream13, "application/pdf", "ManualUsuario_GEOAlternar.pdf");
         InputStream stream14  = new FileInputStream("/opt/maps/MapsCSV/InformacionReportesVariablesPromediosMensualesPorEstacion.csv");   
         fileideam = new DefaultStreamedContent(stream14, "application/csv", "InformacionReportesVariablesPromediosMensualesPorEstacion.csv");
    }  

    public StreamedContent getFilesunyearModis() {
        return filesunyearModis;
    }

    public void setFilesunyearModis(StreamedContent filesunyearModis) {
        this.filesunyearModis = filesunyearModis;
    }

    public StreamedContent getFilesunmonthModis() {
        return filesunmonthModis;
    }

    public void setFilesunmonthModis(StreamedContent filesunmonthModis) {
        this.filesunmonthModis = filesunmonthModis;
    }

    public StreamedContent getFilesunModis() {
        return filesunModis;
    }

    public void setFilesunModis(StreamedContent filesunModis) {
        this.filesunModis = filesunModis;
    }

     
    public StreamedContent getFilebiomassyear() {
        return filebiomassyear;
    }

    public void setFilebiomassyear(StreamedContent filebiomassyear) {
        this.filebiomassyear = filebiomassyear;
    }

    public StreamedContent getFilebiomassmonth() {
        return filebiomassmonth;
    }

    public void setFilebiomassmonth(StreamedContent filebiomassmonth) {
        this.filebiomassmonth = filebiomassmonth;
    }

    public StreamedContent getFilesunyear() {
        return filesunyear;
    }

    public void setFilesunyear(StreamedContent filesunyear) {
        this.filesunyear = filesunyear;
    }

    public StreamedContent getFilesunmonth() {
        return filesunmonth;
    }

    public void setFilesunmonth(StreamedContent filesunmonth) {
        this.filesunmonth = filesunmonth;
    }

    public StreamedContent getFilewindyear() {
        return filewindyear;
    }

    public void setFilewindyear(StreamedContent filewindyear) {
        this.filewindyear = filewindyear;
    }

    public StreamedContent getFilewindmonth() {
        return filewindmonth;
    }

    public void setFilewindmonth(StreamedContent filewindmonth) {
        this.filewindmonth = filewindmonth;
    }

    public StreamedContent getFilebiomass() {
        return filebiomass;
    }

    public void setFilebiomass(StreamedContent filebiomass) {
        this.filebiomass = filebiomass;
    }

    public StreamedContent getFilewind() {
        return filewind;
    }

    public void setFilewind(StreamedContent filewind) {
        this.filewind = filewind;
    }

    public StreamedContent getFilesun() {
        return filesun;
    }

    public void setFilesun(StreamedContent filesun) {
        this.filesun = filesun;
    }

    public StreamedContent getFileManual() {
        return fileManual;
    }

    public void setFileManual(StreamedContent fileManual) {
        this.fileManual = fileManual;
    }

    public StreamedContent getFileideam() {
        return fileideam;
    }

    public void setFileideam(StreamedContent fileideam) {
        this.fileideam = fileideam;
    }
   
}   
