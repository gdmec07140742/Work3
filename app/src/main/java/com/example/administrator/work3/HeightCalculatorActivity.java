package com.example.administrator.work3;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


public class HeightCalculatorActivity extends Activity {
    //计算按钮
    private Button caculatorButton;
    //体重输入框
    private EditText weightEditText;
    //男性选择框
    private CheckBox manCheckBox;
    //女性选择框
    private CheckBox womanCheckBox;
    //显示结果
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置页面布局
        setContentView(R.layout.activity_height_calculator);
        //从main.xml页面布局中获得对应的UI控件
        caculatorButton=(Button)findViewById(R.id.caculator);
        weightEditText=(EditText)findViewById(R.id.weight);
        manCheckBox=(CheckBox)findViewById(R.id.man);
        womanCheckBox=(CheckBox)findViewById(R.id.woman);
        resultTextView=(TextView)findViewById(R.id.result);
    }

    @Override
    protected  void onStart(){
        super.onStart();
        //注册事件
        registerEvent();
    }

    /**
     *注册事件
     */
    private void registerEvent()
    {
        //注册按钮事件
        caculatorButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断是否已填写体重数据
                if (!weightEditText.getText().toString().trim().equals("")) {
                    Double weight = Double.parseDouble(weightEditText.getText().toString());
                    StringBuffer sb = new StringBuffer();
                    sb.append("---------评估结果---------");
                    if (manCheckBox.isChecked()) {
                        sb.append("男性标准身高：");
                        //执行计算
                        double result = evaluateHeight(weight, "男");
                        sb.append((int) result + "(厘米)");
                    }
                    if (womanCheckBox.isChecked()) {
                        sb.append("女性标准身高：");
                        //执行计算
                        double result = evaluateHeight(weight, "女");
                        sb.append((int) result + "(厘米)");
                    }
                    //输出页面显示结果
                    resultTextView.setText(sb.toString());
                } else {
                    showMessage("请输入体重！");
                }
            }
        });
    }

    /**
     * 计算机执行处理代码事件
     */
    private double evaluateHeight(double weight,String sex)
    {
        double height;
        if (sex=="男"){
            height=170-(62-weight)/0.6;
        }else {
            height=158-(52-weight)/0.5;
        }
        return height;
    }

    /**
     * 消息提示
     * @param message
     */
    private void showMessage(String message)
    {
        //提示框
        AlertDialog alert=new AlertDialog.Builder(this).create();
        alert.setTitle("系统消息");
        alert.setMessage(message);
        alert.setButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //可在次方法内编写按下确定按钮的处理代码，但在本项目中不需要编写处理代码
            }
        });
        alert.show();//显示窗口
    }

    /**
     * 创建菜单
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 1, Menu.NONE, "退出");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    /**
     * 菜单事件
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1://退出
            finish();
            break;
        }
        return super.onOptionsItemSelected(item);
    }
}
