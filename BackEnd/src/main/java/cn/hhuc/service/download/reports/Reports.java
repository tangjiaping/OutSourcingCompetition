package cn.hhuc.service.download.reports;


import cn.hhuc.mapper.DownLoadMapper;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;


@Service
public class Reports {


    @Autowired
    public DownLoadMapper downLoadMapper;

    public void downloadExcel(String name,HttpServletResponse response){

        List<LinkedHashMap<String, String>> list =  downLoadMapper.download(name);

        // 通过自定义工具类获得Excel
        HSSFWorkbook workbook = ExcelUtils.getExcel(list,name );

        response.setHeader("Content-disposition", "attachment; filename=" + name + ".xls");
        response.setContentType("application/msexcel");
        ServletOutputStream outputStream = null;

        try {
            outputStream = response.getOutputStream();
            // 将表报通过response写出
            workbook.write(outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void downloadPDF(String name,HttpServletResponse response) {
        List<LinkedHashMap<String, String>> list =  downLoadMapper.download(name);

        PDFUtils.downLoadPDF(response,list);
    }

}
