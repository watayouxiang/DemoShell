package com.watayouxiang.demoshell.tool;

public interface MdFileData {
    /**
     * 输入文件夹
     *
     * @return 输入文件夹路径
     */
    String getInDirPath();

    /**
     * 输出文件
     *
     * @return 输出文件路径
     */
    String getOutFilePath();

    /**
     * 项目地址url
     *
     * @return 项目地址url
     */
    String getProjectUrl();
}
