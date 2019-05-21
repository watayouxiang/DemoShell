package com.watayouxiang.demoshell.tool;

import java.io.File;

class MdFile {
    private File file;
    private int level;

    MdFile(File file, int level) {
        this.file = file;
        this.level = level;
    }

    /**
     * 获取目录层级标识
     */
    private String getTab() {
        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < level; x++) {
            sb.append("\t");
        }
        return sb.toString();
    }

    /**
     * 获取文件的相对路径
     *
     * @return
     */
    private String getRelativePath() {
        String filePath = file.getAbsolutePath();
        String projectPath = System.getProperty("user.dir");
        return filePath.substring(projectPath != null ? projectPath.length() : 0);
    }

    /**
     * 获取链接txt
     *
     * @param projectUrl 项目url
     * @return
     */
    String getLinkTxt(String projectUrl) {
        return getTab() + "- [" + file.getName() + "](" + projectUrl + getRelativePath() + ")";
    }
}
