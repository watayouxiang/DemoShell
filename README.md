# DemoShell


## 1.简介

- 安卓开发，每次写demo总要花些时间搭下架子，写一些重复且没意义的代码。
	- 从而写了该项目，帮助我自己快速搭建起demo项目的外壳。
- Demo项目的`README.md`文档一般就是“文件索引目录”，那么能不能用代码自动生成呢？
	- 从而写了个自动生成工具`MdFileTool.java`

## 2.引入

```
implementation 'com.watayouxiang:DemoShell:1.0.4'
```

最新版本请到 [jcenter仓库](https://dl.bintray.com/watayouxiang/maven/com/watayouxiang/DemoShell/) 查看

## 3.使用

### 功能一：快速搭建demo页面

- 基本页的使用

```
public class HiActivity extends BaseActivity {
    @Override
    protected int getRootViewId() {
        return R.layout.activity_hi;
    }

    @Override
    protected CharSequence getPageTitle() {
        return "新的页面";
    }
}
```

- 列表页的使用

```
public class MainActivity extends ListActivity {
    @Override
    protected ListData getListData() {
        return new ListData()
                .addSection("这是标题")
                .addClick(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(v.getContext(), "hi~", Toast.LENGTH_SHORT).show();
                    }
                })
                .addWeb(this, "https://www.baidu.com")
                .addActivity(this, HiActivity.class);
    }
}
```

### 功能二：快速生成`README.md`文件索引目录

- md文档生成工具的使用

```
public static void main(String[] args) {
	new MdFileTool().start(new MdFileData() {
	    @Override
	    public String getInDirPath() {
		return System.getProperty("user.dir") + "/app/src/main/java/com/watayouxiang/androiddemo";
	    }

	    @Override
	    public String getOutFilePath() {
		return System.getProperty("user.dir") + "/README.md";
	    }

	    @Override
	    public String getProjectUrl() {
		return "https://github.com/watayouxiang/AndroidDemo/tree/master";
	    }
	});
}
```


