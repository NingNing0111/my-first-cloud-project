package org.ygcxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 按两次 Shift 打开“随处搜索”对话框并输入 `show whitespaces`，
// 然后按 Enter 键。现在，您可以在代码中看到空格字符。
@SpringBootApplication
public class VideoApplication {
    public static void main(String[] args) {
        SpringApplication.run(VideoApplication.class,args);
    }
}