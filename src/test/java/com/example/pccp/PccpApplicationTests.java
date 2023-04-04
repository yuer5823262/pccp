package com.example.pccp;

import com.example.pccp.common.Constant;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@SpringBootTest
class PccpApplicationTests {
    public static void main(String[] args) {
        String filepath = Constant.TEST_DATA_PATH+"20230113_110754_130k.bin";
        File file = new File (filepath);
        try {
            FileInputStream filelnput = new FileInputStream(file);
            System.out.println(filelnput);
            System.out.println(filelnput.read());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
