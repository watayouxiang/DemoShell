# DemoShell


### 1.简介

快速构建你的demo项目的外壳

### 2.引入

仓库地址 [https://dl.bintray.com/watayouxiang/maven/com/watayouxiang/DemoShell/](https://dl.bintray.com/watayouxiang/maven/com/watayouxiang/DemoShell/)

```
implementation 'com.watayouxiang:DemoShell:1.0.1'
```

### 3.使用

- md文档生成工具的使用

```
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
```

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
                .addClick(new HiClick())
                .addWeb(this, "https://www.baidu.com")
                .addActivity(this, "Android知识点", HiActivity.class);
    }

    class HiClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "hi~", Toast.LENGTH_SHORT).show();
        }
    }
}
```


