package com.watayouxiang.demoshell.tool;

public interface MdFileData {
    /**
     * 获取哪个文件目录
     *
     * @return
     */
    String getInDirPath();

    /**
     * 存储到哪个文件
     *
     * @return
     */
    String getOutFilePath();

    /**
     * 项目地址url
     *
     * @return
     */
    String getProjectUrl();
}
