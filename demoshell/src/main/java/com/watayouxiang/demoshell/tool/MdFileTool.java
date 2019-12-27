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
        // 获取所有文件
        File inFile = new File(data.getInDirPath());
        List<MdFile> files = new LinkedList<>();
        getFiles(inFile, 0, files);

        // 获取MD行列表
        List<String> lines = getLines(files, data.getProjectUrl());

        // 保存目录结构到 outFile
        File outFile = saveFile(lines, data.getOutFilePath());

        // 打印回显
        for (String line : lines) {
            System.out.println(line);
        }
        System.out.println("文件输出路径：" + outFile.getAbsoluteFile());
    }

    /**
     * 将MD链接字符串列表保存成文件
     *
     * @param mdLinkList  MD链接字符串列表
     * @param outFilePath 输出文件路径
     * @return 文件
     */
    private File saveFile(List<String> mdLinkList, String outFilePath) {
        File outFile = new File(outFilePath);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(outFile));
            for (String mdLink : mdLinkList) {
                writer.write(mdLink);
                writer.newLine();
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return outFile;
    }

    /**
     * 获取MD链接列表
     *
     * @param inFileList MD文件列表
     * @param projectUrl 项目的URL
     * @return MD链接列表
     */
    private List<String> getLines(List<MdFile> inFileList, String projectUrl) {
        if (inFileList == null) return new ArrayList<>(0);
        List<String> mdLinkList = new ArrayList<>(inFileList.size());
        for (MdFile mdFile : inFileList) {
            String link = mdFile.getLink(projectUrl);
            mdLinkList.add(link);
        }
        return mdLinkList;
    }

    /**
     * 获取file下的所有文件，保存到fileList中
     *
     * @param file     文件
     * @param level    初始层级
     * @param fileList 文件集合
     */
    private void getFiles(File file, int level, List<MdFile> fileList) {
        fileList.add(new MdFile(file, level));
        level++;

        // 当file不为目录 或者 报SecurityException错时，返回为null
        File[] files = file.listFiles();
        if (files == null) return;

        for (File f : files) {
            // 如果目录下还有目录，那么递归遍历
            getFiles(f, level, fileList);
        }
    }
}
