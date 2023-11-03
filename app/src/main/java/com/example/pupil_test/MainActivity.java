package com.example.pupil_test;

import androidx.appcompat.app.AppCompatActivity;

//import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //private SQLiteOpenHelper helper;
    private UserDao userDo;
    private Subject subject;
    private Button confirm, delete, query,modify;
    private EditText et_result, et_id;
    private TextView tv_subject, tv_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化
        init();
        userDo = new UserDao(this);
        subject = new Subject();
        subject.setSubject(subject.generateSubject());
        tv_subject.setText(subject.getSubject());
    }

    public void init() {
        // 绑定
        tv_subject = findViewById(R.id.subject);
        et_result = findViewById(R.id.result);
        et_id = findViewById(R.id.subject_id);
        confirm = findViewById(R.id.confirm);
        query = findViewById(R.id.query);
        delete = findViewById(R.id.delete);
        modify = findViewById(R.id.modify);
        tv_show = findViewById(R.id.tv_show);

        // 设置监听器
        confirm.setOnClickListener(this);
        query.setOnClickListener(this);
        delete.setOnClickListener(this);
        modify.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.confirm) {
            double answer = subject.getAnswer();
            String erResultText = et_result.getText().toString();

            if (!erResultText.isEmpty()) {
                subject = new Subject(tv_subject.getText().toString(), Double.parseDouble(erResultText), answer);
                long i = userDo.addSubject(subject);
                if (i != -1) {
                    Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show();
                }

                //下一题，并清空输入框
                subject = new Subject();
                subject.setSubject(subject.generateSubject());
                tv_subject.setText(subject.getSubject());
                et_result.setText("");
            }


        } else if (v.getId() == R.id.query) {
            ArrayList<Subject> list = userDo.queryAll();
            if (list.size() == 0) {
                tv_show.setText("没有数据");
            } else {
                StringBuilder buffer = new StringBuilder();
                for (Subject subject : list) {
                    buffer.append("id:").append(subject.getId())
                            .append(" 题目:").append(subject.getSubject())
                            .append(" 输入:").append(subject.getResult())
                            .append(" 答案:").append(subject.getAnswer()).append("\n");
                }
                tv_show.setText(buffer.toString());
            }
        } else if (v.getId() == R.id.delete) {
            String etIdText = et_id.getText().toString();
            if (!etIdText.isEmpty()) {
                int i = userDo.deleteSubject(Integer.parseInt(etIdText));
                if (i != 0) {
                    Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "删除失败", Toast.LENGTH_SHORT).show();
                }

                et_id.setText("");
            }
        } else if (v.getId() == R.id.modify) {
            String etIdText = et_id.getText().toString();
            String erResultText = et_result.getText().toString();
            if (!etIdText.isEmpty() && !erResultText.isEmpty()) {
                subject.setId(Integer.parseInt(etIdText));
                subject.setResult(Double.parseDouble(erResultText));
                int i = userDo.updateSubject(subject);
                if (i != 0) {
                    Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "修改失败", Toast.LENGTH_SHORT).show();
                }
                et_id.setText("");
                et_result.setText("");
            }

        }
    }
}