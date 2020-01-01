package base.crm;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;


public class Rough {


    private List<String> eventsGeneral = new ArrayList<String>();
    private List<String> companiesGeneral = new ArrayList<String>();
    //  private SessionMap<String, Object> sessionMapGeneral;
    List<String> accountManagerEmail = new ArrayList<String>();
    List<String> siEmailAdd = new ArrayList<String>();
    List<String> comPanyName = new ArrayList<String>();


    private String eventGeneral = null;
    private String companyGeneral = null;
    List<String> arrlist = new ArrayList<>();




    public String getEventGeneral() {
        return eventGeneral;
    }

    public void setEventGeneral(String eventGeneral) {
        this.eventGeneral = eventGeneral;
    }

    public String getCompanyGeneral() {
        return companyGeneral;
    }

    public void setCompanyGeneral(String companyGeneral) {
        this.companyGeneral = companyGeneral;
    }

    public List<String> getDataForGeneralReports() {
        return arrlist;
    }

    public void setDataForGeneralReports(List<String> dataForGeneralReports) {
        this.arrlist = dataForGeneralReports;
    }

    public List<String> getEventsGeneral() {
        return eventsGeneral;
    }

    public void setEventsGeneral(List<String> eventsGeneral) {
        this.eventsGeneral = eventsGeneral;
    }

    public List<String> getCompaniesGeneral() {
        return companiesGeneral;
    }

    public void setCompaniesGeneral(List<String> companiesGeneral) {
        this.companiesGeneral = companiesGeneral;
    }



    public  String generateGeneralXls() throws Exception {
        try {

            String titles="Admin Email Address, SI Email Address, Company Name";
            String[]arrTiltes=titles.split(",");

            FileOutputStream fileOut = new FileOutputStream("D:\\JavaProgram\\FreeCRM\\src\\main\\resources\\Data.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet worksheet = workbook.createSheet("POI Worksheet");


            int row=0;
            Row rowTitle = worksheet.createRow(row);

            // set  titles
            for(int i=0;i<arrTiltes.length;i++){
                Cell cellTitle=rowTitle.createCell(i);
                cellTitle.setCellValue(arrTiltes[i]);
            }

            accountManagerEmail.add("accountamanager@email.com");
            siEmailAdd.add("siemail@email.com");
            comPanyName.add("company ltd");

            //setting values
            row++;
            Row rowValue = worksheet.createRow(row);

            Cell cell0 = rowValue.createCell(0);
            cell0.setCellValue(accountManagerEmail.get(0));

            Cell cell1 = rowValue.createCell(1);
            cell1.setCellValue(siEmailAdd.get(0));

            Cell cell2 = rowValue.createCell(2);
            cell2.setCellValue(comPanyName.get(0));

            row++;
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    public static void main(String[] args) throws Exception {

        Rough rough= new Rough();
        System.out.println(rough.generateGeneralXls());

    }
}
