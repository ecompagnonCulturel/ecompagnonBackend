package uqtr.ecompagnon.util;

import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;
import uqtr.ecompagnon.dto.ResourceDTO;
import uqtr.ecompagnon.dto.UploadExcelResponseDTO;
import uqtr.ecompagnon.model.CpMail;
import uqtr.ecompagnon.model.Etudiant;
import uqtr.ecompagnon.model.Resources;
import uqtr.ecompagnon.util.exception.AppRequestException;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Excel {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static boolean hasExcelFormat(MultipartFile file) {

        return TYPE.equals(file.getContentType());
    }

    public static List<UploadExcelResponseDTO> excelToEtudiantGroup(MultipartFile file, Long groupe, HttpServletRequest request) {

        try {
            String realPathtoUploads =  request.getServletContext().getRealPath("/fileTempPath/");
            if(! new File(realPathtoUploads).exists())
            {
                new File(realPathtoUploads).mkdir();
            }


            //File myFile = new File("C:\\" + UUID.randomUUID() + file.getOriginalFilename());
            File myFile = new File(realPathtoUploads + file.getOriginalFilename());
           // System.out.println("File exists and it is read only, making it writable");
            if(myFile.exists()&&!myFile.canRead()){//&& !myFile.canRead()
                System.out.println(myFile);
                myFile.setWritable(true);
            }
            file.transferTo(myFile);
            FileInputStream is = new FileInputStream(myFile);
            List<UploadExcelResponseDTO> uploadExcelResponsDTOS = new ArrayList<UploadExcelResponseDTO>();
            List<Character> non = new ArrayList<>();
            Workbook hssfWorkbook = WorkbookFactory.create(is);
            for (int i = 0; i < hssfWorkbook.getNumberOfSheets(); i++) {
                String header1 = null, header2 = null, header3 = null, header4 = null;
                // HSSFSheet mySheet = hssfWorkbook.getSheetAt(i);
                org.apache.poi.ss.usermodel.Sheet sheet = hssfWorkbook.getSheetAt(i);
                // Row row1 = sheet.getRowNumber(2);
                for (Row row : sheet) {
                    UploadExcelResponseDTO uploadExcelResponseDTO = new UploadExcelResponseDTO();

                    // First row header skip
                    if ((row.getCell(0) != null)
                            & (row.getCell(1) != null)
                    ) {
                        if (row.getRowNum() == 0) {

                            continue;
                        }
                        String rowValue=row.getCell(0).getStringCellValue();

                        int length=((row.getCell(0).getStringCellValue()).toString()).length();
                        if(length>12)
                        {
                             rowValue=((row.getCell(0).getStringCellValue()).toString()).substring(0,12);

                        }

                        uploadExcelResponseDTO.setGroup(groupe);
                        uploadExcelResponseDTO.setCpEtudiant(rowValue);
                        uploadExcelResponsDTOS.add(uploadExcelResponseDTO);
                    }
                    else
                    {
                        non.add('N');
                    }



                }

            }

            //Delete files
            hssfWorkbook.close();
            myFile.delete();
            return uploadExcelResponsDTOS;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<Etudiant> excelToEtudiant(MultipartFile file, HttpServletRequest request) {

        try {
            String realPathtoUploads =  request.getServletContext().getRealPath("/fileTempPath/");
            if(! new File(realPathtoUploads).exists())
            {
                new File(realPathtoUploads).mkdir();
            }

            File myFile = new File(realPathtoUploads + file.getOriginalFilename());
            if(myFile.exists()&&!myFile.canRead()){//&& !myFile.canRead()
                myFile.setWritable(true);
            }
            file.transferTo(myFile);
            FileInputStream is = new FileInputStream(myFile);
            List<Etudiant> etudiants = new ArrayList<Etudiant>();

            List<Character> non = new ArrayList<>();
            Workbook hssfWorkbook = WorkbookFactory.create(is);
            for (int i = 0; i < hssfWorkbook.getNumberOfSheets(); i++) {
                String header1 = null, header2 = null, header3 = null,header4 = null;
                String  firstName,lastName,cp,courriel;
                // HSSFSheet mySheet = hssfWorkbook.getSheetAt(i);
                org.apache.poi.ss.usermodel.Sheet sheet = hssfWorkbook.getSheetAt(i);
                // Row row1 = sheet.getRowNumber(2);
                for (Row row : sheet) {
                   Etudiant etudiant = new Etudiant();



                    if (((row.getCell(0) != null)
                       & (row.getCell(1) != null))
                                ) {
                        // First row header skip
                        if (row.getRowNum() == 0) {

                            continue;
                        }

                        if((((row.getCell(0).getStringCellValue()).trim()).equals(""))
                           || (((row.getCell(1).getStringCellValue()).trim()).equals(""))
                            || (((row.getCell(2).getStringCellValue()).trim()).equals("")))
                        {
                           continue;
                        }
                        else{
                            firstName=(row.getCell(0).getStringCellValue()).trim();
                            lastName=(row.getCell(1).getStringCellValue()).trim();;
                            cp=(row.getCell(2).getStringCellValue()).trim();
                            int length=cp.length();

                            if(length>12)
                            {
                                cp=cp.substring(0,12);

                            }
                            courriel=row.getCell(3).getStringCellValue();


                   /*     System.out.println("prenom "+firstName);
                        System.out.println("nom "+lastName);
                        System.out.println("cp "+cp);
                        System.out.println("courriel "+courriel);*/

                            etudiant.setId(0L);
                            etudiant.setEtudFirstName(firstName);
                            etudiant.setEtudLastName(lastName);
                            etudiant.setEtudCP(cp.toUpperCase());
                            etudiant.setEtudAdresse(courriel.toLowerCase());
                            etudiant.setEtudStatus(1L);
                            etudiant.setEtudProg(1L);

                            etudiants.add(etudiant);
                        }


                    }
                    else
                    {
                        non.add('N');
                    }



                }

            }

            //Delete files
            hssfWorkbook.close();
            myFile.delete();
            return etudiants;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<ResourceDTO> excelToRessource(MultipartFile file, Long sessionP, HttpServletRequest request) {

        try {
            String realPathtoUploads =  request.getServletContext().getRealPath("/fileTempPath/");
            if(! new File(realPathtoUploads).exists())
            {
                new File(realPathtoUploads).mkdir();
            }

            File myFile = new File(realPathtoUploads + file.getOriginalFilename());
            if(myFile.exists()&&!myFile.canRead()){//&& !myFile.canRead()
                myFile.setWritable(true);
            }
            file.transferTo(myFile);
            FileInputStream is = new FileInputStream(myFile);
            List<ResourceDTO> resourceDTOS = new ArrayList<ResourceDTO>();

            List<Character> non = new ArrayList<>();
            Workbook hssfWorkbook = WorkbookFactory.create(is);
           // if (hssfWorkbook.getSheetAt(0)) {
                String header1 = null, header2 = null, header3 = null,header4 = null;
                String  desc,url,ville=null,adresse=null,cp=null;
                long type=0,region=0, session;
                // HSSFSheet mySheet = hssfWorkbook.getSheetAt(i);
                org.apache.poi.ss.usermodel.Sheet sheet = hssfWorkbook.getSheetAt(0);
                // Row row1 = sheet.getRowNumber(2);
                for (Row row : sheet) {
                    ResourceDTO resourceDTO = new ResourceDTO();


                    // First row header skip
                    if ((row.getCell(0) != null)
                            & (row.getCell(1) != null)
                    ) {
                        if (row.getRowNum() == 0) {

                            continue;
                        }
                        session= sessionP;
                        desc=(row.getCell(0).getStringCellValue());
                        url=(row.getCell(1).getStringCellValue()).trim();
                        if((row.getCell(2).getCellType()==CellType.NUMERIC)
                                ||((row.getCell(3).getCellType()==CellType.NUMERIC)
                                && (row.getCell(3).getNumericCellValue()!=0)))
                        {
                            region= (long)row.getCell(3).getNumericCellValue();;
                            ville=(row.getCell(4).getStringCellValue());
                            adresse=(row.getCell(5).getStringCellValue());
                            cp=(row.getCell(6).getStringCellValue()).trim();
                            type=(long)row.getCell(2).getNumericCellValue();
                        }
                       if(type>0 && session>0)
                       {
                           resourceDTO.setId(0L);
                           resourceDTO.setRessSession(session);
                           resourceDTO.setRessDesc(desc);
                           resourceDTO.setRessUrl(url);
                           resourceDTO.setRessType(type);
                           resourceDTO.setRessRegion(region);
                           resourceDTO.setRessVille(ville);
                           resourceDTO.setRessLieu(adresse);
                           resourceDTO.setRessCodeP(cp);
                           resourceDTOS.add(resourceDTO);
                       }

                    }
                    else
                    {
                        non.add('N');
                    }



                }

            //Delete files
            hssfWorkbook.close();
            myFile.delete();
            return resourceDTOS;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

/*    *//**
     * @param is
     * @return
     *//*
    public static List<Class> excelClass1(InputStream is) {
        try {

            Workbook workbook = new XSSFWorkbook(is);

            System.out.println(workbook.getSheet(SHEET));
            Sheet sheet = workbook.getSheet(SHEET);

            System.out.println(is);
            System.out.println(sheet);
            Iterator<Row> rows = sheet.iterator();

            List<Class> classes = new ArrayList<Class>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Class classe = new Class();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            classe.setClassId((long) currentCell.getNumericCellValue());
                            break;

                        case 1:
                            classe.setClassName(currentCell.getStringCellValue());
                            break;

                        *//*
                         * case 2: classe.setVersId((long)currentCell.getNumericCellValue()); break;
                         *
                         * case 3: classe.setSysId((long)currentCell.getNumericCellValue()); break;
                         *//*

                        default:
                            break;
                    }

                    cellIdx++;
                }

                classes.add(classe);
            }

            workbook.close();

            return classes;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }




    public static ByteArrayInputStream classesToExcel(List<Class> classes) {

        try (
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet(SHEET);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }

            int rowIdx = 1;
            for (Class classe : classes) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(classe.getClassId());
                row.createCell(1).setCellValue(classe.getClassName());
	    *//*    row.createCell(2).setCellValue(classe.getDescription());
	        row.createCell(3).setCellValue(classe.isPublished());
	      }*//*
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());


        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }


    }

    public static List<Impact> ManyImpactClass(long[] classe, Long faute, Long type) {

        try {
            List<Impact> impacts = new ArrayList<Impact>();
            for (Long classeid : classe) {

                Impact impact = new Impact();

                impact.setClassId(classeid);
                impact.setFaultId(faute);
                impact.setType(type);
                impacts.add(impact);


            }

            return impacts;

        } catch (Error e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }


    }*/

}
