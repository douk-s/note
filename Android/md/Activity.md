# Activity 

manifest文件下的**AndroidManifest.xml**是清单文件，清单文件非常重要，它告诉系统我们的app有哪些activity，用到了什么权限等等信息。如果要新建activity，需要在清单中注册。



 layout文件（这里指的是**activity_main.xml**）预设了UI如何摆放。 



  在src/main/java里的文件是控制文件



# Activity 生命周期

## onCreate和onStart的区别

activity的状态区别

- `onCreate`在系统首次创建 Activity 时触发。Activity会在创建后进入*已创建*状态。
- 当 Activity 进入“已开始”状态时，系统会调用此回调。`onStart()` 调用使 Activity 对用户可见，因为应用会为 Activity 进入前台并支持交互做准备。

`onStart()` 方法会非常快速地完成，并且与“已创建”状态一样，Activity 不会一直处于“已开始”状态。一旦此回调结束，Activity 便会进入已恢复状态，系统将调用 `onResume()` 方法。

## onPause和onStop的区别

`onPause()` 执行非常简单，而且不一定要有足够的时间来执行保存操作。 因此，您不应使用 `onPause()` 来保存应用或用户数据、进行网络调用，或执行数据库事务。因为在该方法完成之前，此类工作可能无法完成。

已进入已停止状态，因此系统将调用 `onStop()` 回调。举例而言，如果新启动的 Activity 覆盖整个屏幕，就可能会发生这种情况。

在 `onStop()` 方法中，应用应释放或调整应用对用户不可见时的无用资源。例如，应用可以暂停动画效果，或从细粒度位置更新切换到粗粒度位置更新。 使用 `onStop()` 而非 `onPause()` 可确保与界面相关的工作继续进行，即使用户在多窗口模式下查看您的 Activity 也能如此。 您还应该使用 `onStop()` 执行 CPU 相对密集的关闭操作。

# Android Activity 启动，携带参数启动

## Intent

 通常activity之间的跳转离不开Intent这个类。 Intent，直译为“意图”。我们把信息包裹在intent对象中，然后执行。 比如启动`RelativeLayoutGuideAct`这个activity。 

```java
startActivity(new Intent(getApplicationContext(), RelativeLayoutGuideAct.class));
```

## 带参数的跳转

 在跳转去下一个页面时，我们可能会想携带一些信息到下一个界面去。例如携带一些文本，数字等等。 或者是一个对象。 这些信息我们可以交给Intent，传递到下一个activity去。下一个activity中拿到我们传入的Intent。 

### 携带基本类型和String

```java
Intent intent = new Intent(getApplicationContext(), SendParamsDemo.class);
intent.putExtra(SendParamsDemo.K_INT, 100);
intent.putExtra(SendParamsDemo.K_BOOL, true);
intent.putExtra(SendParamsDemo.K_STR, "Input string");
startActivity(intent);
```

 intent的`putExtra`方法，可以传入参数。它接受1个String作为key，然后是具体参数 

```java
public class SendParamsDemo extends AbsActivity {

    public static final String K_INT = "k_int";
    public static final String K_BOOL = "k_bool";
    public static final String K_STR = "k_str";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gotInput();
    }

    private void gotInput() {
        Intent intent = getIntent();
        if (intent != null) {
            int i = intent.getIntExtra(K_INT, -1);
            boolean b = intent.getBooleanExtra(K_BOOL, false);
            String str = intent.getStringExtra(K_STR);
            Log.d(TAG, "gotInput: i:" + i + ", b: " + b + ", str: " + str);
        } else {
            Log.d(TAG, "gotInput: input null.");
        }
    }
}
```

