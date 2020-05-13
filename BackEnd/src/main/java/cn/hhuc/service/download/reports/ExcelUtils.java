package cn.hhuc.service.download.reports;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.*;

/**
 * Excel表表生成工具类
 */
public class ExcelUtils {

    public static HSSFWorkbook getExcel(List<LinkedHashMap<String, String>> list, String tableName){
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();

        HSSFSheet sheet = hssfWorkbook.createSheet(tableName);

        // 写入字段标题
        ArrayList<String> titles = new ArrayList<>(list.get(0).keySet());
        HSSFRow row = sheet.createRow(0);
        for (int i=0;i<titles.size();i++){
            row.createCell(i).setCellValue(titles.get(i));
        }

        // 循环创建行
        for (int i=0;i<list.size();i++){
            HSSFRow row1 = sheet.createRow(i + 1);

            Map<String, String> map1 = list.get(i);

            Set<String> keySet = map1.keySet();
            ArrayList<String> arrayList = new ArrayList<>(keySet);

            // 循环将每行字段填充
            for (int j=0;j<arrayList.size();j++){
//                System.out.println(map1.get(arrayList.get(j)));
                row1.createCell(j).setCellValue(map1.get(arrayList.get(j)));
            }
        }

        return hssfWorkbook;
    }
}
