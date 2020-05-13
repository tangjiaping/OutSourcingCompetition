package cn.hhuc.controller.download;


import cn.hhuc.service.download.reports.Reports;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class DownloadController {

    @Autowired
    private Reports reports;

    /**
     * 请求下载Excel格式的报表
     * @param name
     * @param response
     */
    @RequestMapping("/downloadExcel")
    public void downloadExcel(@RequestParam String name, HttpServletResponse response){
        reports.downloadExcel(name,response);
    }

    /**
     * 请求下载PDF格式的表报
     * @param name
     * @param response
     */
    @RequestMapping("/downloadPdf")
    public void downloadPdf(@RequestParam String name, HttpServletResponse response){
        reports.downloadPDF(name,response);
    }
}
