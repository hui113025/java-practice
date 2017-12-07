package com.zheng.poi;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** 
 * @Description: TODO
 * @author Somnus
 * @date 2015年11月6日 上午11:22:57 
 * @version V1.0 
 */
public class ExcelTestCase {
	
	@Test
	public void read(){
		List<String[]> list = ExcelReader.readExcel("excel/20171207.xls");
        for(String[] arr:list){
            System.out.println(Arrays.toString(arr));
        }
	}
    
    /**
     * 50W 数据量
     * SXSSFWorkbook
     * @throws Exception
     */
    @Test
    public void createMax() throws Exception {
        List<People> list = new ArrayList<People>();
        for(int i=0; i<500000; i++){
        	 People people = new People("admin"+i,"password"+i,null,null);
        	 list.add(people);
        }
        MaxExcelWriter writer = new MaxExcelWriter();
        byte[] buff = writer.exportExcel("测试例子", new String[]{"用户名","密码"}, list, null);
        OutputStream os = new FileOutputStream(new File("E:/maxCounts.xlsx"));
        IOUtils.write(buff, os);
    }
}