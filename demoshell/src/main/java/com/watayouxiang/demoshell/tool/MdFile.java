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
     * 获取层级标识
     *
     * @return 一个 "level" 对应一个 "tab"
     */
    private String getTabs() {
        StringBuilder builder = new StringBuilder();
        for (int x = 0; x < level; x++) {
            builder.append("\t");
        }
        return builder.toString();
    }

    /**
     * 获取文件相对路径
     *
     * @return 文件相对路径
     */
    private String getRelativePath() {
        String absolutePath = file.getAbsolutePath();
        String projectPath = System.getProperty("user.dir");
        if (projectPath == null) {
            throw new NullPointerException("projectPath is null");
        }
        String relativePath = absolutePath.substring(projectPath.length());
        relativePath = relativePath.replace(File.separator, "/");
        return relativePath;
    }

    /**
     * 获取链接
     *
     * @param projectUrl 项目url
     * @return md格式的链接
     */
    String getLink(String projectUrl) {
        return getTabs() + "- [" + file.getName() + "](" + projectUrl + getRelativePath() + ")";
    }
}
