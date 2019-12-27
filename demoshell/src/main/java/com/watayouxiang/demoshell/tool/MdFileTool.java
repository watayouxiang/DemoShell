package com.watayouxiang.demoshell.tool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MdFileTool {

    /**
     * 开始生成 markdown 文件
     *
     * @param data 一些配置数据
     */
    public void start(MdFileData data) {
        //获取所有文件
        File inFile = new File(data.getInDirPath());
        List<MdFile> inFileList = new LinkedList<>();
        getFileList(inFile, 0, inFileList);

        //获取MD链接字符串列表
        List<String> mdLinkList = getMdLinkList(inFileList, data.getProjectUrl());

        //保存目录结构到 outFile
        File outFile = new File(data.getOutFilePath());
        saveStringList(mdLinkList, outFile);

        //打印回显
        for (String string : mdLinkList) {
            System.out.println(string);
        }
        System.out.println("文件输出路径：" + outFile.getAbsoluteFile());
    }

    /**
     * 获取MD链接列表
     *
     * @param inFileList MD文件列表
     * @param projectUrl 项目的URL
     * @return MD链接列表
     */
    private List<String> getMdLinkList(List<MdFile> inFileList, String projectUrl) {
        if (inFileList == null) return null;
        List<String> mdLinkList = new ArrayList<>(inFileList.size());
        for (MdFile mdFile : inFileList) {
            String link = mdFile.getLink(projectUrl);
            mdLinkList.add(link);
        }
        return mdLinkList;
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
     * 获取file下的所有文件，保存到fileList中
     *
     * @param file     文件
     * @param level    初始层级
     * @param fileList 文件集合
     */
    private void getFileList(File file, int level, List<MdFile> fileList) {
        fileList.add(new MdFile(file, level));
        level++;

        // 当file不为目录 或者 报SecurityException错时，返回为null
        File[] files = file.listFiles();
        if (files == null) return;

        for (File f : files) {
            // 如果目录下还有目录，那么递归遍历
            getFileList(f, level, fileList);
        }
    }
}
