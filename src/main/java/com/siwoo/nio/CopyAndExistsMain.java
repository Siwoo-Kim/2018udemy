package com.siwoo.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertFalse;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-08-12 오후 6:49
 **/


public class CopyAndExistsMain {

    public static void main(String[] args) {
        //Exception doest not occur, even if we create path instance which does not exists
        Path path = FileSystems.getDefault().getPath("thisfiledoesn'texists.txt");
        System.out.println("Exists = " + Files.exists(path));
        //Files.exists static method provide to tell whether path exists or not
        assertFalse(Files.exists(path));
        System.out.println(path.toAbsolutePath());
        path = Paths.get("C:", "dummy", "file", "whatever.txt");
        assertFalse(Files.exists(path));
        System.out.println(path.toAbsolutePath());

        path = FileSystems.getDefault().getPath("files");
        System.out.println("Exists = " + Files.exists(path));

    }

    private static void printFile(Path path) {
        try(BufferedReader fileReader = Files.newBufferedReader(path)) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                System.out.println(line);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
