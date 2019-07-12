# DemoShell


## 1.简介

- 安卓开发，每次写demo时总要花些时间搭下架子，写一些重复的代码。
	- 从而有了本项目，可以快速搭建页面。
- Demo项目的`README.md`文档一般是“文件目录”，那么能不能自动生成呢？
	- 从而有了自动生成工具`MdFileTool.java`。

## 2.引入

implementation 'com.watayouxiang:DemoShell:[版本号](https://dl.bintray.com/watayouxiang/maven/com/watayouxiang/DemoShell/)'

## 3.使用

### 功能一：快速搭建页面

- 1）基本页的使用

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

- 2）列表页的使用

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

- 3）演示页的使用

```
public class TestDemoActivity extends DemoActivity {
    @Override
    protected int getHolderViewId() {
        return R.layout.my_own_view;
    }

    @Override
    protected ListData getListData() {
        return new ListData()
                .addClick("hi", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .addClick("tao", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
    }
}
```

### 功能二：生成`.md`格式的文件目录

- 文件目录生成工具的使用

```
public class TestDemoActivity extends DemoActivity {
    @Override
    protected int getHolderViewId() {
        return R.layout.view_holder;
    }

    @Override
    protected ListData getListData() {
        return new ListData().addClick("TestClick", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
```


