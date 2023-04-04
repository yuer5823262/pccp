package com.example.pccp.ScheduleTask;

import com.example.pccp.common.Constant;
import com.example.pccp.common.WebsocketServerEndpoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class FileCheckScheduleTask {
    @Scheduled(fixedRate=1000)
    @Async
    public void testCheckFileScheduleTask()
    {
        String filePath = Constant.TEST_DATA_PATH;
        File file = new File(filePath);
        File[] fileList = file.listFiles();
        for(File f:fileList)
        {
            String fileName = f.getName();

            String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
            List<Integer> numbers = new ArrayList<>();
            if("txt".equals(ext))
            {
                List<String>  lines = null;
                try {
                    lines = Files.readAllLines(f.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                WebsocketServerEndpoint.print("qjData:"+String.join(",", lines));
                f.delete();
            }
//            {
//                String binPath = Constant.YC_BIN_PATH+fileName.substring(0,fileName.lastIndexOf("."))+".bin";
//                File binFile = new File (binPath);
//                if(binFile.exists()){
//                    try {
//
//                        FileInputStream fileInput = new FileInputStream(binFile);
//                        FileOutputStream fileOutPut = new FileOutputStream(Constant.HISTORY_BIN_PATH+fileName.substring(0,fileName.lastIndexOf("."))+".bin");
//                        byte[] b = new byte[1024];
//                        // 定义一个整型len，主要用来记录输入流读取字节长度，
//                        // 1 判断文件是否读取完毕 2 确定输出流写入到文件的字节数
//                        int len = 0;
//                        int count = 0;
//                        // 输入流读取文件到末尾时，返回-1
//                        while ((len = fileInput.read(b)) != -1) {
//                            count+=len;
//                            numbers.addAll(toInt(b,len));
//                            fileOutPut.write(b, 0, len);
//                        }
//                        fileInput.close();
//                        fileOutPut.close();
//                        WebsocketServerEndpoint.print("qjData:"+numbers);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//
//
//                f.delete();
//
//            }
        }
    }
    public static List<Integer> toInt(byte[] bytes,int len){
        List<Integer> numbers = new ArrayList<>();
        for(int i = 0; i < len ; i=i+4){
            int number = 0;
            for(int j = 0; j < 4 ; j++){
                number += bytes[i+j]& 0xff << j*8;
            }
            numbers.add(number);
        }
        return numbers;
    }



}
