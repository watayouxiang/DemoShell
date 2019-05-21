package com.watayouxiang.demoshell.tool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MdFileTool {

    public void start(MdFileData data) {
        //获取 inDir 的目录结构
        File inDir = new File(data.getInDirPath());
        LinkedList<MdFile> localFiles = new LinkedList<>();
        getFileStructure(inDir, 0, localFiles);
        //获取字符串列表
        List<String> stringList = getStringList(localFiles, data.getProjectUrl());
        //保存目录结构到 outFile
        File outFile = new File(data.getOutFilePath());
        saveStringList(stringList, outFile);
        //打印回显
        for (String string : stringList) {
            System.out.println(string);
        }
        System.out.println(outFile.getAbsoluteFile());
    }

    /**
     * 获取字符串列表
     *
     * @param localFiles 本地文件集合
     * @param projectUrl 项目的 url
     * @return
     */
    private List<String> getStringList(LinkedList<MdFile> localFiles, String projectUrl) {
        if (localFiles == null) return new ArrayList<>();
        List<String> stringList = new ArrayList<>();
        for (MdFile localFile : localFiles) {
            String linkTxt = localFile.getLinkTxt(projectUrl);
            stringList.add(linkTxt);
        }
        return stringList;
    }

    /**
     * 将 stringList 保存到 file 中
     *
     * @param stringList 字符串列表
     * @param file       存储文件
     */
    private void saveStringList(List<String> stringList, File file) {
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file));
            for (String string : stringList) {
                bufferedWriter.write(string);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取 file 的目录结构，保存到 localFiles 中
     *
     * @param file       文件或者目录
     * @param level      初始层级
     * @param localFiles 本地文件集合
     */
    private void getFileStructure(File file, int level, List<MdFile> localFiles) {
        localFiles.add(new MdFile(file, level));
        level++;
        // 当file不为目录 或者 报SecurityException错时，返回为null
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                // 如果目录下还有目录，那么递归遍历
                getFileStructure(f, level, localFiles);
            }
        }
    }
}
