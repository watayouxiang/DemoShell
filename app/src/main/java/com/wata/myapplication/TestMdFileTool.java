package com.wata.myapplication;

import com.watayouxiang.demoshell.tool.MdFileData;
import com.watayouxiang.demoshell.tool.MdFileTool;

public class TestMdFileTool {

    public static void main(String[] args) {
        new MdFileTool().start(new MdFileData() {
            @Override
            public String getInDirPath() {
                return System.getProperty("user.dir") + "/app/src/main/java/com/wata/myapplication";
            }

            @Override
            public String getOutFilePath() {
                return System.getProperty("user.dir") + "/MdFileTool生成的文件.md";
            }

            @Override
            public String getProjectUrl() {
                return "https://github.com/watayouxiang/DemoShell/tree/master";
            }
        });
    }

}
