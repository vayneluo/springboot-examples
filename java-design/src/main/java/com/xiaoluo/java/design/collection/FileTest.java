package com.xiaoluo.java.design.collection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @classname: FileTest
 * @description: java类作用描述
 * @author: Vayne.Luo
 * @date 2019/11/27 15:08
 */
public class FileTest {

    public static void main(String[] args) {
        InputStream is = null;
        {
            try {
                is = new FileInputStream("D:/emp.txt");
                int read = is.read();
                Thread.sleep(10000000);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if(null!= is){
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("doing nothing");
            }
        }
    }

}
