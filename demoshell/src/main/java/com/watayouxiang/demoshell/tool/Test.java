package com.watayouxiang.demoshell.tool;

class Test {
    public static void main(String[] args) {
        new MdFileTool().start(new MdFileData() {
            @Override
            public String getInDirPath() {
                return System.getProperty("user.dir") + "/demoshell/src/main/java/com/watayouxiang/demoshell";
            }

            @Override
            public String getOutFilePath() {
                return System.getProperty("user.dir") + "/demoshell/src/main/java/com/watayouxiang/demoshell/Test.md";
            }

            @Override
            public String getProjectUrl() {
                return "https://github.com/watayouxiang/DemoShell/tree/master";
            }
        });
    }
}
