package cn.hhuc.service.download.reports;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * PDF型表表生成工具类
 */
public class PDFUtils {


    public static void downLoadPDF(HttpServletResponse response,List<LinkedHashMap<String,String>> datas){
        try {
            response.setContentType("application/pdf");
            createPDF(datas,response.getOutputStream());
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void createPDF(List<LinkedHashMap<String,String>> datas,OutputStream fileOutputStream) throws DocumentException {

        Document document = new Document();
//        try {
//            fileOutputStream = new FileOutputStream("C:\\Users\\Lenovo\\Desktop\\test.pdf");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        // 将报表写出
        PdfWriter pdfWriter = PdfWriter.getInstance(document, fileOutputStream);

        document.setPageSize(PageSize.A4);
        document.open();


        // 获得表报数据
        Map map = handleDatas(datas);
        ArrayList titles = (ArrayList) map.get("titles");
        ArrayList<ArrayList> datasList = (ArrayList<ArrayList>) map.get("datas");

        // 创建表报标题
        Paragraph title = new Paragraph("大数据报表", getFont(25));
        title.setAlignment(1);
        document.add(title);

        // 创建报表时间
        Paragraph time = new Paragraph(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(System.currentTimeMillis()), getFont(10));
        time.setAlignment(1);
        document.add(time);


        // 创建表格
        float[] widths = new float[titles.size()];
        Arrays.fill(widths,144);
        PdfPTable table = new PdfPTable(widths);
        table.setTotalWidth(458);

        // 写入表格标题
        addLine(table,titles);

        // 循环向表格写入行
        for (int i=0;i<datasList.size();i++){
            addLine(table,datasList.get(i));
        }
        System.out.println("创建成功");
        document.add(table);
        document.close();
    }

    // 为每行写入信息
    private static void addLine(PdfPTable table,ArrayList list){
        for (int i=0;i<list.size();i++){
            PdfPCell cell = new PdfPCell();
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            Paragraph paragraph = new Paragraph(list.get(i).toString(), getFont(15));
            cell.setPhrase(paragraph);
            table.addCell(cell);
        }
    }

    // 设置字体
    private static Font getFont(int size){
        Font fontchina = null;
        try {
            BaseFont font = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            fontchina = new Font(font, size, Font.NORMAL);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return fontchina;
        }
    }

    // 数据转换
    private static Map handleDatas(List<LinkedHashMap<String,String>> datas){
        HashMap<Object, Object> map = new HashMap<>();
        Set<String> set = datas.get(0).keySet();
        ArrayList<String> titles = new ArrayList<>(set);
        map.put("titles",titles);

        ArrayList<ArrayList> datasList = new ArrayList<>();

        for (LinkedHashMap<String,String> items : datas){
            ArrayList<Object> list = new ArrayList<>();
            for (Map.Entry<String,String> item : items.entrySet()){
                list.add(item.getValue());
            }
            datasList.add(list);
        }
        map.put("datas",datasList);
        return map;
    }


}
