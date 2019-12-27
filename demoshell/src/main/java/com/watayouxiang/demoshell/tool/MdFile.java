package com.watayouxiang.demoshell.tool;

import java.io.File;

class MdFile implements Comparable<MdFile> {
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
     * @return 文件相对路径
     */
    private String getFileRelativePath() {
        String fileAbsolutePath = file.getAbsolutePath();
        String projectPath = System.getProperty("user.dir");
        if (projectPath == null) {
            throw new RuntimeException("-- projectPath is null --");
        }
        return fileAbsolutePath.substring(projectPath.length());
    }

    /**
     * 获取链接
     *
     * @param projectUrl 项目url
     * @return 链接
     */
    String getLinkTxt(String projectUrl) {
        String fileRelativePath = getFileRelativePath();
        fileRelativePath = fileRelativePath.replace(File.separator, "/");
        return getTab() + "- [" + file.getName() + "](" + projectUrl + fileRelativePath + ")";
    }

    @Override
    public int compareTo(MdFile o) {
        int num = level - o.level;
        if (num != 0) return num;
        return file.getName().compareTo(o.file.getName());
    }
}
