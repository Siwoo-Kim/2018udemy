package com.siwoo.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-08-12 오후 6:22
 **/


public class PathMain {

    public static void main(String[] args) {
        List<Path> paths = new ArrayList<>();
        //FileSystems.getDefault().getPath() - Relative to Working Directory
        Path path = FileSystems.getDefault().getPath("WorkingDirectory.txt");
        paths.add(path);
        //path = FileSystems.getDefault().getPath("C:\\Users\\Siwoo\\IdeaProjects\\_2018udemy\\files\\Subdirectory.txt");
        //path = FileSystems.getDefault().getPath("C:", "Users", "Siwoo", "IdeaProjects", "_2018udemy", "files", "Subdirectory.txt");
        //Path filePath = FileSystems.getDefault().getPath("files/Subdirectory.txt");
        path = FileSystems.getDefault().getPath("files", "Subdirectory.txt");
        path = Paths.get(".", "files", "Subdirectory.txt");
        // C:\\Users\\Siwoo\IdeaProjects\\_2018udemy\\files\\Subdirectory.txt

        paths.add(path);
        path = FileSystems.getDefault().getPath("..","OutThere.txt");
        paths.add(path);
        path = Paths.get("C:\\Users\\Siwoo\\IdeaProjects\\OutThere.txt");
        paths.add(path);
        //Relative path is better practice.
        // '.' refers to the working directory.
        path = Paths.get(".");
        System.out.println(path.toAbsolutePath());
        path = Paths.get(System.getProperty("user.home"), "downloads");
        Path samePath = Paths.get(System.getProperty("user.home"), ".", "dummy", "..", "downloads");
        assertTrue(path.endsWith(samePath.normalize()));
        System.out.println(samePath.normalize());

        path = FileSystems.getDefault().getPath(".", "files", "..", "files", "Subdirectory.txt");
        paths.add(path);

        paths.forEach(_path ->  assertTrue(_path.toFile().exists()));
        paths.forEach(PathMain::printFile);
    }

    private static void printFile(Path path) {
        try(BufferedReader fileReader = Files.newBufferedReader(path)) {
            String line;
            while ((line=fileReader.readLine()) != null) {
                System.out.println(line);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}
